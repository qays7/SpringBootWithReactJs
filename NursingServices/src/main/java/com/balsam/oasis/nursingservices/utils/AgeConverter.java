package com.balsam.oasis.nursingservices.utils;

import java.time.LocalDate;
import java.time.Period;

public class AgeConverter {

    public static String convert(LocalDate date) {
        if (null == date)
            return "";

        LocalDate today = LocalDate.now();
        Period intervalPeriod = Period.between(date, today);
        if (intervalPeriod.getYears() != 0)
            return String.format("%sY-%sM", intervalPeriod.getYears(), intervalPeriod.getMonths());
        else if (intervalPeriod.getMonths() != 0)
            return String.format("%sM-%sD", intervalPeriod.getMonths(), intervalPeriod.getDays());
        else
            return intervalPeriod.getDays() + "D";
    }

}
