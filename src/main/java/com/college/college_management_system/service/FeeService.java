package com.college.college_management_system.service;

import com.college.college_management_system.entity.Fee;
import com.college.college_management_system.entity.Student;
import com.college.college_management_system.repository.FeeRepository;
import com.college.college_management_system.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeeService {

    private final FeeRepository feeRepository;

    private final StudentRepository studentRepository;

    public Fee createFee(Fee fee) {

        if (fee.getPaidAmount().compareTo(fee.getTotalAmount()) > 0) {
            throw new RuntimeException("Paid amount cannot be greater than total amount");
        }

        fee.setRemainingAmount(
                fee.getTotalAmount().subtract(fee.getPaidAmount())
        );

        if (fee.getRemainingAmount().compareTo(BigDecimal.ZERO) == 0) {
            fee.setStatus("PAID");
        } else {
            fee.setStatus("PENDING");
        }

        return feeRepository.save(fee);
    }

    public List<Fee> getAllFees() {
        return feeRepository.findAll();
    }

    public Fee getFeeById(Long id) {
        return feeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fee record not found"));
    }

    public Fee updateFee(Long id, Fee fee) {

        Fee existingFee = getFeeById(id);

        if (fee.getPaidAmount().compareTo(fee.getTotalAmount()) > 0) {
            throw new RuntimeException("Paid amount cannot be greater than total amount");
        }

        existingFee.setStudent(fee.getStudent());
        existingFee.setSemester(fee.getSemester());
        existingFee.setTotalAmount(fee.getTotalAmount());
        existingFee.setPaidAmount(fee.getPaidAmount());
        existingFee.setRemainingAmount(
                fee.getTotalAmount().subtract(fee.getPaidAmount())
        );
        existingFee.setDueDate(fee.getDueDate());

        if (existingFee.getRemainingAmount().compareTo(BigDecimal.ZERO) == 0) {
            existingFee.setStatus("PAID");
        } else {
            existingFee.setStatus("PENDING");
        }

        return feeRepository.save(existingFee);
    }
    public void deleteFee(Long id) {
        Fee fee = getFeeById(id);
        feeRepository.delete(fee);
    }

    public List<Fee> getFeesByStudent(Long studentId) {
        return feeRepository.findByStudentId(studentId);
    }

    public List<Fee> getFeesByStudentAndSemester(Long studentId, Long semesterId) {
        return feeRepository.findByStudentIdAndSemesterId(studentId, semesterId);
    }

    public List<Fee> getMyFees(String username) {

        Student student = studentRepository
                .findByUserUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        return feeRepository.findByStudentId(student.getId());
    }
}
