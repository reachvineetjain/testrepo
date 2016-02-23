/**
 * 
 */
package com.ccighgo.service.components.hf.participant.application.process;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFApplicationFamilyDetails;
import com.ccighgo.service.transport.hostfamily.beans.application.homepage.HFHomePage;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.HFApplicationUploadPhotos;
import com.ccighgo.service.transport.hostfamily.beans.application.whyhost.WhyHost;
import com.ccighgo.utils.WSDefaultResponse;

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
    * @param hfApplicationUploadPhotos
    * @return
    */
   public HFApplicationUploadPhotos uploadHFPhotos(HFApplicationUploadPhotos hfApplicationUploadPhotos);

   /**
    * @param photoId
    * @return
    */
   public Response deletePhoto(String photoId);

   /**
    * @param hfSeasonId
    * @return
    */
   public HFApplicationUploadPhotos getHFPhotos(String hfSeasonId);

   /**
    * Service to fetch HF Home Page Sections
    * @param goId
    * @param loginId
    * @return
    */
   public HFHomePage getHostFamilyHome(String goId, String loginId);

   /**
    * 
    * @param hfApplicationFamilyDetails
    * @return
    */
   public WSDefaultResponse saveFamilyBasicData(HFApplicationFamilyDetails hfApplicationFamilyDetails);

}
