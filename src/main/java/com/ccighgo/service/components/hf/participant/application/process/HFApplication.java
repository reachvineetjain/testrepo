/**
 * 
 */
package com.ccighgo.service.components.hf.participant.application.process;

import org.springframework.stereotype.Service;

import com.ccighgo.service.components.hf.participant.application.process.util.ChangeHostFamilyProfilePicParam;
import com.ccighgo.service.components.hf.participant.application.process.util.FamilyBasicsPageParam;
import com.ccighgo.service.components.hf.participant.application.process.util.FamilyStylePageParam;
import com.ccighgo.service.components.hf.participant.application.process.util.HFAirportList;
import com.ccighgo.service.components.hf.participant.application.process.util.HFCommunityAndSchoolPageParam;
import com.ccighgo.service.components.hf.participant.application.process.util.HFHomeDescriptionPageParam;
import com.ccighgo.service.components.hf.participant.application.process.util.HFPetsList;
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
   public WhyHost getWhyHost(String hfSeasonId, String applicationCategoryId);

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
    * 
    * @param goId
    * @param loginId
    * @return
    */
   public HFHomePage getHostFamilyHome(HomePageParam hpp);

   /**
    * persist family basic data
    * 
    * @param hfApplicationFamilyDetails
    * @return
    */
   public HFApplicationFamilyDetails saveFamilyBasicData(HFApplicationFamilyDetails hfApplicationFamilyDetails);

   /**
    * persist family details including life style
    * 
    * @param hfApplicationFamilyDetails
    * @return
    */
   public HFApplicationFamilyLifeStyle saveFamilyLifeStyleData(HFApplicationFamilyLifeStyle hfApplicationFamilyDetails);

   /**
    * fetch family style data
    * 
    * @param familyStylePageParam
    * @return
    */
   public HFApplicationFamilyLifeStyle fetchFamilyLifeStyle(FamilyStylePageParam familyStylePageParam);

   /**
    * 
    * @param familyBasicsPageParam
    * @return
    */
   public HFApplicationFamilyDetails fetchBasicData(FamilyBasicsPageParam familyBasicsPageParam);

   /**
    * @param descriptionPage
    * @return
    */
   public HFHomeDescriptionPage saveHFHouseDescription(HFHomeDescriptionPage descriptionPage);

   /**
    * @param descriptionPageParam
    * @return
    */
   public HFHomeDescriptionPage fetchHFHouseDescription(HFHomeDescriptionPageParam descriptionPageParam);

   /**
    * @param communityAndSchoolPage
    * @return
    */
   public WSDefaultResponse saveHFCoummnityAndSchool(HFCommunityAndSchoolPage communityAndSchoolPage);

   /**
    * @param descriptionPageParam
    * @return
    */
   public HFCommunityAndSchoolPage fetchHFCoummnityAndSchool(HFCommunityAndSchoolPageParam descriptionPageParam);

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

   /**
    * @param goId
    * @param picUrl2
    * @return
    */
   public WSDefaultResponse changeProfilePicture(ChangeHostFamilyProfilePicParam param);

   /**
    * @param hfSeasonId
    * @return
    */
   public HFBackgroundCheck getHFBackgroundDetails(String hfSeasonId);

   /**
    * @param hfSeasonId
    * @return
    */
   public HFApplicationProgress getHFApplicationProgress(String hfSeasonId);

   /**
    * @param hfSeasonId
    * @return
    */
   public Response submitApplication(HFSubmitApplication application);

   /**
    * 
    * @param goId
    * @return
    */
   public HFAirportList hfAirportList();

   /**
    * @param hfAirportId
    * @return
    */
   public WSDefaultResponse removeHostFamilyAirport(int hfAirportId);

   /**
    * @param hfPetId
    * @return
    */
   public WSDefaultResponse removeHostFamilyPet(int hfPetId);

   /**
    * @param hfAdultId
    * @return
    */
   public WSDefaultResponse removeHostFamilyAdult(int hfAdultId);

   /**
    * @param parseInt
    * @return
    */
   public HFProfile viewHFProfile(int hfSeasonId, int loginId);
   
   /**
    * @param seasonId
    * @return
    */
   public HFFamilyMember getHFMembers(Integer seasonId);
   
   /**
    * @param hostfamilySeasonId
    * @return
    */
   public HostFamilyMembers getHFDetails(Integer hostfamilySeasonId);

}
