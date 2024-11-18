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
        
        // 验证请求数据
        if (request == null || request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            logger.error("Invalid request: email is null or empty");
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "邮箱地址不能为空");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            logger.info("Starting to generate and send verification code");
            String code = verificationService.generateAndSendCode(request.getEmail());
            logger.info("Successfully generated and sent verification code: {}", code);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "验证码已发送，请查收邮件");
            return ResponseEntity.ok(response);
        } catch (MessagingException e) {
            logger.error("Failed to send verification code: ", e);
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "发送验证码失败：" + e.getMessage());
            response.put("error", e.toString());
            response.put("stackTrace", e.getStackTrace());
            return ResponseEntity.status(500).body(response);
        } catch (Exception e) {
            logger.error("Unexpected error while sending verification code: ", e);
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "系统错误：" + e.getMessage());
            response.put("error", e.toString());
            response.put("stackTrace", e.getStackTrace());
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