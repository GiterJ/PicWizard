<template>
  <BackHeader title="人脸动漫化"></BackHeader>
  <div class="fix-container">
    <!-- 文字说明 -->
    <div class="text1">
      <van-icon name="circle" color="rgba(84, 115, 232, 0.3)" />&nbsp;图片上传
    </div>
    <!-- 文件上传框 -->
    <div class="uploader">
      <FileUploader type="img" :after-read-func="afterReadFunc"></FileUploader>
      <div class="img-demo">
        <div class="over-text">
          示&nbsp;例&nbsp;图&nbsp;片
        </div>
      </div>
    </div>

    <!-- 文字说明2 -->
    <div class="text2">
      <van-icon name="circle" color="rgba(84, 115, 232, 0.3)" />&nbsp;动漫化图片
    </div>
    <!-- 展示框 -->
    <div class="show">
      <img :src="genPicture" alt="">
    </div>

    <div class="button">
      <van-button type="primary" plain round icon="arrow-up" block @click="onSend">开始处理</van-button>
    </div>
  </div>

  <FloatBubble v-model:genPicture="genPicture"/>
  <NavBar />

</template>

<script setup>
import BackHeader from '@/components/BackHeader.vue';
import FileUploader from '@/components/FileUploader.vue';
import { useNetworkStore } from '@/stores/network';
import { showToast } from 'vant';
import NavBar from '@/components/NavBar.vue';
import { ref } from 'vue';
import 'vant/es/toast/style'

// 用户上传的图片
const userPicture = ref("")
// 生成的图片
const genPicture = ref("")

const onSend = async () => {
  // 用户必须先上传图片
  if (userPicture.value == "") {
    showToast("请上传图片")
    return;
  }

  const networkStore = useNetworkStore()
  const res = await networkStore.pfix(userPicture.value)
  if (res.code != -1) {
    genPicture.value = 'data:image/jpeg;base64,' + res.msg
  } else {
    // 已做错误处理
  }
}

const afterReadFunc = (file) => {
  userPicture.value = file.content.slice('data:image/jpeg;base64,'.length)
  // userPicture.value = file.content
}

// 气泡位置
const offset = ref({ y: 550 })
// 下载函数
const onDownload = () => {
  var base64 = userPicture.value; // imgSrc 就是base64哈
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

<style scoped lang="less">
.fix-container {
  width: 100vw;

  .text1 {
    margin-top: 4vh;
    padding: 0 9vw;
  }

  .uploader {
    margin-top: 8px;
    display: flex;
    justify-content: space-around;
    align-items: center;

    .img-demo {
      background-image: url("../../../assets/images.jpg");
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

  .text2 {
    margin-top: 2vh;
    margin-bottom: 2vh;
    padding: 0 9vw;
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

  .button {
    margin: 2vh auto 0;
    width: 90vw;
  }
}
</style>