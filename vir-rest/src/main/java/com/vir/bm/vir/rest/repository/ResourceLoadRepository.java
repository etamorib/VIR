package com.vir.bm.vir.rest.repository;

import com.vir.bm.vir.rest.model.ResourceLoad;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ResourceLoadRepository extends MongoRepository<ResourceLoad, UUID> {
}
