<template>
  <el-menu
    background-color="#545c64"
    text-color="#fff"
    class="el-menu-demo"
    mode="horizontal"
    :ellipsis="false"
  >
    <el-menu-item>
      <img
        style="width: 50px;height: 50px;"
        src="@/assets/images/logo.png"
        alt="Element logo"
      />
    </el-menu-item>
    <el-menu-item>
      <el-text size="large" type="primary" style="font-size: 20px;">{{ localSystemInfo.name }}</el-text>
    </el-menu-item>
    <div class="flex-grow" />
    <el-menu-item>
      <el-text size="large" style="color: #fff;">欢迎登录：{{ profile.nickname }}</el-text>
    </el-menu-item>
    <el-sub-menu index="2">
      <template #title>系统设置</template>
      <el-menu-item index="2-1" @click="viewProfile">
        <el-icon><UserFilled /></el-icon>
        个人中心
      </el-menu-item>
      <el-menu-item index="2-2" @click="changePwd">
        <el-icon><InfoFilled /></el-icon>
        修改密码
      </el-menu-item>
      <el-menu-item index="2-3" @click="logoutHandler">
        <el-icon><SwitchButton /></el-icon>
        退出
      </el-menu-item>
    </el-sub-menu>
    <el-menu-item>
      <div class="block">
          <el-avatar :size="50" :src="userAvatar" />
        </div>
    </el-menu-item>
  </el-menu>

  <userProfile :dialogProfileVisible="dialogProfileVisible" @closeProfileDialog="closeProfileDialog" @loadAvatar="loadAvatar"></userProfile>

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
import { logout } from '@/api/system'
import { ElMessage, ElMessageBox } from 'element-plus'
import route from '@/router'
import userProfile from '@/components/index/Profile.vue'
import { onMounted, reactive, ref } from 'vue'
import { useStore } from 'vuex'
import { changeUserPwd } from '@/api/user'
import { baseURL } from '@/utils/request'
import { getSystemInfo } from '@/utils/system'

export default {
  name: 'AppHeader',
  components: {
    userProfile: userProfile
  },
  setup () {
    const dialogProfileVisible = ref(false)
    const store = useStore()
    const { profile } = store.state.user
    const dialogChangePwdVisible = ref(false)
    const changePwdFormRef = ref(null)
    const formLabelWidth = '140px'
    const userAvatar = ref('')
    const localSystemInfo = getSystemInfo()
    const changePwdForm = reactive({
      pwd: '',
      confirmPwd: ''
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
    const loadAvatar = () => {
      userAvatar.value = baseURL + 'user/avatar/view?' + Date.now() + '&Authorization=' + profile.token
    }
    onMounted(() => {
      loadAvatar()
    })
    const changePwdFormRules =
    reactive({
      pwd: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { validator: checkPwdConfirmPwdSameRule, trigger: 'blur' }
      ],
      confirmPwd: [
        { required: true, message: '请输入确认密码', trigger: 'blur' },
        { validator: checkPwdConfirmPwdSameRule, trigger: 'blur' }
      ]
    })
    const logoutHandler = () => {
      ElMessageBox.confirm(
        '是否退出',
        '提示',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).then(() => {
        logout().then(data => {
          if (data.code === 0) {
            store.commit('user/setUser', {})
            route.push('/login')
            ElMessage.success('退出成功')
          } else {
            ElMessage.error(data.msg)
          }
        })
      })
    }

    const viewProfile = () => {
      dialogProfileVisible.value = true
    }

    const closeProfileDialog = () => {
      dialogProfileVisible.value = false
    }

    const changePwd = () => {
      dialogChangePwdVisible.value = true
    }

    const handleCancleChangePwd = () => {
      dialogChangePwdVisible.value = false
      changePwdFormRef.value.resetFields()
      changePwdForm.pwd = ''
      changePwdForm.confirmPwd = ''
    }
    const handleSubmitChangePwd = () => {
      changePwdFormRef.value.validate(valid => {
        if (!valid) {
          return
        }

        changeUserPwd(profile.id, changePwdForm.pwd).then(data => {
          if (data.code === 0) {
            handleCancleChangePwd()
            ElMessage.success('密码修改成功')
          } else {
            ElMessage.error(data.msg)
          }
        })
      })
    }

    return {
      userAvatar,
      logoutHandler,
      dialogProfileVisible,
      viewProfile,
      closeProfileDialog,
      profile,
      changePwd,
      dialogChangePwdVisible,
      changePwdFormRef,
      changePwdForm,
      changePwdFormRules,
      handleSubmitChangePwd,
      handleCancleChangePwd,
      formLabelWidth,
      loadAvatar,
      localSystemInfo
    }
  }
}

</script>

<style>
.flex-grow {
  flex-grow: 3;
}
</style>
