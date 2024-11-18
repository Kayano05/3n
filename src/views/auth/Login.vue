<template>
  <div class="login-container">
    <div class="login-content">
      <div class="login-header">
        <img src="@/assets/logo.png" alt="Logo" class="logo" />
        <h1>登录 NoviceNest</h1>
        <p class="subtitle">使用您的 NoviceNest ID 继续操作</p>
      </div>
      
      <el-form :model="form" :rules="rules" ref="loginForm" class="login-form">
        <el-form-item prop="email">
          <el-input 
            v-model="form.email" 
            placeholder="NoviceNest ID"
            size="large"
            :prefix-icon="Message"
          ></el-input>
        </el-form-item>
        
        <el-form-item prop="password">
          <el-input 
            v-model="form.password" 
            type="password" 
            placeholder="密码"
            size="large"
            :prefix-icon="Lock"
            show-password
          ></el-input>
        </el-form-item>

        <div class="forgot-password">
          <a href="#" class="link">忘记 NoviceNest ID 或密码？</a>
        </div>

        <div class="form-footer">
          <el-button 
            type="primary" 
            size="large"
            :loading="loading"
            @click="handleLogin"
          >
            继续
          </el-button>
          
          <div class="divider">
            <span>没有 NoviceNest ID？</span>
          </div>
          
          <router-link to="/register" class="create-account-link">
            创建您的 NoviceNest ID
            <el-icon class="arrow-icon"><ArrowRight /></el-icon>
          </router-link>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, nextTick } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { Message, Lock, ArrowRight } from '@element-plus/icons-vue'
import request from '@/utils/request'
import { useUserStore } from '@/store/user'

const router = useRouter()
const loginForm = ref(null)
const loading = ref(false)
const userStore = useUserStore()

const form = reactive({
  email: '',
  password: ''
})

const rules = {
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { type: 'email', message: '请输入正确的邮箱格式', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, message: '密码长度不能小于6位', trigger: 'blur' }
  ]
}

const handleLogin = async () => {
  if (!loginForm.value) return
  
  try {
    await loginForm.value.validate()
    loading.value = true
    
    const response = await request.post('/auth/login', {
      email: form.email,
      password: form.password
    })
    
    if (response.success) {
      if (response.user && response.token) {
        await userStore.setToken(response.token)
        await userStore.setUser(response.user)
        
        await nextTick(() => {
          ElMessage.success('登录成功')
          const redirect = router.currentRoute.value.query.redirect || '/'
          router.push(redirect)
        })
      } else {
        ElMessage.error('登录响应数据不完整')
      }
    } else {
      ElMessage.error(response.message || '登录失败')
    }
  } catch (error) {
    console.error('Login error:', error)
    ElMessage.error(error.response?.data?.message || '登录失败')
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.login-container {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #fff;
  padding: 20px;
}

.login-content {
  width: 100%;
  max-width: 440px;
  padding: 48px 20px;
}

.login-header {
  text-align: center;
  margin-bottom: 48px;
}

.logo {
  width: 40px;
  height: 40px;
  margin-bottom: 16px;
}

h1 {
  font-size: 28px;
  font-weight: 500;
  color: #1d1d1f;
  margin-bottom: 8px;
}

.subtitle {
  font-size: 17px;
  color: #86868b;
  margin-bottom: 32px;
}

.login-form {
  margin-top: 32px;
}

:deep(.el-input) {
  --el-input-height: 52px;
  --el-input-border-radius: 12px;
  --el-input-border-color: #d2d2d7;
  --el-input-hover-border-color: #86868b;
  --el-input-focus-border-color: #0066cc;
}

:deep(.el-input__wrapper) {
  background-color: rgba(0, 0, 0, 0.02);
  box-shadow: none;
  transition: all 0.3s ease;
  padding: 0 16px;
}

:deep(.el-input__wrapper:hover) {
  background-color: rgba(0, 0, 0, 0.04);
}

:deep(.el-input__wrapper.is-focus) {
  background-color: #fff;
  box-shadow: 0 0 0 4px rgba(0, 102, 204, 0.1);
}

:deep(.el-input__inner) {
  font-size: 17px;
  color: #1d1d1f;
  height: 52px;
}

:deep(.el-input__prefix-inner) {
  font-size: 20px;
  color: #86868b;
}

.forgot-password {
  text-align: center;
  margin: 16px 0 32px;
}

.form-footer {
  margin-top: 24px;
}

.el-button {
  width: 100%;
  height: 52px;
  font-size: 17px;
  border-radius: 12px;
  margin-bottom: 24px;
  font-weight: 400;
}

:deep(.el-button--primary) {
  background: #0066cc;
  border-color: #0066cc;
}

:deep(.el-button--primary:hover) {
  background: #0077ed;
  border-color: #0077ed;
}

.divider {
  text-align: center;
  margin: 32px 0;
  color: #86868b;
  font-size: 14px;
  position: relative;
}

.divider::before,
.divider::after {
  content: '';
  position: absolute;
  top: 50%;
  width: calc(50% - 100px);
  height: 1px;
  background-color: #d2d2d7;
}

.divider::before {
  left: 0;
}

.divider::after {
  right: 0;
}

.create-account-link {
  display: flex;
  align-items: center;
  justify-content: center;
  color: #0066cc;
  text-decoration: none;
  font-size: 17px;
  transition: color 0.3s ease;
  gap: 8px;
}

.create-account-link:hover {
  color: #0077ed;
}

.arrow-icon {
  font-size: 16px;
  transition: transform 0.3s ease;
}

.create-account-link:hover .arrow-icon {
  transform: translateX(4px);
}

.link {
  color: #0066cc;
  text-decoration: none;
  font-size: 14px;
  transition: color 0.3s ease;
}

.link:hover {
  color: #0077ed;
  text-decoration: underline;
}

@media (max-width: 768px) {
  .login-content {
    padding: 32px 16px;
  }
  
  h1 {
    font-size: 24px;
  }
  
  .subtitle {
    font-size: 15px;
  }
}
</style> 