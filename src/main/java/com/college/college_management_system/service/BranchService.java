package com.college.college_management_system.service;

import com.college.college_management_system.entity.Branch;
import com.college.college_management_system.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;

    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public Branch getBranchById(Long id) {
        return branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found"));
    }

    public Branch updateBranch(Long id, Branch branch) {

        Branch existingBranch = getBranchById(id);

        existingBranch.setName(branch.getName());
        existingBranch.setCode(branch.getCode());
        existingBranch.setDepartment(branch.getDepartment());

        return branchRepository.save(existingBranch);
    }

    public void deleteBranch(Long id) {
        Branch branch = getBranchById(id);
        branchRepository.delete(branch);
    }
}
