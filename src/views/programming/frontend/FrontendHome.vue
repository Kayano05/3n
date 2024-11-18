<template>
  <div class="frontend-home">
    <div class="page-header">
      <h1>前端开发</h1>
      <p class="subtitle">构建现代化的用户界面</p>
    </div>

    <div class="language-grid">
      <el-row :gutter="30">
        <el-col :span="8" v-for="tech in technologies" :key="tech.name">
          <el-card 
            class="language-card" 
            :body-style="{ padding: '0px' }"
            @click="navigateToTech(tech.path)"
          >
            <div class="card-content">
              <img :src="tech.icon" :alt="tech.name" class="tech-icon">
              <h2>{{ tech.name }}</h2>
              <p class="description">{{ tech.description }}</p>
              <div class="features">
                <div class="feature" v-for="feature in tech.features" :key="feature">
                  <el-icon><Check /></el-icon>
                  <span>{{ feature }}</span>
                </div>
              </div>
              <el-button type="primary" class="start-button" round>
                开始学习
                <el-icon class="el-icon--right"><ArrowRight /></el-icon>
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
import { useRouter } from 'vue-router'

const router = useRouter()

const technologies = ref([
  {
    name: 'HTML & CSS',
    path: '/programming/frontend/html-css',
    icon: '/icons/html-css.svg',
    description: '网页开发的基础，构建现代化的用户界面',
    features: [
      'HTML5 语义化标签',
      'CSS3 动画与过渡',
      'Flexbox 与 Grid 布局'
    ]
  },
  {
    name: 'JavaScript',
    path: '/programming/frontend/javascript',
    icon: '/icons/javascript.svg',
    description: '为网页添加交互性，实现动态内容',
    features: [
      'ES6+ 新特性',
      'DOM 操作与事件',
      '异步编程'
    ]
  },
  {
    name: 'Vue.js',
    path: '/programming/frontend/vue',
    icon: '/icons/vue.svg',
    description: '渐进式 JavaScript 框架',
    features: [
      '组件化开发',
      '响应式数据',
      '状态管理'
    ]
  }
])

const navigateToTech = (path) => {
  router.push(path)
}
</script>

<style scoped>
.frontend-home {
  padding: 40px;
  max-width: 1200px;
  margin: 0 auto;
}

.page-header {
  text-align: center;
  margin-bottom: 60px;
}

.page-header h1 {
  font-size: 48px;
  font-weight: 600;
  background: linear-gradient(120deg, #007AFF, #00B4DB);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin-bottom: 16px;
}

.subtitle {
  font-size: 20px;
  color: #6e6e73;
  font-weight: 500;
}

.language-grid {
  padding: 20px;
}

.language-card {
  height: 100%;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  border: none;
  border-radius: 20px;
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.04);
  cursor: pointer;
  background: #fff;
}

.language-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.08);
}

.card-content {
  padding: 40px;
  text-align: center;
}

.tech-icon {
  width: 80px;
  height: 80px;
  margin-bottom: 20px;
}

.card-content h2 {
  font-size: 24px;
  margin-bottom: 12px;
  color: #1d1d1f;
  font-weight: 600;
}

.description {
  color: #6e6e73;
  margin-bottom: 24px;
  line-height: 1.6;
}

.features {
  text-align: left;
  margin-bottom: 30px;
}

.feature {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 12px;
  color: #6e6e73;
}

.feature .el-icon {
  color: #007AFF;
}

.start-button {
  width: 100%;
  height: 50px;
  font-size: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
}

:deep(.el-button--primary) {
  background: #007AFF;
  border-color: #007AFF;
}

@media (max-width: 768px) {
  .frontend-home {
    padding: 20px;
  }

  .page-header h1 {
    font-size: 36px;
  }

  .language-card {
    margin-bottom: 20px;
  }
}
</style> 