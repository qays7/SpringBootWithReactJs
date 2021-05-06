package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PATIENT_FILE_MASTER")
public class PatientFileMaster {

    @Id
    private BigDecimal patFileId;

    private BigDecimal hospitalId;

    private String userFileId;

    private BigDecimal medRecType;

    private BigDecimal patientId;

    private BigDecimal episodeNo;

    private BigDecimal appointmentId;

    private BigDecimal volumeId;

}
