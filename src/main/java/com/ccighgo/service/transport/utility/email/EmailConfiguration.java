/**
 * 
 */
package com.ccighgo.service.transport.utility.email;

import java.util.Properties;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
public class EmailConfiguration {
	
	public static final String SMTP_USERNAME = "AKIAJMRZWKWB57EXJXPA";
	public 	static final String SMTP_PASSWORD = "AngQkvsTRoRI5s/A6sgJQDCpu6KGyMFM2LpnjnVgq/7a";
	public static final String HOST = "email-smtp.us-east-1.amazonaws.com";
	static final int PORT = 25;

	
	public static Properties getProperties() {
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", EmailConfiguration.PORT);

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");
		return props;

	}

}
