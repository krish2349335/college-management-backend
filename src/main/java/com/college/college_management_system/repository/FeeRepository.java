package com.college.college_management_system.repository;

import com.college.college_management_system.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeeRepository extends JpaRepository<Fee, Long> {

    List<Fee> findByStudentId(Long studentId);

    List<Fee> findByStudentIdAndSemesterId(Long studentId, Long semesterId);


}
