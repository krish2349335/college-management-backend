package com.college.college_management_system.controller;

import com.college.college_management_system.entity.Section;
import com.college.college_management_system.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @PostMapping
    public ResponseEntity<Section> createSection(
            @RequestBody Section section) {

        return new ResponseEntity<>(
                sectionService.createSection(section),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Section>> getAllSections() {

        return ResponseEntity.ok(
                sectionService.getAllSections()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Section> getSectionById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                sectionService.getSectionById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Section> updateSection(
            @PathVariable Long id,
            @RequestBody Section section) {

        return ResponseEntity.ok(
                sectionService.updateSection(id, section)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(
            @PathVariable Long id) {

        sectionService.deleteSection(id);

        return ResponseEntity.noContent().build();
    }
}
