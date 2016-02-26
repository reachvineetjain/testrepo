/**
 * 
 */
package com.ccighgo.service.components.hf.participant.application.process;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.HFApplicationUploadPhotos;
import com.ccighgo.service.transport.hostfamily.beans.application.potential.hostfamily.PotentialHostFamily;
import com.ccighgo.service.transport.hostfamily.beans.application.references.HostFamilyReferences;
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
    * @param potentialHostFmaily
    * @return
    */
   public Response addPotentialReference(PotentialHostFamily potentialHostFmaily);

   /**
    * @param hostFamilyReferences
    * @return
    */
   public HostFamilyReferences createHFReference(String applicationCategoryId, HostFamilyReferences hostFamilyReferences);

   /**
    * @param applicationCategoryId
    * @param hostFamilyReferences
    * @return
    */
   public HostFamilyReferences updateHFReference(String applicationCategoryId, HostFamilyReferences hostFamilyReferences);

   /**
    * @param hfSeasonId
    * @param applicationCategoryId
    * @return
    */
   public HostFamilyReferences getHFReference(String hfSeasonId, String applicationCategoryId);

}
