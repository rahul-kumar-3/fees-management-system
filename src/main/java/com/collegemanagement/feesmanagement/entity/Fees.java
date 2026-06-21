package com.collegemanagement.feesmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name = "fees_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feesId;
    private String receiptNumber;
    private LocalDate paymentDate;
    private Double paidAmount;
    private Double remainingAmount;
    private String paymentMode;
    private String remarks;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="student_id")
    @JsonIgnoreProperties("fees")
    private Student student;

}
