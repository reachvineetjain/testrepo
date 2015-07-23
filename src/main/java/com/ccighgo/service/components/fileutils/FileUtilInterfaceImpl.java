package com.ccighgo.service.components.fileutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ccighgo.exception.InvalidServiceConfigurationException;
import com.ccighgo.service.components.season.SeasonCloningHelper;
import com.ccighgo.utils.CCIConstants;

@Component
public class FileUtilInterfaceImpl implements FileUtilInterface {

   private Logger LOGGER = LoggerFactory.getLogger(FileUtilInterfaceImpl.class);

   private static final String AWS_BUCKET_NAME = "ccighgo";
   private static final String CLONE_KEY = "cloned/";
   private static final String UPLOAD_KEY = "ccighodocs/";
   private static final Date URL_EXPIRATION_DATE = new Date(Long.MAX_VALUE);
   private static final String CLONE_URL_PREFIX = "https://ccighgo.s3.amazonaws.com/";
   private static final String AWS_BUCKET_ID = "AKIAJSQRJVZU4FSC3OZQ";
   private static final String AWS_BUCKET_KEY = "+Yme9uyeCz3WjVdGVTk+b2UckLRm+cDaaUXVkkl+";
   

   @Override
   public String cloneUploadedFile(String filePath) {
      if(filePath==null || filePath.isEmpty()){
         throw new InvalidServiceConfigurationException("Please check your upload path/url. It cannot be null or malformed");
      }
      String returnUrl = null;
      if (filePath.startsWith(CCIConstants.HTTP) || filePath.startsWith(CCIConstants.HTTPS) || filePath.startsWith(CCIConstants.FTP)) {
         returnUrl = filePath;
      } else {
         returnUrl = uploadFileToS3(filePath, CLONE_KEY);
         LOGGER.info("file cloned successfully in s3 bucket: "+AWS_BUCKET_NAME+", in directory: "+CLONE_KEY+"for url: "+returnUrl);
      }
      return returnUrl;
   }

   @Override
   public String uploadFile(String filePath) {
      if(filePath==null || filePath.isEmpty()){
         throw new InvalidServiceConfigurationException("Please check your upload path/url. It cannot be null or malformed");
      }
      String returnUrl = null;
      if (filePath.startsWith(CCIConstants.HTTP) || filePath.startsWith(CCIConstants.HTTPS) || filePath.startsWith(CCIConstants.FTP)) {
         returnUrl = filePath;
      } else {
         returnUrl = uploadFileToS3(filePath, UPLOAD_KEY);
         LOGGER.info("file uploaded successfully in s3 bucket: "+AWS_BUCKET_NAME+", in directory: "+UPLOAD_KEY+" for url: "+returnUrl);
      }
      return returnUrl;
   }

   public String uploadFileToS3(String filePath, String keyPath) {
      String clonedUrl = null;
      try {
         AWSCredentials credentials = new BasicAWSCredentials(AWS_BUCKET_ID, AWS_BUCKET_KEY);
         AmazonS3 s3Client = new AmazonS3Client(credentials);
         Region region = Region.getRegion(Regions.US_EAST_1);
         s3Client.setRegion(region);
         File inputFile = new File(filePath);
         String key = keyPath + inputFile.getName();
         ObjectMetadata metadata = new ObjectMetadata();
         s3Client.putObject(new PutObjectRequest(AWS_BUCKET_NAME, key, inputFile).withCannedAcl(CannedAccessControlList.PublicRead));
         URL s3Url = s3Client.generatePresignedUrl(AWS_BUCKET_NAME, key, URL_EXPIRATION_DATE);
         // s3Url will be something like
         // https://ccighgo.s3.amazonaws.com/godoc/desert.jpg?AWSAccessKeyId=AKIAJSQRJVZU4FSC3OZQ&Expires=9223372036854775&Signature=eDYpT6htv9v1HPFDp0wH%2Bofm7bY%3D
         // so splitting and saving url as https://ccighgo.s3.amazonaws.com/godoc/desert.jpg
         String[] url = s3Url.toExternalForm().split("\\?");
         clonedUrl = url[0];
      } catch (AmazonServiceException ase) {
         LOGGER.debug("AmazonServiceException, request made it to Amazon S3, but was rejected with an error response");
         LOGGER.debug("Error Message: " + ase.getMessage());
         LOGGER.debug("HTTP Status Code: " + ase.getStatusCode());
         LOGGER.debug("AWS Error Code: " + ase.getErrorCode());
         LOGGER.debug("Error Type: " + ase.getErrorType());
         LOGGER.debug("Request ID: " + ase.getRequestId());
      } catch (AmazonClientException ace) {
         LOGGER.debug("AmazonClientException, internal problem while trying to communicate with S3.");
         LOGGER.debug("Error Message: " + ace.getMessage());
      }
      return clonedUrl;
   }

}
