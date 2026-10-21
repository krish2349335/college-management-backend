package com.college.college_management_system.config;

import com.college.college_management_system.entity.Role;
import com.college.college_management_system.entity.User;
import com.college.college_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminPasswordResetRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${ADMIN_USERNAME:}")
    private String adminUsername;

    @Value("${ADMIN_EMAIL:}")
    private String adminEmail;

    @Value("${ADMIN_PASSWORD:}")
    private String adminPassword;

    @Override
    public void run(String... args) {

        // Admin credentials configure nahi hain toh initialization skip karo.
        if (adminUsername.isBlank()
                || adminEmail.isBlank()
                || adminPassword.isBlank()) {
            System.out.println(
                    "Admin credentials not configured. Admin initialization skipped."
            );
            return;
        }

        // Existing admin ko dobara create ya reset nahi karna.
        if (userRepository.findByUsername(adminUsername).isPresent()) {
            System.out.println("Admin account already exists. Initialization skipped.");
            return;
        }

        // Email kisi existing user ka hai toh duplicate account mat banao.
        if (userRepository.findByEmail(adminEmail).isPresent()) {
            System.out.println(
                    "Admin email already belongs to a user. Initialization skipped."
            );
            return;
        }

        User admin = new User();
        admin.setUsername(adminUsername);
        admin.setEmail(adminEmail);
        admin.setPassword(passwordEncoder.encode(adminPassword));
        admin.setRole(Role.ADMIN);
        admin.setActive(true);

        userRepository.save(admin);

        System.out.println("Initial admin account created successfully.");
    }
}
