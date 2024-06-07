<!-- 文件上传组件，分类型呈现不同的上传表现，包含与服务器交互 -->
<!-- 需要与父组件双向传递参数，以实现获取参数及校验 -->
<!-- props: 1. type:上传的文件类型(img/audio/video) -->

<template>
    <!-- 图片上传组件 -->
    <div class="img-uploader" v-if="props.type == 'img'">
        <van-uploader :after-read="afterRead" v-model="fileListImg" accept="image/*" max-count="1" />
    </div>

    <!-- 音频上传组件 -->
    <div class="audio-uploader" v-else-if="props.type == 'audio'">
        <van-uploader :after-read="afterRead" accept="audio/*" max-count="1" />
    </div>

    <!-- 视频上传组件 -->
    <div class="video-uploader" v-else-if="props.type == 'video'">
        <van-uploader :after-read="afterRead" accept="video/*" max-count="1" />
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

const props = defineProps(['type', 'afterReadFunc'])

/**
 * afterRead，用于调用父组件函数，将读到的文件返回
 * @param {接收到的文件} recvfile 
 */
const afterRead = (recvfile) => {
    props.afterReadFunc(recvfile)
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