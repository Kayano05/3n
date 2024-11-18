<template>
  <div class="n5-home">
    <div class="hero-section">
      <h1>JLPT N5</h1>
      <p class="subtitle">日语能力考试入门级</p>
    </div>

    <div class="course-grid">
      <el-row :gutter="30">
        <el-col :span="8" v-for="course in courses" :key="course.title">
          <el-card class="course-card" shadow="hover">
            <div class="card-content">
              <div class="card-header">
                <el-icon class="course-icon" :size="32">
                  <component :is="course.icon" />
                </el-icon>
                <span class="lesson-count">{{ course.lessons }}课</span>
              </div>
              <h3>{{ course.title }}</h3>
              <p class="description">{{ course.description }}</p>
              <div class="topics">
                <h4>主要内容</h4>
                <ul>
                  <li v-for="topic in course.topics" :key="topic">
                    <el-icon><Check /></el-icon>
                    <span>{{ topic }}</span>
                  </li>
                </ul>
              </div>
              <el-button 
                type="primary" 
                class="start-button" 
                round
                @click="$router.push(course.path)"
              >
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

const courses = ref([
  {
    title: '文字与发音',
    description: '掌握平假名、片假名的书写和发音规则',
    icon: 'Edit',
    lessons: 10,
    path: '/japanese/n5/writing',
    topics: [
      '平假名书写与发音',
      '片假名书写与发音',
      '浊音与拗音',
      '长音与促音',
      '声调规则'
    ]
  },
  {
    title: '基础语法',
    description: '学习日语基本语法结构和句型',
    icon: 'Document',
    lessons: 15,
    path: '/japanese/n5/grammar',
    topics: [
      '动词变形',
      '形容词变形',
      '助词的使用',
      '简体与敬体',
      '基本句型'
    ]
  },
  {
    title: '实用会话',
    description: '掌握日常生活中的基本会话',
    icon: 'ChatDotRound',
    lessons: 12,
    path: '/japanese/n5/conversation',
    topics: [
      '自我介绍',
      '日常问候',
      '购物对话',
      '时间表达',
      '简单请求'
    ]
  }
])
</script>

<style scoped>
.n5-home {
  padding: 40px;
  max-width: 1200px;
  margin: 0 auto;
}

.hero-section {
  text-align: center;
  margin-bottom: 60px;
}

.hero-section h1 {
  font-size: 48px;
  font-weight: 600;
  background: linear-gradient(120deg, #FF2D55, #FF375F);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin-bottom: 16px;
}

.subtitle {
  font-size: 24px;
  color: #6e6e73;
  font-weight: 500;
}

.course-grid {
  padding: 20px;
}

.course-card {
  height: 100%;
  transition: all 0.3s ease;
  border: none;
  border-radius: 20px;
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.04);
}

.course-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.08);
}

.card-content {
  padding: 30px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.course-icon {
  color: #FF2D55;
}

.lesson-count {
  padding: 4px 12px;
  background: #FFF2F4;
  color: #FF2D55;
  border-radius: 20px;
  font-size: 14px;
  font-weight: 500;
}

.card-content h3 {
  font-size: 24px;
  margin-bottom: 12px;
  color: #1d1d1f;
}

.description {
  color: #6e6e73;
  margin-bottom: 24px;
  line-height: 1.6;
}

.topics {
  margin-bottom: 30px;
}

.topics h4 {
  font-size: 16px;
  color: #1d1d1f;
  margin-bottom: 12px;
}

.topics ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.topics li {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 8px;
  color: #6e6e73;
  font-size: 14px;
}

.topics .el-icon {
  color: #FF2D55;
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
  .n5-home {
    padding: 20px;
  }

  .hero-section h1 {
    font-size: 36px;
  }

  .course-card {
    margin-bottom: 20px;
  }
}
</style> 