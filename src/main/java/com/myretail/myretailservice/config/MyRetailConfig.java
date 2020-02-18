package com.myretail.myretailservice.config;

import com.myretail.myretailservice.handler.PriceHandler;
import com.myretail.myretailservice.persistence.Price;
import com.myretail.myretailservice.repository.PriceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;

@Configuration
public class MyRetailConfig {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(PriceHandler handler) {
        return RouterFunctions.route()
                .GET("/product/{id}", handler::getProductInfo)
                .build();
    }

    @Bean
    public CommandLineRunner commandLineRunner(PriceRepository priceRepository) {
        return args -> {
            Flux<Price> priceFlux = Flux.just(
                    Price.builder().id(123).value(14.23).currency_code("USD").build(),
                    Price.builder().id(234).value(74.24).currency_code("USD").build(),
                    Price.builder().id(345).value(593.53).currency_code("EUR").build(),
                    Price.builder().id(456).value(53.55).currency_code("USD").build(),
                    Price.builder().id(13860428).value(1193.33).currency_code("USD").build(),
                    Price.builder().id(567).value(93.33).currency_code("EUR").build()
            );

            priceRepository.deleteAll()
                    .thenMany(priceFlux.flatMap(priceRepository::save))
                    .subscribe(System.out::println);
        };
    }
}
