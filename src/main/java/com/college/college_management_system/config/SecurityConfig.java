package com.college.college_management_system.config;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> {})
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth

                        // =========================
                        // PUBLIC APIs
                        // =========================

                        .requestMatchers(
                                "/api/auth/login",
                                "/api/auth/register"
                        ).permitAll()

                        // Registration page ke liye public data
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/branches/**",
                                "/api/semesters/**",
                                "/api/sections/**"
                        ).permitAll()

                        // Uploaded files
                        .requestMatchers("/uploads/**")
                        .permitAll()


                        // =========================
                        // ADMIN - POST
                        // =========================

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/users/**",
                                "/api/departments/**",
                                "/api/branches/**",
                                "/api/semesters/**",
                                "/api/sections/**",
                                "/api/classrooms/**",
                                "/api/transports/**",
                                "/api/students/**"
                        ).hasRole("ADMIN")


                        // =========================
                        // ADMIN - PUT
                        // =========================

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/users/**",
                                "/api/departments/**",
                                "/api/branches/**",
                                "/api/semesters/**",
                                "/api/sections/**",
                                "/api/classrooms/**",
                                "/api/transports/**",
                                "/api/students/**"
                        ).hasRole("ADMIN")


                        // =========================
                        // ADMIN - DELETE
                        // =========================

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/users/**",
                                "/api/departments/**",
                                "/api/branches/**",
                                "/api/semesters/**",
                                "/api/sections/**",
                                "/api/classrooms/**",
                                "/api/transports/**",
                                "/api/students/**"
                        ).hasRole("ADMIN")


                        // =========================
                        // TEACHER + ADMIN - POST
                        // =========================

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/teachers/**",
                                "/api/subjects/**",
                                "/api/schedule-entries/**",
                                "/api/timetables/**",
                                "/api/exam-schedules/**",
                                "/api/notices/**",
                                "/api/events/**"
                        ).hasAnyRole("TEACHER", "ADMIN")


                        // =========================
                        // TEACHER + ADMIN - PUT
                        // =========================

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/teachers/**",
                                "/api/subjects/**",
                                "/api/schedule-entries/**",
                                "/api/timetables/**",
                                "/api/exam-schedules/**",
                                "/api/notices/**",
                                "/api/events/**"
                        ).hasAnyRole("TEACHER", "ADMIN")


                        // =========================
                        // ADMIN - DELETE
                        // =========================

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/teachers/**",
                                "/api/subjects/**",
                                "/api/schedule-entries/**",
                                "/api/timetables/**",
                                "/api/exam-schedules/**",
                                "/api/notices/**",
                                "/api/events/**"
                        ).hasRole("ADMIN")


                        // =========================
                        // STUDENT PROFILE
                        // =========================

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/students/me"
                        ).hasAnyRole(
                                "STUDENT",
                                "TEACHER",
                                "ADMIN"
                        )

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/students/**"
                        ).hasAnyRole(
                                "TEACHER",
                                "ADMIN"
                        )


                        // =========================
                        // GENERAL GET APIs
                        // =========================

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/teachers/**",
                                "/api/subjects/**",
                                "/api/timetables/**",
                                "/api/schedule-entries/**",
                                "/api/exam-schedules/**",
                                "/api/fees/**",
                                "/api/notices/**",
                                "/api/events/**",
                                "/api/classrooms/**",
                                "/api/transports/**",
                                "/api/departments/**"
                        ).hasAnyRole(
                                "STUDENT",
                                "TEACHER",
                                "ADMIN"
                        )


                        // =========================
                        // EVERYTHING ELSE
                        // =========================

                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }


    // =========================
    // CORS CONFIGURATION
    // =========================

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of("http://localhost:5173")
        );

        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )
        );

        configuration.setAllowedHeaders(
                List.of("*")
        );

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;
    }
}
