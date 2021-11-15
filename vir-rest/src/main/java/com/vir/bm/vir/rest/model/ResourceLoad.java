package com.vir.bm.vir.rest.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "ResourceLoad")
@Data
public class ResourceLoad {

    @Id
    private String id;
    private double cpu;
    private double ram;
    private double disk;
    private LocalDateTime time;

    public ResourceLoad(double cpu, double ram, double disk, LocalDateTime time) {
        this.cpu = cpu;
        this.ram = ram;
        this.disk = disk;
        this.time = time;
    }

}
