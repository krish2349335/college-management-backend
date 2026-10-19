package com.college.college_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "branches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Branch name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Branch code is required")
    @Column(nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
