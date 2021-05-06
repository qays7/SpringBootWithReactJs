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
@Table(name = "SERVICE_DEPT_DATA")
public class ServiceDeptData {

    @Id
    private BigDecimal serviceDept;

    private BigDecimal hospitalId;

    private String deptType;

    @JsonIgnore
    @OneToMany(mappedBy = "serviceDeptData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PatientOrders> patientOrders;
}
