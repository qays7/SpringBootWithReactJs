package com.balsam.oasis.nursingservices.model.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "check_list_item_master")
public class CheckListItemMaster {

    @Id
    private BigDecimal checkListItem;

    private BigDecimal hospitalId;

    private String checkListCategory;

    private String narrative;

    private String multipleAnswers;

    private String replyType;

    private BigDecimal readingType;

    @OneToMany(mappedBy = "checkListItemMaster", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CheckListAnswers> checkListAnswers;

}
