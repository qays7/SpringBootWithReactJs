package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name = "PATIENT_ELIGIBILITY")
public class PatientEligibility {

    @Id
    private BigDecimal patientEligibilityId;

    private BigDecimal hospitalId;

    @Column(name = "PATIENT_ID")
    private BigDecimal patientId;

    @Column(name = "EPISODE_NO")
    private BigDecimal episodeNo;

    @Column(name = "ADMISSION_NO")
    private BigDecimal admissionNo;

    private BigDecimal workEntity;

    private String consultantId;

    @Column(name = "ATTENDANCE_TYPE")
    private String attendenceType;

    private String eligibilityStatus;

    private LocalDate eligibilityStart;

    private LocalDate eligibilityEnd;

    private String responsibility;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "consultantId", referencedColumnName = "staffId", insertable = false, updatable = false)
    private StaffMaster staffMaster;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PATIENT_ID", insertable = false, updatable = false)
    private PatientMasterData patientMasterData;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "ADMISSION_NO", insertable = false, updatable = false)
    // private PatientAd patientAd;

}
