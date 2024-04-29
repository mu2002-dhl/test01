<template>
  <el-form class="demo-form-inline">
    <el-row style="padding: 20px 0px 0px 20px">
      <el-col :span="8">
        <el-form-item label="活动ID">
            <el-input placeholder="活动ID" v-model="query.activityId" clearable />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="创建者">
            <el-input placeholder="创建者" v-model="query.creator" clearable />
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
      <el-table-column prop="activityId" label="活动ID"  />
      <el-table-column prop="createTime" label="创建时间"  />
      <el-table-column prop="creator" label="创建者"  />
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
       <el-form-item label="活动ID：" :label-width="formLabelWidth" prop="activityId">
         <el-input v-model="form.activityId" />
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
import { pageUmsActivityApply, addUmsActivityApply, modifyUmsActivityApply, deleteUmsActivityApply } from '@/api/umsActivityApply'
import { onMounted, reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'

const form = reactive({
  id: '',
  activityId: ''
})
const backupQueryData = JSON.parse(JSON.stringify(form))
const query = reactive({
  activityId: '',
  creator: ''
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
  activityId: [
    { required: true, message: '请输入活动ID', trigger: 'blur' }
  ]
})

const loadData = async (current) => {
  const queryData = {
    activityId: query.activityId,
    creator: query.creator
  }
  pageUmsActivityApply(pageSize.value, current, queryData, data => {
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
    deleteUmsActivityApply(row.id, data => {
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
      promise = addUmsActivityApply(form)
    } else {
      promise = modifyUmsActivityApply(form.id, form)
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
