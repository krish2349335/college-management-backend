package com.college.college_management_system.service;

import com.college.college_management_system.entity.Branch;
import com.college.college_management_system.entity.ExamSchedule;
import com.college.college_management_system.entity.Semester;
import com.college.college_management_system.entity.Student;
import com.college.college_management_system.repository.BranchRepository;
import com.college.college_management_system.repository.ExamScheduleRepository;
import com.college.college_management_system.repository.SemesterRepository;
import com.college.college_management_system.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamScheduleService {

    private final ExamScheduleRepository examScheduleRepository;
    private final BranchRepository branchRepository;
    private final SemesterRepository semesterRepository;
    private final StudentRepository studentRepository;


    // CREATE
    public ExamSchedule createExamSchedule(
            Long branchId,
            Long semesterId,
            String examType,
            String fileUrl,
            String fileType
    ) {

        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() ->
                        new RuntimeException("Branch not found"));

        Semester semester = semesterRepository.findById(semesterId)
                .orElseThrow(() ->
                        new RuntimeException("Semester not found"));

        ExamSchedule examSchedule = new ExamSchedule();

        examSchedule.setBranch(branch);
        examSchedule.setSemester(semester);
        examSchedule.setExamType(examType);
        examSchedule.setFileUrl(fileUrl);
        examSchedule.setFileType(fileType);
        examSchedule.setUploadedAt(LocalDateTime.now());

        return examScheduleRepository.save(examSchedule);
    }


    // GET ALL
    public List<ExamSchedule> getAllExamSchedules() {

        return examScheduleRepository.findAll();
    }


    // GET BY ID
    public ExamSchedule getExamScheduleById(Long id) {

        return examScheduleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Exam schedule not found"));
    }


    // UPDATE
    public ExamSchedule updateExamSchedule(
            Long id,
            ExamSchedule examSchedule
    ) {

        ExamSchedule existing =
                getExamScheduleById(id);

        existing.setBranch(examSchedule.getBranch());
        existing.setSemester(examSchedule.getSemester());
        existing.setExamType(examSchedule.getExamType());
        existing.setFileUrl(examSchedule.getFileUrl());
        existing.setFileType(examSchedule.getFileType());
        existing.setUploadedAt(examSchedule.getUploadedAt());

        return examScheduleRepository.save(existing);
    }


    // DELETE
    public void deleteExamSchedule(Long id) {

        ExamSchedule examSchedule =
                getExamScheduleById(id);

        examScheduleRepository.delete(examSchedule);
    }


    // GET BY BRANCH
    public List<ExamSchedule> getExamSchedulesByBranch(
            Long branchId
    ) {

        return examScheduleRepository
                .findByBranchId(branchId);
    }


    // GET BY SEMESTER
    public List<ExamSchedule> getExamSchedulesBySemester(
            Long semesterId
    ) {

        return examScheduleRepository
                .findBySemesterId(semesterId);
    }


    // GET BY BRANCH + SEMESTER
    public List<ExamSchedule> getExamSchedulesByBranchAndSemester(
            Long branchId,
            Long semesterId
    ) {

        return examScheduleRepository
                .findByBranchIdAndSemesterId(
                        branchId,
                        semesterId
                );
    }


    // GET STUDENT'S EXAM SCHEDULE
    public List<ExamSchedule> getMyExamSchedules(
            String username
    ) {

        Student student = studentRepository
                .findByUserUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        return examScheduleRepository
                .findByBranchIdAndSemesterId(
                        student.getBranch().getId(),
                        student.getSemester().getId()
                );
    }
}