package com.college.college_management_system.controller;

import com.college.college_management_system.entity.Notice;
import com.college.college_management_system.service.FileStorageService;
import com.college.college_management_system.service.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;
    private final FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<Notice> createNotice(
          @Valid  @RequestBody Notice notice) {

        return new ResponseEntity<>(
                noticeService.createNotice(notice),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/upload")
    public ResponseEntity<Notice> uploadNotice(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String targetType,
            @RequestParam(required = false) Long targetId,
            @RequestParam("file") MultipartFile file) throws IOException {

        String attachmentUrl =
                fileStorageService.uploadFile(file);

        Notice notice = new Notice();

        notice.setTitle(title);
        notice.setDescription(description);
        notice.setTargetType(targetType);
        notice.setTargetId(targetId);
        notice.setAttachmentUrl(attachmentUrl);
        notice.setCreatedAt(LocalDateTime.now());

        return new ResponseEntity<>(
                noticeService.createNotice(notice),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Notice>> getAllNotices() {
        return ResponseEntity.ok(
                noticeService.getAllNotices()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notice> getNoticeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                noticeService.getNoticeById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notice> updateNotice(
            @PathVariable Long id,
         @Valid @RequestBody Notice notice) {

        return ResponseEntity.ok(
                noticeService.updateNotice(id, notice)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotice(
            @PathVariable Long id) {

        noticeService.deleteNotice(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/latest")
    public ResponseEntity<List<Notice>> getLatestNotices() {
        return ResponseEntity.ok(
                noticeService.getLatestNotices()
        );
    }

    @GetMapping("/target/{targetType}")
    public ResponseEntity<List<Notice>> getNoticesByTargetType(
            @PathVariable String targetType) {

        return ResponseEntity.ok(
                noticeService.getNoticesByTargetType(targetType)
        );
    }
}
