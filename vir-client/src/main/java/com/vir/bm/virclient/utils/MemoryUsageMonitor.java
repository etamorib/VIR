package com.vir.bm.virclient.utils;

import org.springframework.stereotype.Component;

@Component
public class MemoryUsageMonitor {

    public double getInfo() {
        final long ramTotal = Runtime.getRuntime().totalMemory();
        final long ramFree = Runtime.getRuntime().freeMemory();
        final long ramUsed = ramTotal - ramFree;

        return  ((double) ramUsed / ramTotal) * 100;
    }
}
