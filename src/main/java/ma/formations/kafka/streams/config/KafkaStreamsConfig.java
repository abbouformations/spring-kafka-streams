package ma.formations.kafka.streams.config;

import ma.formations.kafka.streams.dtos.VentesDto;
import ma.formations.kafka.streams.service.KStreamProcessor;
import ma.formations.kafka.streams.service.KTableProcessor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.streams.StreamsConfig.*;

@EnableKafka
@EnableKafkaStreams
@Configuration
public class KafkaStreamsConfig {
    @Value(value = "${spring.kafka.bootstrap-server}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.topic.input}")
    private String inputTopic;

    @Autowired
    private KStreamProcessor kstreamProcessor;
    @Autowired
    private KTableProcessor ktableProcessor;

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(APPLICATION_ID_CONFIG, "streams-app");
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Double().getClass().getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new KafkaStreamsConfiguration(props);
    }

    @Bean
    public KStream<String, VentesDto> kStream(StreamsBuilder kStreamBuilder) {
        KStream<String, VentesDto> stream = kStreamBuilder.stream(inputTopic, Consumed.with(Serdes.String(), new VentesDtoSerDes()));
        //Process KStream
        this.kstreamProcessor.process(stream);
        //Process KTable
        this.ktableProcessor.process(stream);
        return stream;
    }
}
