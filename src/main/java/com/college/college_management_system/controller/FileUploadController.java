package com.college.college_management_system.controller;

import com.college.college_management_system.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(
            @RequestParam("file") MultipartFile file) throws IOException {

        String fileUrl = fileStorageService.uploadFile(file);

        return ResponseEntity.ok(
                Map.of(
                        "message", "File uploaded successfully",
                        "fileUrl", fileUrl
                )
        );
    }
}
