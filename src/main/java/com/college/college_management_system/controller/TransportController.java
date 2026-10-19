package com.college.college_management_system.controller;

import com.college.college_management_system.entity.Transport;
import com.college.college_management_system.service.TransportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transports")
@RequiredArgsConstructor
public class TransportController {

    private final TransportService transportService;

    @PostMapping
    public ResponseEntity<Transport> createTransport(
           @Valid @RequestBody Transport transport) {

        return new ResponseEntity<>(
                transportService.createTransport(transport),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Transport>> getAllTransports() {
        return ResponseEntity.ok(
                transportService.getAllTransports()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transport> getTransportById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                transportService.getTransportById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transport> updateTransport(
            @PathVariable Long id,
          @Valid  @RequestBody Transport transport) {

        return ResponseEntity.ok(
                transportService.updateTransport(id, transport)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransport(
            @PathVariable Long id) {

        transportService.deleteTransport(id);
        return ResponseEntity.noContent().build();
    }
}
