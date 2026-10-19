package com.college.college_management_system.entity;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "fees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Student is required")
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @NotNull(message = "Semester is required")
    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Total amount cannot be negative")
    @Column(nullable = false)
    private BigDecimal totalAmount;

    @NotNull(message = "Paid amount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Paid amount cannot be negative")
    @Column(nullable = false)
    private BigDecimal paidAmount;

    @NotNull(message = "Remaining amount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Remaining amount cannot be negative")
    @Column(nullable = false)
    private BigDecimal remainingAmount;

    private LocalDate dueDate;
    private String status;
}
