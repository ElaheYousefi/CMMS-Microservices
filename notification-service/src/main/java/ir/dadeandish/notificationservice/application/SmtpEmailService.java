package ir.dadeandish.notificationservice.application;

import org.springframework.stereotype.Service;

@Service
public class SmtpEmailService implements EmailService {

    @Override
    public void sendEmail(String to, String subject, String body) {
        // JavaMailSender or external provider (SendGrid, SES, etc.)
        System.out.println("Sending EMAIL to " + to);
    }
}