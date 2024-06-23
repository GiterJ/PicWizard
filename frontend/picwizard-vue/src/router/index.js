import { createRouter, createWebHashHistory, createWebHistory } from 'vue-router'
import PictureQAView from '@/views/PictureQAView.vue'
import PictureGenerateView from '@/views/PictureGenerateView.vue'
import VideoGenerateView from '@/views/VideoGenerateView.vue'
import PictureEditView from '@/views/PictureEditView.vue'
import PictureButifyView from '@/views/subviews/PictureButifyView.vue'
import PictureFixView from '@/views/subviews/PictureFixView.vue'
import PictureMergeView from '@/views/subviews/PictureMergeView.vue'
import PicturePropEditView from '@/views/subviews/PicturePropEditView.vue'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import PictureBlack2Color from '@/views/subviews/PictureBlack2Color.vue'
import PictureClearer from '@/views/subviews/PictureClearer.vue'
import PictureRemoveFog from '@/views/subviews/PictureRemoveFog.vue'

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      name: 'home',
      redirect: 'login'
    },
    {
      path: '/pqa',
      name: 'pqa',
      component: PictureQAView
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
      component: VideoGenerateView,
      redirect: 'vgen/color',
      children: [
        {
          path: 'color',
          name: 'color',
          component: PictureBlack2Color
        },
        {
          path: 'clear',
          name: 'clear',
          component: PictureClearer
        },
        {
          path: 'remove',
          name: 'remove',
          component: PictureRemoveFog
        }
      ]
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
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterView
    }
  ]
})

export default router
