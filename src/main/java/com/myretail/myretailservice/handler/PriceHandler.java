package com.myretail.myretailservice.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface PriceHandler {
    Mono<ServerResponse> getProductInfo(ServerRequest request);
}
