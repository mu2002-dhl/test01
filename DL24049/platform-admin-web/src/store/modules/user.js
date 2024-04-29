// 用户模块
export default {
  namespaced: true,
  state () {
    return {
      // 用户信息
      profile: {
        id: '',
        loginName: '',
        nickname: '',
        email: '',
        phone: '',
        headImg: '',
        token: '',
        dept: {
          id: '',
          deptNum: '',
          deptName: ''
        },
        roleList: [
          {
            id: '',
            name: '',
            num: ''
          }
        ],
        routeList: [],
        menuList: []
      }
    }
  },
  mutations: {
    // 修改用户信息，payload就是用户信息
    setUser (state, payload) {
      state.profile = payload
    }
  }
}
