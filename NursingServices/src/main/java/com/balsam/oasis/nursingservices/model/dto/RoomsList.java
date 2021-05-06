package com.balsam.oasis.nursingservices.model.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class RoomsList {

    private BigDecimal workEntity;

    private BigDecimal roomNo;

    private String roomDescription;

    private String attendenceType;

    private List<RoomsPayloads> children;

}
