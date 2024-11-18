<template>
  <div class="jlpt-home">
    <div class="page-header">
      <h1>日语能力考试</h1>
      <p class="subtitle">从N5到N1的完整学习路径</p>
    </div>

    <div class="level-grid">
      <el-row :gutter="30">
        <el-col :span="8" v-for="level in levels" :key="level.name">
          <div class="level-card" :class="level.class">
            <div class="level-header">
              <h2>{{ level.name }}</h2>
              <span class="level-tag">{{ level.difficulty }}</span>
            </div>
            <p class="level-description">{{ level.description }}</p>
            <div class="requirements">
              <h3>考试要求</h3>
              <ul>
                <li v-for="req in level.requirements" :key="req">
                  <el-icon><Check /></el-icon>
                  <span>{{ req }}</span>
                </li>
              </ul>
            </div>
            <div class="stats">
              <div class="stat-item">
                <el-icon><Timer /></el-icon>
                <div class="stat-text">
                  <span>考试时长</span>
                  <strong>{{ level.duration }}</strong>
                </div>
              </div>
              <div class="stat-item">
                <el-icon><Collection /></el-icon>
                <div class="stat-text">
                  <span>词汇量</span>
                  <strong>{{ level.vocabulary }}</strong>
                </div>
              </div>
            </div>
            <el-button 
              type="primary" 
              class="start-button" 
              round
              @click="$router.push(level.path)"
            >
              开始学习
              <el-icon class="el-icon--right"><ArrowRight /></el-icon>
            </el-button>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const levels = ref([
  {
    name: 'N5',
    class: 'beginner',
    difficulty: '入门级',
    description: '掌握基础日语，能够理解和使用最基本的日语表达',
    requirements: [
      '掌握基础语法',
      '认识平假名和片假名',
      '掌握100个汉字',
      '理解基本的日常用语'
    ],
    duration: '110分钟',
    vocabulary: '800词',
    path: '/japanese/n5'
  },
  {
    name: 'N4',
    class: 'basic',
    difficulty: '基础级',
    description: '能够理解基本日语，在日常生活中进行简单交流',
    requirements: [
      '掌握基本语法',
      '掌握300个汉字',
      '理解基本的日常会话',
      '能读懂简单文章'
    ],
    duration: '120分钟',
    vocabulary: '1500词',
    path: '/japanese/n4'
  },
  {
    name: 'N3',
    class: 'intermediate',
    difficulty: '中级',
    description: '能够理解日常生活中使用的日语，提升交流能力',
    requirements: [
      '掌握常用语法',
      '掌握650个汉字',
      '理解一般性话题',
      '能阅读新闻文章'
    ],
    duration: '140分钟',
    vocabulary: '3000词',
    path: '/japanese/n3'
  }
])
</script>

<style scoped>
.jlpt-home {
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
  background: linear-gradient(120deg, #FF2D55, #FF375F);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin-bottom: 16px;
}

.subtitle {
  font-size: 20px;
  color: #6e6e73;
  font-weight: 500;
}

.level-grid {
  padding: 20px;
}

.level-card {
  padding: 40px;
  background: #fff;
  border-radius: 20px;
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.04);
  transition: all 0.3s ease;
  height: 100%;
  border: 1px solid #f0f0f0;
}

.level-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.08);
}

.level-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 20px;
}

.level-header h2 {
  font-size: 32px;
  font-weight: 600;
  margin: 0;
}

.level-tag {
  padding: 4px 12px;
  border-radius: 20px;
  font-size: 14px;
  font-weight: 500;
}

.beginner .level-tag {
  background: #E8F5E9;
  color: #2E7D32;
}

.basic .level-tag {
  background: #FFF3E0;
  color: #EF6C00;
}

.intermediate .level-tag {
  background: #E3F2FD;
  color: #1976D2;
}

.level-description {
  color: #6e6e73;
  margin-bottom: 30px;
  line-height: 1.6;
}

.requirements {
  margin-bottom: 30px;
}

.requirements h3 {
  font-size: 18px;
  margin-bottom: 16px;
  color: #1d1d1f;
}

.requirements ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.requirements li {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 12px;
  color: #6e6e73;
}

.requirements .el-icon {
  color: #FF2D55;
}

.stats {
  display: flex;
  gap: 20px;
  margin-bottom: 30px;
  padding: 20px;
  background: #f5f5f7;
  border-radius: 12px;
}

.stat-item {
  display: flex;
  align-items: center;
  gap: 12px;
  flex: 1;
}

.stat-item .el-icon {
  font-size: 24px;
  color: #FF2D55;
}

.stat-text {
  display: flex;
  flex-direction: column;
}

.stat-text span {
  font-size: 14px;
  color: #6e6e73;
}

.stat-text strong {
  font-size: 16px;
  color: #1d1d1f;
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
  background: #FF2D55;
  border-color: #FF2D55;
}

@media (max-width: 768px) {
  .jlpt-home {
    padding: 20px;
  }

  .page-header h1 {
    font-size: 36px;
  }

  .level-card {
    margin-bottom: 20px;
  }

  .stats {
    flex-direction: column;
  }
}
</style> 