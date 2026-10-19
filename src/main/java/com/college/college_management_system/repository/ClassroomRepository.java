package com.college.college_management_system.repository;

import com.college.college_management_system.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    Optional<Classroom> findByRoomNumber(String roomNumber);
}
