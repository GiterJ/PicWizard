<template>
    <van-floating-bubble v-if="props.genPicture != ''" icon="down" @click="onDownload" v-model:offset="offset"
        class="bubble-button" style="--van-floating-bubble-background:#5473e8;--van-floating-bubble-size:50px;
            box-shadow: 0px 0px 20px 0px #5473e8;" />
    <van-floating-bubble v-else icon="down" @click="onDownloadButNotgen" v-model:offset="offset" 
    class="bubble-button" style="--van-floating-bubble-background:gray;--van-floating-bubble-size:50px;
            box-shadow: 0px 0px 20px 0px gray;" />
</template>

<script setup>

import { ref } from 'vue'
import { defineProps } from 'vue';

const props = defineProps(['genPicture'])
// 气泡位置
const offset = ref({ y: 550 })
// 下载函数
const onDownload = () => {
    var base64 = props.genPicture; // imgSrc 就是base64
    var byteCharacters = atob(
        base64.replace(/^data:image\/(png|jpeg|jpg);base64,/, "")
    );
    var byteNumbers = new Array(byteCharacters.length);
    for (var i = 0; i < byteCharacters.length; i++) {
        byteNumbers[i] = byteCharacters.charCodeAt(i);
    }
    var byteArray = new Uint8Array(byteNumbers);
    var blob = new Blob([byteArray], {
        type: undefined,
    });
    var aLink = document.createElement("a");
    aLink.download = "image.jpg"; //这里写保存时的图片名称
    aLink.href = URL.createObjectURL(blob);
    aLink.click();
}

const onDownloadButNotgen = () => {
    showToast("请先上传图片")
}

</script>

<style scoped lang="less"></style>