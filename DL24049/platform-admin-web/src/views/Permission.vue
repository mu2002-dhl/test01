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
          <el-table-column prop="name" label="名称" />
          <el-table-column prop="permission" label="权限" />
          <el-table-column prop="url" label="访问地址" />
          <el-table-column prop="component" label="前端组件名称" />
          <el-table-column prop="type" label="类型" >
            <template #default="scope">
              <el-tag
                :type="renderTag(scope.row)"
                disable-transitions
                >{{ renderTagValue(scope.row) }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="enable" label="是否可用" >
            <template #default="scope">
              <el-tag
                :type="scope.row.enable ? 'success':'danger'"
                disable-transitions
                >{{ scope.row.enable ? '可用':'不可用' }}
              </el-tag>
            </template>
          </el-table-column>
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
      <el-form-item label="上级权限" :label-width="formLabelWidth" prop="parentId">
        <el-input v-model="form.parentName" disabled />
        <el-input v-model="form.parentId" style="display: none;"/>
      </el-form-item>
      <el-form-item label="名称" :label-width="formLabelWidth" prop="name">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="权限" :label-width="formLabelWidth" prop="permission">
        <el-input v-model="form.permission" />
      </el-form-item>
      <el-form-item label="访问地址" :label-width="formLabelWidth" prop="url">
        <el-input v-model="form.url" />
      </el-form-item>
      <el-form-item label="前端组件名称" :label-width="formLabelWidth" prop="url">
        <el-input v-model="form.component" />
      </el-form-item>
      <el-form-item label="类型" :label-width="formLabelWidth" prop="type">
        <el-select
          v-model="form.type"
          placeholder="选择类型"
        >
          <el-option label="菜单" value='0'></el-option>
          <el-option label="按钮" value='1'></el-option>
          <el-option label="接口" value='2'></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="图标" :label-width="formLabelWidth" prop="icon">
        <el-input v-model="form.icon" />
      </el-form-item>
      <el-form-item label="排序" :label-width="formLabelWidth" prop="sort">
        <el-input-number v-model="form.sort" :min="0" :max="9999"/>
      </el-form-item>
      <el-form-item label="是否可用" :label-width="formLabelWidth" prop="enable">
        <el-select
          v-model="form.enable"
        >
          <el-option label="可用" value='true'></el-option>
          <el-option label="不可用" value='false'></el-option>
        </el-select>
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
import { permissionList, addPermission, detailPermission, modifyPermission, deletePermission } from '@/api/permission'
import { ElMessage, ElMessageBox } from 'element-plus'

export default {
  name: 'VuePermission',
  setup () {
    const form = reactive({
      id: 0,
      name: '',
      permission: '',
      url: '',
      // vue bug：当设置为整形，select当作label显示无法设置选中。只能设置为字符型
      type: '0',
      sort: 0,
      parentId: 0,
      parentName: '',
      enable: 'true',
      actionType: ''
    })
    const backupData = JSON.parse(JSON.stringify(form))
    const tableData = ref(null)
    const dialogFormVisible = ref(false)
    const formLabelWidth = '140px'
    const formTitle = ref(null)

    const fetchData = async () => {
      const promise = permissionList()
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

    // const checkTypeRule = (rule, value, callback) => {
    //   if (form.type === '0' && form.url.trim().length === 0) {
    //     callback(new Error('权限类型为 `菜单`必须设置 `访问地址`'))
    //   } else {
    //     callback()
    //   }
    // }

    const formRules = reactive({
      name: [
        { required: true, message: '请输入名称', trigger: 'blur' }
      ]
      // ,
      // permission: [
      //   { required: true, message: '请输入权限', trigger: 'blur' }
      // ]
      // ,
      // type: [
      //   { validator: checkTypeRule, trigger: 'blur' }
      // ]
    })

    const handleAddRoot = () => {
      resetForm()
      formTitle.value = '新增权限'
      form.actionType = 'add'
      dialogFormVisible.value = true
    }

    const handleAdd = async (row) => {
      resetForm()
      formTitle.value = '新增下级权限'
      form.actionType = 'add'
      dialogFormVisible.value = true
      form.parentId = row.id
      form.parentName = row.name
    }

    const handleEdit = (row) => {
      formTitle.value = '修改权限'
      dialogFormVisible.value = true
      form.actionType = 'edit'

      const promise = detailPermission(row.id)
      promise.then(data => {
        if (data.code === 0) {
          const currentData = data.data
          Object.assign(form, data.data)
          form.type = data.data.type + ''
          form.enable = data.data.enable + ''
          if (currentData.parentId !== 0) {
            const parentPromise = detailPermission(currentData.parentId)
            parentPromise.then(parentData => {
              if (parentData.code === 0) {
                form.parentName = parentData.data.name
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
        '是否删除当前权限：【' + row.name + '】?',
        '警告',
        {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }
      ).then(() => {
        deletePermission(row.id).then(data => {
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
          promise = addPermission({
            name: form.name,
            permission: form.permission,
            url: form.url,
            type: form.type,
            sort: form.sort,
            parentId: form.parentId,
            enable: form.enable,
            icon: form.icon,
            component: form.component
          })
        } else {
          promise = modifyPermission({
            id: form.id,
            name: form.name,
            permission: form.permission,
            url: form.url,
            type: form.type,
            sort: form.sort,
            parentId: form.parentId,
            enable: form.enable,
            icon: form.icon,
            component: form.component
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

    const renderTag = (row) => {
      if (row.type === 0) {
        return ''
      } else if (row.type === 1) {
        return 'success'
      } else {
        return 'info'
      }
    }

    const renderTagValue = (row) => {
      if (row.type === 0) {
        return '菜单'
      } else if (row.type === 1) {
        return '按钮'
      } else {
        return '接口'
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
      form,
      renderTag,
      renderTagValue
    }
  }
}
</script>
<style>

</style>
