package com.college.college_management_system.controller;

import com.college.college_management_system.entity.Branch;
import com.college.college_management_system.service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<Branch> createBranch(
          @Valid @RequestBody Branch branch) {

        return new ResponseEntity<>(
                branchService.createBranch(branch),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Branch>> getAllBranches() {

        return ResponseEntity.ok(
                branchService.getAllBranches()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                branchService.getBranchById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Branch> updateBranch(
            @PathVariable Long id,
          @Valid  @RequestBody Branch branch) {

        return ResponseEntity.ok(
                branchService.updateBranch(id, branch)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(
            @PathVariable Long id) {

        branchService.deleteBranch(id);

        return ResponseEntity.noContent().build();
    }
}
