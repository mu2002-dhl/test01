
export default {
  namespaced: true,
  state () {
    return {
      info: {
        name: '管理系统',
        loginEnable: true,
        loginRole: true
      }
    }
  },
  mutations: {
    setInfo (state, payload) {
      state.info = payload
    }
  }
}
