# NoviceNest - 综合学习平台

NoviceNest 是一个专注于编程和日语学习的在线教育平台。平台采用现代化的技术栈，提供流畅的用户体验和丰富的学习资源。

## 功能特性

### 用户系统
- 邮箱注册与登录
  - 邮箱格式验证
  - 密码加密存储
  - 验证码邮件发送（5分钟有效期）
  - 自定义邮件模板
- JWT Token 认证
  - 登录状态维护
  - 接口安全认证
- 用户信息管理
  - 个人资料设置
  - 学习进度追踪

### 日语学习模块
- JLPT 考试备考
  - N1-N5 各级别考试资源
  - 真题解析
  - 考试技巧
- 平假名学习
  - 五十音图
  - 发音指导
  - 书写练习
- 日语语法
  - 基础语法讲解
  - 例句分析
  - 练习题
- 课程系统
  - 分级课程
  - 视频教学
  - 课后作业
- 会话练习
  - 日常对话
  - 情景会话
  - 发音纠正

### 编程学习模块
- Node.js 基础教程
  - 环境搭建
  - 基础语法
  - 异步编程
  - 模块系统
- Express 框架学习
  - 路由系统
  - 中间件
  - 错误处理
  - 数据库集成

## 技术架构

### 前端技术栈
- Vue.js 3.0
  - Composition API
  - Script Setup
- Element Plus UI 框架
  - 自定义主题
  - 响应式设计
- Vue Router 4.0
  - 路由守卫
  - 动态路由
- Pinia 状态管理
  - 用户状态
  - 学习进度状态
- Axios 请求处理
  - 请求拦截
  - 响应拦截
  - 统一错误处理

### 后端技术栈
- Spring Boot 2.7.x
  - RESTful API
  - 依赖注入
  - AOP 处理
- Spring Security
  - JWT 认证
  - 密码加密
  - 权限控制
- Java Mail Sender
  - 异步发送
  - HTML 模板
  - 验证码生成
- MySQL 8.0
  - 用户数据
  - 学习记录
  - 课程内容

## 开发环境搭建

### 前端开发环境
```bash
# 安装 Node.js (推荐 v16+)
node -v
npm -v

# 克隆项目
git clone https://github.com/your-username/novicenest.git
cd novicenest

# 安装依赖
npm install

# 启动开发服务器
npm run serve

# 生产环境构建
npm run build

# 代码检查
npm run lint
```

### 后端开发环境
```bash
# 安装 JDK 17
java -version

# 安装 Maven
mvn -v

# 配置数据库
mysql -u root -p
create database novicenest;

# 构建项目
mvn clean install

# 运行应用
mvn spring-boot:run
```

## 项目结构

### 前端目录结构
```
src/
├── assets/            # 静态资源
│   ├── images/       # 图片资源
│   └── styles/       # 全局样式
├── components/        # 公共组件
├── views/            # 页面组件
│   ├── auth/         # 认证相关
│   ├── japanese/     # 日语学习
│   ├── programming/  # 编程学习
│   └── about/        # 其他页面
├── router/           # 路由配置
├── store/            # Pinia 状态
├── utils/            # 工具函数
│   ├── request.js    # axios 封装
│   └── auth.js       # 认证相关
└── App.vue           # 根组件
```

### 后端目录结构
```
src/main/
├── java/com/novicenest/
│   ├── controller/   # 控制器层
│   ├── service/      # 业务逻辑层
│   ├── repository/   # 数据访问层
│   ├── entity/       # 实体类
│   ├── exception/    # 异常处理
│   ├── config/       # 配置类
│   └── util/         # 工具类
└── resources/
    ├── application.yml    # 应用配置
    └── templates/         # 邮件模板
```

## 部署说明
1. 前端部署
   - Nginx 配置
   - 静态资源优化
   - CDN 加速

2. 后端部署
   - JVM 配置优化
   - 数据库优化
   - 日志配置

## 贡献指南
1. Fork 项目
2. 创建特性分支
3. 提交代码
4. 发起 Pull Request

## 版本历史
- v1.0.0 (2024-01) - 初始版本
  - 基础用户系统
  - 日语学习模块
  - 编程学习模块

## 作者
- 开发团队：NoviceNest Team
- 联系邮箱：Kayano04@outlook.jp

## 许可证
© 2024 NoviceNest. All rights reserved.
