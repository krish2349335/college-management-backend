package com.college.college_management_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "Enrollment number is required")
    private String enrollmentNo;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Branch is required")
    private Long branchId;

    @NotNull(message = "Semester is required")
    private Long semesterId;

    @NotNull(message = "Section is required")
    private Long sectionId;
}
