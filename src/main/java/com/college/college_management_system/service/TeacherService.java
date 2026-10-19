package com.college.college_management_system.service;

import com.college.college_management_system.entity.Teacher;
import com.college.college_management_system.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    public Teacher updateTeacher(Long id, Teacher teacher) {

        Teacher existingTeacher = getTeacherById(id);

        existingTeacher.setEmployeeId(teacher.getEmployeeId());
        existingTeacher.setName(teacher.getName());
        existingTeacher.setEmail(teacher.getEmail());
        existingTeacher.setPhone(teacher.getPhone());
        existingTeacher.setDesignation(teacher.getDesignation());
        existingTeacher.setSittingPlace(teacher.getSittingPlace());
        existingTeacher.setDepartment(teacher.getDepartment());

        // Existing User relationship ko change nahi karna
        // existingTeacher.setUser(teacher.getUser());

        return teacherRepository.save(existingTeacher);
    }

    public void deleteTeacher(Long id) {
        Teacher teacher = getTeacherById(id);
        teacherRepository.delete(teacher);
    }
}