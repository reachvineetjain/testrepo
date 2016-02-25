/**
 * 
 */
package com.ccighgo.service.components.hf.participant.application.process;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.Airport;
import com.ccighgo.db.entities.HostFamily;
import com.ccighgo.db.entities.HostFamilyAirport;
import com.ccighgo.db.entities.HostFamilyAnnouncement;
import com.ccighgo.db.entities.HostFamilyDetail;
import com.ccighgo.db.entities.HostFamilyHome;
import com.ccighgo.db.entities.HostFamilyMember;
import com.ccighgo.db.entities.HostFamilyPet;
import com.ccighgo.db.entities.HostFamilyPetType;
import com.ccighgo.db.entities.HostFamilyPhoto;
import com.ccighgo.db.entities.HostFamilySeason;
import com.ccighgo.db.entities.HostFamilySeasonCategory;
import com.ccighgo.db.entities.LookupGender;
import com.ccighgo.db.entities.LookupUSState;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.AirportRepository;
import com.ccighgo.jpa.repositories.GenderRepository;
import com.ccighgo.jpa.repositories.HostFamilyAirportRepository;
import com.ccighgo.jpa.repositories.HostFamilyAnnouncementRepository;
import com.ccighgo.jpa.repositories.HostFamilyAnnouncementResultRepository;
import com.ccighgo.jpa.repositories.HostFamilyDetailRepository;
import com.ccighgo.jpa.repositories.HostFamilyHomeRepository;
import com.ccighgo.jpa.repositories.HostFamilyMemberRepository;
import com.ccighgo.jpa.repositories.HostFamilyPetRepository;
import com.ccighgo.jpa.repositories.HostFamilyPetTypeRepository;
import com.ccighgo.jpa.repositories.HostFamilyPhotosRepository;
import com.ccighgo.jpa.repositories.HostFamilyPhotosTypeRepository;
import com.ccighgo.jpa.repositories.HostFamilyRepository;
import com.ccighgo.jpa.repositories.HostFamilySeasonCategoryRepository;
import com.ccighgo.jpa.repositories.HostFamilySeasonRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.StateRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.HostFamilyMessageConstants;
import com.ccighgo.service.components.hf.participant.application.process.util.HomePageParam;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFAdultDetails;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFAirport;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFApplicationFamilyDetails;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFPets;
import com.ccighgo.service.transport.hostfamily.beans.application.familylifestyle.HFApplicationFamilyLifeStyle;
import com.ccighgo.service.transport.hostfamily.beans.application.homepage.HFAnnouncements;
import com.ccighgo.service.transport.hostfamily.beans.application.homepage.HFApplicationCheckList;
import com.ccighgo.service.transport.hostfamily.beans.application.homepage.HFApplicationCheckListStages;
import com.ccighgo.service.transport.hostfamily.beans.application.homepage.HFHomePage;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.HFApplicationUploadPhotos;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.Photo;
import com.ccighgo.service.transport.hostfamily.beans.application.whyhost.WhyHost;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.CCIUtils;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * @author ravi
 *
 */
