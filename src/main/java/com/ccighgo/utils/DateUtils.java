package com.ccighgo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
    
    private static final String MM_dd_yyy = "MM-dd-yyyy";
	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    
    public static String getMMddYyyyString(Date inputDate){
        SimpleDateFormat sdf = new SimpleDateFormat(MM_dd_yyy);
        String date = sdf.format(inputDate);
        return date;
    }

	public static Date getDateFromString(String endDate) {
		DateFormat format = new SimpleDateFormat(MM_dd_yyy, Locale.ENGLISH);
		Date date = null;
		try {
			date = format.parse(endDate);
		} catch (ParseException e) {
			ExceptionUtil.logException(e, logger );
		}
		return date;
	}

}
