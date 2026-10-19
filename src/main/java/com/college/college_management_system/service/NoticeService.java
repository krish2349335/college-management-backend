package com.college.college_management_system.service;

import com.college.college_management_system.entity.Notice;
import com.college.college_management_system.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Notice createNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    public Notice getNoticeById(Long id) {
        return noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found"));
    }

    public Notice updateNotice(Long id, Notice notice) {

        Notice existingNotice = getNoticeById(id);

        existingNotice.setTitle(notice.getTitle());
        existingNotice.setDescription(notice.getDescription());
        existingNotice.setAttachmentUrl(notice.getAttachmentUrl());
        existingNotice.setTargetType(notice.getTargetType());
        existingNotice.setTargetId(notice.getTargetId());
        existingNotice.setCreatedAt(notice.getCreatedAt());

        return noticeRepository.save(existingNotice);
    }

    public void deleteNotice(Long id) {
        Notice notice = getNoticeById(id);
        noticeRepository.delete(notice);
    }

    public List<Notice> getLatestNotices() {
        return noticeRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Notice> getNoticesByTargetType(String targetType) {
        return noticeRepository.findByTargetTypeOrderByCreatedAtDesc(targetType);
    }
}
