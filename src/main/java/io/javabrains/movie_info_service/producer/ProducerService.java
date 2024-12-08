package io.javabrains.movie_info_service.producer;

import io.javabrains.movie_info_service.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public void produce(Message message) {
        System.out.println("Producing the message " + message);
        kafkaTemplate.send("messages", message);
    }
}
