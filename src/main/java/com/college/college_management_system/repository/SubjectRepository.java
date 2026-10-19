package com.college.college_management_system.repository;

import com.college.college_management_system.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByBranchId(Long branchId);

    List<Subject> findBySemesterId(Long semesterId);

    List<Subject> findByBranchIdAndSemesterId(
            Long branchId,
            Long semesterId
    );

    List<Subject> findByBranchIdAndSemesterIdAndSectionId(
            Long branchId,
            Long semesterId,
            Long sectionId
    );
}
