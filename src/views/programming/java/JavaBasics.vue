<template>
  <div class="java-basics">
    <h2>Java 基础语法</h2>
    <div class="content-section">
      <el-card class="lesson-card">
        <template #header>
          <div class="card-header">
            <h3>第一章：Java 入门基础</h3>
          </div>
        </template>
        <div class="lesson-content">
          <p>本章节将介绍：</p>
          <ul>
            <li>Java 开发环境配置</li>
            <li>第一个 Java 程序</li>
            <li>基本数据类型</li>
            <li>变量与常量</li>
            <li>运算符</li>
          </ul>
          <el-button type="primary" class="start-btn">开始学习</el-button>
        </div>
      </el-card>
    </div>
  </div>
</template>

<style scoped>
.java-basics {
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