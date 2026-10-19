package com.college.college_management_system.service;

import com.college.college_management_system.entity.Semester;
import com.college.college_management_system.repository.SemesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemesterService {

    private final SemesterRepository semesterRepository;

    public Semester createSemester(Semester semester) {
        return semesterRepository.save(semester);
    }

    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    public Semester getSemesterById(Long id) {
        return semesterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Semester not found"));
    }

    public Semester updateSemester(Long id, Semester semester) {

        Semester existingSemester = getSemesterById(id);

        existingSemester.setSemesterNumber(semester.getSemesterNumber());
        existingSemester.setAcademicYear(semester.getAcademicYear());

        return semesterRepository.save(existingSemester);
    }

    public void deleteSemester(Long id) {
        Semester semester = getSemesterById(id);
        semesterRepository.delete(semester);
    }
}
