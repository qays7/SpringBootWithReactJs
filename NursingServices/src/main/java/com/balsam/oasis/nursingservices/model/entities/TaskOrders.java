package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "task_orders")
public class TaskOrders {

    @Id
    private BigDecimal taskOrderLine;

    private BigDecimal hospitalId;

    private BigDecimal patientId;

    private BigDecimal episodeNo;

    private BigDecimal encounterId;

    private String encounterType;

    private BigDecimal nurseGoalId;

    private BigDecimal taskId;

    private String notes;

    private String dosageCode;

    private BigDecimal times;

    private BigDecimal noOfPeriods;

    private BigDecimal typeOfPeriod;

    private BigDecimal noOfOccurrence;

    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "taskOrders", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TaskOrderSchedule> taskOrderSchedule;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "taskId", updatable = false, insertable = false)
    private Tasks tasks;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patientId", insertable = false, updatable = false)
    private PatientMasterData patientMasterData;

}
