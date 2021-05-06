package com.balsam.oasis.nursingservices.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Formula;

import lombok.Data;

@Data
@Entity
@Table(name = "PATIENT_ORDERS")
public class PatientOrders implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    private BigDecimal orderLine;

    private BigDecimal hospitalId;

    private BigDecimal ios;

    private BigDecimal patientId;

    private BigDecimal episodeNo;

    private String attendanceType;

    private String status;

    private BigDecimal unitsGiven;

    private String nurseReviewRequired;

    private String urgentFlag;

    private LocalDateTime lineExpiryDate;

    private LocalDateTime lineOrderDate;

    private BigDecimal serviceDept;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ios", insertable = false, updatable = false)
    private IosMaster iosMaster;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "orderLine", insertable = false, updatable = false)
    private LabTest labTest;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patientId", insertable = false, updatable = false)
    private PatientMasterData patientMasterData;

    @Formula("(select c.description from codes c where c.code_type = 4235 and c.user_code = status)")
    private String statusDesc;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "serviceDept", insertable = false, updatable = false)
    private ServiceDeptData serviceDeptData;

    @JsonIgnore
    @OneToMany(mappedBy = "patientOrders", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderLineSchedule> orderLineSchedule;

}
