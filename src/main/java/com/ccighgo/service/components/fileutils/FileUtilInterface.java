/**
 * 
 */
package com.ccighgo.service.components.fileutils;

import org.springframework.stereotype.Service;

/**
 * 
 * 
 * @author ravi
 *
 */
@Service
public interface FileUtilInterface {
   
   /**
    * Method takes file path of the file to be uploaded, uploads it and returns the path of uploaded location
    * 
    * @param filepath
    * @return returns complete file path of the file uploaded to the server
    */
   public String uploadFile(String filePath);
   
   /**
    * Method takes file path of the file to be uploaded, uploads it and returns the path of uploaded location.Used by season cloning services
    * 
    * @param filePath
    * @return returns complete file path of the cloned file uploaded to the server
    */
   public String cloneUploadedFile(String filePath);

}
