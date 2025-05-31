package com.hunglevi.server.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "alerts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alerts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "server_id", nullable = false)
    private Servers server; // Assuming an alert rule is associated with a server
    @Column(nullable = false)
    private String message; // Alert messageory, Disk)
    @ManyToOne
    @JoinColumn(name = "level_id", nullable = false)
    private Level level; // Alert level (e.g., INFO, WARNING, CRITICAL)
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt; // Timestamp when the alert was created
}
