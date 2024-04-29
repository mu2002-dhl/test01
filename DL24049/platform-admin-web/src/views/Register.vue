<template>
  <div>
    <div id="bubble" class="bubble">
    <div class="title">在线注册</div>
      <canvas id="bubble-canvas" class="bubble-canvas"></canvas>
    </div>
    <div class="login">
      <div class="login-box">
        <div class="head">
          <img src="~@/assets/images/login-header.png" alt="" />
        </div>
        <div class="form">
          <img class="profile-avatar"  src="~@/assets/images/avatar.png" alt="" />
          <div class="content">
            <el-form size="large"
              :model="form"
              :rules="rules"
              ref="formRef"
              >
              <el-form-item prop="loginName">
                <el-input type="text" v-model="form.loginName" placeholder="请输入账号">
                  <template #prefix>
                    <el-icon size="large"><Discount /></el-icon>
                  </template>
                </el-input>
              </el-form-item>
              <el-form-item prop="nickname">
                <el-input type="text" v-model="form.nickname" placeholder="请输入用户昵称">
                  <template #prefix>
                    <el-icon size="large"><Discount /></el-icon>
                  </template>
                </el-input>
              </el-form-item>
              <el-form-item prop="password">
                <el-input type="password" v-model="form.password" placeholder="请输入密码">
                  <template #prefix>
                    <el-icon size="large"><Discount /></el-icon>
                  </template>
                </el-input>
              </el-form-item>
              <el-form-item :label-width="formLabelWidth" prop="confirmPwd">
                <el-input v-model="form.confirmPwd" type="password" placeholder="请输入确认密码">
                  <template #prefix>
                    <el-icon size="large"><Discount /></el-icon>
                  </template>
                </el-input>
              </el-form-item>
              <el-form-item prop="email">
                <el-input type="text" v-model="form.age" placeholder="请输入年龄">
                  <template #prefix>
                    <el-icon size="large"><Discount /></el-icon>
                  </template>
                </el-input>
              </el-form-item>
              <el-form-item prop="email">
                <el-input type="text" v-model="form.email" placeholder="请输入邮箱">
                  <template #prefix>
                    <el-icon size="large"><Discount /></el-icon>
                  </template>
                </el-input>
              </el-form-item>
              <el-form-item prop="phone">
                <el-input type="text" v-model="form.phone" placeholder="请输入手机号码">
                  <template #prefix>
                    <el-icon size="large"><Discount /></el-icon>
                  </template>
                </el-input>
              </el-form-item>
              <!-- <el-form-item prop="role">
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
              </el-form-item> -->
              <!-- <el-checkbox v-model="form.keep" :label="'保持会话'" size="default"></el-checkbox> -->
              <el-form-item>
                <el-button
                  :loading="false"
                  class="submit-button"
                  round
                  type="primary"
                  size="large"
                  @click="submitForm"
                  @keydown.enter="keyboardEnter"
                >
                  提交
                </el-button>
              </el-form-item>
              <el-form-item v-if="localSystemInfo.loginEnable">
                <el-button
                  class="submit-button"
                  round
                  type="primary"
                  size="large"
                  @click="router.push('/login')"
                >
                  返回登录页面
                </el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { onMounted, reactive, ref } from 'vue'
import { bubbleInit } from '@/utils/bubble'
import { systemInfo } from '@/api/system'
import { registerUser } from '@/api/user'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
import { getSystemInfo } from '@/utils/system'
import { useStore } from 'vuex'
import { allRole } from '@/api/role'

export default {
  name: 'VueLogin',
  setup () {
    const store = useStore()
    const router = useRouter()
    const kaptchaUrl = ref('')
    const localSystemInfo = ref('')

    onMounted(() => {
      bubbleInit()
      loadSystemInfo()
      loadRoleOptions()
    })

    const form = reactive({
      loginName: null,
      nickname: null,
      password: null,
      confirmPwd: null,
      email: null,
      phone: null,
      roleList: []
    })
    const roleOptions = ref(null)
    const formRef = ref()

    const checkPwdConfirmPwdSameRule = (rule, value, callback) => {
      if (form.password !== form.confirmPwd) {
        callback(new Error('密码与确认密码不同'))
      } else if (form.password.length < 6 || form.password.length > 24) {
        callback(new Error('密码密码长度范围：6-24位'))
      } else if (form.confirmPwd.length < 6 || form.confirmPwd.length > 24) {
        callback(new Error('密码密码长度范围：6-24位'))
      } else {
        callback()
      }
    }
    const rules = reactive({
      loginName: [
        { required: true, message: '请输入账号', trigger: 'blur' }
      ],
      nickname: [
        { required: true, message: '请输入用户昵称', trigger: 'blur' }
      ],
      password: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { validator: checkPwdConfirmPwdSameRule, trigger: 'blur' }
      ],
      confirmPwd: [
        { required: true, message: '请输入确认密码', trigger: 'blur' },
        { validator: checkPwdConfirmPwdSameRule, trigger: 'blur' }
      ]
    })

    const submitForm = () => {
      formRef.value.validate(valid => {
        if (valid) {
          form.roleIds = [2]// form.roleList.map(data => data.id)
          const promise = registerUser(form)
          promise.then(data => {
            if (data.code === 0) {
              ElMessage.success('注册成功')
              router.push('/login')
            } else {
              ElMessage.error(data.msg)
              formRef.value.resetFields()
            }
          })
        }
      })
    }

    const loadSystemInfo = () => {
      systemInfo((data) => {
        store.commit('system/setInfo', data)
        localSystemInfo.value = getSystemInfo()
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

    return {
      form, kaptchaUrl, rules, formRef, submitForm, localSystemInfo, router, roleOptions
    }
  },
  components: {
  }
}
</script>

<style scoped lang="less">
.title{
  font-size: 30px;
  text-align: center;
  padding-top: 30px;
  color: #F2F6FC;
}
.bubble {
  overflow: hidden;
  background: url(~@/assets/images/bg.jpg) repeat;
}
.form-item-icon {
  height: auto;
}
.login {
  position: absolute;
  top: 0;
  display: flex;
  width: 100vw;
  height: 100vh;
  align-items: center;
  justify-content: center;
  .login-box {
      overflow: hidden;
      width: 430px;
      padding: 0;
      background: #fff;
      margin-bottom: -10px;
  }
  .head {
      background: #ccccff;
      img {
          display: block;
          width: 430px;
          height: 120px;
          margin: 0 auto;
          user-select: none;
      }
  }
  .form {
      position: relative;
      .profile-avatar {
          display: block;
          position: absolute;
          height: 100px;
          width: 100px;
          border-radius: 50%;
          border: 4px solid var(--ba-bg-color-overlay);
          top: -50px;
          right: calc(50% - 50px);
          z-index: 2;
          user-select: none;
      }
      .content {
          padding: 70px 40px 40px 40px;
      }
      .submit-button {
          width: 100%;
          letter-spacing: 2px;
          font-weight: 300;
          --el-button-bg-color: var(--el-color-primary);
      }
  }
}

</style>
