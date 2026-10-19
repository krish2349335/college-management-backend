package com.college.college_management_system.controller;

import com.college.college_management_system.entity.Subject;
import com.college.college_management_system.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Subject> createSubject(
            @Valid @RequestBody Subject subject
    ) {
        return new ResponseEntity<>(
                subjectService.createSubject(subject),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return ResponseEntity.ok(
                subjectService.getAllSubjects()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                subjectService.getSubjectById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(
            @PathVariable Long id,
            @Valid @RequestBody Subject subject
    ) {
        return ResponseEntity.ok(
                subjectService.updateSubject(id, subject)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(
            @PathVariable Long id
    ) {
        subjectService.deleteSubject(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<Subject>> getSubjectsByBranch(
            @PathVariable Long branchId
    ) {
        return ResponseEntity.ok(
                subjectService.getSubjectsByBranch(branchId)
        );
    }

    @GetMapping("/semester/{semesterId}")
    public ResponseEntity<List<Subject>> getSubjectsBySemester(
            @PathVariable Long semesterId
    ) {
        return ResponseEntity.ok(
                subjectService.getSubjectsBySemester(semesterId)
        );
    }

    @GetMapping("/branch/{branchId}/semester/{semesterId}")
    public ResponseEntity<List<Subject>> getSubjectsByBranchAndSemester(
            @PathVariable Long branchId,
            @PathVariable Long semesterId
    ) {
        return ResponseEntity.ok(
                subjectService.getSubjectsByBranchAndSemester(
                        branchId,
                        semesterId
                )
        );
    }

    @GetMapping(
            "/branch/{branchId}/semester/{semesterId}/section/{sectionId}"
    )
    public ResponseEntity<List<Subject>>
    getSubjectsByBranchSemesterAndSection(
            @PathVariable Long branchId,
            @PathVariable Long semesterId,
            @PathVariable Long sectionId
    ) {
        return ResponseEntity.ok(
                subjectService.getSubjectsByBranchSemesterAndSection(
                        branchId,
                        semesterId,
                        sectionId
                )
        );
    }
}
