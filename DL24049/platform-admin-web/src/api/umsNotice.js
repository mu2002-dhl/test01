import request from '@/utils/request'
import { ElMessage } from 'element-plus'

export const pageUmsNotice = (pageSize, pageNum, data, succFun) => {
  data.pageSize = pageSize
  data.pageNum = pageNum
  request('/umsNotice/page', 'post', data).then(data => {
    if (data.code === 0) {
      succFun(data.data)
    } else {
      ElMessage.error(data.msg)
    }
  })
}

export const addUmsNotice = (data, succFun) => {
  return request('/umsNotice', 'post', data)
}

export const modifyUmsNotice = (id, data, succFun) => {
  return request('/umsNotice/' + id, 'put', data)
}

export const deleteUmsNotice = (id, succFun) => {
  request('/umsNotice/' + id, 'delete', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}

export const getAllUmsNotice = (succFun) => {
  return request('/umsNotice/all', 'get', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}
