<template>
  <el-header class="header">
    <div class="logo">
      <router-link to="/">NoviceNest</router-link>
    </div>
    <el-menu
      :default-active="activeIndex"
      mode="horizontal"
      router
      class="menu"
    >
      <!-- 其他菜单项保持不变 -->
    </el-menu>
    <div class="auth-buttons">
      <template v-if="!userStore.isLoggedIn">
        <el-button type="text" @click="goToLogin">登录</el-button>
        <el-button type="primary" @click="goToRegister">注册</el-button>
      </template>
      <template v-else>
        <el-dropdown>
          <span class="el-dropdown-link">
            {{ userStore.userEmail }}
            <el-icon class="el-icon--right"><arrow-down /></el-icon>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="handleLogout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </template>
    </div>
    <div v-if="isDev" class="debug-info">
      <el-tooltip effect="dark" placement="bottom">
        <template #content>
          <div v-if="userStore.tokenInfo">
            <p>Token 有效</p>
            <p>用户: {{ userStore.tokenInfo.email }}</p>
            <p>过期时间: {{ userStore.tokenInfo.exp.toLocaleString() }}</p>
          </div>
          <div v-else>
            Token 无效或不存在
          </div>
        </template>
        <el-tag :type="userStore.tokenInfo ? 'success' : 'danger'" size="small">
          {{ userStore.tokenInfo ? 'Token 有效' : 'Token 无效' }}
        </el-tag>
      </el-tooltip>
    </div>
  </el-header>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { ArrowDown } from '@element-plus/icons-vue'
import { useUserStore } from '@/store/user'

const router = useRouter()
const userStore = useUserStore()
const activeIndex = ref('/')

// 是否为开发环境
const isDev = computed(() => process.env.NODE_ENV === 'development')

onMounted(() => {
  userStore.updateFromStorage()
})

const goToLogin = () => {
  router.push('/login')
}

const goToRegister = () => {
  router.push('/register')
}

const handleLogout = () => {
  userStore.logout()
  ElMessage.success('已退出登录')
  router.push('/login')
}
</script>

<style scoped>
.header {
  display: flex;
  align-items: center;
  padding: 0 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.logo {
  margin-right: 40px;
}

.logo a {
  color: #409EFF;
  text-decoration: none;
  font-size: 24px;
  font-weight: bold;
}

.menu {
  flex: 1;
  border-bottom: none;
}

.auth-buttons {
  display: flex;
  align-items: center;
  gap: 10px;
}

.el-dropdown-link {
  cursor: pointer;
  display: flex;
  align-items: center;
  color: #409EFF;
}

.el-dropdown-link:hover {
  color: #66b1ff;
}

.debug-info {
  margin-left: 10px;
  display: flex;
  align-items: center;
}
</style> 