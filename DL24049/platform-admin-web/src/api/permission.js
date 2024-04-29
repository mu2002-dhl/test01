import request from '@/utils/request'
// 权限相关api请求

export const permissionList = () => {
  return request('/permission', 'get', {})
}

export const addPermission = (data) => {
  return request('/permission', 'post', data)
}

export const modifyPermission = (data) => {
  return request('/permission', 'put', data)
}

export const detailPermission = (id) => {
  return request('/permission/detail/' + id, 'get', {})
}

export const deletePermission = (id) => {
  return request('/permission/' + id, 'delete', {})
}
