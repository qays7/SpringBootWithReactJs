package com.balsam.oasis.nursingservices.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Transient;

import com.balsam.oasis.nursingservices.model.entities.BedDetails;
import com.balsam.oasis.nursingservices.model.entities.PatientMasterData;
import com.balsam.oasis.nursingservices.model.entities.RoomMaster;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RoomsPayloads {

    private BigDecimal admissionNo;

    private BigDecimal hospitalId;

    private String wardDescription;

    private String roomDescription;

    private String bed;

    private BigDecimal workEntity;

    private BigDecimal roomNo;

    private String classDescription;

    private String teamDescription;

    private BigDecimal bedClass;

    private BigDecimal teamFirm;

    private BigDecimal bedStatus;

    private String reserveBedAfterTransfer;

    private String bedSex;

    private String mixedSexFlag;

    private String roomSex;

    private BigDecimal roomClass;

    private BigDecimal patientId;

    private BigDecimal episodeNo;

    private String userFileId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime estimatedDischarge;

    private BigDecimal outcome;

    private LocalDateTime clinicalDischargeDate;

    private LocalDateTime financialDischargeDate;

    private LocalDateTime physicalDischargeDate;

    private String nearDischargeAlerts;

    @JsonIgnore
    @Transient
    private PatientMasterData patientInfo;

    @JsonIgnore
    @Transient
    private RoomMaster roomInfo;

    @JsonIgnore
    @Transient
    private BedDetails bedInfo;

    public String getSex() {
        return Optional.ofNullable(patientInfo).map(PatientMasterData::getSex).orElse(null);
    }

    public String getPatientAge() {
        return Optional.ofNullable(patientInfo).map(PatientMasterData::getAge).orElse(null);
    }

    public String getStaffId() {
        return Optional.ofNullable(patientInfo).map(PatientMasterData::getStaffId).orElse(null);
    }

    public String getAttendenceType() {
        return Optional.ofNullable(patientInfo).map(PatientMasterData::getAttendenceType).orElse(null);
    }

    public String getPatientName() {
        return Optional.ofNullable(patientInfo).map(PatientMasterData::getPatientName).orElse(null);
    }

    public String getDefaultBedSex() {
        return "Y".equals(roomInfo.getMixedSexFlag()) ? "U" : bedInfo.getBedSex();
    }

    public List<PatientProblems> getPatientProblems() {
        return Optional.ofNullable(patientInfo).map(PatientMasterData::getPatientProblems)
                .orElse(Collections.emptyList());
    }

    public List<PendingTasks> getPendingTasks() {
        return Optional.ofNullable(patientInfo).map(PatientMasterData::getPendingTasks).orElse(Collections.emptyList());
    }

    public List<LabCollection> getLabCollection() {
        return Optional.ofNullable(patientInfo).map(PatientMasterData::getLabCollection)
                .orElse(Collections.emptyList());
    }

    public List<DrugAdministrations> getDrugAdministrations() {
        return Optional.ofNullable(patientInfo).map(PatientMasterData::getDrugAdministration)
                .orElse(Collections.emptyList());
    }

    public List<NurseReview> getNurseReview() {
        return Optional.ofNullable(patientInfo).map(PatientMasterData::getNurseReview).orElse(Collections.emptyList());
    }

    public Boolean getG6pdMissing() {
        return Optional.ofNullable(patientInfo).map(PatientMasterData::getG6pdMissing).orElse(false);
    }

    public Boolean getMarkedWithNoAllergies() {
        return Optional.ofNullable(patientInfo).map(PatientMasterData::getMarkedWithNoAllergies).orElse(false);
    }

    public List<G6PDDeficient> getG6PDDeficient() {
        return Optional.ofNullable(patientInfo).map(PatientMasterData::getG6PDDeficient)
                .orElse(Collections.emptyList());
    }

    public List<TotalScore> getTotalScore() {
        return Optional.ofNullable(patientInfo).map(mapper -> mapper.getTotalScore(episodeNo))
                .orElse(Collections.emptyList());
    }

    public List<ActiveAllergies> getActiveAllergies() {
        return Optional.ofNullable(patientInfo).map(PatientMasterData::getActiveAllergies)
                .orElse(Collections.emptyList());
    }

}
