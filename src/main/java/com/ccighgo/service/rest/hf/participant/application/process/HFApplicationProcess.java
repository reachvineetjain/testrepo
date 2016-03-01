/**
 * 
 */
package com.ccighgo.service.rest.hf.participant.application.process;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.hf.participant.application.process.HFApplication;
import com.ccighgo.service.components.hf.participant.application.process.util.HomePageParam;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFApplicationFamilyDetails;
import com.ccighgo.service.transport.hostfamily.beans.application.familylifestyle.HFApplicationFamilyLifeStyle;
import com.ccighgo.service.transport.hostfamily.beans.application.homepage.HFHomePage;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.HFApplicationUploadPhotos;
import com.ccighgo.service.transport.hostfamily.beans.application.potential.hostfamily.PotentialHostFamily;
import com.ccighgo.service.transport.hostfamily.beans.application.references.HostFamilyReferences;
import com.ccighgo.service.transport.hostfamily.beans.application.whyhost.WhyHost;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * @author ravi/ahmed
 *
 */
@Path("/hf/application/")
@Produces("application/json")
@Consumes("application/json")
public class HFApplicationProcess {

   private static final Logger LOGGER = LoggerFactory.getLogger(HFApplicationProcess.class);

   @Autowired HFApplication hfApplication;

   @POST
   @Path("create/whyhost/{applicationCategoryId}")
   @Consumes("application/json")
   @Produces("application/json")
   public WhyHost createWhyHost(@PathParam("applicationCategoryId") String applicationCategoryId, WhyHost whyHost) {
      LOGGER.info("Calling service HFApplicationProcess.createWhyHost for hfSeasonId {}", whyHost.getSeasonId());
      return hfApplication.createWhyHost(applicationCategoryId, whyHost);
   }

   @GET
   @Path("get/whyhost/{hostFamilyHomeId}/{hfSeasonId}/{applicationCategoryId}")
   @Produces("application/json")
   public WhyHost getWhyHost(@PathParam("hostFamilyHomeId") String hostFamilyHomeId, @PathParam("hfSeasonId") String hfSeasonId,
         @PathParam("applicationCategoryId") String applicationCategoryId) {
      LOGGER.info("Calling service HFApplicationProcess.getWhyHost for hfSeasonId {}", hfSeasonId);
      return hfApplication.getWhyHost(hostFamilyHomeId, hfSeasonId, applicationCategoryId);
   }

   @POST
   @Path("update/whyhost/{applicationCategoryId}")
   @Consumes("application/json")
   @Produces("application/json")
   public WhyHost updateWhyHost(@PathParam("applicationCategoryId") String applicationCategoryId, WhyHost whyHost) {
      LOGGER.info("Calling service HFApplicationProcess.updateWhyHost for hfHomeId {}", whyHost.getHostFamilyHomeId());
      return hfApplication.updateWhyHost(applicationCategoryId, whyHost);
   }

   @POST
   @Path("upload/photo")
   @Consumes("application/json")
   @Produces("application/json")
   public HFApplicationUploadPhotos uploadHFPhotos(HFApplicationUploadPhotos hfApplicationUploadPhotos) {
      LOGGER.info("Calling service HFApplicationProcess.uploadHFPhotos");
      return hfApplication.uploadHFPhotos(hfApplicationUploadPhotos);
   }

   @GET
   @Path("get/pictures/{hfSeasonId}")
   @Consumes("application/json")
   @Produces("application/json")
   public HFApplicationUploadPhotos getHFPhotos(@PathParam("hfSeasonId") String hfSeasonId) {
      LOGGER.info("Calling service HFApplicationProcess.uploadHFPhotos");
      return hfApplication.getHFPhotos(hfSeasonId);
   }

   @GET
   @Path("delete/photo/{photoId}")
   @Produces("application/json")
   public Response deletePhoto(@PathParam("photoId") String photoId) {
      LOGGER.info("Calling service HFApplicationProcess.deletePhoto for photoId {}", photoId);
      return hfApplication.deletePhoto(photoId);
   }

   @POST
   @Path("hfHomepage")
   @Consumes("application/json")
   @Produces("application/json")
   public HFHomePage getHostFamilyHome(HomePageParam hpp) {
      return hfApplication.getHostFamilyHome(hpp);
   }

   @POST
   @Path("hfSaveBasicData")
   @Consumes("application/json")
   @Produces("application/json")
   public WSDefaultResponse saveFamilyBasicData(HFApplicationFamilyDetails hfApplicationFamilyDetails) {
      return hfApplication.saveFamilyBasicData(hfApplicationFamilyDetails);
   }

   @POST
   @Path("hfSaveFamilyLifeStyle")
   @Consumes("application/json")
   @Produces("application/json")
   public WSDefaultResponse saveFamilyLifeStyleData(HFApplicationFamilyLifeStyle hfApplicationFamilyDetails) {
      return hfApplication.saveFamilyLifeStyleData(hfApplicationFamilyDetails);
   }

   @POST
   @Path("create/hf/reference/{applicationCategoryId}")
   @Consumes("application/json")
   @Produces("application/json")
   public HostFamilyReferences createHFReference(@PathParam("applicationCategoryId") String applicationCategoryId, HostFamilyReferences hostFamilyReferences) {
      LOGGER.info("Calling service HFApplicationProcess.createHFReference");
      return hfApplication.createHFReference(applicationCategoryId, hostFamilyReferences);
   }

   @POST
   @Path("update/hf/reference/{applicationCategoryId}")
   @Consumes("application/json")
   @Produces("application/json")
   public HostFamilyReferences updateHFReference(@PathParam("applicationCategoryId") String applicationCategoryId, HostFamilyReferences hostFamilyReferences) {
      LOGGER.info("Calling service HFApplicationProcess.createHFReference");
      return hfApplication.updateHFReference(applicationCategoryId, hostFamilyReferences);
   }

   @GET
   @Path("get/hf/reference/{hfSeasonId}/{applicationCategoryId}")
   @Produces("application/json")
   public HostFamilyReferences getHFReference(@PathParam("hfSeasonId") String hfSeasonId, @PathParam("applicationCategoryId") String applicationCategoryId) {
      LOGGER.info("Calling service HFApplicationProcess.getHFReference");
      return hfApplication.getHFReference(hfSeasonId, applicationCategoryId);
   }

   @POST
   @Path("potential/reference/")
   @Consumes("application/json")
   @Produces("application/json")
   public Response addPotentialReference(PotentialHostFamily potentialHostFmaily) {
      LOGGER.info("Calling service HFApplicationProcess.addPotentialReference");
      return hfApplication.addPotentialReference(potentialHostFmaily);
   }

}
