<template>
  <van-nav-bar :title="props.title" @click-right="onClickRight">
    <template #right>
      <van-icon name="wap-nav" size="18" />
    </template>
  </van-nav-bar>

  <van-popup v-model:show="showRight" position="bottom" :style="{ height: '25%' }" closeable>
    <div class="exit-page">
      <div class="icon">
        <van-icon name="close" color="red" size="60px" @click="exitLogin"/>
      </div>
      <div class="text">点击退出登录</div>
    </div>
  </van-popup>
</template>

<script setup>
import { ref } from 'vue';
import { useUserStore } from '@/stores/user';
import { useRouter } from 'vue-router';
// 组件传参，显示信息
const props = defineProps(['title'])

// 点击右侧按钮逻辑
const showRight = ref(false)
const onClickRight = () => {
  showRight.value = true
  console.log('点击了右侧按钮')
}

// 退出登录逻辑
const userStore = useUserStore()
const router = useRouter()
const exitLogin = () => {
  userStore.userInfo.userName = ""
  userStore.userInfo.isLogin = false
  router.replace('/login')
}

</script>

<style scoped lang="less">
.exit-page {
  height: 100%;
  width: 100%;
  line-height: 100%;
  text-align: center;
  overflow-y: hidden;
  
  .icon {
    margin-top: 15%;
  }

  .text {
    font-size: 20px;
    margin: 20px 0 0 0;
    font-weight: bold;
  }

}
</style>