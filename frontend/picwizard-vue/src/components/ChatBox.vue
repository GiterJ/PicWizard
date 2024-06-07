<!-- type: left/right -->
<!-- avatar: 头像 -->
<!-- name: 昵称 -->
<!-- text: 对话文字 -->

<template>
    <div class="left clearfix" v-if="type == 'left'">
        <div class="chatbox">
            <div class="name">
                {{ name }}
            </div>
            
            <div class="box">
                {{ text }}
            </div>

            <!-- 用户头像 -->
            <div class="avatar"></div>
        </div>
    </div>

    <!-- 用于白色背景，框为灰色 -->
    <div class="left-gray clearfix" v-else-if="type == 'leftGray'">
        <div class="chatbox">
            <div class="name">
                {{ name }}
            </div>

            <div class="box">
                {{ text }}
            </div>

            <!-- 用户头像 -->
            <div class="avatar"></div>
        </div>
    </div>

    <div class="right clearfix" v-else>
        <div class="chatbox">
            <div class="name">
                {{ name }}
            </div>

            <div class="box">
                {{ text }}
            </div>

            <!-- 用户头像 -->
            <div class="avatar"></div>
        </div>
    </div>
</template>

<script setup>

const props = defineProps(['type', 'avatar', 'text', 'name'])



</script>

<style scoped lang="less">

.right {
    .chatbox {
        margin: 3vw 2vw;
        height: 12vw;
        width: 96vw;
        position: relative;

        .box {
            position: absolute;
            box-sizing: border-box;
            text-align: left;
            max-width: 60vw;
            min-width: 10vw;
            background-color: #1989FA;
            min-height: 7vw;
            border-radius: 5px;
            top: 3.4vw;
            right: 10vw;
            padding: 1.4vw 1.8vw;
            font-size: 11px;
            color: white;
        }

        .name {
            position: absolute;
            font-family: 'Source Han Sans CN';
            font-weight: 400;
            font-size: 10px;
            color: #999999;
            right: 10vw;
            top: -1vw;
        }

        .avatar {
            position: absolute;
            width: 8.5vw;
            height: 8.5vw;
            background-image: url('@/assets/logo.png');
            background-size: cover;
            border-radius: 3vw;
            right: 0;
        }
    }
}

.left {
    font-family: "Source Han Sans CN Regular";

    .chatbox {
        margin: 3vw 2vw;
        height: 12vw;
        width: 88vw;
        position: relative;

        .box {
            // position: absolute;
            float: left;
            margin: 3.4vw 0 0 10vw;
            box-sizing: border-box;
            text-align: left;
            max-width: 60vw;
            min-width: 10vw;
            background-color: #fff;
            min-height: 7vw;
            border-radius: 5px;
            // top: 3.4vw;
            // left: 10vw;
            padding: 1.4vw 1.8vw;
            font-size: 11px;
        }

        .name {
            position: absolute;
            font-family: 'Source Han Sans CN';
            font-weight: 400;
            font-size: 10px;
            color: #999999;
            left: 10vw;
            top: -1vw;
        }

        .avatar {
            position: absolute;
            width: 8.5vw;
            height: 8.5vw;
            background-image: url('@/assets/logo.png');
            background-size: cover;
            border-radius: 3vw;
        }
    }
}

.left-gray {
    font-family: "Source Han Sans CN Regular";

    .chatbox {
        margin: 3vw 2vw;
        height: 12vw;
        width: 88vw;
        position: relative;

        .box {
            // position: absolute;
            float: left;
            margin: 3.4vw 0 0 10vw;
            box-sizing: border-box;
            text-align: left;
            max-width: 60vw;
            min-width: 10vw;
            background-color: #F3F3F4;
            min-height: 7vw;
            border-radius: 5px;
            // top: 3.4vw;
            // left: 10vw;
            padding: 1.4vw 1.8vw;
            font-size: 11px;
        }

        .name {
            position: absolute;
            font-family: 'Source Han Sans CN';
            font-weight: 400;
            font-size: 10px;
            color: #999999;
            left: 10vw;
            top: -1vw;
        }

        .avatar {
            position: absolute;
            width: 8.5vw;
            height: 8.5vw;
            background-image: url('@/assets/logo.png');
            background-size: cover;
            border-radius: 3vw;
        }
    }
}

.clearfix:after {
    content: "";
    display: block;
    height: 0;
    clear: both;
    visibility: hidden;
}

.clearfix {
    *zoom: 1;
}
</style>