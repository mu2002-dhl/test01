import store from '@/store'
import { selectByDictCode } from '@/api/umsDictItem'
import { ElMessage } from 'element-plus'
export const setBreadcrumb = (firstItem, secondItem, thirdItem) => {
  let firstName = null
  let firstUrl = null
  let secondName = null
  let secondUrl = null
  let thirdName = null
  let thirdUrl = null
  if (firstItem) {
    firstName = firstItem.name ? firstItem.name : ''
    firstUrl = firstItem.url ? firstItem.url : ''
  }
  if (secondItem) {
    secondName = secondItem.name ? secondItem.name : ''
    secondUrl = secondItem.url ? secondItem.url : ''
  }
  if (thirdItem) {
    thirdName = thirdItem.name ? thirdItem.name : ''
    thirdUrl = thirdItem.url ? thirdItem.url : ''
  }
  store.commit('breadcrumb/setBreadcrumb', {
    firstName: firstName,
    firstUrl: firstUrl,
    secondName: secondName,
    secondUrl: secondUrl,
    thirdName: thirdName,
    thirdUrl: thirdUrl
  })
}

export const initBreadcrumb = () => {
  store.commit('breadcrumb/setBreadcrumb', {})
}

export const getSystemInfo = () => {
  return JSON.parse(localStorage.getItem('platform-admin-web')).system.info
}

export const getByDictCode = async (dictCode) => {
  const data = await selectByDictCode(dictCode)
  if (data.code === 0) {
    return data.data
  } else {
    ElMessage.error(data.msg)
  }
}

export const getDictItemNameByValue = (data, value) => {
  if (data) {
    for (let i = 0; i < data.length; i++) {
      const item = data[i]
      if (item.itemValue === value) {
        return item.itemName
      }
    }
  }

  return '-'
}
