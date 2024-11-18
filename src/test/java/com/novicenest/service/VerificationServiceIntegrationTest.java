package com.novicenest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class VerificationServiceIntegrationTest {

    @Autowired
    private VerificationService verificationService;

    @Test
    void testRealEmailSending() throws MessagingException {
        String testEmail = "1250585873@qq.com";  // 替换为你的测试邮箱
        
        // 发送验证码
        String code = verificationService.generateAndSendCode(testEmail);
        
        // 验证生成的验证码
        assertNotNull(code);
        assertTrue(code.matches("\\d{6}"));
        
        // 验证验证码是否有效
        assertTrue(verificationService.verifyCode(testEmail, code));
    }
} 