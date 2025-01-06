package ma.formations.kafka.streams.service;

import ma.formations.kafka.streams.dtos.VentesDto;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KStreamProcessor {

    @Value("${spring.kafka.topic.output}")
    private String outputTopic;
    public void process(KStream<String, VentesDto> stream){
        stream.filter((key, object) -> {
            return object != null && object.getCity() != null && object.getCity().trim().equalsIgnoreCase("casa");
        }).to(outputTopic);

    }
}