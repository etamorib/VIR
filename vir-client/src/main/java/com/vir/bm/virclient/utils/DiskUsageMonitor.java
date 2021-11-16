package com.vir.bm.virclient.utils;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DiskUsageMonitor {

    File root = new File("/");

    public double getInfo() {
        long total = root.getTotalSpace();
        long free = root.getFreeSpace();
        return (double) free / total;
    }

}
