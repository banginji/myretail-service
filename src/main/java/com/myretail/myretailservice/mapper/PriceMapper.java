package com.myretail.myretailservice.mapper;

import com.myretail.myretailservice.domain.CurrentPrice;
import com.myretail.myretailservice.domain.ProductPriceError;
import com.myretail.myretailservice.domain.ProductPriceResponse;
import com.myretail.myretailservice.domain.ProductResponse;
import com.myretail.myretailservice.persistence.Price;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

public class PriceMapper {
    public static Function<Price, Mono<ProductPriceResponse>> productPriceMapper = price -> {
        if (price != null)
            return Mono.just(
                    ProductPriceResponse.builder()
                            .price(price)
                            .build()
            );
        else
            return Mono.just(
                    ProductPriceResponse.builder()
                            .productPriceError(
                                    ProductPriceError.builder()
                                            .productPriceError("price not found in data store")
                                            .build()
                            )
                            .build()
            );
    };

    public static Function<ProductPriceResponse, ProductResponse> retrieveDataMapper = productPriceResponse -> ProductResponse.builder()
            .id(productPriceResponse.getPrice().getId())
            .current_price(
                    CurrentPrice.builder()
                            .value(productPriceResponse.getPrice().getValue())
                            .currency_code(productPriceResponse.getPrice().getCurrency_code())
                            .build()
            ).errors(new ArrayList<>(Collections.singleton(productPriceResponse.getProductPriceError())))
            .build();

    public static Function<ProductResponse, Mono<ServerResponse>> responseMapper = productResponse -> {
        if (productResponse.getErrors().size() < 2)
            return ok().body(Mono.just(productResponse), ProductResponse.class);
        else
            return status(HttpStatus.NOT_FOUND).body(Mono.just(productResponse), ProductResponse.class);
    };
}
