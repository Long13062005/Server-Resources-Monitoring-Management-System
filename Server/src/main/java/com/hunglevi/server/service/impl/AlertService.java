package com.hunglevi.server.service.impl;

import com.hunglevi.server.entities.Alerts;
import com.hunglevi.server.repository.AlertsRepository;
import com.hunglevi.server.service.IAlertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AlertService implements IAlertsService {
    @Autowired
    private AlertsRepository alertsRepository;
    @Autowired
    private JavaMailSender mailSender;

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

    @Override
    public void sendAlert(String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("vulong13062005@gmail.com");
        mailMessage.setTo("vulonghungvn@gmail.com");
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        mailSender.send(mailMessage);
        System.out.println("✅ Email alert sent successfully.");
    }
}
