/**
 * 
 */
package com.ccighgo.service.test.utility;

import org.junit.Test;

import com.ccighgo.service.transport.utility.email.EmailNotifications;
import com.ccighgo.service.transport.utility.email.EmailObject;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
public class SendEmail {

	static final String FROM = "ravi.mishra@creospan.com";
	static final String TO = "ahmed.abdelmaaboud@creospan.com";
	static final String BODY = "This email was sent through the Amazon SES SMTP interface by using Java.";
	static final String SUBJECT = "Amazon SES test (SMTP interface accessed using Java)";
	/**
	 * 
	 */
	public static void main(String[] args) {
		
EmailObject emailObject =new EmailObject();
		emailObject.setBody(BODY);
		emailObject.setSender(FROM);
		emailObject.setReceiver(TO);
		emailObject.setSubject(SUBJECT);
		EmailNotifications.sendEmail(emailObject);
		
	}
}
