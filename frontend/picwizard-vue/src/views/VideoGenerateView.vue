<template>
  <!-- 头部 -->
  <Header title="视频生成"></Header>

  <div class="videogen-container">

    <!-- 文件上传框 -->
    <div class="fileuploader">
      <div class="u1">
        <FileUploader type="img"></FileUploader>
      </div>
      <div class="u2">
        <FileUploader type="audio"></FileUploader>
      </div>
    </div>

    <!-- 文字提示 -->
    <div class="text">
      <div class="pic">上传图片</div>
      <div class="aud">上传音频&nbsp;&nbsp;</div>
    </div>
    <!-- 视频播放 -->
    <div class="video">
      <!-- 播放生成的视频 -->
      <video id="video" autoplay="true" :src="videoUrl" style="width: 100%; height: 100%; object-fit: cover"></video>
    </div>

  </div>
</template>

<script setup>
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import FileUploader from '@/components/FileUploader.vue';
import 'vant/es/toast/style'

const router = useRouter()

const videoUrl = ref('http://vjs.zencdn.net/v/oceans.mp4')

</script>

<style scoped lang="less">
.nav-bar {
  background-color: #F2F5FF;
  --van-nav-bar-icon-color: #333333;
}

.videogen-container {
  text-align: center;
  width: 100vw;
  margin-top: 4.5vw;
  overflow: auto;

  .video {
    width: 90vw;
    height: 40vh;
    margin: 5vh auto 0;
    border-radius: 5px;
    border: 8px solid RGBA(203, 213, 248, 0.5);
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

  .text {
    display: flex;
    justify-content: center;
    margin-top: 1vh;

    .pic {
      margin: 0 auto;
    }

    .aud {
      margin: 0 auto;
    }
  }

}
</style>