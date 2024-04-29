import request from '@/utils/request'

// 部门相关api请求

export const deptList = () => {
  return request('/dept', 'get', {})
}

export const addDept = (data) => {
  return request('/dept', 'post', data)
}

export const modifyDept = (data) => {
  return request('/dept', 'put', data)
}

export const detailDept = (id) => {
  return request('/dept/detail/' + id, 'get', {})
}

export const deleteDept = (id) => {
  return request('/dept/' + id, 'delete', {})
}
