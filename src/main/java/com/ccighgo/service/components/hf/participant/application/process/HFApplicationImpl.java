/**
 * 
 */
package com.ccighgo.service.components.hf.participant.application.process;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.Airport;
import com.ccighgo.db.entities.HostFamily;
import com.ccighgo.db.entities.HostFamilyAirport;
import com.ccighgo.db.entities.HostFamilyBackground;
import com.ccighgo.db.entities.HostFamilyCommunity;
import com.ccighgo.db.entities.HostFamilyDetail;
import com.ccighgo.db.entities.HostFamilyGeneralQuestion;
import com.ccighgo.db.entities.HostFamilyHome;
import com.ccighgo.db.entities.HostFamilyMember;
import com.ccighgo.db.entities.HostFamilyPet;
import com.ccighgo.db.entities.HostFamilyPetType;
import com.ccighgo.db.entities.HostFamilyPhoto;
import com.ccighgo.db.entities.HostFamilyPhotosType;
import com.ccighgo.db.entities.HostFamilyPotentialReference;
import com.ccighgo.db.entities.HostFamilyReference;
import com.ccighgo.db.entities.HostFamilySeason;
import com.ccighgo.db.entities.HostFamilySeasonCategory;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LookupGender;
import com.ccighgo.db.entities.LookupUSState;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.exception.HostFamilyCodes;
import com.ccighgo.jpa.repositories.AirportRepository;
import com.ccighgo.jpa.repositories.GenderRepository;
import com.ccighgo.jpa.repositories.HostFamilyAirportRepository;
import com.ccighgo.jpa.repositories.HostFamilyAnnouncementRepository;
import com.ccighgo.jpa.repositories.HostFamilyAnnouncementResultRepository;
import com.ccighgo.jpa.repositories.HostFamilyBackgroundRepository;
import com.ccighgo.jpa.repositories.HostFamilyCommunityRepository;
import com.ccighgo.jpa.repositories.HostFamilyDetailRepository;
import com.ccighgo.jpa.repositories.HostFamilyGeneralQuestionRepository;
import com.ccighgo.jpa.repositories.HostFamilyHomeRepository;
import com.ccighgo.jpa.repositories.HostFamilyMemberRepository;
import com.ccighgo.jpa.repositories.HostFamilyPetRepository;
import com.ccighgo.jpa.repositories.HostFamilyPetTypeRepository;
import com.ccighgo.jpa.repositories.HostFamilyPhotosRepository;
import com.ccighgo.jpa.repositories.HostFamilyPhotosTypeRepository;
import com.ccighgo.jpa.repositories.HostFamilyPotentialReferenceRepository;
import com.ccighgo.jpa.repositories.HostFamilyReferenceRepository;
import com.ccighgo.jpa.repositories.HostFamilyRepository;
import com.ccighgo.jpa.repositories.HostFamilySeasonCategoryRepository;
import com.ccighgo.jpa.repositories.HostFamilySeasonRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.StateRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.component.emailing.EmailServiceImpl;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.HostFamilyMessageConstants;
import com.ccighgo.service.components.hf.participant.application.process.util.ChangeHostFamilyProfilePicParam;
import com.ccighgo.service.components.hf.participant.application.process.util.FamilyBasicsPageParam;
import com.ccighgo.service.components.hf.participant.application.process.util.FamilyStylePageParam;
import com.ccighgo.service.components.hf.participant.application.process.util.HFAirportData;
import com.ccighgo.service.components.hf.participant.application.process.util.HFAirportList;
import com.ccighgo.service.components.hf.participant.application.process.util.HFCommunityAndSchoolPageParam;
import com.ccighgo.service.components.hf.participant.application.process.util.HFHomeDescriptionPageParam;
import com.ccighgo.service.components.hf.participant.application.process.util.HFSeasonDetails;
import com.ccighgo.service.components.hf.participant.application.process.util.HFSeasonList;
import com.ccighgo.service.components.hf.participant.application.process.util.HomePageParam;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.hostfamily.beans.application.background.check.HFBackgroundCheck;
import com.ccighgo.service.transport.hostfamily.beans.application.background.check.Member;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFAdultDetails;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFAirport;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFApplicationFamilyDetails;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFContactInfo;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFMailingAddress;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFPets;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFPhysicalAddress;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFSingleHostDetails;
import com.ccighgo.service.transport.hostfamily.beans.application.familylifestyle.HFApplicationFamilyLifeStyle;
import com.ccighgo.service.transport.hostfamily.beans.application.familylifestyle.HFDieTrayRestriction;
import com.ccighgo.service.transport.hostfamily.beans.application.familylifestyle.HFFamilyDayDetails;
import com.ccighgo.service.transport.hostfamily.beans.application.familylifestyle.HFFamilyReligious;
import com.ccighgo.service.transport.hostfamily.beans.application.familylifestyle.HFFinancialResource;
import com.ccighgo.service.transport.hostfamily.beans.application.familylifestyle.HFMiscLifeStyle;
import com.ccighgo.service.transport.hostfamily.beans.application.familymember.HFFamilyMember;
import com.ccighgo.service.transport.hostfamily.beans.application.familymember.HFFamilyMemberDetails;
import com.ccighgo.service.transport.hostfamily.beans.application.familymembers.HostFamilyMemberDetails;
import com.ccighgo.service.transport.hostfamily.beans.application.familymembers.HostFamilyMembers;
import com.ccighgo.service.transport.hostfamily.beans.application.hfcommunityandschoolpage.HFCommunity;
import com.ccighgo.service.transport.hostfamily.beans.application.hfcommunityandschoolpage.HFCommunityAndSchoolPage;
import com.ccighgo.service.transport.hostfamily.beans.application.hfcommunityandschoolpage.HFSchoolLife;
import com.ccighgo.service.transport.hostfamily.beans.application.hfhousedescriptionpage.HFHomeDescription;
import com.ccighgo.service.transport.hostfamily.beans.application.hfhousedescriptionpage.HFHomeDescriptionPage;
import com.ccighgo.service.transport.hostfamily.beans.application.homepage.HFApplicationCheckList;
import com.ccighgo.service.transport.hostfamily.beans.application.homepage.HFApplicationCheckListStages;
import com.ccighgo.service.transport.hostfamily.beans.application.homepage.HFHomePage;
import com.ccighgo.service.transport.hostfamily.beans.application.pettype.HFPetType;
import com.ccighgo.service.transport.hostfamily.beans.application.pettype.PetDetails;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.HFApplicationUploadPhotos;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.Photo;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.PhotoType;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.Photos;
import com.ccighgo.service.transport.hostfamily.beans.application.potential.hostfamily.PotentialHostFamily;
import com.ccighgo.service.transport.hostfamily.beans.application.profile.HFProfile;
import com.ccighgo.service.transport.hostfamily.beans.application.profile.HFState;
import com.ccighgo.service.transport.hostfamily.beans.application.profile.landingpage.HFLandingPage;
import com.ccighgo.service.transport.hostfamily.beans.application.profile.update.password.UpdatedPassword;
import com.ccighgo.service.transport.hostfamily.beans.application.progress.HFApplicationProgress;
import com.ccighgo.service.transport.hostfamily.beans.application.progress.Progress;
import com.ccighgo.service.transport.hostfamily.beans.application.references.HostFamilyReferences;
import com.ccighgo.service.transport.hostfamily.beans.application.references.Reference;
import com.ccighgo.service.transport.hostfamily.beans.application.submit.HFSubmitApplication;
import com.ccighgo.service.transport.hostfamily.beans.application.whyhost.WhyHost;
import com.ccighgo.service.transport.participant.beans.hfparticipantlist.HFParticipantDetail;
import com.ccighgo.service.transport.participant.beans.hfparticipantlist.HFParticipantList;
import com.ccighgo.service.transport.participant.beans.hfparticipantlist.ParticipantDetails;
import com.ccighgo.service.transport.partner.beans.hfFieldNetworkInformation.HFFieldNetworkInformationDetail;
import com.ccighgo.service.transport.partner.beans.hfFieldNetworkInformation.HFFieldStaffForFieldNetworkInformation;
import com.ccighgo.service.transport.partner.beans.hfHostAgainQuestion.HFHostAgainQuestionDetail;
import com.ccighgo.service.transport.partner.beans.hfp2workqueuecategory.HFP2WorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.hfp2workqueuetype.HFP2WorkQueueType;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.CCIUtils;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.PasswordUtil;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * @author ravi
 *
 */
@Component
public class HFApplicationImpl implements HFApplication {

   private static final Logger LOGGER = LoggerFactory.getLogger(HFApplicationImpl.class);

   @Autowired EntityManager entityManager;
   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;

   @Autowired LoginRepository loginRepository;
   @Autowired HostFamilyHomeRepository hostFamilyHomeRepository;
   @Autowired HostFamilySeasonRepository hostFamilySeasonRepository;
   @Autowired HostFamilySeasonCategoryRepository hostFamilySeasonCategoryRepository;
   @Autowired HostFamilyPhotosRepository hostFamilyPhotosRepository;
   @Autowired HostFamilyPhotosTypeRepository hostFamilyPhotosTypeRepository;
   @Autowired HostFamilyAnnouncementRepository hfAnnouncementRepository;
   @Autowired HostFamilyAnnouncementResultRepository hfAnnouncementResultRepository;
   @Autowired HostFamilyMemberRepository hfMemberRepository;
   @Autowired HostFamilyPhotosRepository hfPhotosRepository;
   @Autowired HostFamilySeasonCategoryRepository hfSeasonCategoryRepository;
   @Autowired GenderRepository genderRepository;
   @Autowired StateRepository stateRepository;
   @Autowired HostFamilyRepository hostFamilyRepository;
   @Autowired AirportRepository airportRepository;
   @Autowired HostFamilyPetTypeRepository hostFamilyPetTypeRepository;
   @Autowired HostFamilyPetRepository hostFamilyPetRepository;
   @Autowired HostFamilyAirportRepository hostFamilyAirportRepository;

   @Autowired HostFamilyDetailRepository hostFamilyDetailRepository;
   @Autowired HostFamilyCommunityRepository hostFamilyCommunityRepository;
   @Autowired UserTypeRepository userTypeRepository;
   @Autowired HostFamilyPotentialReferenceRepository hostFamilyPotentialReferenceRepository;
   @Autowired HostFamilyGeneralQuestionRepository hostFamilyGeneralQuestionRepository;
   @Autowired HostFamilyReferenceRepository hostFamilyReferenceRepository;
   @Autowired HostFamilyBackgroundRepository hostFamilyBackgroundRepository;
   @Autowired EmailServiceImpl emailService;

   @Autowired EntityManager em;

   private static final String SP_HF_FAMILY_BASIC_DATA = "CALL SPHostFamilyApplicationFamilyBasics(?,?,?) ";
   private static final String SP_HF_FAMILY_BASIC_CONTACT_INFORMATION = "CALL SPHostFamilyApplicationContactInformation(?,?,?)";
   private static final String SP_HF_FAMILY_BASIC_AIRPORTS = "CALL SPHostFamilyApplicationAirport(?,?,?) ";
   private static final String SP_HF_FAMILY_BASIC_PETS = "CALL SPHostFamilyApplicationPets(?,?,?) ";

   private static final String SP_HF_LIFE_STYLE = "CALL SPHostFamilyApplicationLifeStyle(?,?,?)";
   private static final String SP_HF_APPLICATION_CHECKLIST = "CALL SPHostFamilyApplicationChecklist(?,?,?)";
   private static final String SP_HF_HOME = "CALL SPHostFamilyApplicationHome(?,?,?)";
   private static final String SP_HF_COMMUNITY = "CALL SPHostFamilyApplicationCommunity(?,?,?)";
   private static final String SP_HF_SCHOOL_LIFE = "CALL SPHostFamilyApplicationSchoolLife (?,?,?)";
   private static final String SP_HF_SEASON_LIST = "CALL SPHostFamilySeasonList (?)";
   private static final String SP_HF_PARTICIPANT_LIST = "CALL SPHostFamilyParticipantList (?,?)";

   private static final String COMPLETED = "Completed";
   private static final String NOT_COMPLETED = "Not Completed";

