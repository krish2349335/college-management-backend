package com.college.college_management_system.service;

import com.college.college_management_system.entity.Subject;
import com.college.college_management_system.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
    }

    public Subject updateSubject(Long id, Subject subject) {

        Subject existingSubject = getSubjectById(id);

        existingSubject.setName(subject.getName());
        existingSubject.setCode(subject.getCode());
        existingSubject.setCredits(subject.getCredits());
        existingSubject.setBranch(subject.getBranch());
        existingSubject.setSemester(subject.getSemester());
        existingSubject.setSection(subject.getSection());
        existingSubject.setTeacher(subject.getTeacher());

        return subjectRepository.save(existingSubject);
    }

    public void deleteSubject(Long id) {

        Subject subject = getSubjectById(id);

        subjectRepository.delete(subject);
    }

    public List<Subject> getSubjectsByBranch(Long branchId) {
        return subjectRepository.findByBranchId(branchId);
    }

    public List<Subject> getSubjectsBySemester(Long semesterId) {
        return subjectRepository.findBySemesterId(semesterId);
    }

    public List<Subject> getSubjectsByBranchAndSemester(
            Long branchId,
            Long semesterId
    ) {
        return subjectRepository.findByBranchIdAndSemesterId(
                branchId,
                semesterId
        );
    }

    public List<Subject> getSubjectsByBranchSemesterAndSection(
            Long branchId,
            Long semesterId,
            Long sectionId
    ) {
        return subjectRepository
                .findByBranchIdAndSemesterIdAndSectionId(
                        branchId,
                        semesterId,
                        sectionId
                );
    }
}