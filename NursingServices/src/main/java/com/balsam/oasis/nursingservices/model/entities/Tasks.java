package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "TASKS")
public class Tasks {

    @Id
    private BigDecimal taskId;

    private BigDecimal hospitalId;

    private String description;

    private String chartType;

    private String detailedChartForm;

    private String active;

    private String cancelReasonRequired;

    private BigDecimal overdueNoOfMin;

    private BigDecimal retentionNoOfMin;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tasks", cascade = CascadeType.ALL)
    private List<TaskOrders> taskOrders;
}
