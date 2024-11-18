<template>
  <div class="python-home">
    <div class="course-header">
      <h1>Python 编程教程</h1>
      <p class="subtitle">简单易学的全能型编程语言</p>
    </div>

    <div class="course-navigation">
      <el-menu
        mode="horizontal"
        :router="true"
        class="course-menu"
      >
        <el-menu-item index="/programming/python/basics">
          基础语法
        </el-menu-item>
        <el-menu-item index="/programming/python/advanced">
          高级特性
        </el-menu-item>
        <el-menu-item index="/programming/python/web">
          Web开发
        </el-menu-item>
      </el-menu>
    </div>

    <div class="course-content">
      <router-view v-slot="{ Component }">
        <transition name="fade" mode="out-in">
          <component :is="Component" />
        </transition>
      </router-view>
    </div>

    <div class="course-cards" v-if="$route.path === '/programming/python'">
      <el-row :gutter="20">
        <el-col :span="8" v-for="course in courses" :key="course.title">
          <el-card class="course-card" :body-style="{ padding: '0px' }">
            <div class="card-content">
              <el-icon class="card-icon" :size="40">
                <component :is="course.icon" />
              </el-icon>
              <h3>{{ course.title }}</h3>
              <p>{{ course.description }}</p>
              <el-button 
                type="primary" 
                @click="$router.push(course.path)"
                class="start-button"
              >
                开始学习
              </el-button>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const courses = ref([
  {
    title: '基础语法',
    description: '学习 Python 的基本语法、数据类型、控制流程等基础知识',
    path: '/programming/python/basics',
    icon: 'Edit'
  },
  {
    title: '高级特性',
    description: '掌握装饰器、生成器、异步编程等高级特性',
    path: '/programming/python/advanced',
    icon: 'Share'
  },
  {
    title: 'Web开发',
    description: '使用 Django 和 Flask 进行 Web 应用开发',
    path: '/programming/python/web',
    icon: 'Monitor'
  }
])
</script>

<style scoped>
/* 复用 JavaHome.vue 的样式 */
.python-home {
  padding: 40px;
  max-width: 1200px;
  margin: 0 auto;
}

.course-header {
  text-align: center;
  margin-bottom: 40px;
}

.course-header h1 {
  font-size: 48px;
  font-weight: 600;
  background: linear-gradient(120deg, #007AFF, #00B4DB);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin-bottom: 16px;
}

.subtitle {
  font-size: 20px;
  color: #666;
}

.course-navigation {
  margin-bottom: 40px;
}

.course-menu {
  display: flex;
  justify-content: center;
  border-bottom: 1px solid #eee;
}

.course-cards {
  margin-top: 40px;
}

.course-card {
  height: 100%;
  transition: transform 0.3s ease;
  border: none;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.course-card:hover {
  transform: translateY(-5px);
}

.card-content {
  padding: 30px;
  text-align: center;
}

.card-icon {
  color: #007AFF;
  margin-bottom: 20px;
}

.card-content h3 {
  font-size: 20px;
  margin-bottom: 12px;
  color: #333;
}

.card-content p {
  color: #666;
  margin-bottom: 24px;
  line-height: 1.6;
}

.start-button {
  width: 100%;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

:deep(.el-menu--horizontal > .el-menu-item) {
  font-size: 16px;
  height: 56px;
  line-height: 56px;
}

:deep(.el-menu--horizontal > .el-menu-item.is-active) {
  border-bottom: 2px solid #007AFF;
  color: #007AFF;
}
</style> 