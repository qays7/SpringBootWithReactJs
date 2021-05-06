package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "BED_CLASS_MASTER")
public class BedClassMaster {

    @Id
    private BigDecimal bedClass;

    private BigDecimal hospitalId;

    private String description;

}
