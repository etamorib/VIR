package com.vir.bm.virwebclient;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceLoad {
    private String id;
    private double cpu;
    private double ram;
    private double disk;
    private long network;
    private LocalDateTime time;

    public ResourceLoad(double cpu, double ram, double disk, long network, LocalDateTime time) {
        this.cpu = cpu;
        this.ram = ram;
        this.disk = disk;
        this.network = network;
        this.time = time;
    }
}
