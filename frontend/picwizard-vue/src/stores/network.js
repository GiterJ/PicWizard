import { defineStore } from "pinia";
import axios from "axios";
import { showToast } from "vant";

export const useNetworkStore = defineStore("network", () => {
    const api = axios.create({
        baseURL: "http://192.168.1.105:8081",
        // baseURL: "/api",
        timeout: 40000,
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
            console.log(res)
            // 成功返回
            if (res.status == 200) {
                return res.data
            } else {
                showToast("error!", res.data.msg)
                return res.data
            }
        }
        // 失败返回网络错误
        catch (err){
            showToast("error 网络错误" + err)
            console.log("error", err)
            return {
                code: -1,
                msg: "网络错误"
            }
        }
    };

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

    const pregister = async (name, password) => {
        const formData = new FormData();
        formData.append('name', name);
        formData.append('password', password);
        try {
            const res = await api.post("/regis", formData)
            console.log(res)
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

    const plogin = async (name, password) => {
        const formData = new FormData();
        formData.append('name', name);
        formData.append('password', password);
        try {
            const res = await api.post("/login", formData)
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

    const pfog = async (image) => {
        const formData = new FormData();
        formData.append('image', image)
        try {
            const res = await api.post("/picdefog", formData)
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

    const pcolor = async (image) => {
        const formData = new FormData();
        formData.append('image', image)
        try {
            const res = await api.post("/piccolor", formData)
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

    const pclear = async (image) => {
        const formData = new FormData();
        formData.append('image', image)
        try {
            const res = await api.post("/picclear", formData)
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


    return { api, pgen, pqa, pmerge, pfix, pbutify, pedit, pregister, plogin, pfog, pcolor, pclear }
});