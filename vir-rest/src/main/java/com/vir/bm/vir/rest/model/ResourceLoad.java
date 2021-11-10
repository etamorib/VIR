package com.vir.bm.vir.rest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@Document(collation = "ResourceLoad")
@Getter @Setter
@NoArgsConstructor
@Accessors(fluent = true)
public class ResourceLoad implements Serializable {

    @Id
    private UUID uuid;
    private double cpu;
    private double ram;
    private double disk;
    private long network;

    public ResourceLoad(double cpu, double ram, double disk, long network) {
        this.cpu = cpu;
        this.ram = ram;
        this.disk = disk;
        this.network = network;
    }
}
