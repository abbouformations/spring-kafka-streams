package ma.formations.kafka.streams.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AggregateTotalDto {
   private long count;
   private double amount;
}
