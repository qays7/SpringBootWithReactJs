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
@Table(name = "ios_main")
public class IosMain {

    @Id
    private BigDecimal iosMain;

    private BigDecimal hospitalId;

    private String description;

    private String productCode;

    private String oldIosUser;

    @JsonIgnore
    @OneToMany(mappedBy = "iosMain", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SociallyAtRiskRegister> sociallyAtRiskRegister;

}
