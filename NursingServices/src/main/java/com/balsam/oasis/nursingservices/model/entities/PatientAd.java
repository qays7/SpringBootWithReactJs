package com.balsam.oasis.nursingservices.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PATIENT_AD")
public class PatientAd implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ADMISSION_NO")
    private BigDecimal admissionNo;

    private BigDecimal hospitalId;

    @Column(name = "PATIENT_ID")
    private BigDecimal patientId;

    @Column(name = "EPISODE_NO")
    private BigDecimal episodeNo;

    private BigDecimal outcome;

    private LocalDateTime clinicalDischargeDate;

    private LocalDateTime financialDischargeDate;

    private LocalDateTime physicalDischargeDate;

    @Column(name = "EST_DISCHARGE_DATE")
    private LocalDateTime estDischargeDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PATIENT_ID", insertable = false, updatable = false)
    private PatientMasterData patientMasterData;

}
