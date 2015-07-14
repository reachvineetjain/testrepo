package com.ccighgo.service.components.fileutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.ccighgo.utils.CCIConstants;

@Component
public class FileUtilInterfaceImpl implements FileUtilInterface {

   public static final String UPLOAD_LOCATION = "//G://file//";
   private static final Pattern urlPattern = Pattern.compile("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)" + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
         + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
  

   @Override
   public String cloneUploadedFile(String filePath) {
      String returnUrl = null;
      if (filePath.startsWith(CCIConstants.HTTP)||filePath.startsWith(CCIConstants.HTTPS)||filePath.startsWith(CCIConstants.FTP)) {
         returnUrl = filePath;
      }else{
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
   
   public String uploadFileToS3(String filePath){
      String url = null;
      try{
         AmazonS3 s3Client = new AmazonS3Client();
         
      }catch(Exception e){
         
      }
      
      return filePath;
      
   }
   

}
