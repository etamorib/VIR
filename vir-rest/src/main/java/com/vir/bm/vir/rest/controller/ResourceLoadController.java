package com.vir.bm.vir.rest.controller;

import com.vir.bm.vir.rest.model.ResourceLoad;
import com.vir.bm.vir.rest.repository.IResourceLoadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ResourceLoadController  {

    private final IResourceLoadRepository resourceLoadRepository;

    @Autowired
    public ResourceLoadController(IResourceLoadRepository resourceLoadRepository) {
        this.resourceLoadRepository = resourceLoadRepository;
    }

    @PostMapping("/addRS")
    public String saveRS(@RequestBody ResourceLoad resourceLoad)  {
        log.info("Post /addRS: " + resourceLoad);
        resourceLoadRepository.save(resourceLoad);
        return resourceLoad + "added with ID: " + resourceLoad.getId();
    }
}
