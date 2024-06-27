<template>
    <van-nav-bar :title="props.title" left-text="" left-arrow @click-left="onClickLeft" />
</template>

<script setup>
import { useRouter } from 'vue-router';
import { defineProps } from 'vue';

const router = useRouter()
const props = defineProps(['title'])

const onClickLeft = () => {
    router.back()
}
</script>

<style scoped lang="less"></style>