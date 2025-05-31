package com.hunglevi.server.service.impl;

import com.hunglevi.server.entities.Alerts;
import com.hunglevi.server.repository.AlertsRepository;
import com.hunglevi.server.service.IAlertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlertService implements IAlertsService {
    @Autowired
    private AlertsRepository alertsRepository;
    @Override
    public Page<Alerts> findAllAlerts(Pageable pageable) {
        return alertsRepository.findAll(pageable);
    }

    @Override
    public Alerts findAlertById(Long id) {
        return alertsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found with id: " + id));
    }

    @Override
    public Alerts saveAlert(Alerts alert) {
        return alertsRepository.save(alert);
    }

    @Override
    public void deleteAlert(Long id) {
        if (!alertsRepository.existsById(id)) {
            throw new RuntimeException("Alert not found with id: " + id);
        }
        alertsRepository.deleteById(id);
    }
}
