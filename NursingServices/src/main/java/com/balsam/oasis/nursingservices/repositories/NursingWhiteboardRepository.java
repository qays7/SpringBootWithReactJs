package com.balsam.oasis.nursingservices.repositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.balsam.oasis.nursingservices.model.dto.RoomsPayloads;
import com.balsam.oasis.nursingservices.model.dto.WardList;
import com.balsam.oasis.nursingservices.model.entities.QBedClassMaster;
import com.balsam.oasis.nursingservices.model.entities.QBedDetails;
import com.balsam.oasis.nursingservices.model.entities.QCodesData;
import com.balsam.oasis.nursingservices.model.entities.QOrderLines;
import com.balsam.oasis.nursingservices.model.entities.QPatientAd;
import com.balsam.oasis.nursingservices.model.entities.QPatientFileMaster;
import com.balsam.oasis.nursingservices.model.entities.QPatientMasterData;
import com.balsam.oasis.nursingservices.model.entities.QRoomMaster;
import com.balsam.oasis.nursingservices.model.entities.QTeams;
import com.balsam.oasis.nursingservices.model.entities.QUserLoginWorkEntities;
import com.balsam.oasis.nursingservices.model.entities.QWorkEntities;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.stereotype.Repository;

@Repository
public class NursingWhiteboardRepository {

        @PersistenceContext
        private EntityManager em;

        // @Autowired
        // private Queries queries;

        public List<RoomsPayloads> getRoomsList(BigDecimal hospitalId, BigDecimal userId, BigDecimal medRecType,
                        BigDecimal nearDischargeHrs) {
                List<RoomsPayloads> reservedBeds = reservedBeds(hospitalId, userId, medRecType, nearDischargeHrs);
                List<RoomsPayloads> emptyBeds = emptyBeds(hospitalId, userId, medRecType, nearDischargeHrs);
                // return Stream.of(reservedBeds,
                // emptyBeds).flatMap(Collection::stream).collect(
                // Collectors.groupingBy(p -> Arrays.asList(((RoomsPayloads)
                // p).getWorkEntity().toString(),
                // ((RoomsPayloads) p).getRoomDescription())));
                return Stream.of(reservedBeds, emptyBeds).flatMap(Collection::stream).collect(Collectors.toList());
        }

        public List<WardList> getWrdList(BigDecimal hospitalId, BigDecimal userId) {
                var ulwe = QUserLoginWorkEntities.userLoginWorkEntities;
                return new JPAQuery<WardList>(em)
                                .select(Projections.bean(WardList.class, ulwe.workEntities.workEntity,
                                                ulwe.workEntities.description))
                                .from(ulwe).join(ulwe.workEntities)
                                .where(ulwe.hospitalId.eq(hospitalId)
                                                .and(ulwe.hospitalId.eq(ulwe.workEntities.hospitalId))
                                                .and(ulwe.userId.eq(userId).and(ulwe.workEntities.entityType.eq("W"))))
                                .orderBy(ulwe.workEntities.description.asc()).fetch();
        }

        @Transactional
        public void updateOrderLines(BigDecimal orderLine, BigDecimal hospitalId) {
                String updatedValue = null;
                new JPAQueryFactory(em).update(QOrderLines.orderLines)
                                .where(QOrderLines.orderLines.orderLine.eq(orderLine)
                                                .and(QOrderLines.orderLines.nurseReviewRequired.eq("Y"))
                                                .and(QOrderLines.orderLines.hospitalId.eq(hospitalId)))
                                .set(QOrderLines.orderLines.nurseReviewRequired, updatedValue).execute();
        }

