package ir.dadeandish.notificationservice.listener;

import ir.dadeandish.event.WorkOrderCreatedEvent;
import ir.dadeandish.notificationservice.application.EmailService;
import ir.dadeandish.notificationservice.application.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class WorkorderCreatedListener {

    private EmailService emailService;
    private SmsService smsService;
    private static final Logger logger= LoggerFactory.getLogger(WorkorderCreatedListener.class);

    @Autowired
    public WorkorderCreatedListener(SmsService smsService, EmailService emailService) {
        this.emailService = emailService;
        this.smsService= smsService;
    }

    @KafkaListener(topics="workorder-created-topic",
            groupId="notification-group")
    public void handle(WorkOrderCreatedEvent e){
        logger.debug("consume work order assign task");
        sendEmail(e.getWorkOrderId(), e.getEmail(), LocalDateTime.now(), e.getEmployeeName(), e.getEquipmentName());
        sendSMS(e.getWorkOrderId(), e.getMobile(), LocalDateTime.now(), e.getEmployeeName(), e.getEquipmentName());
    }

    private void sendEmail(Integer workOrderId, String email, LocalDateTime dueDate, String employeeName, String equipmentName) {

        String subject = "New Work Order Assigned";
        String body = String.format(
                "Hello %s,\n\n" +
                        "A new work order has been assigned to you.\n\n" +
                        "Work Order ID: %d\n" +
                        "Equipment: %s\n" +
                        "Due Date: %s\n\n" +
                        "Please check your dashboard.",
                employeeName,
                workOrderId,
                equipmentName,
                dueDate
        );

        emailService.sendEmail(email, subject, body);
    }

    private void sendSMS(Integer workOrderId, String mobile, LocalDateTime dueDate, String employeeName, String equipmentName) {

        String message = String.format(
                "New Work Order Assigned:\nID: %d\nEquipment: %s",
                workOrderId,
                equipmentName
        );

        smsService.sendSms(mobile, message);
    }
}
