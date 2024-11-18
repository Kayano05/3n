<template>
  <div class="hiragana">
    <div class="hero-section">
      <h1>五十音图</h1>
      <p class="subtitle">日语基础假名学习</p>
    </div>

    <div class="content-section">
      <template v-if="isPracticing">
        <HiraganaPractice 
          :mode="practiceMode"
          @finish="isPracticing = false"
        />
      </template>
      <template v-else>
        <el-row :gutter="30">
          <el-col :span="18">
            <div class="main-content">
              <div class="section-title">
                <h2>平假名</h2>
                <p>基础五十音图</p>
              </div>

              <div class="kana-grid">
                <div v-for="row in hiraganaRows" :key="row.vowel" class="kana-row">
                  <div v-for="char in row.chars" 
                       :key="char.kana" 
                       class="kana-card"
                       @click="playAudio(char.audio)"
                  >
                    <div class="kana">{{ char.kana }}</div>
                    <div class="romaji">{{ char.romaji }}</div>
                  </div>
                </div>
              </div>

              <div class="section-title">
                <h2>浊音与半浊音</h2>
                <p>变化音</p>
              </div>

              <div class="kana-grid">
                <div v-for="row in dakuonRows" :key="row.vowel" class="kana-row">
                  <div v-for="char in row.chars" 
                       :key="char.kana" 
                       class="kana-card"
                       @click="playAudio(char.audio)"
                  >
                    <div class="kana">{{ char.kana }}</div>
                    <div class="romaji">{{ char.romaji }}</div>
                  </div>
                </div>
              </div>

              <div class="section-title">
                <h2>拗音</h2>
                <p>组合音</p>
              </div>

              <div class="kana-grid">
                <div v-for="row in youonRows" :key="row.vowel" class="kana-row">
                  <div v-for="char in row.chars" 
                       :key="char.kana" 
                       class="kana-card"
                       @click="playAudio(char.audio)"
                  >
                    <div class="kana">{{ char.kana }}</div>
                    <div class="romaji">{{ char.romaji }}</div>
                  </div>
                </div>
              </div>
            </div>
          </el-col>

          <el-col :span="6">
            <div class="sidebar">
              <div class="practice-section">
                <div class="practice-header">
                  <h3>练习模式</h3>
                  <p class="practice-desc">选择你想要练习的假名类型</p>
                </div>
                
                <div class="mode-cards">
                  <div 
                    v-for="mode in practiceModes" 
                    :key="mode.value"
                    :class="['mode-card', { active: practiceMode === mode.value }]"
                    @click="practiceMode = mode.value"
                  >
                    <div class="mode-icon">
                      <el-icon><component :is="mode.icon" /></el-icon>
                    </div>
                    <div class="mode-info">
                      <span class="mode-name">{{ mode.name }}</span>
                      <span class="mode-count">{{ mode.count }}个假名</span>
                    </div>
                    <div class="mode-check">
                      <el-icon><Check /></el-icon>
                    </div>
                  </div>
                </div>

                <div class="practice-actions">
                  <el-button 
                    type="primary" 
                    class="start-btn"
                    size="large"
                    @click="startPractice"
                  >
                    开始练习
                    <el-icon class="el-icon--right"><CaretRight /></el-icon>
                  </el-button>

                  <el-button 
                    class="stats-btn"
                    size="large"
                    @click="showStats"
                  >
                    查看学习记录
                    <el-icon class="el-icon--right"><DataLine /></el-icon>
                  </el-button>
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
      </template>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import HiraganaPractice from '@/components/japanese/HiraganaPractice.vue'
import { Check, CaretRight, DataLine } from '@element-plus/icons-vue'

const practiceMode = ref('all')
const isPracticing = ref(false)

