<template>
  <el-form class="demo-form-inline">
    <el-row style="padding: 20px 0px 0px 20px">
      <el-col :span="8">
        <el-form-item label="名称">
            <el-input placeholder="名称" v-model="query.name" clearable />
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
      <el-table-column prop="name" label="名称"  />
      <el-table-column prop="address" label="地址"  />
      <el-table-column prop="charge" label="法人"  />
      <el-table-column prop="phone" label="联系方式"  />
      <el-table-column prop="scope" label="经营范围"  />
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
       <el-form-item label="名称：" :label-width="formLabelWidth" prop="name">
         <el-input v-model="form.name" />
       </el-form-item>
       <el-form-item label="地址：" :label-width="formLabelWidth" prop="address">
         <el-input v-model="form.address" />
       </el-form-item>
       <el-form-item label="法人：" :label-width="formLabelWidth" prop="charge">
         <el-input v-model="form.charge" />
       </el-form-item>
       <el-form-item label="联系方式：" :label-width="formLabelWidth" prop="phone">
         <el-input v-model="form.phone" />
       </el-form-item>
       <el-form-item label="经营范围：" :label-width="formLabelWidth" prop="scope">
         <el-input type="textarea" :rows="10" v-model="form.scope" />
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
import { pageUmsStore, addUmsStore, modifyUmsStore, deleteUmsStore } from '@/api/umsStore'
import { onMounted, reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'

const form = reactive({
  id: '',
  name: '',
  address: '',
  scope: '',
  charge: '',
  phone: ''
})
const backupQueryData = JSON.parse(JSON.stringify(form))
const query = reactive({
  name: ''
})
const backupData = JSON.parse(JSON.stringify(form))
const dialogFormVisible = ref(false)
const formTitle = ref(null)
const formLabelWidth = '140px'
const pageSize = ref(10)
const pageTotal = ref(0)
const tableData = ref([])
const formRef = ref(null)
const formRules = reactive({
  name: [
    { required: true, message: '请输入名称', trigger: 'blur' }
  ],
  address: [
    { required: true, message: '请输入地址', trigger: 'blur' }
  ],
  scope: [
    { required: true, message: '请输入经营范围', trigger: 'blur' }
  ],
  charge: [
    { required: true, message: '请输入法人', trigger: 'blur' }
  ],
  phone: [
    { required: true, message: '请输入联系方式', trigger: 'blur' }
  ]
})

const loadData = async (current) => {
  const queryData = {
    name: query.name
  }
  pageUmsStore(pageSize.value, current, queryData, data => {
    tableData.value = data.records
    pageTotal.value = data.total
  })
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
    deleteUmsStore(row.id, data => {
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
      promise = addUmsStore(form)
    } else {
      promise = modifyUmsStore(form.id, form)
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
<style scoped>

.demo-form-inline .el-input {
  --el-input-width: 220px;
}

</style>
