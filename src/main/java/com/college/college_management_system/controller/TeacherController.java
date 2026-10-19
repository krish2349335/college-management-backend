package com.college.college_management_system.controller;

import com.college.college_management_system.entity.Teacher;
import com.college.college_management_system.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(
          @Valid @RequestBody Teacher teacher) {

        return new ResponseEntity<>(
                teacherService.createTeacher(teacher),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {

        return ResponseEntity.ok(
                teacherService.getAllTeachers()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                teacherService.getTeacherById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(
            @PathVariable Long id,
          @Valid  @RequestBody Teacher teacher) {

        return ResponseEntity.ok(
                teacherService.updateTeacher(id, teacher)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(
            @PathVariable Long id) {

        teacherService.deleteTeacher(id);

        return ResponseEntity.noContent().build();
    }
}
