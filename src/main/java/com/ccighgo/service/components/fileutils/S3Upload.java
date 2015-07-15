package com.ccighgo.service.components.fileutils;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.regex.Pattern;

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


public class S3Upload {

   public static void main(String[] args) {
      final String UPLOAD_LOCATION = "https://ccighgo.s3.amazonaws.com/godoc/desert.jpg";
       final String BUCKET_NAME = "ccighgo";
      try {
         AWSCredentials credentials = new BasicAWSCredentials("AKIAJSQRJVZU4FSC3OZQ", "+Yme9uyeCz3WjVdGVTk+b2UckLRm+cDaaUXVkkl+");
         AmazonS3 s3Client = new AmazonS3Client(credentials);
         Region region = Region.getRegion(Regions.US_EAST_1);
         s3Client.setRegion(region);
         
         
         File inputFile = new File(UPLOAD_LOCATION);
        // FileInputStream fis = new FileInputStream(inputFile);
         
         
         s3Client.putObject(new PutObjectRequest(BUCKET_NAME, "godoc/"+inputFile.getName(), inputFile));
         URL url =  s3Client.generatePresignedUrl(BUCKET_NAME, "godoc/"+inputFile.getName(), new Date(Long.MAX_VALUE));
         String[] s = url.toExternalForm().split("\\?");
         System.out.println(s[0]);
         System.out.println(url.toString());
         System.out.println(url.getPath());
         System.out.println(url.getFile());
         System.out.println(url.getHost());
         System.out.println(url.getProtocol());
         
       //  s3Client.getS3AccountOwner().
        
         //s3Client.putObject(BUCKET_NAME, KEY,new File(filePath)).withCannedAcl(CannedAccessControlList.PublicRead));

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
   }

}
