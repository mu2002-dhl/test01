import request from '@/utils/request'
import { ElMessage } from 'element-plus'
// 角色相关api请求

export const page = (pageSize, pageNum, loginName, nickname, email, phone) => {
  return request('/user/page', 'post', {
    pageSize: pageSize,
    pageNum: pageNum,
    loginName: loginName,
    nickname: nickname,
    email: email,
    phone: phone
  })
}

export const addUser = (data) => {
  return request('/user', 'post', data)
}

export const registerUser = (data) => {
  return request('/user/register', 'post', data)
}

export const modifyUser = (data) => {
  return request('/user', 'put', data)
}

export const deleteUser = (id) => {
  return request('/user/' + id, 'delete', {})
}

export const changeUserPwd = (id, password) => {
  return request('/user/changePwd', 'put', {
    id: id,
    password: password
  })
}

export const changeUserEnable = (id) => {
  return request('/user/changeEnable/' + id, 'put', {})
}

export const changeUserLocked = (id) => {
  return request('/user/changeLocked/' + id, 'put', {})
}

export const selectByRoleId = async (roleId, succFun) => {
  return request('/user/selectByRoleId/' + roleId, 'get', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}

export const getAllUser = (succFun) => {
  return request('/user/all', 'get', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}
