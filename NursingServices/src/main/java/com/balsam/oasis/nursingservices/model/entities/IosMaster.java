package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "IOS_MASTER")
public class IosMaster {

    @Id
    private BigDecimal ios;

    private String description;

    private String consultantDisplayFlag;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iosMaster", fetch = FetchType.LAZY)
    private List<PatientOrders> patientOrders;

}
