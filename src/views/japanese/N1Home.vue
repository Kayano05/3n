<template>
  <div class="n1-home">
    <div class="hero-section">
      <h1>JLPT N1</h1>
      <p class="subtitle">最高级日语水平</p>
    </div>

    <div class="content-section">
      <el-row :gutter="30">
        <el-col :span="16">
          <div class="main-content">
            <div class="section-title">
              <h2>课程内容</h2>
              <p>系统化的 N1 备考课程</p>
            </div>

            <div class="course-grid">
              <el-card v-for="course in courses" 
                      :key="course.title" 
                      class="course-card" 
                      shadow="hover"
              >
                <div class="course-content">
                  <el-icon class="course-icon" :size="32">
                    <component :is="course.icon" />
                  </el-icon>
                  <h3>{{ course.title }}</h3>
                  <p class="description">{{ course.description }}</p>
                  <div class="features">
                    <div class="feature" v-for="feature in course.features" :key="feature">
                      <el-icon><Check /></el-icon>
                      <span>{{ feature }}</span>
                    </div>
                  </div>
                  <el-button type="primary" class="start-btn" round>
                    开始学习
                    <el-icon class="el-icon--right"><ArrowRight /></el-icon>
                  </el-button>
                </div>
              </el-card>
            </div>
          </div>
        </el-col>

        <el-col :span="8">
          <div class="sidebar">
            <el-card class="exam-info">
              <template #header>
                <div class="card-header">
                  <h3>考试要求</h3>
                </div>
              </template>
              <div class="info-content">
                <div class="info-item">
                  <h4>词汇量</h4>
                  <p>10000词</p>
                </div>
                <div class="info-item">
                  <h4>汉字</h4>
                  <p>2000字</p>
                </div>
                <div class="info-item">
                  <h4>考试时长</h4>
                  <p>170分钟</p>
                </div>
                <div class="info-item">
                  <h4>及格分数</h4>
                  <p>100/180分</p>
                </div>
              </div>
            </el-card>

            <el-card class="study-tips">
              <template #header>
                <div class="card-header">
                  <h3>学习建议</h3>
                </div>
              </template>
              <ul class="tips-list">
                <li v-for="tip in studyTips" :key="tip">
                  <el-icon><InfoFilled /></el-icon>
                  <span>{{ tip }}</span>
                </li>
              </ul>
            </el-card>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const courses = ref([
  {
    title: '高难语法',
    icon: 'Edit',
    description: '掌握 N1 水平的语法知识',
    features: [
      '古典语法',
      '文言文体',
      '抽象表达',
      '高级敬语'
    ]
  },
  {
    title: '阅读理解',
    icon: 'Document',
    description: '提高日语文章阅读能力',
    features: [
      '学术论文',
      '文学作品',
      '新闻社论',
      '专业文献'
    ]
  },
  {
    title: '听力训练',
    icon: 'Headset',
    description: '提升日语听力理解能力',
    features: [
      '学术讲座',
      '新闻评论',
      '辩论内容',
      '专业报告'
    ]
  }
])

const studyTips = ref([
  '每天坚持学习4-5小时',
  '阅读原版日语小说和论文',
  '观看NHK新闻和纪录片',
  '参加日语辩论活动',
  '练习专业领域写作'
])
</script>

<style scoped>
.n1-home {
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
  background: linear-gradient(120deg, #D84315, #FF5722);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin-bottom: 16px;
}

.subtitle {
  font-size: 24px;
  color: #6e6e73;
  font-weight: 500;
}

.main-content {
  background: #fff;
  border-radius: 20px;
  padding: 40px;
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.04);
}

.section-title {
  margin-bottom: 30px;
}

.section-title h2 {
  font-size: 28px;
  color: #1d1d1f;
  margin-bottom: 8px;
}

.section-title p {
  color: #6e6e73;
  font-size: 16px;
}

.course-grid {
  display: grid;
  gap: 30px;
}

.course-card {
  border: none;
  border-radius: 16px;
  transition: all 0.3s ease;
}

.course-card:hover {
  transform: translateY(-5px);
}

.course-content {
  padding: 24px;
}

.course-icon {
  color: #D84315;
  margin-bottom: 16px;
}

.course-content h3 {
  font-size: 20px;
  color: #1d1d1f;
  margin-bottom: 12px;
}

.description {
  color: #6e6e73;
  margin-bottom: 20px;
}

.features {
  margin-bottom: 24px;
}

.feature {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 8px;
  color: #6e6e73;
  font-size: 14px;
}

.feature .el-icon {
  color: #D84315;
}

.start-btn {
  width: 100%;
}

.sidebar {
  position: sticky;
  top: 84px;
}

.exam-info,
.study-tips {
  border: none;
  border-radius: 20px;
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.card-header h3 {
  font-size: 18px;
  color: #1d1d1f;
  margin: 0;
}

.info-content {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.info-item h4 {
  font-size: 14px;
  color: #6e6e73;
  margin: 0 0 8px 0;
}

.info-item p {
  font-size: 20px;
  color: #D84315;
  font-weight: 600;
  margin: 0;
}

.tips-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.tips-list li {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 12px 0;
  color: #6e6e73;
  font-size: 14px;
}

.tips-list li:not(:last-child) {
  border-bottom: 1px solid #f5f5f7;
}

.tips-list .el-icon {
  color: #D84315;
}

:deep(.el-button--primary) {
  background: #D84315;
  border-color: #D84315;
}

@media (max-width: 768px) {
  .n1-home {
    padding: 20px;
  }

  .hero-section h1 {
    font-size: 36px;
  }

  .main-content {
    padding: 20px;
  }

  .info-content {
    grid-template-columns: 1fr;
  }
}
</style> 