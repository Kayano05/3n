<template>
  <div class="algorithm-home">
    <div class="page-header">
      <h1>算法训练</h1>
      <p class="subtitle">掌握数据结构与算法，提升编程能力</p>
    </div>

    <div class="course-grid">
      <el-row :gutter="30">
        <el-col :span="8" v-for="course in courses" :key="course.name">
          <el-card 
            class="course-card" 
            :body-style="{ padding: '0px' }"
            @click="navigateToCourse(course.path)"
          >
            <div class="card-content">
              <el-icon class="course-icon" :size="40">
                <component :is="course.icon" />
              </el-icon>
              <h2>{{ course.name }}</h2>
              <p class="description">{{ course.description }}</p>
              <div class="features">
                <div class="feature" v-for="feature in course.features" :key="feature">
                  <el-icon><Check /></el-icon>
                  <span>{{ feature }}</span>
                </div>
              </div>
              <div class="difficulty" :class="course.level">
                <el-icon><TrendCharts /></el-icon>
                <span>{{ course.difficulty }}</span>
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

const courses = ref([
  {
    name: '基础算法',
    path: '/programming/algorithm/basics',
    icon: 'DataLine',
    description: '掌握基本的算法思维和编程技巧',
    features: [
      '时间复杂度分析',
      '排序算法',
      '查找算法'
    ],
    difficulty: '入门级',
    level: 'easy'
  },
  {
    name: '数据结构',
    path: '/programming/algorithm/data-structure',
    icon: 'Connection',
    description: '学习常用数据结构的原理与实现',
    features: [
      '数组与链表',
      '栈与队列',
      '树与图'
    ],
    difficulty: '进阶级',
    level: 'medium'
  },
  {
    name: '高级算法',
    path: '/programming/algorithm/advanced',
    icon: 'Share',
    description: '深入学习复杂算法与解题技巧',
    features: [
      '动态规划',
      '贪心算法',
      '回溯算法'
    ],
    difficulty: '高级',
    level: 'hard'
  }
])

const navigateToCourse = (path) => {
  router.push(path)
}
</script>

<style scoped>
.algorithm-home {
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

.course-grid {
  padding: 20px;
}

.course-card {
  height: 100%;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  border: none;
  border-radius: 20px;
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.04);
  cursor: pointer;
  background: #fff;
}

.course-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.08);
}

.card-content {
  padding: 40px;
  text-align: center;
}

.course-icon {
  color: #007AFF;
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
  margin-bottom: 24px;
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

.difficulty {
  display: inline-flex;
  align-items: center;
  gap: 6px;
  padding: 6px 12px;
  border-radius: 20px;
  margin-bottom: 24px;
  font-size: 14px;
}

.difficulty.easy {
  background: #E8F5E9;
  color: #2E7D32;
}

.difficulty.medium {
  background: #FFF3E0;
  color: #EF6C00;
}

.difficulty.hard {
  background: #FFEBEE;
  color: #C62828;
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
  .algorithm-home {
    padding: 20px;
  }

  .page-header h1 {
    font-size: 36px;
  }

  .course-card {
    margin-bottom: 20px;
  }
}
</style> 