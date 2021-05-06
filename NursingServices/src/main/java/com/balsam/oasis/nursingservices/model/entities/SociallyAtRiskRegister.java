package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Formula;

import lombok.Data;

@Data
@Entity
@Table(name = "socially_at_risk_register")
public class SociallyAtRiskRegister {

    @Id
    private BigDecimal atRiskId;

    private BigDecimal hospitalId;

    private BigDecimal patientId;

    private BigDecimal sAtRiskType;

    private LocalDateTime addedDate;

    private LocalDateTime removedDate;

    private BigDecimal removedByUser;

    private String notes;

    private BigDecimal answerSessionId;

    private String status;

    private BigDecimal encounterId;

    private String encounterType;

    private BigDecimal genericId;

    private BigDecimal drugId;

    @Formula("(select count(1) from codes_data c where c.code_type = 310  AND  c.prog_code IN (1,0) AND c.code = s_at_risk_type )")
    private Integer g6PDMissingCode;

    @Formula("(select count(1) from codes_data c where c.code_type = 299  AND  c.prog_code = 0 AND c.code = s_at_risk_type )")
    private Integer noAllergiesCode;

    @Formula("(select count(1) from codes_data c where c.code_type <> 299  AND c.code = s_at_risk_type )")
    private Integer g6pdDeficientCode;

    @Formula("(select c.description from codes c where c.code = s_at_risk_type )")
    private String description;

    @Formula("(select count(1) from codes c where c.code_type in (299,478,1687)  AND c.code = s_at_risk_type )")
    private Integer allergiesCodes;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patientId", insertable = false, updatable = false)
    private PatientMasterData patientMasterData;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "genericId", insertable = false, updatable = false)
    private Generics generics;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "drugId", referencedColumnName = "iosMain", insertable = false, updatable = false)
    private IosMain iosMain;

}
