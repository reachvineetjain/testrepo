package com.ccighgo.service.components.fileutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.regex.Pattern;

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
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ccighgo.utils.CCIConstants;

@Component
public class FileUtilInterfaceImpl implements FileUtilInterface {

   public static final String UPLOAD_LOCATION = "//G://file//";
   private static final Pattern urlPattern = Pattern.compile("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)" + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
         + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
   private static final String BUCKET_NAME = "ccighgo";
   private static final String CLONE_KEY = "cloned/";
   private static final Date URL_EXPIRATION_DATE = new Date(Long.MAX_VALUE);
   private static final String CLONE_URL_PREFIX = "https://ccighgo.s3.amazonaws.com/";

   @Override
   public String cloneUploadedFile(String filePath) {
      String returnUrl = null;
      if (filePath.startsWith(CCIConstants.HTTP) || filePath.startsWith(CCIConstants.HTTPS) || filePath.startsWith(CCIConstants.FTP)) {
         returnUrl = filePath;
      } else {
         returnUrl = uploadFileToS3(filePath);
      }
      return returnUrl;
   }

   @Override
   public String uploadFile(String filePath) {
      String uploadFilePath = null;
      if (filePath != null && !(filePath.isEmpty())) {
         File inputFile = new File(filePath);
         FileInputStream fis = null;
         OutputStream outputStream = null;
         try {
            fis = new FileInputStream(inputFile);
            int read = 0;
            byte[] bytes = new byte[1024];
            uploadFilePath = UPLOAD_LOCATION + inputFile.getName();

            File outFile = new File(uploadFilePath);
            if (outFile.exists()) {
               // throw message file with same name already exists
            } else {
               outputStream = new FileOutputStream(outFile);
               while ((read = fis.read(bytes)) != -1) {
                  outputStream.write(bytes, 0, read);
               }
               outputStream.flush();
               outputStream.close();
               fis.close();
            }
         } catch (Exception e) {

         } finally {

         }
      }
      return uploadFilePath;
   }

   public String uploadFileToS3(String filePath) {
      String clonedUrl = null;
      try {
         AWSCredentials credentials = new BasicAWSCredentials("AKIAJSQRJVZU4FSC3OZQ", "+Yme9uyeCz3WjVdGVTk+b2UckLRm+cDaaUXVkkl+");
         AmazonS3 s3Client = new AmazonS3Client(credentials);
         Region region = Region.getRegion(Regions.US_EAST_1);
         s3Client.setRegion(region);
         File inputFile = new File(filePath);
         String key = CLONE_KEY+inputFile.getName();
         s3Client.putObject(new PutObjectRequest(BUCKET_NAME, key, inputFile).withCannedAcl(CannedAccessControlList.PublicRead));
         URL s3Url = s3Client.generatePresignedUrl(BUCKET_NAME, key, URL_EXPIRATION_DATE);
         // s3Url will be something like https://ccighgo.s3.amazonaws.com/godoc/desert.jpg?AWSAccessKeyId=AKIAJSQRJVZU4FSC3OZQ&Expires=9223372036854775&Signature=eDYpT6htv9v1HPFDp0wH%2Bofm7bY%3D
         //so splitting and saving url as https://ccighgo.s3.amazonaws.com/godoc/desert.jpg
         String[] url = s3Url.toExternalForm().split("\\?");
         clonedUrl = url[0];

      } catch (AmazonServiceException ase) {
         System.out.println("Caught an AmazonServiceException, which means your request made it to Amazon S3, but was rejected with an error response for some reason.");
         System.out.println("Error Message:    " + ase.getMessage());
         System.out.println("HTTP Status Code: " + ase.getStatusCode());
         System.out.println("AWS Error Code:   " + ase.getErrorCode());
         System.out.println("Error Type:       " + ase.getErrorType());
         System.out.println("Request ID:       " + ase.getRequestId());
      } catch (AmazonClientException ace) {
         System.out.println("Caught an AmazonClientException, which means the client encountered a serious internal problem while trying to communicate with S3, "
               + "such as not being able to access the network.");
         System.out.println("Error Message: " + ace.getMessage());
      } 
      return clonedUrl;
   }

}
