<template>
  <div class="java-oop">
    <h2>Java 面向对象编程</h2>
    <div class="content-section">
      <el-card class="lesson-card">
        <template #header>
          <div class="card-header">
            <h3>第一章：类与对象</h3>
          </div>
        </template>
        <div class="lesson-content">
          <p>本章节将介绍：</p>
          <ul>
            <li>类的定义与使用</li>
            <li>对象的创建与初始化</li>
            <li>继承与多态</li>
            <li>接口与抽象类</li>
            <li>封装与访问控制</li>
          </ul>
          <el-button type="primary" class="start-btn">开始学习</el-button>
        </div>
      </el-card>
    </div>
  </div>
</template>

<style scoped>
.java-oop {
  padding: 20px;
}

.content-section {
  margin-top: 20px;
}

.lesson-card {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.lesson-content {
  padding: 10px 0;
}

.lesson-content ul {
  padding-left: 20px;
  margin: 15px 0;
}

.lesson-content li {
  margin: 8px 0;
  color: #666;
}

.start-btn {
  margin-top: 20px;
}
</style> 