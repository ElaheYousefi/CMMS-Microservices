package ir.dadeandish.notificationservice.application;

public interface SmsService {
    void sendSms(String phoneNumber, String message);
}
