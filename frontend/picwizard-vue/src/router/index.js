import { createRouter, createWebHashHistory, createWebHistory } from 'vue-router'
import PictureQAView from '@/views/pictureQA/PictureQAView.vue'
import PictureGenerateView from '@/views/pictureGenerate/PictureGenerateView.vue'
import PictureEnhanceView from '@/views/pictureEnhance/PictureEnhanceView.vue'
import PictureEditView from '@/views/pictureEdit/PictureEditView.vue'
import PictureButifyView from '@/views/pictureEdit/subviews/PictureButifyView.vue'
import PictureAnimateView from '@/views/pictureEdit/subviews/PictureAnimateView.vue'
import PictureMergeView from '@/views/pictureEdit/subviews/PictureMergeView.vue'
import PicturePropEditView from '@/views/pictureEdit/subviews/PicturePropEditView.vue'
import LoginView from '@/views/login/LoginView.vue'
import RegisterView from '@/views/login/RegisterView.vue'
import PictureBlack2Color from '@/views/pictureEnhance/subviews/PictureBlack2Color.vue'
import PictureClearer from '@/views/pictureEnhance/subviews/PictureClearer.vue'
import PictureRemoveFog from '@/views/pictureEnhance/subviews/PictureRemoveFog.vue'
import PictureSavedView from '@/views/pictureSaved/PictureSavedView.vue'

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
      component: PictureEnhanceView,
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
      component: PictureAnimateView
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
    },
    {
      path: '/psaved',
      name: 'psaved',
      component: PictureSavedView
    }
  ]
})

export default router
