/**
 * 
 */
package com.ccighgo.service.components.hf.participant.application.process;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.HFApplicationUploadPhotos;
import com.ccighgo.service.transport.hostfamily.beans.application.whyhost.WhyHost;

/**
 * @author ravi/ahmed
 *
 */
@Service
public interface HFApplication {

   /**
    * @param hfSeasonId
    * @param whyHost
    * @return
    */
   public WhyHost createWhyHost(String applicationCategoryId, WhyHost whyHost);

   /**
    * @param hfSeasonId
    * @return
    */
   public WhyHost getWhyHost(String hfHomeId, String hfSeasonId, String applicationCategoryId);

   /**
    * @param hfHomeId
    * @param applicationCategoryId
    * @param whyHost
    * @return
    */
   public WhyHost updateWhyHost(String applicationCategoryId, WhyHost whyHost);
   
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
