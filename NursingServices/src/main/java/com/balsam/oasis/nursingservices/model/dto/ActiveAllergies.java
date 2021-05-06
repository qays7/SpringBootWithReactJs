package com.balsam.oasis.nursingservices.model.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ActiveAllergies {

    private BigDecimal atRiskId;

    private String description;

    public ActiveAllergies() {
    }

    public ActiveAllergies(BigDecimal atRiskId, String description) {
        this.atRiskId = atRiskId;
        this.description = String.format("%s %s", description, "(allergy)");
    }
}
