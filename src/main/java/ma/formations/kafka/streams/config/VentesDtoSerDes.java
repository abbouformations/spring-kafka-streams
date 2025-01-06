package ma.formations.kafka.streams.config;

import ma.formations.kafka.streams.dtos.VentesDto;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class VentesDtoSerDes extends Serdes.WrapperSerde<VentesDto>{
    public VentesDtoSerDes() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(VentesDto.class));
    }
}
