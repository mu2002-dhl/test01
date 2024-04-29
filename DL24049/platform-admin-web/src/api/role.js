import request from '@/utils/request'
// 角色相关api请求

export const page = (pageSize, pageNum, name, num) => {
  return request('/role/page', 'post', {
    pageSize: pageSize,
    pageNum: pageNum,
    name: name,
    num: num
  })
}

export const addRole = (data) => {
  return request('/role', 'post', data)
}

export const modifyRole = (data) => {
  return request('/role', 'put', data)
}

export const deleteRole = (id) => {
  return request('/role/' + id, 'delete', {})
}

export const allRole = () => {
  return request('/role/all', 'get', {})
}

export const roleAuthPermission = (roleId, permissionList) => {
  return request('/role/authPermission', 'put', {
    roleId: roleId,
    permissionList: permissionList
  })
}

export const getRoleAuthPermission = (roleId) => {
  return request('/role/getRoleAuthPermission/' + roleId, 'get', {})
}
