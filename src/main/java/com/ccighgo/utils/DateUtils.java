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

   private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

   public static String getMMddYyyyString(Date inputDate) {
      SimpleDateFormat sdf = new SimpleDateFormat(CCIConstants.MM_DD_YY);
      String date = null;
      if (inputDate != null)
         try {
            date = sdf.format(inputDate);
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
      return date;
   }

   public static Date getDateFromString(String endDate) {
      DateFormat format = new SimpleDateFormat(CCIConstants.MM_DD_YY, Locale.US);
      Date date = null;
      if (endDate != null)
         try {
            date = format.parse(endDate);
         } catch (ParseException e) {
            ExceptionUtil.logException(e, logger);
         }
      return date;
   }

   /**
    * Method takes input as date and converts into String date in MM-DD-YY format
    * 
    * @param inputDate
    * @return
    */
   public static String getMMddyyDate(Date inputDate) {
      String date = null;
      try {
         if (inputDate != null)
            date = DateFormatUtils.format(inputDate, CCIConstants.MM_DD_YY);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, logger);
      }
      return date;
   }

   /**
    * Method takes string date as input and returns date object
    * 
    * @param inputString
    * @return
    */
   public static Date getMMddyyDateFromString(String inputString) {
      Date date = null;
      DateFormat format = new SimpleDateFormat(CCIConstants.MM_DD_YY);
      try {
         if (inputString != null && !inputString.isEmpty())
            date = format.parse(inputString);
      } catch (ParseException e) {
         ExceptionUtil.logException(e, logger);
      }
      return date;
   }
}
