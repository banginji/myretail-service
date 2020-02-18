package com.myretail.myretailservice.service;

import com.myretail.myretailservice.domain.ProductPriceResponse;
import reactor.core.publisher.Mono;

public interface PriceService {
    Mono<ProductPriceResponse> getProductInfo(Integer id);
}
