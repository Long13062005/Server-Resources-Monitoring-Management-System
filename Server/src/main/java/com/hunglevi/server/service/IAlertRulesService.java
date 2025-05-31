package com.hunglevi.server.service;


import com.hunglevi.server.entities.AlertRules;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IAlertRulesService {
    // Define methods that the user service should implement
    // For example:
    Page<AlertRules> findAllAlertRules(Pageable pageable);
    AlertRules findAlertRuleById(Long id);
    AlertRules saveAlertRule(AlertRules alertRule);
    void deleteAlertRule(Long id);

}
