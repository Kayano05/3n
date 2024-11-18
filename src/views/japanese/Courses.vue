<template>
  <div class="courses">
    <div class="hero-section">
      <h1>日语课程</h1>
      <p class="subtitle">探索完整的日语学习体系</p>
    </div>

    <div class="content-section">
      <el-row :gutter="30">
        <el-col :span="18">
          <div class="main-content">
            <div class="section-title">
              <span class="overline">JLPT 考试课程</span>
              <h2>从入门到精通的学习路径</h2>
              <p>系统化的日语能力考试备考课程，助你一步步实现目标</p>
            </div>

            <div class="course-grid">
              <div v-for="course in filteredJlptCourses" 
                   :key="course.level" 
                   class="course-card"
                   @click="$router.push(course.path)"
              >
                <div class="course-content">
                  <span class="level-tag" :class="course.levelClass">{{ course.level }}</span>
                  <h3>{{ course.title }}</h3>
                  <p class="description">{{ course.description }}</p>
                  <div class="features">
                    <div class="feature" v-for="feature in course.features" :key="feature">
                      <el-icon><Check /></el-icon>
                      <span>{{ feature }}</span>
                    </div>
                  </div>
                  <div class="course-footer">
                    <span class="duration">
                      <el-icon><Timer /></el-icon>
                      {{ course.duration }}
                    </span>
                    <el-button class="learn-more" text>
                      了解更多
                      <el-icon class="el-icon--right"><ArrowRight /></el-icon>
                    </el-button>
                  </div>
                </div>
              </div>
            </div>

            <div class="section-title">
              <span class="overline">专项课程</span>
              <h2>提升特定领域的语言能力</h2>
              <p>针对性的技能提升课程，满足不同场景的学习需求</p>
            </div>

            <div class="course-grid">
              <div v-for="course in filteredSpecialCourses" 
                   :key="course.title" 
                   class="course-card special-card"
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
                  <div class="course-footer">
                    <span class="duration">
                      <el-icon><Timer /></el-icon>
                      {{ course.duration }}
                    </span>
                    <el-button class="learn-more" text>
                      了解更多
                      <el-icon class="el-icon--right"><ArrowRight /></el-icon>
                    </el-button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </el-col>

        <el-col :span="6">
          <div class="sidebar">
            <div class="filter-section">
              <h3>难度等级</h3>
              <el-checkbox-group 
                v-model="selectedLevels"
                @change="handleFilterChange"
              >
                <el-checkbox label="n5">N5 入门级</el-checkbox>
                <el-checkbox label="n4">N4 基础级</el-checkbox>
                <el-checkbox label="n3">N3 中级</el-checkbox>
                <el-checkbox label="n2">N2 高级</el-checkbox>
                <el-checkbox label="n1">N1 精通级</el-checkbox>
              </el-checkbox-group>
            </div>

            <div class="filter-section">
              <h3>课程类型</h3>
              <el-checkbox-group 
                v-model="selectedTypes"
                @change="handleFilterChange"
              >
                <el-checkbox label="jlpt">JLPT 考试</el-checkbox>
                <el-checkbox label="conversation">口语会话</el-checkbox>
                <el-checkbox label="business">商务日语</el-checkbox>
                <el-checkbox label="culture">文化研究</el-checkbox>
              </el-checkbox-group>
            </div>

            <div class="learning-path">
              <h3>学习建议</h3>
              <div class="path-steps">
                <div class="path-step" v-for="(step, index) in learningPath" :key="index">
                  <div class="step-number">{{ index + 1 }}</div>
                  <div class="step-content">
                    <h4>{{ step.title }}</h4>
                    <p>{{ step.description }}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

const selectedLevels = ref(['n5', 'n4'])
const selectedTypes = ref(['jlpt'])

