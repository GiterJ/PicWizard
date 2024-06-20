<!-- FIXME 修复聊天框无限扩大的问题 -->

<template>
  <Header title="图片生成"></Header>
  <!-- 图片生成框 -->
  <div class="uploader-box">
    <div class="note1">
      <van-icon name="circle" color="rgba(84, 115, 232, 0.3)" />
      &nbsp;生成图片：
    </div>
    <div class="uploader">
      <van-uploader disabled v-model="pictures" :after-read="afterRead" :max-count="1" preview-size="12vh" />
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
        <van-field v-model="sms" left clearable label="" style="border:2px solid black;border-radius: 25px;">
        </van-field>
      </van-cell-group>
    </div>
    <div class="button">
      <van-button size="small" round type="primary" @click="sendMessage">
        <van-icon name="guide-o" size="large" /></van-button>
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

// 聊天记录
// TODO 添加聊天记录
const chatHistory = reactive([
  // {
  //   name: 'user',
  //   text: '帮我生成一章小猫图片',
  //   type: 'right'
  // },
  // {
  //   name: 'PicWizard',
  //   text: '好的，已为您生成',
  //   type: 'left'
  // }
])

// TODO 添加接收图片逻辑
const pictures = ref([
])

// 聊天输入消息
const sms = ref("")

// TODO 发送事件函数
const sendMessage = async () => {
  if(sms.value == ""){
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
    if (pictures.value.length < 2) {
      pictures.value.push({
        "url": res.msg,
      })
    }
    // 如果大于两张图片，需要删去第一张
    else {
      pictures.value[0] = pictures.value[1]
      pictures.value[1] = { "url": res.data.msg }
    }
  } else {
    // 错误处理已做
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

  .chatboxs {
    overflow-y: hidden;
    width: 93vw;
    height: 40vh;
    max-height: 40vh;
  }

  .chatboxs::-webkit-scrollbar {
    display: none;
  }


  .typein {
    position: absolute;
    bottom: 1vh;
    width: 90vw;
    height: 4vh;
    margin: 0 auto;
    left: 0vw;
  }

  .button {
    position: absolute;
    bottom: 0.9vh;
    right: 2.7vw;
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
  width: 10.5vw;
  font-size: 12px;
  background: #1989FA;
  border: 0;
}
</style>