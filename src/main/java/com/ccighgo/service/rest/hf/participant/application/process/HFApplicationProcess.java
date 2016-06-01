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
import com.ccighgo.service.components.hf.participant.application.process.util.ChangeHostFamilyProfilePicParam;
import com.ccighgo.service.components.hf.participant.application.process.util.FamilyBasicsPageParam;
import com.ccighgo.service.components.hf.participant.application.process.util.FamilyStylePageParam;
import com.ccighgo.service.components.hf.participant.application.process.util.HFAirportList;
import com.ccighgo.service.components.hf.participant.application.process.util.HFCommunityAndSchoolPageParam;
import com.ccighgo.service.components.hf.participant.application.process.util.HFHomeDescriptionPageParam;
import com.ccighgo.service.components.hf.participant.application.process.util.HFSeasonList;
import com.ccighgo.service.components.hf.participant.application.process.util.HomePageParam;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.hostfamily.beans.application.background.check.HFBackgroundCheck;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFApplicationFamilyDetails;
import com.ccighgo.service.transport.hostfamily.beans.application.familylifestyle.HFApplicationFamilyLifeStyle;
import com.ccighgo.service.transport.hostfamily.beans.application.familymember.HFFamilyMember;
import com.ccighgo.service.transport.hostfamily.beans.application.familymembers.HostFamilyMembers;
import com.ccighgo.service.transport.hostfamily.beans.application.hfcommunityandschoolpage.HFCommunityAndSchoolPage;
import com.ccighgo.service.transport.hostfamily.beans.application.hfhousedescriptionpage.HFHomeDescriptionPage;
import com.ccighgo.service.transport.hostfamily.beans.application.homepage.HFHomePage;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.HFApplicationUploadPhotos;
import com.ccighgo.service.transport.hostfamily.beans.application.potential.hostfamily.PotentialHostFamily;
import com.ccighgo.service.transport.hostfamily.beans.application.profile.HFProfile;
import com.ccighgo.service.transport.hostfamily.beans.application.progress.HFApplicationProgress;
import com.ccighgo.service.transport.hostfamily.beans.application.references.HostFamilyReferences;
import com.ccighgo.service.transport.hostfamily.beans.application.submit.HFSubmitApplication;
import com.ccighgo.service.transport.hostfamily.beans.application.whyhost.WhyHost;
import com.ccighgo.service.transport.participant.beans.hfparticipantlist.HFParticipantDetail;
import com.ccighgo.service.transport.participant.beans.hfparticipantlist.HFParticipantList;
import com.ccighgo.service.transport.partner.beans.hfp2workqueuecategory.HFP2WorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.hfp2workqueuetype.HFP2WorkQueueType;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.AdminPartnerWorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.AdminPartnerWorkQueueType;
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
      LOGGER.info("Calling service HFApplicationProcess.createWhyHost for hfSeasonId {}", whyHost.getHostfamilySeasonId());
      return hfApplication.createWhyHost(applicationCategoryId, whyHost);
   }

   @GET
   @Path("get/whyhost/{hfSeasonId}/{applicationCategoryId}")
   @Produces("application/json")
   public WhyHost getWhyHost(@PathParam("hfSeasonId") String hfSeasonId, @PathParam("applicationCategoryId") String applicationCategoryId) {
      LOGGER.info("Calling service HFApplicationProcess.getWhyHost for hfSeasonId {}", hfSeasonId);
      return hfApplication.getWhyHost(hfSeasonId, applicationCategoryId);
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
   @Path("upload/mandatory/photo")
   @Consumes("application/json")
   @Produces("application/json")
   public HFApplicationUploadPhotos uploadHFMandatoryPhotos(HFApplicationUploadPhotos hfApplicationUploadPhotos) {
      LOGGER.info("Calling service HFApplicationProcess.uploadHFPhotos");
      return hfApplication.uploadHFMandatoryPhotos(hfApplicationUploadPhotos);
   }

   @POST
   @Path("upload/optional/photo")
   @Consumes("application/json")
   @Produces("application/json")
   public HFApplicationUploadPhotos uploadOptionalHFPhotos(HFApplicationUploadPhotos hfApplicationUploadPhotos) {
      LOGGER.info("Calling service HFApplicationProcess.uploadHFPhotos");
      return hfApplication.uploadOptionalHFPhotos(hfApplicationUploadPhotos);
   }

   @GET
   @Path("get/pictures/{hfSeasonId}")
   @Produces("application/json")
   public HFApplicationUploadPhotos getHFPhotos(@PathParam("hfSeasonId") String hfSeasonId) {
      LOGGER.info("Calling service HFApplicationProcess.uploadHFPhotos");
      return hfApplication.getHFPhotos(hfSeasonId);
   }

   @GET
   @Path("delete/photo/{photoId}/{optional}/{loginId}")
   @Produces("application/json")
   public Response deletePhoto(@PathParam("photoId") String photoId, @PathParam("optional") String optional, @PathParam("loginId") String loginId) {
      LOGGER.info("Calling service HFApplicationProcess.deletePhoto for photoId {}", photoId);
      return hfApplication.deletePhoto(Integer.parseInt(photoId), Integer.parseInt(optional), Integer.parseInt(loginId));
   }

   @POST
   @Path("hfHomepage")
   @Consumes("application/json")
   @Produces("application/json")
   public HFHomePage getHostFamilyHome(HomePageParam hpp) {
      return hfApplication.getHostFamilyHome(hpp);
   }

   @POST
   @Path("hfSaveBasicData/{applicationCategoryId}")
   @Consumes("application/json")
   @Produces("application/json")
   public HFApplicationFamilyDetails saveFamilyBasicData(@PathParam("applicationCategoryId") String applicationCategoryId, HFApplicationFamilyDetails hfApplicationFamilyDetails) {
      return hfApplication.saveFamilyBasicData(applicationCategoryId, hfApplicationFamilyDetails);
   }

   @POST
   @Path("hfSaveFamilyLifeStyle/{applicationCategoryId}")
   @Consumes("application/json")
   @Produces("application/json")
   public HFApplicationFamilyLifeStyle saveFamilyLifeStyleData(@PathParam("applicationCategoryId") String applicationCategoryId,
         HFApplicationFamilyLifeStyle hfApplicationFamilyDetails) {
      return hfApplication.saveFamilyLifeStyleData(applicationCategoryId, hfApplicationFamilyDetails);
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

   @POST
   @Path("fetchFamilyLifeStyle")
   @Consumes("application/json")
   @Produces("application/json")
   public HFApplicationFamilyLifeStyle fetchFamilyLifeStyle(FamilyStylePageParam familyStylePageParam) {
      LOGGER.info("Calling service HFApplicationProcess.fetchFamilyLifeStyle");
      return hfApplication.fetchFamilyLifeStyle(familyStylePageParam);
   }

   @POST
   @Path("fetchBasicData")
   @Consumes("application/json")
   @Produces("application/json")
   public HFApplicationFamilyDetails fetchBasicData(FamilyBasicsPageParam familyBasicsPageParam) {
      LOGGER.info("Calling service HFApplicationProcess.fetchBasicData");
      return hfApplication.fetchBasicData(familyBasicsPageParam);
   }

   @POST
   @Path("hfSaveHouseDescription/{applicationCategoryId}")
   @Consumes("application/json")
   @Produces("application/json")
   public HFHomeDescriptionPage createHFHouseDescription(@PathParam("applicationCategoryId") String applicationCategoryId, HFHomeDescriptionPage descriptionPage) {
      LOGGER.info("Calling service HFApplicationProcess.createHFHouseDescription");
      return hfApplication.saveHFHouseDescription(applicationCategoryId, descriptionPage);
   }

   @POST
   @Path("fetchHFHouseDescription")
   @Consumes("application/json")
   @Produces("application/json")
   public HFHomeDescriptionPage fetchHFHouseDescription(HFHomeDescriptionPageParam descriptionPageParam) {
      LOGGER.info("Calling service HFApplicationProcess.fetchHFHouseDescription");
      return hfApplication.fetchHFHouseDescription(descriptionPageParam);
   }

   @POST
   @Path("hfSaveCoummnityAndSchoolDetails/{applicationCategoryId}")
   @Consumes("application/json")
   @Produces("application/json")
   public HFCommunityAndSchoolPage createHFCoummnityAndSchool(@PathParam("applicationCategoryId") String applicationCategoryId, HFCommunityAndSchoolPage communityAndSchoolPage) {
      LOGGER.info("Calling service HFApplicationProcess.createHFCoummnityAndSchool");
      return hfApplication.saveHFCoummnityAndSchool(applicationCategoryId, communityAndSchoolPage);
   }

   @POST
   @Path("fetchHFCoummnityAndSchool")
   @Consumes("application/json")
   @Produces("application/json")
   public HFCommunityAndSchoolPage fetchHFCoummnityAndSchool(HFCommunityAndSchoolPageParam descriptionPageParam) {
      LOGGER.info("Calling service HFApplicationProcess.fetchHFCoummnityAndSchool");
      return hfApplication.fetchHFCoummnityAndSchool(descriptionPageParam);
   }

   @GET
   @Path("background/details/{hfSeasonId}")
   @Produces("application/json")
   public HFBackgroundCheck getHFBackgroundDetails(@PathParam("hfSeasonId") String hfSeasonId) {
      LOGGER.info("Calling service HFApplicationProcess.getHFBackgroundDetails");
      return hfApplication.getHFBackgroundDetails(hfSeasonId);
   }

   @GET
   @Path("progress/{hfSeasonId}")
   @Produces("application/json")
   public HFApplicationProgress getHFApplicationProgress(@PathParam("hfSeasonId") String hfSeasonId) {
      LOGGER.info("Calling service HFApplicationProcess.getHFApplicationProgress");
      return hfApplication.getHFApplicationProgress(hfSeasonId);
   }

   @POST
   @Path("submit")
   @Consumes("application/json")
   @Produces("application/json")
   public Response submitApplication(HFSubmitApplication application) {
      LOGGER.info("Calling service HFApplicationProcess.submitApplication");
      return hfApplication.submitApplication(application);
   }

   @POST
   @Path("changeProfilePic")
   @Consumes("application/json")
   @Produces("application/json")
   public WSDefaultResponse changeProfilePicture(ChangeHostFamilyProfilePicParam param) {
      LOGGER.info("Calling service HFApplicationProcess.changeProfilePicture");
      return hfApplication.changeProfilePicture(param);
   }

   @GET
   @Path("airportList")
   @Produces("application/json")
   public HFAirportList hfAirportList() {
      LOGGER.info("Calling service HFApplicationProcess.hfAirportList");
      return hfApplication.hfAirportList();
   }

   @GET
   @Path("removeHostFamilyAirport/{hfAirportId}")
   @Produces("application/json")
   public WSDefaultResponse removeHostFamilyAirport(@PathParam("hfAirportId") String hfAirportId) {
      LOGGER.info("Calling service HFApplicationProcess.removeHostFamilyAirport");
      return hfApplication.removeHostFamilyAirport(Integer.valueOf(hfAirportId));
   }

   @GET
   @Path("removeHostFamilyPet/{hfPetId}")
   @Produces("application/json")
   public WSDefaultResponse removeHostFamilyPet(@PathParam("hfPetId") String hfPetId) {
      LOGGER.info("Calling service HFApplicationProcess.removeHostFamilyPet");
      return hfApplication.removeHostFamilyPet(Integer.parseInt(hfPetId));
   }

   @GET
   @Path("removeHostFamilyAdult/{hfAdultId}")
   @Produces("application/json")
   public WSDefaultResponse removeHostFamilyAdult(@PathParam("hfAdultId") String hfAdultId) {
      LOGGER.info("Calling service HFApplicationProcess.removeHostFamilyAdult");
      return hfApplication.removeHostFamilyAdult(Integer.parseInt(hfAdultId));
   }

   @GET
   @Path("view/profile/{hfSeasonId}/{loginId}")
   @Produces("application/json")
   public HFProfile viewHFProfile(@PathParam("hfSeasonId") String hfSeasonId, @PathParam("loginId") String loginId) {
      LOGGER.info("Calling service HFApplicationProcess.viewHFProfile");
      return hfApplication.viewHFProfile(Integer.parseInt(hfSeasonId), Integer.parseInt(loginId));
   }

   @GET
   @Path("getHFMembers/{seasonId}")
   @Produces("application/json")
   public HFFamilyMember getHFMembers(@PathParam("seasonId") Integer seasonId) {
      LOGGER.info("Calling service HFApplicationProcess.getHFMembers");
      return hfApplication.getHFMembers(seasonId);
   }

   @GET
   @Path("getHFDetails/{hostfamilySeasonId}")
   @Produces("application/json")
   public HostFamilyMembers getHFDetails(@PathParam("hostfamilySeasonId") Integer hostfamilySeasonId) {
      LOGGER.info("Calling service HFApplicationProcess.getHFDetails");
      return hfApplication.getHFDetails(hostfamilySeasonId);
   }

   // ///// part 2

   @GET
   @Path("getSeasonList/{hostFamilyGoId}")
   @Produces("application/json")
   public HFSeasonList getSeasonList(@PathParam("hostFamilyGoId") String hostFamilyGoId) {
      LOGGER.info("Calling service HFApplicationProcess.getSeasonList");
      return hfApplication.getSeasonList(Integer.valueOf(hostFamilyGoId));
   }

   @GET
   @Path("getPresentedParticipant/{hostFamilyGoId}/{category}")
   @Produces("application/json")
   public HFParticipantList getPresentedParticipant(@PathParam("hostFamilyGoId") String hostFamilyGoId, @PathParam("category") String category) {
      LOGGER.info("Calling service HFApplicationProcess.getPresentedParticipant");
      return hfApplication.getParticipantsList(Integer.valueOf(hostFamilyGoId), category);
   }

   @GET
   @Path("getPlacedParticipant/{hostFamilyGoId}/{category}")
   @Produces("application/json")
   public HFParticipantList getPlacedParticipant(@PathParam("hostFamilyGoId") String hostFamilyGoId, @PathParam("category") String category) {
      LOGGER.info("Calling service HFApplicationProcess.getPlacedParticipant");
      return hfApplication.getParticipantsList(Integer.valueOf(hostFamilyGoId), category);
   }

   @GET
   @Path("getParticipantHistory/{hostFamilyGoId}/{category}")
   @Produces("application/json")
   public HFParticipantList getParticipantHistory(@PathParam("hostFamilyGoId") String hostFamilyGoId, @PathParam("category") String category) {
      LOGGER.info("Calling service HFApplicationProcess.getParticipantHistory");
      return hfApplication.getParticipantsList(Integer.valueOf(hostFamilyGoId), category);
   }

   @GET
   @Path("getParticipantDetail/{participantId}")
   @Produces("application/json")
   public HFParticipantDetail getParticipantDetail(@PathParam("participantId") String participantId) {
      LOGGER.info("Calling service HFApplicationProcess.getParticipantDetail");
      return hfApplication.getParticipantDetail(Integer.valueOf(participantId));
   }

   @GET
   @Path("create/mandatory/photos/{hfSeasonId}/{loginId}")
   @Produces("application/json")
   public HFApplicationUploadPhotos hfCreateMandatoryPhotos(@PathParam("hfSeasonId") String hfSeasonId, @PathParam("loginId") String loginId) {
      LOGGER.info("Calling service HFApplicationProcess.hfCreateMandatoryPhotos");
      return hfApplication.hfCreateMandatoryPhotos(Integer.valueOf(hfSeasonId), Integer.valueOf(loginId));
   }
   
   @GET
   @Path("workQueueType/{roleType}")
   @Produces("application/json")
   public HFP2WorkQueueType getWorkQueueType(@PathParam("roleType") String roleType) {
      LOGGER.debug("fun : getWorkQueueType []  roleType : " + roleType);
      return hfApplication.getWorkQueueType(roleType);
   }

   @GET
   @Path("workQueueCategory/{workQueueTypeId}/{loginId}")
   @Produces("application/json")
   public HFP2WorkQueueCategory getWorkQueueCategory(@PathParam("workQueueTypeId") String workQueueTypeId, @PathParam("loginId") String loginId) {
      LOGGER.debug("fun : getWorkQueueCategory [] type id: " + workQueueTypeId + " loginId: " + loginId);
      return hfApplication.getWorkQueueCategory(Integer.parseInt(workQueueTypeId), Integer.parseInt(loginId));
   }

}
