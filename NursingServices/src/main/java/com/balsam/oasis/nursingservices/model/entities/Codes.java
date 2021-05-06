package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "CODES")
public class Codes {

    @Id
    private BigDecimal code;

    private BigDecimal hospitalId;

    private String description;

    private String userCode;

    private BigDecimal progCode;

    private BigDecimal codeType;

    @JsonIgnore
    @OneToMany(mappedBy = "codes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProblemPatients> problemPatients;

}
