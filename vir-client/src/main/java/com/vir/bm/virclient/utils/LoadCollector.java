package com.vir.bm.virclient.utils;

import com.vir.bm.virwebclient.ResourceLoad;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LoadCollector {

    private MemoryUsageMonitor memoryUsageMonitor;
    private CpuUsageMonitor cpuUsageMonitor;
    private DiskUsageMonitor diskUsageMonitor;

    public LoadCollector(MemoryUsageMonitor memoryUsageMonitor, CpuUsageMonitor cpuUsageMonitor, DiskUsageMonitor diskUsageMonitor) {
        this.memoryUsageMonitor = memoryUsageMonitor;
        this.cpuUsageMonitor = cpuUsageMonitor;
        this.diskUsageMonitor = diskUsageMonitor;
    }

    public ResourceLoad loadResource() {
        ResourceLoad resourceLoad = new ResourceLoad();
        resourceLoad.setCpu(cpuUsageMonitor.getInfo());
        resourceLoad.setDisk(diskUsageMonitor.getInfo());
        resourceLoad.setRam(memoryUsageMonitor.getInfo());
        resourceLoad.setTime(LocalDateTime.now());
        return resourceLoad;
    }
}
