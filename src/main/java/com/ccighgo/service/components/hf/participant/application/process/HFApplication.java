/**
 * 
 */
package com.ccighgo.service.components.hf.participant.application.process;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.HFApplicationUploadPhotos;

/**
 * @author ravi/ahmed
 *
 */
@Service
public interface HFApplication {

   /**
    * Service used to upload host family photos for application process
    * 
    * @param goId
    * @param seasonId
    * @param hfApplicationUploadPhotos
    * @return
    */
   public HFApplicationUploadPhotos uploadHFPhotos(String goId, String seasonId, HFApplicationUploadPhotos hfApplicationUploadPhotos);

}
