package ma.formations.kafka.streams.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaCasaTopicListener {
    @KafkaListener(topics = "${spring.kafka.topic.output}")
    public void readRxClaimStream(@Payload String record) {
        if(record!=null && record.length()>0) {
            try {
                System.out.println("CASA TOPIC => " + record);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
