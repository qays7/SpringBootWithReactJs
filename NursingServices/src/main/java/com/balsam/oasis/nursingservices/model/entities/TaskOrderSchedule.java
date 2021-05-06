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
@Table(name = "task_order_schedule")
public class TaskOrderSchedule {

    @Id
    private BigDecimal taskScheduleId;

    private BigDecimal hospitalId;

    private BigDecimal taskOrderLine;

    private LocalDateTime scheduleDatetime;

    private LocalDateTime actualDatetime;

    private BigDecimal delayReason;

    private String notes;

    private BigDecimal status;

    private LocalDateTime statusDate;

    private BigDecimal statusReason;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "taskOrderLine", insertable = false, updatable = false)
    private TaskOrders taskOrders;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "status", referencedColumnName = "code", insertable = false, updatable = false)
    private CodesData codesData;

}
