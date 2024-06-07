<template>
    <Header title="图片问答"></Header>
    <div class="qa-container">
        <!-- 文件上传框 -->
        <div class="uploader-box">
            <div class="note1">请上传图片</div>
            <div class="uploader">
                <van-uploader v-model="picture" :after-read="afterRead" :max-count="1" preview-size="12vh" />
            </div>
        </div>

        <!-- 聊天框 -->
        <div class="chat">
            <!-- 气泡框 -->
            <div class="chatboxes">
                <ChatBox v-for="(item, index) in chatHistory" :key="index" :name="item.name" :text="item.text"
                    :type="item.type"></ChatBox>
            </div>

            <!-- 输入框 -->
            <div class="typein">
                <van-cell-group inset>
                    <van-field v-model="sms" left clearable label=""
                        style="border:2px solid black;border-radius: 25px;">
                    </van-field>
                </van-cell-group>
            </div>
            <div class="button">
                <van-button size="small" round type="primary" @click="sendMessage">
                    <van-icon name="guide-o" size="large" /></van-button>
            </div>
        </div>
    </div>


</template>

<script setup>
import Header from '@/components/Header.vue';
import ChatBox from '@/components/ChatBox.vue';
import { useNetworkStore } from '@/stores/network';
import { reactive, ref } from 'vue';
import { showToast } from 'vant';

// 聊天记录
const chatHistory = reactive([
    {
        name: 'user',
        text: '这张图片里有什么',
        type: 'right'
    },
    {
        name: 'PicWizard',
        text: '这里面是一条狗',
        type: 'left'
    },
])

// TODO 补充上传处理函数 base64编码
const afterRead = () => {
    // TODO 可以添加一个指示逻辑，表示上传成功

}

// 输入框消息文字
const sms = ref("")

const sendMessage = async () => {
    // 页面更新
    chatHistory.push({
        name: 'user',
        text: sms.value,
        type: 'right'
    })
    const input = sms.value
    // 清空输入框
    sms.value = ""

    // 发送图片和文本
    const netWorkStore = useNetworkStore()
    if (picture.value.length == 0) {
        chatHistory.push({
            name: 'PicWizard',
            text: '请上传图片',
            type: 'left'
        })
    }
    else {
        const res = await netWorkStore.pqa(picture.value[0].content, input)
        if (code != -1) {
            chatHistory.push({
                name: 'PicWizard',
                text: res.msg,
                type: 'left'
            })
        }
        else {
            // 失败处理已做
        }
    }
    
}

// 接受到的图片
const picture = ref([])

</script>

<style scoped lang="less">
.qa-container {
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

        .uploader {}
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