package com.dreamdream.util;

import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

import com.dreamdream.model.Dreamer;
import com.google.common.base.Strings;

public class NumberUtils {

    public static String randomNumber(int length) {
        Double divider = Math.pow(10, length);
        Random ran = new Random();
        int number = ran.nextInt() % divider.intValue();
        String numberStr = String.valueOf(Math.abs(number));

        if (numberStr.length() < length) {
            numberStr = Strings.padEnd(numberStr, length, '0');
        }
        return numberStr;
    }

    public static String createOrderNumber(Dreamer o) {
        StringBuilder sb = new StringBuilder();
        sb.append("RSZCZ");
        DateTime dt;
        if(o.getCreateTime() == null) {
            dt = DateTime.now();
        } else {
            dt = new DateTime(o.getCreateTime().getTime());
        }
        sb.append(DateUtils.getDateFormat(dt, "yyMMddHH"));
        sb.append(randomNumber(6));
        return sb.toString();
    }
}
