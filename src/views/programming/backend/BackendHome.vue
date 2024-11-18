<template>
  <div class="backend-home">
    <div class="page-header">
      <h1>后端开发</h1>
      <p class="subtitle">选择你感兴趣的编程语言开始学习</p>
    </div>

    <div class="language-grid">
      <el-row :gutter="30">
        <el-col :span="8" v-for="lang in languages" :key="lang.name">
          <el-card 
            class="language-card" 
            :body-style="{ padding: '0px' }"
            @click="navigateToLanguage(lang.path)"
          >
            <div class="card-content">
              <img :src="lang.icon" :alt="lang.name" class="lang-icon">
              <h2>{{ lang.name }}</h2>
              <p class="description">{{ lang.description }}</p>
              <div class="features">
                <div class="feature" v-for="feature in lang.features" :key="feature">
                  <el-icon><Check /></el-icon>
                  <span>{{ feature }}</span>
                </div>
              </div>
              <el-button type="primary" class="start-button">开始学习</el-button>
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

const languages = ref([
  {
    name: 'Java',
    path: '/programming/java',
    icon: '/icons/java.svg',
    description: '最流行的企业级开发语言，拥有丰富的生态系统',
    features: [
      '跨平台兼容性',
      '企业级应用开发',
      '安卓应用开发'
    ]
  },
  {
    name: 'Python',
    path: '/programming/python',
    icon: '/icons/python.svg',
    description: '简单易学的全能型编程语言，广泛应用于各个领域',
    features: [
      '数据分析',
      '人工智能',
      'Web开发'
    ]
  },
  {
    name: 'Node.js',
    path: '/programming/nodejs',
    icon: '/icons/nodejs.svg',
    description: '基于 JavaScript 的服务器端运行环境',
    features: [
      '高性能服务器',
      '前后端统一语言',
      '丰富的包管理系统'
    ]
  }
])

const navigateToLanguage = (path) => {
  router.push(path)
}
</script>

<style scoped>
.backend-home {
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
  color: #666;
}

.language-card {
  height: 100%;
  transition: all 0.3s ease;
  border: none;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  cursor: pointer;
}

.language-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
}

.card-content {
  padding: 40px;
  text-align: center;
}

.lang-icon {
  width: 80px;
  height: 80px;
  margin-bottom: 20px;
}

.card-content h2 {
  font-size: 24px;
  margin-bottom: 12px;
  color: #333;
}

.description {
  color: #666;
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
  color: #666;
}

.feature .el-icon {
  color: #007AFF;
}

.start-button {
  width: 100%;
}

:deep(.el-button--primary) {
  background: #007AFF;
  border-color: #007AFF;
  font-size: 16px;
  padding: 12px 24px;
  height: auto;
}

@media (max-width: 768px) {
  .backend-home {
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