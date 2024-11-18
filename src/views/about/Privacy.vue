<template>
  <div class="privacy">
    <div class="hero-section">
      <h1>隐私政策</h1>
      <p class="subtitle">我们如何保护您的个人信息</p>
    </div>

    <div class="content-section">
      <div class="privacy-content">
        <section class="privacy-section">
          <h2>1. 信息收集</h2>
          <p>我们仅收集必要的个人信息用于提供服务：</p>
          <ul>
            <li>账号信息（用户名、邮箱）</li>
            <li>学习记录和进度</li>
            <li>系统日志信息</li>
          </ul>
        </section>

        <section class="privacy-section">
          <h2>2. 信息使用</h2>
          <p>收集的信息将用于：</p>
          <ul>
            <li>提供个性化学习体验</li>
            <li>改进我们的服务</li>
            <li>发送必要的通知</li>
            <li>保障账号安全</li>
          </ul>
        </section>

        <section class="privacy-section">
          <h2>3. 信息保护</h2>
          <p>我们采取多重措施保护您的信息：</p>
          <ul>
            <li>数据加密存储</li>
            <li>访问权限控制</li>
            <li>安全传输协议</li>
            <li>定期安全审计</li>
          </ul>
        </section>

        <section class="privacy-section">
          <h2>4. Cookie 使用</h2>
          <p>我们使用 Cookie 技术改善用户体验：</p>
          <ul>
            <li>保持登录状态</li>
            <li>记住用户偏好</li>
            <li>提供个性化推荐</li>
          </ul>
        </section>

        <section class="privacy-section">
          <h2>5. 用户权利</h2>
          <p>您对个人信息拥有以下权利：</p>
          <ul>
            <li>访问和导出个人数据</li>
            <li>更正不准确信息</li>
            <li>删除个人信息</li>
            <li>撤回同意授权</li>
          </ul>
        </section>
      </div>

      <div class="privacy-sidebar">
        <div class="quick-links">
          <h3>快速导航</h3>
          <ul>
            <li><a href="#" @click.prevent="scrollTo('1')">信息收集</a></li>
            <li><a href="#" @click.prevent="scrollTo('2')">信息使用</a></li>
            <li><a href="#" @click.prevent="scrollTo('3')">信息保护</a></li>
            <li><a href="#" @click.prevent="scrollTo('4')">Cookie 使用</a></li>
            <li><a href="#" @click.prevent="scrollTo('5')">用户权利</a></li>
          </ul>
        </div>

        <div class="contact-support">
          <h3>隐私问题？</h3>
          <p>如果您对我们的隐私政策有任何疑问，请联系我们的隐私保护团队。</p>
          <el-button type="primary" round>联系我们</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
const scrollTo = (section) => {
  const element = document.querySelector(`h2:contains("${section}.")`)
  if (element) {
    element.scrollIntoView({ behavior: 'smooth' })
  }
}
</script>

<style scoped>
.privacy {
  padding: 80px 40px;
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
  color: #1d1d1f;
  font-weight: 500;
}

.content-section {
  display: grid;
  grid-template-columns: 1fr 300px;
  gap: 60px;
}

.privacy-content {
  background: #fff;
  border-radius: 20px;
  padding: 40px;
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.04);
}

.privacy-section {
  margin-bottom: 40px;
}

.privacy-section:last-child {
  margin-bottom: 0;
}

.privacy-section h2 {
  font-size: 24px;
  color: #1d1d1f;
  margin-bottom: 20px;
  font-weight: 600;
}

.privacy-section p {
  color: #424245;
  line-height: 1.6;
  margin-bottom: 16px;
  font-size: 16px;
}

.privacy-section ul {
  list-style: disc;
  padding-left: 24px;
  margin-bottom: 16px;
}

.privacy-section li {
  color: #424245;
  margin-bottom: 8px;
  line-height: 1.6;
}

.privacy-sidebar {
  position: sticky;
  top: 84px;
}

.quick-links {
  background: #fff;
  border-radius: 20px;
  padding: 24px;
  margin-bottom: 24px;
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.04);
}

.quick-links h3 {
  font-size: 18px;
  color: #1d1d1f;
  margin-bottom: 16px;
  font-weight: 600;
}

.quick-links ul {
  list-style: none;
  padding: 0;
}

.quick-links li {
  margin-bottom: 12px;
}

.quick-links a {
  color: #424245;
  text-decoration: none;
  font-size: 14px;
  transition: color 0.3s ease;
}

.quick-links a:hover {
  color: #007AFF;
}

.contact-support {
  background: #fff;
  border-radius: 20px;
  padding: 24px;
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.04);
  text-align: center;
}

.contact-support h3 {
  font-size: 18px;
  color: #1d1d1f;
  margin-bottom: 12px;
  font-weight: 600;
}

.contact-support p {
  color: #424245;
  margin-bottom: 20px;
  font-size: 14px;
  line-height: 1.5;
}

:deep(.el-button--primary) {
  background: #007AFF;
  border-color: #007AFF;
  width: 100%;
}

@media (max-width: 768px) {
  .privacy {
    padding: 40px 20px;
  }

  .hero-section h1 {
    font-size: 36px;
  }

  .content-section {
    grid-template-columns: 1fr;
  }

  .privacy-content {
    padding: 24px;
  }

  .privacy-section h2 {
    font-size: 20px;
  }
}
</style> 