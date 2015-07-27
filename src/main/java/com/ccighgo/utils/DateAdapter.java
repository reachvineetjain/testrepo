package com.ccighgo.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.DatatypeConverter;

public class DateAdapter {
	  public static Date parseDate(String s) {
	    return DatatypeConverter.parseDate(s).getTime();
	  }
	  
	  public static String printDate(Date dt) {
	    Calendar cal = new GregorianCalendar();
	    cal.setTime(dt);
	    return DatatypeConverter.printDate(cal);
	  }

	  public static String format8601(Date date){
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);
			
			String dtFormatted = cal.get(Calendar.YEAR) + "-" + String.format("%02d",cal.get(Calendar.MONTH)+1) + "-" + 
					String.format("%02d",cal.get(Calendar.DAY_OF_MONTH)) + "T" + String.format("%02d",cal.get(Calendar.HOUR_OF_DAY)) + 
					":" + String.format("%02d",cal.get(Calendar.MINUTE)) + "Z";
			return dtFormatted;
	  }
}