const hiraganaRows = ref([
  {
    vowel: 'a',
    chars: [
      { kana: 'あ', romaji: 'a', audio: 'a.mp3' },
      { kana: 'い', romaji: 'i', audio: 'i.mp3' },
      { kana: 'う', romaji: 'u', audio: 'u.mp3' },
      { kana: 'え', romaji: 'e', audio: 'e.mp3' },
      { kana: 'お', romaji: 'o', audio: 'o.mp3' }
    ]
  },
  {
    vowel: 'ka',
    chars: [
      { kana: 'か', romaji: 'ka', audio: 'ka.mp3' },
      { kana: 'き', romaji: 'ki', audio: 'ki.mp3' },
      { kana: 'く', romaji: 'ku', audio: 'ku.mp3' },
      { kana: 'け', romaji: 'ke', audio: 'ke.mp3' },
      { kana: 'こ', romaji: 'ko', audio: 'ko.mp3' }
    ]
  },
  {
    vowel: 'sa',
    chars: [
      { kana: 'さ', romaji: 'sa', audio: 'sa.mp3' },
      { kana: 'し', romaji: 'shi', audio: 'shi.mp3' },
      { kana: 'す', romaji: 'su', audio: 'su.mp3' },
      { kana: 'せ', romaji: 'se', audio: 'se.mp3' },
      { kana: 'そ', romaji: 'so', audio: 'so.mp3' }
    ]
  },
  {
    vowel: 'ta',
    chars: [
      { kana: 'た', romaji: 'ta', audio: 'ta.mp3' },
      { kana: 'ち', romaji: 'chi', audio: 'chi.mp3' },
      { kana: 'つ', romaji: 'tsu', audio: 'tsu.mp3' },
      { kana: 'て', romaji: 'te', audio: 'te.mp3' },
      { kana: 'と', romaji: 'to', audio: 'to.mp3' }
    ]
  },
  {
    vowel: 'na',
    chars: [
      { kana: 'な', romaji: 'na', audio: 'na.mp3' },
      { kana: 'に', romaji: 'ni', audio: 'ni.mp3' },
      { kana: 'ぬ', romaji: 'nu', audio: 'nu.mp3' },
      { kana: 'ね', romaji: 'ne', audio: 'ne.mp3' },
      { kana: 'の', romaji: 'no', audio: 'no.mp3' }
    ]
  },
  {
    vowel: 'ha',
    chars: [
      { kana: 'は', romaji: 'ha', audio: 'ha.mp3' },
      { kana: 'ひ', romaji: 'hi', audio: 'hi.mp3' },
      { kana: 'ふ', romaji: 'fu', audio: 'fu.mp3' },
      { kana: 'へ', romaji: 'he', audio: 'he.mp3' },
      { kana: 'ほ', romaji: 'ho', audio: 'ho.mp3' }
    ]
  },
  {
    vowel: 'ma',
    chars: [
      { kana: 'ま', romaji: 'ma', audio: 'ma.mp3' },
      { kana: 'み', romaji: 'mi', audio: 'mi.mp3' },
      { kana: 'む', romaji: 'mu', audio: 'mu.mp3' },
      { kana: 'め', romaji: 'me', audio: 'me.mp3' },
      { kana: 'も', romaji: 'mo', audio: 'mo.mp3' }
    ]
  },
  {
    vowel: 'ya',
    chars: [
      { kana: 'や', romaji: 'ya', audio: 'ya.mp3' },
      { kana: 'ゆ', romaji: 'yu', audio: 'yu.mp3' },
      { kana: 'よ', romaji: 'yo', audio: 'yo.mp3' }
    ]
  },
  {
    vowel: 'ra',
    chars: [
      { kana: 'ら', romaji: 'ra', audio: 'ra.mp3' },
      { kana: 'り', romaji: 'ri', audio: 'ri.mp3' },
      { kana: 'る', romaji: 'ru', audio: 'ru.mp3' },
      { kana: 'れ', romaji: 're', audio: 're.mp3' },
      { kana: 'ろ', romaji: 'ro', audio: 'ro.mp3' }
    ]
  },
  {
    vowel: 'wa',
    chars: [
      { kana: 'わ', romaji: 'wa', audio: 'wa.mp3' },
      { kana: 'を', romaji: 'wo', audio: 'wo.mp3' },
      { kana: 'ん', romaji: 'n', audio: 'n.mp3' }
    ]
  }
])

