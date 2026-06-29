package ir.dadeandish.notificationservice.application;

import org.springframework.stereotype.Service;

@Service
public class TwilioSmsService implements SmsService {

    @Override
    public void sendSms(String phoneNumber, String message) {
        System.out.println("Sending SMS to " + phoneNumber);
    }
}