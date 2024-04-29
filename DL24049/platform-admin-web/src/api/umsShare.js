import request from '@/utils/request'
import { ElMessage } from 'element-plus'

export const pageUmsShare = (pageSize, pageNum, data, succFun) => {
  data.pageSize = pageSize
  data.pageNum = pageNum
  request('/umsShare/page', 'post', data).then(data => {
    if (data.code === 0) {
      succFun(data.data)
    } else {
      ElMessage.error(data.msg)
    }
  })
}

export const addUmsShare = (data, succFun) => {
  return request('/umsShare', 'post', data)
}

export const modifyUmsShare = (id, data, succFun) => {
  return request('/umsShare/' + id, 'put', data)
}

export const deleteUmsShare = (id, succFun) => {
  request('/umsShare/' + id, 'delete', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}

export const getAllUmsShare = (succFun) => {
  return request('/umsShare/all', 'get', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}
