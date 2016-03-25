package com.ccighgo.service.components.sevis.common;

import gov.ice.xmlschema.sevisbatch.log.TransactionLogType;

import java.io.File;
import java.io.StringWriter;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.SchemaFactory;

import org.springframework.context.MessageSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ccighgo.service.component.serviceutils.ApplicationContextProvider;
import com.google.common.base.Preconditions;

public final class SevisUtils {

   static MessageSource msgSource = ApplicationContextProvider.getContext().getBean(MessageSource.class);

   public static XMLGregorianCalendar convert(LocalDate date) {
      Preconditions.checkNotNull(date);

      GregorianCalendar gcal = GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
      try {
         XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
         return xcal;
      } catch (DatatypeConfigurationException e) {
         throw new RuntimeException(e.getMessage());
      }
   }

   public static XMLGregorianCalendar convert(LocalDateTime date) {
      Preconditions.checkNotNull(date);
      try {
         XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(date.toString());
         return xcal;
      } catch (DatatypeConfigurationException e) {
         throw new RuntimeException(e.getMessage());
      }
   }

   public static XMLGregorianCalendar convert(Date sqlDate) {
      Preconditions.checkNotNull(sqlDate);
      GregorianCalendar c = new GregorianCalendar();
      c.setTime(sqlDate);
      try {
         XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
         return date2;
      } catch (DatatypeConfigurationException e) {
         throw new RuntimeException(e.getMessage());
      }

   }

   public static String generateBatchId(String fName, String lName) {
      Preconditions.checkNotNull(fName);
      Preconditions.checkNotNull(lName);
      Preconditions.checkArgument(!fName.isEmpty());
      Preconditions.checkArgument(!lName.isEmpty());

      String fNameInitial = fName.substring(0, 1).toUpperCase();
      String lNameInitial = lName.substring(0, 1).toUpperCase();
      // TODO generate ID
      String batchId = "AAAAAAAAAAAA" + fNameInitial + lNameInitial;
      return batchId;
   }

   public static String createBatchId() {
      Instant timestamp = Instant.now();
      String str = timestamp.toString().replace("-", "").replace("T", "").replace(":", "");
      return str.substring(0, str.lastIndexOf("."));
   }

   public static TransactionLogType unmarshalSevisLog(File xmlFile) {
      Preconditions.checkNotNull(xmlFile);
      Preconditions.checkArgument(xmlFile.exists());

      try {
         JAXBContext context = JAXBContext.newInstance(TransactionLogType.class);
         Unmarshaller unmarshaller = context.createUnmarshaller();
         TransactionLogType transactionLog = (TransactionLogType) unmarshaller.unmarshal(xmlFile);
         return transactionLog;
      } catch (JAXBException e) {
         throw new RuntimeException(e);
      }
   }

   public static String getErrorMessage(String key) {
      Preconditions.checkNotNull(key);
      Preconditions.checkArgument(!key.isEmpty());
      try {
         return msgSource.getMessage(key, null, Locale.US);
      } catch (Exception e) {
         return key + " not available.";
      }
   }

   public static String getProperty(String key) {
      return getErrorMessage(key);
   }

   /**
    * Validates a JAXB object against a schema file.
    * 
    * @param ctx
    * @param jaxb
    * @param schemaFile
    * @return
    * @throws JAXBException
    * @throws SAXException
    */
   public static boolean validate(JAXBContext ctx, Object jaxb, File schemaFile) throws JAXBException, SAXException {
      Marshaller marshaller;
      marshaller = ctx.createMarshaller();

      // Set Schema for Validation
      SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
      marshaller.setSchema(sf.newSchema(schemaFile));

      // validate with schema
      marshaller.marshal(jaxb, new DefaultHandler());

      return true;
   }

   public static boolean validateJaxb(JAXBContext ctx, Object jaxb, File schemaFile) {
      Marshaller marshaller;
      try {
         marshaller = ctx.createMarshaller();

         // Set Schema for Validation
         SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
         marshaller.setSchema(sf.newSchema(schemaFile));

         // validate with schema
         marshaller.marshal(jaxb, new DefaultHandler());

         return true;
      } catch (JAXBException e) {
         e.printStackTrace();
      } catch (SAXException e) {
         e.printStackTrace();
      }

      return false;
   }

   /**
    * Marshals JAXB object into XML file.
    * 
    * @param ctx
    * @param jaxb
    * @param file
    * @return
    * @throws JAXBException
    */
   public static boolean marshalToFile(JAXBContext ctx, Object jaxb, File file) throws JAXBException {
      Marshaller marshaller = ctx.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      marshaller.marshal(jaxb, file);
      return true;
   }

   public static boolean marshalJaxbToFile(JAXBContext ctx, Object jaxb, File file) {
      Marshaller marshaller;
      try {
         marshaller = ctx.createMarshaller();
         marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
         marshaller.marshal(jaxb, file);
         return true;
      } catch (JAXBException e) {
         e.printStackTrace();
      }
      return false;
   }

   /**
    * Marshals JAXB object into a String object.
    * 
    * @param ctx
    * @param jaxb
    * @return
    * @throws JAXBException
    */
   public static String marshalToString(JAXBContext ctx, Object jaxb) throws JAXBException {
      StringWriter sw = new StringWriter();
      Marshaller marshaller = ctx.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
      marshaller.marshal(jaxb, sw);
      return sw.toString();
   }

   /**
    * Validates JAXB object and saves to a file.
    * 
    * @param jaxb
    * @param outputFile
    * @param schemaFile
    * @return {@link Boolean}
    */
   public static boolean generateBatchFile(Object jaxb, String outputFile, String schemaFile) {
      Preconditions.checkNotNull(jaxb);
      Preconditions.checkArgument(jaxb != null);
      Preconditions.checkNotNull(outputFile);
      Preconditions.checkArgument(!outputFile.isEmpty());
      Preconditions.checkNotNull(schemaFile);
      Preconditions.checkArgument(!schemaFile.isEmpty());

      try {
         JAXBContext context = JAXBContext.newInstance(jaxb.getClass());
         validate(context, jaxb, new File(schemaFile));
         return marshalToFile(context, jaxb, new File(outputFile));
      } catch (JAXBException e) {
         e.printStackTrace();
      } catch (SAXException e) {
         e.printStackTrace();
      }
      return false;
   }

   public static String batchFileRealPath(String file, ServletContext servletContext) {
      String xmlDir = SevisUtils.getProperty(SevisConstants.XML_DIR);
      String xmlFile = xmlDir + File.separator + file;
      return servletContext.getRealPath(xmlFile);
   }

}
