<template>
  <el-form class="demo-form-inline">
    <el-row style="padding: 20px;">
      <el-col :span="6">
        <el-form-item label="角色名称：">
          <el-input placeholder="角色名称" v-model="queryName" clearable />
        </el-form-item>
      </el-col>
      <el-col :span="6">
        <el-form-item label="角色编号：">
          <el-input placeholder="角色编号" v-model="queryNum" clearable />
        </el-form-item>
      </el-col>
      <el-col :span="6">
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
      <el-table-column prop="name" label="角色名称"/>
      <el-table-column prop="num" label="角色编号"/>
      <el-table-column prop="description" label="角色描述"  />
      <el-table-column fixed="right" label="操作" >
        <template #default="scope">
          <el-button  type="primary" size="small" @click="handleModify(scope.row)">修改</el-button>
          <el-button  type="success" size="small" @click="handleAuth(scope.row)">权限授权</el-button>
          <el-button  type="danger" size="small" @click="handleDelete(scope.row)">删除</el-button>
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
      <el-form-item label="编号：" :label-width="formLabelWidth" prop="num">
        <el-input v-model="form.num" />
      </el-form-item>
      <el-form-item label="描述：" :label-width="formLabelWidth" prop="description">
        <el-input v-model="form.description" type="textarea" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleCancle()">取消</el-button>
        <el-button type="primary" @click="handleSubmit()">提交</el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="dialogPermissionTreeVisibel" title="权限授权">
    <el-tree
      ref="permissionTreeRef"
      :data="permissionData"
      show-checkbox
      default-expand-all
      node-key="id"
      highlight-current
      :check-strictly='checkStrictly'
      :render-content="renderNode"
      :default-checked-keys="permissionCheckedKeys"
    />
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handlePermissionCancle()">取消</el-button>
        <el-button type="primary" @click="handlePermissionSubmit()">提交</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import { page, deleteRole, addRole, modifyRole, roleAuthPermission, getRoleAuthPermission } from '@/api/role'
import { onMounted, reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { permissionList } from '@/api/permission'
export default {
  name: 'VueRole',
  setup () {
    const form = reactive({
      id: 0,
      name: '',
      num: '',
      description: '',
      actionType: ''
    })
    const backupData = JSON.parse(JSON.stringify(form))
    const dialogFormVisible = ref(false)
    const formTitle = ref(null)
    const formLabelWidth = '140px'
    const pageSize = ref(10)
    const pageTotal = ref(0)
    const queryName = ref('')
    const queryNum = ref('')
    const tableData = ref([])
    const formRef = ref(null)
    const dialogPermissionTreeVisibel = ref(false)
    const permissionData = ref(null)
    const authRole = ref(null)
    const permissionTreeRef = ref(null)
    const permissionCheckedKeys = ref([])
    const checkStrictly = ref(true)
    const formRules = reactive({
      name: [
        { required: true, message: '请输入名称', trigger: 'blur' }
      ],
      num: [
        { required: true, message: '请输入编号', trigger: 'blur' }
      ]
    })
    const loadData = async (current) => {
      page(pageSize.value, current, queryName.value, queryNum.value).then(data => {
        if (data.code === 0) {
          tableData.value = data.data.records
          pageTotal.value = data.data.total
        } else {
          ElMessage.error(data.msg)
        }
      })
    }
    const loadPermission = async () => {
      await permissionList().then(data => {
        if (data.code === 0) {
          permissionData.value = data.data
        } else {
          ElMessage.error(data.msg)
        }
      })
    }
    const loadPermissionChecked = async () => {
      await getRoleAuthPermission(authRole.value.id).then(data => {
        if (data.code === 0) {
          permissionCheckedKeys.value = data.data
        } else {
          ElMessage.error(data.msg)
        }
      })
    }
    onMounted(async () => {
      await loadData(1)
    })
    const onQuery = () => {
      loadData(1)
    }
    const onQueryReset = () => {
      queryName.value = ''
      queryNum.value = ''
      loadData(1)
    }
    const handleAdd = () => {
      resetForm()
      dialogFormVisible.value = true
      formTitle.value = '新增'
      form.actionType = 'add'
    }
    const handleModify = (row) => {
      dialogFormVisible.value = true
      formTitle.value = '修改'
      form.actionType = 'edit'
      form.id = row.id
      Object.assign(form, row)
    }
    const handleDelete = (row) => {
      ElMessageBox.confirm(
        '是否删除当前角色：【' + row.name + '】?',
        '警告',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).then(() => {
        deleteRole(row.id).then(data => {
          if (data.code === 0) {
            ElMessage.success('删除成功')
            loadData(1)
          } else {
            ElMessage.error(data.msg)
          }
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
          promise = addRole({
            name: form.name,
            num: form.num,
            description: form.description
          })
        } else {
          promise = modifyRole({
            id: form.id,
            name: form.name,
            num: form.num,
            description: form.description
          })
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

    const handleAuth = (row) => {
      authRole.value = row
      loadPermission()
      loadPermissionChecked()
      dialogPermissionTreeVisibel.value = true
    }

    const handlePermissionCancle = () => {
      authRole.value = null
      permissionCheckedKeys.value = []
      dialogPermissionTreeVisibel.value = false
    }

    const handlePermissionSubmit = () => {
      const nodes = []
      permissionTreeRef.value.getHalfCheckedNodes().forEach(node => nodes.push(node.id))
      permissionTreeRef.value.getCheckedNodes().forEach(node => nodes.push(node.id))
      roleAuthPermission(authRole.value.id, nodes).then(data => {
        if (data.code === 0) {
          dialogPermissionTreeVisibel.value = false
          authRole.value = null
          permissionCheckedKeys.value = []
          ElMessage.success('授权成功')
          loadData(1)
        } else {
          ElMessage.error(data.msg)
        }
      })
    }

    const renderNode = (h, { node, data }) => {
      return data.name
    }

    return {
      form,
      formRef,
      formRules,
      dialogFormVisible,
      formTitle,
      formLabelWidth,
      handleSubmit,
      handleCancle,
      onQuery,
      onQueryReset,
      handleModify,
      handleAdd,
      handleDelete,
      tableData,
      handleCurrentChange,
      pageTotal,
      queryName,
      queryNum,
      dialogPermissionTreeVisibel,
      handleAuth,
      permissionData,
      renderNode,
      handlePermissionCancle,
      handlePermissionSubmit,
      permissionTreeRef,
      permissionCheckedKeys,
      checkStrictly
    }
  }
}
</script>
<style scoped>

.demo-form-inline .el-input {
  --el-input-width: 220px;
}

</style>
