package com.college.college_management_system.repository;

import com.college.college_management_system.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findAllByOrderByCreatedAtDesc();

    List<Notice> findByTargetTypeOrderByCreatedAtDesc(String targetType);
}
