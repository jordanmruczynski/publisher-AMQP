package pl.jordanmruczynski.publisher.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jordanmruczynski.publisher.model.Notification;
import pl.jordanmruczynski.publisher.model.Student;
import pl.jordanmruczynski.publisher.service.NotificationService;

@RestController
public class MessageController {

    private final NotificationService notificationService;

    public MessageController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notification")
    public String sendStudentNotification(@RequestParam Long studentId) {
        notificationService.sendStudentNotification(studentId);
        return "Wysłano powiadomienie!";
    }
}
