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

    public String generateAndSendCode(String email) throws MessagingException {
        String code = generateCode();
        verificationCodes.put(email, new VerificationCode(code));

        sendVerificationEmail(email, code);
        return code;
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

    private void sendVerificationEmail(String email, String code) throws MessagingException {
        try {
            logger.info("Creating email message for: {}", email);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("1250585873@qq.com");
            helper.setTo(email);
            helper.setSubject("NoviceNest - 注册验证码");

            String content = String.format(
                "<div style=\"padding: 20px; background-color: #f5f5f7; border-radius: 10px;\">" +
                    "<h2 style=\"color: #1d1d1f;\">您的注册验证码</h2>" +
                    "<p style=\"font-size: 24px; color: #FF2D55; letter-spacing: 2px; margin: 20px 0;\">" +
                        "%s" +
                    "</p>" +
                    "<p style=\"color: #6e6e73;\">验证码有效期为5分钟，请尽快完成注册。</p>" +
                "</div>", code);

            helper.setText(content, true);
            logger.info("Sending email to: {}", email);
            mailSender.send(message);
            logger.info("Email sent successfully to: {}", email);
        } catch (MessagingException e) {
            logger.error("Failed to send email to: " + email, e);
            throw e;
        }
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