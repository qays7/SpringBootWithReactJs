package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "lab_test")
public class LabTest {

    @Id
    private BigDecimal orderLine;

    private BigDecimal hospitalId;

    private String status;

    @JsonIgnore
    @OneToOne(mappedBy = "labTest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PatientOrders patientOrders;

}
