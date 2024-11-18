<template>
  <div class="practice-wrapper">
    <!-- 顶部状态栏 -->
    <div class="status-bar">
      <div class="progress-wrapper">
        <div class="progress-dots">
          <div v-for="(dot, index) in totalQuestions" 
               :key="index"
               :class="['dot', {
                 'completed': index < currentIndex,
                 'current': index === currentIndex
               }]">
          </div>
        </div>
      </div>
      <div class="hearts">
        <el-icon v-for="n in 3" :key="n" :class="['heart', { 'lost': n > lives }]">
          <HeartFilled />
        </el-icon>
      </div>
    </div>

    <!-- 练习内容 -->
    <div class="question-container" v-if="currentQuestion">
      <div class="question-type">{{ currentQuestion.prompt }}</div>
      
      <div class="question-display">
        {{ currentQuestion.question }}
      </div>

      <div class="options-grid">
        <div v-for="option in currentQuestion.options" 
             :key="option.id"
             :class="['option-card', {
               'selected': selectedAnswer === option.value,
               'correct': showResult && option.value === currentQuestion.answer,
               'wrong': showResult && selectedAnswer === option.value && 
                       option.value !== currentQuestion.answer
             }]"
             @click="selectAnswer(option.value)">
          {{ option.display }}
        </div>
      </div>
    </div>

    <!-- 底部按钮 -->
    <div class="action-bar">
      <el-button 
        type="primary" 
        size="large"
        :disabled="!selectedAnswer"
        @click="checkAnswer">
        检查
      </el-button>
    </div>

    <!-- 结果弹窗 -->
    <el-dialog
      v-model="showCompleteDialog"
      title="练习完成"
      width="300px"
      :show-close="false"
      center>
      <div class="result-stats">
        <div class="stat-item">
          <span>正确率</span>
          <span class="value">{{ Math.round((correctCount / totalQuestions) * 100) }}%</span>
        </div>
        <div class="stat-item">
          <span>剩余生命</span>
          <span class="value">{{ lives }}/3</span>
        </div>
      </div>
      <template #footer>
        <el-button @click="restart">再来一次</el-button>
        <el-button type="primary" @click="finish">完成</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { ElMessage } from 'element-plus'
import { HeartFilled } from '@element-plus/icons-vue'

// 修复: 添加 defineProps 和 defineEmits 的导入
import { defineProps, defineEmits } from 'vue'

// 定义 props 和 emits
const props = defineProps({
  mode: {
    type: String,
    default: 'all', // 'all', 'seion', 'dakuon', 'youon'
    validator: (value) => ['all', 'seion', 'dakuon', 'youon'].includes(value)
  }
})

const emit = defineEmits(['finish'])

// 假名数据
const hiraganaRows = [
  {
    vowel: 'a',
    chars: [
      { kana: 'あ', romaji: 'a' },
      { kana: 'い', romaji: 'i' },
      { kana: 'う', romaji: 'u' },
      { kana: 'え', romaji: 'e' },
      { kana: 'お', romaji: 'o' }
    ]
  },
  {
    vowel: 'ka',
    chars: [
      { kana: 'か', romaji: 'ka' },
      { kana: 'き', romaji: 'ki' },
      { kana: 'く', romaji: 'ku' },
      { kana: 'け', romaji: 'ke' },
      { kana: 'こ', romaji: 'ko' }
    ]
  },
  {
    vowel: 'sa',
    chars: [
      { kana: 'さ', romaji: 'sa' },
      { kana: 'し', romaji: 'shi' },
      { kana: 'す', romaji: 'su' },
      { kana: 'せ', romaji: 'se' },
      { kana: 'そ', romaji: 'so' }
    ]
  }
]

const dakuonRows = [
  {
    vowel: 'ga',
    chars: [
      { kana: 'が', romaji: 'ga' },
      { kana: 'ぎ', romaji: 'gi' },
      { kana: 'ぐ', romaji: 'gu' },
      { kana: 'げ', romaji: 'ge' },
      { kana: 'ご', romaji: 'go' }
    ]
  },
  {
    vowel: 'za',
    chars: [
      { kana: 'ざ', romaji: 'za' },
      { kana: 'じ', romaji: 'ji' },
      { kana: 'ず', romaji: 'zu' },
      { kana: 'ぜ', romaji: 'ze' },
      { kana: 'ぞ', romaji: 'zo' }
    ]
  }
]

