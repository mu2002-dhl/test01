import request from '@/utils/request'
import { ElMessage } from 'element-plus'

export const pageUmsDict = (pageSize, pageNum, data, succFun) => {
  data.pageSize = pageSize
  data.pageNum = pageNum
  request('/umsDict/page', 'post', data).then(data => {
    if (data.code === 0) {
      succFun(data.data)
    } else {
      ElMessage.error(data.msg)
    }
  })
}

export const addUmsDict = (data, succFun) => {
  return request('/umsDict', 'post', data)
}

export const modifyUmsDict = (id, data, succFun) => {
  return request('/umsDict/' + id, 'put', data)
}

export const deleteUmsDict = (id, succFun) => {
  request('/umsDict/' + id, 'delete', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}

export const getAllUmsDict = (succFun) => {
  return request('/umsDict/all', 'get', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}
