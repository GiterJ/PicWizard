<template>
  <BackHeader title="AI美颜"></BackHeader>
  <div class="fix-container">
    <!-- 文字说明 -->
    <div class="text1">
      <van-icon name="circle" color="rgba(84, 115, 232, 0.3)" />&nbsp;图片上传
    </div>
    <!-- 文件上传框 -->
    <div class="uploader">
      <FileUploader type="img" :after-read-func="afterReadFunc" ></FileUploader>
      <div class="img-demo">
        <div class="over-text">
          示&nbsp;例&nbsp;图&nbsp;片
        </div>
      </div>
    </div>

    <!-- 文字说明2 -->
    <div class="text2">
      <van-icon name="circle" color="rgba(84, 115, 232, 0.3)" />&nbsp;美颜结果
    </div>
    <!-- 展示框 -->
    <div class="show">
      <img :src="genPicture" alt="">
    </div>

    <div class="button">
      <van-button type="primary" plain round icon="arrow-up" block @click="onSend">开始修复</van-button>
    </div>
  </div>
</template>

<script setup>
import BackHeader from '@/components/BackHeader.vue';
import FileUploader from '@/components/FileUploader.vue';
import { useNetworkStore } from '@/stores/network';
import { ref } from 'vue';

// 用户上传的文件
const userPicture = ref("")
const afterReadFunc = (file)=>{
  userPicture.value = file.content
}

// TODO 完成发送逻辑
const genPicture = ref("")
const onSend = async () => {
  // 用户必须先上传图片
  if (userPicture.value == "") {
    showToast("请上传图片")
    return;
  }

  const networkStore = useNetworkStore()
  const res = await networkStore.pbutify(userPicture.value)
  if (res.code != -1) {
    genPicture.value = res.msg
  } else {
    // 已做错误处理
  }
}
</script>

<style scoped lang="less">
.fix-container {
  width: 100vw;

  .text1 {
    margin-top: 4vh;
    padding: 0 9vw;
  }

  .text2 {
    margin-top: 2vh;
    margin-bottom: 2vh;
    padding: 0 9vw;
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