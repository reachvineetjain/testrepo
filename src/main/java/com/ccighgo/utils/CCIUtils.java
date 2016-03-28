/**
 * 
 */
package com.ccighgo.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ravi
 *
 */
public class CCIUtils {

   public static String nullCheck(String field, String value) {
      if (value != null && !(value.equals(CCIConstants.EMPTY_DATA))) {
         field = value;
      }
      return field;
   }

   /**
    * Method can take input of any primitive type list and returns comma
    * separated string of values. "?" in the list is left on purpose to take any
    * object type
    * 
    * @param ls
    * @param param
    * @return string
    */
   public static String parseParameter(List<Integer> ls, String param) {
      if (ls != null && !(ls.isEmpty()) && !(ls.get(0) == 0)) {
         StringBuffer sb = new StringBuffer();
         sb.append(CCIConstants.SINGLE_QUOTE);
         sb.append(StringUtils.join(ls, CCIConstants.COMMA));
         sb.append(CCIConstants.SINGLE_QUOTE);
         param = sb.toString();
      }
      return param;
   }

   public static String getActiveValue(Boolean b) {
      String returnVal = null;
      StringBuffer sb = null;
      if (b != null && b.equals(Boolean.TRUE)) {
         sb = new StringBuffer();
         sb.append(CCIConstants.SINGLE_QUOTE);
         sb.append(String.valueOf(CCIConstants.ACTIVE));
         sb.append(CCIConstants.SINGLE_QUOTE);
         returnVal = sb.toString();
      } else if (b != null && b.equals(Boolean.FALSE)) {
         sb = new StringBuffer();
         sb.append(CCIConstants.SINGLE_QUOTE);
         sb.append(String.valueOf(CCIConstants.INACTIVE));
         sb.append(CCIConstants.SINGLE_QUOTE);
         returnVal = sb.toString();
      } else {
         sb = new StringBuffer();
         sb.append(CCIConstants.SINGLE_QUOTE);
         sb.append(String.valueOf(CCIConstants.INACTIVE) + CCIConstants.COMMA + String.valueOf(CCIConstants.ACTIVE));
         sb.append(CCIConstants.SINGLE_QUOTE);
         returnVal = sb.toString();
      }
      return returnVal;
   }

   public static String formResetURL(HttpServletRequest request) {
      String url = "";
      try {
         url = request.getHeader("Origin") + CCIConstants.RESET_PASSWORD_LINK;
      } catch (Exception e) {
         e.printStackTrace();
      }
      return url;
   }

   public static String getNameString(String input) {
      if (input != null) {
         if (input.contains(";")) {
            String[] output = input.split(";");
            return output[0];
         } else {
            return input;
         }
      } else {
         return "";
      }
   }

   public static Double getFormFilledPercentage(Integer totalFields, Integer filledOnes) {
      Double filledPercentage = (double) (filledOnes * 100.0d) / totalFields;
      return filledPercentage;
   }

}