// 所有课程数据
const allJlptCourses = ref([
  {
    level: 'N5',
    levelClass: 'n5',
    title: 'JLPT N5 综合课程',
    description: '零基础入门级别，掌握基本的日语知识',
    duration: '3个月',
    path: '/japanese/n5',
    type: 'jlpt',
    features: [
      '基础语法与词汇',
      '日常会话练习',
      '听力训练',
      '考试技巧'
    ]
  },
  {
    level: 'N4',
    levelClass: 'n4',
    title: 'JLPT N4 进阶课程',
    description: '基础级别，能够理解日常生活中的日语',
    duration: '4个月',
    path: '/japanese/n4',
    type: 'jlpt',
    features: [
      '进阶语法',
      '词汇扩充',
      '阅读理解',
      '实战模拟'
    ]
  },
  {
    level: 'N3',
    levelClass: 'n3',
    title: 'JLPT N3 中级课程',
    description: '中级水平，掌握更复杂的日语表达',
    duration: '6个月',
    path: '/japanese/n3',
    type: 'jlpt',
    features: [
      '中级语法',
      '商务日语',
      '新闻阅读',
      '听力强化'
    ]
  },
  {
    level: 'N2',
    levelClass: 'n2',
    title: 'JLPT N2 高级课程',
    description: '高级水平，能够理解接近自然的日语',
    duration: '8个月',
    path: '/japanese/n2',
    type: 'jlpt',
    features: [
      '高级语法',
      '地道表达',
      '文学作品',
      '综合训练'
    ]
  },
  {
    level: 'N1',
    levelClass: 'n1',
    title: 'JLPT N1 精通课程',
    description: '最高级别，掌握高度抽象的日语表达',
    duration: '12个月',
    path: '/japanese/n1',
    type: 'jlpt',
    features: [
      '高难语法',
      '学术日语',
      '专业翻译',
      '实战备考'
    ]
  }
])

const allSpecialCourses = ref([
  {
    title: '商务日语',
    icon: 'Briefcase',
    description: '职场必备的日语交际能力培养',
    duration: '2个月',
    type: 'business',
    features: [
      '商务礼仪',
      '邮件写作',
      '会议用语',
      '谈判技巧'
    ]
  },
  {
    title: '日语口语速成',
    icon: 'ChatDotRound',
    description: '快速提升日常交流能力',
    duration: '6周',
    type: 'conversation',
    features: [
      '发音纠正',
      '场景对话',
      '口语技巧',
      '文化习俗'
    ]
  }
])

// 根据筛选条件过滤课程
const filteredJlptCourses = computed(() => {
  return allJlptCourses.value.filter(course => {
    const levelMatch = selectedLevels.value.includes(course.levelClass.toLowerCase())
    const typeMatch = selectedTypes.value.includes('jlpt')
    return levelMatch && typeMatch
  })
})

const filteredSpecialCourses = computed(() => {
  return allSpecialCourses.value.filter(course => {
    return selectedTypes.value.includes(course.type)
  })
})

// 处理筛选变化
const handleFilterChange = () => {
  // 如果没有选中任何选项，默认显示所有
  if (selectedLevels.value.length === 0) {
    selectedLevels.value = ['n5', 'n4', 'n3', 'n2', 'n1']
  }
  if (selectedTypes.value.length === 0) {
    selectedTypes.value = ['jlpt', 'conversation', 'business', 'culture']
  }
}

const learningPath = ref([
  {
    title: '入门准备',
    description: '掌握五十音图和基本发音规则'
  },
  {
    title: 'N5 水平',
    description: '学习基础语法和常用词汇'
  },
  {
    title: 'N4 水平',
    description: '提升阅读理解和听力能力'
  },
  {
    title: '实战运用',
    description: '参与口语练习和实际对话'
  }
])
</script>

<style scoped>
.courses {
  padding: 80px 40px;
  max-width: 1400px;
  margin: 0 auto;
  background: #fff;
}

.hero-section {
  text-align: center;
  margin-bottom: 80px;
}

