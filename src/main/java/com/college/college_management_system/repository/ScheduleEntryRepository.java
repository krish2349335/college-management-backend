package com.college.college_management_system.repository;

import com.college.college_management_system.entity.ScheduleEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleEntryRepository extends JpaRepository<ScheduleEntry, Long> {

    List<ScheduleEntry> findBySectionId(Long sectionId);

    List<ScheduleEntry> findBySectionIdAndDayIgnoreCase(
            Long sectionId,
            String day
    );
}
