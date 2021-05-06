package com.balsam.oasis.nursingservices.model.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DrugAdministrations {

    private BigDecimal scheduleSeq;

    private String description;

    private String urgentFlag;

    public DrugAdministrations() {
    }

    public DrugAdministrations(BigDecimal scheduleSeq, String description, String urgentFlag) {
        this.scheduleSeq = scheduleSeq;
        this.description = description;
        this.urgentFlag = description;
    }
}
