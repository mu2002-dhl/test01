<template>
  <el-form class="demo-form-inline">
    <el-row style="padding: 20px 0px 0px 20px">
      <el-col :span="8">
        <el-form-item label="登录名称：">
          <el-input placeholder="登录名称" v-model="query.loginName" clearable />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="用户昵称：">
          <el-input placeholder="用户昵称" v-model="query.nickname" clearable />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="邮箱：">
          <el-input placeholder="邮箱" v-model="query.email" clearable />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row style="padding-left: 20px;">
      <el-col :span="8">
        <el-form-item label="手机号码：">
          <el-input placeholder="手机号码" v-model="query.phone" clearable />
        </el-form-item>
      </el-col>
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
      <el-table-column prop="loginName" label="登录名称"/>
      <el-table-column prop="nickname" label="用户昵称"/>
      <el-table-column prop="age" label="年龄"  />
      <el-table-column prop="email" label="邮箱"  />
      <el-table-column prop="phone" label="手机号码"  />
      <el-table-column prop="enable" label="启用状态" width="90px">
        <template #default="scope">
          <el-tag v-if="scope.row.enable">已启用</el-tag>
          <el-tag type="danger" v-if="!scope.row.enable">未启用</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="locked" label="锁定状态" width="90px">
        <template #default="scope">
          <el-tag type="danger" v-if="scope.row.locked">已锁定</el-tag>
          <el-tag v-if="!scope.row.locked">未锁定</el-tag>
        </template>
      </el-table-column>
      <!-- <el-table-column prop="dept" label="部门">
        <template #default="scope">
          <el-tag type="success" v-if="scope.row.dept">{{ scope.row.dept.deptName }}</el-tag>
        </template>
      </el-table-column> -->
      <el-table-column prop="roleList" label="角色">
        <template #default="scope">
          <el-tag type="warning" v-if="scope.row.roleList">{{ scope.row.roleList.map(item => `${item.name}`).join(", ") }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="250px">
        <template #default="scope">
          <el-button type="primary" size="small" @click="handleModify(scope.row)">修改</el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope.row)">删除</el-button>
          <el-dropdown size="small" split-button type="success" style="padding-left: 10px;padding-top: 1.5px;">
            更多
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="changePwd(scope.row)">
                  <el-icon><InfoFilled /></el-icon>
                  修改密码
                </el-dropdown-item>
                <el-dropdown-item @click="changeEnabel(scope.row)">
                  <el-icon><Promotion /></el-icon>
                  {{scope.row.enable?'禁用':'启用'}}
                </el-dropdown-item>
                <el-dropdown-item @click="changeLock(scope.row)">
                  <el-icon><Lock /></el-icon>
                  {{scope.row.locked?'解锁':'锁定'}}
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
  </el-row>
  <el-row style="padding: 10px;float: right;">
    <el-pagination background layout="prev, pager, next" :total="pageTotal" small @current-change="handleCurrentChange"/>
  </el-row>

  <el-dialog  v-model="dialogFormVisible" :title="formTitle">
    <el-form :model="form" :rules="formRules" ref="formRef">
      <el-form-item label="登录名称：" :label-width="formLabelWidth" prop="loginName">
        <el-input v-model="form.loginName" />
      </el-form-item>
      <el-form-item label="用户昵称：" :label-width="formLabelWidth" prop="nickname">
        <el-input v-model="form.nickname" />
      </el-form-item>
      <el-form-item v-if="form.actionType === 'add'" label="密码：" :label-width="formLabelWidth" prop="password">
        <el-input v-model="form.password" />
      </el-form-item>
      <el-form-item label="年龄：" :label-width="formLabelWidth" prop="email">
        <el-input v-model="form.age" />
      </el-form-item>
      <el-form-item label="邮箱：" :label-width="formLabelWidth" prop="email">
        <el-input v-model="form.email" />
      </el-form-item>
      <el-form-item label="手机号码：" :label-width="formLabelWidth" prop="phone">
        <el-input v-model="form.phone" />
      </el-form-item>
      <el-form-item label="角色" :label-width="formLabelWidth" prop="role">
        <el-select
          v-model="form.roleList"
          multiple
          collapse-tags
          collapse-tags-tooltip
          :max-collapse-tags="5"
          placeholder="选择角色"
          style="width: 400px"
          value-key="num"
        >
          <el-option
            v-for="item in roleOptions"
            :key="item.id"
            :label="item.name"
            :value="item"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="部门" :label-width="formLabelWidth" prop="deptId">
        <el-tree-select
          v-model="form.deptId"
          :data="deptData"
          check-strictly
          :render-after-expand="false"
          placeholder="选择部门"
        />
      </el-form-item> -->
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleCancle()">取消</el-button>
        <el-button type="primary" @click="handleSubmit()">提交</el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog  v-model="dialogChangePwdVisible" title="修改密码">
    <el-form :model="changePwdForm" :rules="changePwdFormRules" ref="changePwdFormRef">
      <el-form-item label="密码：" :label-width="formLabelWidth" prop="pwd">
        <el-input v-model="changePwdForm.pwd" type="password"/>
      </el-form-item>
      <el-form-item label="确认密码：" :label-width="formLabelWidth" prop="confirmPwd">
        <el-input v-model="changePwdForm.confirmPwd" type="password"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleCancleChangePwd()">取消</el-button>
        <el-button type="primary" @click="handleSubmitChangePwd()">提交</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import { page, deleteUser, addUser, modifyUser, changeUserPwd, changeUserEnable, changeUserLocked } from '@/api/user'
