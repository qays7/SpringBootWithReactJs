package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.balsam.oasis.nursingservices.model.dto.ActiveAllergies;
import com.balsam.oasis.nursingservices.model.dto.DrugAdministrations;
import com.balsam.oasis.nursingservices.model.dto.G6PDDeficient;
import com.balsam.oasis.nursingservices.model.dto.LabCollection;
import com.balsam.oasis.nursingservices.model.dto.NurseReview;
import com.balsam.oasis.nursingservices.model.dto.PatientProblems;
import com.balsam.oasis.nursingservices.model.dto.PendingTasks;
import com.balsam.oasis.nursingservices.model.dto.TotalScore;
import com.balsam.oasis.nursingservices.utils.AgeConverter;
import com.balsam.oasis.nursingservices.utils.NameConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "PATIENT_MASTER_DATA")
public class PatientMasterData {

        @Id
        @Column(name = "PATIENT_ID")
        private BigDecimal patientId;

        private BigDecimal hospitalId;

        private LocalDate dateOfBirth;

        private String sex;

        @Column(name = "PAT_NAME_1")
        private String patName1;

        @Column(name = "PAT_NAME_2")
        private String patName2;

        @Column(name = "PAT_NAME_3")
        private String patName3;

        @Column(name = "PAT_NAME_FAMILY")
        private String patNameFamily;

        private String status;

