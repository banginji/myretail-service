package com.myretail.myretailservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Value
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrentPrice {
    @NonNull
    Double value;

    @NonNull
    String currency_code;
}
