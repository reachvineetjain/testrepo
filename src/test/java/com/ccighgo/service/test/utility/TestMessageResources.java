/**
 * 
 */
package com.ccighgo.service.test.utility;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author Ahmed Abdelmaaboud
 *
 */

public class TestMessageResources {

   // @Test
   public void test() {
      // fail("Not yet implemented");

      ApplicationContext context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");

      String name = context.getMessage("login.err.user.notexist", new Object[] { 28, "creoSpan.com" }, Locale.US);
      System.out.println(name);

   }

}
