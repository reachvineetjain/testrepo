/**
 * 
 */
package com.ccighgo.utils;

import org.slf4j.Logger;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
public class ExceptionUtil {

   public static void logException(Exception ex, Logger logger) {
      if (logger != null && ex != null) {
         logger.error(ex.getMessage() + " : " + ex.getCause());
      }
   }

   public static void logException(Exception ex, org.apache.log4j.Logger logger) {
      if (logger != null && ex != null) {
         logger.error(ex.getMessage() + " : " + ex.getCause());
      }
   }

}
