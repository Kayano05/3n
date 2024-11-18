<template>
  <div class="about-us">
    <div class="hero-section">
      <h1>关于我们</h1>
      <p class="subtitle">让学习更简单，让成长更轻松</p>
    </div>

    <div class="content-section">
      <div class="mission">
        <h2>我们的使命</h2>
        <p>NoviceNest 致力于为学习者提供优质的编程和日语学习资源，打造一个友好、专业的学习平台。我们相信，通过系统化的课程内容和科学的学习方法，每个人都能实现自己的学习目标。</p>
      </div>

      <div class="values">
        <h2>核心价值观</h2>
        <div class="values-grid">
          <div class="value-card">
            <el-icon><Star /></el-icon>
            <h3>专业</h3>
            <p>提供高质量的课程内容和专业的技术指导</p>
          </div>
          <div class="value-card">
            <el-icon><User /></el-icon>
            <h3>以学习者为中心</h3>
            <p>关注学习者需求，优化学习体验</p>
          </div>
          <div class="value-card">
            <el-icon><Connection /></el-icon>
            <h3>持续创新</h3>
            <p>不断改进教学方法和学习内容</p>
          </div>
        </div>
      </div>

      <div class="contact">
        <h2>联系我们</h2>
        <p>如果您有任何问题或建议，欢迎通过以下方式与我们联系：</p>
        <div class="contact-info">
          <div class="contact-item">
            <el-icon><Message /></el-icon>
            <span>Kayano04@outlook.jp</span>
          </div>
          <div class="contact-item">
            <el-icon><Location /></el-icon>
            <span>中国四川乐山</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.about-us {
  padding: 80px 40px;
  max-width: 1200px;
  margin: 0 auto;
}

.hero-section {
  text-align: center;
  margin-bottom: 80px;
}

.hero-section h1 {
  font-size: 56px;
  font-weight: 600;
  background: linear-gradient(120deg, #007AFF, #00B4DB);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin-bottom: 16px;
}

.subtitle {
  font-size: 24px;
  color: #1d1d1f;
  font-weight: 500;
}

.content-section > div {
  margin-bottom: 80px;
}

.content-section h2 {
  font-size: 32px;
  color: #1d1d1f;
  margin-bottom: 24px;
  font-weight: 600;
}

.mission p {
  font-size: 18px;
  color: #6e6e73;
  line-height: 1.6;
  max-width: 800px;
}

.values-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 30px;
  margin-top: 40px;
}

.value-card {
  background: #fff;
  padding: 40px;
  border-radius: 20px;
  text-align: center;
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.04);
  transition: transform 0.3s ease;
}

.value-card:hover {
  transform: translateY(-5px);
}

.value-card .el-icon {
  font-size: 40px;
  color: #007AFF;
  margin-bottom: 20px;
}

.value-card h3 {
  font-size: 20px;
  color: #1d1d1f;
  margin-bottom: 12px;
}

.value-card p {
  color: #6e6e73;
  line-height: 1.5;
}

.contact p {
  font-size: 18px;
  color: #6e6e73;
  margin-bottom: 30px;
}

.contact-info {
  display: flex;
  gap: 40px;
}

.contact-item {
  display: flex;
  align-items: center;
  gap: 12px;
  color: #1d1d1f;
  font-size: 16px;
}

.contact-item .el-icon {
  color: #007AFF;
  font-size: 20px;
}

@media (max-width: 768px) {
  .about-us {
    padding: 40px 20px;
  }

  .hero-section h1 {
    font-size: 42px;
  }

  .subtitle {
    font-size: 20px;
  }

  .content-section h2 {
    font-size: 28px;
  }

  .contact-info {
    flex-direction: column;
    gap: 20px;
  }
}
</style> 