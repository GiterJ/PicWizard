<template>
  <BackHeader title="人脸融合"></BackHeader>
  <!-- 合成主页面 -->
  <div class="merge-main">
    <!-- 文件上传框 -->
    <!-- TODO fileuploader需要修改，主要是发送逻辑不一样 -->
    <div class="fileuploader">
      <div class="u1">
        <FileUploader type="img"></FileUploader>
      </div>
      <div class="u2">
        <FileUploader type="img"></FileUploader>
      </div>
    </div>

    <div class="result">
      <!-- TODO 补全照片展示框 -->
      <img src="https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg" class="img">
    </div>

    <div class="button">
      <van-button type="primary" plain icon="arrow-up" block @click="onSend">开始融合！</van-button>
    </div>

  </div>
</template>

<script setup>
import BackHeader from '@/components/BackHeader.vue';

// TODO 补充发送逻辑
const onSend = () => {

}
</script>

<style scoped lang="less">
.merge-main {
  .result {
    margin: 5vh auto;
    width: 90vw;
    height: 40vh;
    border: 8px solid RGBA(203, 213, 248, 0.5);
    display: flex;

    .img {
      width: 100%;
      height: auto;
    }
  }

  .button {
    margin: 2vh auto 0;
    width: 90vw;
  }

  .fileuploader {
    display: flex;
    justify-content: center;
    margin-top: 6vh;

    .u1 {
      text-align: center;
      margin: 0 auto;
    }

    .u2 {
      text-align: center;
      margin: 0 auto;
    }
  }
}
</style>