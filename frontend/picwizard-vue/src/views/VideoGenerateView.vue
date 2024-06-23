<template>
  <!-- 头部 -->
  <Header title="图像增强"></Header>

  <!-- 标签页 -->
  <UpNavBar />

  <!-- 主体内容部分 -->
  <RouterView />

  <NavBar />

</template>

<script setup>
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import NavBar from '@/components/NavBar.vue';
import UpNavBar from '@/components/UpNavBar.vue'
import { RouterView } from 'vue-router';
import 'vant/es/toast/style'


</script>

<style scoped lang="less">

</style>