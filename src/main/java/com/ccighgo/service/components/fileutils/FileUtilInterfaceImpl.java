package com.ccighgo.service.components.fileutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Component;

@Component
public class FileUtilInterfaceImpl implements FileUtilInterface{
   
   public static final String UPLOAD_LOCATION = "//G://file//";

   @Override
   public String uploadFile(String filePath) {
      String uploadFilePath = null;
      if(filePath!=null && !(filePath.isEmpty())){
         File inputFile = new File(filePath);
         FileInputStream fis = null;
         OutputStream outputStream = null;
         try{
            fis = new FileInputStream(inputFile);
            int read = 0;
            byte[] bytes = new byte[1024];
            uploadFilePath = UPLOAD_LOCATION+inputFile.getName();
            
            File outFile = new File(uploadFilePath);
            if(outFile.exists()){
               //throw message file with same name already exists
            }else{
               outputStream = new FileOutputStream(outFile);
               while ((read = fis.read(bytes)) != -1) {
                  outputStream.write(bytes, 0, read);
               }
               outputStream.flush();
               outputStream.close();
               fis.close();
            }
         }catch(Exception e){
            
         }
         finally{
           
         }
      }
      return uploadFilePath;
   }

   @Override
   public String cloneUploadedFile(String filePath) {
      // TODO Auto-generated method stub
      return null;
   }

}
