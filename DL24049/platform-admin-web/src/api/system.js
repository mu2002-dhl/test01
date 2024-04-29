import request from '@/utils/request'
import { ElMessage } from 'element-plus'
// 系统级别请求接口

// 用户登录
export const login = (loginName, password, captcha) => {
  return request('/auth/login', 'post', {
    loginName: loginName,
    password: password,
    captcha: captcha
  })
}

// 退出
export const logout = () => {
  return request('/auth/logout', 'get', {})
}

// 删除图片
export const deleteFile = (id) => {
  return request('/file/' + id, 'delete', {})
}

// 获取系统信息
export const systemInfo = (succFun) => {
  request('/system/info', 'get', {}).then(data => {
    if (data.code === 0) {
      succFun(data.data)
    } else {
      ElMessage.error(data.msg)
    }
  })
}
