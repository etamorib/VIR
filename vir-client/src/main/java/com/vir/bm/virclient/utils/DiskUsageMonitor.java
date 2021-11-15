package com.vir.bm.virclient.utils;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DiskUsageMonitor {

    File root = new File("/");

    public double getInfo() {
        return (double) root.getUsableSpace();
    }

}
