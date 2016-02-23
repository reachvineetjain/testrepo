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
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Airport;
import com.ccighgo.db.entities.FieldStaffAnnouncement;
import com.ccighgo.db.entities.HostFamily;
import com.ccighgo.db.entities.HostFamilyAirport;
import com.ccighgo.db.entities.HostFamilyAnnouncement;
import com.ccighgo.db.entities.HostFamilyMember;
import com.ccighgo.db.entities.HostFamilyPet;
import com.ccighgo.db.entities.HostFamilyPetType;
import com.ccighgo.db.entities.HostFamilyPhoto;
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
import com.ccighgo.jpa.repositories.HostFamilyMemberRepository;
import com.ccighgo.jpa.repositories.HostFamilyPetRepository;
import com.ccighgo.jpa.repositories.HostFamilyPetTypeRepository;
import com.ccighgo.jpa.repositories.HostFamilyPhotosRepository;
import com.ccighgo.jpa.repositories.HostFamilyRepository;
import com.ccighgo.jpa.repositories.HostFamilySeasonCategoryRepository;
import com.ccighgo.jpa.repositories.StateRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.HostFamilyMessageConstants;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboardAnnouncements;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFAdultDetails;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFAirport;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFApplicationFamilyDetails;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFPets;
import com.ccighgo.service.transport.hostfamily.beans.application.homepage.HFAnnouncements;
import com.ccighgo.service.transport.hostfamily.beans.application.homepage.HFApplicationCheckList;
import com.ccighgo.service.transport.hostfamily.beans.application.homepage.HFApplicationCheckListStages;
import com.ccighgo.service.transport.hostfamily.beans.application.homepage.HFHomePage;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.HFApplicationUploadPhotos;
import com.ccighgo.service.transport.hostfamily.beans.application.whyhost.WhyHost;
import com.ccighgo.utils.CCIConstants;
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
	HostFamilyAirport hostFamilyAirport;
	@Autowired
	HostFamilyPetTypeRepository hostFamilyPetTypeRepository;
	@Autowired
	HostFamilyPetRepository hostFamilyPetRepository;
	@Autowired
	HostFamilyAirportRepository hostFamilyAirportRepository;

	@Override
	public HFApplicationUploadPhotos uploadHFPhotos(String goId, String seasonId, HFApplicationUploadPhotos hfApplicationUploadPhotos) {
		HFApplicationUploadPhotos updatedObject = new HFApplicationUploadPhotos();
		try {
			if (hfApplicationUploadPhotos == null) {
				throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_HF_UPDATE_PHOTO_OBJECT));
			}
			if (hfApplicationUploadPhotos.getLoginId() == 0 || hfApplicationUploadPhotos.getLoginId() < 0) {
				throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_OR_NULL_LOGIN_ID));
			}
			if (goId == null || Integer.valueOf(goId) == 0 || Integer.valueOf(goId) < 0) {
				throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_HF_GOID));
			}
			if (seasonId == null || Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0) {
				throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_HF_SEASONID));
			}

		} catch (CcighgoException e) {
			updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), e.getMessage()));
			LOGGER.error(e.getMessage());
		}
		return updatedObject;
	}

	@Override
	public HFHomePage getHostFamilyHome(String goId, String loginId) {
		HFHomePage hp = new HFHomePage();
		try {
			if (goId == null || Integer.valueOf(goId) == 0 || Integer.valueOf(goId) < 0) {
				throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_HF_GOID));
			}
			if (loginId == null || Integer.valueOf(loginId) == 0 || Integer.valueOf(loginId) < 0) {
				throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_OR_NULL_LOGIN_ID));
			}

			List<HostFamilyAnnouncement> announcements = hfAnnouncementRepository.findAll();
			if (announcements != null)
				for (HostFamilyAnnouncement ann : announcements) {
					HFAnnouncements announcement = new HFAnnouncements();
					announcement.setHfAnnouncement(ann.getAnnouncement());
					if (ann.getCreatedOn() != null)
						announcement.setAnnouncementDate(DateUtils.getTimestamp(ann.getCreatedOn()));
					hp.getAnnouncements().add(announcement);
				}
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

	@Override
	public WSDefaultResponse saveFamilyBasicData(HFApplicationFamilyDetails hfApplicationFamilyDetails) {
		WSDefaultResponse hp = new WSDefaultResponse();
		try {

			// add photo
			if (hfApplicationFamilyDetails.getPhoto() != null) {
				HostFamilyPhoto f = new HostFamilyPhoto();
				f.setActive(CCIConstants.ACTIVE);
				f.setDescription(hfApplicationFamilyDetails.getPhoto().getDescription());
				f.setFileName(hfApplicationFamilyDetails.getPhoto().getName());
				f.setFilePath(hfApplicationFamilyDetails.getPhoto().getPhotoUrl());
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

			hostFamilyRepository.saveAndFlush(hf);
			// Airport

			List<HostFamilyAirport> airports = new ArrayList<HostFamilyAirport>();
			for (HFAirport aps : hfApplicationFamilyDetails.getAirports()) {
				HostFamilyAirport hfa = new HostFamilyAirport();
				hfa.setHostFamily(hf);
				Airport airport = new Airport();
				// TODO Fetching the airport
				hfa.setAirport(airport);
				hfa.setDistanceToAirport(aps.getDistanceToNearestAirport());

			}
			if (!airports.isEmpty())
				hostFamilyAirportRepository.save(airports);
			
			// Pets
			List<HostFamilyPet> pets = new ArrayList<HostFamilyPet>();
			for (HFPets pts : hfApplicationFamilyDetails.getPets()) {
				HostFamilyPet hfp = new HostFamilyPet();
				// TODO fetch petType by name
				HostFamilyPetType hostFamilyPetType = hostFamilyPetTypeRepository.findOne(1);
				hfp.setHostFamilyPetType(hostFamilyPetType);
				hfp.setIsIndoor(pts.isIndoor() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
				hfp.setIsOutdoor(pts.isOutDoor() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
				hfp.setNumber(pts.getNumber());
				// TODO get HostFamilySeason
				// hfp.setHostFamilySeason(hostFamilySeason);
			}
			if (!pets.isEmpty())
				hostFamilyPetRepository.save(pets);

		} catch (CcighgoException e) {
			hp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_HF_HOME_PAGE.getValue(), e.getMessage()));
			LOGGER.error(e.getMessage());
		}
		return hp;
	}

	@Override
	public WhyHost createWhyHost(String applicationCategoryId, WhyHost whyHost) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WhyHost getWhyHost(String hfHomeId, String hfSeasonId, String applicationCategoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WhyHost updateWhyHost(String applicationCategoryId, WhyHost whyHost) {
		// TODO Auto-generated method stub
		return null;
	}

}
