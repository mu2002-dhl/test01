<template>
  <el-form class="demo-form-inline">
    <el-row style="padding: 20px 0px 0px 20px">
      <el-col :span="8">
        <el-form-item label="标题">
            <el-input placeholder="标题" v-model="query.title" clearable />
        </el-form-item>
      </el-col>
      <!-- <el-col :span="8">
        <el-form-item label="是否最新公告">
            <el-select
              v-model="query.isNew"
              collapse-tags
              collapse-tags-tooltip
              :max-collapse-tags="5"
              placeholder="选择是否最新公告"
              clearable
            >
              <el-option
                v-for="item in dictYesNoOptions"
                :key="item.itemValue"
                :label="item.itemName"
                :value="item.itemValue"
              />
            </el-select>
        </el-form-item>
      </el-col> -->
    </el-row>
    <el-row style="padding: 20px 0px 0px 20px">
      <el-col :span="8">
        <el-form-item>
          <el-button type="primary" size="small" @click="onQuery">查询</el-button>
          <el-button type="success" size="small" @click="onQueryReset">重置</el-button>
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
  <el-row>
    <el-table :data="tableData" border  style="width: 100%">
      <el-table-column prop="title" label="标题"  />
      <el-table-column prop="isNew" label="是否最新公告"  >
        <template #default="scope">
          <el-tag >{{getDictItemNameByValue(dictYesNoOptions, scope.row.isNew )}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间"  />
      <el-table-column fixed="right" label="操作" width="250px">
        <template #default="scope">
          <el-button type="primary" size="small" @click="handleModify(scope.row)">查看详情</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-row>
  <el-row style="padding: 10px;float: right;">
    <el-pagination background layout="prev, pager, next" :total="pageTotal" small @current-change="handleCurrentChange"/>
  </el-row>

  <el-dialog  v-model="dialogFormVisible" :title="formTitle">
    <el-form :model="form" :rules="formRules" ref="formRef">
      <el-row>
        <el-col style="text-align: center;font-size: 30px;">
          {{ form.title }}
        </el-col>
      </el-row>
      <el-row>
        <el-divider border-style="double" />
        <div v-html="form.content" ></div>
      </el-row>
    </el-form>
  </el-dialog>
</template>

<script setup>
import { pageUmsNotice } from '@/api/umsNotice'
import { onMounted, reactive, ref } from 'vue'
import { getByDictCode, getDictItemNameByValue } from '@/utils/system'

const dictYesNoOptions = ref(null)

const form = reactive({
  id: '',
  title: '',
  content: '',
  isNew: ''
})
const backupQueryData = JSON.parse(JSON.stringify(form))
const query = reactive({
  title: '',
  isNew: ''
})
const backupData = JSON.parse(JSON.stringify(form))
const dialogFormVisible = ref(false)
const formTitle = ref(null)
const pageSize = ref(10)
const pageTotal = ref(0)
const tableData = ref([])
const formRef = ref(null)
const formRules = reactive({
  title: [
    { required: true, message: '请输入标题', trigger: 'blur' }
  ],
  content: [
    { required: true, message: '请输入内容', trigger: 'blur' }
  ],
  isNew: [
    { required: true, message: '请输入是否最新公告', trigger: 'blur' }
  ]
})

const loadData = async (current) => {
  const queryData = {
    title: query.title,
    isNew: query.isNew
  }
  pageUmsNotice(pageSize.value, current, queryData, data => {
    tableData.value = data.records
    pageTotal.value = data.total
  })
  dictYesNoOptions.value = await getByDictCode('dict_yes_no')
}

onMounted(() => {
  loadData(1)
})
const onQuery = () => {
  loadData(1)
}
const onQueryReset = () => {
  Object.assign(query, backupQueryData)
  loadData(1)
}
const handleModify = (row) => {
  resetForm()
  dialogFormVisible.value = true
  formTitle.value = '详情'
  form.actionType = 'edit'
  form.id = row.id
  Object.assign(form, row)
}
const handleCurrentChange = (number) => {
  loadData(number)
}

const resetForm = () => {
  if (formRef.value !== null) {
    formRef.value.resetFields()
    Object.assign(form, backupData)
  }
}
</script>
<style>

.demo-form-inline .el-input {
  --el-input-width: 220px;
}

.ck-content {
  height:500px;
}

</style>
