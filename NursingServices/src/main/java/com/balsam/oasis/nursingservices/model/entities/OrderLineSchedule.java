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

import lombok.Data;

@Data
@Entity
@Table(name = "order_line_schedule")
public class OrderLineSchedule {

    @Id
    private BigDecimal scheduleSeq;

    private BigDecimal hospitalId;

    private BigDecimal orderLine;

    private LocalDateTime scheduleDatetime;

    private BigDecimal scheduleQty;

    private BigDecimal deliveryLine;

    private String medicineAdminNurse;

    private LocalDateTime medicineAdminDatetime;

    private BigDecimal status;

    private LocalDateTime statusDate;

    private String notes;

    private BigDecimal phDosageMasterSeq;

    private BigDecimal originalScheduleSeq;

    private BigDecimal statusReason;

    private BigDecimal delayReason;

    private BigDecimal originalDeliveryLine;

    private String extraDoseFlag;

    private BigDecimal adminQtySale;

    private String tpnPreparationPart;

    private LocalDateTime tpnEndAdminDatetime;

    private String witnessedBy;

    private BigDecimal preparationId;

    private BigDecimal dispenseNo;

    private BigDecimal firstVerifyUser;

    private BigDecimal secondVerifyUser;

    private LocalDateTime verificationDate;

    private BigDecimal diluentScheduleSeq;

    private BigDecimal preHoldStatus;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "status", referencedColumnName = "code", insertable = false, updatable = false)
    private CodesData codesData;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "orderLine", insertable = false, updatable = false)
    private PatientOrders patientOrders;

}
