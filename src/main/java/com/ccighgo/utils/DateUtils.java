package com.ccighgo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    
    private static final String MM_dd_yyy = "MM-dd-yyyy";
    
    static String getMMddYyyyString(Date inputDate){
        SimpleDateFormat sdf = new SimpleDateFormat(MM_dd_yyy);
        String date = sdf.format(inputDate);
        return date;
    }

}