// 添加拗音数据
const youonRows = [
  {
    vowel: 'kya',
    chars: [
      { kana: 'きゃ', romaji: 'kya' },
      { kana: 'きゅ', romaji: 'kyu' },
      { kana: 'きょ', romaji: 'kyo' }
    ]
  },
  {
    vowel: 'sha',
    chars: [
      { kana: 'しゃ', romaji: 'sha' },
      { kana: 'しゅ', romaji: 'shu' },
      { kana: 'しょ', romaji: 'sho' }
    ]
  },
  {
    vowel: 'cha',
    chars: [
      { kana: 'ちゃ', romaji: 'cha' },
      { kana: 'ちゅ', romaji: 'chu' },
      { kana: 'ちょ', romaji: 'cho' }
    ]
  },
  {
    vowel: 'nya',
    chars: [
      { kana: 'にゃ', romaji: 'nya' },
      { kana: 'にゅ', romaji: 'nyu' },
      { kana: 'にょ', romaji: 'nyo' }
    ]
  },
  {
    vowel: 'hya',
    chars: [
      { kana: 'ひゃ', romaji: 'hya' },
      { kana: 'ひゅ', romaji: 'hyu' },
      { kana: 'ひょ', romaji: 'hyo' }
    ]
  },
  {
    vowel: 'mya',
    chars: [
      { kana: 'みゃ', romaji: 'mya' },
      { kana: 'みゅ', romaji: 'myu' },
      { kana: 'みょ', romaji: 'myo' }
    ]
  },
  {
    vowel: 'rya',
    chars: [
      { kana: 'りゃ', romaji: 'rya' },
      { kana: 'りゅ', romaji: 'ryu' },
      { kana: 'りょ', romaji: 'ryo' }
    ]
  },
  {
    vowel: 'gya',
    chars: [
      { kana: 'ぎゃ', romaji: 'gya' },
      { kana: 'ぎゅ', romaji: 'gyu' },
      { kana: 'ぎょ', romaji: 'gyo' }
    ]
  },
  {
    vowel: 'ja',
    chars: [
      { kana: 'じゃ', romaji: 'ja' },
      { kana: 'じゅ', romaji: 'ju' },
      { kana: 'じょ', romaji: 'jo' }
    ]
  },
  {
    vowel: 'bya',
    chars: [
      { kana: 'びゃ', romaji: 'bya' },
      { kana: 'びゅ', romaji: 'byu' },
      { kana: 'びょ', romaji: 'byo' }
    ]
  },
  {
    vowel: 'pya',
    chars: [
      { kana: 'ぴゃ', romaji: 'pya' },
      { kana: 'ぴゅ', romaji: 'pyu' },
      { kana: 'ぴょ', romaji: 'pyo' }
    ]
  }
]

// 状态管理
const totalQuestions = 10
const currentIndex = ref(0)
const lives = ref(3)
const questions = ref([])
const selectedAnswer = ref(null)
const showResult = ref(false)
const showCompleteDialog = ref(false)
const correctCount = ref(0)

// 当前问题
const currentQuestion = computed(() => questions.value[currentIndex.value])

// 生成练习题
const generateQuestions = () => {
  const questionTypes = ['kanaToRomaji', 'romajiToKana']
  const generatedQuestions = []

  for (let i = 0; i < totalQuestions; i++) {
    const type = questionTypes[Math.floor(Math.random() * questionTypes.length)]
    const question = generateQuestion(type)
    generatedQuestions.push(question)
  }

  questions.value = generatedQuestions
}

