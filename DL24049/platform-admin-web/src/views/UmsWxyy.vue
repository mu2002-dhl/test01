<template>
  <div class="common-layout">
    <el-container>
      <el-aside style="width: 300px;">
        <ul v-if="count!=null && count.length > 0" v-infinite-scroll="load" class="infinite-list" style="overflow: auto">
          {{ count.size }}
          <li v-for="i in count" :key="i" class="infinite-list-item">
            <span class="radius" @click="show(i.reply)">{{ i.search }}</span>
            <el-button style="margin-left: 5px;"
                  @click="deleteItem(i.id)"
                  :icon="Delete" />
          </li>
        </ul>
        <div v-if="count == null || count.length == 0">暂无历史对话信息</div>
      </el-aside>
      <el-main>
        <el-row style="padding-left: 5px;">
          <el-col>
            <el-row class="content">
              <div style="white-space: pre-wrap" v-html="reply"/>
            </el-row>
            <el-row>
              <el-input
                v-model="question"
                style="max-width: 800px;height: 70px;padding-top: 20px;"
                placeholder="在这里输入问题"
                class="input-with-select"
              >
                <template #append>
                  <el-button
                  @click="openFullScreen2"
                  :icon="Search" />
                </template>
              </el-input>
            </el-row>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { Search, Delete } from '@element-plus/icons-vue'

import { ElLoading, ElMessage } from 'element-plus'
import { ask, pageUmWxyy, deleteUmWxyy } from '@/api/umWxyy'

const question = ref(null)
const count = ref(null)
const reply = ref(null)
const load = () => {

}

onMounted(() => {
  loadHis()
})
const loadHis = () => {
  pageUmWxyy(10000, 0, {
    type: 'line'
  }, data => {
    count.value = data.records
  })
}
const openFullScreen2 = () => {
  if (question.value === null || question.value === '') {
    ElMessage.error('请输入问题')
    return
  }
  const loading = ElLoading.service({
    lock: true,
    text: '文心一言处理中，请稍等',
    background: 'rgba(0, 0, 0, 0.7)'
  })
  ask('line', question.value)
    .then(data => {
      loading.close()
      if (data.code === 0) {
        reply.value = data.data.result
        loadHis()
        question.value = ''
      } else {
        ElMessage.error(data.data.msg)
      }
    })
    .catch(_ => {
      loading.close()
    })
}

const deleteItem = (id) => {
  console.log(id)
  deleteUmWxyy(id, data => {
    ElMessage.success('删除成功')
    loadHis()
  })
}

const show = (content) => {
  reply.value = content
}
</script>

<style>

.infinite-list {
  height: 700px;
  padding: 0;
  margin: 0;
  list-style: none;
}

.infinite-list .radius {
  display: inline-block;
  height: 60px;
  width: 80%;
  border: 1px solid var(--el-border-color);
  border-radius: var(--el-border-radius-base);
  margin-top: 20px;
}
.content{
  border: 1px solid var(--el-border-color);
  border-radius: var(--el-border-radius-base);
  height: 550px;
}

</style>
