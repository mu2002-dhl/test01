<template>
  <el-form class="demo-form-inline">
    <el-row style="padding: 20px 0px 0px 20px">
      <el-col :span="8">
        <el-form-item label="名称">
            <el-input placeholder="名称" v-model="query.name" clearable />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="所属景点">
            <el-select
              v-model="query.placeId"
              collapse-tags
              collapse-tags-tooltip
              :max-collapse-tags="5"
              placeholder="所属景点"
              clearable
            >
              <el-option
                v-for="item in placeOptions"
                :key="item.id"
                :label="item.name"
                :value="item.id"
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
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
  <el-row>
    <el-table :data="tableData" border  style="width: 100%">
      <el-table-column prop="name" label="名称"  />
      <el-table-column prop="placeName" label="所属景点"  />
      <el-table-column prop="unit" label="主办单位"  />
      <el-table-column prop="num" label="最大人数"  />
      <el-table-column prop="phone" label="联系方式"  />
      <el-table-column fixed="right" label="操作" width="250px">
        <template #default="scope">
          <el-button v-if="!scope.row.apply" type="primary" size="small" @click="activityApply(scope.row)">报名</el-button>
          <el-button v-if="scope.row.apply" type="danger" size="small" @click="activityCancleApply(scope.row)">取消报名</el-button>
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
       <el-form-item label="所属景点：" :label-width="formLabelWidth" prop="placeId">
         <el-select
              v-model="form.placeId"
              collapse-tags
              collapse-tags-tooltip
              :max-collapse-tags="5"
              placeholder="所属景点"
              clearable
            >
              <el-option
                v-for="item in placeOptions"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
       </el-form-item>
       <el-form-item label="主办单位：" :label-width="formLabelWidth" prop="unit">
         <el-input v-model="form.unit" />
       </el-form-item>
       <el-form-item label="最大人数：" :label-width="formLabelWidth" prop="num">
         <el-input v-model="form.num" />
       </el-form-item>
       <el-form-item label="联系方式：" :label-width="formLabelWidth" prop="phone">
         <el-input v-model="form.phone" />
       </el-form-item>
       <el-form-item label="活动描述：" :label-width="formLabelWidth" prop="description">
         <el-input type="textarea" :rows="10" v-model="form.description" />
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
import { pageUmsActivity, addUmsActivity, modifyUmsActivity } from '@/api/umsActivity'
import { onMounted, reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { getAllUmsPlaces } from '@/api/umsPlaces'
import { addUmsActivityApply, deleteUmsActivityApply } from '@/api/umsActivityApply'

const form = reactive({
  id: '',
  name: '',
  unit: '',
  num: '',
  phone: '',
  description: '',
  placeId: ''
})
const backupQueryData = JSON.parse(JSON.stringify(form))
const query = reactive({
  name: '',
  placeId: ''
})
const backupData = JSON.parse(JSON.stringify(form))
const dialogFormVisible = ref(false)
const formTitle = ref(null)
const formLabelWidth = '140px'
const pageSize = ref(10)
const pageTotal = ref(0)
const tableData = ref([])
const formRef = ref(null)
const placeOptions = ref(null)
const formRules = reactive({
  name: [
    { required: true, message: '请输入名称', trigger: 'blur' }
  ],
  unit: [
    { required: true, message: '请输入主办单位', trigger: 'blur' }
  ],
  phone: [
    { required: true, message: '请输入联系方式', trigger: 'blur' }
  ],
  description: [
    { required: true, message: '请输入活动描述', trigger: 'blur' }
  ]
})

const loadPlace = () => {
  getAllUmsPlaces(data => {
    placeOptions.value = data
  })
}
const loadData = async (current) => {
  const queryData = {
    name: query.name,
    placeId: query.placeId
  }
  pageUmsActivity(pageSize.value, current, queryData, data => {
    tableData.value = data.records
    pageTotal.value = data.total
  })
}

onMounted(() => {
  loadData(1)
  loadPlace()
})
const onQuery = () => {
  loadData(1)
}
const onQueryReset = () => {
  Object.assign(query, backupQueryData)
  loadData(1)
}

const activityApply = (row) => {
  const data = {
    activityId: row.id
  }
  addUmsActivityApply(data).then(data => {
    if (data.code === 0) {
      ElMessage.success('报名成功')
      loadData(1)
    } else {
      ElMessage.error(data.msg)
    }
  })
}

const activityCancleApply = (row) => {
  deleteUmsActivityApply(row.applyId, data => {
    ElMessage.success('取消报名成功')
    loadData(1)
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
      promise = addUmsActivity(form)
    } else {
      promise = modifyUmsActivity(form.id, form)
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
