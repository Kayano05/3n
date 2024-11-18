<template>
  <div class="conversation">
    <div class="hero-section">
      <h1>实用会话</h1>
      <p class="subtitle">掌握日常生活会话</p>
    </div>

    <div class="content-section">
      <el-row :gutter="30">
        <el-col :span="16">
          <!-- 主要内容区 -->
          <div class="main-content">
            <div class="section-title">
              <h2>场景会话</h2>
              <p>生活中常见的对话场景</p>
            </div>

            <div class="conversation-grid">
              <el-card v-for="scene in scenes" 
                      :key="scene.title" 
                      class="scene-card" 
                      shadow="hover"
              >
                <div class="scene-content">
                  <el-icon class="scene-icon" :size="32">
                    <component :is="scene.icon" />
                  </el-icon>
                  <h3>{{ scene.title }}</h3>
                  <p class="scene-description">{{ scene.description }}</p>
                  <div class="example-dialog">
                    <div class="dialog-item" v-for="(dialog, index) in scene.example" :key="index">
                      <div class="japanese">{{ dialog.japanese }}</div>
                      <div class="pinyin">{{ dialog.pinyin }}</div>
                      <div class="chinese">{{ dialog.chinese }}</div>
                    </div>
                  </div>
                  <el-button type="primary" class="practice-btn" round>
                    开始练习
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
            <el-card class="tools-card">
              <template #header>
                <div class="card-header">
                  <h3>学习工具</h3>
                </div>
              </template>
              <div class="tools-list">
                <el-button v-for="tool in tools" 
                          :key="tool.name"
                          class="tool-btn"
                          :icon="tool.icon"
                >
                  {{ tool.name }}
                </el-button>
              </div>
            </el-card>

            <el-card class="tips-card">
              <template #header>
                <div class="card-header">
                  <h3>会话技巧</h3>
                </div>
              </template>
              <ul class="tips-list">
                <li v-for="tip in tips" :key="tip">
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

const scenes = ref([
  {
    title: '自我介绍',
    description: '学习如何用日语介绍自己',
    icon: 'User',
    example: [
      {
        japanese: 'はじめまして。',
        pinyin: 'Hajimemashite.',
        chinese: '初次见面。'
      },
      {
        japanese: '私は田中です。',
        pinyin: 'Watashi wa Tanaka desu.',
        chinese: '我是田中。'
      }
    ]
  },
  {
    title: '购物对话',
    description: '商店、超市等场景的实用对话',
    icon: 'ShoppingCart',
    example: [
      {
        japanese: 'これはいくらですか？',
        pinyin: 'Kore wa ikura desu ka?',
        chinese: '这个多少钱？'
      },
      {
        japanese: '千円です。',
        pinyin: 'Sen-en desu.',
        chinese: '1000日元。'
      }
    ]
  },
  {
    title: '餐厅用餐',
    description: '在餐厅点餐和用餐的对话',
    icon: 'Food',
    example: [
      {
        japanese: 'メニューをください。',
        pinyin: 'Menu wo kudasai.',
        chinese: '请给我菜单。'
      },
      {
        japanese: 'ご注文は何にしますか？',
        pinyin: 'Go-chuumon wa nani ni shimasu ka?',
        chinese: '您要点什么？'
      }
    ]
  }
])

const tools = ref([
  { name: '发音练习', icon: 'Microphone' },
  { name: '录音对比', icon: 'VideoPlay' },
  { name: '情境模拟', icon: 'Monitor' },
  { name: '单词本', icon: 'Notebook' }
])

const tips = ref([
  '注意语气的抑扬顿挫',
  '使用适当的敬语',
  '注意对话中的礼仪',
  '掌握常用语气词',
  '学会适时使用表情'
])
</script>

<style scoped>
.conversation {
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

.conversation-grid {
  display: grid;
  gap: 30px;
}

.scene-card {
  border: none;
  border-radius: 16px;
  transition: all 0.3s ease;
}

.scene-card:hover {
  transform: translateY(-5px);
}

.scene-content {
  padding: 20px;
}

.scene-icon {
  color: #FF2D55;
  margin-bottom: 16px;
}

.scene-content h3 {
  font-size: 20px;
  color: #1d1d1f;
  margin-bottom: 8px;
}

.scene-description {
  color: #6e6e73;
  margin-bottom: 20px;
}

.example-dialog {
  background: #f5f5f7;
  border-radius: 12px;
  padding: 16px;
  margin-bottom: 20px;
}

.dialog-item {
  margin-bottom: 12px;
}

.dialog-item:last-child {
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

.practice-btn {
  width: 100%;
}

.sidebar {
  position: sticky;
  top: 84px;
}

.tools-card,
.tips-card {
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

.tools-list {
  display: grid;
  gap: 12px;
}

.tool-btn {
  width: 100%;
  justify-content: flex-start;
  padding: 12px;
  font-size: 14px;
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
  color: #FF2D55;
}

:deep(.el-button--primary) {
  background: #FF2D55;
  border-color: #FF2D55;
}

@media (max-width: 768px) {
  .conversation {
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