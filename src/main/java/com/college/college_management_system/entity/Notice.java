package com.college.college_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Notice title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Notice description is required")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    private String attachmentUrl;

    @NotBlank(message = "Target type is required")
    private String targetType;

    private Long targetId;

    private LocalDateTime createdAt;
}