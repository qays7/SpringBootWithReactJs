package com.balsam.oasis.nursingservices.model.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TotalScore {

    private BigDecimal checkListItem;

    private String narrative;

    private BigDecimal totalScore;

    private BigDecimal patientId;

    private BigDecimal episodeNo;

    public TotalScore() {
    }

    public TotalScore(BigDecimal checkListItem, String narrative, BigDecimal totalScore, BigDecimal patientId,
            BigDecimal episodeNo) {
        this.checkListItem = checkListItem;
        this.narrative = narrative;
        this.totalScore = totalScore;
        this.patientId = patientId;
        this.episodeNo = episodeNo;
    }

}
