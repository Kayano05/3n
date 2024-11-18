<template>
  <div class="writing">
    <div class="hero-section">
      <h1>文字与发音</h1>
      <p class="subtitle">掌握日语基础文字系统</p>
    </div>

    <div class="content-section">
      <el-row :gutter="30">
        <el-col :span="16">
          <!-- 主要学习内容 -->
          <div class="main-content">
            <div class="section-title">
              <h2>平假名</h2>
              <p>基础五十音图</p>
            </div>
            
            <div class="hiragana-grid">
              <div v-for="char in hiraganaBasic" :key="char.kana" class="kana-card">
                <div class="kana">{{ char.kana }}</div>
                <div class="romaji">{{ char.romaji }}</div>
              </div>
            </div>

            <div class="section-title">
              <h2>浊音与半浊音</h2>
              <p>变化音</p>
            </div>

            <div class="hiragana-grid">
              <div v-for="char in hiraganaDakuon" :key="char.kana" class="kana-card">
                <div class="kana">{{ char.kana }}</div>
                <div class="romaji">{{ char.romaji }}</div>
              </div>
            </div>
          </div>
        </el-col>

        <el-col :span="8">
          <!-- 学习进度和练习 -->
          <div class="sidebar">
            <el-card class="progress-card">
              <template #header>
                <div class="card-header">
                  <h3>学习进度</h3>
                </div>
              </template>
              <el-progress 
                type="circle" 
                :percentage="progress" 
                :stroke-width="10"
                :color="progressColor"
              />
              <p class="progress-text">已掌握 {{ progress }}%</p>
            </el-card>

            <el-card class="practice-card">
              <template #header>
                <div class="card-header">
                  <h3>练习模式</h3>
                </div>
              </template>
              <ul class="practice-list">
                <li v-for="mode in practiceModes" :key="mode.name">
                  <el-button 
                    type="primary" 
                    plain 
                    round
                    class="practice-btn"
                  >
                    <el-icon>
                      <component :is="mode.icon" />
                    </el-icon>
                    {{ mode.name }}
                  </el-button>
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

const progress = ref(35)
const progressColor = '#FF2D55'

const hiraganaBasic = [
  { kana: 'あ', romaji: 'a' },
  { kana: 'い', romaji: 'i' },
  { kana: 'う', romaji: 'u' },
  { kana: 'え', romaji: 'e' },
  { kana: 'お', romaji: 'o' },
  // 可以继续添加其他假名
]

const hiraganaDakuon = [
  { kana: 'が', romaji: 'ga' },
  { kana: 'ぎ', romaji: 'gi' },
  { kana: 'ぐ', romaji: 'gu' },
  { kana: 'げ', romaji: 'ge' },
  { kana: 'ご', romaji: 'go' },
  // 可以继续添加其他假名
]

const practiceModes = [
  { name: '书写练习', icon: 'Edit' },
  { name: '发音练习', icon: 'Microphone' },
  { name: '听写测试', icon: 'Headset' },
  { name: '记忆游戏', icon: 'GamePad' }
]
</script>

<style scoped>
.writing {
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

.hiragana-grid {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 20px;
  margin-bottom: 40px;
}

.kana-card {
  background: #f5f5f7;
  border-radius: 12px;
  padding: 20px;
  text-align: center;
  transition: all 0.3s ease;
  cursor: pointer;
}

.kana-card:hover {
  transform: translateY(-5px);
  background: #fff;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
}

.kana {
  font-size: 32px;
  color: #1d1d1f;
  margin-bottom: 8px;
}

.romaji {
  font-size: 14px;
  color: #6e6e73;
}

.sidebar {
  position: sticky;
  top: 84px;
}

.progress-card {
  margin-bottom: 20px;
  text-align: center;
  border: none;
  border-radius: 20px;
}

.progress-text {
  margin-top: 16px;
  color: #6e6e73;
}

.practice-card {
  border: none;
  border-radius: 20px;
}

.practice-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.practice-btn {
  width: 100%;
  margin-bottom: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  height: 44px;
}

:deep(.el-button--primary) {
  background: #FF2D55;
  border-color: #FF2D55;
}

:deep(.el-button--primary.is-plain) {
  color: #FF2D55;
  background: #fff;
  border-color: #FF2D55;
}

:deep(.el-card) {
  border: none;
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.04);
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

@media (max-width: 768px) {
  .writing {
    padding: 20px;
  }

  .hero-section h1 {
    font-size: 36px;
  }

  .hiragana-grid {
    grid-template-columns: repeat(3, 1fr);
  }
}
</style> 