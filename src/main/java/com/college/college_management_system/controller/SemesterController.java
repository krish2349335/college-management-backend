package com.college.college_management_system.controller;

import com.college.college_management_system.entity.Semester;
import com.college.college_management_system.service.SemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/semesters")
@RequiredArgsConstructor
public class SemesterController {

    private final SemesterService semesterService;

    @PostMapping
    public ResponseEntity<Semester> createSemester(
            @RequestBody Semester semester) {

        return new ResponseEntity<>(
                semesterService.createSemester(semester),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Semester>> getAllSemesters() {

        return ResponseEntity.ok(
                semesterService.getAllSemesters()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Semester> getSemesterById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                semesterService.getSemesterById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Semester> updateSemester(
            @PathVariable Long id,
            @RequestBody Semester semester) {

        return ResponseEntity.ok(
                semesterService.updateSemester(id, semester)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSemester(
            @PathVariable Long id) {

        semesterService.deleteSemester(id);

        return ResponseEntity.noContent().build();
    }
}
