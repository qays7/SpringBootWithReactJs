package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class OrderLines {

    @Id
    private BigDecimal orderLine;

    private BigDecimal hospitalId;

    private String nurseReviewRequired;

}
