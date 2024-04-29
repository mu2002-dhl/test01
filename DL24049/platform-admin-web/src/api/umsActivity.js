import request from '@/utils/request'
import { ElMessage } from 'element-plus'

export const pageUmsActivity = (pageSize, pageNum, data, succFun) => {
  data.pageSize = pageSize
  data.pageNum = pageNum
  request('/umsActivity/page', 'post', data).then(data => {
    if (data.code === 0) {
      succFun(data.data)
    } else {
      ElMessage.error(data.msg)
    }
  })
}

export const addUmsActivity = (data, succFun) => {
  return request('/umsActivity', 'post', data)
}

export const modifyUmsActivity = (id, data, succFun) => {
  return request('/umsActivity/' + id, 'put', data)
}

export const deleteUmsActivity = (id, succFun) => {
  request('/umsActivity/' + id, 'delete', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}

export const getAllUmsActivity = (succFun) => {
  return request('/umsActivity/all', 'get', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}