const dakuonRows = ref([
  {
    vowel: 'ga',
    chars: [
      { kana: 'が', romaji: 'ga', audio: 'ga.mp3' },
      { kana: 'ぎ', romaji: 'gi', audio: 'gi.mp3' },
      { kana: 'ぐ', romaji: 'gu', audio: 'gu.mp3' },
      { kana: 'げ', romaji: 'ge', audio: 'ge.mp3' },
      { kana: 'ご', romaji: 'go', audio: 'go.mp3' }
    ]
  },
  {
    vowel: 'za',
    chars: [
      { kana: 'ざ', romaji: 'za', audio: 'za.mp3' },
      { kana: 'じ', romaji: 'ji', audio: 'ji.mp3' },
      { kana: 'ず', romaji: 'zu', audio: 'zu.mp3' },
      { kana: 'ぜ', romaji: 'ze', audio: 'ze.mp3' },
      { kana: 'ぞ', romaji: 'zo', audio: 'zo.mp3' }
    ]
  },
  {
    vowel: 'da',
    chars: [
      { kana: 'だ', romaji: 'da', audio: 'da.mp3' },
      { kana: 'ぢ', romaji: 'ji', audio: 'ji2.mp3' },
      { kana: 'づ', romaji: 'zu', audio: 'zu2.mp3' },
      { kana: 'で', romaji: 'de', audio: 'de.mp3' },
      { kana: 'ど', romaji: 'do', audio: 'do.mp3' }
    ]
  },
  {
    vowel: 'ba',
    chars: [
      { kana: 'ば', romaji: 'ba', audio: 'ba.mp3' },
      { kana: 'び', romaji: 'bi', audio: 'bi.mp3' },
      { kana: 'ぶ', romaji: 'bu', audio: 'bu.mp3' },
      { kana: 'べ', romaji: 'be', audio: 'be.mp3' },
      { kana: 'ぼ', romaji: 'bo', audio: 'bo.mp3' }
    ]
  },
  {
    vowel: 'pa',
    chars: [
      { kana: 'ぱ', romaji: 'pa', audio: 'pa.mp3' },
      { kana: 'ぴ', romaji: 'pi', audio: 'pi.mp3' },
      { kana: 'ぷ', romaji: 'pu', audio: 'pu.mp3' },
      { kana: 'ぺ', romaji: 'pe', audio: 'pe.mp3' },
      { kana: 'ぽ', romaji: 'po', audio: 'po.mp3' }
    ]
  }
])

const youonRows = ref([
  {
    vowel: 'kya',
    chars: [
      { kana: 'きゃ', romaji: 'kya', audio: 'kya.mp3' },
      { kana: 'きゅ', romaji: 'kyu', audio: 'kyu.mp3' },
      { kana: 'きょ', romaji: 'kyo', audio: 'kyo.mp3' }
    ]
  },
  {
    vowel: 'sha',
    chars: [
      { kana: 'しゃ', romaji: 'sha', audio: 'sha.mp3' },
      { kana: 'しゅ', romaji: 'shu', audio: 'shu.mp3' },
      { kana: 'しょ', romaji: 'sho', audio: 'sho.mp3' }
    ]
  },
  {
    vowel: 'cha',
    chars: [
      { kana: 'ちゃ', romaji: 'cha', audio: 'cha.mp3' },
      { kana: 'ちゅ', romaji: 'chu', audio: 'chu.mp3' },
      { kana: 'ちょ', romaji: 'cho', audio: 'cho.mp3' }
    ]
  },
  {
    vowel: 'nya',
    chars: [
      { kana: 'にゃ', romaji: 'nya', audio: 'nya.mp3' },
      { kana: 'にゅ', romaji: 'nyu', audio: 'nyu.mp3' },
      { kana: 'にょ', romaji: 'nyo', audio: 'nyo.mp3' }
    ]
  },
  {
    vowel: 'hya',
    chars: [
      { kana: 'ひゃ', romaji: 'hya', audio: 'hya.mp3' },
      { kana: 'ひゅ', romaji: 'hyu', audio: 'hyu.mp3' },
      { kana: 'ひょ', romaji: 'hyo', audio: 'hyo.mp3' }
    ]
  },
  {
    vowel: 'mya',
    chars: [
      { kana: 'みゃ', romaji: 'mya', audio: 'mya.mp3' },
      { kana: 'みゅ', romaji: 'myu', audio: 'myu.mp3' },
      { kana: 'みょ', romaji: 'myo', audio: 'myo.mp3' }
    ]
  },
  {
    vowel: 'rya',
    chars: [
      { kana: 'りゃ', romaji: 'rya', audio: 'rya.mp3' },
      { kana: 'りゅ', romaji: 'ryu', audio: 'ryu.mp3' },
      { kana: 'りょ', romaji: 'ryo', audio: 'ryo.mp3' }
    ]
  },
  {
    vowel: 'gya',
    chars: [
      { kana: 'ぎゃ', romaji: 'gya', audio: 'gya.mp3' },
      { kana: 'ぎゅ', romaji: 'gyu', audio: 'gyu.mp3' },
      { kana: 'ぎょ', romaji: 'gyo', audio: 'gyo.mp3' }
    ]
  },
  {
    vowel: 'ja',
    chars: [
      { kana: 'じゃ', romaji: 'ja', audio: 'ja.mp3' },
      { kana: 'じゅ', romaji: 'ju', audio: 'ju.mp3' },
      { kana: 'じょ', romaji: 'jo', audio: 'jo.mp3' }
    ]
  },
  {
    vowel: 'bya',
    chars: [
      { kana: 'びゃ', romaji: 'bya', audio: 'bya.mp3' },
      { kana: 'びゅ', romaji: 'byu', audio: 'byu.mp3' },
      { kana: 'びょ', romaji: 'byo', audio: 'byo.mp3' }
    ]
  },
  {
    vowel: 'pya',
    chars: [
      { kana: 'ぴゃ', romaji: 'pya', audio: 'pya.mp3' },
      { kana: 'ぴゅ', romaji: 'pyu', audio: 'pyu.mp3' },
      { kana: 'ぴょ', romaji: 'pyo', audio: 'pyo.mp3' }
    ]
  }
])

