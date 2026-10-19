package com.college.college_management_system.repository;

import com.college.college_management_system.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByOrderByEventDateAsc();

    List<Event> findByEventDateGreaterThanEqualOrderByEventDateAsc(
            LocalDate date
    );
}
