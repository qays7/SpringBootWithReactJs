package com.balsam.oasis.nursingservices.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PendingTasks {

    private BigDecimal taskOrderLine;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime scheduleDatetime;

    private String taskName;

    public PendingTasks() {
    }

    public PendingTasks(BigDecimal taskOrderLine, LocalDateTime scheduleDatetime, String taskName) {
        this.taskOrderLine = taskOrderLine;
        this.scheduleDatetime = scheduleDatetime;
        this.taskName = taskName;
    }

}
