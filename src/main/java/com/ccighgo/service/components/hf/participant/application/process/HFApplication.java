/**
 * 
 */
package com.ccighgo.service.components.hf.participant.application.process;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFApplicationFamilyDetails;
import com.ccighgo.service.transport.hostfamily.beans.application.homepage.HFHomePage;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.HFApplicationUploadPhotos;
import com.ccighgo.utils.WSDefaultResponse;

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

   /**
    * Service to fetch HF Home Page Sections
    * @param goId
    * @param loginId
    * @return
    */
   public HFHomePage getHostFamilyHome(String goId, String loginId);

public WSDefaultResponse saveOrUpdateFamilyBasicData(HFApplicationFamilyDetails hfApplicationFamilyDetails);

}