.hero-section h1 {
  font-size: 56px;
  font-weight: 600;
  background: linear-gradient(120deg, #FF2D55, #FF375F);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin-bottom: 16px;
  letter-spacing: -0.5px;
}

.subtitle {
  font-size: 26px;
  color: #1d1d1f;
  font-weight: 500;
}

.section-title {
  margin-bottom: 48px;
  text-align: left;
}

.section-title .overline {
  font-size: 16px;
  color: #FF2D55;
  font-weight: 600;
  letter-spacing: 1.5px;
  text-transform: uppercase;
  display: block;
  margin-bottom: 16px;
}

.section-title h2 {
  font-size: 32px;
  color: #1d1d1f;
  margin-bottom: 12px;
  font-weight: 600;
  letter-spacing: -0.5px;
}

.section-title p {
  font-size: 18px;
  color: #6e6e73;
  line-height: 1.5;
}

.course-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 24px;
  margin-bottom: 80px;
}

.course-card {
  background: #fff;
  border-radius: 20px;
  overflow: hidden;
  transition: all 0.5s cubic-bezier(0.4, 0, 0.2, 1);
  cursor: pointer;
  position: relative;
  border: 1px solid #f5f5f7;
}

.course-card:hover {
  transform: scale(1.02);
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
}

.course-content {
  padding: 32px;
}

.level-tag {
  display: inline-block;
  padding: 6px 12px;
  border-radius: 20px;
  font-size: 14px;
  font-weight: 500;
  margin-bottom: 20px;
}

.course-card h3 {
  font-size: 24px;
  color: #1d1d1f;
  margin-bottom: 12px;
  font-weight: 600;
}

.description {
  font-size: 16px;
  color: #6e6e73;
  margin-bottom: 24px;
  line-height: 1.5;
}

.features {
  margin-bottom: 32px;
}

.feature {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 12px;
  color: #1d1d1f;
  font-size: 15px;
}

.feature .el-icon {
  color: #FF2D55;
}

.course-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-top: 24px;
  border-top: 1px solid #f5f5f7;
}

.duration {
  display: flex;
  align-items: center;
  gap: 8px;
  color: #6e6e73;
  font-size: 14px;
}

.learn-more {
  color: #FF2D55;
  font-size: 15px;
  font-weight: 500;
}

.sidebar {
  position: sticky;
  top: 84px;
  background: #fff;
  border-radius: 20px;
  padding: 32px;
  border: 1px solid #f5f5f7;
}

.filter-section {
  margin-bottom: 32px;
}

.filter-section h3 {
  font-size: 18px;
  color: #1d1d1f;
  margin-bottom: 16px;
  font-weight: 600;
}

.learning-path {
  margin-top: 40px;
}

.path-steps {
  margin-top: 20px;
}

.path-step {
  display: flex;
  gap: 16px;
  margin-bottom: 24px;
}

.step-number {
  width: 28px;
  height: 28px;
  background: #FF2D55;
  color: #fff;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 14px;
  font-weight: 500;
}

.step-content h4 {
  font-size: 16px;
  color: #1d1d1f;
  margin: 0 0 4px 0;
  font-weight: 500;
}

.step-content p {
  font-size: 14px;
  color: #6e6e73;
  margin: 0;
  line-height: 1.4;
}

:deep(.el-checkbox__input.is-checked + .el-checkbox__label) {
  color: #FF2D55;
}

:deep(.el-checkbox__input.is-checked .el-checkbox__inner) {
  background-color: #FF2D55;
  border-color: #FF2D55;
}

@media (max-width: 1200px) {
  .course-grid {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 768px) {
  .courses {
    padding: 40px 20px;
  }

  .hero-section h1 {
    font-size: 42px;
  }

  .subtitle {
    font-size: 20px;
  }

  .section-title h2 {
    font-size: 28px;
  }
}
</style> 