// 生成单个问题
const generateQuestion = (type) => {
  // 根据 mode 获取可用的假名数据
  let availableKana
  switch (props.mode) {
    case 'seion':
      availableKana = hiraganaRows
      break
    case 'dakuon':
      availableKana = dakuonRows
      break
    case 'youon':
      availableKana = youonRows
      break
    default:
      availableKana = [...hiraganaRows, ...dakuonRows, ...youonRows]
  }
  
  // 随机选择一个假名
  const row = availableKana[Math.floor(Math.random() * availableKana.length)]
  const char = row.chars[Math.floor(Math.random() * row.chars.length)]
  
  // 生成错误选项
  const wrongOptions = availableKana
    .flatMap(r => r.chars)
    .filter(c => c.kana !== char.kana && c.romaji !== char.romaji)
    .sort(() => Math.random() - 0.5)
    .slice(0, 3)

  const baseQuestion = {
    type,
    answer: type === 'kanaToRomaji' ? char.romaji : char.kana,
    prompt: getPromptByType(type),
    question: type === 'kanaToRomaji' ? char.kana : char.romaji,
  }

  // 生成选项
  const options = [
    ...(type === 'kanaToRomaji' 
      ? wrongOptions.map(c => ({ id: c.romaji, value: c.romaji, display: c.romaji }))
      : wrongOptions.map(c => ({ id: c.kana, value: c.kana, display: c.kana }))
    ),
    {
      id: baseQuestion.answer,
      value: baseQuestion.answer,
      display: baseQuestion.answer
    }
  ].sort(() => Math.random() - 0.5)

  return {
    ...baseQuestion,
    options
  }
}

const getPromptByType = (type) => {
  switch (type) {
    case 'kanaToRomaji':
      return '选择假名的读音'
    case 'romajiToKana':
      return '选择正确的假名'
    default:
      return ''
  }
}

// 选择答案
const selectAnswer = (answer) => {
  if (!showResult.value) {
    selectedAnswer.value = answer
  }
}

// 检查答案
const checkAnswer = () => {
  showResult.value = true
  
  if (selectedAnswer.value === currentQuestion.value.answer) {
    correctCount.value++
    ElMessage.success('回答正确！')
  } else {
    lives.value--
    ElMessage.error(`回答错误，正确答案是：${currentQuestion.value.answer}`)
  }

  setTimeout(() => {
    if (lives.value === 0 || currentIndex.value === totalQuestions - 1) {
      showCompleteDialog.value = true
    } else {
      nextQuestion()
    }
  }, 1500)
}

// 下一题
const nextQuestion = () => {
  currentIndex.value++
  selectedAnswer.value = null
  showResult.value = false
}

// 重新开始
const restart = () => {
  currentIndex.value = 0
  lives.value = 3
  correctCount.value = 0
  selectedAnswer.value = null
  showResult.value = false
  showCompleteDialog.value = false
  generateQuestions()
}

// 完成练习
const finish = () => {
  emit('finish')
}

// 初始化
generateQuestions()
</script>

<style scoped>
.practice-wrapper {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

.status-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 40px;
}

.progress-dots {
  display: flex;
  gap: 8px;
}

.dot {
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background: #e4e4e4;
}

.dot.completed {
  background: #FF2D55;
}

.dot.current {
  background: #FF2D55;
  transform: scale(1.2);
}

.hearts {
  display: flex;
  gap: 8px;
}

.heart {
  color: #FF2D55;
  font-size: 24px;
}

.heart.lost {
  color: #e4e4e4;
}

.question-container {
  text-align: center;
  margin-bottom: 40px;
}

.question-type {
  font-size: 18px;
  color: #6e6e73;
  margin-bottom: 24px;
}

.question-display {
  font-size: 48px;
  font-weight: 600;
  margin-bottom: 32px;
}

.options-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
}

.option-card {
  background: #f5f5f7;
  border-radius: 12px;
  padding: 24px;
  font-size: 32px;
  cursor: pointer;
  transition: all 0.3s;
}

.option-card:hover {
  background: #e5e5ea;
}

.option-card.selected {
  background: #FF2D55;
  color: white;
}

.option-card.correct {
  background: #34C759;
  color: white;
}

.option-card.wrong {
  background: #FF3B30;
  color: white;
}

.action-bar {
  text-align: center;
}

.result-stats {
  display: grid;
  gap: 16px;
}

.stat-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 16px;
}

.stat-item .value {
  font-size: 24px;
  font-weight: 600;
  color: #FF2D55;
}
</style> 