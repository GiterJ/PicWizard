<template>
  <Header title="图片编辑"></Header>
  <!-- 内容框 -->
  <div class="buttons">
    <div class="buttons-1line">
      <div class="button1" @click="enterMerge">
        <div class="text">
          人脸融合
        </div>
      </div>
      <div class="button2" @click="enterButify">
        <div class="text">
          AI美颜
        </div>
      </div>
    </div>
    <div class="buttons-2line">
      <div class="button3" @click="enterEdit">
        <div class="text">
          属性编辑
        </div>
      </div>
      <div class="button4" @click="enterFix">
        <div class="text">
          照片修复
        </div>
      </div>
    </div>
  </div>

</template>

<script setup>
import Header from '@/components/Header.vue';
import { useRouter } from 'vue-router';

const router = useRouter()

const enterMerge = () => {
  router.replace('/pm')
}

const enterButify = () => {
  router.replace('/pb')
}

const enterEdit = () => {
  router.replace('/ppe')
}

const enterFix = () => {
  router.replace('/pf')
}


</script>

<style scoped lang="less">
.buttons {
  margin-top: 14vh;
  .buttons-1line {
    margin: 0 auto;
    height: 30vh;
    width: 100vw;
    display: flex;
    justify-content: space-between;
    align-items: center;

    .button1 {
      width: 30vw;
      height: 30vw;
      border-radius: 15vw;
      background-color: #5473E8;
      margin: 0 auto;
      display: flex;
      align-items: center;
      justify-content: center;
      color: white;
      font-weight: bold;
      box-shadow: 0px 0px 40px 2px #5473e8
    }

    .button2 {
      width: 30vw;
      height: 30vw;
      border-radius: 15vw;
      background-color: #5473E8;
      margin: 0 auto;
      display: flex;
      align-items: center;
      justify-content: center;
      color: white;
      font-weight: bold;
      box-shadow: 0px 0px 40px 2px #5473e8
    }


  }

  .buttons-2line {
    margin: 0 auto;
    height: 30vh;
    width: 100vw;
    display: flex;
    justify-content: space-between;
    align-items: center;

    .button3 {
      width: 30vw;
      height: 30vw;
      border-radius: 15vw;
      background-color: #5473E8;
      margin: 0 auto;
      display: flex;
      align-items: center;
      justify-content: center;
      color: white;
      font-weight: bold;
      box-shadow: 0px 0px 40px 2px #5473e8
    }

    .button4 {
      width: 30vw;
      height: 30vw;
      border-radius: 15vw;
      background-color: #5473E8;
      margin: 0 auto;
      display: flex;
      align-items: center;
      justify-content: center;
      color: white;
      font-weight: bold;
      box-shadow: 0px 0px 40px 2px #5473e8
    }
  }
}
</style>