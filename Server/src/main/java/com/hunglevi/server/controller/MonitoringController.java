package com.hunglevi.server.controller;

import com.hunglevi.server.entities.Monitoring;
import com.hunglevi.server.service.impl.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/monitoring")
@CrossOrigin
public class MonitoringController {
    @Autowired
    private MonitoringService monitoringService;

    @GetMapping
    public ResponseEntity<?> getAllMonitorings(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "4") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Monitoring> monitorings = monitoringService.findAllMonitoring(pageable);

            if (monitorings.isEmpty()) {
                return new ResponseEntity<>("No Monitorings found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(monitorings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching contracts", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getMonitoringById(@PathVariable Long id) {
        try {
            Monitoring monitoring = monitoringService.findMonitoringById(id);
            return new ResponseEntity<>(monitoring, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Monitoring not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> createMonitoring(@RequestBody Monitoring monitoring) {
        try {
            Monitoring saveMonitoring = monitoringService.saveMonitoring(monitoring);
            return new ResponseEntity<>(saveMonitoring, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the monitoring", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMonitoring(@PathVariable Long id, @RequestBody Monitoring monitoring) {
        try {
            monitoring.setId(id);
            Monitoring updateMonitoring = monitoringService.saveMonitoring(monitoring);
            return new ResponseEntity<>(updateMonitoring, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while updating the monitoring", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMonitoring(@PathVariable Long id) {
        try {
            monitoringService.deleteMonitoring(id);
            return new ResponseEntity<>("Monitoring deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deleting the user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
