/**
 * 
 */
package com.ccighgo.service.test.utility;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author Ahmed Abdelmaaboud
 *
 */

public class TestMessageResources {

	@Test
	public void test() {
		// fail("Not yet implemented");

		ApplicationContext context =new FileSystemXmlApplicationContext(
		        "src/main/webapp/WEB-INF/applicationContext.xml");

		String name = context.getMessage("ssais.header", new Object[] { 28,
				"http://www.mkyong.com" }, Locale.US);
		System.out.println(name);

	}

}
