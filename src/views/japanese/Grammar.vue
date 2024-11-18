<template>
  <div class="grammar">
    <div class="hero-section">
      <h1>语法教程</h1>
      <p class="subtitle">系统学习日语语法</p>
    </div>

    <div class="content-section">
      <el-row :gutter="30">
        <el-col :span="16">
          <!-- 主要内容区 -->
          <div class="main-content">
            <div class="section-title">
              <h2>基础语法</h2>
              <p>从零开始学习日语语法</p>
            </div>

            <div class="grammar-grid">
              <el-card v-for="item in grammarItems" 
                      :key="item.title" 
                      class="grammar-card" 
                      shadow="hover"
              >
                <div class="grammar-content">
                  <div class="grammar-header">
                    <span class="level-tag" :class="item.level">{{ item.levelText }}</span>
                    <h3>{{ item.title }}</h3>
                  </div>
                  <p class="grammar-description">{{ item.description }}</p>
                  <div class="example-box">
                    <div class="example-item" v-for="(example, index) in item.examples" :key="index">
                      <div class="japanese">{{ example.japanese }}</div>
                      <div class="pinyin">{{ example.pinyin }}</div>
                      <div class="chinese">{{ example.chinese }}</div>
                    </div>
                  </div>
                  <div class="usage-notes">
                    <h4>使用要点</h4>
                    <ul>
                      <li v-for="(note, index) in item.notes" :key="index">
                        <el-icon><InfoFilled /></el-icon>
                        <span>{{ note }}</span>
                      </li>
                    </ul>
                  </div>
                  <el-button type="primary" class="practice-btn" round>
                    练习
                    <el-icon class="el-icon--right"><ArrowRight /></el-icon>
                  </el-button>
                </div>
              </el-card>
            </div>
          </div>
        </el-col>

        <el-col :span="8">
          <!-- 侧边栏 -->
          <div class="sidebar">
            <el-card class="filter-card">
              <template #header>
                <div class="card-header">
                  <h3>筛选</h3>
                </div>
              </template>
              <div class="filter-section">
                <h4>难度等级</h4>
                <el-checkbox-group v-model="selectedLevels">
                  <el-checkbox label="n5">N5</el-checkbox>
                  <el-checkbox label="n4">N4</el-checkbox>
                  <el-checkbox label="n3">N3</el-checkbox>
                  <el-checkbox label="n2">N2</el-checkbox>
                  <el-checkbox label="n1">N1</el-checkbox>
                </el-checkbox-group>
              </div>
              <div class="filter-section">
                <h4>语法类型</h4>
                <el-checkbox-group v-model="selectedTypes">
                  <el-checkbox label="basic">基础语法</el-checkbox>
                  <el-checkbox label="particle">助词用法</el-checkbox>
                  <el-checkbox label="verb">动词变形</el-checkbox>
                  <el-checkbox label="keigo">敬语</el-checkbox>
                </el-checkbox-group>
              </div>
            </el-card>

            <el-card class="progress-card">
              <template #header>
                <div class="card-header">
                  <h3>学习进度</h3>
                </div>
              </template>
              <div class="progress-stats">
                <div class="stat-item">
                  <div class="stat-number">42</div>
                  <div class="stat-label">已学语法点</div>
                </div>
                <div class="stat-item">
                  <div class="stat-number">85%</div>
                  <div class="stat-label">掌握程度</div>
                </div>
              </div>
              <el-progress 
                :percentage="85" 
                :stroke-width="10"
                :color="progressColor"
              />
            </el-card>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const selectedLevels = ref(['n5', 'n4'])
const selectedTypes = ref(['basic'])
const progressColor = '#FF2D55'

