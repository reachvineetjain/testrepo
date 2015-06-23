package com.ccighgo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccighgo.exception.CcighgoException;

public class DateUtils {

   private static final String MM_dd_yyy = "MM-dd-yyyy";
   private static final String MM_dd_yy = "MM-dd-yy";
   private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

   public static String getMMddYyyyString(Date inputDate) {
      SimpleDateFormat sdf = new SimpleDateFormat(MM_dd_yyy);
      String date = sdf.format(inputDate);
      return date;
   }

   public static Date getDateFromString(String endDate) {
      DateFormat format = new SimpleDateFormat(MM_dd_yyy, Locale.US);
      Date date = null;
      try {
         date = format.parse(endDate);
      } catch (ParseException e) {
         ExceptionUtil.logException(e, logger);
      }
      return date;
   }

   public static String getMMddyyDate(Date inputDate) {
      String date = null;
      try {
         date = DateFormatUtils.format(inputDate, CCIConstants.MM_DD_YY);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, logger);
      }
      return date;
   }
}