@Component
public class HFApplicationImpl implements HFApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(HFApplicationImpl.class);

	@Autowired
	EntityManager entityManager;
	@Autowired
	MessageUtils messageUtil;
	@Autowired
	CommonComponentUtils componentUtils;

	@Autowired
	LoginRepository loginRepository;
	@Autowired
	HostFamilyHomeRepository hostFamilyHomeRepository;
	@Autowired
	HostFamilySeasonRepository hostFamilySeasonRepository;
	@Autowired
	HostFamilySeasonCategoryRepository hostFamilySeasonCategoryRepository;
	@Autowired
	HostFamilyPhotosRepository hostFamilyPhotosRepository;
	@Autowired
	HostFamilyPhotosTypeRepository hostFamilyPhotosTypeRepository;
	@Autowired
	HostFamilyAnnouncementRepository hfAnnouncementRepository;
	@Autowired
	HostFamilyAnnouncementResultRepository hfAnnouncementResultRepository;
	@Autowired
	HostFamilyMemberRepository hfMemberRepository;
	@Autowired
	HostFamilyPhotosRepository hfPhotosRepository;
	@Autowired
	HostFamilySeasonCategoryRepository hfSeasonCategoryRepository;
	@Autowired
	GenderRepository genderRepository;
	@Autowired
	StateRepository stateRepository;
	@Autowired
	HostFamilyRepository hostFamilyRepository;
	@Autowired
	AirportRepository airportRepository;
	@Autowired
	HostFamilyPetTypeRepository hostFamilyPetTypeRepository;
	@Autowired
	HostFamilyPetRepository hostFamilyPetRepository;
	@Autowired
	HostFamilyAirportRepository hostFamilyAirportRepository;

	@Autowired
	HostFamilyDetailRepository hostFamilyDetailRepository;

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
			HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(whyHost.getSeasonId(),
					Integer.valueOf(applicationCategoryId));
			hostFamilySeasonCategory.setFilledMandatoryFields(whyHost.getFieldsFilled());
			hostFamilySeasonCategoryRepository.saveAndFlush(hostFamilySeasonCategory);
			HostFamilyHome hfHome = new HostFamilyHome();
			hfHome.setHostingReason(whyHost.getWhyFamilyInterestedInHosting());
			hfHome.setHopeToLearn(whyHost.getAspectsOfAmericanCultureYouWillShare());
			hfHome.setExtraActivities(whyHost.getActivitiesPlanned());
			hfHome.setLocalCoordinatorOther(whyHost.isWillYouBeWorkingasLCForAnotherOrg() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
			hfHome.setLocalCoordinatorDetails(whyHost.getForWhomYouWillBeWorkingasLCForAnotherOrg());
			hfHome.setHostedOther(whyHost.isHaveYouHostedForAnotherOrg() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
			hfHome.setHostedOtherDetails(whyHost.getIfYesForWhomAndHowManyYears());
			hfHome.setStudentsResponsibilities(whyHost.getFamilyExpectationOnStudentResponsibility());
			hfHome.setHostFamilySeason(hostFamilySeasonRepository.findOne(whyHost.getSeasonId()));
			hfHome.setCreatedBy(whyHost.getLoginId());
			hfHome.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
			hfHome.setModifiedBy(whyHost.getLoginId());
			hfHome.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
			hfHome = hostFamilyHomeRepository.saveAndFlush(hfHome);
			updatedObject = getWhyHost(String.valueOf(hfHome.getHostFamilyHomeId()), String.valueOf(whyHost.getSeasonId()), applicationCategoryId);
			updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
		} catch (CcighgoException e) {
			updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), e.getMessage()));
			LOGGER.error(e.getMessage());
		}
		return updatedObject;
	}

	@Override
	@Transactional(readOnly = true)
	public WhyHost getWhyHost(String hfHomeId, String hfSeasonId, String applicationCategoryId) {
		WhyHost whyHost = new WhyHost();
		try {
			if (hfHomeId == null || hfSeasonId == null || applicationCategoryId == null) {
				throw new CcighgoException("invalid search parameters");
			}
			HostFamilyHome hfHome = hostFamilyHomeRepository.getHFHomebyIdAndSeasonId(Integer.valueOf(hfHomeId), Integer.valueOf(hfSeasonId));
			HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(Integer.valueOf(hfSeasonId),
					Integer.valueOf(applicationCategoryId));
			if (hfHome != null && hostFamilySeasonCategory != null) {
				whyHost.setGoId(hfHome.getHostFamilySeason().getHostFamily().getHostFamilyGoId());
				whyHost.setSeasonId(hfHome.getHostFamilySeason().getHostFamilySeasonId());
				whyHost.setHostFamilyHomeId(hfHome.getHostFamilyHomeId());
				whyHost.setWhyFamilyInterestedInHosting(hfHome.getHostingReason());
				whyHost.setAspectsOfAmericanCultureYouWillShare(hfHome.getHopeToLearn());
				whyHost.setActivitiesPlanned(hfHome.getExtraActivities());
				whyHost.setWillYouBeWorkingasLCForAnotherOrg(hfHome.getLocalCoordinatorOther().equals(CCIConstants.ACTIVE) ? true : false);
				whyHost.setForWhomYouWillBeWorkingasLCForAnotherOrg(hfHome.getLocalCoordinatorDetails());
				whyHost.setHaveYouHostedForAnotherOrg(hfHome.getHostedOther().equals(CCIConstants.ACTIVE) ? true : false);
				whyHost.setIfYesForWhomAndHowManyYears(hfHome.getHostedOtherDetails());
				whyHost.setFamilyExpectationOnStudentResponsibility(hfHome.getStudentsResponsibilities());
				whyHost.setPercentUpdate(CCIUtils.getFormFilledPercentage(hostFamilySeasonCategory.getTotalMandatoryFields(), hostFamilySeasonCategory.getFilledMandatoryFields()));
				whyHost.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
			} else {
				whyHost.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(), messageUtil.getMessage(CCIConstants.NO_RECORD)));
			}
		} catch (CcighgoException e) {
			whyHost.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), e.getMessage()));
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
				HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(whyHost.getSeasonId(),
						Integer.valueOf(applicationCategoryId));
				hostFamilySeasonCategory.setFilledMandatoryFields(whyHost.getFieldsFilled());
				hostFamilySeasonCategoryRepository.saveAndFlush(hostFamilySeasonCategory);
				hfHome.setHostingReason(whyHost.getWhyFamilyInterestedInHosting());
				hfHome.setHopeToLearn(whyHost.getAspectsOfAmericanCultureYouWillShare());
				hfHome.setExtraActivities(whyHost.getActivitiesPlanned());
				hfHome.setLocalCoordinatorOther(whyHost.isWillYouBeWorkingasLCForAnotherOrg() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
				hfHome.setLocalCoordinatorDetails(whyHost.getForWhomYouWillBeWorkingasLCForAnotherOrg());
				hfHome.setHostedOther(whyHost.isHaveYouHostedForAnotherOrg() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
				hfHome.setHostedOtherDetails(whyHost.getIfYesForWhomAndHowManyYears());
				hfHome.setStudentsResponsibilities(whyHost.getFamilyExpectationOnStudentResponsibility());
				hfHome.setModifiedBy(whyHost.getLoginId());
				hfHome.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
				hfHome = hostFamilyHomeRepository.saveAndFlush(hfHome);
				updatedObject = getWhyHost(String.valueOf(hfHome.getHostFamilyHomeId()), String.valueOf(whyHost.getSeasonId()), applicationCategoryId);
				updatedObject
						.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
			} else {
				updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(), messageUtil.getMessage(CCIConstants.NO_RECORD)));
			}
		} catch (CcighgoException e) {
			updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), e.getMessage()));
			LOGGER.error(e.getMessage());
		}
		return updatedObject;
	}

	@Override
	public HFApplicationUploadPhotos uploadHFPhotos(HFApplicationUploadPhotos hfApplicationUploadPhotos) {
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
			if (hfApplicationUploadPhotos.getPhotos() != null) {
				for (Photo ph : hfApplicationUploadPhotos.getPhotos().getPhotos()) {
					HostFamilyPhoto hfPhoto = new HostFamilyPhoto();
					hfPhoto.setHostFamilySeason(hostFamilySeasonRepository.findOne(hfApplicationUploadPhotos.getHfSeasonId()));
					hfPhoto.setHostFamilyPhotosType(hostFamilyPhotosTypeRepository.findOne(ph.getType().getTypeId()));
					hfPhoto.setFileName(ph.getName());
					hfPhoto.setFilePath(ph.getPhotoUrl());
					hfPhoto.setPhotoName(ph.getName());
					hfPhoto.setDescription(ph.getDescription() != null ? ph.getDescription() : "");
					hfPhoto.setIsOptional("0");
					hfPhoto.setCreatedBy(hfApplicationUploadPhotos.getLoginId());
					hfPhoto.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
					hfPhoto.setActive(CCIConstants.ACTIVE);
					hfPhoto.setSubmittedToCCI(CCIConstants.INACTIVE);
					hfPhoto.setApprovedByCCI(CCIConstants.INACTIVE);
					hfPhoto.setRejectedByCCI(CCIConstants.INACTIVE);
					hostFamilyPhotoList.add(hfPhoto);
				}
			}
			if (hfApplicationUploadPhotos.getOptionalPhotos() != null) {
				for (Photo ph : hfApplicationUploadPhotos.getOptionalPhotos().getPhotos()) {
					HostFamilyPhoto hfPhoto = new HostFamilyPhoto();
					hfPhoto.setHostFamilySeason(hostFamilySeasonRepository.findOne(hfApplicationUploadPhotos.getHfSeasonId()));
					hfPhoto.setHostFamilyPhotosType(hostFamilyPhotosTypeRepository.findOne(ph.getType().getTypeId()));
					hfPhoto.setFileName(ph.getName());
					hfPhoto.setFilePath(ph.getPhotoUrl());
					hfPhoto.setPhotoName(ph.getName());
					hfPhoto.setDescription(ph.getDescription() != null ? ph.getDescription() : "");
					hfPhoto.setIsOptional("1");
					hfPhoto.setCreatedBy(hfApplicationUploadPhotos.getLoginId());
					hfPhoto.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
					hfPhoto.setActive(CCIConstants.ACTIVE);
					hfPhoto.setSubmittedToCCI(CCIConstants.INACTIVE);
					hfPhoto.setApprovedByCCI(CCIConstants.INACTIVE);
					hfPhoto.setRejectedByCCI(CCIConstants.INACTIVE);
					hostFamilyPhotoList.add(hfPhoto);
				}
			}
			hostFamilyPhotosRepository.save(hostFamilyPhotoList);
			hostFamilyPhotosRepository.flush();
			updatedObject = hfApplicationUploadPhotos;
			updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
		} catch (CcighgoException e) {
			updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), e.getMessage()));
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

			} else {
				photoList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(), messageUtil.getMessage(CCIConstants.NO_RECORD)));
			}
		} catch (CcighgoException e) {
			photoList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), e.getMessage()));
			LOGGER.error(e.getMessage());
		}
		return photoList;
	}

	@Override
	@Transactional
	@Modifying
	public Response deletePhoto(String photoId) {
		Response resp = new Response();
		try {
			if (photoId == null) {
				throw new CcighgoException("invalid photo id");
			}
			hostFamilyPhotosRepository.delete(Integer.valueOf(photoId));
			resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
		} catch (CcighgoException e) {
			resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), "error deleting photo"));
			LOGGER.error(e.getMessage());
		}
		return resp;
	}

	// Pending SP from Phani
	@Override
	public HFHomePage getHostFamilyHome(HomePageParam hpp) {
		HFHomePage hp = new HFHomePage();
		try {
			if (hpp.getHfGoId() == 0 || hpp.getHfGoId() < 0) {
				throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_HF_GOID));
			}
			if (hpp.getLoginId() == 0 || hpp.getLoginId() < 0) {
				throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_OR_NULL_LOGIN_ID));
			}
			// 	Waiting for SP From Phani