        @JsonIgnore
        @OneToMany(mappedBy = "patientMasterData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        private List<ProblemPatients> problemPatients;

        @JsonIgnore
        @OneToMany(mappedBy = "patientMasterData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        private List<PatientAd> patientAd;

        @JsonIgnore
        @OneToMany(mappedBy = "patientMasterData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        private List<PatientEligibility> patientEligibility;

        @JsonIgnore
        @OneToMany(mappedBy = "patientMasterData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        private List<TaskOrders> taskOrders;

        @JsonIgnore
        @OneToMany(mappedBy = "patientMasterData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        private List<PatientOrders> patientOrders;

        @JsonIgnore
        @OneToMany(mappedBy = "patientMasterData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        private List<SociallyAtRiskRegister> sociallyAtRiskRegister;

        @JsonIgnore
        @OneToMany(mappedBy = "patientMasterData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        private List<CheckListAnswers> checkListAnswers;

        @JsonIgnore
        public String getAttendenceType() {
                if (patientEligibility != null && patientEligibility.size() > 0 && patientAd != null
                                && patientAd.size() > 0)
                        return patientEligibility.stream().filter((patientEligibility) -> patientEligibility != null
                                        && Arrays.asList("I", "D").contains(patientEligibility.getAttendenceType())
                                        && !Arrays.asList("C", "S").contains(patientEligibility.getEligibilityStatus())
                                        && "1".equals(patientEligibility.getResponsibility())
                                        && patientEligibility.getEligibilityEnd() == null
                                        && patientAd.stream().anyMatch((pa) -> pa.getAdmissionNo()
                                                        .equals(patientEligibility.getAdmissionNo())
                                                        && pa.getEpisodeNo().equals(patientEligibility.getEpisodeNo())))

                                        .map(PatientEligibility::getAttendenceType).findFirst().orElse(null);
                return null;

        }

        @JsonIgnore
        public String getStaffId() {
                if (patientEligibility != null && patientEligibility.size() > 0 && patientAd != null
                                && patientAd.size() > 0)
                        return patientEligibility.stream().filter((patientEligibility) -> patientEligibility != null
                                        && Arrays.asList("I", "D").contains(patientEligibility.getAttendenceType())
                                        && !Arrays.asList("C", "S").contains(patientEligibility.getEligibilityStatus())
                                        && "1".equals(patientEligibility.getResponsibility())
                                        && patientEligibility.getEligibilityEnd() == null
                                        && patientAd.stream().anyMatch((pa) -> pa.getAdmissionNo()
                                                        .equals(patientEligibility.getAdmissionNo())
                                                        && pa.getEpisodeNo().equals(patientEligibility.getEpisodeNo()))
                                        && patientEligibility.getConsultantId()
                                                        .equals(patientEligibility.getStaffMaster().getStaffId()))
                                        .map(PatientEligibility::getConsultantId).findFirst().orElse(null);
                return null;

        }

        public String getAge() {
                return AgeConverter.convert(dateOfBirth);
        }

        public String getPatientName() {
                return NameConverter.convert(patName1, patName2, patName3, patNameFamily);
        }

        @JsonIgnore
        public List<PatientProblems> getPatientProblems() {
                if (problemPatients != null && problemPatients.size() > 0)
                        return problemPatients.stream()
                                        .filter((row) -> row.getCodes() != null && row.getCodes().getCodeType()
                                                        .equals(BigDecimal.valueOf(309L)))
                                        .map(row -> new PatientProblems(row.getProblemPatientId(),
                                                        row.getDescription()))
                                        .collect(Collectors.toList());
                return null;
        }

        @JsonIgnore
        public List<PendingTasks> getPendingTasks() {
                if (taskOrders != null && taskOrders.size() > 0) {
                        Object[] collect = taskOrders.stream()
                                        .filter(row -> row.getTaskOrderSchedule() != null
                                                        && row.getTaskOrderSchedule().size() > 0
                                                        && row.getTasks() != null && "R".equals(row.getStatus()))
                                        .map(this.prepareTasks()).collect(Collectors.toList()).toArray();
                        return (List<PendingTasks>) Stream.of(collect).map(List.class::cast).flatMap(Collection::stream)
                                        .distinct().collect(Collectors.toList());
                }
                return null;
        }

        @JsonIgnore
        private Function<TaskOrders, List<PendingTasks>> prepareTasks() {
                return (i) -> {
                        return i.getTaskOrderSchedule().stream().filter((row) -> (row.getScheduleDatetime()
                                        .isAfter(LocalDateTime.now().minusDays(1L).truncatedTo(ChronoUnit.DAYS))
                                        && row.getScheduleDatetime().isBefore(
                                                        LocalDateTime.now().plusDays(1L).truncatedTo(ChronoUnit.DAYS)))
                                        && Duration.between(row.getScheduleDatetime(), LocalDateTime.now())
                                                        .toMinutes() < (i.getTasks().getRetentionNoOfMin() != null
                                                                        ? i.getTasks().getRetentionNoOfMin().longValue()
                                                                        : 1440L)
                                        && row.getCodesData() != null
                                        && BigDecimal.valueOf(44L).equals(row.getCodesData().getCodeType())
                                        && BigDecimal.ONE.equals(row.getCodesData().getProgCode()))
                                        .map((row) -> new PendingTasks(i.getTaskOrderLine(), row.getScheduleDatetime(),
                                                        i.getTasks().getDescription()))
                                        .collect(Collectors.toList());
                };
        }

        @JsonIgnore
        public List<LabCollection> getLabCollection() {
                if (patientOrders != null && patientOrders.size() > 0)
                        return patientOrders.stream().filter(row -> (row.getLabTest() == null
                                        || !(row.getLabTest() != null && !"C".equals(row.getLabTest().getStatus())))
                                        && row.getLineOrderDate() != null && row.getLineExpiryDate() != null
                                        && LocalDateTime.now().isBefore(row.getLineExpiryDate())
                                        && LocalDateTime.now().minusDays(1L).truncatedTo(ChronoUnit.DAYS)
                                                        .isBefore(row.getLineOrderDate())
                                        && LocalDateTime.now().plusHours(2L).isAfter(row.getLineOrderDate())
                                        && Arrays.asList("R", "D").contains(row.getStatus())
                                        && (row.getServiceDeptData() != null
                                                        && "LAB".equals(row.getServiceDeptData().getDeptType())))
                                        .map((mapper) -> new LabCollection(mapper.getOrderLine(),
                                                        mapper.getIosMaster().getDescription(), mapper.getUrgentFlag(),
                                                        mapper.getStatusDesc()))
                                        .collect(Collectors.toList());
                return null;
        }

        @JsonIgnore
        public List<DrugAdministrations> getDrugAdministration() {
                if (patientOrders != null && patientOrders.size() > 0) {
                        Object[] collect = patientOrders.stream()
                                        .filter(row -> row.getLineExpiryDate() != null
                                                        && LocalDateTime.now().isBefore(row.getLineExpiryDate())
                                                        && Arrays.asList("R", "D").contains(row.getStatus()))
                                        .map(this.prepareDrugAdministration()).collect(Collectors.toList()).toArray();
                        return (List<DrugAdministrations>) Stream.of(collect).map(List.class::cast)
                                        .flatMap(Collection::stream).distinct().collect(Collectors.toList());
                }

                return null;
        }

        @JsonIgnore
        private Function<PatientOrders, List<DrugAdministrations>> prepareDrugAdministration() {
                return (i) -> {
                        return i.getOrderLineSchedule().stream().filter((row) -> LocalDateTime.now().minusDays(1L)
                                        .truncatedTo(ChronoUnit.DAYS).isBefore(row.getScheduleDatetime())
                                        && LocalDateTime.now().plusHours(2L).isAfter(row.getScheduleDatetime())
                                        && row.getCodesData() != null
                                        && BigDecimal.valueOf(439L).equals(row.getCodesData().getCodeType())
                                        && BigDecimal.valueOf(4L).equals(row.getCodesData().getProgCode()))
                                        .map((row) -> new DrugAdministrations(row.getScheduleSeq(),
                                                        i.getIosMaster().getDescription(), i.getUrgentFlag()))
                                        .collect(Collectors.toList());
                };
        }

        public List<NurseReview> getNurseReview() {
                if (patientOrders != null && patientOrders.size() > 0)
                        return patientOrders.stream()
                                        .filter(row -> "R".equals(row.getStatus())
                                                        && Arrays.asList("I", "D").contains(row.getAttendanceType())
                                                        && BigDecimal.ZERO.equals(row.getUnitsGiven())
                                                        && "Y".equals(row.getNurseReviewRequired())
                                                        && "Y".equals(row.getIosMaster().getConsultantDisplayFlag()))
                                        .map(mapper -> new NurseReview(mapper, mapper.getIosMaster().getDescription()))
                                        .collect(Collectors.toList());
                return null;
        }

        public Boolean getG6pdMissing() {
                if (sociallyAtRiskRegister != null && sociallyAtRiskRegister.size() > 0) {
                        long count = sociallyAtRiskRegister.stream()
                                        .filter((row) -> (row.getStatus() == null
                                                        || (row.getStatus() != null && !row.getStatus().equals("Q")))
                                                        && row.getG6PDMissingCode() > 0)
                                        .count();
                        return count > 0;

                }
                return Boolean.FALSE;
        }

        public Boolean getMarkedWithNoAllergies() {
                if (sociallyAtRiskRegister != null && sociallyAtRiskRegister.size() > 0) {
                        long count = sociallyAtRiskRegister.stream()
                                        .filter((row) -> (row.getStatus() == null
                                                        || (row.getStatus() != null && !row.getStatus().equals("Q")))
                                                        && row.getRemovedDate() == null && row.getNoAllergiesCode() > 0)
                                        .count();
                        return count > 0;

                }
                return Boolean.FALSE;
        }

        public List<G6PDDeficient> getG6PDDeficient() {
                if (sociallyAtRiskRegister != null && sociallyAtRiskRegister.size() > 0) {
                        return sociallyAtRiskRegister.stream()
                                        .filter((row) -> (row.getStatus() == null
                                                        || (row.getStatus() != null && !row.getStatus().equals("Q")))
                                                        && (row.getRemovedDate() == null || row.getRemovedDate()
                                                                        .equals(LocalDateTime.now().plusDays(1L)))
                                                        && row.getG6pdDeficientCode() > 0)
                                        .map((mapper) -> new G6PDDeficient(mapper.getAtRiskId(),
                                                        mapper.getDescription()))
                                        .collect(Collectors.toList());

                }
                return null;
        }

        public List<TotalScore> getTotalScore(BigDecimal episodeNo) {

                if (checkListAnswers != null && checkListAnswers.size() > 0)
                        return checkListAnswers.stream().filter(row -> row.getEncounterIdGroup()
                                        .equals(episodeNo.toString())
                                        && "S".equals(row.getCheckListItemMaster().getReplyType())
                                        && row.getRecordedAt().equals(getMaxRecordedAt(row.getContextKey(),
                                                        row.getEncounterIdGroup(), row.getCheckListItem())))
                                        .map((mapper) -> new TotalScore(mapper.getCheckListItem(),
                                                        mapper.getCheckListItemMaster().getNarrative(),
                                                        mapper.getTotalScore(), new BigDecimal(mapper.getContextKey()),
                                                        new BigDecimal(mapper.getEncounterIdGroup())))
                                        .collect(Collectors.toList());
                return null;
        }

        private LocalDateTime getMaxRecordedAt(String contextKey, String encounterIdGroup, BigDecimal checkListItem) {
                List<LocalDateTime> localDateTimes = checkListAnswers.stream()
                                .filter(row -> row.getContextKey().equals(contextKey)
                                                && row.getEncounterIdGroup().equals(encounterIdGroup)
                                                && row.getCheckListItem().equals(checkListItem))
                                .map(CheckListAnswers::getRecordedAt).collect(Collectors.toList());
                return Collections.max(localDateTimes);
        }

        public List<ActiveAllergies> getActiveAllergies() {
                if (sociallyAtRiskRegister != null && sociallyAtRiskRegister.size() > 0) {
                        return union(codesAllergies(), genericAllergies(), iosMainAllergies());
                }
                return null;
        }

        private List<ActiveAllergies> codesAllergies() {
                return sociallyAtRiskRegister.stream().filter(row -> (row.getStatus() == null
                                || (row.getStatus() != null && !row.getStatus().equals("Q")))
                                && (row.getRemovedDate() == null
                                                || row.getRemovedDate().equals(LocalDateTime.now().plusDays(1L)))
                                && row.getAllergiesCodes() > 0)
                                .map((mapper) -> new ActiveAllergies(mapper.getAtRiskId(), mapper.getDescription()))
                                .collect(Collectors.toList());
        }

        private List<ActiveAllergies> genericAllergies() {
                return sociallyAtRiskRegister.stream().filter(row -> (row.getStatus() == null
                                || (row.getStatus() != null && !row.getStatus().equals("Q")))
                                && (row.getRemovedDate() == null
                                                || row.getRemovedDate().equals(LocalDateTime.now().plusDays(1L)))
                                && row.getGenericId() != null)
                                .map((mapper) -> new ActiveAllergies(mapper.getAtRiskId(),
                                                mapper.getGenerics().getGenericName()))
                                .collect(Collectors.toList());
        }

        private List<ActiveAllergies> iosMainAllergies() {
                return sociallyAtRiskRegister.stream().filter(row -> (row.getStatus() == null
                                || (row.getStatus() != null && !row.getStatus().equals("Q")))
                                && (row.getRemovedDate() == null
                                                || row.getRemovedDate().equals(LocalDateTime.now().plusDays(1L)))
                                && row.getDrugId() != null)
                                .map((mapper) -> new ActiveAllergies(mapper.getAtRiskId(),
                                                mapper.getIosMain().getDescription()))
                                .collect(Collectors.toList());
        }

        private final <T> List<T> union(List<T>... queries) {
                List<T> union = Stream.of(queries).flatMap(Collection::stream).distinct().collect(Collectors.toList());
                return union;
        }

}
