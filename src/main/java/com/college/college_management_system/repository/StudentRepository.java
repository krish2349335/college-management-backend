package com.college.college_management_system.repository;

import com.college.college_management_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUserUsername(String username);
}
