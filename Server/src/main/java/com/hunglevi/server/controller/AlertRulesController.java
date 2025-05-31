package com.hunglevi.server.controller;

import com.hunglevi.server.entities.AlertRules;
import com.hunglevi.server.service.impl.AlertRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alert-rules")
@CrossOrigin
public class AlertRulesController {
    @Autowired
    private AlertRuleService alertRuleService;

    @GetMapping
    public ResponseEntity<?> getAllAlertRules(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "4") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<AlertRules> allAlerts = alertRuleService.findAllAlertRules(pageable);

            if (allAlerts.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return new ResponseEntity<>(allAlerts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching contracts", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAlertRulesById(@PathVariable Long id) {
        try {
            AlertRules alertRules = alertRuleService.findAlertRuleById(id);
            return new ResponseEntity<>(alertRules, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Alert Rule not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> createAlertRules(@RequestBody AlertRules alertRules) {
        try {
            AlertRules createdAlert = alertRuleService.saveAlertRule(alertRules);
            return new ResponseEntity<>(createdAlert, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the alertRules", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAlert(@PathVariable Long id, @RequestBody AlertRules alertRules) {
        try {
            alertRules.setId(id);
            AlertRules updatedAlert = alertRuleService.saveAlertRule(alertRules);
            return new ResponseEntity<>(updatedAlert, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while updating the alertRules", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAlertRules(@PathVariable Long id) {
        try {
            alertRuleService.deleteAlertRule(id);
            return new ResponseEntity<>("Alert deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deleting the alert", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
