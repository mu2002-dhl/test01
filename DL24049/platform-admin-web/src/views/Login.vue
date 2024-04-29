<template>
  <div>
    <div id="bubble" class="bubble">
    <div class="title">{{ localSystemInfo.name }}</div>
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
                <el-input
                  type="text"
                  v-model="form.loginName"
                  placeholder="请输入账号"
                >
                  <template #prefix>
                    <el-icon size="large"><Avatar/></el-icon>
                  </template>
                </el-input>
              </el-form-item>
              <el-form-item prop="password">
                <el-input
                  v-model="form.password"
                  type="password"
                  placeholder="请输入密码"
                  show-password
                >
                  <template #prefix>
                    <el-icon size="large"><BellFilled /></el-icon>
                  </template>
                </el-input>
              </el-form-item>
              <el-form-item prop="captcha">
                <img style="width: 100px;height: 37px;" :src='kaptchaUrl' @click="mounteKaptchaUrl"/>
                <el-input
                  style="width: 250px;padding-left: 10px;"
                  v-model="form.captcha"
                  type="text"
                  placeholder="请输入验证码"
                >
                  <template #prefix>
                    <el-icon size="large"><Comment /></el-icon>
                  </template>
                </el-input>
              </el-form-item>
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
                  登录
                </el-button>
              </el-form-item>
              <el-form-item>
                <el-button
                  class="submit-button"
                  round
                  type="primary"
                  size="large"
                  @click="router.push('/register')"
                >
                  注册
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
import { onMounted, onUnmounted, reactive, ref } from 'vue'
import { bubbleInit } from '@/utils/bubble'
import { baseURL } from '@/utils/request'
import { login, systemInfo } from '@/api/system'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { initBackendRouter } from '@/config/config'
import { getSystemInfo } from '@/utils/system'

export default {
  name: 'VueLogin',
  setup () {
    const router = useRouter()
    const store = useStore()
    const kaptchaUrl = ref('')
    const localSystemInfo = ref('')
    const mounteKaptchaUrl = () => {
      kaptchaUrl.value = baseURL + 'notify/captcha?' + Date.now()
    }

    const loadSystemInfo = () => {
      systemInfo((data) => {
        store.commit('system/setInfo', data)
        localSystemInfo.value = getSystemInfo()
      })
    }

    onMounted(() => {
      bubbleInit()
      mounteKaptchaUrl()
      window.addEventListener('keydown', keyboardEnter)
      loadSystemInfo()
    })

    onUnmounted(() => {
      // 销毁事件
      window.removeEventListener('keydown', keyboardEnter, false)
    })

    const form = reactive({
      loginName: null,
      password: null,
      captcha: ''
    })

    const formRef = ref()

    const rules = reactive({
      loginName: [
        { required: true, message: '请输入账号', trigger: 'blur' }
      ],
      password: [
        { required: true, message: '请输入密码', trigger: 'blur' }
      ],
      captcha: [
        { required: true, message: '请输入验证码', trigger: 'blur' }
      ]
    })

    const submitForm = () => {
      formRef.value.validate(valid => {
        if (valid) {
          const promise = login(form.loginName, form.password, form.captcha)
          promise.then(data => {
            if (data.code === 0) {
              data = data.data
              store.commit('user/setUser', data)
              initBackendRouter()
              ElMessage.success('登录成功')
              router.push('/')
            } else {
              ElMessage.error(data.msg)
              mounteKaptchaUrl()
              formRef.value.resetFields()
            }
          })
        }
      })
    }

    const keyboardEnter = (e) => {
      if (e.keyCode === 13 || e.keyCode === 100) {
        submitForm()
      }
    }

    return {
      form, kaptchaUrl, mounteKaptchaUrl, rules, formRef, submitForm, keyboardEnter, localSystemInfo, router
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
