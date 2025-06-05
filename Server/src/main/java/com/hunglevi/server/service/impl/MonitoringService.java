package com.hunglevi.server.service.impl;

import com.hunglevi.server.entities.Monitoring;
import com.hunglevi.server.entities.Servers;
import com.hunglevi.server.repository.MonitoringRepository;
import com.hunglevi.server.service.IMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MonitoringService implements IMonitoringService {
    @Autowired
    private MonitoringRepository monitoringRepository;

    @Autowired
    private AlertService alertService;
    @Override
    public Page<Monitoring> findAllMonitoring(Pageable pageable) {
        return monitoringRepository.findAll(pageable);
    }

    @Override
    public Monitoring findMonitoringById(Long id) {
        return monitoringRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Monitoring not found with id: " + id));
    }

    @Override
    public Monitoring saveMonitoring(Monitoring monitoring) {
        return monitoringRepository.save(monitoring);
    }

    @Override
    public void deleteMonitoring(Long id) {
        if (!monitoringRepository.existsById(id)) {
            throw new RuntimeException("Monitoring not found with id: " + id);
        }
        monitoringRepository.deleteById(id);
    }

    @Override
    public void monitoringSystem(Monitoring serverMetrics) {
        if (serverMetrics.getCpuUsage() > 90) {
            alertService.sendAlert(
                    "⚠️ CPU Alert!",
                    "Server " + serverMetrics.getServer().getName() + " đang sử dụng CPU vượt quá 90%."
            );
        }

        if (serverMetrics.getMemoryUsage() > 90) {
            alertService.sendAlert(
                    "⚠️ RAM Alert!",
                    "Server " + serverMetrics.getServer().getName() + " đang sử dụng RAM vượt quá 90%."
            );
        }

        if (serverMetrics.getDiskUsage() > 80) {
            alertService.sendAlert(
                    "⚠️ Disk Alert!",
                    "Server " + serverMetrics.getServer().getName() + " sắp đầy ổ đĩa."
            );
        }
    }
}