//			List<HostFamilyAnnouncement> announcements = hfAnnouncementRepository.findAll();
//			if (announcements != null)
//				for (HostFamilyAnnouncement ann : announcements) {
//					HFAnnouncements announcement = new HFAnnouncements();
//					announcement.setHfAnnouncement(ann.getAnnouncement());
//					if (ann.getCreatedOn() != null)
//						announcement.setAnnouncementDate(DateUtils.getTimestamp(ann.getCreatedOn()));
//					hp.getAnnouncements().add(announcement);
//			}
			HFApplicationCheckList applicationChecklist = new HFApplicationCheckList();
			// TODO
			HostFamilyPhoto hfp = hfPhotosRepository.getHFPhoto(2);
			// TODO
			HostFamilyMember hfm = hfMemberRepository.getHFMember(1);
			applicationChecklist.setApplicantName(hfm.getFirstName());
			applicationChecklist.setPercentage("50");
			applicationChecklist.setPhotoUrl(hfp.getPhotoName());
			// TODO
			List<HostFamilySeasonCategory> hfsc = hfSeasonCategoryRepository.getHFSeasonCategoryBySeasonId(1);
			hp.setApplicationCheckList(applicationChecklist);
			for (HostFamilySeasonCategory hostFamilySeasonCategory : hfsc) {
				HFApplicationCheckListStages stage = new HFApplicationCheckListStages();
				stage.setCategory(hostFamilySeasonCategory.getHostFamilyApplicationCategory().getHostFamilyApplicationCategoryName());
				stage.setStatus(hostFamilySeasonCategory.getStatus() == CCIConstants.ACTIVE ? "Completed" : "Not completed");
				applicationChecklist.getStages().add(stage);
			}

		} catch (CcighgoException e) {
			hp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_HF_HOME_PAGE.getValue(), e.getMessage()));
			LOGGER.error(e.getMessage());
		}
		return hp;
	}

	@Transactional
	@Override
	public WSDefaultResponse saveFamilyBasicData(HFApplicationFamilyDetails hfApplicationFamilyDetails) {
		WSDefaultResponse hp = new WSDefaultResponse();
		try {
			if (hfApplicationFamilyDetails.getLoginId() > 0) {
				throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_OR_NULL_LOGIN_ID));
			}
			if (hfApplicationFamilyDetails.getSeasonId() > 0) {
				throw new CcighgoException("NO Season ID");
			}
			// add photo
			HostFamilySeason season = hostFamilySeasonRepository.findOne(hfApplicationFamilyDetails.getSeasonId());
			if (hfApplicationFamilyDetails.getPhoto() != null) {
				HostFamilyPhoto hfPhoto = new HostFamilyPhoto();
				hfPhoto.setHostFamilySeason(season);
				hfPhoto.setHostFamilyPhotosType(hostFamilyPhotosTypeRepository.findOne(hfApplicationFamilyDetails.getPhoto().getTypeId()));
				hfPhoto.setFileName(hfApplicationFamilyDetails.getPhoto().getName());
				hfPhoto.setFilePath(hfApplicationFamilyDetails.getPhoto().getPhotoUrl());
				hfPhoto.setPhotoName(hfApplicationFamilyDetails.getPhoto().getName());
				hfPhoto.setDescription(hfApplicationFamilyDetails.getPhoto().getDescription());
				hfPhoto.setIsOptional("0");
				hfPhoto.setCreatedBy(hfApplicationFamilyDetails.getLoginId());
				hfPhoto.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
				hfPhoto.setActive(CCIConstants.ACTIVE);
				hfPhoto.setSubmittedToCCI(CCIConstants.INACTIVE);
				hfPhoto.setApprovedByCCI(CCIConstants.INACTIVE);
				hfPhoto.setRejectedByCCI(CCIConstants.INACTIVE);
				hostFamilyPhotosRepository.saveAndFlush(hfPhoto);
			}

			// HOstFamilly Member
			List<HostFamilyMember> listOfMembers = new ArrayList<HostFamilyMember>();
			for (HFAdultDetails member : hfApplicationFamilyDetails.getAdults()) {
				HostFamilyMember hfm = new HostFamilyMember();
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
				hfm.setLivingAtHome(member.isLivesinsideOfHomePartTime() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
				hfm.setLivingAtHomeExplanation(member.getLivingInsideHomeExplanation());
				hfm.setCommunityInvolvement(member.getCommunityInvolvement());
				hfm.setInterests(member.getActivitiesOrInterests());
				hfm.setEmployed(member.getEmployed());
				hfm.setEmployer1(member.getEmployer());
				hfm.setJobTitle1(member.getJobTitle());
				hfm.setContactName1(member.getContactName());
				hfm.setPhone1(member.getJobPhone());
				if (member.getOtherEmployer() != null)
					hfm.setEmployer2(member.getOtherEmployer());
				if (member.getOtherJobTitle() != null)
					hfm.setJobTitle2(member.getOtherJobTitle());
				if (member.getOtherContactName() != null)
					hfm.setContactName2(member.getOtherContactName());
				if (member.getOtherJobPhone() != null)
					hfm.setPhone2(member.getOtherJobPhone());
				hfm.setCreatedBy(hfApplicationFamilyDetails.getLoginId());
				hfm.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
				listOfMembers.add(hfm);
			}
			if (!listOfMembers.isEmpty())
				hfMemberRepository.save(listOfMembers);

			// contact Information
			HostFamily hf = new HostFamily();
			hf.setHaveAHomePhone(hfApplicationFamilyDetails.getContactInfo().isHaveHomePhoneOrLandline() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
			hf.setHomePhone(hfApplicationFamilyDetails.getContactInfo().getPhone());
			hf.setPreferredContactMethodEmail(hfApplicationFamilyDetails.getContactInfo().isPreferEmail() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
			hf.setPreferredContactMethodPhone(hfApplicationFamilyDetails.getContactInfo().isPreferPhone() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
			hf.setPreferredEmail(hfApplicationFamilyDetails.getContactInfo().getContactEmail());
			hf.setPreferredPhone(hfApplicationFamilyDetails.getContactInfo().getContactPhone());
			hf.setEmergencyContact(hfApplicationFamilyDetails.getContactInfo().getEmergencyContactPerson());
			hf.setEmergencyPhone(hfApplicationFamilyDetails.getContactInfo().getEmergencyPhone());
			// Physical Address
			hf.setPhysicalAddress(hfApplicationFamilyDetails.getPhysicalAddress().getAddress1());
			hf.setPhysicalCity(hfApplicationFamilyDetails.getPhysicalAddress().getCity());
			hf.setPhysicalZipCode(hfApplicationFamilyDetails.getPhysicalAddress().getZipCode());
			LookupUSState physicalAddressState = stateRepository.findOne(hfApplicationFamilyDetails.getPhysicalAddress().getStateId());
			hf.setLookupUsstate1(physicalAddressState);
			// mailing address
			hf.setMailingAddressSameAsCurrentAddress(hfApplicationFamilyDetails.getMailingAddress().isSameAsPhysicalAddress() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
			hf.setMailingAddress(hfApplicationFamilyDetails.getMailingAddress().getAddress1());
			hf.setMailingCity(hfApplicationFamilyDetails.getMailingAddress().getCity());
			hf.setMailingZipCode(hfApplicationFamilyDetails.getMailingAddress().getZipCode());
			LookupUSState mailingAddressState = stateRepository.findOne(hfApplicationFamilyDetails.getMailingAddress().getStateId());
			hf.setLookupUsstate2(mailingAddressState);

			hf.setCreatedBy(hfApplicationFamilyDetails.getLoginId());
			hf.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
			hostFamilyRepository.saveAndFlush(hf);

			// Airport
			List<HostFamilyAirport> airports = new ArrayList<HostFamilyAirport>();
			for (HFAirport aps : hfApplicationFamilyDetails.getAirports()) {
				HostFamilyAirport hfa = new HostFamilyAirport();
				hfa.setHostFamily(hf);
				Airport airport = airportRepository.findOne(aps.getAirportId());
				hfa.setAirport(airport);
				hfa.setDistanceToAirport(aps.getDistanceToNearestAirport());
				hfa.setCreatedBy(hfApplicationFamilyDetails.getLoginId());
				hfa.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
			}
			if (!airports.isEmpty())
				hostFamilyAirportRepository.save(airports);

			// Pets
			List<HostFamilyPet> pets = new ArrayList<HostFamilyPet>();
			for (HFPets pts : hfApplicationFamilyDetails.getPets()) {
				HostFamilyPet hfp = new HostFamilyPet();
				HostFamilyPetType hostFamilyPetType = hostFamilyPetTypeRepository.findOne(pts.getTypeId());
				hfp.setHostFamilyPetType(hostFamilyPetType);
				hfp.setIsIndoor(pts.isIndoor() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
				hfp.setIsOutdoor(pts.isOutDoor() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
				hfp.setNumber(pts.getNumber());
				hfp.setHostFamilySeason(season);
				hfp.setAdditionalInformation(pts.getAdditionalInfo());
			}
			if (!pets.isEmpty())
				hostFamilyPetRepository.save(pets);

			hp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
		} catch (CcighgoException e) {
			hp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_HF_BASIC_DATA.getValue(), e.getMessage()));
			LOGGER.error(e.getMessage());
		}
		return hp;
	}

	@Override
	public WSDefaultResponse saveFamilyLifeStyleData(HFApplicationFamilyLifeStyle hfApplicationFamilyDetails) {
		WSDefaultResponse hp = new WSDefaultResponse();
		try {
			if (hfApplicationFamilyDetails.getLoginId() > 0) {
				throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_OR_NULL_LOGIN_ID));
			}
			if (hfApplicationFamilyDetails.getSeasonId() > 0) {
				throw new CcighgoException("NO Season ID");
			}
			// Household members
			HostFamilyDetail hfd = new HostFamilyDetail();
			hfd.setFamilyMemberDescription(hfApplicationFamilyDetails.getFamilyDescription());
			hfd.setIllness(hfApplicationFamilyDetails.isAnyOneHasSeriousIllness()?CCIConstants.TRUE_BYTE:CCIConstants.FALSE_BYTE);
			hfd.setIllnessDetails(hfApplicationFamilyDetails.getIllnessExplanation());
			hfd.setDisability(hfApplicationFamilyDetails.isAnyOneHaveDisability()?CCIConstants.TRUE_BYTE:CCIConstants.FALSE_BYTE);
			hfd.setDisabilityDetails(hfApplicationFamilyDetails.getDisabilityExplanation());
			hfd.setAdaptCircumtances(hfApplicationFamilyDetails.getThingsStudentNeedsToAdaptWith());

			// dayIn The life of The Family
			hfd.setHouseHoldType(hfApplicationFamilyDetails.getFamilyDay().getHousehold());
			hfd.setTypicalWeekday(hfApplicationFamilyDetails.getFamilyDay().getTypicalWeekdayAtHome());
			hfd.setTypicalWeekend(hfApplicationFamilyDetails.getFamilyDay().getTypicalWeekendAtHome());
			hfd.setFavouriteWeekend(hfApplicationFamilyDetails.getFamilyDay().getFavouriteThingsToDoAsFamily());
		
			// Religion
			hfd.setReligiousAffiliation(hfApplicationFamilyDetails.getReligious().getReligious());
			hfd.setOtherReligiousDetails(hfApplicationFamilyDetails.getReligious().getExplanation());
			hfd.setReligiousAttendance(hfApplicationFamilyDetails.getReligious().getOftenAttendReligiousMeetings());
			hfd.setPreferStudentJoins(hfApplicationFamilyDetails.getReligious().getPreferedTheStudentJoinYou());
			hfd.setInviteStudentForReligiousExperience(hfApplicationFamilyDetails.getReligious().isInviteStudentForReligiousExperience()?CCIConstants.TRUE_BYTE:CCIConstants.FALSE_BYTE);
			hfd.setProblemWithReligiousDifference(hfApplicationFamilyDetails.getReligious().isDiffecultyHostingPersonWithDifferentReligious()?1:0);
			
			// Diet
			hfd.setAgreeToServeMeals(hfApplicationFamilyDetails.getDieTrayRestriction().isProvideStudentWithThreeMeals()?CCIConstants.TRUE_BYTE:CCIConstants.FALSE_BYTE);
			hfd.setDietaryRestrictions(hfApplicationFamilyDetails.getDieTrayRestriction().isFollowDietrayRestriction()?1:0);
			hfd.setDescribeDietaryRestrictions(hfApplicationFamilyDetails.getDieTrayRestriction().getDietrayRestrictionExplanation());
			hfd.setParticipantFollowDiet(hfApplicationFamilyDetails.getDieTrayRestriction().isFollowDietrayRestriction()?CCIConstants.TRUE_BYTE:CCIConstants.FALSE_BYTE);
			hfd.setDescPaxDietaryRestrictions(hfApplicationFamilyDetails.getDieTrayRestriction().getStudentFollowDietrayRestrictionExplanation());
			hfd.setComfortableHostingDiet(hfApplicationFamilyDetails.getDieTrayRestriction().isHostStudentWhoFollowDietrayRestriction()?CCIConstants.TRUE_BYTE:CCIConstants.FALSE_BYTE);
			
			// Miscellaneous
			hfd.setHasAutoInsurance(hfApplicationFamilyDetails.getMiscLifeStyle().getHaveAutoInsurranceForAllCarsYouHave());
			hfd.setFamilySmoker(hfApplicationFamilyDetails.getMiscLifeStyle().isAnyOneIsSmokingInyourFamily()?CCIConstants.TRUE_BYTE:CCIConstants.FALSE_BYTE);
			hfd.setFamilySmokingPlace(hfApplicationFamilyDetails.getMiscLifeStyle().getWhereSmoking());
			hfd.setDrinkAlcohol(hfApplicationFamilyDetails.getMiscLifeStyle().getAnyOneDrinkAlcoholic());
			hfd.setCrimeConviction(hfApplicationFamilyDetails.getMiscLifeStyle().isAnyOneConvictedInCrime()?CCIConstants.TRUE_BYTE:CCIConstants.FALSE_BYTE);
			hfd.setCrimeConvictionDetails(hfApplicationFamilyDetails.getMiscLifeStyle().getConvictedInCrimeDesc());
			hfd.setChildServicesContact(hfApplicationFamilyDetails.getMiscLifeStyle().isAnyOneInProtectiveServiceAgency()?CCIConstants.TRUE_BYTE:CCIConstants.FALSE_BYTE);
			hfd.setChildServicesContactDetails(hfApplicationFamilyDetails.getMiscLifeStyle().getChildInProtectiveServiceExplanation());

			// Financial
			hfd.setIncomeRange(hfApplicationFamilyDetails.getFinancialResources().getTotalHouseHoldIncome());
			hfd.setReceivePublicAssistance(hfApplicationFamilyDetails.getFinancialResources().isAnyOneReceivePublicAssistant()?CCIConstants.TRUE_BYTE:CCIConstants.FALSE_BYTE);
			hfd.setPublicAssistanceExplanation(hfApplicationFamilyDetails.getFinancialResources().getPublicAssistantExplanation());

			hostFamilyDetailRepository.saveAndFlush(hfd);
			hp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
		} catch (CcighgoException e) {
			hp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_HF_LIFE_STYLE.getValue(), e.getMessage()));
			LOGGER.error(e.getMessage());
		}
		return hp;

	}

}
