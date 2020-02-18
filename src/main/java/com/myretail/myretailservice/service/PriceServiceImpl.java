package com.myretail.myretailservice.service;

import com.myretail.myretailservice.domain.ProductPriceResponse;
import com.myretail.myretailservice.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.myretail.myretailservice.mapper.PriceMapper.productPriceMapper;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    PriceRepository priceRepository;

    @Override
    public Mono<ProductPriceResponse> getProductInfo(Integer id) {
        return priceRepository.findById(id)
                .flatMap(productPriceMapper)
                .switchIfEmpty(productPriceMapper.apply(null));
    }
}
