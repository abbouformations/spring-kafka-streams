package ma.formations.kafka.streams.controllers;

import ma.formations.kafka.streams.dtos.VentesDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@RestController
public class KafkaProducerController {
    @Value(value = "${spring.kafka.topic.input}")
    private String inputTopic;
    private KafkaTemplate<String, VentesDto> kafkaTemplate;
    Random random = new Random();
    private static final List<String> types=List.of("ELECTROMENAGER","INFORMATIQUE","Divers");

    public KafkaProducerController(KafkaTemplate<String, VentesDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @GetMapping("/send/{customer}/{city}/{price}")
    public String sendMessage(@PathVariable String customer,@PathVariable String city,@PathVariable String price) {
        kafkaTemplate.send(inputTopic, VentesDto.builder().
                         customerCode(customer).
                         city(city).
                         price(price).
                         transactionId(UUID.randomUUID().toString()).
                         typeArticle(types.get(random.nextInt(types.size()))).
                        build()
                );
        return "Messages sent with success ";
    }
}

    ;