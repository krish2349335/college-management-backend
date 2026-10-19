package com.college.college_management_system.controller;

import com.college.college_management_system.entity.Timetable;
import com.college.college_management_system.service.FileStorageService;
import com.college.college_management_system.service.TimetableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/timetables")
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableService timetableService;
    private final FileStorageService fileStorageService;


    // ================= CREATE TIMETABLE =================

    @PostMapping
    public ResponseEntity<Timetable> createTimetable(
            @Valid @RequestBody Timetable timetable) {

        timetable.setUploadedAt(LocalDateTime.now());

        return new ResponseEntity<>(
                timetableService.createTimetable(timetable),
                HttpStatus.CREATED
        );
    }


    // ================= UPLOAD TIMETABLE =================

    @PostMapping("/upload")
    public ResponseEntity<Timetable> uploadTimetable(
            @RequestParam Long branchId,
            @RequestParam Long semesterId,
            @RequestParam Long sectionId,
            @RequestParam("file") MultipartFile file) throws IOException {

        String fileUrl =
                fileStorageService.uploadFile(file);

        Timetable timetable = new Timetable();

        timetable.setFileUrl(fileUrl);
        timetable.setFileType(file.getContentType());
        timetable.setUploadedAt(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        timetableService.createTimetableWithDetails(
                                branchId,
                                semesterId,
                                sectionId,
                                timetable
                        )
                );
    }


    // ================= GET ALL =================

    @GetMapping
    public ResponseEntity<List<Timetable>> getAllTimetables() {

        return ResponseEntity.ok(
                timetableService.getAllTimetables()
        );
    }


    // ================= MY TIMETABLE =================

    @GetMapping("/me")
    public ResponseEntity<List<Timetable>> getMyTimetable(
            Authentication authentication) {

        Timetable timetable =
                timetableService.getMyTimetable(
                        authentication.getName()
                );

        return ResponseEntity.ok(
                List.of(timetable)
        );
    }


    // ================= GET BY ID =================

    @GetMapping("/{id}")
    public ResponseEntity<Timetable> getTimetableById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                timetableService.getTimetableById(id)
        );
    }


    // ================= UPDATE =================

    @PutMapping("/{id}")
    public ResponseEntity<Timetable> updateTimetable(
            @PathVariable Long id,
            @Valid @RequestBody Timetable timetable) {

        return ResponseEntity.ok(
                timetableService.updateTimetable(
                        id,
                        timetable
                )
        );
    }


    // ================= DELETE =================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimetable(
            @PathVariable Long id) {

        timetableService.deleteTimetable(id);

        return ResponseEntity.noContent().build();
    }


    // ================= BY BRANCH =================

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<Timetable>> getTimetablesByBranch(
            @PathVariable Long branchId) {

        return ResponseEntity.ok(
                timetableService.getTimetablesByBranch(
                        branchId
                )
        );
    }


    // ================= BY SEMESTER =================

    @GetMapping("/semester/{semesterId}")
    public ResponseEntity<List<Timetable>> getTimetablesBySemester(
            @PathVariable Long semesterId) {

        return ResponseEntity.ok(
                timetableService.getTimetablesBySemester(
                        semesterId
                )
        );
    }


    // ================= BY SECTION =================

    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<Timetable>> getTimetablesBySection(
            @PathVariable Long sectionId) {

        return ResponseEntity.ok(
                timetableService.getTimetablesBySection(
                        sectionId
                )
        );
    }


    // ================= EXACT TIMETABLE =================

    @GetMapping(
            "/branch/{branchId}/semester/{semesterId}/section/{sectionId}"
    )
    public ResponseEntity<Timetable>
    getTimetableByBranchSemesterSection(
            @PathVariable Long branchId,
            @PathVariable Long semesterId,
            @PathVariable Long sectionId) {

        return ResponseEntity.ok(
                timetableService
                        .getTimetableByBranchSemesterSection(
                                branchId,
                                semesterId,
                                sectionId
                        )
        );
    }
}
