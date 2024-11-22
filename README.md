# NoviceNest

简单、优雅的在线学习平台。

## 概览

NoviceNest 提供直观的用户体验和强大的学习工具，帮助您轻松开启学习之旅。

### 精心设计的用户界面

我们注重每一个细节，从字体到交互，都经过精心打磨，为您带来流畅优雅的使用体验。

### 安全可靠的账号体系

采用行业标准的安全措施，保护您的账号安全。支持邮箱验证码注册、安全登录，让您安心使用。

## 技术规格

### 前端设计

#### 登录页面
优雅的设计，简洁的界面。
```javascript
// Login.vue
handleLogin() {
    // 智能的登录处理
    // 实时的状态反馈
    // 流畅的动画过渡
}
```

#### 注册流程
简单三步，轻松注册。
```javascript
// Register.vue
sendVerificationCode() {
    // 智能的邮箱验证
    // 实时的倒计时反馈
}

submitForm() {
    // 流畅的表单验证
    // 即时的注册响应
}
```

### 后端架构

#### 认证控制
安全可靠的身份验证。
```java
// AuthController.java
@PostMapping("/auth/send-code")    // 发送验证码
@PostMapping("/auth/register")     // 注册新用户
@PostMapping("/auth/login")        // 安全登录
```

#### 用户服务
智能的用户管理。
```java
// UserService.java
public User register()      // 新用户注册
public LoginResponse login() // 用户登录
```

#### 验证服务
可靠的验证系统。
```java
// VerificationService.java
generateAndSendCode()   // 生成验证码
verifyCode()           // 验证处理
```

## 系统要求

### 开发环境

#### 前端
- Node.js 16 或更高版本
- 现代浏览器

#### 后端
- JDK 17
- MySQL 8.0

## 开始使用

### 前端
```bash
npm install
npm run serve
```

### 后端
```bash
mvn clean install
mvn spring-boot:run
```

## 设计规范

### 色彩系统
- 主色：#007AFF
- 背景：#FFFFFF
- 文字：#1D1D1F
- 次要文字：#86868B

### 字体规范
- 系统字体：-apple-system
- 标题：SF Pro Display
- 正文：SF Pro Text

### 间距规范
- 大间距：40px
- 中间距：24px
- 小间距：16px

## 了解更多

### 联系我们
Kayano04@outlook.jp

### 版权信息
© 2024 NoviceNest. 保留所有权利。

---

###### 设计与开发：NoviceNest Team
