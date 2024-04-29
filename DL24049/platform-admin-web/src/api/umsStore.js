import request from '@/utils/request'
import { ElMessage } from 'element-plus'

export const pageUmsStore = (pageSize, pageNum, data, succFun) => {
  data.pageSize = pageSize
  data.pageNum = pageNum
  request('/umsStore/page', 'post', data).then(data => {
    if (data.code === 0) {
      succFun(data.data)
    } else {
      ElMessage.error(data.msg)
    }
  })
}

export const addUmsStore = (data, succFun) => {
  return request('/umsStore', 'post', data)
}

export const modifyUmsStore = (id, data, succFun) => {
  return request('/umsStore/' + id, 'put', data)
}

export const deleteUmsStore = (id, succFun) => {
  request('/umsStore/' + id, 'delete', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}

export const getAllUmsStore = (succFun) => {
  return request('/umsStore/all', 'get', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}
