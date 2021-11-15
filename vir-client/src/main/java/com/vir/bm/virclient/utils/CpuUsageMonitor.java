package com.vir.bm.virclient.utils;


import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;

@Component
public class CpuUsageMonitor {

    public double getInfo() {
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return operatingSystemMXBean.getSystemCpuLoad() * 100;

    }
}
