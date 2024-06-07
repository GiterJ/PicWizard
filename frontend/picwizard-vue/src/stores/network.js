import { defineStore } from "pinia";
import axios from "axios";
import { showToast } from "vant";

export const useNetworkStore = defineStore("network", () => {
    const api = axios.create({
        baseURL: "http://127.0.0.1:4523/m1/4612346-0-default",
        timeout: 10000,
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });

    /**
     * 获取问答结果
     * @param {输入图片} image 
     * @param {输入问题} question 
     * @returns 成功返回data对象，失败其code为-1
     */
    const pqa = async (image, question) => {
        // 构造formdata对象，以传递数据
        const formData = new FormData();
        formData.append("image", image)
        formData.append("question", question)
        // 发送请求
        try {
            const res = await api.post("/picqa", formData)
            // 成功返回
            if (res.status == 200) {
                return res.data
            } else {
                showToast("error!", res.data.msg)
                return res.data
            }
        }
        // 失败返回网络错误
        catch {
            console.log("error", "网络错误")
            return {
                code: -1,
                msg: "网络错误"
            }
        }
    };

    // TODO 这里用的是query参数吗
    /**
     * 获取图片生成结果
     * @param {提示词} prompt 
     * @returns 正常返回data对象，失败其code为-1
     */
    const pgen = async (prompt) => {
        const formData = new FormData();
        formData.append("prompt", prompt)
        try {
            const res = await api.post("/generatepic", formData)
            if (res.status == 200) {
                return res.data
            } else {
                showToast("error!", res.data.msg)
                return res.data
            }
        }catch {
            return {
                code: -1,
                msg: "网络错误"
            }
        }
    }

    return { api, pgen, pqa }
});