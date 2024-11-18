<template>
  <div class="terms">
    <div class="hero-section">
      <h1>使用条款</h1>
      <p class="subtitle">请仔细阅读以下条款</p>
    </div>

    <div class="content-section">
      <div class="terms-content">
        <section class="terms-section">
          <h2>1. 接受条款</h2>
          <p>欢迎使用 NoviceNest 平台。通过访问和使用本网站，您同意遵守这些使用条款。如果您不同意这些条款，请不要使用本网站。</p>
        </section>

        <section class="terms-section">
          <h2>2. 知识产权</h2>
          <p>本网站的所有内容，包括但不限于文本、图像、代码示例、视频等，均受版权法保护。未经明确许可，不得复制、分发或用于商业目的。</p>
          <ul>
            <li>课程内容仅供个人学习使用</li>
            <li>禁止传播或分享账号</li>
            <li>禁止对课程内容进行录制或分发</li>
          </ul>
        </section>

        <section class="terms-section">
          <h2>3. 用户责任</h2>
          <p>用户在使用本平台时应当：</p>
          <ul>
            <li>遵守相关法律法规</li>
            <li>保护账号安全</li>
            <li>不得发布违法或不当内容</li>
            <li>尊重其他用户的权利</li>
          </ul>
        </section>

        <section class="terms-section">
          <h2>4. 服务变更</h2>
          <p>我们保留随时修改或终止服务的权利，修改后的条款将在网站上公布。继续使用本网站则视为接受修改后的条款。</p>
        </section>

        <section class="terms-section">
          <h2>5. 免责声明</h2>
          <p>本平台提供的内容仅供学习参考，不构成任何形式的保证。对于使用本平台产生的任何直接或间接损失，本平台不承担责任。</p>
        </section>
      </div>

      <div class="terms-sidebar">
        <div class="quick-links">
          <h3>快速导航</h3>
          <ul>
            <li><a href="#" @click.prevent="scrollTo('1')">接受条款</a></li>
            <li><a href="#" @click.prevent="scrollTo('2')">知识产权</a></li>
            <li><a href="#" @click.prevent="scrollTo('3')">用户责任</a></li>
            <li><a href="#" @click.prevent="scrollTo('4')">服务变更</a></li>
            <li><a href="#" @click.prevent="scrollTo('5')">免责声明</a></li>
          </ul>
        </div>

        <div class="contact-support">
          <h3>需要帮助？</h3>
          <p>如果您对使用条款有任何疑问，请联系我们的支持团队。</p>
          <el-button type="primary" round>联系支持</el-button>
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
.terms {
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

.terms-content {
  background: #fff;
  border-radius: 20px;
  padding: 40px;
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.04);
}

.terms-section {
  margin-bottom: 40px;
}

.terms-section:last-child {
  margin-bottom: 0;
}

.terms-section h2 {
  font-size: 24px;
  color: #1d1d1f;
  margin-bottom: 20px;
  font-weight: 600;
}

.terms-section p {
  color: #424245;
  line-height: 1.6;
  margin-bottom: 16px;
  font-size: 16px;
}

.terms-section ul {
  list-style: disc;
  padding-left: 24px;
  margin-bottom: 16px;
}

.terms-section li {
  color: #424245;
  margin-bottom: 8px;
  line-height: 1.6;
}

.terms-sidebar {
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
  .terms {
    padding: 40px 20px;
  }

  .hero-section h1 {
    font-size: 36px;
  }

  .content-section {
    grid-template-columns: 1fr;
  }

  .terms-content {
    padding: 24px;
  }

  .terms-section h2 {
    font-size: 20px;
  }
}
</style> 