package com.college.college_management_system.controller;

import com.college.college_management_system.entity.ExamSchedule;
import com.college.college_management_system.service.ExamScheduleService;
import com.college.college_management_system.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/exam-schedules")
@RequiredArgsConstructor
public class ExamScheduleController {

    private final ExamScheduleService examScheduleService;
    private final FileStorageService fileStorageService;


    // ===============================
    // UPLOAD EXAM SCHEDULE
    // ===============================
    @PostMapping("/upload")
    public ResponseEntity<ExamSchedule> uploadExamSchedule(

            @RequestParam Long branchId,

            @RequestParam Long semesterId,

            @RequestParam String examType,

            @RequestParam("file") MultipartFile file

    ) throws IOException {

        String fileUrl =
                fileStorageService.uploadFile(file);

        ExamSchedule examSchedule =
                examScheduleService.createExamSchedule(
                        branchId,
                        semesterId,
                        examType,
                        fileUrl,
                        file.getContentType()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(examSchedule);
    }


    // ===============================
    // GET ALL EXAM SCHEDULES
    // ===============================
    @GetMapping
    public ResponseEntity<List<ExamSchedule>>
    getAllExamSchedules() {

        return ResponseEntity.ok(
                examScheduleService.getAllExamSchedules()
        );
    }


    // ===============================
    // GET MY EXAM SCHEDULE
    // ===============================
    @GetMapping("/me")
    public ResponseEntity<List<ExamSchedule>>
    getMyExamSchedules(
            Authentication authentication
    ) {

        return ResponseEntity.ok(
                examScheduleService.getMyExamSchedules(
                        authentication.getName()
                )
        );
    }


    // ===============================
    // GET BY ID
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<ExamSchedule>
    getExamScheduleById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                examScheduleService
                        .getExamScheduleById(id)
        );
    }


    // ===============================
    // UPDATE
    // ===============================
    @PutMapping("/{id}")
    public ResponseEntity<ExamSchedule>
    updateExamSchedule(
            @PathVariable Long id,
            @RequestBody ExamSchedule examSchedule
    ) {

        return ResponseEntity.ok(
                examScheduleService
                        .updateExamSchedule(
                                id,
                                examSchedule
                        )
        );
    }


    // ===============================
    // DELETE
    // ===============================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    deleteExamSchedule(
            @PathVariable Long id
    ) {

        examScheduleService
                .deleteExamSchedule(id);

        return ResponseEntity
                .noContent()
                .build();
    }


    // ===============================
    // GET BY BRANCH
    // ===============================
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<ExamSchedule>>
    getByBranch(
            @PathVariable Long branchId
    ) {

        return ResponseEntity.ok(
                examScheduleService
                        .getExamSchedulesByBranch(
                                branchId
                        )
        );
    }


    // ===============================
    // GET BY SEMESTER
    // ===============================
    @GetMapping("/semester/{semesterId}")
    public ResponseEntity<List<ExamSchedule>>
    getBySemester(
            @PathVariable Long semesterId
    ) {

        return ResponseEntity.ok(
                examScheduleService
                        .getExamSchedulesBySemester(
                                semesterId
                        )
        );
    }


    // ===============================
    // GET BY BRANCH + SEMESTER
    // ===============================
    @GetMapping(
            "/branch/{branchId}/semester/{semesterId}"
    )
    public ResponseEntity<List<ExamSchedule>>
    getByBranchAndSemester(
            @PathVariable Long branchId,
            @PathVariable Long semesterId
    ) {

        return ResponseEntity.ok(
                examScheduleService
                        .getExamSchedulesByBranchAndSemester(
                                branchId,
                                semesterId
                        )
        );
    }
}
