package com.myretail.myretailservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myretail.myretailservice.persistence.Price;
import lombok.*;

@Value
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductPriceResponse {
    private final Price price;

    private final ProductPriceError productPriceError;
}
