import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate'

import user from '@/store/modules/user'
import system from '@/store/modules/system'

export default createStore({
  state: {
    name: '123'
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    // 模块化存放
    user,
    system
  },
  // 配置插件
  plugins: [
    createPersistedState({
      // 本地存储名字(不指定此插件，关闭浏览器后没有持久化到localStorge的数据会消失。这里指定modules中的数据会自动存入localStorge，做数据持久化)
      key: 'platform-admin-web',
      // 指定需要存储的模块
      paths: ['user', 'system']
    })
  ]
})
