/**
 * 
 */
package com.ccighgo.utils;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
public class ExceptionUtil {

	public static void logException(Exception ex, Logger logger) {
		if (logger != null && ex != null) {
			logger.error(ex.getMessage() + " : " + ex.getCause());
			ex.printStackTrace();
		}
	}

}
