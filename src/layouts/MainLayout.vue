<template>
  <div class="layout">
    <header class="header">
      <div class="logo">
        <img src="@/assets/logo.png" alt="Logo" />
        <span>NoviceNest</span>
      </div>
      
      <nav class="nav">
        <div class="nav-item" 
          v-for="(item, index) in menuItems" 
          :key="index"
          @mouseenter="handleMouseEnter(index)"
          @mouseleave="handleMouseLeave"
          @click="handleMenuItemClick(item)"
        >
          <span class="nav-link">{{ item.label }}</span>
          
          <!-- 子菜单 -->
          <div class="submenu" 
               :class="{ 'submenu-active': activeIndex === index }"
               v-show="activeIndex === index || isTransitioning"
          >
            <transition name="fade">
              <div class="submenu-content" v-show="activeIndex === index">
                <div class="submenu-grid">
                  <div v-for="(subItem, subIndex) in item.children" 
                       :key="subIndex"
                       class="submenu-item"
                       @click="handleSubMenuClick(subItem.path)"
                  >
                    <el-icon class="submenu-icon" v-if="subItem.icon">
                      <component :is="subItem.icon" />
                    </el-icon>
                    <div class="submenu-text">
                      <h3>{{ subItem.label }}</h3>
                      <p>{{ subItem.description }}</p>
                    </div>
                  </div>
                </div>
              </div>
            </transition>
          </div>
        </div>
      </nav>

      <div class="auth-actions">
        <div v-if="!isLoggedIn" class="auth-buttons">
          <el-button 
            type="primary" 
            class="register-btn"
            @click="router.push('/register')"
          >
            注册账号
          </el-button>
          <el-button 
            class="login-btn"
            @click="router.push('/login')"
          >
            登录
          </el-button>
        </div>
        <div v-else class="user-info">
          <span class="welcome-text">欢迎，{{ userEmail }}</span>
          <el-button 
            class="logout-btn"
            @click="handleLogout"
          >
            退出
          </el-button>
        </div>
      </div>
    </header>

    <main class="main">
      <router-view></router-view>
    </main>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/store/user'

const router = useRouter()
const userStore = useUserStore()
const isTransitioning = ref(false)
const activeIndex = ref(null)
let closeTimer = null
let transitionTimer = null

// 使用计算属性来监听用户状态
const isLoggedIn = computed(() => userStore.isLoggedIn())
const userEmail = computed(() => userStore.state.user?.email)

const menuItems = [
  {
    label: '首页',
    path: '/',
    children: []
  },
  {
    label: '日语学习',
    children: [
      {
        label: '五十音图',
        description: '日语入门基础，交互式学习体验',
        path: '/japanese/hiragana',
        icon: 'Document'
      },
      {
        label: 'N5-N1课程',
        description: '系统化的日语能力考试备考课程',
        path: '/japanese/courses',
        icon: 'Reading'
      },
      {
        label: '实用会话',
        description: '生活场景日语对话练习',
        path: '/japanese/conversation',
        icon: 'ChatDotRound'
      },
      {
        label: '语法教程',
        description: '详细的日语语法讲解',
        path: '/japanese/grammar',
        icon: 'Edit'
      }
    ]
  },
  {
    label: '编程教程',
    children: [
      {
        label: '前端开发',
        description: 'HTML, CSS, JavaScript基础到进阶',
        path: '/programming/frontend',
        icon: 'Monitor'
      },
      {
        label: '后端开发',
        description: 'Python, Java, Node.js服务器开发',
        path: '/programming/backend',
        icon: 'Connection'
      },
      {
        label: '移动开发',
        description: 'iOS, Android应用开发教程',
        path: '/programming/mobile',
        icon: 'Iphone'
      },
      {
        label: '算法训练',
        description: '编程算法与数据结构',
        path: '/programming/algorithm',
        icon: 'Share'
      }
    ]
  },
  {
    label: '关于',
    children: [
      {
        label: '关于我们',
        description: '了解 NoviceNest 的故事',
        path: '/about',
        icon: 'InfoFilled'
      },
      {
        label: '使用条款',
        description: '使用条款与服务协议',
        path: '/terms',
        icon: 'Document'
      },
      {
        label: '隐私政策',
        description: '了解我们如何保护您的隐私',
        path: '/privacy',
        icon: 'Lock'
      }
    ]
  }
]

const handleMouseEnter = (index) => {
  if (closeTimer) {
    clearTimeout(closeTimer)
    clearTimeout(transitionTimer)
  }
  isTransitioning.value = true
  activeIndex.value = index
}

const handleMouseLeave = () => {
  closeTimer = setTimeout(() => {
    activeIndex.value = null
    transitionTimer = setTimeout(() => {
      isTransitioning.value = false
    }, 300) // 与过渡动画时间匹配
  }, 150)
}

const handleSubMenuClick = (path) => {
  router.push(path)
  activeIndex.value = null
}

const handleMenuItemClick = (item) => {
  if (!item.children || item.children.length === 0) {
    router.push(item.path)
    activeIndex.value = null
  }
}

const handleLogout = () => {
  userStore.logout()
  router.push('/')
}
</script>

