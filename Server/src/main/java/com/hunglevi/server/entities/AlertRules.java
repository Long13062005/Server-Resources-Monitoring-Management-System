package com.hunglevi.server.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "alert_rules")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertRules {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "server_id", nullable = false)
    private Servers server; // Assuming an alert rule is associated with a server
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type; // Alert type (e.g., CPU, Mem
    @Column(nullable = false)
    private Float threshold; // Threshold value for the alert rule
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt; // Timestamp when the alert rule was created
}
