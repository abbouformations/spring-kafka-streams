package ma.formations.kafka.streams.service;

import ma.formations.kafka.streams.config.AgregateTotalDtoSerDes;
import ma.formations.kafka.streams.dtos.AggregateTotalDto;
import ma.formations.kafka.streams.dtos.VentesDto;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KGroupedStream;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.Stores;
import org.springframework.stereotype.Component;

@Component
public class KTableProcessor {

    public void process(KStream<String, VentesDto> stream){
        KeyValueBytesStoreSupplier customerSales = Stores.persistentKeyValueStore("customer-sales-amount");
        KGroupedStream<String, Double> salesByCustomer = stream
                .map((key, sales) -> new KeyValue(sales.getCustomerCode(), Double.parseDouble(sales.getPrice())))
                .groupByKey();
        KTable<String, AggregateTotalDto> customerAggregate = salesByCustomer.aggregate(() -> new AggregateTotalDto(),
                (k,v,aggregate) -> {
                    aggregate.setCount(aggregate.getCount()+1);
                    aggregate.setAmount(aggregate.getAmount()+v);
                    return aggregate;
                }, Materialized.with(Serdes.String(),new AgregateTotalDtoSerDes()));
        final KTable<String, Double> dealerTotal =
                customerAggregate.mapValues(value -> value.getAmount(),Materialized.as(customerSales));
    }
}
