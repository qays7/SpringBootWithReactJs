package com.balsam.oasis.nursingservices.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.balsam.oasis.nursingservices.services.NursingWhiteboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@RequestMapping("api/v1/hospitalId/")
@RestController
public class NursingWhiteboardController {

    @Autowired
    private NursingWhiteboardService nursingWhiteboardService;

    @GetMapping("{hospitalId}")
    public ResponseEntity<Map<String, Object>> getRoomsList(@PathVariable BigDecimal hospitalId,
            @RequestParam(name = "userId", required = true) BigDecimal userId,
            @RequestParam(name = "medRecType", required = true) BigDecimal medRecType,
            @RequestParam(name = "neerDischargeHrs", required = true) BigDecimal neerDischargeHrs) {
        log.debug("<<< Session Params >>>"
                + String.format("Hospital Id [%s] , UserId [%s] , Med Rec Type [%s] , Adt Neer Discharge Hrs Rule [%s]",
                        hospitalId, userId, medRecType, neerDischargeHrs));
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("rooms", nursingWhiteboardService.getRoomsList(hospitalId, userId, medRecType, neerDischargeHrs));
        data.put("wards", nursingWhiteboardService.getWrdList(hospitalId, userId));
        return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
    }

    @PostMapping("{hospitalId}")
    public void updateOrderLines(@RequestParam(name = "orderLine", required = true) BigDecimal orderLine,
            @PathVariable BigDecimal hospitalId) {
        nursingWhiteboardService.updateOrderLines(orderLine, hospitalId);
    }

    // @GetMapping("test/{hospitalId}")
    // public ResponseEntity<List<RoomsPayloads>> test(@PathVariable BigDecimal
    // hospitalId,
    // @RequestParam(name = "userId", required = true) BigDecimal userId,
    // @RequestParam(name = "medRecType", required = true) BigDecimal medRecType,
    // @RequestParam(name = "neerDischargeHrs", required = true) BigDecimal
    // neerDischargeHrs) {
    // return new ResponseEntity<List<RoomsPayloads>>(
    // nursingWhiteboardService.test(hospitalId, userId, medRecType,
    // neerDischargeHrs), HttpStatus.OK);
    // }

}
