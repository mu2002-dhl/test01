// 1. 创建一个新的axios实例
// 2. 请i去拦截器，如果有token进行头部携带
// 3. 响应拦截器：1. 剥离无效数据 2. 处理token失效
// 4. 导出一个函数，调用当前的axios实例发请求，返回值promise

import axios from 'axios'
import store from '@/store'
import route from '@/router'
import JSONBIG from 'json-bigint'

// 导出基准地址，原因：其他地方不是通过axios发请求的地方用上基准地址
export const baseURL = 'http://localhost:9001/'
const instance = axios.create({
  // axios的一些配置 baseURL timeout
  baseURL
})

instance.interceptors.request.use(config => {
  // 拦截业务逻辑
  // 进行请求配置的修改
  // 如果本地有token就在头部携带
  // 1. 获取用户信息对象
  const { profile } = store.state.user
  // 2. 判断是否有token
  if (profile.token) {
    // 3. 设置token
    config.headers.Authorization = `${profile.token}`
  }
  return config
}, err => {
  return Promise.reject(err)
})

// res=>res.data 取出来data数据，将来调用接口的时候直接拿到的就是后台的数据
instance.interceptors.response.use(res => res.data, err => {
  // 401 状态码，进入该函数
  if (err.response && err.response.status === 401) {
    // 1. 清空无效用户信息
    store.commit('user/setUser', {})
    // 2. 跳转到登录页
    // 3. 跳转需要传参（当前路由地址）给登录页码
    // js模块中：route.currentRoute.value.fullPath 就是当前路由地址（包括参数），vue3中route.currentRoute是ref取值的时候要.value获取
    // encodeURIComponent转换uri编码，防止解析地址出问题
    const fullPath = encodeURIComponent(route.currentRoute.value.fullPath)
    route.push('/login?redirectUrl=' + fullPath)
  }
  return Promise.reject(err)
})

// 后端返回id过长导致数据失真处理
instance.defaults.transformResponse = [data => {
  // 对data（后台的原始数据）进行转换
  return JSONBIG.parse(data)
}]

// 请求工具函数
export default (url, method, submitData) => {
  // 负责发请求：请求地址，请求方式，提交的数据
  return instance({
    url,
    method,
    // 1. 如果是get请求  需要使用params来传递submitData   ?a=10&c=10
    // 2. 如果不是get请求  需要使用data来传递submitData   请求体传参
    // [] 设置一个动态的key, 写js表达式，js表达式的执行结果当作KEY
    // method参数：get,Get,GET  转换成小写再来判断
    // 在对象，['params']:submitData ===== params:submitData 这样理解
    [method.toLowerCase() === 'get' ? 'params' : 'data']: submitData
  })
}
