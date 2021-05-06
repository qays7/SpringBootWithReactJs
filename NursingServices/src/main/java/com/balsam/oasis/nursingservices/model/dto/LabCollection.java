package com.balsam.oasis.nursingservices.model.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class LabCollection {

    private BigDecimal orderLine;

    private String description;

    private String urgentFlag;

    private String statusDesc;

    public LabCollection(BigDecimal orderLine, String description, String urgentFlag, String statusDesc) {
        this.orderLine = orderLine;
        this.description = description;
        this.urgentFlag = urgentFlag;
        this.statusDesc = statusDesc;
    }

    public LabCollection() {
    }

}
