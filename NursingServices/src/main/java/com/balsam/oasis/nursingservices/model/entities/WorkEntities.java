package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "WORK_ENTITIES")
public class WorkEntities {

    @Id
    @Column(name = "WORK_ENTITY")
    private BigDecimal workEntity;

    private BigDecimal hospitalId;

    private String description;

    private String entityType;

    @Column(name = "reserve_bed_after_transfer")
    private String reserveBedAfterTransfer;

    @JsonIgnore
    @OneToMany(targetEntity = UserLoginWorkEntities.class, mappedBy = "workEntities", orphanRemoval = false, fetch = FetchType.LAZY)
    private List<UserLoginWorkEntities> userLoginWorkEntities;

    public WorkEntities(BigDecimal workEntity, String description) {
        this.workEntity = workEntity;
        this.description = description;
    }

    public WorkEntities() {
    }

}
