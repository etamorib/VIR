package com.vir.bm.vir.rest.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Document(collection = "ResourceLoad")
@Data
public class ResourceLoad {

    @Id
    private String id;
    private double cpu;
    private double ram;
    private double disk;
    private long network;
    @DateTimeFormat(pattern="yyyy-MM-dd-HH:mm:ss")
    private LocalDateTime time;

    public ResourceLoad(double cpu, double ram, double disk, long network, LocalDateTime time) {
        this.cpu = cpu;
        this.ram = ram;
        this.disk = disk;
        this.network = network;
        this.time = time;
    }

}
