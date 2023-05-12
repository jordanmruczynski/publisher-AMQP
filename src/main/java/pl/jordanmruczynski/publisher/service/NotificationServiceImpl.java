package pl.jordanmruczynski.publisher.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.jordanmruczynski.publisher.model.Notification;
import pl.jordanmruczynski.publisher.model.Student;

@Service
public class NotificationServiceImpl implements NotificationService {

    public static final String STUDENTS = "http://localhost:8080/students/";
    private final RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;

    public NotificationServiceImpl(RestTemplate restTemplate, RabbitTemplate rabbitTemplate) {
        this.restTemplate = restTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendStudentNotification(Long studentId) {
        Student student = restTemplate.exchange(STUDENTS + studentId, HttpMethod.GET, HttpEntity.EMPTY, Student.class).getBody();
        rabbitTemplate.convertAndSend("testqueue",
                new Notification(student.getEmail(), "Witamy na www.xyz.pl", "Dziekujemy za rejestracje!"));

    }
}
