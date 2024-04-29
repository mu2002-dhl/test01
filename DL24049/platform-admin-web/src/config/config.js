import store from '@/store'
import router from '@/router'

export const initBackendRouter = () => {
  const { profile } = store.state.user
  const Index = () => import('@/views/IndexVue')
  if (!profile.routeList) {
    return
  }
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
  const indexRoute = router.options.routes.find(route => route.name === 'index')
  indexRoute.children = routeList
  // 清空已存在的子路由
  router.removeRoute('index')

  // 添加更新后的子路由到路由器
  router.addRoute(indexRoute)
}
