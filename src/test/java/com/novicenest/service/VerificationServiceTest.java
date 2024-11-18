package com.novicenest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VerificationServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private VerificationService verificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateAndSendCode() throws Exception {
        String email = "kayano04@outlook.jp";

        // Mock MimeMessage
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Call the method to test
        String code = verificationService.generateAndSendCode(email);

        // Verify that the email was sent
        verify(mailSender, times(1)).send(any(MimeMessage.class));

        // Verify that the code is 6 digits
        assertEquals(6, code.length());
        assertTrue(code.matches("\\d{6}"));
    }

    @Test
    void testVerifyCode() throws MessagingException {
        // Mock MimeMessage for generateAndSendCode
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        String email = "kayano04@outlook.jp";
        String code = verificationService.generateAndSendCode(email);

        // Verify the code
        assertTrue(verificationService.verifyCode(email, code));

        // Verify that the code is removed after verification
        assertFalse(verificationService.verifyCode(email, code));
    }

    @Test
    void testSendEmail() throws MessagingException {
        String testEmail = "1250585873@qq.com";

        // Mock MimeMessage
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        
        // Call the method to test
        verificationService.generateAndSendCode(testEmail);

        // Verify that the email was sent
        verify(mailSender, times(1)).send(any(MimeMessage.class));
    }
} 