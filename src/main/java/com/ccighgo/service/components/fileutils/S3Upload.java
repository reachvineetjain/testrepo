package com.ccighgo.service.components.fileutils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.glacier.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.glacier.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.glacier.model.InitiateMultipartUploadResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.UploadPartRequest;

public class S3Upload {

   public static void main(String[] args) {
      String existingBucketName = "ccighgo";
      String keyName = "AKIAJSQRJVZU4FSC3OZQ";
      String filePath = "//G://file//butterfly.jpg";

      AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider());
      List<PartETag> partETags = new ArrayList<PartETag>();

      // Step 1: Initialize.
      InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(existingBucketName, keyName);
      InitiateMultipartUploadResult initResponse = s3Client.initiateMultipartUpload(initRequest);

      File file = new File(filePath);
      long contentLength = file.length();
      long partSize = 5242880; // Set part size to 5 MB.

      try {
         // Step 2: Upload parts.
         long filePosition = 0;
         for (int i = 1; filePosition < contentLength; i++) {
            // Last part can be less than 5 MB. Adjust part size.
            partSize = Math.min(partSize, (contentLength - filePosition));

            // Create request to upload a part.
            UploadPartRequest uploadRequest = new UploadPartRequest().withBucketName(existingBucketName).withKey(keyName).withUploadId(initResponse.getUploadId())
                  .withPartNumber(i).withFileOffset(filePosition).withFile(file).withPartSize(partSize);

            // Upload part and add response to our list.
            partETags.add(s3Client.uploadPart(uploadRequest).getPartETag());

            filePosition += partSize;
         }

         // Step 3: Complete.
         CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(existingBucketName, keyName, initResponse.getUploadId(), partETags);

         s3Client.completeMultipartUpload(compRequest);
      } catch (Exception e) {
         s3Client.abortMultipartUpload(new AbortMultipartUploadRequest(existingBucketName, keyName, initResponse.getUploadId()));
      }

   }

}
