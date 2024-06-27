<script setup>
import { RouterView } from 'vue-router'
import { useRouter } from 'vue-router';
import { useUserStore } from './stores/user';
import { showToast } from 'vant';

const router = useRouter();
const userStore = useUserStore();

// 打开登录拦截
router.beforeEach((to, from, next) => {
  if (to.path != '/login' && to.path != '/register') {
    if (userStore.userInfo.isLogin == true) {
      next()
    } else {
      showToast("请先登录！")
      next('/login')
    }
  }else {
    next()
  }
})

</script>

<template>
  <RouterView />
</template>

<style>
body {
  font-family: "Source Han Serif SC", "STXihei", "微软雅黑", "宋体", sans-serif !important;
}
</style>