   @Override
   @Transactional
   public WhyHost createWhyHost(String applicationCategoryId, WhyHost whyHost) {
      WhyHost updatedObject = new WhyHost();
      try {
         if (whyHost == null) {
            throw new CcighgoException("cannot create empty record");
         }
         if (whyHost.getFieldsFilled() == 0 || whyHost.getFieldsFilled() < 0) {
            throw new CcighgoException("number of fields filled is mandatory to create the record");
         }
         HostFamilyHome hfHomeExists = hostFamilyHomeRepository.getHFHomebyIdAndSeasonId(whyHost.getHostfamilySeasonId());
         // an unnecessary check because we are super smart
         if (hfHomeExists != null) {
            throw new CcighgoException("Record(s) already exist with specified season");
         } else {
            HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(whyHost.getHostfamilySeasonId(),
                  Integer.valueOf(applicationCategoryId));
            hostFamilySeasonCategory.setFilledMandatoryFields(whyHost.getFieldsFilled());
            hostFamilySeasonCategoryRepository.saveAndFlush(hostFamilySeasonCategory);
            HostFamilyHome hfHome = new HostFamilyHome();
            hfHome.setHostingReason(whyHost.getWhyFamilyInterestedInHosting());
            hfHome.setHopeToLearn(whyHost.getHopingToLearn());
            hfHome.setExtraActivities(whyHost.getAspectsOfAmericanCultureYouWillShare());
            hfHome.setLocalCoordinatorOther(whyHost.isWillYouBeWorkingasLCForAnotherOrg() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            hfHome.setLocalCoordinatorDetails(whyHost.getForWhomYouWillBeWorkingasLCForAnotherOrg());
            hfHome.setHostedOther(whyHost.isHaveYouHostedForAnotherOrg() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            hfHome.setHostedOtherDetails(whyHost.getIfYesForWhomAndHowManyYears());
            hfHome.setStudentsResponsibilities(whyHost.getFamilyExpectationOnStudentResponsibility());
            hfHome.setHostFamilySeason(hostFamilySeasonRepository.findOne(whyHost.getHostfamilySeasonId()));
            hfHome.setCreatedBy(whyHost.getLoginId());
            hfHome.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            hfHome.setModifiedBy(whyHost.getLoginId());
            hfHome.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            hfHome = hostFamilyHomeRepository.saveAndFlush(hfHome);
            updatedObject = getWhyHost(String.valueOf(whyHost.getHostfamilySeasonId()), applicationCategoryId);
            updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (CcighgoException e) {
         updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_CREATE_WHYHOST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return updatedObject;
   }

   @Override
   @Transactional(readOnly = true)
   public WhyHost getWhyHost(String hfSeasonId, String applicationCategoryId) {
      WhyHost whyHost = new WhyHost();
      try {
         if (hfSeasonId == null || applicationCategoryId == null) {
            throw new CcighgoException("invalid search parameters");
         }
         HostFamilyHome hfHome = hostFamilyHomeRepository.getHFHomebyIdAndSeasonId(Integer.valueOf(hfSeasonId));
         HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(Integer.valueOf(hfSeasonId),
               Integer.valueOf(applicationCategoryId));
         if (hfHome != null && hostFamilySeasonCategory != null) {
            whyHost.setGoId(hfHome.getHostFamilySeason().getHostFamily().getHostFamilyGoId());
            whyHost.setHostfamilySeasonId(hfHome.getHostFamilySeason().getHostFamilySeasonId());
            whyHost.setHostFamilyHomeId(hfHome.getHostFamilyHomeId());
            whyHost.setWhyFamilyInterestedInHosting(hfHome.getHostingReason());
            whyHost.setHopingToLearn(hfHome.getHopeToLearn());
            whyHost.setAspectsOfAmericanCultureYouWillShare(hfHome.getExtraActivities());
            whyHost.setWillYouBeWorkingasLCForAnotherOrg(hfHome.getLocalCoordinatorOther().equals(CCIConstants.ACTIVE) ? true : false);
            whyHost.setForWhomYouWillBeWorkingasLCForAnotherOrg(hfHome.getLocalCoordinatorDetails());
            whyHost.setHaveYouHostedForAnotherOrg(hfHome.getHostedOther().equals(CCIConstants.ACTIVE) ? true : false);
            whyHost.setIfYesForWhomAndHowManyYears(hfHome.getHostedOtherDetails());
            whyHost.setFamilyExpectationOnStudentResponsibility(hfHome.getStudentsResponsibilities());
            whyHost.setPercentUpdate(CCIUtils.getFormFilledPercentage(hostFamilySeasonCategory.getTotalMandatoryFields(), hostFamilySeasonCategory.getFilledMandatoryFields()));
            whyHost.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            whyHost.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         whyHost.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_GET_WHYHOST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return whyHost;
   }

   @Override
   @Transactional
   public WhyHost updateWhyHost(String applicationCategoryId, WhyHost whyHost) {
      WhyHost updatedObject = new WhyHost();
      try {
         if (applicationCategoryId == null) {
            throw new CcighgoException("invalid parameters");
         }
         if (whyHost == null) {
            throw new CcighgoException("cannot create update record");
         }
         if (whyHost.getFieldsFilled() == 0 || whyHost.getFieldsFilled() < 0) {
            throw new CcighgoException("number of fields filled is mandatory to update the record");
         }
         HostFamilyHome hfHome = hostFamilyHomeRepository.findOne(whyHost.getHostFamilyHomeId());
         if (hfHome != null) {
            HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(whyHost.getHostfamilySeasonId(),
                  Integer.valueOf(applicationCategoryId));
            hostFamilySeasonCategory.setFilledMandatoryFields(whyHost.getFieldsFilled());
            hostFamilySeasonCategoryRepository.saveAndFlush(hostFamilySeasonCategory);
            hfHome.setHostingReason(whyHost.getWhyFamilyInterestedInHosting());
            hfHome.setHopeToLearn(whyHost.getHopingToLearn());
            hfHome.setExtraActivities(whyHost.getAspectsOfAmericanCultureYouWillShare());
            hfHome.setLocalCoordinatorOther(whyHost.isWillYouBeWorkingasLCForAnotherOrg() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            hfHome.setLocalCoordinatorDetails(whyHost.getForWhomYouWillBeWorkingasLCForAnotherOrg());
            hfHome.setHostedOther(whyHost.isHaveYouHostedForAnotherOrg() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            hfHome.setHostedOtherDetails(whyHost.getIfYesForWhomAndHowManyYears());
            hfHome.setStudentsResponsibilities(whyHost.getFamilyExpectationOnStudentResponsibility());
            hfHome.setModifiedBy(whyHost.getLoginId());
            hfHome.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            hfHome = hostFamilyHomeRepository.saveAndFlush(hfHome);
            updatedObject = getWhyHost(String.valueOf(whyHost.getHostfamilySeasonId()), applicationCategoryId);
            updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_UPDATE_WHYHOST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return updatedObject;
   }

   @Override
   @Transactional
   public HFApplicationUploadPhotos hfCreateMandatoryPhotos(Integer hfSeasonId, Integer loginId) {
      HFApplicationUploadPhotos uploadObject = new HFApplicationUploadPhotos();
      try {
         if (hfSeasonId == null || hfSeasonId == 0) {
            throw new CcighgoException("invalid host family season");
         }
         if (loginId == null || loginId == 0) {
            throw new CcighgoException("invalid login details");
         }
         List<HostFamilyPhoto> uploadList = new ArrayList<HostFamilyPhoto>();
         // id 2 - 6 are mandatory photos
         for (int i = 2; i <= 6; i++) {
            HostFamilyPhoto hfPhoto = new HostFamilyPhoto();
            hfPhoto.setHostFamilySeason(hostFamilySeasonRepository.findOne(hfSeasonId));
            HostFamilyPhotosType type = hostFamilyPhotosTypeRepository.findOne(i);
            hfPhoto.setHostFamilyPhotosType(type);
            hfPhoto.setFileName("");
            hfPhoto.setFilePath("");
            hfPhoto.setPhotoName("");
            hfPhoto.setDescription("");
            hfPhoto.setTitle(type.getHostFamilyPhotoTypeName());
            hfPhoto.setIsOptional(CCIConstants.INACTIVE);
            hfPhoto.setCreatedBy(loginId);
            hfPhoto.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            hfPhoto.setModifiedBy(loginId);
            hfPhoto.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            hfPhoto.setActive(CCIConstants.ACTIVE);
            hfPhoto.setSubmittedToCCI(CCIConstants.INACTIVE);
            hfPhoto.setApprovedByCCI(CCIConstants.INACTIVE);
            hfPhoto.setRejectedByCCI(CCIConstants.INACTIVE);
            uploadList.add(hfPhoto);
         }
         hostFamilyPhotosRepository.save(uploadList);
         hostFamilyPhotosRepository.flush();
         uploadObject = getHFPhotos(String.valueOf(hfSeasonId));
         uploadObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         uploadObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_HF_DETAILS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return uploadObject;
   }

   @Override
   public HFApplicationUploadPhotos uploadHFMandatoryPhotos(HFApplicationUploadPhotos hfApplicationUploadPhotos) {
      HFApplicationUploadPhotos updatedObject = new HFApplicationUploadPhotos();
      try {
         if (hfApplicationUploadPhotos == null) {
            throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_HF_UPDATE_PHOTO_OBJECT));
         }
         if (hfApplicationUploadPhotos.getLoginId() == 0 || hfApplicationUploadPhotos.getLoginId() < 0) {
            throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_OR_NULL_LOGIN_ID));
         }
         if (hfApplicationUploadPhotos.getHfSeasonId() == 0 || hfApplicationUploadPhotos.getHfSeasonId() < 0) {
            throw new CcighgoException("season information is required to save photos");
         }
         List<HostFamilyPhoto> hostFamilyPhotoList = hostFamilyPhotosRepository.findPhotosBySeasonId(hfApplicationUploadPhotos.getHfSeasonId());
         if (hfApplicationUploadPhotos.getPhotos() != null) {
            for (Photo ph : hfApplicationUploadPhotos.getPhotos().getPhotos()) {
               for (HostFamilyPhoto hfp : hostFamilyPhotoList) {
                  if (ph.getPhotoId() == hfp.getHostFamilyPhotoId()) {
                     hfp.setFileName(ph.getName());
                     hfp.setFilePath(ph.getPhotoUrl());
                     hfp.setPhotoName(ph.getName());
                     hfp.setDescription(ph.getDescription() != null ? ph.getDescription() : "");
                     hfp.setModifiedBy(hfApplicationUploadPhotos.getLoginId());
                     hfp.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                     hostFamilyPhotosRepository.saveAndFlush(hfp);
                  }
               }
            }
         }
         updatedObject = getHFPhotos(String.valueOf(hfApplicationUploadPhotos.getHfSeasonId()));
         updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_UPLOAD_HF_PHOTOS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return updatedObject;
   }

   @Override
   @Transactional
   public HFApplicationUploadPhotos uploadOptionalHFPhotos(HFApplicationUploadPhotos hfApplicationUploadPhotos) {
      HFApplicationUploadPhotos updatedObject = new HFApplicationUploadPhotos();
      try {
         if (hfApplicationUploadPhotos == null) {
            throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_HF_UPDATE_PHOTO_OBJECT));
         }
         if (hfApplicationUploadPhotos.getLoginId() == 0 || hfApplicationUploadPhotos.getLoginId() < 0) {
            throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_OR_NULL_LOGIN_ID));
         }
         if (hfApplicationUploadPhotos.getHfSeasonId() == 0 || hfApplicationUploadPhotos.getHfSeasonId() < 0) {
            throw new CcighgoException("season information is required to save photos");
         }
         List<HostFamilyPhoto> hostFamilyPhotoList = new ArrayList<HostFamilyPhoto>();
         if (hfApplicationUploadPhotos.getOptionalPhotos() != null) {
            for (Photo ph : hfApplicationUploadPhotos.getOptionalPhotos().getPhotos()) {
               HostFamilyPhoto hfPhoto = new HostFamilyPhoto();
               hfPhoto.setHostFamilySeason(hostFamilySeasonRepository.findOne(hfApplicationUploadPhotos.getHfSeasonId()));
               hfPhoto.setHostFamilyPhotosType(hostFamilyPhotosTypeRepository.findOne(ph.getType().getTypeId()));
               hfPhoto.setFileName(ph.getName());
               hfPhoto.setFilePath(ph.getPhotoUrl());
               hfPhoto.setPhotoName(ph.getName());
               hfPhoto.setDescription(ph.getDescription() != null ? ph.getDescription() : "");
               hfPhoto.setIsOptional(CCIConstants.ACTIVE);
               hfPhoto.setCreatedBy(hfApplicationUploadPhotos.getLoginId());
               hfPhoto.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               hfPhoto.setModifiedBy(hfApplicationUploadPhotos.getLoginId());
               hfPhoto.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               hfPhoto.setActive(CCIConstants.ACTIVE);
               hfPhoto.setSubmittedToCCI(CCIConstants.INACTIVE);
               hfPhoto.setApprovedByCCI(CCIConstants.INACTIVE);
               hfPhoto.setRejectedByCCI(CCIConstants.INACTIVE);
               hostFamilyPhotoList.add(hfPhoto);
            }
         }
         hostFamilyPhotosRepository.save(hostFamilyPhotoList);
         hostFamilyPhotosRepository.flush();
         updatedObject = getHFPhotos(String.valueOf(hfApplicationUploadPhotos.getHfSeasonId()));
         updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         updatedObject
               .setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_UPLOAD_OPTIONAL_HF_PHOTOS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return updatedObject;

   }

   @Override
   public HFApplicationUploadPhotos getHFPhotos(String hfSeasonId) {
      HFApplicationUploadPhotos photoList = new HFApplicationUploadPhotos();
      try {
         if (hfSeasonId == null) {
            throw new CcighgoException("invalid search parameters");
         }
         List<HostFamilyPhoto> hfPhotos = hostFamilyPhotosRepository.findPhotosBySeasonId(Integer.valueOf(hfSeasonId));
         if (hfPhotos != null) {
            // photoList.setPercentUpdate(value);
            List<Photo> familyPhotos = new ArrayList<Photo>();
            List<Photo> optionalPhotoList = new ArrayList<Photo>();
            Photos photos = new Photos();
            Photos optionalPhotos = new Photos();
            int phCount = 0;
            int ophCount = 0;
            for (HostFamilyPhoto ph : hfPhotos) {
               if (ph.getIsOptional().equals(CCIConstants.ACTIVE)) {
                  Photo op = new Photo();
                  op.setPhotoId(ph.getHostFamilyPhotoId());
                  op.setName(ph.getPhotoName());
                  op.setDescription(ph.getDescription() != null ? ph.getDescription() : "");
                  op.setPhotoUrl(ph.getFilePath() != null ? ph.getFilePath() : "");
                  op.setTitle(ph.getTitle());
                  PhotoType type = new PhotoType();
                  type.setTypeId(ph.getHostFamilyPhotosType().getHostFamilyPhotoTypeId());
                  type.setType(ph.getHostFamilyPhotosType().getHostFamilyPhotoTypeName());
                  op.setType(type);
                  op.setOptional(true);
                  ophCount += 1;
                  optionalPhotoList.add(op);
               } else {
                  Photo p = new Photo();
                  p.setPhotoId(ph.getHostFamilyPhotoId());
                  p.setName(ph.getPhotoName());
                  p.setTitle(ph.getTitle());
                  p.setDescription(ph.getDescription() != null ? ph.getDescription() : "");
                  p.setPhotoUrl(ph.getFilePath() != null ? ph.getFilePath() : "");
                  PhotoType type = new PhotoType();
                  type.setTypeId(ph.getHostFamilyPhotosType().getHostFamilyPhotoTypeId());
                  type.setType(ph.getHostFamilyPhotosType().getHostFamilyPhotoTypeName());
                  p.setType(type);
                  p.setOptional(false);
                  phCount += 1;
                  familyPhotos.add(p);
               }
            }
            photos.getPhotos().addAll(familyPhotos);
            photos.setCount(phCount);
            optionalPhotos.getPhotos().addAll(optionalPhotoList);
            optionalPhotos.setCount(ophCount);
            photoList.setHfSeasonId(Integer.valueOf(hfSeasonId));
            photoList.setPhotos(photos);
            photoList.setOptionalPhotos(optionalPhotos);
            photoList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            photoList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         photoList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_GET_HF_PHOTOS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return photoList;
   }

   @Override
   @Transactional
   @Modifying
   public Response deletePhoto(Integer photoId, Integer optional, Integer loginId) {
      Response resp = new Response();
      try {
         if (photoId == null || optional == null || loginId == null) {
            throw new CcighgoException("invalid parameters, cannot delete photo");
         }
         if (optional == 1) {
            hostFamilyPhotosRepository.delete(Integer.valueOf(photoId));
         }
         if (optional == 0) {
            HostFamilyPhoto photo = hostFamilyPhotosRepository.findOne(photoId);
            photo.setFileName("");
            photo.setFilePath("");
            photo.setPhotoName("");
            photo.setDescription("");
            photo.setModifiedBy(loginId);
            photo.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            hostFamilyPhotosRepository.saveAndFlush(photo);
         } else {
            throw new CcighgoException("optional type is required to delete");
         }
         resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_DELETE_HF_PHOTO.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return resp;
   }

   @Override
   public HFHomePage getHostFamilyHome(HomePageParam hpp) {
      HFHomePage hp = new HFHomePage();
      try {
         if (hpp.getHostFamilyId() == 0 || hpp.getHostFamilyId() < 0) {
            throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_HF_GOID));
         }
         // TODO Announcement

         HFApplicationCheckList applicationChecklist = new HFApplicationCheckList();

         Query query = em.createNativeQuery(SP_HF_APPLICATION_CHECKLIST);
         query.setParameter(1, hpp.getHostFamilyId() == 0 ? null : hpp.getHostFamilyId());
         query.setParameter(2, hpp.getSeasonId() == 0 ? null : hpp.getSeasonId());
         query.setParameter(3, hpp.getDepartmentProgramId() == 0 ? null : hpp.getDepartmentProgramId());
         @SuppressWarnings("unchecked")
         List<Object[]> result = query.getResultList();
         if (result != null && !result.isEmpty()) {
            int size = 0, completedCategoriesCount = 0;
            for (Object[] obj : result) {
               HFApplicationCheckListStages stage = new HFApplicationCheckListStages();
               String hostFamilyName = String.valueOf(obj[0]);
               applicationChecklist.setApplicantName(hostFamilyName.equalsIgnoreCase("null") ? "" : hostFamilyName);
               stage.setCategory(String.valueOf(obj[1]));
               stage.setStatus(String.valueOf(obj[2]));
               if (stage.getStatus().equalsIgnoreCase("true"))
                  completedCategoriesCount++;
               applicationChecklist.setPhotoUrl(String.valueOf(obj[3]));
               hp.setHostfamilySeasonId(Integer.valueOf(String.valueOf(obj[4])));
               hp.setSeasonId(Integer.valueOf(String.valueOf(obj[5])));
               hp.setDepartmentProgramId(Integer.valueOf(String.valueOf(obj[6])));
               hp.setHostFamilyGoId(Integer.valueOf(String.valueOf(obj[7])));
               stage.setHostFamilyApplicationCategoriesId(Integer.valueOf(String.valueOf(obj[8])));
               applicationChecklist.getStages().add(stage);
               size++;
            }
            if (size != 0 && completedCategoriesCount != 0)
               applicationChecklist.setPercentage(((completedCategoriesCount * 100) / size) + "");
            else
               applicationChecklist.setPercentage(0 + "");

            hp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            hp.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD, CCIConstants.TYPE_INFO, HostFamilyCodes.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }

         hp.setApplicationCheckList(applicationChecklist);

      } catch (CcighgoException e) {
         hp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_GET_HF_HOME_PAGE.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hp;
   }

   @Override
   public HFApplicationFamilyLifeStyle fetchFamilyLifeStyle(FamilyStylePageParam familyStylePageParam) {
      HFApplicationFamilyLifeStyle hfl = new HFApplicationFamilyLifeStyle();
      try {
         HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(
               Integer.valueOf(familyStylePageParam.getSeasonId()), familyStylePageParam.getApplicationCategoryId());

         Query query = em.createNativeQuery(SP_HF_LIFE_STYLE);
         query.setParameter(1, familyStylePageParam.getHostFamilyId() == 0 ? null : familyStylePageParam.getHostFamilyId());
         query.setParameter(2, familyStylePageParam.getSeasonId() == 0 ? null : familyStylePageParam.getSeasonId());
         query.setParameter(3, familyStylePageParam.getDeptProgramId() == 0 ? null : familyStylePageParam.getDeptProgramId());
         @SuppressWarnings("unchecked")
         List<Object[]> result = query.getResultList();
         if (result != null && !result.isEmpty()) {
            for (Object[] obj : result) {
               hfl.setFamilyDescription(String.valueOf(obj[0]));
               hfl.setAnyOneHasSeriousIllness((boolean) obj[1]);
               hfl.setIllnessExplanation(String.valueOf(obj[2]));
               hfl.setAnyOneHaveDisability((boolean) obj[3]);
               hfl.setDisabilityExplanation(String.valueOf(obj[4]));
               hfl.setThingsStudentNeedsToAdaptWith(String.valueOf(obj[5]));

               HFFamilyDayDetails familyDay = new HFFamilyDayDetails();
               familyDay.setHousehold(String.valueOf(obj[6]));
               familyDay.setTypicalWeekdayAtHome(String.valueOf(obj[7]));
               familyDay.setTypicalWeekendAtHome(String.valueOf(obj[8]));
               familyDay.setFavouriteThingsToDoAsFamily(String.valueOf(obj[9]));
               familyDay.setFamilyHomeLanguage(String.valueOf(obj[37]));
               familyDay.setFamilyOtherLanguage(String.valueOf(obj[38]));
               hfl.setFamilyDay(familyDay);

               HFFamilyReligious religious = new HFFamilyReligious();
               religious.setReligious(String.valueOf(obj[10]));
               religious.setExplanation(String.valueOf(obj[11]));
               religious.setOftenAttendReligiousMeetings(String.valueOf(obj[12]));
               religious.setPreferedTheStudentJoinYou(String.valueOf(obj[13]));
               religious.setInviteStudentForReligiousExperience((boolean) obj[14]);
               religious.setDiffecultyHostingPersonWithDifferentReligious((boolean) obj[15]);
               hfl.setReligious(religious);

               HFDieTrayRestriction diet = new HFDieTrayRestriction();
               diet.setProvideStudentWithThreeMeals((boolean) obj[16]);
               diet.setFollowDietrayRestriction((boolean) obj[17]);
               diet.setDietrayRestrictionExplanation(String.valueOf(obj[18]));
               diet.setExpectStudentFollowDietrayRestriction((boolean) obj[19]);
               diet.setStudentFollowDietrayRestrictionExplanation(String.valueOf(obj[20]));
               diet.setHostStudentWhoFollowDietrayRestriction((boolean) obj[21]);
               hfl.setDieTrayRestriction(diet);

               HFMiscLifeStyle m = new HFMiscLifeStyle();
               m.setHaveAutoInsurranceForAllCarsYouHave(String.valueOf(obj[22]));
               m.setAnyOneIsSmokingInyourFamily((boolean) obj[23]);
               m.setWhereSmoking(String.valueOf(obj[24]));
               m.setAnyOneDrinkAlcoholic(String.valueOf(obj[25]));
               m.setAnyOneConvictedInCrime((boolean) obj[26]);
               m.setConvictedInCrimeDesc(String.valueOf(obj[27]));
               m.setAnyOneInProtectiveServiceAgency((boolean) obj[28]);
               m.setChildInProtectiveServiceExplanation(String.valueOf(obj[29]));
               hfl.setMiscLifeStyle(m);

               HFFinancialResource f = new HFFinancialResource();
               f.setTotalHouseHoldIncome(String.valueOf(obj[30]));
               f.setAnyOneReceivePublicAssistant((boolean) obj[31]);
               f.setPublicAssistantExplanation(String.valueOf(obj[32]));
               hfl.setFinancialResources(f);

               hfl.setSeasonId(Integer.valueOf(String.valueOf(obj[33])));
               hfl.setProgramId(Integer.valueOf(String.valueOf(obj[34])));
               hfl.setHostFamilySeasonId(Integer.valueOf(String.valueOf(obj[35])));
               hfl.setHostFamilyDetailsId(Integer.valueOf(String.valueOf(obj[36])));
               if (hostFamilySeasonCategory != null)
                  hfl.setPercentUpdate(CCIUtils.getFormFilledPercentage(hostFamilySeasonCategory.getTotalMandatoryFields(), hostFamilySeasonCategory.getFilledMandatoryFields()));
               break;
            }
            hfl.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            hfl.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD, CCIConstants.TYPE_INFO, HostFamilyCodes.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }

      } catch (CcighgoException e) {
         hfl.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_HF_LIFE_STYLE.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hfl;
   }

   @Override
   public HFApplicationFamilyDetails fetchBasicData(FamilyBasicsPageParam familyBasicsPageParam) {
      HFApplicationFamilyDetails hfbs = new HFApplicationFamilyDetails();
      try {
         HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(
               Integer.valueOf(familyBasicsPageParam.getSeasonId()), familyBasicsPageParam.getApplicationCategoryId());
         if (hostFamilySeasonCategory != null)
            hfbs.setPercentUpdate(CCIUtils.getFormFilledPercentage(hostFamilySeasonCategory.getTotalMandatoryFields(), hostFamilySeasonCategory.getFilledMandatoryFields()));
         Query query = em.createNativeQuery(SP_HF_FAMILY_BASIC_DATA);
         query.setParameter(1, familyBasicsPageParam.getHostfamilyId() == 0 ? null : familyBasicsPageParam.getHostfamilyId());
         query.setParameter(2, familyBasicsPageParam.getSeasonId() == 0 ? null : familyBasicsPageParam.getSeasonId());
         query.setParameter(3, familyBasicsPageParam.getDepartmentProgramId() == 0 ? null : familyBasicsPageParam.getDepartmentProgramId());
         hfbs.setHostFamilyId(familyBasicsPageParam.getHostfamilyId());

         // Fetching The single host Data TODO
         @SuppressWarnings("unchecked")
         List<Object[]> result = query.getResultList();
         boolean singleHost = false;
         if (result != null && !result.isEmpty()) {
            for (Object[] obj : result) {
               try {
                  HFAdultDetails adult = new HFAdultDetails();
                  com.ccighgo.service.transport.hostfamily.beans.application.familydetails.Photo photo = new com.ccighgo.service.transport.hostfamily.beans.application.familydetails.Photo();
                  photo.setFilePath(String.valueOf(obj[0]));
                  // put the null check for PhotoId
                  try {
                     photo.setPhotoId(Integer.valueOf(String.valueOf(obj[25]) != null ? String.valueOf(obj[25]) : ""));
                  } catch (NumberFormatException nfe) {
                     LOGGER.error(nfe.getMessage());
                  }
                  photo.setTypeId(CCIConstants.ACTIVE);
                  photo.setDescription(String.valueOf(obj[30]));
                  hfbs.setPhoto(photo);
                  singleHost |= Boolean.valueOf(String.valueOf(obj[1]));
                  adult.setRelationship(String.valueOf(obj[2]));
                  adult.setFirstName(String.valueOf(obj[3]));
                  adult.setLastName(String.valueOf(obj[4]));
                  adult.setIsHostParent(Boolean.valueOf(String.valueOf(obj[5])));
                  adult.setEmail(String.valueOf(obj[6]));
                  adult.setPersonalPhone(String.valueOf(obj[7]));
                  Date dateformat = (Date) obj[8];
                  adult.setBirthdate(DateUtils.getMMddYyyyString(dateformat));
                  adult.setGenderId(Integer.valueOf(String.valueOf(obj[9])));
                  adult.setEducationLevel(String.valueOf(obj[10]));
                  adult.setResidencyTime(String.valueOf(String.valueOf(obj[11])));
                  adult.setLivingInsideHomeExplanation(String.valueOf(obj[12]));
                  adult.setCommunityInvolvement(String.valueOf(obj[13]));
                  adult.setActivitiesOrInterests(String.valueOf(obj[14]));
                  adult.setEmployed(String.valueOf(obj[15]));
                  adult.setEmploymentType(String.valueOf(obj[31]));
                  adult.setEmployer(String.valueOf(obj[16]));
                  adult.setJobTitle(String.valueOf(obj[17]));
                  adult.setContactName(String.valueOf(obj[18]));
                  adult.setJobPhone(String.valueOf(obj[19]));
                  if (obj[20] != null) {
                     Boolean valueOf = Boolean.valueOf(String.valueOf(obj[20]));
                     if (valueOf) {
                        adult.setHasAnotherJob(valueOf);
                        adult.setOtherEmployer(String.valueOf(obj[21]));
                        adult.setOtherJobTitle(String.valueOf(obj[22]));
                        adult.setOtherContactName(String.valueOf(obj[23]));
                        adult.setOtherJobPhone(String.valueOf(obj[24]));
                     }
                  }
                  adult.setHostfamilyMemberId(Integer.valueOf(String.valueOf(obj[26])));
                  hfbs.getAdults().add(adult);
               } catch (Exception e) {
                  LOGGER.error(e.getMessage());
               }
            }
            hfbs.setSingleHost(singleHost);
         } else {
            hfbs.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD, CCIConstants.TYPE_INFO, HostFamilyCodes.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            return hfbs;
         }

         query = em.createNativeQuery(SP_HF_FAMILY_BASIC_CONTACT_INFORMATION);
         query.setParameter(1, familyBasicsPageParam.getHostfamilyId() == 0 ? null : familyBasicsPageParam.getHostfamilyId());
         query.setParameter(2, familyBasicsPageParam.getSeasonId() == 0 ? null : familyBasicsPageParam.getSeasonId());
         query.setParameter(3, familyBasicsPageParam.getDepartmentProgramId() == 0 ? null : familyBasicsPageParam.getDepartmentProgramId());

         result = query.getResultList();
         if (result != null && !result.isEmpty()) {
            for (Object[] obj : result) {
               try {
                  HFContactInfo info = new HFContactInfo();
                  info.setHaveHomePhoneOrLandline(Boolean.valueOf(String.valueOf(obj[0])));
                  info.setPhone(String.valueOf(obj[1]));
                  info.setPreferPhone(Boolean.valueOf(String.valueOf(obj[2])));
                  info.setPreferEmail(Boolean.valueOf(String.valueOf(obj[3])));
                  info.setContactPhone(String.valueOf(obj[4]));
                  info.setContactEmail(String.valueOf(obj[5]));
                  info.setEmergencyContactPerson(String.valueOf(obj[6]));
                  info.setEmergencyPhone(String.valueOf(obj[7]));

                  hfbs.setContactInfo(info);

                  HFPhysicalAddress paddress = new HFPhysicalAddress();
                  paddress.setAddress1(String.valueOf(obj[8]));
                  paddress.setCity(String.valueOf(obj[9]));
                  // us1.`stateName` AS physicalState,
                  // LookupUSState st =
                  // stateRepository.getStateByName(String.valueOf(obj[10]));
                  paddress.setStateId(Integer.parseInt(String.valueOf(obj[10]))); // we
                                                                                  // need
                                                                                  // state
                                                                                  // Id
                  // obj[10]
                  paddress.setZipCode(String.valueOf(obj[11]));
                  hfbs.setPhysicalAddress(paddress);

                  // hf.`mailingAddress`,
                  HFMailingAddress mailAddress = new HFMailingAddress();
                  mailAddress.setAddress1(String.valueOf(obj[12]));
                  // hf.`mailingCity`,
                  mailAddress.setCity(String.valueOf(obj[13]));
                  // us2.`stateName` as mailingState,
                  // st =
                  // stateRepository.getStateByName(String.valueOf(obj[14]));
                  mailAddress.setStateId(Integer.valueOf(String.valueOf(obj[14])));
                  // hf.`mailingZipCode`,
                  mailAddress.setZipCode(String.valueOf(obj[15]));
                  // hf.`mailingAddressSameAsCurrentAddress`
                  mailAddress.setSameAsPhysicalAddress(Boolean.valueOf(String.valueOf(obj[16])));
                  hfbs.setMailingAddress(mailAddress);
               } catch (Exception e) {
                  LOGGER.error(e.getMessage());
               }
            }
         }

         query = em.createNativeQuery(SP_HF_FAMILY_BASIC_AIRPORTS);
         query.setParameter(1, familyBasicsPageParam.getHostfamilyId() == 0 ? null : familyBasicsPageParam.getHostfamilyId());
         query.setParameter(2, familyBasicsPageParam.getSeasonId() == 0 ? null : familyBasicsPageParam.getSeasonId());
         query.setParameter(3, familyBasicsPageParam.getDepartmentProgramId() == 0 ? null : familyBasicsPageParam.getDepartmentProgramId());
         result = query.getResultList();
         if (result != null && !result.isEmpty()) {
            for (Object[] obj : result) {
               try {
                  HFAirport airport = new HFAirport();
                  // a.`airportName`,
                  // List<Airport> a =
                  // airportRepository.getAirportByName(String.valueOf(obj[0]));
                  // airport.set
                  // airport.setAirportName(String.valueOf(obj[0]));
                  airport.setCity(String.valueOf(obj[0]));
                  // hfa.`distanceToAirport`
                  airport.setDistanceToNearestAirport(Integer.valueOf(String.valueOf(obj[1])));
                  airport.setHostFamilyAirportId(Integer.valueOf(String.valueOf(obj[2])));
                  HostFamilyAirport h = hostFamilyAirportRepository.findOne(airport.getHostFamilyAirportId());
                  airport.setAirportId(h.getAirport().getAirportId());

                  // airport.set
                  hfbs.getAirports().add(airport);
               } catch (Exception e) {
                  LOGGER.error(e.getMessage());
               }
            }
         }

         query = em.createNativeQuery(SP_HF_FAMILY_BASIC_PETS);
         query.setParameter(1, familyBasicsPageParam.getHostfamilyId() == 0 ? null : familyBasicsPageParam.getHostfamilyId());
         query.setParameter(2, familyBasicsPageParam.getSeasonId() == 0 ? null : familyBasicsPageParam.getSeasonId());
         query.setParameter(3, familyBasicsPageParam.getDepartmentProgramId() == 0 ? null : familyBasicsPageParam.getDepartmentProgramId());
         result = query.getResultList();
         if (result != null && !result.isEmpty()) {
            for (Object[] obj : result) {
               try {
                  HFPets pet = new HFPets();
                  // hfpt.`hostFamilyPetTypeName`,
                  // HostFamilyPetType p =
                  // hostFamilyPetTypeRepository.findPetByName(String.valueOf(obj[0]));
                  pet.setTypeId(Integer.valueOf(String.valueOf(obj[0])));
                  // hfp.`number`,
                  pet.setNumber(Integer.valueOf(String.valueOf(obj[1])));
                  // hfp.`isIndoor`,
                  pet.setIndoor(Boolean.valueOf(String.valueOf(obj[2])));
                  // hfp.`isOutdoor`,
                  pet.setOutDoor(Boolean.valueOf(String.valueOf(obj[3])));
                  // hfp.`additionalInformation`
                  pet.setAdditionalInfo(String.valueOf(obj[4]));
                  pet.setHostFamilyPetId(Integer.valueOf(String.valueOf(obj[5])));
                  hfbs.getPets().add(pet);
               } catch (Exception e) {
                  LOGGER.error(e.getMessage());
               }
            }
         }
         try {
            HostFamilyReference singleHostData = hostFamilyReferenceRepository.findBySeasonIdAndThirdReferenceForSingleHost(familyBasicsPageParam.getSeasonId());

            if (singleHostData != null) {
               HFSingleHostDetails singleHostDetail = new HFSingleHostDetails();
               singleHostDetail.setCity(singleHostData.getCity());
               singleHostDetail.setFirstName(singleHostData.getFirstName());
               singleHostDetail.setLastName(singleHostData.getLastName());
               singleHostDetail.setPhone(singleHostData.getPhone());
               singleHostDetail.setRelationshipToFamily(singleHostData.getRelationship());
               if (singleHostData.getLookupUsstate() != null)
                  singleHostDetail.setState(singleHostData.getLookupUsstate().getStateCode());
               singleHostDetail.setStreetAddress(singleHostData.getAddress());
               singleHostDetail.setZipCode(singleHostData.getZipCode());
               singleHostDetail.setHostfamilyMemberId(singleHostData.getHostFamilyReferenceId());
               hfbs.setSingleHostDetail(singleHostDetail);
            }
         } catch (Exception e) {
            LOGGER.error(e.getMessage());
         }

         hfbs.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         hfbs.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_GET_HF_BASIC_DATA.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hfbs;
   }

   @Override
   @Transactional
   public HFHomeDescriptionPage saveHFHouseDescription(String applicationCategoryId, HFHomeDescriptionPage descriptionPage) {
      HFHomeDescriptionPage hp = new HFHomeDescriptionPage();
      try {
         if (descriptionPage.getLoginId() <= 0) {
            throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_OR_NULL_LOGIN_ID));
         }
         if (descriptionPage.getSeasonId() <= 0) {
            throw new CcighgoException("NO Season ID");
         }
         HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(descriptionPage.getHostFamilySeasonId(),
               Integer.valueOf(applicationCategoryId));
         if (hostFamilySeasonCategory != null) {
            hostFamilySeasonCategory.setFilledMandatoryFields(descriptionPage.getFieldsFilled());
            hostFamilySeasonCategoryRepository.saveAndFlush(hostFamilySeasonCategory);
         }
         HostFamilySeason season = hostFamilySeasonRepository.getSeason(descriptionPage.getSeasonId(), descriptionPage.getProgramId(), descriptionPage.getHostFamilyId());
         HostFamilyHome hfd = new HostFamilyHome();
         if (descriptionPage.getHostFamilyHomeId() > 0)
            hfd = hostFamilyHomeRepository.findOne(descriptionPage.getHostFamilyHomeId());

         if (hfd == null)
            hfd = new HostFamilyHome();

         hfd.setHostFamilySeason(season);
         hfd.setHomeType(descriptionPage.getDescription().getHomeType());
         hfd.setHomeLocation(descriptionPage.getDescription().getLocatedIn());
         hfd.setBedroomNumber(descriptionPage.getDescription().getNoOfBedRooms());
         hfd.setBathroomNumber(descriptionPage.getDescription().getNoOfBathRooms());
         hfd.setHomeDescription(descriptionPage.getDescription().getWhatWhouldTheStudentsKnowAboutYourHouse());
         hfd.setInterestedForTwoStudents(descriptionPage.getDescription().isInterestedInHostingTwoAtAtime() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hfd.setSharesBedroom(descriptionPage.getDescription().isStudentWillShareBedRoom() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hfd.setSharesBedroomWith(descriptionPage.getDescription().getRoomMateName());
         LookupGender l = genderRepository.findOne(descriptionPage.getDescription().getRoomMateGenderId());
         hfd.setLookupGender(l);
         hfd.setSharingAge(descriptionPage.getDescription().getRoomMateAge());
         hfd.setExtraFacilities(descriptionPage.getDescription().getExtraFacilities());
         hfd.setIsStudentsRoomBasement(descriptionPage.getDescription().isStudentRoomWillbeInThebasement() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hfd.setExitBasement(descriptionPage.getDescription().isIsTheirExistInTheBasement() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hfd.setResidenceSiteFunctioningBusiness(descriptionPage.getDescription().isResidenceFunctioningBusiness() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hfd.setSpecifyTypeOfBusiness(descriptionPage.getDescription().getResidenceTypeOfBusiness());
         hfd.setOtherTypeOfBusiness(descriptionPage.getDescription().getResidenceTypeOfBusinessDescription());
         hfd.setUtilities(descriptionPage.getDescription().getUtilities());
         hfd.setSpecialFeaturesInHome(descriptionPage.getDescription().getSpecialFeatureInyourHome());
         hfd.setAmenities(descriptionPage.getDescription().getAmenities());
         hfd.setHostedOther(CCIConstants.FALSE_BYTE);
         hfd.setLocalCoordinatorOther(CCIConstants.FALSE_BYTE);

         hostFamilyHomeRepository.saveAndFlush(hfd);
         // hp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS,
         // CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
         // messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

         HFHomeDescriptionPageParam param = new HFHomeDescriptionPageParam();
         param.setDeptProgramId(descriptionPage.getProgramId());
         param.setHostFamilyId(descriptionPage.getHostFamilyId());
         param.setLoginId(descriptionPage.getLoginId());
         param.setSeasonId(descriptionPage.getSeasonId());
         hp = fetchHFHouseDescription(param);
      } catch (CcighgoException e) {
         hp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_SAVE_HF_HOUSE_DESCRIPTION.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hp;
   }

   @Override
   public HFHomeDescriptionPage fetchHFHouseDescription(HFHomeDescriptionPageParam descriptionPageParam) {
      HFHomeDescriptionPage hfbs = new HFHomeDescriptionPage();
      try {
         HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(
               Integer.valueOf(descriptionPageParam.getSeasonId()), descriptionPageParam.getApplicationCategoryId());
         if (hostFamilySeasonCategory != null)
            hfbs.setPercentUpdate(CCIUtils.getFormFilledPercentage(hostFamilySeasonCategory.getTotalMandatoryFields(), hostFamilySeasonCategory.getFilledMandatoryFields()));

         Query query = em.createNativeQuery(SP_HF_HOME);
         query.setParameter(1, descriptionPageParam.getHostFamilyId() == 0 ? null : descriptionPageParam.getHostFamilyId());
         query.setParameter(2, descriptionPageParam.getSeasonId() == 0 ? null : descriptionPageParam.getSeasonId());
         query.setParameter(3, descriptionPageParam.getDeptProgramId() == 0 ? null : descriptionPageParam.getDeptProgramId());
         @SuppressWarnings("unchecked")
         List<Object[]> result = query.getResultList();
         if (result != null && !result.isEmpty()) {
            for (Object[] obj : result) {
               // ps.programName as seasonName, obj[0]
               HFHomeDescription hd = new HFHomeDescription();
               // ps.seasonId as seasonId,
               hfbs.setSeasonId(Integer.valueOf(String.valueOf(obj[1])));
               // ps.departmentProgramId as depProgramId,
               hfbs.setProgramId(Integer.valueOf(String.valueOf(obj[2])));
               // hfh.homeType AS homeType,
               hd.setHomeType(String.valueOf(obj[3]));
               // hfh.homeLocation AS homeLocation,
               hd.setLocatedIn(String.valueOf(obj[4]));
               // hfh.bedroomNumber AS bedroomNumber,
               hd.setNoOfBedRooms(Integer.valueOf(String.valueOf(obj[5])));
               // hfh.bathroomNumber AS bathroomNumber,
               hd.setNoOfBathRooms(Integer.valueOf(String.valueOf(obj[6])));
               // hfh.homeDescription AS homeDescription,
               hd.setWhatWhouldTheStudentsKnowAboutYourHouse(String.valueOf(obj[7]));
               // hfh.interestedForTwoStudents AS interestedForTwoStudents,
               hd.setInterestedInHostingTwoAtAtime(Boolean.valueOf(String.valueOf(obj[8])));
               // hfh.sharesBedroom AS sharesBedroom,
               hd.setStudentWillShareBedRoom(Boolean.valueOf(String.valueOf(obj[9])));
               // hfh.sharesBedroomWith AS sharesBedroomWith,
               hd.setRoomMateName(String.valueOf(obj[10]));
               // lg.genderName AS sharingBedroomGender,
               if (obj[11] != null) {
                  hd.setRoomMateGenderId(Integer.valueOf(String.valueOf(obj[11])));
                  hd.setRoomMateGender(genderRepository.findOne(Integer.valueOf(String.valueOf(obj[11]))).getGenderName());
               }
               // hfh.sharingAge AS sharingAge,
               if (obj[12] != null)
                  hd.setRoomMateAge(Integer.valueOf(String.valueOf(obj[12])));
               // hfh.extraFacilities AS extraFacilities,
               hd.setExtraFacilities(String.valueOf(obj[13]));
               // hfh.isStudentsRoomBasement AS isStudentsRoomBasement,
               hd.setStudentRoomWillbeInThebasement(Boolean.valueOf(String.valueOf(obj[14])));
               // hfh.exitBasement AS exitBasement,
               hd.setIsTheirExistInTheBasement(Boolean.valueOf(String.valueOf(obj[15])));
               // hfh.residenceSiteFunctioningBusiness AS
               hd.setResidenceFunctioningBusiness(Boolean.valueOf(String.valueOf(obj[16])));
               // hfh.specifyTypeOfBusiness AS specifyTypeOfBusiness,
               hd.setResidenceTypeOfBusiness(String.valueOf(obj[17]));
               // hfh.otherTypeOfBusiness AS otherTypeOfBusiness,
               hd.setResidenceTypeOfBusinessDescription(String.valueOf(obj[18]));
               // hfh.utilities AS utilities,
               hd.setUtilities(String.valueOf(obj[19]));
               // hfh.specialFeaturesInHome AS specialFeaturesInHome,
               hd.setSpecialFeatureInyourHome(String.valueOf(obj[20]));
               // hfh.amenities AS amenities
               hd.setAmenities(String.valueOf(obj[21]));
               hfbs.setDescription(hd);
               hfbs.setHostFamilySeasonId(Integer.valueOf(String.valueOf(obj[22])));
               hfbs.setHostFamilyHomeId(Integer.valueOf(String.valueOf(obj[23])));

               break;
            }
         }

         hfbs.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (CcighgoException e) {
         hfbs.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_GET_HF_HOUSE_DESCRIPTION.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hfbs;
   }

   @Override
   @Transactional
   public HFCommunityAndSchoolPage saveHFCoummnityAndSchool(String applicationCategoryId, HFCommunityAndSchoolPage communityAndSchoolPage) {
      HFCommunityAndSchoolPage hp = new HFCommunityAndSchoolPage();
      try {
         if (communityAndSchoolPage.getLoginId() <= 0) {
            throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_OR_NULL_LOGIN_ID));
         }
         if (communityAndSchoolPage.getSeasonId() <= 0) {
            throw new CcighgoException("NO Season ID");
         }
         HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(
               communityAndSchoolPage.getHostFamilySeasonId(), Integer.valueOf(applicationCategoryId));
         if (hostFamilySeasonCategory != null) {
            hostFamilySeasonCategory.setFilledMandatoryFields(communityAndSchoolPage.getFieldsFilled());
            hostFamilySeasonCategoryRepository.saveAndFlush(hostFamilySeasonCategory);
         }
         HostFamilySeason season = hostFamilySeasonRepository.getSeason(communityAndSchoolPage.getSeasonId(), communityAndSchoolPage.getProgramId(),
               communityAndSchoolPage.getHostFamilyId());
         HostFamilyCommunity hfd = new HostFamilyCommunity();
         if (communityAndSchoolPage.getHostFamilyCommunityId() > 0)
            hfd = hostFamilyCommunityRepository.findOne(communityAndSchoolPage.getHostFamilyCommunityId());
         if (hfd == null)
            hfd = new HostFamilyCommunity();
         hfd.setHostFamilySeason(season);
         hfd.setPopulation(communityAndSchoolPage.getCommunity().getPopulationOfTheTown());
         hfd.setCityWebsite(communityAndSchoolPage.getCommunity().getCityOrTownWebSite());
         hfd.setNearestCity(communityAndSchoolPage.getCommunity().getNearestMajorCity());
         hfd.setNearestCityPopulation(communityAndSchoolPage.getCommunity().getPopulationOfNearestCity());
         hfd.setDistanceFromCity(communityAndSchoolPage.getCommunity().getDistanceFromCity() + "");
         hfd.setUniquenessAboutCommunity(communityAndSchoolPage.getCommunity().getUniqueAboutYourCommunity());
         hfd.setPlacesOfInterest(communityAndSchoolPage.getCommunity().getPlacesAndEventsMightInterestTheStudent());
         hfd.setAreasToAvoid(communityAndSchoolPage.getCommunity().getAreasToBeAvoidedInTheNeighbourhood());
         hfd.setVolunteeringOpportunitiesCommunity(communityAndSchoolPage.getCommunity().getVolunteeringOpportunitiesInTheCommunity());
         hfd.setSchoolTravelMethod(communityAndSchoolPage.getSchoolLife().getStudentWillGotoSchoolBy());
         hfd.setDistanceToSchool(communityAndSchoolPage.getSchoolLife().getDistanceBetweenSchoolAndHome() + "");
         hfd.setTransportationToActivities(communityAndSchoolPage.getSchoolLife().isProvideSpecialTransformation() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hfd.setTransportationToActivitiesDetails(communityAndSchoolPage.getSchoolLife().getSpecialTransformationDetails());
         hfd.setChildrenEnrolled(communityAndSchoolPage.getSchoolLife().getFamilyChildEnrolledInTheSameSchool());
         hfd.setChildrenActivities(communityAndSchoolPage.getSchoolLife().getActivitiesChildrenInvolvedInAtSchool());
         hfd.setContactACoach(communityAndSchoolPage.getSchoolLife().isContactedCoatchForParticularAthleticAbility() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hfd.setContactByCoachDetails(communityAndSchoolPage.getSchoolLife().getAlthleticAbilityDetails());
         hfd.setParentIsTeacher(communityAndSchoolPage.getSchoolLife().isAnyMemberTeachOrCoachAtSchool() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         // hfd.setCreatedBy(hfApplicationFamilyDetails.getLoginId());
         // hfd.setCreatedOn(new
         // java.sql.Timestamp(System.currentTimeMillis()));
         // hfd.setCreatedBy(hfApplicationFamilyDetails.getLoginId());
         // hfd.setCreatedOn(new
         // java.sql.Timestamp(System.currentTimeMillis()));
         hostFamilyCommunityRepository.saveAndFlush(hfd);
         HFCommunityAndSchoolPageParam descriptionPageParam = new HFCommunityAndSchoolPageParam();
         descriptionPageParam.setDeptProgramId(communityAndSchoolPage.getProgramId());
         descriptionPageParam.setHostFamilyId(communityAndSchoolPage.getHostFamilyId());
         descriptionPageParam.setLoginId(communityAndSchoolPage.getLoginId());
         descriptionPageParam.setSeasonId(communityAndSchoolPage.getSeasonId());
         descriptionPageParam.setHostFamilySeasnId(communityAndSchoolPage.getHostFamilySeasonId());
         descriptionPageParam.setApplicationCategoryId(Integer.valueOf(applicationCategoryId));
         // hp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS,
         // CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
         // messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         hp = fetchHFCoummnityAndSchool(descriptionPageParam);
      } catch (CcighgoException e) {
         hp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_SAVE_HF_COMMUNITY_AND_SCHOOL.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hp;
   }

   @Override
   public HFCommunityAndSchoolPage fetchHFCoummnityAndSchool(HFCommunityAndSchoolPageParam descriptionPageParam) {
      HFCommunityAndSchoolPage hfbs = new HFCommunityAndSchoolPage();
      try {
         HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(
               Integer.valueOf(descriptionPageParam.getHostFamilySeasnId()), descriptionPageParam.getApplicationCategoryId());
         if (hostFamilySeasonCategory != null) {
            hfbs.setPercentUpdate(CCIUtils.getFormFilledPercentage(hostFamilySeasonCategory.getTotalMandatoryFields(), hostFamilySeasonCategory.getFilledMandatoryFields()));
            hfbs.setFieldsFilled(hostFamilySeasonCategory.getFilledMandatoryFields());
         }

         Query query = em.createNativeQuery(SP_HF_COMMUNITY);
         query.setParameter(1, descriptionPageParam.getHostFamilyId() == 0 ? null : descriptionPageParam.getHostFamilyId());
         query.setParameter(2, descriptionPageParam.getSeasonId() == 0 ? null : descriptionPageParam.getSeasonId());
         query.setParameter(3, descriptionPageParam.getDeptProgramId() == 0 ? null : descriptionPageParam.getDeptProgramId());
         @SuppressWarnings("unchecked")
         List<Object[]> result = query.getResultList();
         if (result != null && !result.isEmpty()) {
            HFCommunity c = new HFCommunity();
            for (Object[] obj : result) {
               // hfc.`population`,
               c.setPopulationOfTheTown(String.valueOf(obj[0]));
               // hfc.`cityWebsite`,
               c.setCityOrTownWebSite(String.valueOf(obj[1]));
               // hfc.`nearestCity`,
               c.setNearestMajorCity(String.valueOf(obj[2]));
               // hfc.`nearestCityPopulation`,
               c.setPopulationOfNearestCity(String.valueOf(obj[3]));
               // hfc.`distanceFromCity`,
               c.setDistanceFromCity(String.valueOf(obj[4]));
               // hfc.`uniquenessAboutCommunity`,
               c.setUniqueAboutYourCommunity(String.valueOf(obj[5]));
               // hfc.`placesOfInterest`,
               c.setPlacesAndEventsMightInterestTheStudent(String.valueOf(obj[6]));
               // hfc.`areasToAvoid`,
               c.setAreasToBeAvoidedInTheNeighbourhood(String.valueOf(obj[7]));
               // hfc.`volunteeringOpportunitiesCommunity`
               c.setVolunteeringOpportunitiesInTheCommunity(String.valueOf(obj[8]));
               c.setHostFamilySeasonId(Integer.valueOf(String.valueOf(obj[11])));
               c.setHostFamilyCommunityId(Integer.valueOf(String.valueOf(obj[12])));
               hfbs.setHostFamilyCommunityId(Integer.valueOf(String.valueOf(obj[12])));
               break;
            }
            hfbs.setCommunity(c);
         }
         query = em.createNativeQuery(SP_HF_SCHOOL_LIFE);
         query.setParameter(1, descriptionPageParam.getHostFamilyId() == 0 ? null : descriptionPageParam.getHostFamilyId());
         query.setParameter(2, descriptionPageParam.getSeasonId() == 0 ? null : descriptionPageParam.getSeasonId());
         query.setParameter(3, descriptionPageParam.getDeptProgramId() == 0 ? null : descriptionPageParam.getDeptProgramId());
         result = query.getResultList();
         if (result != null && !result.isEmpty()) {
            HFSchoolLife l = new HFSchoolLife();
            for (Object[] obj : result) {
               // hfc.`schoolTravelMethod`,
               l.setStudentWillGotoSchoolBy(String.valueOf(obj[0]));
               // hfc.`distanceToSchool`,
               l.setDistanceBetweenSchoolAndHome(String.valueOf(String.valueOf(obj[1])));
               // hfc.`transportationToActivities`,
               l.setProvideSpecialTransformation(Boolean.valueOf(String.valueOf(obj[2])));
               // hfc.`transportationToActivitiesDetails`,
               l.setSpecialTransformationDetails(String.valueOf(obj[3]));
               // hfc.`childrenEnrolled`,
               l.setFamilyChildEnrolledInTheSameSchool(String.valueOf(obj[4]));
               // hfc.`childrenActivities`,
               l.setActivitiesChildrenInvolvedInAtSchool(String.valueOf(obj[5]));
               // hfc.`contactACoach`,
               l.setContactedCoatchForParticularAthleticAbility(Boolean.valueOf(String.valueOf(obj[6])));
               // hfc.`parentIsTeacher`
               l.setAnyMemberTeachOrCoachAtSchool(Boolean.valueOf(String.valueOf(obj[7])));
               l.setHostFamilySeasonId(Integer.valueOf(String.valueOf(obj[11])));
               l.setHostFamilySchoolLifeId(Integer.valueOf(String.valueOf(obj[12])));
               hfbs.setHostFamilyCommunityId(Integer.valueOf(String.valueOf(obj[12])));
               break;
            }
            hfbs.setSchoolLife(l);
         }
         hfbs.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         hfbs.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_GET_HF_COMMUNITY_AND_SCHOOL.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hfbs;
   }

   @Override
   public HFApplicationFamilyDetails saveFamilyBasicData(String applicationCategoryId, HFApplicationFamilyDetails hfApplicationFamilyDetails) {
      HFApplicationFamilyDetails hp = new HFApplicationFamilyDetails();
      try {
         if (hfApplicationFamilyDetails.getLoginId() <= 0) {
            throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_OR_NULL_LOGIN_ID));
         }
         if (hfApplicationFamilyDetails.getSeasonId() <= 0) {
            throw new CcighgoException("NO Season ID");
         }
         HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(
               hfApplicationFamilyDetails.getSeasonId(), Integer.valueOf(applicationCategoryId));
         if (hostFamilySeasonCategory != null) {
            hostFamilySeasonCategory.setFilledMandatoryFields(hfApplicationFamilyDetails.getFieldsFilled());
            hostFamilySeasonCategoryRepository.saveAndFlush(hostFamilySeasonCategory);
         }
         // Save Single host Data TODO
         // add photo
         HostFamilySeason season = hostFamilySeasonRepository.getSeason(hfApplicationFamilyDetails.getSeasonId(), hfApplicationFamilyDetails.getProgramId(),
               hfApplicationFamilyDetails.getHostFamilyId());
         if (hfApplicationFamilyDetails.getPhoto() != null) {
            HostFamilyPhoto hfPhoto = new HostFamilyPhoto();
            if (hfApplicationFamilyDetails.getPhoto() != null && hfApplicationFamilyDetails.getPhoto().getPhotoId() > 0)
               hfPhoto = hostFamilyPhotosRepository.findOne(hfApplicationFamilyDetails.getPhoto().getPhotoId());

            if (hfPhoto == null)
               hfPhoto = new HostFamilyPhoto();
            hfPhoto.setHostFamilySeason(season);
            hfPhoto.setHostFamilyPhotosType(hostFamilyPhotosTypeRepository.findOne(hfApplicationFamilyDetails.getPhoto().getTypeId()));
            hfPhoto.setFilePath(hfApplicationFamilyDetails.getPhoto().getFilePath());
            hfPhoto.setDescription(hfApplicationFamilyDetails.getPhoto().getDescription());
            hfPhoto.setIsOptional(CCIConstants.INACTIVE);
            hfPhoto.setCreatedBy(hfApplicationFamilyDetails.getLoginId());
            hfPhoto.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            hfPhoto.setActive(CCIConstants.ACTIVE);
            hfPhoto.setSubmittedToCCI(CCIConstants.INACTIVE);
            hfPhoto.setApprovedByCCI(CCIConstants.INACTIVE);
            hfPhoto.setRejectedByCCI(CCIConstants.INACTIVE);
            hostFamilyPhotosRepository.saveAndFlush(hfPhoto);
         }

         // HOstFamilly Member
         for (HFAdultDetails member : hfApplicationFamilyDetails.getAdults()) {
            HostFamilyMember hfm = new HostFamilyMember();
            if (member.getHostfamilyMemberId() > 0)
               hfm = hfMemberRepository.findOne(member.getHostfamilyMemberId());
            if (hfm == null)
               hfm = new HostFamilyMember();
            hfm.setIsSingleAdult(hfApplicationFamilyDetails.isSingleHost() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
            hfm.setFirstName(member.getFirstName());
            hfm.setLastName(member.getLastName());
            hfm.setIsHostParent(member.isIsHostParent() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
            hfm.setMemberEmail(member.getEmail());
            hfm.setMemberPhone(member.getPersonalPhone());
            hfm.setBirthDate(DateUtils.getMMddyyDateForHostFamily(member.getBirthdate()));
            LookupGender gender = genderRepository.findOne(member.getGenderId());
            hfm.setLookupGender(gender);
            hfm.setEducationLevel(member.getEducationLevel());
            hfm.setLivingAtHome(member.getResidencyTime());
            hfm.setLivingAtHomeExplanation(member.getLivingInsideHomeExplanation());
            hfm.setCommunityInvolvement(member.getCommunityInvolvement());
            hfm.setInterests(member.getActivitiesOrInterests());
            hfm.setEmployed(member.getEmployed());
            hfm.setEmploymentType(member.getEmploymentType());
            hfm.setEmployer1(member.getEmployer());
            hfm.setJobTitle1(member.getJobTitle());
            hfm.setContactName1(member.getContactName());
            hfm.setRelationship(member.getRelationship());
            hfm.setPhone1(member.getJobPhone());
            if (member.isHasAnotherJob()) {
               hfm.setHaveAnotherJob(CCIConstants.TRUE_BYTE);
               if (member.getOtherEmployer() != null)
                  hfm.setEmployer2(member.getOtherEmployer());
               if (member.getOtherJobTitle() != null)
                  hfm.setJobTitle2(member.getOtherJobTitle());
               if (member.getOtherContactName() != null)
                  hfm.setContactName2(member.getOtherContactName());
               if (member.getOtherJobPhone() != null)
                  hfm.setPhone2(member.getOtherJobPhone());
            }
            hfm.setCreatedBy(hfApplicationFamilyDetails.getLoginId());
            hfm.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            hfm.setHostFamilySeason(season);

            // listOfMembers.add(hfm);
            hfMemberRepository.saveAndFlush(hfm);
         }
         // if (!listOfMembers.isEmpty())
         // hfMemberRepository.save(listOfMembers);

         // contact Information
         HostFamily hf = hostFamilyRepository.findOne(hfApplicationFamilyDetails.getHostFamilyId());
         hf.setHaveAHomePhone(hfApplicationFamilyDetails.getContactInfo().isHaveHomePhoneOrLandline() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hf.setHomePhone(hfApplicationFamilyDetails.getContactInfo().getPhone());
         hf.setPreferredContactMethodEmail(hfApplicationFamilyDetails.getContactInfo().isPreferEmail() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hf.setPreferredContactMethodPhone(hfApplicationFamilyDetails.getContactInfo().isPreferPhone() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hf.setPreferredEmail(hfApplicationFamilyDetails.getContactInfo().getContactEmail());
         hf.setPreferredPhone(hfApplicationFamilyDetails.getContactInfo().getContactPhone());
         hf.setEmergencyContact(hfApplicationFamilyDetails.getContactInfo().getEmergencyContactPerson());
         hf.setEmergencyPhone(hfApplicationFamilyDetails.getContactInfo().getEmergencyPhone());
         if (hfApplicationFamilyDetails.getPhysicalAddress() != null) {
            // Physical Address
            hf.setPhysicalAddress(hfApplicationFamilyDetails.getPhysicalAddress().getAddress1());
            hf.setPhysicalCity(hfApplicationFamilyDetails.getPhysicalAddress().getCity());
            hf.setPhysicalZipCode(hfApplicationFamilyDetails.getPhysicalAddress().getZipCode());
            LookupUSState physicalAddressState = stateRepository.findOne(hfApplicationFamilyDetails.getPhysicalAddress().getStateId());
            hf.setLookupUsstate2(physicalAddressState);
         }
         if (hfApplicationFamilyDetails.getMailingAddress() != null) {
            // mailing address
            hf.setMailingAddressSameAsCurrentAddress(hfApplicationFamilyDetails.getMailingAddress().isSameAsPhysicalAddress() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
            hf.setMailingAddress(hfApplicationFamilyDetails.getMailingAddress().getAddress1());
            hf.setMailingCity(hfApplicationFamilyDetails.getMailingAddress().getCity());
            hf.setMailingZipCode(hfApplicationFamilyDetails.getMailingAddress().getZipCode());
            LookupUSState mailingAddressState = stateRepository.findOne(hfApplicationFamilyDetails.getMailingAddress().getStateId());
            hf.setLookupUsstate1(mailingAddressState);
         }

         hf.setCreatedBy(hfApplicationFamilyDetails.getLoginId());
         hf.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         hostFamilyRepository.saveAndFlush(hf);

         // Airport
         for (HFAirport aps : hfApplicationFamilyDetails.getAirports()) {
            HostFamilyAirport hfa = new HostFamilyAirport();
            if (aps.getHostFamilyAirportId() > 0)
               hfa = hostFamilyAirportRepository.findOne(aps.getHostFamilyAirportId());
            if (hfa == null)
               hfa = new HostFamilyAirport();

            hfa.setHostFamily(hf);
            Airport airport = airportRepository.findOne(aps.getAirportId());
            hfa.setAirport(airport);
            hfa.setDistanceToAirport(aps.getDistanceToNearestAirport());
            hfa.setCreatedBy(hfApplicationFamilyDetails.getLoginId());
            hfa.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            // airports.add(hfa);
            hostFamilyAirportRepository.saveAndFlush(hfa);
         }
         // if (!airports.isEmpty())
         // hostFamilyAirportRepository.save(airports);

         // Pets
         for (HFPets pts : hfApplicationFamilyDetails.getPets()) {
            HostFamilyPet hfp = new HostFamilyPet();
            if (pts.getHostFamilyPetId() > 0)
               hfp = hostFamilyPetRepository.findOne(pts.getHostFamilyPetId());
            if (hfp == null)
               hfp = new HostFamilyPet();
            HostFamilyPetType hostFamilyPetType = hostFamilyPetTypeRepository.findOne(pts.getTypeId());
            hfp.setHostFamilyPetType(hostFamilyPetType);
            hfp.setIsIndoor(pts.isIndoor() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
            hfp.setIsOutdoor(pts.isOutDoor() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
            hfp.setNumber(pts.getNumber());
            hfp.setHostFamilySeason(season);
            hfp.setAdditionalInformation(pts.getAdditionalInfo());
            // pets.add(hfp);
            hostFamilyPetRepository.saveAndFlush(hfp);
         }
         // if (!pets.isEmpty())
         // hostFamilyPetRepository.save(pets);

         try {
            if (hfApplicationFamilyDetails.getSingleHostDetail() != null) {
               HostFamilyReference hRef1 = new HostFamilyReference();
               hRef1.setFirstName(hfApplicationFamilyDetails.getSingleHostDetail().getFirstName());
               hRef1.setLastName(hfApplicationFamilyDetails.getSingleHostDetail().getLastName());
               hRef1.setAddress(hfApplicationFamilyDetails.getSingleHostDetail().getStreetAddress());
               hRef1.setCity(hfApplicationFamilyDetails.getSingleHostDetail().getCity());
               LookupUSState lookupUsstate = stateRepository.getStateByStateCode(hfApplicationFamilyDetails.getSingleHostDetail().getState());
               hRef1.setLookupUsstate(lookupUsstate);
               hRef1.setZipCode(hfApplicationFamilyDetails.getSingleHostDetail().getZipCode());
               hRef1.setPhone(hfApplicationFamilyDetails.getSingleHostDetail().getPhone());
               hRef1.setRelationship(hfApplicationFamilyDetails.getSingleHostDetail().getRelationshipToFamily());

               hRef1.setActive(CCIConstants.ACTIVE);
               hRef1.setHostFamilySeason(hostFamilySeasonRepository.findOne(hfApplicationFamilyDetails.getSeasonId()));
               hRef1.setCreatedBy(hfApplicationFamilyDetails.getLoginId());
               hRef1.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               hRef1.setModifiedBy(hfApplicationFamilyDetails.getLoginId());
               hRef1.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               hRef1.setIsThirdReferenceForSingleHost(CCIConstants.TRUE_BYTE);
               hostFamilyReferenceRepository.saveAndFlush(hRef1);
            }
         } catch (Exception e) {
            LOGGER.error(e.getMessage());
         }

         FamilyBasicsPageParam familyBasicsPageParam = new FamilyBasicsPageParam();
         familyBasicsPageParam.setDepartmentProgramId(hfApplicationFamilyDetails.getProgramId());
         familyBasicsPageParam.setHostfamilyId(hfApplicationFamilyDetails.getHostFamilyId());
         familyBasicsPageParam.setSeasonId(hfApplicationFamilyDetails.getSeasonId());
         familyBasicsPageParam.setLoginId(hfApplicationFamilyDetails.getLoginId());
         hp = fetchBasicData(familyBasicsPageParam);

      } catch (CcighgoException e) {
         hp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_SAVE_HF_BASIC_DATA.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hp;
   }

   @Transactional
   @Override
   public HFApplicationFamilyLifeStyle saveFamilyLifeStyleData(String applicationCategoryId, HFApplicationFamilyLifeStyle hfApplicationFamilyDetails) {
      HFApplicationFamilyLifeStyle hp = new HFApplicationFamilyLifeStyle();
      try {
         if (hfApplicationFamilyDetails.getLoginId() <= 0) {
            throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_OR_NULL_LOGIN_ID));
         }
         if (hfApplicationFamilyDetails.getSeasonId() <= 0) {
            throw new CcighgoException("NO Season ID");
         }
         HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(
               hfApplicationFamilyDetails.getSeasonId(), Integer.valueOf(applicationCategoryId));
         if (hostFamilySeasonCategory != null) {
            hostFamilySeasonCategory.setFilledMandatoryFields(hfApplicationFamilyDetails.getFieldsFilled());
            hostFamilySeasonCategoryRepository.saveAndFlush(hostFamilySeasonCategory);
         }
         HostFamilySeason season = hostFamilySeasonRepository.getSeason(hfApplicationFamilyDetails.getSeasonId(), hfApplicationFamilyDetails.getProgramId(),
               hfApplicationFamilyDetails.getHostFamilyId());

         // Household members
         HostFamilyDetail hfd = new HostFamilyDetail();
         if (hfApplicationFamilyDetails.getHostFamilyDetailsId() > 0) {
            hfd = hostFamilyDetailRepository.findOne(hfApplicationFamilyDetails.getHostFamilyDetailsId());
         }
         if (hfd == null)
            hfd = new HostFamilyDetail();

         hfd.setHostFamilySeason(season);
         hfd.setFamilyMemberDescription(hfApplicationFamilyDetails.getFamilyDescription());
         hfd.setIllness(hfApplicationFamilyDetails.isAnyOneHasSeriousIllness() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hfd.setIllnessDetails(hfApplicationFamilyDetails.getIllnessExplanation());
         hfd.setDisability(hfApplicationFamilyDetails.isAnyOneHaveDisability() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hfd.setDisabilityDetails(hfApplicationFamilyDetails.getDisabilityExplanation());
         hfd.setAdaptCircumtances(hfApplicationFamilyDetails.getThingsStudentNeedsToAdaptWith());

         // dayIn The life of The Family
         hfd.setHouseHoldType(hfApplicationFamilyDetails.getFamilyDay().getHousehold());
         hfd.setTypicalWeekday(hfApplicationFamilyDetails.getFamilyDay().getTypicalWeekdayAtHome());
         hfd.setTypicalWeekend(hfApplicationFamilyDetails.getFamilyDay().getTypicalWeekendAtHome());
         hfd.setFavouriteWeekend(hfApplicationFamilyDetails.getFamilyDay().getFavouriteThingsToDoAsFamily());
         hfd.setHomeLanguage(hfApplicationFamilyDetails.getFamilyDay().getFamilyHomeLanguage());
         hfd.setOtherLaungage(hfApplicationFamilyDetails.getFamilyDay().getFamilyOtherLanguage());

         // Religion
         hfd.setReligiousAffiliation(hfApplicationFamilyDetails.getReligious().getReligious());
         hfd.setOtherReligiousDetails(hfApplicationFamilyDetails.getReligious().getExplanation());
         hfd.setReligiousAttendance(hfApplicationFamilyDetails.getReligious().getOftenAttendReligiousMeetings());
         hfd.setPreferStudentJoins(hfApplicationFamilyDetails.getReligious().getPreferedTheStudentJoinYou());
         hfd.setInviteStudentForReligiousExperience(hfApplicationFamilyDetails.getReligious().isInviteStudentForReligiousExperience() ? CCIConstants.TRUE_BYTE
               : CCIConstants.FALSE_BYTE);
         hfd.setProblemWithReligiousDifference(hfApplicationFamilyDetails.getReligious().isDiffecultyHostingPersonWithDifferentReligious() ? CCIConstants.TRUE_BYTE
               : CCIConstants.FALSE_BYTE);

         // Diet
         hfd.setAgreeToServeMeals(hfApplicationFamilyDetails.getDieTrayRestriction().isProvideStudentWithThreeMeals() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hfd.setDietaryRestrictions(hfApplicationFamilyDetails.getDieTrayRestriction().isFollowDietrayRestriction() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hfd.setDescribeDietaryRestrictions(hfApplicationFamilyDetails.getDieTrayRestriction().getDietrayRestrictionExplanation());
         hfd.setParticipantFollowDiet(hfApplicationFamilyDetails.getDieTrayRestriction().isFollowDietrayRestriction() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hfd.setDescPaxDietaryRestrictions(hfApplicationFamilyDetails.getDieTrayRestriction().getStudentFollowDietrayRestrictionExplanation());
         hfd.setComfortableHostingDiet(hfApplicationFamilyDetails.getDieTrayRestriction().isHostStudentWhoFollowDietrayRestriction() ? CCIConstants.TRUE_BYTE
               : CCIConstants.FALSE_BYTE);

         // Miscellaneous
         hfd.setHasAutoInsurance(hfApplicationFamilyDetails.getMiscLifeStyle().getHaveAutoInsurranceForAllCarsYouHave());
         hfd.setFamilySmoker(hfApplicationFamilyDetails.getMiscLifeStyle().isAnyOneIsSmokingInyourFamily() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hfd.setFamilySmokingPlace(hfApplicationFamilyDetails.getMiscLifeStyle().getWhereSmoking());
         hfd.setDrinkAlcohol(hfApplicationFamilyDetails.getMiscLifeStyle().getAnyOneDrinkAlcoholic());
         hfd.setCrimeConviction(hfApplicationFamilyDetails.getMiscLifeStyle().isAnyOneConvictedInCrime() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hfd.setCrimeConvictionDetails(hfApplicationFamilyDetails.getMiscLifeStyle().getConvictedInCrimeDesc());
         hfd.setChildServicesContact(hfApplicationFamilyDetails.getMiscLifeStyle().isAnyOneInProtectiveServiceAgency() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hfd.setChildServicesContactDetails(hfApplicationFamilyDetails.getMiscLifeStyle().getChildInProtectiveServiceExplanation());

         // Financial
         hfd.setIncomeRange(hfApplicationFamilyDetails.getFinancialResources().getTotalHouseHoldIncome());
         hfd.setReceivePublicAssistance(hfApplicationFamilyDetails.getFinancialResources().isAnyOneReceivePublicAssistant() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         hfd.setPublicAssistanceExplanation(hfApplicationFamilyDetails.getFinancialResources().getPublicAssistantExplanation());

         hostFamilyDetailRepository.saveAndFlush(hfd);
         // hp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS,
         // CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
         // messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

         FamilyStylePageParam param = new FamilyStylePageParam();
         param.setDeptProgramId(hfApplicationFamilyDetails.getProgramId());
         param.setHostFamilyId(hfApplicationFamilyDetails.getHostFamilyId());
         param.setLoginId(hfApplicationFamilyDetails.getLoginId());
         param.setSeasonId(hfApplicationFamilyDetails.getSeasonId());
         hp = fetchFamilyLifeStyle(param);
      } catch (CcighgoException e) {
         hp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_SAVE_HF_LIFE_STYLE.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hp;
   }

   @Override
   @Transactional
   public Response addPotentialReference(PotentialHostFamily potentialHostFmaily) {
      Response response = new Response();
      try {
         if (potentialHostFmaily == null) {
            throw new CcighgoException("cannot create reference with empty data");
         }
         if (potentialHostFmaily.getLoginId() == 0 || potentialHostFmaily.getLoginId() < 0) {
            throw new CcighgoException("invalid login id");
         }
         if (potentialHostFmaily.getGoId() == 0 || potentialHostFmaily.getGoId() < 0) {
            throw new CcighgoException("go id is required to create reference");
         }
         HostFamilyPotentialReference potentialReference = new HostFamilyPotentialReference();
         potentialReference.setHostFamily(hostFamilyRepository.findOne(potentialHostFmaily.getGoId()));
         potentialReference.setReferenceFirstName(potentialHostFmaily.getReferenceFirstName());
         potentialReference.setReferenceLastName(potentialHostFmaily.getReferenceLastName());
         potentialReference.setReferenceReason(potentialHostFmaily.getReferenceReason());
         potentialReference.setReferencePhone(potentialHostFmaily.getReferencePhone());
         potentialReference.setReferenceEmail(potentialHostFmaily.getReferenceEmail());
         potentialReference.setMailingAddress(potentialHostFmaily.getReferenceMailingAddress());
         potentialReference.setReferenceStreetAddress(potentialHostFmaily.getReferenceStreetAddress());
         potentialReference.setReferenceCity(potentialHostFmaily.getReferenceCity());
         potentialReference.setLookupUsstate1(stateRepository.findOne(potentialHostFmaily.getReferenceStateId()));
         potentialReference.setReferenceZipCode(potentialHostFmaily.getReferenceZip());
         potentialReference.setFirstName(potentialHostFmaily.getRefereeFirstName());
         potentialReference.setLastName(potentialHostFmaily.getRefereeLastName());
         potentialReference.setRefereeType(potentialHostFmaily.getRefereeUserType() + "");
         potentialReference.setEmail(potentialHostFmaily.getRefereeEmail());
         potentialReference.setMailingAddress(potentialHostFmaily.getRefereeMailingAddress());
         potentialReference.setStreetAddress(potentialHostFmaily.getRefereeStreetAddress());
         potentialReference.setCity(potentialHostFmaily.getRefereeCity());
         potentialReference.setLookupUsstate2(stateRepository.findOne(potentialHostFmaily.getRefereeStateId()));
         potentialReference.setZipCode(potentialHostFmaily.getRefereeZip());
         potentialReference.setCreatedBy(potentialHostFmaily.getLoginId());
         potentialReference.setCretatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         potentialReference.setModifiedBy(potentialHostFmaily.getLoginId());
         potentialReference.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         hostFamilyPotentialReferenceRepository.saveAndFlush(potentialReference);
         response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_ADD_POTENTIAL_REFERENCE.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return response;
   }

   @Override
   @Transactional
   public HostFamilyReferences createHFReference(String applicationCategoryId, HostFamilyReferences hostFamilyReferences) {
      HostFamilyReferences hfReferences = new HostFamilyReferences();
      try {
         if (hostFamilyReferences.getLoginId() == 0 || hostFamilyReferences.getLoginId() < 0) {
            throw new CcighgoException("login id is missing");
         }
         if (hostFamilyReferences.getSeasonId() == 0 || hostFamilyReferences.getSeasonId() < 0) {
            throw new CcighgoException("host family season is required");
         }
         if (hostFamilyReferences.getReferences() == null) {
            throw new CcighgoException("cannot create record with empty objects");
         }
         if (hostFamilyReferences.getFieldsFilled() == 0 || hostFamilyReferences.getFieldsFilled() < 0) {
            throw new CcighgoException("number of fields filled is mandatory to create the record");
         }
         HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(hostFamilyReferences.getSeasonId(),
               Integer.valueOf(applicationCategoryId));
         hostFamilySeasonCategory.setFilledMandatoryFields(hostFamilyReferences.getFieldsFilled());
         hostFamilySeasonCategoryRepository.saveAndFlush(hostFamilySeasonCategory);
         if (hostFamilyReferences.getReferences() == null) {
            // if null create empty records
            HostFamilyReference hRef1 = new HostFamilyReference();
            hRef1.setActive(CCIConstants.ACTIVE);
            hRef1.setHostFamilySeason(hostFamilySeasonRepository.findOne(hostFamilyReferences.getSeasonId()));
            hRef1.setCreatedBy(hostFamilyReferences.getLoginId());
            hRef1.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            hRef1.setModifiedBy(hostFamilyReferences.getLoginId());
            hRef1.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            hostFamilyReferenceRepository.saveAndFlush(hRef1);
            HostFamilyReference hRef2 = new HostFamilyReference();
            hRef2.setActive(CCIConstants.ACTIVE);
            hRef2.setHostFamilySeason(hostFamilySeasonRepository.findOne(hostFamilyReferences.getSeasonId()));
            hRef2.setCreatedBy(hostFamilyReferences.getLoginId());
            hRef2.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            hRef2.setModifiedBy(hostFamilyReferences.getLoginId());
            hRef2.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            hostFamilyReferenceRepository.saveAndFlush(hRef2);
         } else {
            for (Reference ref : hostFamilyReferences.getReferences()) {
               HostFamilyReference hRef = new HostFamilyReference();
               hRef.setActive(CCIConstants.ACTIVE);
               hRef.setHostFamilySeason(hostFamilySeasonRepository.findOne(hostFamilyReferences.getSeasonId()));
               hRef.setFirstName(ref.getFirstName());
               hRef.setLastName(ref.getLastName());
               hRef.setAddress(ref.getStreet());
               hRef.setCity(ref.getCity());
               hRef.setLookupUsstate(stateRepository.findOne(ref.getState()));
               hRef.setZipCode(ref.getZip());
               hRef.setPhone(ref.getPhone());
               hRef.setRelationship(ref.getRelationshipToFamily());
               hRef.setPersonNotRelatedToBlood(ref.isBloodRelated() ? CCIConstants.INACTIVE : CCIConstants.ACTIVE);
               hRef.setSecondReferenceRelationtoFirst(hfReferences.isSecondReferenceRelatedToFirst() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               hRef.setCreatedBy(hostFamilyReferences.getLoginId());
               hRef.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               hRef.setModifiedBy(hostFamilyReferences.getLoginId());
               hRef.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               hostFamilyReferenceRepository.saveAndFlush(hRef);
            }
         }
         HostFamilyGeneralQuestion exists = hostFamilyGeneralQuestionRepository.getBySeasonId(hostFamilyReferences.getSeasonId());
         if (exists == null) {
            HostFamilyGeneralQuestion questions = new HostFamilyGeneralQuestion();
            questions.setPreviousHostingWithCCI(hostFamilyReferences.isPreviouslyHosted() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            questions.setHostFamilySeason(hostFamilySeasonRepository.findOne(hostFamilyReferences.getSeasonId()));
            questions.setInternet(hostFamilyReferences.getInternet());
            questions.setInternetOthers(hostFamilyReferences.getOtherWebsites());
            questions.setCommunity(hostFamilyReferences.getCommunity());
            questions.setCommunityEvent(hostFamilyReferences.getEvent());
            questions.setCommunityMagazine(hostFamilyReferences.getMagazine());
            questions.setCommunityOthers(hostFamilyReferences.getOtherCommunity());
            hostFamilyGeneralQuestionRepository.saveAndFlush(questions);
         }
         hfReferences = getHFReference(String.valueOf(hostFamilyReferences.getSeasonId()), applicationCategoryId);
         hfReferences.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         hfReferences.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_CREATE_HF_REFERENCE.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hfReferences;
   }

   @Override
   @Transactional
   public HostFamilyReferences updateHFReference(String applicationCategoryId, HostFamilyReferences hostFamilyReferences) {
      HostFamilyReferences upadatedObject = new HostFamilyReferences();
      try {
         if (applicationCategoryId == null) {
            throw new CcighgoException("invalid parameters");
         }
         if (hostFamilyReferences == null) {
            throw new CcighgoException("cannot create update record");
         }
         if (hostFamilyReferences.getLoginId() == 0 || hostFamilyReferences.getLoginId() < 0) {
            throw new CcighgoException("login info is required to update the record");
         }
         if (hostFamilyReferences.getSeasonId() == 0 || hostFamilyReferences.getSeasonId() < 0) {
            throw new CcighgoException("season infi is required to update the record");
         }
         if (hostFamilyReferences.getFieldsFilled() == 0 || hostFamilyReferences.getFieldsFilled() < 0) {
            throw new CcighgoException("number of fields filled is mandatory to update the record");
         }
         List<HostFamilyReference> hfRefList = hostFamilyReferenceRepository.findBySeasonId(hostFamilyReferences.getSeasonId());
         if (hfRefList != null) {
            for (HostFamilyReference hRef : hfRefList) {
               for (Reference ref : hostFamilyReferences.getReferences()) {
                  if (hRef.getHostFamilyReferenceId() == ref.getHostFamilyReferenceId()) {
                     hRef.setActive(CCIConstants.ACTIVE);
                     hRef.setFirstName(ref.getFirstName());
                     hRef.setLastName(ref.getLastName());
                     hRef.setAddress(ref.getStreet());
                     hRef.setCity(ref.getCity());
                     hRef.setLookupUsstate(stateRepository.findOne(ref.getState()));
                     hRef.setZipCode(ref.getZip());
                     hRef.setPhone(ref.getPhone());
                     hRef.setRelationship(ref.getRelationshipToFamily());
                     hRef.setPersonNotRelatedToBlood(ref.isBloodRelated() ? CCIConstants.INACTIVE : CCIConstants.ACTIVE);
                     hRef.setSecondReferenceRelationtoFirst(hostFamilyReferences.isSecondReferenceRelatedToFirst() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     hRef.setModifiedBy(hostFamilyReferences.getLoginId());
                     hRef.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                     hostFamilyReferenceRepository.saveAndFlush(hRef);
                  }
               }
            }
            HostFamilyGeneralQuestion questions = hostFamilyGeneralQuestionRepository.getBySeasonId(hostFamilyReferences.getSeasonId());
            HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(hostFamilyReferences.getSeasonId(),
                  Integer.valueOf(applicationCategoryId));
            hostFamilySeasonCategory.setFilledMandatoryFields(hostFamilyReferences.getFieldsFilled());
            hostFamilySeasonCategoryRepository.saveAndFlush(hostFamilySeasonCategory);
            if (questions != null) {
               questions.setPreviousHostingWithCCI(hostFamilyReferences.isPreviouslyHosted() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               questions.setHostFamilySeason(hostFamilySeasonRepository.findOne(hostFamilyReferences.getSeasonId()));
               questions.setInternet(hostFamilyReferences.getInternet());
               questions.setInternetOthers(hostFamilyReferences.getOtherWebsites());
               questions.setCommunity(hostFamilyReferences.getCommunity());
               questions.setCommunityEvent(hostFamilyReferences.getEvent());
               questions.setCommunityMagazine(hostFamilyReferences.getMagazine());
               questions.setCommunityOthers(hostFamilyReferences.getOtherCommunity());
               hostFamilyGeneralQuestionRepository.saveAndFlush(questions);
               upadatedObject = getHFReference(String.valueOf(hostFamilyReferences.getSeasonId()), applicationCategoryId);
               upadatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               upadatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
                     "no question details found to update"));
            }
         } else {
            upadatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
                  "no reference details found to update"));
         }
      } catch (CcighgoException e) {
         upadatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_UPDATE_HF_REFERENCE.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return upadatedObject;
   }

   @Override
   @Transactional(readOnly = true)
   public HostFamilyReferences getHFReference(String hfSeasonId, String applicationCategoryId) {
      HostFamilyReferences hfReferences = new HostFamilyReferences();
      try {
         if (hfSeasonId == null || applicationCategoryId == null) {
            throw new CcighgoException("invalid search parameters");
         }
         List<Reference> references = new ArrayList<Reference>();
         List<HostFamilyReference> hfRefList = hostFamilyReferenceRepository.findBySeasonId(Integer.valueOf(hfSeasonId));
         for (HostFamilyReference hfr : hfRefList) {
            Reference r = new Reference();
            r.setHostFamilyReferenceId(hfr.getHostFamilyReferenceId());
            r.setFirstName(hfr.getFirstName() != null ? hfr.getFirstName() : CCIConstants.EMPTY);
            r.setLastName(hfr.getLastName() != null ? hfr.getLastName() : CCIConstants.EMPTY);
            r.setStreet(hfr.getAddress() != null ? hfr.getAddress() : CCIConstants.EMPTY);
            r.setCity(hfr.getCity() != null ? hfr.getCity() : CCIConstants.EMPTY);
            if (hfr.getLookupUsstate() != null) {
               r.setState(hfr.getLookupUsstate().getUsStatesId());
            }
            r.setZip(hfr.getZipCode() != null ? hfr.getZipCode() : CCIConstants.EMPTY);
            r.setPhone(hfr.getPhone() != null ? hfr.getPhone() : CCIConstants.EMPTY);
            r.setRelationshipToFamily(hfr.getRelationship() != null ? hfr.getRelationship() : CCIConstants.EMPTY);
            r.setBloodRelated(hfr.getPersonNotRelatedToBlood().equals(CCIConstants.ACTIVE) ? false : true);
            hfReferences.setSecondReferenceRelatedToFirst(hfr.getSecondReferenceRelationtoFirst().equals(CCIConstants.ACTIVE) ? true : false);
            references.add(r);
         }
         List<HostFamilyGeneralQuestion> questionList = hostFamilyGeneralQuestionRepository.getListBySeasonId(Integer.valueOf(hfSeasonId));
         HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(Integer.valueOf(hfSeasonId),
               Integer.valueOf(applicationCategoryId));
         if (questionList != null) {
            for (HostFamilyGeneralQuestion question : questionList) {
               if (question.getHostFamilySeason().getHostFamilySeasonId().equals(Integer.valueOf(hfSeasonId))) {
                  hfReferences.setGoId(question.getHostFamilySeason().getHostFamily().getHostFamilyGoId());
                  hfReferences.setSeasonId(question.getHostFamilySeason().getSeason().getSeasonId());
                  hfReferences.setHostFamilyGenQuestionId(question.getHostFamilyGeneralQuestionsId());
                  hfReferences.getReferences().addAll(references);
                  hfReferences.setPreviouslyHosted(question.getPreviousHostingWithCCI().equals(CCIConstants.ACTIVE) ? true : false);
                  hfReferences.setInternet(question.getInternet() != null ? question.getInternet() : CCIConstants.EMPTY);
                  hfReferences.setOtherWebsites(question.getInternetOthers() != null ? question.getInternetOthers() : CCIConstants.EMPTY);
                  hfReferences.setCommunity(question.getCommunity() != null ? question.getCommunity() : CCIConstants.EMPTY);
                  hfReferences.setEvent(question.getCommunityEvent() != null ? question.getCommunityEvent() : CCIConstants.EMPTY);
                  hfReferences.setMagazine(question.getCommunityMagazine() != null ? question.getCommunityMagazine() : CCIConstants.EMPTY);
                  hfReferences.setOtherCommunity(question.getCommunityOthers() != null ? question.getCommunityOthers() : CCIConstants.EMPTY);
                  hfReferences.setPercentUpdate(CCIUtils.getFormFilledPercentage(hostFamilySeasonCategory.getTotalMandatoryFields(),
                        hostFamilySeasonCategory.getFilledMandatoryFields()));
               }
            }
         } else {
            hfReferences.setGoId(0);
            hfReferences.setSeasonId(0);
            hfReferences.setHostFamilyGenQuestionId(0);
            hfReferences.getReferences().addAll(references);
            hfReferences.setPreviouslyHosted(false);
            hfReferences.setInternet(CCIConstants.EMPTY);
            hfReferences.setOtherWebsites(CCIConstants.EMPTY);
            hfReferences.setCommunity(CCIConstants.EMPTY);
            hfReferences.setEvent(CCIConstants.EMPTY);
            hfReferences.setMagazine(CCIConstants.EMPTY);
            hfReferences.setOtherCommunity(CCIConstants.EMPTY);
            hfReferences
                  .setPercentUpdate(CCIUtils.getFormFilledPercentage(hostFamilySeasonCategory.getTotalMandatoryFields(), hostFamilySeasonCategory.getFilledMandatoryFields()));
         }
         hfReferences.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         hfReferences.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_GET_HF_REFERENCE.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hfReferences;
   }

   @Override
   @Transactional(readOnly = true)
   public HFBackgroundCheck getHFBackgroundDetails(String hfSeasonId) {
      HFBackgroundCheck backgroundCheck = new HFBackgroundCheck();
      try {
         if (hfSeasonId == null) {
            throw new CcighgoException("invalid search parameters");
         }
         List<HostFamilyBackground> hfBackground = hostFamilyBackgroundRepository.getBySeasonId(Integer.valueOf(hfSeasonId));
         int noOfMembers = 0;
         int completed = 0;
         if (hfBackground != null && hfBackground.size() > 0) {
            noOfMembers = hfBackground.size();
            List<Member> members = new ArrayList<Member>();
            for (HostFamilyBackground hfb : hfBackground) {
               Member m = new Member();
               m.setFirstName(hfb.getFirstName());
               m.setLastName(hfb.getLastName());
               m.setBirthDate(DateUtils.getUSDate(hfb.getBirthDate()));
               m.setRelationship(hfb.getRelationshipToHostParent());
               m.setBackgroundCheckStatus(hfb.getStatus().equals(CCIConstants.ACTIVE) ? COMPLETED : NOT_COMPLETED);
               if (hfb.getStatus().equals(CCIConstants.ACTIVE)) {
                  completed += 1;
               }
               m.setDateCheckedByCCI(DateUtils.getUSDate(hfb.getDateCheckedByCCI()));
               members.add(m);
            }
            // percent complete calculated based on how many members completed
            // background check over total number of members.
            if (noOfMembers > 0 && completed > 0) {
               backgroundCheck.setPercentUpdate(CCIUtils.getFormFilledPercentage(noOfMembers, completed));
            } else {
               backgroundCheck.setPercentUpdate(new Double(0));
            }
            backgroundCheck.setHfSeasonId(Integer.valueOf(hfSeasonId));
            backgroundCheck.getMembers().addAll(members);
            backgroundCheck.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            backgroundCheck.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         backgroundCheck.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_GET_HF_BACKGROUND_DETAILS.getValue(),
               e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return backgroundCheck;
   }

   @Override
   @Transactional(readOnly = true)
   public HFApplicationProgress getHFApplicationProgress(String hfSeasonId) {
      HFApplicationProgress appProgress = new HFApplicationProgress();
      try {
         if (hfSeasonId == null) {
            throw new CcighgoException("invalid search parameters");
         }
         List<HostFamilySeasonCategory> categoriesList = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonId(Integer.valueOf(hfSeasonId));
         if (categoriesList != null) {
            List<Progress> sectionList = new ArrayList<Progress>();
            boolean isFamilyBasicComplete = false;
            boolean isFamilyLifeStyleComplete = false;
            boolean isHouseHomeComplete = false;
            boolean isCommunityComplete = false;
            boolean isWhyHostComplete = false;
            boolean isPhotoAlbumComplete = false;
            boolean isReferenceComplete = false;
            boolean isBackgroundComplete = false;
            for (HostFamilySeasonCategory cat : categoriesList) {
               if (cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoriesId() == CCIConstants.FAMILY_BASICS) {
                  Progress familyBasics = new Progress();
                  familyBasics.setCategoryId(cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoriesId());
                  familyBasics.setCategoryName(cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoryName());
                  Double fmBasicCompletion = CCIUtils.getFormFilledPercentage(cat.getTotalMandatoryFields(), cat.getFilledMandatoryFields());
                  if (fmBasicCompletion.equals(new Double(100.0))) {
                     isFamilyBasicComplete = true;
                  }
                  familyBasics.setPercentFilled(fmBasicCompletion);
                  sectionList.add(familyBasics);
               }
               if (cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoriesId() == CCIConstants.FAMILY_LIFESTYLE) {
                  Progress familyLifeStyle = new Progress();
                  familyLifeStyle.setCategoryId(cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoriesId());
                  familyLifeStyle.setCategoryName(cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoryName());
                  Double fmLfsCompletion = CCIUtils.getFormFilledPercentage(cat.getTotalMandatoryFields(), cat.getFilledMandatoryFields());
                  if (fmLfsCompletion.equals(new Double(100.0))) {
                     isFamilyLifeStyleComplete = true;
                  }
                  familyLifeStyle.setPercentFilled(fmLfsCompletion);
                  sectionList.add(familyLifeStyle);
               }
               if (cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoriesId() == CCIConstants.HOUSE_HOME) {
                  Progress houseAndHome = new Progress();
                  houseAndHome.setCategoryId(cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoriesId());
                  houseAndHome.setCategoryName(cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoryName());
                  Double hhCompletion = CCIUtils.getFormFilledPercentage(cat.getTotalMandatoryFields(), cat.getFilledMandatoryFields());
                  if (hhCompletion.equals(new Double(100.0))) {
                     isHouseHomeComplete = true;
                  }
                  houseAndHome.setPercentFilled(hhCompletion);
                  sectionList.add(houseAndHome);
               }
               if (cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoriesId() == CCIConstants.COMMUNITY) {
                  Progress communityAndSchool = new Progress();
                  communityAndSchool.setCategoryId(cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoriesId());
                  communityAndSchool.setCategoryName(cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoryName());
                  Double communityCompletion = CCIUtils.getFormFilledPercentage(cat.getTotalMandatoryFields(), cat.getFilledMandatoryFields());
                  if (communityCompletion.equals(new Double(100.0))) {
                     isCommunityComplete = true;
                  }
                  communityAndSchool.setPercentFilled(communityCompletion);
                  sectionList.add(communityAndSchool);
               }
               if (cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoriesId() == CCIConstants.WHY_HOST) {
                  Progress whyHost = new Progress();
                  whyHost.setCategoryId(cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoriesId());
                  whyHost.setCategoryName(cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoryName());
                  Double whyHostCompletion = CCIUtils.getFormFilledPercentage(cat.getTotalMandatoryFields(), cat.getFilledMandatoryFields());
                  if (whyHostCompletion.equals(new Double(100.0))) {
                     isWhyHostComplete = true;
                  }
                  whyHost.setPercentFilled(whyHostCompletion);
                  sectionList.add(whyHost);
               }
               if (cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoriesId() == CCIConstants.PHOTO_ALBUM) {
                  Progress photoAlbum = new Progress();
                  photoAlbum.setCategoryId(cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoriesId());
                  photoAlbum.setCategoryName(cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoryName());
                  Double photoAlbumCompletion = CCIUtils.getFormFilledPercentage(cat.getTotalMandatoryFields(), cat.getFilledMandatoryFields());
                  if (photoAlbumCompletion.equals(new Double(100.0))) {
                     isPhotoAlbumComplete = true;
                  }
                  photoAlbum.setPercentFilled(photoAlbumCompletion);
                  sectionList.add(photoAlbum);
               }
               if (cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoriesId() == CCIConstants.REFRENCES) {
                  Progress references = new Progress();
                  references.setCategoryId(cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoriesId());
                  references.setCategoryName(cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoryName());
                  Double refCompletion = CCIUtils.getFormFilledPercentage(cat.getTotalMandatoryFields(), cat.getFilledMandatoryFields());
                  if (refCompletion.equals(new Double(100.0))) {
                     isReferenceComplete = true;
                  }
                  references.setPercentFilled(refCompletion);
                  sectionList.add(references);
               }
               if (cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoriesId() == CCIConstants.BG_CHECK) {
                  Progress backgroundCheck = new Progress();
                  backgroundCheck.setCategoryId(cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoriesId());
                  backgroundCheck.setCategoryName(cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoryName());
                  List<HostFamilyBackground> hfBackground = hostFamilyBackgroundRepository.getBySeasonId(Integer.valueOf(hfSeasonId));
                  int noOfMembers = 0;
                  int completed = 0;
                  Double bgCheck = new Double(0);
                  if (hfBackground != null && hfBackground.size() > 0) {
                     noOfMembers = hfBackground.size();
                     for (HostFamilyBackground hfb : hfBackground) {
                        if (hfb.getStatus().equals(CCIConstants.ACTIVE)) {
                           completed += 1;
                        }
                     }
                     if (noOfMembers > 0 && completed > 0) {
                        bgCheck = CCIUtils.getFormFilledPercentage(noOfMembers, completed);
                     }
                     if (bgCheck.equals(new Double(100.0))) {
                        isBackgroundComplete = true;
                     }
                     backgroundCheck.setPercentFilled(bgCheck);
                  }
                  sectionList.add(backgroundCheck);
               }
               if (cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoriesId() == CCIConstants.SUBMIT_APPLICATION) {
                  Progress submitApplication = new Progress();
                  submitApplication.setCategoryId(cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoriesId());
                  submitApplication.setCategoryName(cat.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoryName());
                  if (isFamilyBasicComplete && isFamilyLifeStyleComplete && isHouseHomeComplete && isCommunityComplete && isWhyHostComplete && isPhotoAlbumComplete
                        && isReferenceComplete && isBackgroundComplete) {
                     submitApplication.setPercentFilled(new Double(100.0));
                  } else {
                     submitApplication.setPercentFilled(new Double(0));
                  }
                  sectionList.add(submitApplication);
               }
            }
            appProgress.setHfSeasonId(Integer.valueOf(hfSeasonId));
            appProgress.getSections().addAll(sectionList);
            appProgress.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            appProgress.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }

      } catch (CcighgoException e) {
         appProgress
               .setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_GET_HF_APPLICATION_PROGRESS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return appProgress;
   }

   @Override
   @Transactional
   public Response submitApplication(HFSubmitApplication application) {
      Response resp = new Response();
      try {
         if (application.getSignature() == null) {
            throw new CcighgoException("signature is mandatory");
         }
         HostFamilySeason hfSeason = hostFamilySeasonRepository.findOne(application.getSeasonId());
         if (hfSeason != null) {
            hfSeason.setSignature(application.getSignature());
            hfSeason.setIsDoublePlacement(application.isDbHostingAgreementCheck() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            hfSeason.setModifiedBy(application.getLoginId());
            hostFamilySeasonRepository.saveAndFlush(hfSeason);
            resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_HF_SUBMIT_APPLICATION.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return resp;
   }

   public WSDefaultResponse changeProfilePicture(ChangeHostFamilyProfilePicParam param) {
      WSDefaultResponse hp = new WSDefaultResponse();
      try {
         HostFamily hf = hostFamilyRepository.findOne(param.getHostFamilyGoId());
         hf.setPhoto(param.getPhotoUrl());
         hostFamilyRepository.saveAndFlush(hf);
         hp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         hp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_UPDATE_HF_PROFILE_PHOTO.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hp;
   }

   @Override
   public HFAirportList hfAirportList() {
      HFAirportList hp = new HFAirportList();
      try {
         List<Airport> airports = airportRepository.getAirportInUSA();
         for (Airport airport : airports) {
            HFAirportData a = new HFAirportData();
            a.setAirportCity(airport.getAirportCity());
            a.setAirportCode(airport.getAirportCode());
            a.setAirportName(airport.getAirportName());
            a.setInternational(airport.getIsInternational() == CCIConstants.TRUE_BYTE);
            a.setAirportId(airport.getAirportId());
            hp.getAirportInfo().add(a);
         }
         hp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         hp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_GET_HF_AIRPORT_LIST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hp;
   }

   @Override
   public WSDefaultResponse removeHostFamilyAirport(int airportId) {
      WSDefaultResponse hp = new WSDefaultResponse();
      try {
         hostFamilyAirportRepository.delete(airportId);
         hp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         hp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_REMOVE_HF_AIRPORT.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hp;
   }

   @Override
   public WSDefaultResponse removeHostFamilyPet(int petId) {
      WSDefaultResponse hp = new WSDefaultResponse();
      try {
         hostFamilyPetRepository.delete(petId);
         hp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         hp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_REMOVE_HF_PET.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hp;
   }

   @Override
   public WSDefaultResponse removeHostFamilyAdult(int adultId) {
      WSDefaultResponse hp = new WSDefaultResponse();
      try {
         hfMemberRepository.delete(adultId);
         hp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         hp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_REMOVE_HF_ADULT.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hp;
   }

   @Override
   @Transactional(readOnly = true)
   public HFProfile viewHFProfile(int hfSeasonId, int loginId) {
      HFProfile hfProfile = new HFProfile();
      try {
         HostFamily hostFamily = hostFamilyRepository.findBySeasonId(hfSeasonId);
         if (hostFamily != null) {

            hfProfile.setPrimaryPhone(hostFamily.getPhone());
            hfProfile.setPicUrl(hostFamily.getPhoto());
            hfProfile.setFirstName(hostFamily.getFirstName());
            hfProfile.setLastName(hostFamily.getLastName());
            Login hfLogin = loginRepository.findByCCIGoId(hostFamily.getHostFamilyGoId());
            if (hfLogin != null) {
               hfProfile.setEmail(hfLogin.getEmail());
               hfProfile.setUserName(hfLogin.getLoginName());
            }
            hfProfile.setEmergencyPhone(hostFamily.getEmergencyPhone());
            hfProfile.setEmergencyContact(hostFamily.getEmergencyContact());
            hfProfile.setPhysicalAddress(hostFamily.getPhysicalAddress());
            hfProfile.setPhysicalCity(hostFamily.getPhysicalCity());
            HFState pState = new HFState();
            if (hostFamily.getLookupUsstate2() != null) {
               pState.setHfStateId(hostFamily.getLookupUsstate2().getUsStatesId());
               pState.setHfState(hostFamily.getLookupUsstate2().getStateName());
            }
            hfProfile.setPhysicalState(pState);
            hfProfile.setPhysicalZip(hostFamily.getPhysicalZipCode());
            hfProfile.setMailingAddress(hostFamily.getMailingAddress());
            hfProfile.setMailingCity(hostFamily.getMailingCity());
            HFState mState = new HFState();
            if (hostFamily.getLookupUsstate1() != null) {
               mState.setHfStateId(hostFamily.getLookupUsstate2().getUsStatesId());
               mState.setHfState(hostFamily.getLookupUsstate2().getStateName());
            }
            hfProfile.setMailingState(mState);
            hfProfile.setMailingZip(hostFamily.getMailingZipCode());
            hfProfile.setRecieveEmail(hostFamily.getReceiveEmails().equals(CCIConstants.ACTIVE) ? true : false);
            hfProfile.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            hfProfile.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         hfProfile.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_VIEW_HF_PROFILE.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hfProfile;
   }

   public HFFamilyMember getHFMembers(Integer seasonId) {
      HFFamilyMember hp = new HFFamilyMember();
      try {
         List<HostFamilyMember> members = hfMemberRepository.getHFMember(seasonId);
         if (members != null) {
            for (HostFamilyMember hostFamilyMember : members) {
               HFFamilyMemberDetails d = new HFFamilyMemberDetails();
               d.setFirstName(hostFamilyMember.getFirstName());
               d.setLastName(hostFamilyMember.getLastName());
               d.setHostfamilyMemberId(hostFamilyMember.getHostFamilyMemberId());
               hp.getFamilyMembers().add(d);
            }
         }
         hp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         hp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_FETCH_HF_MEMBERS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hp;
   }

   @Override
   public HostFamilyMembers getHFDetails(Integer hostfamilySeasonId) {
      HostFamilyMembers hfM = new HostFamilyMembers();
      try {
         if (hostfamilySeasonId == null) {
            throw new CcighgoException("Id of Host family is required to fetch details.");
         }

         List<HostFamilyMember> members = hfMemberRepository.getHFMember(hostfamilySeasonId);
         if (members != null && members.size() != 0) {
            for (HostFamilyMember hf : members) {
               HostFamilyMemberDetails hfMD = new HostFamilyMemberDetails();
               hfMD.setRoomMateName(hf.getFirstName() + " " + hf.getLastName());
               hfMD.setHostfamilyMemberId(hf.getHostFamilyMemberId());
               hfMD.setGenderId(hf.getLookupGender().getGenderId());
               hfMD.setGender(hf.getLookupGender().getGenderName());
               if (hf.getBirthDate() != null) {
                  DateTime d1 = new DateTime(hf.getBirthDate().getTime());
                  DateTime d2 = new DateTime(new Date().getTime());
                  Years age = Years.yearsBetween(d1, d2);
                  hfMD.setAge(age.getYears());
               }
               hfM.getFamilyMembers().add(hfMD);
            }
            hfM.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            hfM.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }

      } catch (CcighgoException e) {
         hfM.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_FETCH_HF_MEMBERS_DETAILS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hfM;
   }

   @Override
   public HFPetType getHFPetTypeDetails() {
      HFPetType hfPetType = new HFPetType();
      try {
         List<HostFamilyPetType> hfPetTypeList = hostFamilyPetTypeRepository.findAll();
         if (hfPetTypeList != null && !hfPetTypeList.isEmpty()) {
            for (HostFamilyPetType petType : hfPetTypeList) {
               PetDetails pd = new PetDetails();
               pd.setPetTypeId(petType.getHostFamilyPetTypeId());
               pd.setPetTypeName(petType.getHostFamilyPetTypeName());
               hfPetType.getPetDetails().add(pd);
            }
            hfPetType.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            hfPetType.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, HostFamilyCodes.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         hfPetType.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, HostFamilyCodes.ERROR_FETCH_HF_PETTYPE.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hfPetType;

   }

   @Override
   public HFSeasonList getSeasonList(Integer hostFamilyGoId) {
      HFSeasonList hfs = new HFSeasonList();
      try {

         Query query = em.createNativeQuery(SP_HF_SEASON_LIST);
         query.setParameter(1, hostFamilyGoId);
         @SuppressWarnings("unchecked")
         List<Object[]> result = query.getResultList();
         if (result != null && !result.isEmpty()) {
            for (Object[] obj : result) {
               HFSeasonDetails detail = new HFSeasonDetails();
               detail.setSeasonName(String.valueOf(obj[0]));
               detail.setSeasonId(Integer.valueOf(String.valueOf(obj[1])));
               detail.setDepartmentProgramId(Integer.valueOf(String.valueOf(obj[2])));
               detail.setHostFamilySeasonId(Integer.valueOf(String.valueOf(obj[3])));
               hfs.getSeasonDetails().add(detail);
            }
            hfs.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            hfs.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(), messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }

      } catch (CcighgoException e) {
         hfs.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_HF_DETAILS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hfs;
   }

   @Override
   public HFParticipantList getParticipantsList(Integer hostFamilyGoId, String category) {
      HFParticipantList hfs = new HFParticipantList();
      try {
         Query query = em.createNativeQuery(SP_HF_PARTICIPANT_LIST);
         query.setParameter(1, hostFamilyGoId);
         query.setParameter(2, category);
         @SuppressWarnings("unchecked")
         List<Object[]> result = query.getResultList();
         if (result != null && !result.isEmpty()) {
            for (Object[] obj : result) {
               ParticipantDetails details = new ParticipantDetails();
               details.setPhoto(String.valueOf(obj[0]));
               details.setFirstName(String.valueOf(obj[1]));
               details.setLastName(String.valueOf(obj[2]));
               details.setParticipantGoId(String.valueOf(obj[3]));
               details.setRanking(Integer.valueOf(String.valueOf(obj[4])));
               details.setProgram(String.valueOf(obj[5]));
               details.setProgramOption(String.valueOf(obj[6]));
               details.setProgramStartDate(String.valueOf(obj[7]));
               details.setProgramEndDate(String.valueOf(obj[8]));
               details.setAge(String.valueOf(obj[9]));
               details.setCountryId(String.valueOf(obj[10]));
               details.setCountryName(String.valueOf(obj[11]));
               details.setCountryFlag(String.valueOf(obj[12]));
               details.setGenderId(String.valueOf(obj[13]));
               details.setGender(String.valueOf(obj[14]));
               hfs.getParticipants().add(details);
            }
            hfs.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            hfs.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(), messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }

      } catch (CcighgoException e) {
         hfs.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_HF_DETAILS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hfs;
   }

   @Override
   public HFParticipantDetail getParticipantDetail(Integer valueOf) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public HFP2WorkQueueType getWorkQueueType(String roleType) {
      HFP2WorkQueueType pwt = new HFP2WorkQueueType();
      // try {
      // List<AdminWorkQueueType> types =
      // adminWorkQueueTypeRepository.findTypesByPartnerRole(roleType);
      // if (types != null) {
      // for (AdminWorkQueueType adminWorkQueueType : types) {
      // AdminPartnerWorkQueueTypeDetail newType = new
      // AdminPartnerWorkQueueTypeDetail();
      // newType.setTypeId(adminWorkQueueType.getAdminWQTypeId());
      // newType.setTypeName(adminWorkQueueType.getAdminWQTypeName());
      // pwt.getWorkQueueType().add(newType);
      // }
      // pwt.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS,
      // CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
      // messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      // } else {
      // pwt.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD_IN_DB,
      // CCIConstants.TYPE_INFO, CCIConstants.NO_DATA_CODE,
      // messageUtil.getMessage(CCIConstants.NO_RECORD)));
      // }
      // } catch (Exception e) {
      // ExceptionUtil.logException(e, LOGGER);
      // pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE,
      // CCIConstants.TYPE_ERROR, PartnerCodes.NO_WOEKQUEUE_TYPE.getValue(),
      // messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_TYPE)));
      // LOGGER.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_TYPE));
      // }
      return pwt;
   }

   @Override
   public HFP2WorkQueueCategory getWorkQueueCategory(int parseInt, int parseInt2) {
      HFP2WorkQueueCategory pwqc = new HFP2WorkQueueCategory();
      // try {
      // List<AdminWorkQueueCategory> categories =
      // adminWorkQueueCategoryRepository.findAllCategoriesByTypeId(adminWorkQueueTypeId);
      // if (categories != null) {
      // for (AdminWorkQueueCategory adminWorkQueueCategory : categories) {
      // AdminPartnerWorkQueueCategoryDetail newCategory = new
      // AdminPartnerWorkQueueCategoryDetail();
      // newCategory.setAdminWorkQueueTypeId(adminWorkQueueCategory.getAdminWorkQueueType().getAdminWQTypeId());
      // newCategory.setCategoryId(adminWorkQueueCategory.getAdminWorkQueueCategoryId());
      // newCategory.setCategoryName(adminWorkQueueCategory.getAdminWorkQueueCategoryName());
      // if
      // (adminWorkQueueCategory.getAdminWorkQueueType().getAdminWQTypeName().equalsIgnoreCase(WORK_QUEUE_TYPE_APPLICATION))
      // {
      // if (newCategory.getCategoryName().equals(CATEGORY_NAME_SUBMITTED)) {
      // newCategory.setServiceUrl(CCIConstants.SERVICE_URL_WORK_QUEUE_CATEGORY_SUBMITTED_TYPE_APPLICATION_1);
      // } else {
      // newCategory.setServiceUrl(CCIConstants.SERVICE_URL_NDY);
      // }
      // } else {
      // newCategory.setServiceUrl(CCIConstants.SERVICE_URL_NDY);
      // }
      // Login login = loginRepository.findByLoginId(Integer.valueOf(userId));
      // int goId = login.getGoIdSequence().getGoId().intValue();
      // AdminWorkQueueCategoryAggregate categoryAggregate =
      // adminWorkQueueCategoryAggregateRepository.findAggregateValueForCategory(adminWorkQueueTypeId,
      // adminWorkQueueCategory.getAdminWorkQueueCategoryId(), goId);
      // if (categoryAggregate != null) {
      // newCategory.setCategoryAggregate(categoryAggregate.getAdminWQCategoryAggregate());
      // }
      // pwqc.getAdminWorkQueueCategory().add(newCategory);
      // }
      // pwqc.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS,
      // CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
      // messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      // } else {
      // pwqc.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD_IN_DB,
      // CCIConstants.TYPE_INFO, CCIConstants.NO_DATA_CODE,
      // messageUtil.getMessage(CCIConstants.NO_RECORD)));
      // }
      // } catch (Exception e) {
      // ExceptionUtil.logException(e, logger);
      // pwqc.setStatus(componentUtils.getStatus(CCIConstants.FAILURE,
      // CCIConstants.TYPE_ERROR, PartnerCodes.NO_WOEKQUEUE_CATEGORY.getValue(),
      // messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_CATEGORY)));
      // logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_CATEGORY));
      // }
      return pwqc;
   }

   @Override
   public HFFieldNetworkInformationDetail fetchFieldNetworkInformation(String hfSeasonId, String hfGoId) {
      HFFieldNetworkInformationDetail info = new HFFieldNetworkInformationDetail();
      try {
         // TODO
         // Static Data Until Phani send The Business Logic

         HFFieldStaffForFieldNetworkInformation hfInfo = new HFFieldStaffForFieldNetworkInformation();
         hfInfo.setFieldStaffEmail("crespan@gmail.com");
         hfInfo.setFieldStaffLink("www.creospan.com");
         hfInfo.setFieldStaffName("StaffName");
         hfInfo.setFieldStaffPhone("6304880523");
         hfInfo.setFieldStaffPic("pic1.jpg");
         hfInfo.setFieldStaffType("Regional Director");
         info.getFieldStaff().add(hfInfo);

         HFFieldStaffForFieldNetworkInformation hfInfo2 = new HFFieldStaffForFieldNetworkInformation();
         hfInfo2.setFieldStaffEmail("crespan@gmail.com");
         hfInfo2.setFieldStaffLink("www.creospan.com");
         hfInfo2.setFieldStaffName("StaffName");
         hfInfo2.setFieldStaffPhone("6304880523");
         hfInfo2.setFieldStaffPic("pic1.jpg");
         hfInfo2.setFieldStaffType("Regional Director");
         info.getFieldStaff().add(hfInfo2);

         info.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         // } else {
         // info.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD,
         // CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
         // messageUtil.getMessage(CCIConstants.NO_RECORD)));
         // }

      } catch (CcighgoException e) {
         info.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FETCHING_STAFF_FIELD_NETWORK_INFORMATION.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return info;
   }

   @Override
   public HFHostAgainQuestionDetail fetchHostAgainQuestion() {
      HFHostAgainQuestionDetail detail = new HFHostAgainQuestionDetail();
      try {
         // TODO
         detail.setLikeToHostAgain(true);
         detail.getHostFor().add("Full Academic Year");
         detail.getHostFor().add("Short Term");

         detail.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         // } else {
         // detail.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD,
         // CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
         // messageUtil.getMessage(CCIConstants.NO_RECORD)));
         // }

      } catch (CcighgoException e) {
         detail.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FETCHING_HOST_AGAIN_QUESTIONS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return detail;
   }

   @Override
   public WSDefaultResponse updateHostAgainQuestion(HFHostAgainQuestionDetail detail) {
      WSDefaultResponse result = new WSDefaultResponse();
      try {
         // TODO
         result.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         // } else {
         // result.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD,
         // CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
         // messageUtil.getMessage(CCIConstants.NO_RECORD)));
         // }

      } catch (CcighgoException e) {
         // info.setStatus(componentUtils.getStatus(CCIConstants.FAILURE,
         // CCIConstants.TYPE_ERROR, ErrorCode.Exce.getValue(),
         // e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return result;
   }

   @Override
   public WSDefaultResponse updateLandingPage(HFLandingPage hfLandingPage) {
      WSDefaultResponse result = new WSDefaultResponse();
      try {
         // TODO
         // if(true){
         result.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         // } else {
         // result.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD,
         // CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
         // messageUtil.getMessage(CCIConstants.NO_RECORD)));
         // }

      } catch (CcighgoException e) {
         // info.setStatus(componentUtils.getStatus(CCIConstants.FAILURE,
         // CCIConstants.TYPE_ERROR, ErrorCode.Exce.getValue(),
         // e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return result;
   }

   @Override
   public WSDefaultResponse isUserLoggedInForFirstTime(String hfUserId) {
      WSDefaultResponse result = new WSDefaultResponse();
      try {
         // TODO
         // if(true){
         result.setBooleanResult(true);
         result.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         // } else {
         // result.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD,
         // CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
         // messageUtil.getMessage(CCIConstants.NO_RECORD)));
         // }

      } catch (CcighgoException e) {
         // info.setStatus(componentUtils.getStatus(CCIConstants.FAILURE,
         // CCIConstants.TYPE_ERROR, ErrorCode.Exce.getValue(),
         // e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return result;
   }

   @Override
   @Transactional
   public Response updateHFEmail(String loginId, String email) {
      Response resp = new Response();
      if (email == null || email.isEmpty()) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage("valid email is required")));
      }
      try {
         Login login = loginRepository.findByEmail(email);
         if (login != null) {
            resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage("user with same email already exists")));
         } else {
            Login updateLogin = loginRepository.findOne(Integer.valueOf(loginId));
            updateLogin.setEmail(email);
            loginRepository.saveAndFlush(updateLogin);
            resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage("an error occured while updating email")));
      }
      return resp;
   }

   @Override
   @Transactional
   public Response updateHFUserName(String loginId, String userName) {
      Response resp = new Response();
      if (userName == null || userName.isEmpty()) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage("valid user name is required")));
      }
      try {
         Login login = loginRepository.findByLoginName(userName);
         if (login != null) {
            resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage("user with same username already exists")));
         } else {
            Login updateLogin = loginRepository.findOne(Integer.valueOf(loginId));
            updateLogin.setLoginName(userName);
            loginRepository.saveAndFlush(updateLogin);
            resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage("an error occured while updating user name")));
      }
      return resp;
   }

   @Override
   public Response sendTestEmail(String email) {
      Response resp = new Response();
      if (email == null || email.isEmpty()) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage("valid email is required")));
      }
      try{
         String body = "<p>Ciao! </p>" + "<p>This email was sent automatically by Greenheart Online (GO) in response to your request to test email address. </p>" + "<p>"
               + "<p>CCI Greenheart</p>";
         emailService.send(email, CCIConstants.TEST_SUBJECT, body, true);
         resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
               messageUtil.getMessage((CCIConstants.SERVICE_SUCCESS))));
      }catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage("an error occured while sending test email")));
      }
      return resp;
   }

   @Override
   @Transactional
   public Response updatePassword(UpdatedPassword updatedPassword) {
      Response resp = new Response();
      if (updatedPassword == null || updatedPassword.getPassword() == null) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage("valid password is required")));
      }
      try {
         Login updateLogin = loginRepository.findOne(updatedPassword.getLoginId());
         if (updateLogin != null) {
            updateLogin.setPassword(PasswordUtil.hashKey(updatedPassword.getPassword()));
            loginRepository.saveAndFlush(updateLogin);
            resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(), messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage("an error occured while updating passsword")));
      }
      return resp;
   }

}
