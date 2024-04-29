import request from '@/utils/request'
import { ElMessage } from 'element-plus'

export const pageUmsComment = (pageSize, pageNum, data, succFun) => {
  data.pageSize = pageSize
  data.pageNum = pageNum
  request('/umsComment/page', 'post', data).then(data => {
    if (data.code === 0) {
      succFun(data.data)
    } else {
      ElMessage.error(data.msg)
    }
  })
}

export const addUmsComment = (data, succFun) => {
  return request('/umsComment', 'post', data)
}

export const modifyUmsComment = (id, data, succFun) => {
  return request('/umsComment/' + id, 'put', data)
}

export const deleteUmsComment = (id, succFun) => {
  request('/umsComment/' + id, 'delete', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}

export const getAllUmsComment = (succFun) => {
  return request('/umsComment/all', 'get', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}
