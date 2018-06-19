package com.airam.helpfisio.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String FORMAT_DEFAULT = "dd-MM-yyyy";

    public static String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_DEFAULT);
        return dateFormat.format(date);
    }

    public static Date stringToDate(String strDate) {
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_DEFAULT);

        try {
            return dateFormat.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }
}
