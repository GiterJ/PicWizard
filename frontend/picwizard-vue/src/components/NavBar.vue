<template>
  <van-tabbar v-model="countStore.activeCounts.navBar">
    <van-tabbar-item icon="chat-o" to="/pqa" replace>图片问答</van-tabbar-item>
    <van-tabbar-item icon="photo-o" to="/pgen" replace>图片生成</van-tabbar-item>
    <van-tabbar-item icon="apps-o" to="/pedit" replace>图像编辑</van-tabbar-item>
    <van-tabbar-item icon="tv-o" to="/vgen" replace>图像增强</van-tabbar-item>
  </van-tabbar>
</template>

<script setup>
import { useCounterStore } from '@/stores/counter';
const countStore = useCounterStore();

</script>

<style scoped lang="less"></style>