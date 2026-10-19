package com.college.college_management_system.service;

import com.college.college_management_system.entity.ScheduleEntry;
import com.college.college_management_system.entity.Student;
import com.college.college_management_system.repository.ScheduleEntryRepository;
import com.college.college_management_system.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleEntryService {

    private final ScheduleEntryRepository scheduleEntryRepository;

    private final StudentRepository studentRepository;

    public ScheduleEntry createScheduleEntry(ScheduleEntry entry) {
        return scheduleEntryRepository.save(entry);
    }

    public List<ScheduleEntry> getAllScheduleEntries() {
        return scheduleEntryRepository.findAll();
    }

    public ScheduleEntry getScheduleEntryById(Long id) {
        return scheduleEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule entry not found"));
    }

    public ScheduleEntry updateScheduleEntry(Long id, ScheduleEntry entry) {

        ScheduleEntry existingEntry = getScheduleEntryById(id);

        existingEntry.setDay(entry.getDay());
        existingEntry.setStartTime(entry.getStartTime());
        existingEntry.setEndTime(entry.getEndTime());
        existingEntry.setSubject(entry.getSubject());
        existingEntry.setTeacher(entry.getTeacher());
        existingEntry.setClassroom(entry.getClassroom());
        existingEntry.setSection(entry.getSection());

        return scheduleEntryRepository.save(existingEntry);
    }

    public void deleteScheduleEntry(Long id) {
        ScheduleEntry entry = getScheduleEntryById(id);
        scheduleEntryRepository.delete(entry);
    }

    public List<ScheduleEntry> getScheduleBySection(Long sectionId) {
        return scheduleEntryRepository.findBySectionId(sectionId);
    }

    public List<ScheduleEntry> getScheduleBySectionAndDay(
            Long sectionId,
            String day) {

        return scheduleEntryRepository
                .findBySectionIdAndDayIgnoreCase(sectionId, day);
    }

    public List<ScheduleEntry> getMySchedule(String username) {

        Student student = studentRepository
                .findByUserUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        return scheduleEntryRepository.findBySectionId(
                student.getSection().getId()
        );
    }
}
