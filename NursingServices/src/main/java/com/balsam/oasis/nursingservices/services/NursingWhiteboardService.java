package com.balsam.oasis.nursingservices.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.balsam.oasis.nursingservices.model.dto.RoomsPayloads;
import com.balsam.oasis.nursingservices.model.dto.WardList;
import com.balsam.oasis.nursingservices.repositories.NursingWhiteboardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NursingWhiteboardService {

    @Autowired
    private NursingWhiteboardRepository nursingWhiteboardRepository;

    public List<RoomsPayloads> getRoomsList(BigDecimal hospitalId, BigDecimal userId, BigDecimal medRecType,
            BigDecimal neerDischargeHrs) {
        return nursingWhiteboardRepository.getRoomsList(hospitalId, userId, medRecType, neerDischargeHrs);

    }

    public List<WardList> getWrdList(BigDecimal hospitalId, BigDecimal userId) {
        return nursingWhiteboardRepository.getWrdList(hospitalId, userId);
    }

    public void updateOrderLines(BigDecimal orderLine, BigDecimal hospitalId) {
        nursingWhiteboardRepository.updateOrderLines(orderLine, hospitalId);
    }

}
