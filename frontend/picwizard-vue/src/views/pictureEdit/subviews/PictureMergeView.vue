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
      <img :src="genPicture" alt="" class="img">
    </div>

    <div class="button">
      <van-button type="primary" plain round icon="arrow-up" block @click="onSend">开始融合</van-button>
    </div>

  </div>

  <FloatBubble v-model:genPicture="genPicture"/>

  <NavBar />

</template>

<script setup>
import BackHeader from '@/components/BackHeader.vue';
import { useNetworkStore } from '@/stores/network';
import { showToast } from 'vant';
import { ref, reactive } from 'vue';
import NavBar from '@/components/NavBar.vue';

// 图片位置
const genPicture = ref("")

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
    genPicture.value = "data:image/jpeg;base64,"+res.msg
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
    justify-content: center;

    .img {
      width: 100%;
      height: auto;
      margin: 0 auto;
    }
  }

  .button {
    margin: 2vh auto 0;
    width: 90vw;
  }


}
</style>