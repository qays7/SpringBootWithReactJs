package com.balsam.oasis.nursingservices.utils;

import org.springframework.util.StringUtils;

public class NameConverter {

    public static String convert(String... names) {
        StringBuilder res = new StringBuilder();
        for (String name : names) {
            if (StringUtils.hasLength(name))
                res.append(String.format(" %s", name));
        }
        return capitalize(res.toString());
    }

    private static String capitalize(String text) {
        if (StringUtils.containsWhitespace(text)) {
            String[] arr = text.toLowerCase().split(" ");
            StringBuilder sb = new StringBuilder();
            for (String txt : arr) {
                sb.append(StringUtils.capitalize(txt)).append(" ");
            }
            return sb.toString().trim();
        }
        return StringUtils.capitalize(text);
    }

}