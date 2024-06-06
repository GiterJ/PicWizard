import { createRouter, createWebHashHistory, createWebHistory } from 'vue-router'
import PictureQAView from '@/views/PictureQAView.vue'
import PictureGenerateView from '@/views/PictureGenerateView.vue'
import VideoGenerateView from '@/views/VideoGenerateView.vue'
import PictureEditView from '@/views/PictureEditView.vue'
import PictureButifyView from '@/views/subviews/PictureButifyView.vue'
import PictureFixView from '@/views/subviews/PictureFixView.vue'
import PictureMergeView from '@/views/subviews/PictureMergeView.vue'
import PicturePropEditView from '@/views/subviews/PicturePropEditView.vue'

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
    },
    {
      path: '/pb',
      name: 'pb',
      component: PictureButifyView
    },
    {
      path: '/pf',
      name: 'pf',
      component: PictureFixView
    },
    {
      path: '/pm',
      name: 'pm',
      component: PictureMergeView
    },
    {
      path: '/ppe',
      name: 'ppe',
      component: PicturePropEditView
    }
  ]
})

export default router
