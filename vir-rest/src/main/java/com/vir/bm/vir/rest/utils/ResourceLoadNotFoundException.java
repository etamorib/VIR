package com.vir.bm.vir.rest.utils;

public class ResourceLoadNotFoundException  extends RuntimeException{

    public ResourceLoadNotFoundException(String id) {
        super("Could not retrieve Resource Load with id: " + id);
    }

}
