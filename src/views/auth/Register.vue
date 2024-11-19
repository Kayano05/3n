<template>
  <div class="register-container">
    <div class="register-content">
      <!-- Logo 部分 -->
      <div class="brand">
        <img src="@/assets/logo.png" alt="Logo" class="logo" />
        <h1>NoviceNest</h1>
      </div>

      <!-- 注册表单 -->
      <div class="register-card">
        <h2>创建账号</h2>
        <p class="subtitle">开启你的学习之旅</p>

        <el-form 
          ref="formRef"
          :model="form"
          :rules="rules"
          class="register-form"
        >
          <!-- 邮箱输入 -->
          <el-form-item prop="email">
            <el-input
              v-model="form.email"
              placeholder="邮箱地址"
              :class="{ 'is-verifying': isVerifying }"
              :disabled="isVerifying"
            >
              <template #prefix>
                <el-icon><Message /></el-icon>
              </template>
              <template #append>
                <el-button
                  class="verify-btn"
                  :disabled="!canSendCode || isVerifying"
                  @click="sendVerificationCode"
                  :loading="isSending"
                >
                  {{ sendButtonText }}
                </el-button>
              </template>
            </el-input>
          </el-form-item>

          <transition name="slide-fade">
            <div v-if="isVerifying" class="verification-section">
              <!-- 验证码输入 -->
              <el-form-item prop="verificationCode">
                <el-input
                  v-model="form.verificationCode"
                  placeholder="验证码"
                  maxlength="6"
                >
                  <template #prefix>
                    <el-icon><Key /></el-icon>
                  </template>
                </el-input>
              </el-form-item>

              <!-- 密码输入 -->
              <el-form-item prop="password">
                <el-input
                  v-model="form.password"
                  type="password"
                  placeholder="设置密码"
                  show-password
                >
                  <template #prefix>
                    <el-icon><Lock /></el-icon>
                  </template>
                </el-input>
              </el-form-item>

              <!-- 确认密码 -->
              <el-form-item prop="confirmPassword">
                <el-input
                  v-model="form.confirmPassword"
                  type="password"
                  placeholder="确认密码"
                  show-password
                >
                  <template #prefix>
                    <el-icon><Lock /></el-icon>
                  </template>
                </el-input>
              </el-form-item>
            </div>
          </transition>

          <!-- 注册按钮 -->
          <el-form-item>
            <el-button 
              type="primary" 
              class="submit-btn"
              :loading="isSubmitting"
              @click="submitForm(formRef)"
              :disabled="!isVerifying"
            >
              创建账号
            </el-button>
          </el-form-item>
        </el-form>

        <!-- 登录链接 -->
        <div class="login-link">
          已有账号？<router-link to="/login">登录</router-link>
        </div>
      </div>

      <!-- 底部文案 -->
      <div class="terms">
        注册即表示同意 
        <router-link to="/terms">服务条款</router-link> 
        和 
        <router-link to="/privacy">隐私政策</router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
import { Message, Lock, Key } from '@element-plus/icons-vue'
import request from '@/utils/request'

const router = useRouter()
const formRef = ref(null)

// 表单数据
const form = ref({
  email: '',
  verificationCode: '',
  password: '',
  confirmPassword: ''
})

