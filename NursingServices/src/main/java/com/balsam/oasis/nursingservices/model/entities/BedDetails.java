package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "BED_DETAILS")
public class BedDetails {

    @Id
    private BigDecimal bedDetailId;

    private BigDecimal hospitalId;

    private BigDecimal workEntity;

    private BigDecimal roomNo;

    private String bedLocation;

    private BigDecimal bedStatus;

    private BigDecimal bedClass;

    private BigDecimal patientId;

    private BigDecimal episodeNo;

    private BigDecimal admissionNo;

    private BigDecimal teamFirm;

    private String currentRecord;

    private String bedSex;

    private LocalDateTime startDate;

}
