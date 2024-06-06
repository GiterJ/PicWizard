<!-- 文件上传组件，分类型呈现不同的上传表现，包含与服务器交互 -->
<!-- 需要与父组件双向传递参数，以实现获取参数及校验 -->
<!-- props: 1. type:上传的文件类型(img/audio/video) -->

<template>
    <!-- 图片上传组件 -->
    <div class="img-uploader" v-if="props.type == 'img'">
        <van-uploader :after-read="afterReadImg" v-model="fileListImg" accept="image/*" max-count="1" />
    </div>

    <!-- 音频上传组件 -->
    <div class="audio-uploader" v-else-if="props.type == 'audio'">
        <van-uploader :after-read="afterReadAudio" accept="audio/*" max-count="1" />
    </div>

    <!-- 视频上传组件 -->
    <div class="video-uploader" v-else-if="props.type == 'video'">
        <van-uploader :after-read="afterReadVideo" accept="video/*" max-count="1" />
    </div>

    <div class="unable" v-else>
        <van-uploader disabled accept="image/*" max-count="1" />
    </div>

</template>

<script setup>
import { ref } from 'vue';
import 'vant/es/toast/style'
import { showToast } from 'vant';
// 图片文件列表
const fileListImg = ref([])

// const props = defineProps(['type', 'addUrl'])
const props = defineProps(['type'])

// TODO 修改三个读后处理函数，改为新的请求地址
/**
 * 图片读后处理函数
 */
const afterReadImg = async (recvfile) => {
    let file = recvfile.file
    try {
        // FIXME 修改这里
        let res = 0
        if (res.code === 0) {
            props.addUrl('img', res.data)
            showToast('图片上传成功')
        } else {
            showToast("图片上传失败" + res.message)
        }
    } catch (err) {
        // props.addUrl('img', 'fail')
        showToast("文件上传失败！网络或服务器错误")
    }
}

/**
 * 音频读后处理函数
 */
const afterReadAudio = async (recvfile) => {
    let file = recvfile.file
    // 上传图片并获取桶路径
    try {
        // FIXME 修改这里
        let res = 0
        if (res.code === 0) {
            props.addUrl('audio', res.data)
            showToast('音频上传成功')
        } else {
            showToast("音频上传失败" + res.message)
        }
    } catch (err) {
        // 添加失败参数传递
        // props.addUrl('audio', 'fail')
        showToast("文件上传失败！网络或服务器错误")
    }
}

/** 
 * 视频读后处理函数
 */
const afterReadVideo = async (recvfile) => {
    let file = recvfile.file
    // 上传图片并获取桶路径
    try {
        // FIXME 修改这里
        let res = 0
        if (res.code === 0) {
            props.addUrl('video', res.data)
            showToast('视频上传成功')
        } else {
            showToast("视频上传失败" + res.message)
        }
    } catch (err) {
        // 添加失败参数传递
        // props.addUrl('video', 'fail')
        showToast("文件上传失败！网络或服务器错误")
    }
}

</script>

<style scoped lang="less">
// 整体样式
:deep(.van-uploader__upload) {
    background-color: #F3F3F4;
    width: 35vw;
    height: 39vw;
    border-radius: 20px;
}

// 内部图标
:deep(.van-icon-photograph:before) {
    content: '\2b';
}

// 预览样式
:deep(.van-uploader__preview-image) {
    width: 35vw;
    height: 39vw;
    border-radius: 20px;
}
</style>