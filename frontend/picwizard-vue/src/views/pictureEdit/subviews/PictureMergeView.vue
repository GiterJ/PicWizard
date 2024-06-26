<template>
  <BackHeader title="人脸融合"></BackHeader>
  <!-- 合成主页面 -->
  <div class="merge-main">
    <!-- 文字提示框1 -->
    <div class="text1">
      <van-icon name="circle" color="rgba(84, 115, 232, 0.3)" />&nbsp;图片上传
    </div>
    <!-- 文件上传框 -->
    <div class="fileuploader">
      <div class="u1">
        <van-uploader :after-read="afterReadTemplate" preview-size="30vw" :max-count="1" v-model="vmodelPicture1"/>
      </div>
      <div class="u2">
        <van-uploader :after-read="afterReadTarget" preview-size="30vw" :max-count="1" v-model="vmodelPicture2" />
      </div>
    </div>

    <!-- 文字提示框2 -->
    <div class="text2">
      <van-icon name="circle" color="rgba(84, 115, 232, 0.3)" />&nbsp;图片输出
    </div>

    <div class="result">
      <!-- TODO 补全照片展示框 -->
      <img :src="genPictureUrl" class="img">
    </div>

    <div class="button">
      <van-button type="primary" plain round icon="arrow-up" block @click="onSend">开始融合</van-button>
    </div>

  </div>

  <NavBar />

</template>

<script setup>
import BackHeader from '@/components/BackHeader.vue';
import { useNetworkStore } from '@/stores/network';
import { showToast } from 'vant';
import { ref, reactive } from 'vue';
import NavBar from '@/components/NavBar.vue';

// 图片位置
const genPictureUrl = ref("data:image/jpeg;base64,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")

// 用于展示图片
const vmodelPicture1 = ref([])
const vmodelPicture2 = ref([])

// 用户上传的图片
const userPicture = reactive({
  target: "",
  template: ""
})

const onSend = async () => {
  if(vmodelPicture1.value.length < 1 || vmodelPicture2.value.length <1){
    showToast("请上传需要合并的两张图像")
    return;
  }

  const networkStore = useNetworkStore()
  const res = await networkStore.pmerge(userPicture.target, userPicture.template)
  if (res.code != -1) {
    // TODO 待验证能否直接用base64放到src中展示图片
    genPictureUrl.value = "data:image/jpeg;base64,"+res.msg
  } else {
    // 已做错误处理
  }
}

// 模板图片存储
const afterReadTemplate = (file) => {
  userPicture.template = file.content.slice('data:image/jpeg;base64,'.length);
}

// 目标图片存储
const afterReadTarget = (file) => {
  userPicture.target = file.content.slice('data:image/jpeg;base64,'.length);
}

</script>

<style scoped lang="less">
.merge-main {
  .text1 {
    margin-top: 4vh;
    padding: 0 9vw;
  }

  .fileuploader {
    display: flex;
    justify-content: center;
    margin-top: 1vh;

    .u1 {
      text-align: center;
      margin: 0 auto;
    }

    .u2 {
      text-align: center;
      margin: 0 auto;
    }
  }

  .text2 {
    margin-top: 2vh;
    padding: 0 9vw;
  }

  .result {
    margin: 2vh auto;
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


}
</style>