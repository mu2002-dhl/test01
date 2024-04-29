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
          <el-button type="primary" size="small" @click="handleModify(scope.row)">查看详情</el-button>
          <el-button type="danger" size="small" @click="comment(scope.row)">评论</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-row>
  <el-row style="padding: 10px;float: right;">
    <el-pagination background layout="prev, pager, next" :total="pageTotal" small @current-change="handleCurrentChange"/>
  </el-row>

  <el-dialog  v-model="dialogFormVisible" :title="formTitle">
    <el-col class="goods-card">
          <el-tabs type="border-card">
            <el-tab-pane label="景点详情">
              <el-form :model="form" :rules="formRules" ref="formRef">
              <el-form-item label="名称：" :label-width="formLabelWidth" prop="name">
                <el-input v-model="form.name" readonly/>
              </el-form-item>
              <el-form-item label="地址：" :label-width="formLabelWidth" prop="address">
                <el-input v-model="form.address" readonly/>
              </el-form-item>
              <el-form-item label="法人：" :label-width="formLabelWidth" prop="charge">
                <el-input v-model="form.charge" readonly/>
              </el-form-item>
              <el-form-item label="联系方式：" :label-width="formLabelWidth" prop="phone">
                <el-input v-model="form.phone" readonly/>
              </el-form-item>
              <el-form-item label="经营范围：" :label-width="formLabelWidth" prop="scope">
                <el-input type="textarea" :rows="10" v-model="form.scope" readonly/>
              </el-form-item>
            </el-form>
            </el-tab-pane>
            <el-tab-pane label="商铺评价">
              <el-timeline style="max-width: 600px">
                <el-timeline-item
                  v-for="(comment, index) in commentOptions"
                  :key="index"
                  :timestamp="comment.createTime"
                >
                  {{ comment.description }}
                </el-timeline-item>
              </el-timeline>
            </el-tab-pane>
          </el-tabs>
        </el-col>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleCancle()">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { pageUmsStore } from '@/api/umsStore'
import { onMounted, reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { addUmsComment, pageUmsComment } from '@/api/umsComment'

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
const commentOptions = ref(null)
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

const handleModify = (row) => {
  resetForm()
  pageUmsComment(1000, 0, {
    objId: row.id,
    type: 'store'
  }, data => {
    commentOptions.value = data.records
  })
  dialogFormVisible.value = true
  formTitle.value = '详情'
  form.actionType = 'edit'
  form.id = row.id
  Object.assign(form, row)
}

const handleCurrentChange = (number) => {
  loadData(number)
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
const comment = (row) => {
  ElMessageBox.prompt('请输入对商铺【' + row.name + '】的评论', '评论', {
    confirmButtonText: '提交',
    cancelButtonText: '取消'
  })
    .then(({ value }) => {
      const commentData = {
        objId: row.id,
        type: 'store',
        description: value
      }
      if (value === '' || value === null) {
        ElMessage.error('评论不能为空')
      } else {
        addUmsComment(commentData).then(data => {
          if (data.code === 0) {
            ElMessage.success('评论成功')
            loadData(1)
          } else {
            ElMessage.error(data.msg)
          }
        })
      }
    })
}
</script>
<style scoped>

.demo-form-inline .el-input {
  --el-input-width: 220px;
}

</style>
