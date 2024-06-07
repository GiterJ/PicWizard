import { defineStore } from "pinia";
import axios from "axios";
import { Form, showToast } from "vant";

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

    // TODO 等待后端改为body传参数
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
        } catch {
            return {
                code: -1,
                msg: "网络错误"
            }
        }
    }

    /**
     * 获取融合图片
     * @param {用户图片1} target 
     * @param {用户图片2} template 
     * @returns 成功返回data对象，msg中为base64图片，失败code为-1
     */
    const pmerge = async (target, template) => {
        const formData = new FormData();
        formData.append("template", template)
        formData.append("target", target)
        try {
            const res = await api.post("/facemerge", formData)
            if (res.status == 200) {
                return res.data
            } else {
                showToast("error!", res.data.msg)
                return res.data
            }
        } catch {
            return {
                code: -1,
                msg: "网络错误"
            }
        }
    }

    /**
     * 人物动漫化
     * @param {原图片} image 
     * @returns 成功返回正常data对象，失败返回code为-1
     */
    const pfix = async (image) => {
        const formData = new FormData()
        formData.append("image", image)
        try {
            const res = await api.post("/faceanime", formData)
            if (res.status == 200) {
                console.log(res.data)
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

    /**
     * ai美颜
     * @param {原图片} image 
     * @returns 成功返回正常data对象，失败返回code为-1
     */
    const pbutify = async (image) => {
        const formData = new FormData()
        formData.append("image", image)
        try {
            const res = await api.post("/beauty", formData)
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

    const pedit = async (image, prompt) => {
        const formData = new FormData();
        formData.append('image', image);
        formData.append('prompt', prompt);
        try {
            const res = await api.post("/attributeedit", formData)
            if(res.status == 200) {
                return res.data
            }
            else {
                showToast("Error", res.data.msg)
                return res.data
            }
        }catch {
            return {
                code: -1,
                msg: "网络错误"
            }
        }
    }

    return { api, pgen, pqa, pmerge, pfix, pbutify, pedit }
});