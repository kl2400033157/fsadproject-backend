package com.webinarhub.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Email Service for sending registration confirmations.
 * Demonstrates Spring Boot email sending capability.
 * Uses JavaMailSender which is auto-configured by spring-boot-starter-mail.
 * 
 * NOTE: Email sending is optional. If SMTP is not configured,
 * failures are logged but do not break any functionality.
 */
@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Send registration confirmation email.
     * Fails silently if SMTP is not configured.
     */
    public void sendRegistrationConfirmation(String toEmail, String userName,
                                              String webinarTitle, LocalDateTime webinarDateTime) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setFrom("pythondevelopmenttestingemail@gmail.com");
            message.setSubject("✅ Registration Confirmed: " + webinarTitle);
            message.setText(
                    "Dear " + userName + ",\n\n" +
                    "Your registration has been confirmed!\n\n" +
                    "Webinar: " + webinarTitle + "\n" +
                    "Date & Time: " + webinarDateTime.format(
                            DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a")) + "\n\n" +
                    "Please join on time. You can access your session from your LearnHub dashboard.\n\n" +
                    "Best regards,\n" +
                    "LearnHub Team"
            );

            mailSender.send(message);
            System.out.println("✅ Registration email sent to: " + toEmail);
        } catch (Exception e) {
            System.out.println("⚠️ Email not sent: " + e.getMessage());
        }
    }

    public void sendOtpEmail(String toEmail, String otp) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setFrom("pythondevelopmenttestingemail@gmail.com");
            message.setSubject("🔑 LearnHub Registration OTP");
            message.setText(
                    "Hello,\n\n" +
                    "Your one-time verification code is: " + otp + "\n\n" +
                    "This code will expire in 10 minutes.\n\n" +
                    "If you didn't request this code, please ignore this email.\n\n" +
                    "Best regards,\n" +
                    "LearnHub Team"
            );

            mailSender.send(message);
            System.out.println("✅ OTP email sent to: " + toEmail);
        } catch (Exception e) {
            System.out.println("⚠️ OTP Email not sent: " + e.getMessage());
        }
    }

    /**
     * Send webinar reminder email.
     * Fails silently if SMTP is not configured.
     */
    public void sendWebinarReminder(String toEmail, String userName,
                                     String webinarTitle, LocalDateTime webinarDateTime) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Reminder: " + webinarTitle + " starts soon!");
            message.setText(
                    "Dear " + userName + ",\n\n" +
                    "This is a reminder that the webinar '" + webinarTitle + "' is starting soon.\n\n" +
                    "Date & Time: " + webinarDateTime.format(
                            DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a")) + "\n\n" +
                    "Don't miss it!\n\n" +
                    "Best regards,\n" +
                    "WebinarHub Team"
            );
            message.setFrom("noreply@webinarhub.com");

            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("⚠️ Reminder email not sent: " + e.getMessage());
        }
    }
}
