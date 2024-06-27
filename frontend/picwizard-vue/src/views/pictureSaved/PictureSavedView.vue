<template>
    <!-- Header -->
    <div class="header">
        <BackHeader title="图片查看" />
    </div>

    <!-- component box -->
    <div class="list">
        <!-- components -->
        <div style="margin-top: 2vh"></div>
        <!-- 列表 -->
        <van-cell-group inset>
            <!-- 列表项 -->
            <van-cell v-for="(data, index) in datas" :key="index" :title="data.title" :label="data.label">
                <!-- 右侧value内容 -->
                <template #value>
                    <van-button type="primary" size="mini" @click="previewFunc(data.base64)">查看</van-button>
                    <span>&nbsp;&nbsp;</span>
                    <van-button type="primary" size="mini" @click="downloadFunc(data.base64)">下载</van-button>
                </template>
            </van-cell>
        </van-cell-group>
        <div style="margin-bottom: 2vh"></div>
    </div>

</template>

<script setup>
import BackHeader from '@/components/BackHeader.vue';
import { ref } from 'vue';

// 接收到的图片数据
const datas = [
    {
        title: '图片生成',
        label: '2024-10-1 24:00',
        base64: '1'
    }
]

// 点击查看
const previewFunc = (imageBase64) => {
    console.log('preview:',imageBase64);
}

// 点击下载
const downloadFunc = (imageBase64) => {
    console.log('download:', imageBase64);
}

</script>

<style scoped lang="less">
.list {
    background-color: #F7F8FA;
    height: 94.7vh;
    overflow-y: auto;
}
</style>