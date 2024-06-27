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


<!-- TODO -->
<!-- 1. 之前所有的network多传递一个name参数 -->
<!-- 2. 完成图片数据获取函数 -->
<!-- 3. 完成获取到的图片的预览和下载 -->
<!-- 4. 打开登录拦截，测试所有功能 -->
<!-- 5. 准备用例，准备验收 -->
<!-- 6. 打包到服务器运行 -->