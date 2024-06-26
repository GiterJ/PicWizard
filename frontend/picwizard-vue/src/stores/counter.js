import { reactive } from 'vue'
import { defineStore } from 'pinia'

export const useCounterStore = defineStore('counter', () => {
  const activeCounts = reactive({
    navBar: 0,
    sideBar: 0
  })

  return { activeCounts }
},
  {
    persist: true
  }
)