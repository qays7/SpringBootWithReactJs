package com.balsam.oasis.nursingservices.model.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PatientProblems {

    private BigDecimal patientProblemsId;

    private String description;

    public PatientProblems(BigDecimal patientProblemsId, String description) {
        this.patientProblemsId = patientProblemsId;
        this.description = description;
    }

    public PatientProblems() {
    }

}
