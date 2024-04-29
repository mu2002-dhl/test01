<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <el-button type="primary" @click="handleAddRoot()">新增</el-button>
      </el-header>
      <el-main>
        <el-table
          :data="tableData"
          style="width: 100%; margin-bottom: 20px"
          row-key="id"
          border
          default-expand-all
        >
          <el-table-column prop="deptName" label="部门名称" />
          <el-table-column prop="deptNum" label="部门编号" />
          <el-table-column prop="sort" label="排序" />
          <el-table-column label="操作">
            <template #default="scope">
              <el-button size="small" type="success" @click="handleAdd(scope.row)">新增下级</el-button>
              <el-button size="small" type="primary" @click="handleEdit(scope.row)">修改</el-button>
              <el-button size="small" type="danger" @click="handleDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </div>

  <el-dialog  v-model="dialogFormVisible" :title="formTitle">
    <el-form :model="form" :rules="formRules" ref="formRef">
      <el-form-item label="上级部门" :label-width="formLabelWidth" prop="parentId">
        <el-input v-model="form.parentName" disabled />
        <el-input v-model="form.parentId" style="display: none;"/>
      </el-form-item>
      <el-form-item label="部门名称" :label-width="formLabelWidth" prop="deptName">
        <el-input v-model="form.deptName" />
      </el-form-item>
      <el-form-item label="部门编号" :label-width="formLabelWidth" prop="deptNum">
        <el-input v-model="form.deptNum" />
      </el-form-item>
      <el-form-item label="排序" :label-width="formLabelWidth" prop="sort">
        <el-input-number v-model="form.sort" :min="0" :max="9999"/>
      </el-form-item>
      <el-form-item label="描述" :label-width="formLabelWidth" prop="description">
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
</template>

<script>
import { onMounted, reactive, ref } from 'vue'
import { deptList, addDept, detailDept, modifyDept, deleteDept } from '@/api/dept'
import { ElMessage, ElMessageBox } from 'element-plus'

export default {
  name: 'VueDept',
  setup () {
    const form = reactive({
      id: 0,
      parentId: 0,
      parentName: '',
      deptNum: '',
      deptName: '',
      sort: 0,
      description: '',
      actionType: ''
    })
    const backupData = JSON.parse(JSON.stringify(form))
    const tableData = ref(null)
    const dialogFormVisible = ref(false)
    const formLabelWidth = '140px'
    const formTitle = ref(null)

    const fetchData = async () => {
      const promise = deptList()
      promise.then(data => {
        if (data.code === 0) {
          tableData.value = data.data
        } else {
          ElMessage.error(data.msg)
        }
      })
    }

    onMounted(async () => {
      await fetchData()
    })

    const formRef = ref(null)

    const formRules = reactive({
      deptName: [
        { required: true, message: '请输入部门名称', trigger: 'blur' }
      ]
    })

    const handleAddRoot = () => {
      resetForm()
      formTitle.value = '新增部门'
      form.actionType = 'add'
      dialogFormVisible.value = true
    }

    const handleAdd = async (row) => {
      resetForm()
      formTitle.value = '新增下级部门'
      form.actionType = 'add'
      dialogFormVisible.value = true
      form.parentId = row.id
      form.parentName = row.deptName
    }

    const handleEdit = (row) => {
      formTitle.value = '修改部门'
      dialogFormVisible.value = true
      form.actionType = 'edit'

      const promise = detailDept(row.id)
      promise.then(data => {
        if (data.code === 0) {
          const currentData = data.data
          Object.assign(form, data.data)
          if (currentData.parentId !== 0) {
            const parentPromise = detailDept(currentData.parentId)
            parentPromise.then(parentData => {
              if (parentData.code === 0) {
                form.parentName = parentData.data.deptName
              } else {
                ElMessage.error('查询上级节点失败')
              }
            })
          }
        } else {
          ElMessage.error(data.msg)
        }
      })
    }

    const handleDelete = (row) => {
      ElMessageBox.confirm(
        '是否删除当前部门：【' + row.deptName + '】?',
        '警告',
        {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }
      ).then(() => {
        deleteDept(row.id).then(data => {
          if (data.code === 0) {
            ElMessage.success('删除成功')
            fetchData()
          } else {
            ElMessage.error(data.msg)
          }
        })
      })
    }

    const handleSubmit = () => {
      formRef.value.validate(valid => {
        if (!valid) {
          return
        }
        let promise = null
        if (form.actionType === 'add') {
          promise = addDept({
            deptNum: form.deptNum,
            deptName: form.deptName,
            parentId: form.parentId,
            sort: form.sort,
            description: form.description
          })
        } else {
          promise = modifyDept({
            id: form.id,
            deptNum: form.deptNum,
            deptName: form.deptName,
            parentId: form.parentId,
            sort: form.sort,
            description: form.description
          })
        }

        promise.then(data => {
          if (data.code === 0) {
            ElMessage.success('操作成功')
            fetchData()
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

    return {
      handleAddRoot,
      handleAdd,
      handleEdit,
      handleDelete,
      handleSubmit,
      handleCancle,
      tableData,
      dialogFormVisible,
      formTitle,
      formRef,
      formRules,
      formLabelWidth,
      form
    }
  }
}
</script>

<style>

</style>
