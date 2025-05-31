package com.hunglevi.server.service.impl;

import com.hunglevi.server.entities.AlertRules;
import com.hunglevi.server.repository.AlertRulesRepository;
import com.hunglevi.server.service.IAlertRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlertRuleService implements IAlertRulesService {
    @Autowired
    private AlertRulesRepository alertRulesRepository;
    @Override
    public Page<AlertRules> findAllAlertRules(Pageable pageable) {
        return alertRulesRepository.findAll(pageable);
    }

    @Override
    public AlertRules findAlertRuleById(Long id) {
        return alertRulesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert rule not found with id: " + id));
    }

    @Override
    public AlertRules saveAlertRule(AlertRules alertRule) {
        return alertRulesRepository.save(alertRule);
    }

    @Override
    public void deleteAlertRule(Long id) {
        if (!alertRulesRepository.existsById(id)) {
            throw new RuntimeException("Alert rule not found with id: " + id);
        }
        alertRulesRepository.deleteById(id);
    }
}
