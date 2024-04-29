<template>
  <el-dialog  title="个人信息" v-model="dialogVisible">
    <div class="common-layout">
      <el-container>
        <el-aside width="200px">
          <el-form>
            <el-form-item  label-width="80px" prop="loginName">
              <el-avatar :size="120" :src="userAvatar" />
            </el-form-item>
            <el-form-item label-width="95px">
              <el-upload
                ref="uploadRef"
                class="avatar-uploader"
                :action=uploadAction
                :show-file-list="false"
                :on-success="handleAvatarSuccess"              >
              <template #trigger>
                <el-button type="primary">修改头像</el-button>
              </template>
              </el-upload>
            </el-form-item>
          </el-form>

        </el-aside>
        <el-main>
          <el-form :model="form" :rules="formRules" ref="formRef">
            <el-form-item label="登录名：" :label-width="formLabelWidth" prop="loginName">
              <el-text class="mx-1">{{ form.loginName }}</el-text>
            </el-form-item>
            <el-form-item label="用户昵称：" :label-width="formLabelWidth" prop="nickname">
              <el-input v-model="form.nickname" />
            </el-form-item>
            <el-form-item label="邮箱：" :label-width="formLabelWidth" prop="email">
              <el-input v-model="form.email" />
            </el-form-item>
            <el-form-item label="手机号码：" :label-width="formLabelWidth" prop="phone">
              <el-input v-model="form.phone" />
            </el-form-item>
            <el-form-item label="部门：" :label-width="formLabelWidth" prop="dept">
              <el-tag class="ml-2" type="success">{{ form.dept }}</el-tag>
            </el-form-item>
            <el-form-item label="角色：" :label-width="formLabelWidth" prop="role">
              <template v-for="item in form.role" :key="item.id">
                <el-tag type="warning" style="margin-left: 5px;">{{ item.name }}</el-tag>
              </template>
            </el-form-item>
          </el-form>
          <span class="dialog-footer" style="float: right;">
            <el-button @click="handleCancle()">取消</el-button>
            <el-button type="primary" @click="handleSubmit()">提交</el-button>
          </span>
        </el-main>
      </el-container>
    </div>
  </el-dialog>
</template>

<script>
import { onMounted, reactive, ref, watch } from 'vue'
import { useStore } from 'vuex'
import { modifyUser } from '@/api/user'
import { ElMessage } from 'element-plus'
import { baseURL } from '@/utils/request'
export default {
  name: 'VueProfile',
  props: {
    dialogProfileVisible: {
      type: Boolean,
      default: false
    }
  },
  setup (props, { emit }) {
    const store = useStore()
    const { profile } = store.state.user
    const form = reactive({
      loginName: profile.loginName,
      nickname: profile.nickname,
      email: profile.email,
      phone: profile.phone,
      dept: profile.dept != null ? profile.dept.deptName : '',
      role: profile.roleList ? profile.roleList : []
    })
    const uploadRef = ref(null)
    const userAvatar = ref(null)
    const uploadAction = baseURL + 'user/avatar/upload?Authorization=' + profile.token
    const loadAvatar = () => {
      userAvatar.value = baseURL + 'user/avatar/view?' + Date.now() + '&Authorization=' + profile.token
    }
    onMounted(() => {
      loadAvatar()
    })
    const formRef = ref(null)
    const formRules = reactive({
      nickname: [
        { required: true, message: '请输入用户昵称', trigger: 'blur' }
      ],
      email: [
        { required: true, message: '请输入邮箱', trigger: 'blur' }
      ],
      phone: [
        { required: true, message: '请输入手机号码', trigger: 'blur' }
      ]
    })

    const formLabelWidth = '140px'
    const dialogVisible = ref(props.dialogProfileVisible)
    watch(() => dialogVisible.value, (newValue) => {
      if (!newValue) {
        emit('closeProfileDialog', newValue)
      }
    })
    watch(() => props.dialogProfileVisible, (newValue) => {
      dialogVisible.value = newValue
    })

    const handleCancle = () => {
      emit('closeProfileDialog')
    }

    const handleSubmit = () => {
      formRef.value.validate(valid => {
        if (!valid) {
          return
        }
        profile.nickname = form.nickname
        profile.email = form.email
        profile.phone = form.phone
        modifyUser({
          id: profile.id,
          nickname: form.nickname,
          email: form.email,
          phone: form.phone,
          roleIds: profile.roleList.map(role => role.id)
        }).then(data => {
          if (data.code === 0) {
            dialogVisible.value = false
            store.commit('user/setUser', profile)
            ElMessage.success('修改成功')
          } else {
            ElMessage.error(data.msg)
          }
        })
      })
    }

    const handleAvatarSuccess = (response) => {
      loadAvatar()
      emit('loadAvatar')
    }

    return {
      form,
      formLabelWidth,
      dialogVisible,
      handleCancle,
      handleSubmit,
      formRef,
      formRules,
      userAvatar,
      uploadAction,
      handleAvatarSuccess,
      uploadRef
    }
  }
}
</script>

<style>

</style>
