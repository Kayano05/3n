package com.novicenest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VerificationService {
    private static final Logger logger = LoggerFactory.getLogger(VerificationService.class);

    @Autowired
    private JavaMailSender mailSender;

    private final Map<String, VerificationCode> verificationCodes = new ConcurrentHashMap<>();

    private static final String ACCOUNT_NOT_FOUND_MESSAGE = "该账号尚未注册，请先完成注册后再登录";

    public String generateAndSendCode(String email) throws MessagingException {
        String code = generateCode();
        verificationCodes.put(email, new VerificationCode(code));

        sendVerificationEmail(email, code);
        return code;
    }

    private String generateVerificationEmailContent(String code) {
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

    private void sendVerificationEmail(String email, String code) throws MessagingException {
        logger.info("Creating email message for: {}", email);
        
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        
        helper.setTo(email);
        helper.setFrom("1250585873@qq.com");
        helper.setSubject("NoviceNest 验证码");
        helper.setText(generateVerificationEmailContent(code), true);
        
        logger.info("Sending email to: {}", email);
        mailSender.send(message);
    }

    public boolean verifyCode(String email, String code) {
        VerificationCode storedCode = verificationCodes.get(email);
        if (storedCode != null && !storedCode.isExpired() && storedCode.getCode().equals(code)) {
            verificationCodes.remove(email);
            return true;
        }
        return false;
    }

    private String generateCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

    private static class VerificationCode {
        private String code;
        private long timestamp;
        private static final long EXPIRATION_TIME = 5 * 60 * 1000; // 5分钟

        public VerificationCode(String code) {
            this.code = code;
            this.timestamp = System.currentTimeMillis();
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() - timestamp > EXPIRATION_TIME;
        }

        @Override
        public String toString() {
            return "VerificationCode{" +
                    "code='" + code + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }
} 