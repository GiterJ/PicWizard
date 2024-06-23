import { reactive } from 'vue'
import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', () => {
  const userInfo = reactive({
    userName: '',
    isLogin: false
  }) 

  return { userInfo }
})