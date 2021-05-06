package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ROOM_MASTER")
public class RoomMaster {

    @Id
    private BigDecimal roomNo;

    private BigDecimal hospitalId;

    private BigDecimal workEntity;

    private String description;

    private BigDecimal maxBedsInRoom;

    private BigDecimal roomStatus;

    private BigDecimal roomClass;

    private String roomSex;

    private String privateFlag;

    private String mixedSexFlag;

}
