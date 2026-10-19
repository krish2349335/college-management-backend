package com.college.college_management_system.repository;

import com.college.college_management_system.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    List<Timetable> findByBranchId(Long branchId);

    List<Timetable> findBySemesterId(Long semesterId);

    List<Timetable> findBySectionId(Long sectionId);

    Optional<Timetable> findByBranchIdAndSemesterIdAndSectionId(
            Long branchId,
            Long semesterId,
            Long sectionId
    );
}