package com.college.college_management_system.service;

import com.college.college_management_system.dto.LoginRequest;
import com.college.college_management_system.dto.LoginResponse;
import com.college.college_management_system.dto.RegisterRequest;
import com.college.college_management_system.entity.*;
import com.college.college_management_system.repository.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final StudentRepository studentRepository;
    private final BranchRepository branchRepository;
    private final SemesterRepository semesterRepository;
    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;


    /* =========================
       LOGIN
    ========================= */

    public LoginResponse login(LoginRequest request) {

        User user = userService.getUserByUsername(
                request.getUsername()
        );

        if (!user.isActive()) {
            throw new RuntimeException(
                    "User account is inactive"
            );
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {

            throw new RuntimeException(
                    "Invalid username or password"
            );
        }

        String token = jwtService.generateToken(
                user.getUsername(),
                user.getRole().name()
        );

        return new LoginResponse(
                token,
                user.getUsername(),
                user.getRole().name()
        );
    }


    /* =========================
       STUDENT REGISTRATION
    ========================= */

    @Transactional
    public User registerStudent(RegisterRequest request) {

        /*
         * Only students can register
         */
        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole(Role.STUDENT);
        user.setActive(true);

        User savedUser = userRepository.save(user);


        /*
         * Find selected Branch
         */
        Branch branch = branchRepository
                .findById(request.getBranchId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Selected branch not found"
                        )
                );


        /*
         * Find selected Semester
         */
        Semester semester = semesterRepository
                .findById(request.getSemesterId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Selected semester not found"
                        )
                );


        /*
         * Find selected Section
         */
        Section section = sectionRepository
                .findById(request.getSectionId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Selected section not found"
                        )
                );


        /*
         * Create Student Profile
         */
        Student student = new Student();

        student.setName(request.getUsername());

        student.setEmail(
                request.getEmail()
        );

        /*
         * Actual Enrollment Number
         */
        student.setEnrollmentNo(
                request.getEnrollmentNo()
        );

        /*
         * Phone Number
         */
        student.setPhone(
                request.getPhone()
        );

        student.setBranch(branch);

        student.setSemester(semester);

        student.setSection(section);

        student.setUser(savedUser);


        /*
         * Save Student
         */
        studentRepository.save(student);

        return savedUser;
    }
}
