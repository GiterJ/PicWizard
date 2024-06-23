<template>
  <van-tabbar v-model="countStore.activeCounts.navBar">
    <van-tabbar-item icon="chat-o" to="/pqa">图片问答</van-tabbar-item>
    <van-tabbar-item icon="photo-o" to="pgen">图片生成</van-tabbar-item>
    <van-tabbar-item icon="apps-o" to="pedit">图像编辑</van-tabbar-item>
    <van-tabbar-item icon="tv-o" to="vgen">图像增强</van-tabbar-item>
  </van-tabbar>
</template>

<script setup>
import { useCounterStore } from '@/stores/counter';
const countStore = useCounterStore();

</script>

<style scoped lang="less"></style>