/**
 * 
 */
package com.ccighgo.service.transport.utility.email;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccighgo.utils.ExceptionUtil;

/**
 * @author Ahmed Abdelmaaboud
 *
 */

public class EmailNotifications {

	private static Logger logger = LoggerFactory.getLogger(EmailNotifications.class);

	/**
	 * @param emailObject
	 * 
	 */
	public static void sendEmail(EmailObject emailObject) {
		Session session = Session.getDefaultInstance(EmailConfiguration.getProperties());
		try {
			sendEmail(session, getMimeMessage(emailObject, session));
		} catch (Exception e) {
			ExceptionUtil.logException(e, logger);
		}

	}

	  static MimeMessage getMimeMessage(EmailObject emailObject, Session session)throws Exception {
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(emailObject.getSender()));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(emailObject.getReceiver()));
		msg.setSubject(emailObject.getSubject());
		msg.setContent(emailObject.getBody(), emailObject.getContentType());
		return msg;
	}

	/**
	 * @param session
	 * @param msg
	 * @throws NoSuchProviderException
	 * @throws MessagingException
	 */
	static void sendEmail(Session session, MimeMessage msg)
			throws NoSuchProviderException, MessagingException {
		Transport transport = session.getTransport();
		try {
			System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");
			transport.connect(EmailConfiguration.HOST,
					EmailConfiguration.SMTP_USERNAME,
					EmailConfiguration.SMTP_PASSWORD);
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email sent!");
		} catch (Exception ex) {
		ExceptionUtil.logException(ex, logger);
		} finally {
			transport.close();
		}
	}
}