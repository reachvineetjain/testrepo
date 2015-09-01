/**
 * 
 */
package com.ccighgo.service.component.emailing;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.simpleemail.AWSJavaMailTransport;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.UtilityServiceMessageConstants;

/**
 * @author ravi
 *
 */
@Component
public class EmailServiceImpl implements EmailService {

   private String fromAddress;
   private Properties mailProperties;

   @Autowired MessageUtils messageUtil;

   public void init() throws IOException {
      
      mailProperties = new Properties();
      mailProperties.setProperty(UtilityServiceMessageConstants.MAIL_TRANSPORT_PROTOCOL, messageUtil.getMessage(UtilityServiceMessageConstants.MAIL_TRANSPORT_PROTOCOL));
      mailProperties.setProperty(UtilityServiceMessageConstants.MAIL_AWS_USER, messageUtil.getMessage(UtilityServiceMessageConstants.MAIL_AWS_USER));
      mailProperties.setProperty(UtilityServiceMessageConstants.MAIL_AWS_PASSWORD, messageUtil.getMessage(UtilityServiceMessageConstants.MAIL_AWS_PASSWORD));
      fromAddress = messageUtil.getMessage(UtilityServiceMessageConstants.MAIL_FROM_ADDRESS);
   }

   public void send(String toAddress, String subject, String content, boolean isHtml) {
      LOGGER.trace("Sending email to '{}' with subject '{}'", new Object[] { toAddress, subject });
      try {
         init();
         // Create a new Message
         Session session = Session.getInstance(mailProperties);
         MimeMessage msg = new MimeMessage(session);
         msg.setFrom(new InternetAddress(fromAddress, "Admin"));
         msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
         msg.setSubject(subject);
         if (isHtml)
            msg.setContent(content, "text/html");
         else
            msg.setText(content);
         msg.saveChanges();
         Transport t = new AWSJavaMailTransport(session, null);
         t.connect();
         t.sendMessage(msg, null);
         t.close();
         LOGGER.trace("Sent email to {}", toAddress);
      } catch (AddressException e) {
         LOGGER.error("One or more of your addresses are improperly formatted.", e);
      } catch (MessagingException e) {
         LOGGER.error("Problem sending your message to Amazon's E-mail Service", e);
      } catch (IOException e) {
         LOGGER.error("Error loading credentials for Amazon SES", e);
      } catch (Exception e) {
         LOGGER.error("Problem sending your message to Amazon's E-mail Service", e);
      }
   }

   public String getFromAddress() {
      return fromAddress;
   }

   public void setFromAddress(String fromAddress) {
      this.fromAddress = fromAddress;
      LOGGER.trace("fromAddress: {}", fromAddress);
   }

   private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

}
