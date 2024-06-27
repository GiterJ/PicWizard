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
            <van-cell v-for="(data, index) in datas" :key="index" :title="data.func" :label="data.time">
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
import { useNetworkStore } from '@/stores/network';
import { showImagePreview } from 'vant';
import { onMounted, reactive, ref } from 'vue';
import { useRouter } from 'vue-router';

// 接收到的图片数据
const datas = ref([])

// 点击查看
const previewFunc = (imageBase64) => {
    showImagePreview(['data:image/jpeg;base64,' + imageBase64])
}

// 点击下载
const downloadFunc = (imageBase64) => {
    console.log('download:', imageBase64);
    var base64 = imageBase64; // imgSrc 就是base64
    var byteCharacters = atob(
        base64.replace(/^data:image\/(png|jpeg|jpg);base64,/, "")
    );
    var byteNumbers = new Array(byteCharacters.length);
    for (var i = 0; i < byteCharacters.length; i++) {
        byteNumbers[i] = byteCharacters.charCodeAt(i);
    }
    var byteArray = new Uint8Array(byteNumbers);
    var blob = new Blob([byteArray], {
        type: undefined,
    });
    var aLink = document.createElement("a");
    aLink.download = "image.jpg"; //这里写保存时的图片名称
    aLink.href = URL.createObjectURL(blob);
    aLink.click();
}

// 获取数据
const networkStore = useNetworkStore()
onMounted(async () => {
    datas.value = await networkStore.pSavedGet()
})


</script>

<style scoped lang="less">
.list {
    background-color: #F7F8FA;
    height: 94.7vh;
    overflow-y: auto;
}
</style>