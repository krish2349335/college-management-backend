package com.college.college_management_system.controller;

import com.college.college_management_system.entity.Student;
import com.college.college_management_system.service.FileStorageService;
import com.college.college_management_system.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<Student> createStudent(
            @RequestBody Student student) {

        return new ResponseEntity<>(
                studentService.createStudent(student),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/{id}/profile-photo")
    public ResponseEntity<Student> uploadProfilePhoto(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) throws IOException {

        Student student = studentService.getStudentById(id);

        String photoUrl = fileStorageService.uploadFile(file);

        student.setProfilePhotoUrl(photoUrl);

        Student updatedStudent = studentService.updateStudent(id, student);

        return ResponseEntity.ok(updatedStudent);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(
                studentService.getAllStudents()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                studentService.getStudentById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        return ResponseEntity.ok(
                studentService.updateStudent(id, student)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<Student> getMyProfile(
            Authentication authentication) {

        String username = authentication.getName();

        return ResponseEntity.ok(
                studentService.getStudentByUsername(username)
        );
    }
}
