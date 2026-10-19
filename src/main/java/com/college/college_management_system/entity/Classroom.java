package com.college.college_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "classrooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Room number is required")
    @Column(nullable = false, unique = true)
    private String roomNumber;

    @NotBlank(message = "Block is required")
    @Column(nullable = false)
    private String block;

    private String floor;

    private String roomType;

    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;
}
