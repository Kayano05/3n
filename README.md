# NoviceNest 项目说明文档

## 一、项目概述
NoviceNest 是一个在线学习平台，提供用户注册、登录等基础功能。

## 二、功能模块说明

### 2.1 前端页面

#### 2.1.1 登录页面 (Login.vue)
1. 页面路径：`src/views/auth/Login.vue`
2. 主要组件：
   - 邮箱输入框
   - 密码输入框
   - 登录按钮
   - 忘记密码链接
   - 注册账号链接
3. 核心函数：
   ```javascript
   handleLogin() {
     // 处理登录请求
     // 参数：email, password
     // 返回：用户信息和token
   }
   ```

#### 2.1.2 注册页面 (Register.vue)
1. 页面路径：`src/views/auth/Register.vue`
2. 主要组件：
   - 邮箱输入框
   - 验证码输入框（带倒计时）
   - 密码输入框
   - 确认密码输入框
   - 注册按钮
3. 核心函数：
   ```javascript
   sendVerificationCode() {
     // 发送验证码
     // 参数：email
     // 功能：邮箱验证、发送请求、倒计时
   }

   submitForm() {
     // 提交注册表单
     // 参数：email, verificationCode, password
     // 功能：表单验证、注册请求
   }
   ```

### 2.2 后端接口

#### 2.2.1 认证控制器 (AuthController.java)
1. 类路径：`src/main/java/com/novicenest/controller/AuthController.java`
2. API 接口：
   ```java
   @PostMapping("/auth/send-code")
   // 发送验证码
   // 请求参数：email
   // 返回：success, message

   @PostMapping("/auth/register")
   // 用户注册
   // 请求参数：email, verificationCode, password
   // 返回：user, success, message

   @PostMapping("/auth/login")
   // 用户登录
   // 请求参数：email, password
   // 返回：user, token, success, message
   ```

#### 2.2.2 用户服务 (UserService.java)
1. 类路径：`src/main/java/com/novicenest/service/UserService.java`
2. 主要方法：
   ```java
   public User register(String email, String password)
   // 用户注册
   // 功能：邮箱查重、密码加密、保存用户

   public LoginResponse login(String email, String password)
   // 用户登录
   // 功能：账号验证、密码验证、生成token
   ```

#### 2.2.3 验证服务 (VerificationService.java)
1. 类路径：`src/main/java/com/novicenest/service/VerificationService.java`
2. 主要方法：
   ```java
   public String generateAndSendCode(String email)
   // 生成并发送验证码
   // 功能：生成验证码、发送邮件、存储验证码

   public boolean verifyCode(String email, String code)
   // 验证码校验
   // 功能：验证码匹配、过期检查
   ```

## 三、数据模型

### 3.1 用户模型 (User.java)
```java
public class User {
    private Long id;           // 用户ID
    private String email;      // 邮箱
    private String password;   // 加密密码
}
```

### 3.2 验证码模型 (VerificationCode.java)
```java
private static class VerificationCode {
    private String code;       // 验证码
    private long timestamp;    // 创建时间戳
    private static final long EXPIRATION_TIME = 5 * 60 * 1000;  // 有效期5分钟
}
```

## 四、开发环境

### 4.1 前端环境
- Node.js v16+
- Vue.js 3
- Element Plus UI
- 开发命令：
  ```bash
  npm install
  npm run serve
  ```

### 4.2 后端环境
- JDK 17
- Spring Boot 2.7.x
- MySQL 8.0
- 开发命令：
  ```bash
  mvn clean install
  mvn spring-boot:run
  ```

## 五、作者信息
- 开发团队：NoviceNest Team
- 联系邮箱：Kayano04@outlook.jp

## 六、版权信息
© 2024 NoviceNest. All rights reserved.
