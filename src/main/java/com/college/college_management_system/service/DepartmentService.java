package com.college.college_management_system.service;

import com.college.college_management_system.entity.Department;
import com.college.college_management_system.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public Department updateDepartment(Long id, Department department) {

        Department existingDepartment = getDepartmentById(id);

        existingDepartment.setName(department.getName());
        existingDepartment.setCode(department.getCode());
        existingDepartment.setDescription(department.getDescription());

        return departmentRepository.save(existingDepartment);
    }

    public void deleteDepartment(Long id) {
        Department department = getDepartmentById(id);
        departmentRepository.delete(department);
    }
}
