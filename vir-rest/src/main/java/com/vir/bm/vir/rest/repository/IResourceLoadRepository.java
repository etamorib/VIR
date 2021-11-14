package com.vir.bm.vir.rest.repository;

import com.vir.bm.vir.rest.model.ResourceLoad;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;

public interface IResourceLoadRepository extends ReactiveMongoRepository<ResourceLoad, String> {
    public Flux<ResourceLoad> findByTimeBetween(LocalDateTime ldt1, LocalDateTime ldt2);
}
