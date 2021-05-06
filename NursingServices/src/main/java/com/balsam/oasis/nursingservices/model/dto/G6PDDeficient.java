package com.balsam.oasis.nursingservices.model.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class G6PDDeficient {

    private BigDecimal atRiskId;

    private String description;

    public G6PDDeficient() {
    }

    public G6PDDeficient(BigDecimal atRiskId, String description) {
        this.atRiskId = atRiskId;
        this.description = String.format("%s %s", description, "(risk)");
    }

}
