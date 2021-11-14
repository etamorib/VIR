package com.vir.bm.vir.rest.controller;

import com.vir.bm.vir.rest.model.ResourceLoad;
import com.vir.bm.vir.rest.repository.IResourceLoadRepository;
import com.vir.bm.vir.rest.utils.ResourceLoadNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
public class ResourceLoadController  {

    private final IResourceLoadRepository resourceLoadRepository;

    @Autowired
    public ResourceLoadController(IResourceLoadRepository resourceLoadRepository) {
        this.resourceLoadRepository = resourceLoadRepository;
    }

    @PostMapping("/addResourceLoad")
    public Mono<ResourceLoad> saveRS(@RequestBody ResourceLoad resourceLoad)  {
        log.info("Post /addResourceLoad: " + resourceLoad);
        return resourceLoadRepository.save(resourceLoad);
    }

    @GetMapping("/getResourceLoads")
    public Flux<ResourceLoad> getAllRL() {
        log.info("Get /getResourceLoads");
        return resourceLoadRepository.findAll();
    }

    @GetMapping("/getResourceLoad/{id}")
    public Mono<ResourceLoad> getRL(@PathVariable  String id) {
        log.info("Get /getResourceLoad{id} with id: " + id );
        return resourceLoadRepository.findById(id);
    }

    @GetMapping("/getResourceLoadsByTimeInterval")
    public Flux<ResourceLoad> getRLBetween(
            @RequestParam(value = "first_date", required = true)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime first,
            @RequestParam(value = "second_date", required = true)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  LocalDateTime second) {
        log.info("Get getResourceLoads from time interval with: " +
                first + "-" + second);
        return resourceLoadRepository.findByTimeBetween(first, second);
    }
}