        private List<RoomsPayloads> reservedBeds(BigDecimal hospitalId, BigDecimal userId, BigDecimal medRecType,
                        BigDecimal nearDischargeHrs) {

                var ulwe = QUserLoginWorkEntities.userLoginWorkEntities;
                var we = QWorkEntities.workEntities;
                var pa = QPatientAd.patientAd;
                var pmd = QPatientMasterData.patientMasterData;
                var pfm = QPatientFileMaster.patientFileMaster;
                var rm = QRoomMaster.roomMaster;
                var teams = QTeams.teams;
                var bd = QBedDetails.bedDetails;
                var bd2 = QBedDetails.bedDetails;
                var bcm = QBedClassMaster.bedClassMaster;

                return new JPAQuery<RoomsPayloads>(
                                em).select(Projections.bean(RoomsPayloads.class, pmd.as("patientInfo"), rm.as("roomInfo"), bd.as("bedInfo"), pa.admissionNo, pa.hospitalId, we.description.as("wardDescription"), rm.description.as("roomDescription"), bd.bedLocation.as("bed"), we.workEntity, rm.roomNo, bcm.description.as("classDescription"), teams.description.as("teamDescription"), bcm.bedClass, bd.teamFirm, bd.bedStatus, we.reserveBedAfterTransfer, bd.bedSex, rm.mixedSexFlag, rm.roomSex, rm.roomClass, pa.patientId, pa.episodeNo, pfm.userFileId, pa.estDischargeDate.as("estimatedDischarge"), pa.outcome, pa.clinicalDischargeDate, pa.financialDischargeDate, pa.physicalDischargeDate, Expressions.cases().when(pa.estDischargeDate.before(LocalDateTime.now()).or(pa.estDischargeDate.milliSecond().subtract(Expressions.asNumber(ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()).toEpochSecond()).divide(1000L * 60L * 60L).mod(24L)).lt(nearDischargeHrs))).then("Y").otherwise("N").as("nearDischargeAlerts"))).from(pmd).innerJoin(pfm).on(pmd.patientId.eq(pfm.patientId)).innerJoin(pa).on(pmd.patientId.eq(pa.patientId)).innerJoin(bd).on(pa.admissionNo.eq(bd.admissionNo)).rightJoin(rm).on(bd.roomNo.eq(rm.roomNo).and(bd.workEntity.eq(rm.workEntity))).innerJoin(we).on(bd.workEntity.eq(we.workEntity)).innerJoin(teams).on(bd.teamFirm.eq(teams.teamId)).rightJoin(bcm).on(bd.bedClass.eq(bcm.bedClass)).where(bd.hospitalId.eq(hospitalId).and(bd.currentRecord.eq("Y")).and(rm.roomNo.isNotNull()).and(pfm.volumeId.eq(BigDecimal.ONE).and(pfm.medRecType.eq(medRecType))).and(bd.startDate.eq(JPAExpressions.select(bd2.startDate.max()).from(bd2).where(bd2.admissionNo.eq(pa.admissionNo).and(bd2.patientId.eq(pa.patientId)).and(bd2.hospitalId.eq(pa.hospitalId)).and(bd2.currentRecord.eq("Y"))))).and(we.workEntity.in(JPAExpressions.select(ulwe.workEntity).from(ulwe).join(ulwe.workEntities).where(ulwe.hospitalId.eq(hospitalId).and(ulwe.hospitalId.eq(ulwe.workEntities.hospitalId)).and(ulwe.userId.eq(userId).and(ulwe.workEntities.entityType.eq("W"))))))).fetch();
        }

        private List<RoomsPayloads> emptyBeds(BigDecimal hospitalId, BigDecimal userId, BigDecimal medRecType,
                        BigDecimal nearDischargeHrs) {
                var ulwe = QUserLoginWorkEntities.userLoginWorkEntities;
                var we = QWorkEntities.workEntities;
                var rm = QRoomMaster.roomMaster;
                var bd = QBedDetails.bedDetails;
                var bcm = QBedClassMaster.bedClassMaster;
                var cd = QCodesData.codesData;
                return new JPAQuery<RoomsPayloads>(
                                em).select(Projections.bean(RoomsPayloads.class, rm.as("roomInfo"), bd.as("bedInfo"), rm.hospitalId, we.description.as("wardDescription"), rm.description.as("roomDescription"), bd.bedLocation.as("bed"), we.workEntity, rm.roomNo, bcm.description.as("classDescription"), bcm.bedClass, bd.teamFirm, bd.bedStatus, we.reserveBedAfterTransfer, bd.bedSex, rm.mixedSexFlag, rm.roomSex, rm.roomClass)).from(bd).innerJoin(cd).on(bd.bedStatus.eq(cd.code)).innerJoin(we).on(bd.workEntity.eq(we.workEntity)).innerJoin(rm).on(bd.roomNo.eq(rm.roomNo)).innerJoin(bcm).on(bd.bedClass.eq(bcm.bedClass)).where(bd.currentRecord.eq("Y").and(cd.progCode.eq(BigDecimal.ZERO)).and(bd.patientId.isNull()).and(bd.hospitalId.eq(hospitalId)).and(rm.isNotNull()).and(we.workEntity.in(JPAExpressions.select(ulwe.workEntity).from(ulwe).join(ulwe.workEntities).where(ulwe.hospitalId.eq(hospitalId).and(ulwe.hospitalId.eq(ulwe.workEntities.hospitalId)).and(ulwe.userId.eq(userId).and(ulwe.workEntities.entityType.eq("W"))))))).fetch();

        }

        // private final <T> List<T> union(List<T>... queries) {
        // List<T> union =
        // Stream.of(queries).flatMap(Collection::stream).collect(Collectors.toList());
        // return union;
        // }

}
