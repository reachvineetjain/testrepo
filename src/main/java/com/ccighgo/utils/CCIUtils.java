/**
 * 
 */
package com.ccighgo.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ravi
 *
 */
public class CCIUtils {
   
   
   public static String nullCheck(String field, String value){
      if(value!=null && !(value.equals(CCIConstants.EMPTY_DATA))){
         field = value;
      }
      return field;
   }
   
   /**
    * Method can take input of any primitive type list and returns comma separated string of values.
    * "?" in the list is left on purpose to take any object type
    * 
    * @param ls
    * @param param
    * @return string
    */
   public static String parseParameter(List<?> ls, String param){
      if(ls!=null){
         StringBuffer sb = new StringBuffer();
         sb.append(CCIConstants.SINGLE_QUOTE);
         sb.append(StringUtils.join(ls,CCIConstants.COMMA));
         sb.append(CCIConstants.SINGLE_QUOTE);
         param = sb.toString();
      }
      return param;
   }

}
