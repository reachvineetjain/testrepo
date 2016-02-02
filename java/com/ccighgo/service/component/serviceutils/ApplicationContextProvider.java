/**
 * 
 */
package com.ccighgo.service.component.serviceutils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Root application context provider. Returns context if already exists else initializes and returns one
 * 
 * @author ravi
 *
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

   private static ApplicationContext context;

   public static ApplicationContext getContext() {
      if (context != null) {
         return context;
      } else {
         throw new IllegalStateException("The application context is not available.");
      }
   }

   @Override
   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      if (context == null) {
         ApplicationContextProvider.context = applicationContext;
      } else {
         throw new IllegalStateException("The application context provider was already initialized.");
      }
   }

}
