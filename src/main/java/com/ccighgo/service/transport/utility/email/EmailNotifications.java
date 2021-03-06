/**
 * 
 */
package com.ccighgo.service.transport.utility.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

   static MimeMessage getMimeMessage(EmailObject emailObject, Session session) throws Exception {
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
   static void sendEmail(Session session, MimeMessage msg) throws NoSuchProviderException, MessagingException {
      Transport transport = session.getTransport();
      try {
         logger.info("Attempting to send an email through the Amazon SES SMTP interface...");
         transport.connect(EmailConfiguration.HOST, EmailConfiguration.SMTP_USERNAME, EmailConfiguration.SMTP_PASSWORD);
         transport.sendMessage(msg, msg.getAllRecipients());
         logger.info("Email sent!");
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      } finally {
         transport.close();
      }
   }
}