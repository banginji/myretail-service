package com.myretail.myretailservice.persistence;

import lombok.*;
import org.springframework.data.annotation.Id;

@Value
@Builder
@AllArgsConstructor
public class Price {
    @Id
    @NonNull
    Integer id;

    @NonNull
    Double value;

    @NonNull
    String currency_code;
}
