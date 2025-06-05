package com.hunglevi.server.service;


import com.hunglevi.server.entities.Monitoring;
import com.hunglevi.server.entities.Servers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IMonitoringService {
    // Define methods that the monitoring service should implement
    // For example:
    Page<Monitoring> findAllMonitoring(Pageable pageable);
    Monitoring findMonitoringById(Long id);
    Monitoring saveMonitoring(Monitoring monitoring);
    void deleteMonitoring(Long id);

    void monitoringSystem(Monitoring serverMetrics);
}
