<template>
  <el-form class="demo-form-inline">
    <el-row style="padding: 20px 0px 0px 20px">
      <el-col :span="8">
        <el-form-item label="标题">
            <el-input placeholder="标题" v-model="query.title" clearable />
        </el-form-item>
      </el-col>
      <el-col :span="8">
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
      </el-col>
    </el-row>
    <el-row style="padding: 20px 0px 0px 20px">
      <el-col :span="8">
        <el-form-item>
          <el-button type="primary" size="small" @click="onQuery">查询</el-button>
          <el-button type="success" size="small" @click="onQueryReset">重置</el-button>
          <el-button type="warning" size="small" @click="handleAdd">新增</el-button>
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
          <el-button type="primary" size="small" @click="handleModify(scope.row)">修改</el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-row>
  <el-row style="padding: 10px;float: right;">
    <el-pagination background layout="prev, pager, next" :total="pageTotal" small @current-change="handleCurrentChange"/>
  </el-row>

  <el-dialog  v-model="dialogFormVisible" :title="formTitle">
    <el-form :model="form" :rules="formRules" ref="formRef">
       <el-form-item label="标题：" :label-width="formLabelWidth" prop="title">
         <el-input v-model="form.title" />
       </el-form-item>
       <el-form-item label="是否最新公告：" :label-width="formLabelWidth" prop="isNew">
         <el-select
            v-model="form.isNew"
            collapse-tags
            collapse-tags-tooltip
            :max-collapse-tags="5"
            placeholder="选择是否最新公告"
         >
           <el-option
             v-for="item in dictYesNoOptions"
             :key="item.itemValue"
             :label="item.itemName"
             :value="item.itemValue"
           />
         </el-select>
       </el-form-item>
       <el-form-item label="内容：" :label-width="formLabelWidth" prop="content">
         <ckeditor
          :editor="state.editor"
          v-model="form.content"
          :config="state.editorConfig"
        ></ckeditor>
       </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleCancle()">取消</el-button>
        <el-button type="primary" @click="handleSubmit()">提交</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { pageUmsNotice, addUmsNotice, modifyUmsNotice, deleteUmsNotice } from '@/api/umsNotice'
import { onMounted, reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getByDictCode, getDictItemNameByValue } from '@/utils/system'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'

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
const formLabelWidth = '140px'
const pageSize = ref(10)
const pageTotal = ref(0)
const tableData = ref([])
const formRef = ref(null)
const state = reactive({
  editor: ClassicEditor,
  editorConfig: {
  }
})
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
const handleAdd = () => {
  resetForm()
  dialogFormVisible.value = true
  formTitle.value = '新增'
  form.actionType = 'add'
}
const handleModify = (row) => {
  resetForm()
  dialogFormVisible.value = true
  formTitle.value = '修改'
  form.actionType = 'edit'
  form.id = row.id
  Object.assign(form, row)
}
const handleDelete = (row) => {
  ElMessageBox.confirm(
    '是否删除当前数据：【' + row.name + '】?',
    '警告',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(() => {
    deleteUmsNotice(row.id, data => {
      ElMessage.success('删除成功')
      loadData(1)
    })
  })
}
const handleCurrentChange = (number) => {
  loadData(number)
}

const handleSubmit = () => {
  formRef.value.validate(valid => {
    if (!valid) {
      return
    }
    let promise = null
    if (form.actionType === 'add') {
      promise = addUmsNotice(form)
    } else {
      promise = modifyUmsNotice(form.id, form)
    }

    promise.then(data => {
      if (data.code === 0) {
        ElMessage.success('操作成功')
        loadData()
        handleCancle()
      } else {
        ElMessage.error(data.msg)
      }
    })
  })
}

const handleCancle = () => {
  resetForm()
  dialogFormVisible.value = false
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
