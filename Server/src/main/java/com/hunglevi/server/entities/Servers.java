package com.hunglevi.server.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "servers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servers {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 45)
    private String ipAddress;
    @Column(nullable = false, length = 100)
    private String location;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user; // Assuming a server is associated with a user
}
