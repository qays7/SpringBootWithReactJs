package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "problem_patients")
public class ProblemPatients {

    @Id
    private BigDecimal problemPatientId;

    private BigDecimal hospitalId;

    private BigDecimal patientId;

    private BigDecimal patProbAttenType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patProbAttenType", referencedColumnName = "code", updatable = false, insertable = false)
    private Codes codes;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patientId", insertable = false, updatable = false)
    private PatientMasterData patientMasterData;

    public String getDescription() {
        return codes.getDescription() + " (warning)";
    }

}
