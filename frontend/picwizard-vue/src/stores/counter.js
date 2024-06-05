import { ref, computed, reactive } from 'vue'
import { defineStore } from 'pinia'

export const useCounterStore = defineStore('counter', () => {
  const activeCounts = reactive({
    navBar:0,
  }) 

  return { activeCounts }
})