import request from '@/utils/request'
import { ElMessage } from 'element-plus'

export const pageUmWxyy = (pageSize, pageNum, data, succFun) => {
  data.pageSize = pageSize
  data.pageNum = pageNum
  request('/umWxyy/page', 'post', data).then(data => {
    if (data.code === 0) {
      succFun(data.data)
    } else {
      ElMessage.error(data.msg)
    }
  })
}

export const addUmWxyy = (data, succFun) => {
  return request('/umWxyy', 'post', data)
}

export const modifyUmWxyy = (id, data, succFun) => {
  return request('/umWxyy/' + id, 'put', data)
}

export const deleteUmWxyy = (id, succFun) => {
  request('/umWxyy/' + id, 'delete', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}

export const getAllUmWxyy = (succFun) => {
  return request('/umWxyy/all', 'get', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}

export const ask = (type, question) => {
  const askData = {
    type: type,
    question: question
  }
  return request('/umWxyy/ask', 'post', askData)
}
