package ma.formations.kafka.streams.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentesDto {
    private String city;
    private String customerCode;
    private String price;
    private String transactionId;
    private String typeArticle;
    private String date;
}