// 验证规则
const rules = {
  email: [
    { required: true, message: '请输入邮箱地址', trigger: 'blur' },
    { type: 'email', message: '请输入正确的邮箱格式', trigger: 'blur' },
    { pattern: /^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$/, message: '请输入正确的邮箱格式', trigger: 'blur' }
  ],
  verificationCode: [
    { required: true, message: '请输入验证码', trigger: 'blur' },
    { len: 6, message: '验证码长度应为6位', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请设置密码', trigger: 'blur' },
    { min: 6, message: '密码长度不能小于6位', trigger: 'blur' }
  ],
  confirmPassword: [
    { required: true, message: '请确认密码', trigger: 'blur' },
    {
      validator: (rule, value, callback) => {
        if (value !== form.value.password) {
          callback(new Error('两次输入的密码不一致'))
        } else {
          callback()
        }
      },
      trigger: 'blur'
    }
  ]
}

// 状态控制
const isSending = ref(false)
const isVerifying = ref(false)
const isSubmitting = ref(false)
const countdown = ref(0)

// 是否可以发送验证码
const canSendCode = computed(() => {
  return form.value.email && !countdown.value
})

// 发送按钮文字
const sendButtonText = computed(() => {
  if (countdown.value) {
    return `${countdown.value}秒后重试`
  }
  return isSending.value ? '发送中...' : '获取验证码'
})

// 发送验证码
const sendVerificationCode = async () => {
  try {
    isSending.value = true
    
    const response = await request.post('/auth/send-code', {
      email: form.value.email
    })

    if (response.success) {
      ElMessage.success('验证码已发送，请查收邮件')
      isVerifying.value = true
      startCountdown()
    }
  } catch (error) {
    console.error('发送验证码失败:', error)
    ElMessage.error(error.response?.data?.message || '发送验证码失败，请稍后重试')
  } finally {
    isSending.value = false
  }
}

// 开始倒计时
const startCountdown = () => {
  countdown.value = 60
  const timer = setInterval(() => {
    countdown.value--
    if (countdown.value <= 0) {
      clearInterval(timer)
    }
  }, 1000)
}

// 提交注册
const submitForm = async (formEl) => {
  if (!formEl) return

  await formEl.validate(async (valid) => {
    if (valid) {
      try {
        isSubmitting.value = true
        
        const response = await request.post('/auth/register', {
          email: form.value.email,
          verificationCode: form.value.verificationCode,
          password: form.value.password
        })

        if (response.success) {
          ElMessage.success('注册成功！')
          router.push('/login')
        }
      } catch (error) {
        console.error('注册失败:', error)
        ElMessage.error(error.response?.data?.message || '注册失败，请稍后重试')
      } finally {
        isSubmitting.value = false
      }
    }
  })
}
</script>

<style scoped>
.register-container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #f5f5f7 0%, #fff 100%);
  padding: 40px 20px;
}

.register-content {
  width: 100%;
  max-width: 420px;
  text-align: center;
}

.brand {
  margin-bottom: 40px;
}

.brand img.logo {
  width: 64px;
  height: 64px;
  margin-bottom: 16px;
}

.brand h1 {
  font-size: 32px;
  font-weight: 600;
  background: linear-gradient(120deg, #007AFF, #00B4DB);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin: 0;
}

.register-card {
  background: rgba(255, 255, 255, 0.8);
  backdrop-filter: blur(20px);
  border-radius: 24px;
  padding: 40px;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.08);
  margin-bottom: 24px;
}

.register-card h2 {
  font-size: 24px;
  font-weight: 600;
  color: #1d1d1f;
  margin: 0 0 8px;
}

.subtitle {
  color: #6e6e73;
  font-size: 16px;
  margin-bottom: 32px;
}

.register-form {
  text-align: left;
}

:deep(.el-input__wrapper) {
  border-radius: 12px;
  padding: 12px;
  box-shadow: none;
  border: 1px solid #e4e4e4;
  background: white;
}

:deep(.el-input__wrapper.is-focus) {
  border-color: #007AFF;
  box-shadow: 0 0 0 1px #007AFF;
}

:deep(.el-input__prefix) {
  margin-right: 12px;
  color: #86868b;
}

.verify-btn {
  height: 100%;
  border: none;
  background: transparent;
  color: #007AFF;
  font-weight: 500;
  padding: 0 16px;
}

.verify-btn:not(:disabled):hover {
  color: #0056b3;
}

.submit-btn {
  width: 100%;
  height: 48px;
  font-size: 16px;
  font-weight: 500;
  border-radius: 12px;
  background: #007AFF;
  border: none;
  margin-top: 16px;
}

.submit-btn:not(:disabled):hover {
  background: #0056b3;
}

.login-link {
  margin-top: 24px;
  color: #6e6e73;
}

.login-link a,
.terms a {
  color: #007AFF;
  text-decoration: none;
  font-weight: 500;
}

.login-link a:hover,
.terms a:hover {
  text-decoration: underline;
}

.terms {
  color: #86868b;
  font-size: 14px;
}

.verification-section {
  margin-top: 24px;
}

/* 过渡动画 */
.slide-fade-enter-active,
.slide-fade-leave-active {
  transition: all 0.3s ease;
}

.slide-fade-enter-from,
.slide-fade-leave-to {
  transform: translateY(-20px);
  opacity: 0;
}

@media (max-width: 480px) {
  .register-card {
    padding: 32px 24px;
  }
}
</style> 