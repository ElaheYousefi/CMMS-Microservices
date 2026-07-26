package ir.dadeandish.notificationservice;

import ir.dadeandish.event.WorkOrderCreatedEvent;
import ir.dadeandish.notificationservice.application.EmailService;
import ir.dadeandish.notificationservice.application.SmsService;
import ir.dadeandish.notificationservice.listener.WorkorderCreatedListener;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NotificationConsumerTest {

    @Mock
    private EmailService emailService;

    @Mock
    private SmsService smsService;

    @InjectMocks
    private WorkorderCreatedListener consumer;

    @Test
    void handle_shouldSendEmailAndSms() {

        // ---------- Arrange ----------

        WorkOrderCreatedEvent event = new WorkOrderCreatedEvent();
        event.setWorkOrderId(100);
        event.setEmployeeName("John");
        event.setEmail("john@test.com");
        event.setMobile("09121234567");
        event.setEquipmentName("Pump");

        // ---------- Act ----------

        consumer.handle(event);

        // ---------- Assert Email ----------

        ArgumentCaptor<String> emailBodyCaptor =
                ArgumentCaptor.forClass(String.class);

        verify(emailService).sendEmail(
                eq("john@test.com"),
                eq("New Work Order Assigned"),
                emailBodyCaptor.capture());

        String emailBody = emailBodyCaptor.getValue();

        assertTrue(emailBody.contains("John"));
        assertTrue(emailBody.contains("100"));
        assertTrue(emailBody.contains("Pump"));

        // ---------- Assert SMS ----------

        ArgumentCaptor<String> smsCaptor =
                ArgumentCaptor.forClass(String.class);

        verify(smsService).sendSms(
                eq("09121234567"),
                smsCaptor.capture());

        String sms = smsCaptor.getValue();

        assertTrue(sms.contains("100"));
        assertTrue(sms.contains("Pump"));
    }
}