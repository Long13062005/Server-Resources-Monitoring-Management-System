package com.hunglevi.server.config;

import com.hunglevi.server.entities.Roles;
import com.hunglevi.server.entities.Users;
import com.hunglevi.server.repository.RolesRepository;
import com.hunglevi.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class AutomationInsertExample implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    public RolesRepository roleRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (roleRepository.findByName("ADMIN").isEmpty()) {
            roleRepository.save(new Roles("ADMIN"));
        }

        if (roleRepository.findByName("USER").isEmpty()) {
            roleRepository.save(new Roles("USER"));
        }
        if (userRepository.findByUsername("admin").isEmpty()) {
            Users admin = new Users();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEmail("admin123@gmail.com");
            admin.setRole(roleRepository.findByName("ADMIN").orElseThrow(() -> new RuntimeException("Role ADMIN not found")));
            userRepository.save(admin);
        }
        if (userRepository.findByUsername("user").isEmpty()) {
            Users user = new Users();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setEmail("user123@gmail.com");
            user.setRole(roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("Role ADMIN not found")));
            userRepository.save(user);
        }
    }
}
