package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TEAMS")
public class Teams {

    @Id
    private BigDecimal teamId;

    private BigDecimal hospitalId;

    private BigDecimal teamType;

    private String description;

}