import { allRole } from '@/api/role'
import { deptList } from '@/api/dept'
import { onMounted, reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
export default {
  name: 'VueUser',
  setup () {
    const form = reactive({
      id: 0,
      loginName: '',
      nickname: '',
      password: '',
      email: '',
      phone: '',
      roleList: [],
      deptId: '',
      actionType: ''

    })
    const changePwdForm = reactive({
      pwd: '',
      confirmPwd: ''
    })
    const backupQueryData = JSON.parse(JSON.stringify(form))
    const query = reactive({
      loginName: '',
      nickname: '',
      email: '',
      phone: ''
    })
    const roleOptions = ref(null)
    const deptData = ref()
    const backupData = JSON.parse(JSON.stringify(form))
    const dialogFormVisible = ref(false)
    const formTitle = ref(null)
    const formLabelWidth = '140px'
    const pageSize = ref(10)
    const pageTotal = ref(0)
    const tableData = ref([])
    const formRef = ref(null)
    const dialogChangePwdVisible = ref(false)
    const changePwdFormRef = ref(null)
    const currentChangePwdUser = ref(null)
    const formRules = reactive({
      loginName: [
        { required: true, message: '请输入登录名称', trigger: 'blur' }
      ],
      nickname: [
        { required: true, message: '请输入用户昵称', trigger: 'blur' }
      ]
    })
    const checkPwdConfirmPwdSameRule = (rule, value, callback) => {
      if (changePwdForm.pwd !== changePwdForm.confirmPwd) {
        callback(new Error('密码与确认密码不同'))
      } else if (changePwdForm.pwd.length < 6 || changePwdForm.pwd.length > 24) {
        callback(new Error('密码密码长度范围：6-24位'))
      } else if (changePwdForm.confirmPwd.length < 6 || changePwdForm.confirmPwd.length > 24) {
        callback(new Error('密码密码长度范围：6-24位'))
      } else {
        callback()
      }
    }
    const changePwdFormRules = reactive({
      pwd: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { validator: checkPwdConfirmPwdSameRule, trigger: 'blur' }
      ],
      confirmPwd: [
        { required: true, message: '请输入确认密码', trigger: 'blur' },
        { validator: checkPwdConfirmPwdSameRule, trigger: 'blur' }
      ]
    })
    const loadData = async (current) => {
      await page(pageSize.value, current, query.loginName, query.nickname, query.email, query.phone).then(data => {
        if (data.code === 0) {
          tableData.value = data.data.records
          pageTotal.value = data.data.total
        } else {
          ElMessage.error(data.msg)
        }
      })
    }
    const loadRoleOptions = async () => {
      await allRole().then(data => {
        if (data.code === 0) {
          roleOptions.value = data.data
        } else {
          ElMessage.error(data.msg)
        }
      })
    }
    const loadDeptTreeData = async () => {
      await deptList().then(data => {
        if (data.code === 0) {
          deptData.value = data.data
        } else {
          ElMessage.error(data.msg)
        }
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
      loadRoleOptions()
      loadDeptTreeData()
      dialogFormVisible.value = true
      formTitle.value = '新增'
      form.actionType = 'add'
    }
    const handleModify = (row) => {
      resetForm()
      loadRoleOptions()
      loadDeptTreeData()
      dialogFormVisible.value = true
      formTitle.value = '修改'
      form.actionType = 'edit'
      form.id = row.id
      Object.assign(form, row)
      if (row.dept) {
        form.deptId = row.dept.id.toString()
      }
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
        deleteUser(row.id).then(data => {
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
          promise = addUser({
            loginName: form.loginName,
            nickname: form.nickname,
            email: form.email,
            phone: form.phone,
            roleIds: form.roleList.map(data => data.id),
            deptId: form.deptId,
            password: form.password,
            age: form.age
          })
        } else {
          promise = modifyUser({
            id: form.id,
            loginName: form.loginName,
            nickname: form.nickname,
            email: form.email,
            phone: form.phone,
            roleIds: form.roleList.map(data => data.id),
            deptId: form.deptId,
            age: form.age
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
    const changePwd = (row) => {
      dialogChangePwdVisible.value = true
      currentChangePwdUser.value = row
    }
    const handleCancleChangePwd = () => {
      dialogChangePwdVisible.value = false
      currentChangePwdUser.value = null
      changePwdFormRef.value.resetFields()
      changePwdForm.pwd = ''
      changePwdForm.confirmPwd = ''
    }
    const handleSubmitChangePwd = () => {
      changePwdFormRef.value.validate(valid => {
        if (!valid) {
          return
        }

        changeUserPwd(currentChangePwdUser.value.id, changePwdForm.pwd).then(data => {
          if (data.code === 0) {
            handleCancleChangePwd()
            ElMessage.success('密码修改成功')
          } else {
            ElMessage.error(data.msg)
          }
        })
      })
    }
    const changeLock = (row) => {
      ElMessageBox.confirm(
        '是否' + (row.locked ? '解锁' : '锁定') + '用户',
        '提示',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).then(() => {
        changeUserLocked(row.id).then(data => {
          if (data.code === 0) {
            ElMessage.success('操作成功')
            loadData(1)
          } else {
            ElMessage.error(data.msg)
          }
        })
      })
    }
    const changeEnabel = (row) => {
      ElMessageBox.confirm(
        '是否' + (row.enable ? '禁用' : '启用') + '用户',
        '提示',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).then(() => {
        changeUserEnable(row.id).then(data => {
          if (data.code === 0) {
            ElMessage.success('操作成功')
            loadData(1)
          } else {
            ElMessage.error(data.msg)
          }
        })
      })
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
      query,
      roleOptions,
      deptData,
      currentChangePwdUser,
      changePwd,
      dialogChangePwdVisible,
      changePwdForm,
      changePwdFormRules,
      changePwdFormRef,
      handleCancleChangePwd,
      handleSubmitChangePwd,
      changeLock,
      changeEnabel
    }
  }
}
</script>
<style scoped>

.demo-form-inline .el-input {
  --el-input-width: 220px;
}

</style>