const grammarItems = ref([
  {
    title: 'は助词的用法',
    description: '表示主题或对比的重要助词',
    level: 'n5',
    levelText: 'N5',
    examples: [
      {
        japanese: '私は学生です。',
        pinyin: 'Watashi wa gakusei desu.',
        chinese: '我是学生。'
      },
      {
        japanese: '彼は医者です。',
        pinyin: 'Kare wa isha desu.',
        chinese: '他是医生。'
      }
    ],
    notes: [
      '用于标识句子的主题',
      '可以用来表示对比',
      '与が助词的区别'
    ]
  },
  {
    title: 'て形的变化与用法',
    description: '动词て形是日语中最重要的语法形式之一',
    level: 'n5',
    levelText: 'N5',
    examples: [
      {
        japanese: '食べて寝ます。',
        pinyin: 'Tabete nemasu.',
        chinese: '吃完睡觉。'
      },
      {
        japanese: '音楽を聴いて勉強します。',
        pinyin: 'Ongaku wo kiite benkyou shimasu.',
        chinese: '听着音乐学习。'
      }
    ],
    notes: [
      '表示动作的连续',
      '可以表示方式、手段',
      '用于请求和许可'
    ]
  }
])
</script>

<style scoped>
.grammar {
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

.grammar-grid {
  display: grid;
  gap: 30px;
}

.grammar-card {
  border: none;
  border-radius: 16px;
  transition: all 0.3s ease;
}

.grammar-card:hover {
  transform: translateY(-5px);
}

.grammar-content {
  padding: 24px;
}

.grammar-header {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 16px;
}

.level-tag {
  padding: 4px 12px;
  border-radius: 20px;
  font-size: 14px;
  font-weight: 500;
}

.level-tag.n5 {
  background: #E8F5E9;
  color: #2E7D32;
}

.grammar-header h3 {
  font-size: 20px;
  color: #1d1d1f;
  margin: 0;
}

.grammar-description {
  color: #6e6e73;
  margin-bottom: 20px;
}

.example-box {
  background: #f5f5f7;
  border-radius: 12px;
  padding: 16px;
  margin-bottom: 20px;
}

.example-item {
  margin-bottom: 12px;
}

.example-item:last-child {
  margin-bottom: 0;
}

.japanese {
  font-size: 16px;
  color: #1d1d1f;
  margin-bottom: 4px;
}

.pinyin {
  font-size: 14px;
  color: #FF2D55;
  margin-bottom: 4px;
}

.chinese {
  font-size: 14px;
  color: #6e6e73;
}

.usage-notes {
  margin-bottom: 20px;
}

.usage-notes h4 {
  font-size: 16px;
  color: #1d1d1f;
  margin-bottom: 12px;
}

.usage-notes ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.usage-notes li {
  display: flex;
  align-items: center;
  gap: 8px;
  color: #6e6e73;
  margin-bottom: 8px;
  font-size: 14px;
}

.usage-notes .el-icon {
  color: #FF2D55;
}

.practice-btn {
  width: 100%;
}

.sidebar {
  position: sticky;
  top: 84px;
}

.filter-card,
.progress-card {
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

.filter-section {
  margin-bottom: 20px;
}

.filter-section h4 {
  font-size: 16px;
  color: #1d1d1f;
  margin-bottom: 12px;
}

.progress-stats {
  display: flex;
  justify-content: space-around;
  margin-bottom: 20px;
}

.stat-item {
  text-align: center;
}

.stat-number {
  font-size: 24px;
  font-weight: 600;
  color: #FF2D55;
  margin-bottom: 4px;
}

.stat-label {
  font-size: 14px;
  color: #6e6e73;
}

:deep(.el-button--primary) {
  background: #FF2D55;
  border-color: #FF2D55;
}

:deep(.el-checkbox__input.is-checked + .el-checkbox__label) {
  color: #FF2D55;
}

:deep(.el-checkbox__input.is-checked .el-checkbox__inner) {
  background-color: #FF2D55;
  border-color: #FF2D55;
}

@media (max-width: 768px) {
  .grammar {
    padding: 20px;
  }

  .hero-section h1 {
    font-size: 36px;
  }

  .main-content {
    padding: 20px;
  }
}
</style> 