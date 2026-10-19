package com.college.college_management_system.repository;

import com.college.college_management_system.entity.ExamSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamScheduleRepository
        extends JpaRepository<ExamSchedule, Long> {

    List<ExamSchedule> findByBranchId(Long branchId);

    List<ExamSchedule> findBySemesterId(Long semesterId);

    List<ExamSchedule> findByBranchIdAndSemesterId(
            Long branchId,
            Long semesterId
    );
}
