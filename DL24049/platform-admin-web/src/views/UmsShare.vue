<template>
  <el-form class="demo-form-inline">
    <el-row style="padding: 20px 0px 0px 20px">
      <el-col :span="8">
        <el-form-item label="标题">
            <el-input placeholder="标题" v-model="query.title" clearable />
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
      <el-table-column prop="createTime" label="创建时间"  />
      <el-table-column prop="createName" label="创建者"  />
      <el-table-column fixed="right" label="操作" width="250px">
        <template #default="scope">
          <el-button type="success" size="small" @click="detail(scope.row)">详情</el-button>
          <el-button v-if="profile.id === scope.row.creator" type="primary" size="small" @click="handleModify(scope.row)">修改</el-button>
          <el-button v-if="profile.id === scope.row.creator" type="danger" size="small" @click="handleDelete(scope.row)">删除</el-button>
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
       <el-form-item label="描述：" :label-width="formLabelWidth" prop="description">
         <ckeditor
          :editor="state.editor"
          v-model="form.description"
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

  <el-dialog  v-model="detailFormVisible" title="详情">
    <el-form :model="form" :rules="formRules" ref="formRef">
      <el-row>
        <el-col style="text-align: center;font-size: 30px;">
          {{ form.title }}
        </el-col>
      </el-row>
      <el-row>
        <el-divider border-style="double" />
        <div v-html="form.description" ></div>
      </el-row>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="detailFormVisible = false">关闭</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { pageUmsShare, addUmsShare, modifyUmsShare, deleteUmsShare } from '@/api/umsShare'
import { onMounted, reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
import { useStore } from 'vuex'

const store = useStore()
const { profile } = store.state.user

const form = reactive({
  id: '',
  title: '',
  description: '',
  url: '',
  type: 'share'
})
const backupQueryData = JSON.parse(JSON.stringify(form))
const query = reactive({
  title: '',
  creator: '',
  type: 'share'
})
const state = reactive({
  editor: ClassicEditor,
  editorConfig: {
  }
})
const backupData = JSON.parse(JSON.stringify(form))
const dialogFormVisible = ref(false)
const formTitle = ref(null)
const formLabelWidth = '140px'
const pageSize = ref(10)
const pageTotal = ref(0)
const tableData = ref([])
const formRef = ref(null)
const detailFormVisible = ref(false)
const formRules = reactive({
  title: [
    { required: true, message: '请输入标题', trigger: 'blur' }
  ],
  description: [
    { required: true, message: '请输入描述', trigger: 'blur' }
  ]
})

const loadData = async (current) => {
  const queryData = {
    title: query.title,
    creator: query.creator,
    type: query.type
  }
  pageUmsShare(pageSize.value, current, queryData, data => {
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
    deleteUmsShare(row.id, data => {
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
      promise = addUmsShare(form)
    } else {
      promise = modifyUmsShare(form.id, form)
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
const detail = (row) => {
  detailFormVisible.value = true
  Object.assign(form, row)
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