<style scoped>
.layout {
  min-height: 100vh;
  background-color: #fff;
}

.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 16px 40px;
  background: white;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;
}

.logo {
  display: flex;
  align-items: center;
  gap: 10px;
}

.logo img {
  height: 24px;
}

.logo span {
  font-size: 20px;
  font-weight: 500;
  background: linear-gradient(120deg, #007AFF, #00B4DB);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.nav {
  display: flex;
  gap: 40px;
  height: 44px;
  align-items: center;
}

.nav-item {
  position: relative;
  height: 100%;
  display: flex;
  align-items: center;
  cursor: pointer;
}

.nav-link {
  color: #1d1d1f;
  font-size: 14px;
  transition: color 0.3s ease;
}

.nav-item:hover .nav-link {
  color: #007AFF;
}

.submenu {
  position: fixed;
  top: 44px;
  left: 0;
  width: 100%;
  background: transparent;
  z-index: 999;
  opacity: 0;
  visibility: hidden;
  transition: opacity 0.3s ease, visibility 0.3s ease;
}

.submenu-active {
  opacity: 1;
  visibility: visible;
}

.submenu-content {
  background: rgba(255, 255, 255, 0.98);
  backdrop-filter: saturate(180%) blur(20px);
  padding: 40px 0;
  margin-top: 1px;
  transform: translateY(-10px);
  opacity: 0;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

.fade-enter-active,
.fade-leave-active {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
  transform: translateY(-10px);
}

.fade-enter-to,
.fade-leave-from {
  opacity: 1;
  transform: translateY(0);
}

.submenu-active .submenu-content {
  transform: translateY(0);
  opacity: 1;
}

.submenu-grid {
  max-width: 1000px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
  gap: 30px;
  padding: 0 40px;
}

.submenu-item {
  display: flex;
  align-items: flex-start;
  gap: 16px;
  padding: 16px;
  border-radius: 12px;
  transition: all 0.3s ease;
}

.submenu-item:hover {
  background: rgba(0, 0, 0, 0.03);
}

.submenu-icon {
  font-size: 24px;
  color: #007AFF;
}

.submenu-text h3 {
  font-size: 16px;
  font-weight: 500;
  color: #1d1d1f;
  margin: 0 0 4px 0;
}

.submenu-text p {
  font-size: 14px;
  color: #86868b;
  margin: 0;
  line-height: 1.4;
}

.menu-overlay {
  position: fixed;
  top: 44px;
  left: 0;
  width: 100%;
  height: calc(100vh - 44px);
  background: rgba(0, 0, 0, 0);
  z-index: 999;
  backdrop-filter: blur(0);
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

.menu-overlay[v-show="true"] {
  background: rgba(0, 0, 0, 0.3);
  backdrop-filter: blur(5px);
}

.main-content {
  padding-top: 44px;
  min-height: calc(100vh - 44px - 200px);
}

.footer {
  background-color: #f5f5f7;
  padding: 40px;
  height: auto;
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 30px;
}

.footer-section h3 {
  font-size: 20px;
  margin-bottom: 10px;
  color: #1d1d1f;
}

.footer-section p {
  color: #86868b;
}

.footer-links {
  display: flex;
  gap: 30px;
}

.footer-links a {
  color: #424245;
  text-decoration: none;
  font-size: 14px;
  transition: color 0.3s ease;
}

.footer-links a:hover {
  color: #007AFF;
}

.copyright {
  text-align: center;
  color: #86868b;
  font-size: 12px;
  border-top: 1px solid #d2d2d7;
  padding-top: 20px;
}

.auth-actions {
  position: relative;
  display: flex;
  gap: 12px;
  z-index: 1000;
}

.auth-buttons,
.user-info {
  display: flex;
  gap: 12px;
  align-items: center;
}

.welcome-text {
  color: #1d1d1f;
  font-size: 14px;
  white-space: nowrap;
  margin-right: 12px;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 12px;
}

.logout-btn {
  color: #1d1d1f;
  border-color: #e4e4e4;
}

.logout-btn:hover {
  color: #FF2D55;
  border-color: #FF2D55;
  background: white;
}

.register-btn {
  background: #FF2D55;
  border-color: #FF2D55;
}

.register-btn:hover {
  background: #FF1F4B;
  border-color: #FF1F4B;
}

.login-btn {
  color: #1d1d1f;
  border-color: #e4e4e4;
}

.login-btn:hover {
  color: #FF2D55;
  border-color: #FF2D55;
  background: white;
}

/* Element Plus 样式覆盖 */
:deep(.el-button--primary) {
  background: #007AFF;
  border-color: #007AFF;
  font-size: 14px;
}

:deep(.el-menu-item) {
  font-size: 14px;
  height: 44px;
  line-height: 44px;
}

:deep(.el-menu--horizontal > .el-menu-item.is-active) {
  border-bottom: 2px solid #007AFF;
  color: #007AFF;
}

:deep(.el-menu--horizontal > .el-menu-item) {
  border-bottom: none;
}

@media (max-width: 768px) {
  .header {
    padding: 0 20px;
  }
  
  .nav-menu {
    display: none;
  }
}
</style> 