package com.hunglevi.server.controller;

import com.hunglevi.server.entities.Alerts;
import com.hunglevi.server.service.impl.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alert")
@CrossOrigin
public class AlertController {
    @Autowired
    private AlertService alertService;

    @GetMapping
    public ResponseEntity<?> getAllAlert(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "4") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Alerts> alerts = alertService.findAllAlerts(pageable);

            if (alerts.isEmpty()) {
                return new ResponseEntity<>("No Monitorings found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(alerts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching contracts", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAlertById(@PathVariable Long id) {
        try {
            Alerts alert = alertService.findAlertById(id);
            return new ResponseEntity<>(alert, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Alert not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> createAlert(@RequestBody Alerts alert) {
        try {
            Alerts createdAlert = alertService.saveAlert(alert);
            return new ResponseEntity<>(createdAlert, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the alert", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAlert(@PathVariable Long id, @RequestBody Alerts alert) {
        try {
            alert.setId(id);
            Alerts updatedAlert = alertService.saveAlert(alert);
            return new ResponseEntity<>(updatedAlert, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while updating the alert", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAlert(@PathVariable Long id) {
        try {
            alertService.deleteAlert(id);
            return new ResponseEntity<>("Alert deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deleting the alert", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/test-alert")
    public ResponseEntity<String> testAlert() {
        alertService.sendAlert("🚨 Test Alert", "This is a test email from your monitoring system.");
        return ResponseEntity.ok("Test email sent.");
    }

}
