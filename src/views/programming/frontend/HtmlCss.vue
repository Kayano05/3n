<template>
  <div class="html-css">
    <div class="hero-section">
      <h1>HTML & CSS</h1>
      <p class="subtitle">网页开发的基础构建块</p>
    </div>

    <div class="content-section">
      <div class="chapter-grid">
        <el-card class="chapter-card" shadow="hover">
          <div class="chapter-content">
            <div class="chapter-header">
              <span class="chapter-number">01</span>
              <h3>HTML 基础</h3>
            </div>
            <div class="chapter-details">
              <ul>
                <li>
                  <el-icon><Check /></el-icon>
                  <span>HTML5 语义化标签</span>
                </li>
                <li>
                  <el-icon><Check /></el-icon>
                  <span>表单与输入控件</span>
                </li>
                <li>
                  <el-icon><Check /></el-icon>
                  <span>多媒体元素</span>
                </li>
                <li>
                  <el-icon><Check /></el-icon>
                  <span>SEO 最佳实践</span>
                </li>
                <li>
                  <el-icon><Check /></el-icon>
                  <span>无障碍开发</span>
                </li>
              </ul>
              <el-button type="primary" class="start-btn" round>
                开始学习
                <el-icon class="el-icon--right"><ArrowRight /></el-icon>
              </el-button>
            </div>
          </div>
        </el-card>

        <el-card class="chapter-card" shadow="hover">
          <div class="chapter-content">
            <div class="chapter-header">
              <span class="chapter-number">02</span>
              <h3>CSS 进阶</h3>
            </div>
            <div class="chapter-details">
              <ul>
                <li>
                  <el-icon><Check /></el-icon>
                  <span>Flexbox 布局</span>
                </li>
                <li>
                  <el-icon><Check /></el-icon>
                  <span>Grid 网格系统</span>
                </li>
                <li>
                  <el-icon><Check /></el-icon>
                  <span>响应式设计</span>
                </li>
                <li>
                  <el-icon><Check /></el-icon>
                  <span>动画与过渡</span>
                </li>
                <li>
                  <el-icon><Check /></el-icon>
                  <span>CSS 预处理器</span>
                </li>
              </ul>
              <el-button type="primary" class="start-btn" round>
                开始学习
                <el-icon class="el-icon--right"><ArrowRight /></el-icon>
              </el-button>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </div>
</template>

<style scoped>
.html-css {
  padding: 40px 20px;
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
  background: linear-gradient(120deg, #007AFF, #00B4DB);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin-bottom: 16px;
}

.subtitle {
  font-size: 24px;
  color: #6e6e73;
  font-weight: 500;
}

.chapter-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 30px;
  padding: 20px;
}

.chapter-card {
  border: none;
  border-radius: 20px;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  background: #fff;
}

.chapter-card:hover {
  transform: translateY(-5px);
}

.chapter-content {
  padding: 30px;
}

.chapter-header {
  margin-bottom: 30px;
}

.chapter-number {
  font-size: 14px;
  color: #007AFF;
  font-weight: 600;
  display: block;
  margin-bottom: 10px;
}

.chapter-header h3 {
  font-size: 24px;
  color: #1d1d1f;
  font-weight: 600;
  margin: 0;
}

.chapter-details ul {
  list-style: none;
  padding: 0;
  margin: 0 0 30px 0;
}

.chapter-details li {
  display: flex;
  align-items: center;
  gap: 10px;
  color: #6e6e73;
  margin-bottom: 15px;
  font-size: 16px;
}

.chapter-details .el-icon {
  color: #007AFF;
}

.start-btn {
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

:deep(.el-card) {
  border: none;
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.04);
}

:deep(.el-card.is-hover-shadow:hover) {
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.08);
}

@media (max-width: 768px) {
  .hero-section h1 {
    font-size: 36px;
  }

  .subtitle {
    font-size: 20px;
  }

  .chapter-content {
    padding: 20px;
  }
}
</style> 