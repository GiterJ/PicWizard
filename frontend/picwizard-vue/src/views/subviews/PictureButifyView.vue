<template>
  <BackHeader title="AI美颜"></BackHeader>
  <div class="fix-container">
    <!-- 文字说明 -->
    <div class="text1">
      <p>&nbsp;&nbsp;请上传图片</p>
    </div>
    <!-- 文件上传框 -->
    <div class="uploader">
      <FileUploader type="img"></FileUploader>
      <div class="img-demo">
        <div class="over-text">
          示&nbsp;例&nbsp;图&nbsp;片
        </div>
      </div>
    </div>

    <!-- 文字说明2 -->
    <div class="text2">
      &nbsp;&nbsp;结果
    </div>
    <!-- 展示框 -->
    <div class="show">
      <img src="" alt="">
    </div>

    <div class="button">
      <van-button type="primary" plain icon="arrow-up" block @click="onSend">开始修复！</van-button>
    </div>
  </div>
</template>

<script setup>
import BackHeader from '@/components/BackHeader.vue';
import FileUploader from '@/components/FileUploader.vue';

// TODO 完成发送逻辑
const onSend = () => {

}
</script>

<style scoped lang="less">
.fix-container {
  width: 100vw;

  .text1 {
    margin-top: 5vh;
    font-size: 500;
  }

  .text2 {
    margin-top: 3vh;
  }

  .uploader {
    margin-top: 2vh;
    display: flex;
    justify-content: space-around;
    align-items: center;

    .img-demo {
      background-image: url("https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg");
      background-repeat: no-repeat;
      background-size: cover;
      width: 35vw;
      height: 39vw;
      border-radius: 15px;
      display: flex;
      flex-direction: column-reverse;
      margin-right: 6px;

      .over-text {
        text-align: center;
        width: 35vw;
        height: 7vw;
        background-color: rgba(0, 0, 0, 0.3);
        color: white;
        border-radius: 0 0 15px 15px;
        line-height: 7vw;
        font-size: 3.7vw;
      }
    }
  }

  .button {
    margin: 2vh auto 0;
    width: 90vw;
  }

  .show {
    width: 90vw;
    height: 40vh;
    border: 8px solid RGBA(203, 213, 248, 0.5);
    display: flex;
    margin: 0 auto;

    .img {
      width: 100%;
      height: auto;
    }
  }
}
</style>