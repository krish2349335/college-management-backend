package com.college.college_management_system.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path uploadDirectory = Paths.get("uploads");

    public FileStorageService() throws IOException {
        Files.createDirectories(uploadDirectory);
    }

    public String uploadFile(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        String contentType = file.getContentType();

        if (contentType == null ||
                (!contentType.equals("application/pdf")
                        && !contentType.equals("image/jpeg")
                        && !contentType.equals("image/png"))) {

            throw new RuntimeException(
                    "Only PDF, JPG and PNG files are allowed"
            );
        }

        String originalFileName = file.getOriginalFilename();

        String extension = "";

        if (originalFileName != null &&
                originalFileName.contains(".")) {

            extension = originalFileName.substring(
                    originalFileName.lastIndexOf(".")
            );
        }

        String fileName = UUID.randomUUID() + extension;

        Path filePath = uploadDirectory.resolve(fileName);

        Files.copy(
                file.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        return "/uploads/" + fileName;
    }
}
