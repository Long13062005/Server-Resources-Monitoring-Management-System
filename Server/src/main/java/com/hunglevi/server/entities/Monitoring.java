package com.hunglevi.server.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "monitorings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Monitoring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "server_id", nullable = false)
    private Servers server; // Assuming a monitoring is associated with a server
    @Column(nullable = false,name = "cpu_usage")
    private Float cpuUsage; // CPU usage percentage
    @Column(nullable = false,name = "memory_usage")
    private Float memoryUsage; // Memory usage percentage
    @Column(nullable = false,name = "disk_usage")
    private Float diskUsage; // Disk usage percentage
    @Column(nullable = false, name = "network_in")
    private Float networkIn; // Network inbound traffic in bytes
    @Column(nullable = false, name = "network_out")
    private Float networkOut; // Network outbound traffic in bytes
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime timestamp; // Timestamp when the monitoring was created
}
