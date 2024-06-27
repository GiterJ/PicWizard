<template>
  <van-nav-bar :title="props.title" @click-left="onClickLeft">
    <template #left>
      <van-popover v-model:show="showPopover" :actions="actions" @select="onSelect" placement="bottom-start">
        <template #reference>
          <van-icon name="wap-nav" size="18" />
        </template>
      </van-popover>
    </template>
  </van-nav-bar>

  <van-popup v-model:show="showRight" position="bottom" :style="{ height: '25%' }" closeable>
    <div class="exit-page">
      <div class="icon">
        <van-icon name="close" color="red" size="60px" @click="exitLogin" />
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

// 进入退出登录确认
const showRight = ref(false)
const logoutConfirm = () => {
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

// 进入图片查看逻辑
const enterPicSaved = () => {
  router.push('/psaved')
}

// 点击左侧按钮，弹出功能菜单
const onClickLeft = () => {
  console.log('点击了左侧按钮')
  showPopover.value = true
}

// 功能按钮弹出层逻辑
const showPopover = ref(false);
const actions = [
  {
    text: '图片查看',
    icon: 'search',
    func: enterPicSaved
  },
  {
    text: '退出登录',
    icon: 'close',
    func: logoutConfirm
  }
];
const onSelect = (action) => {
  action.func()
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