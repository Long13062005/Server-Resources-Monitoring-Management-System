package com.hunglevi.server.service.impl;

import com.hunglevi.server.entities.Monitoring;
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
}
