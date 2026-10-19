package com.college.college_management_system.controller;

import com.college.college_management_system.entity.Classroom;
import com.college.college_management_system.service.ClassroomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping
    public ResponseEntity<Classroom> createClassroom(
          @Valid  @RequestBody Classroom classroom) {

        return new ResponseEntity<>(
                classroomService.createClassroom(classroom),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Classroom>> getAllClassrooms() {

        return ResponseEntity.ok(
                classroomService.getAllClassrooms()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassroomById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                classroomService.getClassroomById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classroom> updateClassroom(
            @PathVariable Long id,
           @Valid @RequestBody Classroom classroom) {

        return ResponseEntity.ok(
                classroomService.updateClassroom(id, classroom)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(
            @PathVariable Long id) {

        classroomService.deleteClassroom(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/room/{roomNumber}")
    public ResponseEntity<Classroom> getClassroomByRoomNumber(
            @PathVariable String roomNumber) {

        return ResponseEntity.ok(
                classroomService.getClassroomByRoomNumber(roomNumber)
        );
    }
}
