package com.collegemanagement.feesmanagement.services.impl;

import com.collegemanagement.feesmanagement.entity.Fees;
import com.collegemanagement.feesmanagement.entity.Student;
import com.collegemanagement.feesmanagement.exception.FeesRecordNotFoundException;
import com.collegemanagement.feesmanagement.exception.StudentNotFoundException;
import com.collegemanagement.feesmanagement.repository.FeesRepository;
import com.collegemanagement.feesmanagement.repository.StudentRepository;
import com.collegemanagement.feesmanagement.services.FeesServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeesServicesImpl implements FeesServices {
    private FeesRepository feesRepository;
    private StudentRepository studentRepository;

    @Override
    public Fees insertFees(Integer sid, Fees fees) {
        Student student = studentRepository.findById(sid).orElseThrow(()-> new StudentNotFoundException("Student with id "+sid+" doesn't exist"));

        fees.setStudent(student);
        return feesRepository.save(fees);
    }

    @Override
    public List<Fees> fetchAllFees() {
        return feesRepository.findAll();
    }

    @Override
    public Fees fetchFeesById(Integer id) {

        return feesRepository.findById(id).orElseThrow(()->new FeesRecordNotFoundException("Fees record with id "+id+" doesn't exist"));
    }

    @Override
    public Fees updateFeesDetails(Integer id, Fees fees) {
        Fees existingFees = feesRepository.findById(id).orElseThrow(()-> new FeesRecordNotFoundException("Fees record with id "+id+" doesn't exist"));
        if(fees.getReceiptNumber() != null){
            existingFees.setReceiptNumber(fees.getReceiptNumber());
        }

        if(fees.getPaymentDate() != null){
            existingFees.setPaymentDate(fees.getPaymentDate());
        }

        if(fees.getPaidAmount() != null){
            existingFees.setPaidAmount(fees.getPaidAmount());
        }

        if(fees.getRemainingAmount() != null){
            existingFees.setRemainingAmount(fees.getRemainingAmount());
        }

        if(fees.getPaymentMode() != null){
            existingFees.setPaymentMode(fees.getPaymentMode());
        }

        if(fees.getRemarks() != null){
            existingFees.setRemarks(fees.getRemarks());
        }
        return feesRepository.save(existingFees);
    }

    @Override
    public void removeFeesEntry(Integer id) {

        if(!feesRepository.existsById(id)){
            throw new FeesRecordNotFoundException("Fees record with id "+id+" doesn't exist");
        }

        feesRepository.deleteById(id);
    }
}
