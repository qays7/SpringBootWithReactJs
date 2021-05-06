package com.balsam.oasis.nursingservices.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_LOGIN_WORK_ENTITIES")
@IdClass(UserLoginWorkEntities.class)
public class UserLoginWorkEntities implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    private BigDecimal hospitalId;

    @Id
    @Column(name = "WORK_ENTITY")
    private BigDecimal workEntity;

    @Id
    private BigDecimal userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORK_ENTITY", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    private WorkEntities workEntities;

}
