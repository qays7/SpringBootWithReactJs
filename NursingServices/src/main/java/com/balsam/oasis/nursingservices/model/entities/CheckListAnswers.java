package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "check_list_answers")
public class CheckListAnswers {

    @Id
    private BigDecimal checkListAnswerId;

    private BigDecimal hospitalId;

    private BigDecimal checkListItem;

    private BigDecimal checkListType;

    private LocalDateTime recordedAt;

    private BigDecimal replyNumber;

    private BigDecimal checkListAnswerReadingId;

    private String key;

    private String contextKey;

    private String encounterType;

    private String encounterId;

    private BigDecimal checkListAnswersMasterId;

    private String encounterIdGroup;

    private BigDecimal checkListGroupId;

    private BigDecimal totalScore;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "checkListItem", insertable = false, updatable = false)
    private CheckListItemMaster checkListItemMaster;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contextKey", referencedColumnName = "PATIENT_ID", insertable = false, updatable = false)
    private PatientMasterData patientMasterData;

}
