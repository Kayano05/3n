package com.novicenest.controller;

import com.novicenest.entity.User;
import com.novicenest.service.VerificationService;
import com.novicenest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private UserService userService;

    @PostMapping("/send-code")
    public ResponseEntity<?> sendVerificationCode(@RequestBody EmailRequest request) {
        logger.info("Received send-code request for email: {}", request.getEmail());
        
        // 验证邮箱格式
        String email = request.getEmail();
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "请输入正确的邮箱格式");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            logger.info("Starting to generate and send verification code");
            String code = verificationService.generateAndSendCode(email);
            logger.info("Successfully generated and sent verification code: {}", code);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "验证码已发送，请查收邮件");
            return ResponseEntity.ok(response);
        } catch (MessagingException e) {
            logger.error("Failed to send verification code: ", e);
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "发送验证码失败，请检查邮箱地址是否正确");
            return ResponseEntity.status(500).body(response);
        } catch (Exception e) {
            logger.error("Unexpected error while sending verification code: ", e);
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "系统错误，请稍后重试");
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        logger.info("Received register request for email: {}", request.getEmail());

        try {
            // 验证验证码
            if (!verificationService.verifyCode(request.getEmail(), request.getVerificationCode())) {
                logger.error("Invalid verification code for email: {}", request.getEmail());
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "验证码错误或已过期");
                return ResponseEntity.badRequest().body(response);
            }

            // 注册用户
            User user = userService.register(request.getEmail(), request.getPassword());
            logger.info("Successfully registered user: {}", user);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "注册成功");
            response.put("user", user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Registration failed: ", e);
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        logger.info("Received login request for email: {}", request.getEmail());
        
        try {
            UserService.LoginResponse loginResponse = userService.login(request.getEmail(), request.getPassword());
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "登录成功");
            response.put("user", loginResponse.getUser());
            response.put("token", loginResponse.getToken());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Login failed: ", e);
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(401).body(response);
        }
    }

    public String generateVerificationEmailContent(String code) {
        StringBuilder html = new StringBuilder();
        html.append("<div style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 40px 20px;\">");
        
        // Logo
        html.append("<div style=\"text-align: center; margin-bottom: 40px;\">")
            .append("<img src=\"https://your-domain.com/logo.png\" alt=\"NoviceNest\" style=\"width: 40px; height: 40px;\">")
            .append("</div>");
        
        // 标题
        html.append("<div style=\"margin-bottom: 30px; text-align: center;\">")
            .append("<h1 style=\"color: #1d1d1f; font-size: 24px; font-weight: 500; margin: 0 0 12px;\">验证你的邮箱地址</h1>")
            .append("<p style=\"color: #424245; font-size: 16px; line-height: 1.5; margin: 0;\">")
            .append("感谢你注册 NoviceNest。")
            .append("</p>")
            .append("</div>");
        
        // 验证码
        html.append("<div style=\"background-color: #f5f5f7; border-radius: 12px; padding: 20px; text-align: center; margin-bottom: 30px;\">")
            .append("<p style=\"color: #86868b; font-size: 14px; margin: 0 0 8px;\">你的验证码是</p>")
            .append("<span style=\"color: #1d1d1f; font-size: 32px; font-weight: 500; letter-spacing: 8px;\">")
            .append(code)
            .append("</span>")
            .append("</div>");
        
        // 提示文案
        html.append("<p style=\"color: #86868b; font-size: 14px; line-height: 1.5; text-align: center; margin: 0;\">")
            .append("验证码在 5 分钟内有效。<br>")
            .append("如果你没有请求此验证码，请忽略这封邮件。")
            .append("</p>");
        
        // 底部
        html.append("<div style=\"border-top: 1px solid #e5e5e7; margin-top: 40px; padding-top: 20px; text-align: center;\">")
            .append("<p style=\"color: #86868b; font-size: 12px; line-height: 1.5; margin: 0;\">")
            .append("此邮件由系统自动发送，请勿回复。<br>")
            .append("© 2024 NoviceNest. All rights reserved.")
            .append("</p>")
            .append("</div>");
        
        html.append("</div>");
        
        return html.toString();
    }

    public static class EmailRequest {
        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static class RegisterRequest {
        private String email;
        private String verificationCode;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getVerificationCode() {
            return verificationCode;
        }

        public void setVerificationCode(String verificationCode) {
            this.verificationCode = verificationCode;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
} 