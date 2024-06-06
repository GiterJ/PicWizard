<template>
  <Header title="图片问答"></Header>

  <!-- 聊天框 -->
  <div class="chat">
    <!-- 气泡框 -->
    <div class="chatboxes">
      <ChatBox v-for="(item, index) in chatHistory" :key="index" :name="item.name" :text="item.text" :type="item.type">
      </ChatBox>
    </div>

    <div class="uploader">
      <van-uploader v-model="pictures" :after-read="afterRead" />
    </div>

    <!-- 输入框 -->
    <div class="typein">
      <van-cell-group inset>
        <van-field v-model="sms" left clearable label="">
        </van-field>
      </van-cell-group>
    </div>
    <div class="button">
      <van-button size="small" type="primary" @click="sendMessage">发送</van-button>
    </div>
  </div>
</template>

<script setup>
import Header from '@/components/Header.vue';
import ChatBox from '@/components/ChatBox.vue';
import { reactive, ref } from 'vue';

// 聊天记录
// TODO 添加聊天记录
const chatHistory = reactive([
  {
    name: 'user',
    text: '帮我生成一章小猫图片',
    type: 'right'
  },
  {
    name: 'assistant',
    text: '好的，已为您生成',
    type: 'left'
  }
])

// TODO 添加接收图片逻辑
const pictures = ref([
  {
    url: "https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg",
  }
])

// 聊天输入消息
const sms = ref("")

// TODO 发送事件函数
const sendMessage = () => {
  
}

</script>

<style scoped lang="less">
.chat {
  width: 100vw;
  height: 88vh;
  background-color: #fff;
  position: relative;

  .chatboxs {
    overflow-y: auto;
    width: 93vw;
    height: 80vh-6vh;
  }

  .chatboxs::-webkit-scrollbar {
    display: none;
  }

  .uploader {
    position: absolute;
    bottom: 5vh;
    right: 1vw;
  }

  .typein {
    position: absolute;
    bottom: 1vh;
    width: 78vw;
    height: 4.5vh;
    margin: 0 auto;
    left: -1vw;
  }

  .button {
    position: absolute;
    bottom: 1.2vh;
    right: 2.5vw;
  }
}
</style>