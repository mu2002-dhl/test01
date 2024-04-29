import request from '@/utils/request'
import { ElMessage } from 'element-plus'

export const pageUmsPlaces = (pageSize, pageNum, data, succFun) => {
  data.pageSize = pageSize
  data.pageNum = pageNum
  request('/umsPlaces/page', 'post', data).then(data => {
    if (data.code === 0) {
      succFun(data.data)
    } else {
      ElMessage.error(data.msg)
    }
  })
}

export const addUmsPlaces = (data, succFun) => {
  return request('/umsPlaces', 'post', data)
}

export const modifyUmsPlaces = (id, data, succFun) => {
  return request('/umsPlaces/' + id, 'put', data)
}

export const deleteUmsPlaces = (id, succFun) => {
  request('/umsPlaces/' + id, 'delete', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}

export const getAllUmsPlaces = (succFun) => {
  return request('/umsPlaces/all', 'get', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}

export const detailPlaces = (id, succFun) => {
  return request('/umsPlaces/' + id, 'get', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}

export const statistics = (succFun) => {
  return request('/umsPlaces/statistics', 'get', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}
