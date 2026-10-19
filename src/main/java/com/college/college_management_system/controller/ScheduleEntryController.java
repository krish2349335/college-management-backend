package com.college.college_management_system.controller;

import com.college.college_management_system.entity.ScheduleEntry;
import com.college.college_management_system.service.ScheduleEntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule-entries")
@RequiredArgsConstructor
public class ScheduleEntryController {

    private final ScheduleEntryService scheduleEntryService;


    // ================= CREATE =================

    @PostMapping
    public ResponseEntity<ScheduleEntry> createScheduleEntry(
            @Valid @RequestBody ScheduleEntry entry) {

        return new ResponseEntity<>(
                scheduleEntryService.createScheduleEntry(entry),
                HttpStatus.CREATED
        );
    }


    // ================= GET ALL =================

    @GetMapping
    public ResponseEntity<List<ScheduleEntry>> getAllScheduleEntries() {

        return ResponseEntity.ok(
                scheduleEntryService.getAllScheduleEntries()
        );
    }


    // ================= MY SCHEDULE =================

    @GetMapping("/me")
    public ResponseEntity<List<ScheduleEntry>> getMySchedule(
            Authentication authentication) {

        String username = authentication.getName();

        return ResponseEntity.ok(
                scheduleEntryService.getMySchedule(username)
        );
    }


    // ================= BY SECTION + DAY =================

    @GetMapping("/section/{sectionId}/day/{day}")
    public ResponseEntity<List<ScheduleEntry>>
    getScheduleBySectionAndDay(
            @PathVariable Long sectionId,
            @PathVariable String day) {

        return ResponseEntity.ok(
                scheduleEntryService.getScheduleBySectionAndDay(
                        sectionId,
                        day
                )
        );
    }


    // ================= BY SECTION =================

    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<ScheduleEntry>>
    getScheduleBySection(
            @PathVariable Long sectionId) {

        return ResponseEntity.ok(
                scheduleEntryService.getScheduleBySection(
                        sectionId
                )
        );
    }


    // ================= GET BY ID =================

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleEntry>
    getScheduleEntryById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                scheduleEntryService.getScheduleEntryById(id)
        );
    }


    // ================= UPDATE =================

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleEntry>
    updateScheduleEntry(
            @PathVariable Long id,
            @Valid @RequestBody ScheduleEntry entry) {

        return ResponseEntity.ok(
                scheduleEntryService.updateScheduleEntry(
                        id,
                        entry
                )
        );
    }


    // ================= DELETE =================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduleEntry(
            @PathVariable Long id) {

        scheduleEntryService.deleteScheduleEntry(id);

        return ResponseEntity.noContent().build();
    }
}