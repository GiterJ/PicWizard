<template>
    <van-tabs v-model:active="counterStore.activeCounts.sideBar" animated="true" swipeable="true" style="--van-tab-font-size:13px">
        <van-tab v-for="(item, index) in titles" :key="index" :title="item.title" :to="item.to" replace>
            <template #title>
                {{ item.title }}
            </template>
        </van-tab>
    </van-tabs>
</template>

<script setup>
import { useCounterStore } from '@/stores/counter';

const counterStore = useCounterStore()
const titles = [
    {
        title: '黑白上色',
        to: '/vgen/color'
    },
    {
        title: '清晰度增强',
        to: '/vgen/clear'
    },
    {
        title: '图像去雾',
        to: '/vgen/remove'
    },
]

</script>

<style scoped lang="less"></style>