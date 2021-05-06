package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "CODES_DATA")
public class CodesData {

    @Id
    private BigDecimal code;

    private BigDecimal hospitalId;

    private String description;

    private String userCode;

    private BigDecimal progCode;

    private BigDecimal codeType;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "codesData")
    private List<TaskOrderSchedule> taskOrderSchedule;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "codesData")
    private List<OrderLineSchedule> orderLineSchedule;

}
