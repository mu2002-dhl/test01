import request from '@/utils/request'
import { ElMessage } from 'element-plus'

export const pageUmsActivityApply = (pageSize, pageNum, data, succFun) => {
  data.pageSize = pageSize
  data.pageNum = pageNum
  request('/umsActivityApply/page', 'post', data).then(data => {
    if (data.code === 0) {
      succFun(data.data)
    } else {
      ElMessage.error(data.msg)
    }
  })
}

export const addUmsActivityApply = (data, succFun) => {
  return request('/umsActivityApply', 'post', data)
}

export const modifyUmsActivityApply = (id, data, succFun) => {
  return request('/umsActivityApply/' + id, 'put', data)
}

export const deleteUmsActivityApply = (id, succFun) => {
  request('/umsActivityApply/' + id, 'delete', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}

export const getAllUmsActivityApply = (activityId, succFun) => {
  return request('/umsActivityApply/all/' + activityId, 'get', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}
