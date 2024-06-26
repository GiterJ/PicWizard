<template>
  <Header title="图片生成"></Header>
  <!-- 图片生成框 -->
  <div class="uploader-box">
    <div class="note1">
      <van-icon name="circle" color="rgba(84, 115, 232, 0.3)" />
      &nbsp;生成图片：
    </div>
    <div class="uploader">
      <van-image width="100" height="100" :class="picture" round @click="showPreview">
        <template v-slot:loading>
          <van-loading type="spinner" size="20" color="rgba(84, 115, 232, 0.5)" />
        </template>
      </van-image>
      <van-overlay :show="show" @click="show = false">
        <div style="line-height: 100vh;">
          <img :src="picture" alt="" width="100%">
        </div>
      </van-overlay>
    </div>
  </div>
  <!-- 聊天框 -->
  <div class="chat">
    <!-- 气泡框 -->
    <div class="chatboxes">
      <ChatBox v-for="(item, index) in chatHistory" :key="index" :name="item.name" :text="item.text" :type="item.type">
      </ChatBox>
    </div>

    <!-- 输入框 -->
    <div class="typein">
      <van-cell-group inset>
        <van-field v-model="sms" left clearable label="" style="border:2px solid black; border-radius: 25px;">
        </van-field>
      </van-cell-group>
    </div>
    <div class="button1">
      <van-button size="small" round type="primary" @click="sendMessage">
        <van-icon name="guide-o" size="large" /></van-button>
    </div>

    <div v-if="picture != ''" class="button2">
      <van-button size="small" round type="primary" @click="downloadImage">
        <van-icon name="down" size="large" /></van-button>
    </div>
    <div v-else class="button2">
      <van-button size="small" round type="primary" @click="downloadImage" color="gray">
        <van-icon name="down" size="large" /></van-button>
    </div>
  </div>

  <NavBar />
</template>

<script setup>
import Header from '@/components/Header.vue';
import ChatBox from '@/components/ChatBox.vue';
import { reactive, ref } from 'vue';
import { useNetworkStore } from '@/stores/network';
import NavBar from '@/components/NavBar.vue';
import { showToast } from 'vant';

// 聊天记录
const chatHistory = reactive([])

const picture = ref("")

// 聊天输入消息
const sms = ref("")

const sendMessage = async () => {
  if (sms.value == "") {
    return
  }
  // 更新输入
  chatHistory.push({
    name: 'user',
    text: sms.value,
    type: 'right'
  })
  const input = sms.value
  sms.value = ""

  // 发送请求
  const netWorkStore = useNetworkStore()
  const res = await netWorkStore.pgen(input)
  if (res.code != -1) {
    // 最多展示两张生成图片
    picture.value = res.msg
  } else {
    // 错误处理已做
  }
}

// 遮罩层逻辑
const show = ref(false)
const showPreview = () => {
  show.value = true
}

// 根据URL下载图片
const downloadImage = () => {
  if (picture.value != "") {
    let image = new Image();
    image.setAttribute("crossOrigin", "anonymous");
    image.src = picture.value;
    image.onload = () => {
      let canvas = document.createElement("canvas");
      canvas.width = image.width;
      canvas.height = image.height;
      let ctx = canvas.getContext("2d");
      ctx.drawImage(image, 0, 0, image.width, image.height);
      canvas.toBlob(blob => {
        let url = URL.createObjectURL(blob);
        let a = document.createElement("a");
        a.download = "name.jpg";
        a.href = url;
        a.click();
        a.remove();
        // 用完释放URL对象
        URL.revokeObjectURL(url);
      });
    };
  }
  else {
    showToast("生成图片后才能下载！")
  }
}


</script>

<style scoped lang="less">
.uploader-box {
  margin: 2.5vh auto;
  width: 90vw;
  height: 20vh;
  border: 1px solid #F3F3F4;
  background-color: #F3F3F4;
  border-radius: 7vw;
  display: flex;
  justify-content: space-around;
  align-items: center;

  .note1 {
    font-weight: bold;
  }

}

.chat {
  margin: 0 auto;
  width: 99vw;
  height: 61vh;
  position: relative;
  // background-color: #F3F3F4;
  padding: 2vw 2px;

  .chatboxes {
    overflow-y: hidden;
    width: 93vw;
    height: 55vh;
    max-height: 40vh;
  }

  .chatboxes::-webkit-scrollbar {
    display: none;
  }


  .typein {
    position: absolute;
    bottom: 1vh;
    width: 78vw;
    height: 4vh;
    margin: 0 auto;
    left: 0vw;
  }

  .button1 {
    position: absolute;
    bottom: 0.9vh;
    right: 14.5vw;
  }

  .button2 {
    position: absolute;
    bottom: 0.9vh;
    right: 3vw;
  }
}

:deep(.van-cell) {
  padding: 0px 10px 0px 2.5vw;
  line-height: 4.5vh;
  height: 4.3vh;
  font-size: 13px;
}

:deep(.van-button--small) {
  height: 4.1vh;
  width: 9.5vw;
  font-size: 12px;
  background: #1989FA;
  border: 0;
}
</style>