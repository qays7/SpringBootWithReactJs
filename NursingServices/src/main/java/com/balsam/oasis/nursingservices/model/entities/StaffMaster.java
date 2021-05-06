package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "STAFF_MASTER")
public class StaffMaster {

    @Id
    private String staffId;

    private BigDecimal hospitalId;

    @Column(name = "STAFF_NAME_1")
    private String staffName1;

    @Column(name = "STAFF_NAME_2")
    private String staffName2;

    @Column(name = "STAFF_NAME_3")
    private String staffName3;

    @Column(name = "STAFF_NAME_FAMILY")
    private String staffNameFamily;

    @OneToMany(mappedBy = "staffMaster", fetch = FetchType.LAZY)
    private List<PatientEligibility> patientEligibility;

}
