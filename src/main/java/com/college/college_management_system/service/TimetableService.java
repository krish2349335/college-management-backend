package com.college.college_management_system.service;

import com.college.college_management_system.entity.Branch;
import com.college.college_management_system.entity.Section;
import com.college.college_management_system.entity.Semester;
import com.college.college_management_system.entity.Student;
import com.college.college_management_system.entity.Timetable;
import com.college.college_management_system.repository.BranchRepository;
import com.college.college_management_system.repository.SectionRepository;
import com.college.college_management_system.repository.SemesterRepository;
import com.college.college_management_system.repository.StudentRepository;
import com.college.college_management_system.repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimetableService {

    private final TimetableRepository timetableRepository;
    private final BranchRepository branchRepository;
    private final SemesterRepository semesterRepository;
    private final SectionRepository sectionRepository;
    private final StudentRepository studentRepository;


    // ================= CREATE =================

    public Timetable createTimetable(Timetable timetable) {
        return timetableRepository.save(timetable);
    }


    // ================= GET ALL =================

    public List<Timetable> getAllTimetables() {
        return timetableRepository.findAll();
    }


    // ================= GET BY ID =================

    public Timetable getTimetableById(Long id) {

        return timetableRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Timetable not found"));
    }


    // ================= UPDATE =================

    public Timetable updateTimetable(
            Long id,
            Timetable timetable) {

        Timetable existingTimetable =
                getTimetableById(id);

        existingTimetable.setBranch(
                timetable.getBranch());

        existingTimetable.setSemester(
                timetable.getSemester());

        existingTimetable.setSection(
                timetable.getSection());

        existingTimetable.setFileUrl(
                timetable.getFileUrl());

        existingTimetable.setFileType(
                timetable.getFileType());

        existingTimetable.setUploadedAt(
                timetable.getUploadedAt());

        return timetableRepository.save(
                existingTimetable);
    }


    // ================= DELETE =================

    public void deleteTimetable(Long id) {

        Timetable timetable =
                getTimetableById(id);

        timetableRepository.delete(timetable);
    }


    // ================= BY BRANCH =================

    public List<Timetable> getTimetablesByBranch(
            Long branchId) {

        return timetableRepository
                .findByBranchId(branchId);
    }


    // ================= BY SEMESTER =================

    public List<Timetable> getTimetablesBySemester(
            Long semesterId) {

        return timetableRepository
                .findBySemesterId(semesterId);
    }


    // ================= BY SECTION =================

    public List<Timetable> getTimetablesBySection(
            Long sectionId) {

        return timetableRepository
                .findBySectionId(sectionId);
    }


    // ================= BY BRANCH + SEMESTER + SECTION =================

    public Timetable getTimetableByBranchSemesterSection(
            Long branchId,
            Long semesterId,
            Long sectionId) {

        return timetableRepository
                .findByBranchIdAndSemesterIdAndSectionId(
                        branchId,
                        semesterId,
                        sectionId
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Timetable not found"));
    }


    // ================= CREATE WITH DETAILS =================

    public Timetable createTimetableWithDetails(
            Long branchId,
            Long semesterId,
            Long sectionId,
            Timetable timetable) {

        Branch branch =
                branchRepository.findById(branchId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Branch not found"));

        Semester semester =
                semesterRepository.findById(semesterId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Semester not found"));

        Section section =
                sectionRepository.findById(sectionId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Section not found"));

        timetable.setBranch(branch);
        timetable.setSemester(semester);
        timetable.setSection(section);

        return timetableRepository.save(timetable);
    }


    // ================= STUDENT MY TIMETABLE =================

    public Timetable getMyTimetable(String username) {

        Student student =
                studentRepository
                        .findByUserUsername(username)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student not found"));

        return timetableRepository
                .findByBranchIdAndSemesterIdAndSectionId(
                        student.getBranch().getId(),
                        student.getSemester().getId(),
                        student.getSection().getId()
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Timetable not found"));
    }
}
