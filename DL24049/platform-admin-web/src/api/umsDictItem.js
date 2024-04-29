import request from '@/utils/request'
import { ElMessage } from 'element-plus'

export const pageUmsDictItem = (pageSize, pageNum, data, succFun) => {
  data.pageSize = pageSize
  data.pageNum = pageNum
  request('/umsDictItem/page', 'post', data).then(data => {
    if (data.code === 0) {
      succFun(data.data)
    } else {
      ElMessage.error(data.msg)
    }
  })
}

export const addUmsDictItem = (data, succFun) => {
  return request('/umsDictItem', 'post', data)
}

export const modifyUmsDictItem = (id, data, succFun) => {
  return request('/umsDictItem/' + id, 'put', data)
}

export const deleteUmsDictItem = (id, succFun) => {
  request('/umsDictItem/' + id, 'delete', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}

export const getAllUmsDictItem = (succFun) => {
  return request('/umsDictItem/all', 'get', {})
    .then(data => {
      if (data.code === 0) {
        succFun(data.data)
      } else {
        ElMessage.error(data.msg)
      }
    })
}

export async function selectByDictCode (dictCode) {
  return await request('/umsDictItem/dict/' + dictCode, 'get', {})
}
