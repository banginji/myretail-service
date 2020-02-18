package com.myretail.myretailservice.handler;

import com.myretail.myretailservice.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.myretail.myretailservice.mapper.PriceMapper.responseMapper;
import static com.myretail.myretailservice.mapper.PriceMapper.retrieveDataMapper;

@Component
public class PriceHandlerImpl implements PriceHandler {

    @Autowired
    PriceService priceService;

    @Override
    public Mono<ServerResponse> getProductInfo(ServerRequest request) {
        Integer id = Integer.parseInt(request.pathVariable("id"));
        return priceService.getProductInfo(id)
                .flatMap(retrieveDataMapper.andThen(responseMapper));
    }
}
