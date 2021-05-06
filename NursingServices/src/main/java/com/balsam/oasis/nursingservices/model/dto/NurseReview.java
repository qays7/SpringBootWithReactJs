package com.balsam.oasis.nursingservices.model.dto;

import java.math.BigDecimal;

import com.balsam.oasis.nursingservices.model.entities.PatientOrders;

import lombok.Data;

@Data
public class NurseReview {

    private BigDecimal orderLine;

    private BigDecimal hospitalId;

    private String description;

    private BigDecimal patientId;

    private BigDecimal episodeNo;

    public NurseReview() {
    }

    public NurseReview(PatientOrders order, String description) {
        this.orderLine = order.getOrderLine();
        this.hospitalId = order.getHospitalId();
        this.description = description;
        this.patientId = order.getPatientId();
        this.episodeNo = order.getEpisodeNo();
    }

}
