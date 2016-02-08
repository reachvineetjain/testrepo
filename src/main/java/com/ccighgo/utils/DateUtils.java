package com.ccighgo.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccighgo.exception.CcighgoException;

public class DateUtils {

   private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

   public static String getMMddYyyyString(Date inputDate) {
      SimpleDateFormat sdf = new SimpleDateFormat(CCIConstants.MM_dd_yyy, Locale.US);
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
      DateFormat format = new SimpleDateFormat(CCIConstants.MM_dd_yyy, Locale.US);
      Date date = null;
      if (endDate != null && !endDate.trim().isEmpty())
         try {
            date = format.parse(endDate);
         } catch (ParseException e) {
            ExceptionUtil.logException(e, logger);
         }
      return date;
   }
   public static Date getMysqlDateFromString(String endDate) {
      DateFormat format = new SimpleDateFormat(CCIConstants.MYSQL_DATE_FORMAT, Locale.US);
      Date date = null;
      if (endDate != null && !endDate.trim().isEmpty())
         try {
            date = format.parse(endDate);
         } catch (ParseException e) {
            ExceptionUtil.logException(e, logger);
         }
      return date;
   }
   public static Date getMysqlDateFromString_FormatwithSlash(String endDate) {
	      DateFormat format = new SimpleDateFormat(CCIConstants.MYSQL_DATE_FORMAT_SLASH, Locale.US);
	      Date date = null;
	      if (endDate != null && !endDate.trim().isEmpty())
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
            date = DateFormatUtils.format(inputDate, CCIConstants.MM_dd_yyy, Locale.US);
      } catch (CcighgoException e) {
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
   public static String getUSDate(Date inputDate) {
      String date = null;
      try {
         if (inputDate != null)
            date = DateFormatUtils.format(inputDate, CCIConstants.MM_DD_YY, Locale.US);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, logger);
      }
      return date;
   }

   /**
    * Method takes input as date and converts into String date in MM-DD-YY hh:mm:ss format
    * 
    * @param inputDate
    * @return
    */
   public static String getTimeStamp(Timestamp inputDate) {
      String date = null;
      try {
         if (inputDate != null)
            return inputDate.getTime() + "";
      } catch (CcighgoException e) {
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
   public static String getDateAndTime(Date inputDate) {
      String date = null;
      try {
         if (inputDate != null)
            date = DateFormatUtils.format(inputDate, CCIConstants.DATE_TIME, Locale.US);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, logger);
      }
      return date;
   }
   
   public static String getDateAndTime2(Date inputDate) {
	      String date = null;
	      try {
	         if (inputDate != null)
	            date = DateFormatUtils.format(inputDate, CCIConstants.DATE_TIME2, Locale.US);
	      } catch (CcighgoException e) {
	         ExceptionUtil.logException(e, logger);
	      }
	      return date;
	   }
   
   public static String getTimestamp(Date inputDate) {
      String date = null;
      try {
         if (inputDate != null)
            date = String.valueOf(inputDate.getTime());
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
      DateFormat format = new SimpleDateFormat(CCIConstants.MM_dd_yyy, Locale.US);
      try {
         if (inputString != null && !inputString.trim().isEmpty())
            date = format.parse(inputString);
      } catch (ParseException e) {
         ExceptionUtil.logException(e, logger);
      }
      return date;
   }

   public static XMLGregorianCalendar getXMLGregorianCalendar(Date date) {
      GregorianCalendar c = new GregorianCalendar(Locale.US);
      try {
         if (date == null)
            return null;
         c.setTime(date);
         return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
      } catch (DatatypeConfigurationException e) {
         ExceptionUtil.logException(e, logger);
      }

      return null;
   }

   public static Date getXMLGregorianCalendarFromDate(XMLGregorianCalendar xmlGregorianCalendar) {
      try {
         if (xmlGregorianCalendar == null)
            return null;

         GregorianCalendar gregorianCalendar = xmlGregorianCalendar.toGregorianCalendar();
         return gregorianCalendar.getTime();
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return null;
   }

   public static Date getDateFromString_followUpdate(String newFollowUpDate) {
      DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
      Date date = null;
      if (newFollowUpDate != null && !newFollowUpDate.trim().isEmpty())
         try {
            date = format.parse(newFollowUpDate);
         } catch (ParseException e) {
            ExceptionUtil.logException(e, logger);
         }
      return date;
   }
   
   public static String getFormattedStringDate(String date){
      Date dt = getDateFromString(date);
      return getMMddyyDate(dt);
   }
   
   public static String getUSFormatDate(String date){
      Date dt = getDateFromString(date);
      return getUSDate(dt);
   }
}
