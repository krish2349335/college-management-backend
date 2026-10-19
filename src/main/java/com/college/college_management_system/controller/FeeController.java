package com.college.college_management_system.controller;

import com.college.college_management_system.entity.Fee;
import com.college.college_management_system.service.FeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fees")
@RequiredArgsConstructor
public class FeeController {

    private final FeeService feeService;

    @PostMapping
    public ResponseEntity<Fee> createFee(
          @Valid  @RequestBody Fee fee) {

        return new ResponseEntity<>(
                feeService.createFee(fee),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Fee>> getAllFees() {

        return ResponseEntity.ok(
                feeService.getAllFees()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fee> getFeeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                feeService.getFeeById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fee> updateFee(
            @PathVariable Long id,
         @Valid @RequestBody Fee fee) {

        return ResponseEntity.ok(
                feeService.updateFee(id, fee)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFee(
            @PathVariable Long id) {

        feeService.deleteFee(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Fee>> getFeesByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                feeService.getFeesByStudent(studentId)
        );
    }

    @GetMapping("/student/{studentId}/semester/{semesterId}")
    public ResponseEntity<List<Fee>> getFeesByStudentAndSemester(
            @PathVariable Long studentId,
            @PathVariable Long semesterId) {

        return ResponseEntity.ok(
                feeService.getFeesByStudentAndSemester(studentId, semesterId)
        );
    }

    @GetMapping("/me")
    public ResponseEntity<List<Fee>> getMyFees(
            Authentication authentication) {

        String username = authentication.getName();

        return ResponseEntity.ok(
                feeService.getMyFees(username)
        );
    }
}
