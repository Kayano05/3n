<template>
  <div class="java-advanced">
    <h2>Java 高级特性</h2>
    <div class="content-section">
      <el-card class="lesson-card">
        <template #header>
          <div class="card-header">
            <h3>第一章：Java 集合框架</h3>
          </div>
        </template>
        <div class="lesson-content">
          <p>本章节将介绍：</p>
          <ul>
            <li>List、Set、Map 集合</li>
            <li>泛型编程</li>
            <li>多线程基础</li>
            <li>Java IO 流</li>
            <li>异常处理机制</li>
          </ul>
          <el-button type="primary" class="start-btn">开始学习</el-button>
        </div>
      </el-card>
    </div>
  </div>
</template>

<style scoped>
.java-advanced {
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