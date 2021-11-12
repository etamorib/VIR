package com.vir.bm.vir.rest.repository;

import com.vir.bm.vir.rest.model.ResourceLoad;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IResourceLoadRepository extends MongoRepository<ResourceLoad, String> {
    public List<ResourceLoad> findByTimeBetween(LocalDateTime ldt1, LocalDateTime ldt2);
}
