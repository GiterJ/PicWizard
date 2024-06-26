<template>
  <BackHeader title="属性编辑"></BackHeader>
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

    <!-- 功能选择 -->
    <div class="text3">
      <van-icon name="circle" color="rgba(84, 115, 232, 0.3)" />&nbsp;功能选择
    </div>
    <div class="choose">
      <van-field v-model="funcChoose" is-link readonly placeholder="点击选择功能" @click="showPicker = true" />
      <van-popup v-model:show="showPicker" round position="bottom">
        <van-picker :columns="columns" @cancel="showPicker = false" @confirm="onConfirm" />
      </van-popup>
    </div>
    
    <!-- 文字说明2 -->
    <div class="text2">
      <van-icon name="circle" color="rgba(84, 115, 232, 0.3)" />&nbsp;属性编辑结果
    </div>

    <!-- 展示框 -->
    <div class="show">
      <img :src="genPicture" alt="">
    </div>

    <!-- 开始按钮 -->
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
import { ref } from 'vue';
import NavBar from '@/components/NavBar.vue';
import FloatBubble from '@/components/FloatBubble.vue';

// 选择框用
const funcChoose = ref('');
const showPicker = ref(false);
let chooseValue = ""
const onConfirm = ({ selectedOptions }) => {
  showPicker.value = false;
  funcChoose.value = selectedOptions[0].text;
  chooseValue = selectedOptions[0].value;
};
const columns = [
  { text: '变男人', value: 'TO_MALE' },
  { text: '变女人', value: 'TO_FEMALE' },
  { text: '变小孩', value: 'TO_KID' },
  { text: '变老人', value: 'TO_OLD' },
];

// 用户上传的文件
const userPicture = ref("")
const afterReadFunc = (file)=>{
  userPicture.value = file.content.slice('data:image/jpeg;base64,'.length)
}

const genPicture = ref("")
const onSend = async () => {
  // 用户必须先上传图片
  if (userPicture.value == "") {
    showToast("请上传图片")
    return;
  }

  if(funcChoose.value = ""){
    showToast("请选择功能")
    return;
  }

  const networkStore = useNetworkStore()
  console.log(funcChoose.value)
  const res = await networkStore.pedit(userPicture.value, chooseValue)
  if (res.code != -1) {
    genPicture.value = "data:image/jpeg;base64,"+res.msg
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
    margin-bottom: 1vh;
    padding: 0 9vw;
  }

  .text3 {
    margin-top: 1vh;
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
      margin: 0 auto;
    }
  }

  .choose {
    width: 86vw;
    margin: 0 auto;
  }

  .button {
    margin: 2vh auto 0;
    width: 90vw;
  }
}
</style>