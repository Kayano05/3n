<template>
  <div class="home-page">
    <div class="hero-section">
      <h1 class="main-title">NoviceNest</h1>
      <p class="hero-subtitle">探索编程与日语学习的乐园</p>
      <div class="hero-buttons">
        <el-button type="primary" size="large" round @click="$router.push('/programming/frontend')">开始学习</el-button>
      </div>
    </div>

    <div class="course-tracks">
      <h2 class="section-title">学习方向</h2>
      <el-row :gutter="30">
        <el-col :span="8" v-for="track in tracks" :key="track.title">
          <div class="track-card" @click="$router.push(track.path)">
            <el-icon class="track-icon" :size="40">
              <component :is="track.icon" />
            </el-icon>
            <h3>{{ track.title }}</h3>
            <p>{{ track.description }}</p>
            <el-button type="primary" plain round>查看详情</el-button>
          </div>
        </el-col>
      </el-row>
    </div>

    <div class="cta-section">
      <h2>准备好开始学习了吗？</h2>
      <p>选择你感兴趣的方向，开启学习之旅</p>
      <div class="cta-buttons">
        <el-button type="primary" size="large" round @click="$router.push('/programming/frontend')">编程课程</el-button>
        <el-button size="large" round>日语课程</el-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const tracks = ref([
  {
    title: '前端开发',
    description: '学习 HTML、CSS、JavaScript 和主流框架，构建现代化的网页应用',
    icon: 'Monitor',
    path: '/programming/frontend'
  },
  {
    title: '后端开发',
    description: '掌握服务器端开发技术，构建强大的应用程序后台',
    icon: 'Connection',
    path: '/programming/backend'
  },
  {
    title: '算法训练',
    description: '提升编程思维，掌握数据结构与算法',
    icon: 'Share',
    path: '/programming/algorithm'
  }
])
</script>

<style scoped>
.home-page {
  padding: 60px 20px;
  text-align: center;
  background: linear-gradient(180deg, #fff 0%, #f5f5f7 100%);
}

.hero-section {
  margin-bottom: 80px;
  padding: 60px 0;
}

.main-title {
  font-size: 56px;
  font-weight: 600;
  background: linear-gradient(120deg, #007AFF, #00B4DB);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin-bottom: 20px;
}

.hero-subtitle {
  font-size: 28px;
  color: #1d1d1f;
  margin-bottom: 40px;
  font-weight: 500;
}

.hero-buttons {
  display: flex;
  gap: 20px;
  justify-content: center;
  margin-top: 40px;
}

.section-title {
  font-size: 36px;
  font-weight: 600;
  color: #1d1d1f;
  margin-bottom: 40px;
}

.course-tracks {
  max-width: 1200px;
  margin: 80px auto;
  padding: 0 20px;
}

.track-card {
  padding: 40px;
  background: #fff;
  border-radius: 20px;
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.04);
  transition: transform 0.3s ease;
  cursor: pointer;
}

.track-card:hover {
  transform: translateY(-5px);
}

.track-icon {
  color: #007AFF;
  margin-bottom: 20px;
}

.track-card h3 {
  font-size: 24px;
  margin-bottom: 16px;
  color: #1d1d1f;
}

.track-card p {
  color: #6e6e73;
  margin-bottom: 24px;
  line-height: 1.6;
}

.cta-section {
  margin: 80px auto;
  padding: 80px 0;
  background: linear-gradient(120deg, #007AFF, #00B4DB);
  color: #fff;
}

.cta-section h2 {
  font-size: 36px;
  margin-bottom: 16px;
}

.cta-section p {
  font-size: 18px;
  margin-bottom: 32px;
  opacity: 0.9;
}

.cta-buttons {
  display: flex;
  gap: 20px;
  justify-content: center;
}

.cta-buttons .el-button--default {
  background: #fff;
  border-color: #fff;
}

:deep(.el-button--primary) {
  background: #007AFF;
  border-color: #007AFF;
  font-size: 17px;
  padding: 12px 32px;
  height: auto;
}

:deep(.el-button--default) {
  border-color: #007AFF;
  color: #007AFF;
  font-size: 17px;
  padding: 12px 32px;
  height: auto;
}

@media (max-width: 768px) {
  .main-title {
    font-size: 42px;
  }
  
  .hero-subtitle {
    font-size: 24px;
  }
  
  .track-card {
    margin-bottom: 20px;
  }

  .section-title {
    font-size: 28px;
  }

  .cta-section h2 {
    font-size: 28px;
  }
}
</style> 