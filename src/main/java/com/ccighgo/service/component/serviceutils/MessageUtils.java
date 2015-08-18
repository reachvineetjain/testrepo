/**
 * 
 */
package com.ccighgo.service.component.serviceutils;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * MessageUtils loads properties file from application context.
 * 
 * Autowire this class in your class to get messages from properties file
 * 
 * @author ravi
 *
 */
@Component
@Scope("singleton")
public class MessageUtils {
   public String getMessage(String key) {
      try {
         MessageSource bean = ApplicationContextProvider.getContext().getBean(MessageSource.class);
         return bean.getMessage(key, null, Locale.getDefault());
      } catch (Exception e) {
         e.printStackTrace();
         return "Unresolved key: " + key;
      }
   }
}
