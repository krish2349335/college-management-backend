package com.college.college_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Vehicle number is required")
    @Column(nullable = false, unique = true)
    private String vehicleNumber;

    @NotBlank(message = "Route name is required")
    @Column(nullable = false)
    private String routeName;

    private String vehicleType;

    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;

    private String driverName;
    private String driverPhone;
    private String pickupPoint;
    private String pickupTime;
}
