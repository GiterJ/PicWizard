import { createRouter, createWebHashHistory, createWebHistory } from 'vue-router'
import PictureQAView from '@/views/PictureQAView.vue'
import PictureGenerateView from '@/views/PictureGenerateView.vue'
import VideoGenerateView from '@/views/VideoGenerateView.vue'
import PictureEditView from '@/views/PictureEditView.vue'

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      name: 'home',
      redirect: 'pqa'
    },
    {
      path:'/pqa',
      name: 'pqa',
      component:PictureQAView
    },
    {
      path: '/pgen',
      name: 'pgen',
      component: PictureGenerateView
    },
    {
      path: "/pedit",
      name: 'pedit',
      component: PictureEditView
    },
    {
      path: '/vgen',
      name: 'vgen',
      component: VideoGenerateView
    }
  ]
})

export default router
