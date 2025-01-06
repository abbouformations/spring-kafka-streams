package ma.formations.kafka.streams.config;

import ma.formations.kafka.streams.dtos.AggregateTotalDto;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class AgregateTotalDtoSerDes extends Serdes.WrapperSerde<AggregateTotalDto>{
    public AgregateTotalDtoSerDes() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(AggregateTotalDto.class));
    }
}
