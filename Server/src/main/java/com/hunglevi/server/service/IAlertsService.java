package com.hunglevi.server.service;


import com.hunglevi.server.entities.Alerts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IAlertsService {
    // Define methods that the user service should implement
    // For example:
    Page<Alerts> findAllAlerts(Pageable pageable);
    Alerts findAlertById(Long id);
    Alerts saveAlert(Alerts alert);
    void deleteAlert(Long id);

    void sendAlert(String subject, String content);
}
