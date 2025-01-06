package ma.formations.kafka.streams.controllers;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ventes")
public class StreamsController {
    private final StreamsBuilderFactoryBean factoryBean;

    public StreamsController(StreamsBuilderFactoryBean factoryBean) {
        this.factoryBean=factoryBean;
    }

    @GetMapping("/customer/{id}")
    public String getDealerSales(@PathVariable String id){
        KafkaStreams kafkaStreams =  factoryBean.getKafkaStreams();
        //Read the KTable Store and get the total aggregated sales by customer
        ReadOnlyKeyValueStore<String, Long> amounts = kafkaStreams
                .store(StoreQueryParameters.fromNameAndType("customer-sales-amount", QueryableStoreTypes.keyValueStore()));
        return "Total Article Sales for Customer "+id +" is MAD"+ amounts.get(id);
    }
}