const playAudio = (audioFile) => {
  console.log('Playing:', audioFile)
}

const startPractice = () => {
  isPracticing.value = true
}

const showStats = () => {
  console.log('Showing learning statistics')
}

const practiceModes = [
  {
    name: '全部假名',
    value: 'all',
    icon: 'Collection',
    count: computed(() => 
      hiraganaRows.value.reduce((acc, row) => acc + row.chars.length, 0) +
      dakuonRows.value.reduce((acc, row) => acc + row.chars.length, 0) +
      youonRows.value.reduce((acc, row) => acc + row.chars.length, 0)
    )
  },
  {
    name: '清音',
    value: 'seion',
    icon: 'Sunrise',
    count: computed(() => 
      hiraganaRows.value.reduce((acc, row) => acc + row.chars.length, 0)
    )
  },
  {
    name: '浊音',
    value: 'dakuon',
    icon: 'MoonNight',
    count: computed(() => 
      dakuonRows.value.reduce((acc, row) => acc + row.chars.length, 0)
    )
  },
  {
    name: '拗音',
    value: 'youon',
    icon: 'Connection',
    count: computed(() => 
      youonRows.value.reduce((acc, row) => acc + row.chars.length, 0)
    )
  }
]
</script>

<style scoped>
.hiragana {
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

.kana-grid {
  margin-bottom: 40px;
}

.kana-row {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 20px;
  margin-bottom: 20px;
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

.practice-section {
  background: #fff;
  border-radius: 20px;
  padding: 24px;
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.04);
}

.practice-header {
  margin-bottom: 24px;
}

.practice-header h3 {
  font-size: 20px;
  font-weight: 600;
  color: #1d1d1f;
  margin-bottom: 8px;
}

.practice-desc {
  font-size: 14px;
  color: #6e6e73;
}

.mode-cards {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-bottom: 24px;
}

.mode-card {
  display: flex;
  align-items: center;
  padding: 16px;
  background: #f5f5f7;
  border-radius: 12px;
  cursor: pointer;
  transition: all 0.3s ease;
  border: 2px solid transparent;
}

.mode-card:hover {
  background: #e5e5ea;
}

.mode-card.active {
  background: #fff;
  border-color: #FF2D55;
}

.mode-icon {
  width: 40px;
  height: 40px;
  border-radius: 10px;
  background: #FF2D55;
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  margin-right: 16px;
}

.mode-info {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.mode-name {
  font-size: 16px;
  font-weight: 500;
  color: #1d1d1f;
  margin-bottom: 4px;
}

.mode-count {
  font-size: 13px;
  color: #6e6e73;
}

.mode-check {
  color: #FF2D55;
  font-size: 20px;
  opacity: 0;
  transition: opacity 0.3s ease;
}

.mode-card.active .mode-check {
  opacity: 1;
}

.practice-actions {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.start-btn,
.stats-btn {
  width: 100%;
  height: 48px;
  border-radius: 12px;
  font-size: 16px;
  font-weight: 500;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
}

.start-btn {
  background: #FF2D55;
  border-color: #FF2D55;
}

.start-btn:hover {
  background: #FF1F4B;
  border-color: #FF1F4B;
}

.stats-btn {
  color: #1d1d1f;
  border-color: #e4e4e4;
}

.stats-btn:hover {
  color: #FF2D55;
  border-color: #FF2D55;
  background: #fff;
}

@media (max-width: 768px) {
  .hiragana {
    padding: 20px;
  }

  .hero-section h1 {
    font-size: 36px;
  }

  .main-content {
    padding: 20px;
  }

  .kana-row {
    grid-template-columns: repeat(3, 1fr);
  }

  .practice-section {
    margin-top: 24px;
  }
}

.kana-grid:last-child .kana-row {
  grid-template-columns: repeat(3, 1fr);
  justify-content: center;
  padding: 0 20%;
}

@media (max-width: 768px) {
  .kana-grid:last-child .kana-row {
    padding: 0;
  }
}
</style> 