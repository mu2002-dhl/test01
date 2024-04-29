import { createRouter, createWebHistory } from 'vue-router'
import store from '@/store'
const Layout = () => import('@/views/Layout')
const Login = () => import('@/views/Login')
const Register = () => import('@/views/Register')
const dictItem = () => import('@/views/UmsDictItem')
const notFoundPage = () => import('@/views/common/error/404')
const Index = () => import('@/views/IndexVue')
const { profile } = store.state.user
const routeList = [
  {
    name: 'main',
    path: '/main',
    component: Index,
    meta: {
      title: ''
    }
  }
]
if (profile.routeList) {
  profile.routeList.forEach(item => {
    routeList.push({
      name: item.name,
      path: item.path,
      component: () => import('@/views' + item.component),
      meta: {
        title: item.title
      }
    })
  })
}
// 路由规则
const routes = [
  // 一级路由
  {
    name: 'login',
    path: '/login',
    component: Login,
    meta: {
      title: '登录'
    }
  },
  {
    name: 'register',
    path: '/register',
    component: Register,
    meta: {
      title: '注册'
    }
  },
  {
    name: 'dictItem',
    path: '/dictItem',
    component: dictItem,
    meta: {
      title: '字典项'
    }
  },
  {
    name: 'index',
    path: '/',
    redirect: '/main',
    component: Layout,
    meta: {
      title: '首页'
    },
    children: routeList
  },
  {
    path: '/:catchAll(.*)',
    name: 'not-found',
    component: notFoundPage
  }
]

const router = createRouter({
  // 使用hash的路由模式
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const requestIRL = to.fullPath

  const { profile } = store.state.user
  if (requestIRL !== '/login' && requestIRL !== '/register' && (profile.token === undefined || profile.token === null || profile.token.length === 0)) {
    next('/login')
  } else {
    next()
  }
})

export default router
