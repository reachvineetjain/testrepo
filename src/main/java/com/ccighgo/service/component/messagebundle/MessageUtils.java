/**
 * 
 */
package com.ccighgo.service.component.messagebundle;

import java.util.Locale;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * @author ravi
 *
 */
@Component
@Scope("singleton")
public class MessageUtils {

   public static String getMessage(String key) {

      try {
         ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
         bean.setBasename("META-INF\\ccighgomessages");
         return bean.getMessage(key, null, Locale.getDefault());
      } catch (Exception e) {
         return "Unresolved key: " + key;
      }

   }

}
