package com.college.college_management_system.config;

import com.college.college_management_system.entity.User;
import com.college.college_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminPasswordResetRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        User admin = userRepository.findByUsername("admin")
                .orElseThrow(() -> new RuntimeException("Admin user not found"));

        admin.setPassword(passwordEncoder.encode("Admin@123"));
        userRepository.save(admin);

        System.out.println("====================================");
        System.out.println("ADMIN PASSWORD RESET SUCCESSFULLY");
        System.out.println("Username: admin");
        System.out.println("Password: Admin@123");
        System.out.println("====================================");
    }
}
