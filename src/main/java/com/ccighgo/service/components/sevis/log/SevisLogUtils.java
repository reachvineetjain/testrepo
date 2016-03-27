package com.ccighgo.service.components.sevis.log;

import static java.util.stream.Collectors.toList;
import gov.ice.xmlschema.sevisbatch.log.TransactionLogType;
import gov.ice.xmlschema.sevisbatch.log.TransactionLogType.BatchDetail.Download;
import gov.ice.xmlschema.sevisbatch.log.TransactionLogType.BatchDetail.Process;
import gov.ice.xmlschema.sevisbatch.log.TransactionLogType.BatchDetail.Process.Record;
import gov.ice.xmlschema.sevisbatch.log.TransactionLogType.BatchDetail.Upload;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

import org.springframework.context.MessageSource;

import com.ccighgo.service.component.serviceutils.ApplicationContextProvider;

/**
 * 
 * Provides utility methods for SEVIS JAXB class {@link TransactionLogType} to
 * avoid the deep hierarchical structure.
 *
 */
public class SevisLogUtils {
   private static final String SUCCESS = "S0000";
   private TransactionLogType sevisLog;
   private List<Record> records;
   private Upload upload;
   private Process process;
   private Download download;

   private static MessageSource msgSource = ApplicationContextProvider.getContext().getBean(MessageSource.class);

   public SevisLogUtils(TransactionLogType log) {
      sevisLog = log;
      upload = sevisLog.getBatchDetail().getUpload();
      process = sevisLog.getBatchDetail().getProcess();
      download = sevisLog.getBatchDetail().getDownload();
      records = process.getRecord();
   }

   public boolean isAllWell() {
      return sevisLog != null && sevisLog.getBatchDetail().isStatus() == true;
   }

   public boolean isUploadSuccess() {
      /*
       * The resultCode attribute is set to success if the upload passes all
       * security requirements and the XML file is accepted and successfully
       * validated against the appropriate SEVIS schema. Otherwise, resultCode
       * contains the appropriate result / error code.
       */
      return upload != null && upload.getResultCode().equals(SUCCESS);
   }

   public String getUploadErrorMessage() {
      if (!isUploadSuccess()) {
         return getErrorMessage(upload.getResultCode());
      }
      return "";
   }

   public boolean isProcessSuccess() {
      /*
       * The resultCode attribute is set to success if all submitted records
       * process successfully. Otherwise, the resultCode indicates either
       * indicates that:
       * 
       * 1) file has not yet been processed or
       * 
       * 2) the file has been processed with at least one record failing
       * business rules validation (although all other records are successfully
       * loaded into SEVIS)
       */
      return process != null && process.getResultCode().equals(SUCCESS);
   }

   public String getProcessErrorMessage() {
      if (!isProcessSuccess()) {
         return getErrorMessage(process.getResultCode());
      }
      return "";
   }

   public boolean isDownloadSuccess() {
      return download != null && download.getResultCode().equals(SUCCESS);
   }

   public String getDownloadErrorMessage() {
      if (!isDownloadSuccess()) {
         return getErrorMessage(download.getResultCode());
      }
      return "";
   }

   /**
    * Total records submitted (must always equal Success + Failure) to SEVIS.
    * 
    * @return
    */
   public List<Record> getTotalRecords() {
      return records;
   }

   private Predicate<Record> successRecords = record -> record.getResult().getErrorCode().equals(SUCCESS);

   private Predicate<Record> failedRecords = successRecords.negate();

   /**
    * Records which failed business rule validation; consequently, these records
    * were not loaded into SEVIS.
    * 
    * @return
    */
   public List<Record> getFailedRecords() {
      return records.stream().filter(failedRecords).collect(toList());
   }

   /**
    * Records successfully loaded into SEVIS.
    * 
    * @return
    */
   public List<Record> getSuccessRecords() {
      return records.stream().filter(successRecords).collect(toList());
   }

   public boolean isDependentInfoAvailable(Record record) {
      return record.getDependent() != null && !record.getDependent().isEmpty();
   }

   public boolean isSOAInfoAvailable(Record record) {
      return record.getSiteOfActivity() != null && !record.getSiteOfActivity().isEmpty();
   }

   private static String getErrorMessage(String key) {
      try {
         return msgSource.getMessage(key, null, Locale.US);
      } catch (Exception e) {
         return key + " not available.";
      }
   }

}
