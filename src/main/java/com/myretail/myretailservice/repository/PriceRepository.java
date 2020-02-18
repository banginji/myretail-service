package com.myretail.myretailservice.repository;

import com.myretail.myretailservice.persistence.Price;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PriceRepository extends ReactiveCrudRepository<Price, Integer> {
}
