# NoviceNest - 综合学习平台

## 前端实现 (Vue.js)

### 登录页面 (Login.vue)
```javascript
// 主要功能函数
handleLogin()        // 处理登录逻辑
- 表单验证
- 发送登录请求
- 处理响应结果
- 保存用户状态
- 路由跳转
```

### 注册页面 (Register.vue)
```javascript
// 主要功能函数
sendVerificationCode()   // 发送验证码
- 邮箱格式验证
- 发送请求
- 倒计时处理
- 错误提示

submitForm()            // 提交注册
- 表单验证
- 验证码验证
- 密码确认
- 注册请求
- 结果处理
```

## 后端实现 (Spring Boot)

### 认证控制器 (AuthController.java)
```java
// API 端点
POST /auth/send-code    // 发送验证码
- 验证邮箱格式
- 调用验证服务
- 错误处理
- 响应封装

POST /auth/register     // 用户注册
- 验证码校验
- 用户注册
- 错误处理
- 响应封装

POST /auth/login        // 用户登录
- 登录验证
- Token 生成
- 错误处理
- 响应封装
```

### 用户服务 (UserService.java)
```java
// 核心方法
register()             // 用户注册
- 邮箱查重
- 密码加密
- 用户保存
- 异常处理

login()                // 用户登录
- 用户查找
- 密码验证
- Token 生成
- 响应封装
```

### 验证服务 (VerificationService.java)
```java
// 核心功能
generateAndSendCode()   // 生成并发送验证码
- 验证码生成
- 邮件内容生成
- 异步发送
- 验证码存储

verifyCode()           // 验证码校验
- 验证码获取
- 过期检查
- 验证码匹配
- 使用后清理
```

## 数据模型

### User 实体
```java
class User {
    Long id;           // 用户ID
    String email;      // 邮箱
    String password;   // 加密密码
    // 其他字段...
}
```

### 验证码模型
```java
class VerificationCode {
    String code;           // 验证码
    long timestamp;        // 创建时间
    static long EXPIRATION_TIME = 5 * 60 * 1000;  // 5分钟有效期
}
```

## 技术特点

### 前端特性
- Vue.js 3 + Element Plus
- 响应式表单验证
- 状态管理 (Pinia)
- 路由守卫
- 统一错误处理

### 后端特性
- Spring Boot 2.7.x
- JWT 认证
- 邮件服务
- 密码加密
- 统一异常处理

## 安装部署

### 前端部署
```bash
npm install
npm run serve    # 开发环境
npm run build    # 生产环境
```

### 后端部署
```bash
mvn clean install
mvn spring-boot:run
```

## 作者
- 开发团队：NoviceNest Team
- 联系邮箱：Kayano04@outlook.jp

## 许可证
© 2024 NoviceNest. All rights reserved.
