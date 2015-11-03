/**
 * 
 */
package com.ccighgo.service.components.partner.subpartner;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.CCIStaffUsersCCIStaffRole;
import com.ccighgo.db.entities.GoIdSequence;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.db.entities.LookupCountry;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerContact;
import com.ccighgo.db.entities.PartnerNote;
import com.ccighgo.db.entities.PartnerNoteTag;
import com.ccighgo.db.entities.PartnerNoteTopic;
import com.ccighgo.db.entities.PartnerOffice;
import com.ccighgo.db.entities.PartnerReviewStatus;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerStatus;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.db.entities.PartnerUserRole;
import com.ccighgo.db.entities.Salutation;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.CCIStaffRolesRepository;
import com.ccighgo.jpa.repositories.CCIStaffUsersCCIStaffRolesRepository;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.LoginUserTypeRepository;
import com.ccighgo.jpa.repositories.PartnerContactRepository;
import com.ccighgo.jpa.repositories.PartnerNoteRepository;
import com.ccighgo.jpa.repositories.PartnerNoteTagRepository;
import com.ccighgo.jpa.repositories.PartnerNoteTopicRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeTypeRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerReviewStatusRepository;
import com.ccighgo.jpa.repositories.PartnerUserRepository;
import com.ccighgo.jpa.repositories.SalutationRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.component.emailing.EmailServiceImpl;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminMessageConstants;
import com.ccighgo.service.components.errormessages.constants.RegionManagementMessageConstants;
import com.ccighgo.service.components.errormessages.constants.SubPartnerMessageConstants;
import com.ccighgo.service.components.utility.UtilityServices;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.NoteUserCreator;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningNotes;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerMailingAddress;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.Creator;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.Note;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.NoteTags;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.NoteTopics;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.Topic;
import com.ccighgo.service.transport.partner.beans.subpartner.PartnerSubPartners;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartner;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerAgency;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerCountry;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerCountryStatus;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerDetail;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerDetails;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerMailingAddress;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerPhysicalAddress;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerPrimaryContact;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerSeasons;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerStatus;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartners;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.Country;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.Details;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerScreeningNotes;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnersMailingAddress;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnersPhysicalAddress;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnersPrimaryContact;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.Topics;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.TypeofPartnerUser;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.CCIUtils;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.PasswordUtil;
import com.ccighgo.utils.UuidUtils;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * @author ravi
 *
 */
@Component
public class SubPartnerInterfaceImpl implements SubPartnerInterface {

	private static final Logger LOGGER = Logger
			.getLogger(SubPartnerInterfaceImpl.class);

	@Autowired
	PartnerRepository partnerRepository;

	@Autowired
	GoIdSequenceRepository goIdSequenceRepository;

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	UserTypeRepository userTypeRepository;

	@Autowired
	LoginUserTypeRepository loginUserTypeRepository;

	@Autowired
	PartnerNoteTopicRepository partnerNoteTopicRepository;

	@Autowired
	PartnerNoteRepository partnerNoteRepository;

	@Autowired
	PartnerNoteTagRepository partnerNoteTagRepository;

	@Autowired
	PartnerOfficeTypeRepository partnerOfficeTypeRepository;

	@Autowired
	PartnerOfficeRepository partnerOfficeRepository;

	@Autowired
	PartnerContactRepository partnerContactRepository;

	@Autowired
	CommonComponentUtils componentUtils;

	@Autowired
	MessageUtils messageUtil;

	@Autowired
	EmailServiceImpl email;

	@Autowired
	SalutationRepository salutationRepository;

	@Autowired
	UtilityServices utilityServices;
	// TODO
	// @Autowired PartnerCCIContactRepository partnerCCIContactRepository;

	@Autowired
	PartnerReviewStatusRepository partnerReviewStatusRepository;

	@Autowired
	PartnerUserRepository partnerUserRepository;
	
	
	@Override
	public PartnerSubPartners getSubPartnersOfpartners(String partnerId) {
		PartnerSubPartners psp = new PartnerSubPartners();
		psp.setCount(2);
		psp.setPartnerGoId(1111);
		List<SubPartners> subPartners = new ArrayList<SubPartners>();

		SubPartnerCountry subPartnerCountry1 = new SubPartnerCountry();
		subPartnerCountry1.setSubPartnerCountry("United States");
		subPartnerCountry1.setSubPartnerCountryId(233);

		SubPartnerCountry subPartnerCountry2 = new SubPartnerCountry();
		subPartnerCountry2.setSubPartnerCountry("Taiwan");
		subPartnerCountry2.setSubPartnerCountryId(228);

		SubPartnerCountry subPartnerCountry3 = new SubPartnerCountry();
		subPartnerCountry3.setSubPartnerCountry("Romania");
		subPartnerCountry3.setSubPartnerCountryId(189);

		SubPartnerCountry subPartnerCountry4 = new SubPartnerCountry();
		subPartnerCountry4.setSubPartnerCountry("New Zealand");
		subPartnerCountry4.setSubPartnerCountryId(171);

		SubPartnerCountry subPartnerCountry5 = new SubPartnerCountry();
		subPartnerCountry5.setSubPartnerCountry("Norway");
		subPartnerCountry5.setSubPartnerCountryId(167);

		SubPartnerStatus subPartnerStatus1 = new SubPartnerStatus();
		subPartnerStatus1.setSubPartnerStatusId(1);
		subPartnerStatus1.setSubPartnerStatus("Active");

		SubPartnerStatus subPartnerStatus2 = new SubPartnerStatus();
		subPartnerStatus2.setSubPartnerStatusId(2);
		subPartnerStatus2.setSubPartnerStatus("Inactive");

		List<SubPartnerSeasons> subPartnerSeasons = new ArrayList<SubPartnerSeasons>();
		SubPartnerSeasons partnerSeasons = new SubPartnerSeasons();
		partnerSeasons.setSubPartnerSeasonId(1);
		partnerSeasons.setSubPartnerSeasonProgramId(1);
		partnerSeasons.setSubPartnerSeasonProgram("J1HS");

		SubPartnerSeasons partnerSeasons1 = new SubPartnerSeasons();
		partnerSeasons1.setSubPartnerSeasonId(2);
		partnerSeasons1.setSubPartnerSeasonProgramId(2);
		partnerSeasons1.setSubPartnerSeasonProgram("F1");
		subPartnerSeasons.add(partnerSeasons);
		subPartnerSeasons.add(partnerSeasons1);

		SubPartners sPart = new SubPartners();
		sPart.setSubPartnerId(123);
		sPart.setSubPartnerFirstName("Super");
		sPart.setSubPartnerLastName("Man");
		sPart.setSubPartnerCountry(subPartnerCountry1);
		sPart.setSubPartnerStatus(subPartnerStatus1);
		sPart.getSubPartnerSeasons().addAll(subPartnerSeasons);

		SubPartners sPart1 = new SubPartners();
		sPart1.setSubPartnerId(1234);
		sPart1.setSubPartnerFirstName("Bat");
		sPart1.setSubPartnerLastName("Man");
		sPart1.setSubPartnerCountry(subPartnerCountry2);
		sPart1.setSubPartnerStatus(subPartnerStatus2);
		sPart1.getSubPartnerSeasons().addAll(subPartnerSeasons);

		SubPartners sPart2 = new SubPartners();
		sPart2.setSubPartnerId(1234);
		sPart2.setSubPartnerFirstName("Iron");
		sPart2.setSubPartnerLastName("Man");
		sPart2.setSubPartnerCountry(subPartnerCountry3);
		sPart2.setSubPartnerStatus(subPartnerStatus2);

		SubPartners sPart3 = new SubPartners();
		sPart3.setSubPartnerId(1234);
		sPart3.setSubPartnerFirstName("Ant");
		sPart3.setSubPartnerLastName("Man");
		sPart3.setSubPartnerCountry(subPartnerCountry4);
		sPart3.setSubPartnerStatus(subPartnerStatus1);

		SubPartners sPart4 = new SubPartners();
		sPart4.setSubPartnerId(1234);
		sPart4.setSubPartnerFirstName("Milk");
		sPart4.setSubPartnerLastName("Man");
		sPart4.setSubPartnerCountry(subPartnerCountry5);
		sPart4.setSubPartnerStatus(subPartnerStatus2);
		sPart4.getSubPartnerSeasons().addAll(subPartnerSeasons);

		subPartners.add(sPart);
		subPartners.add(sPart1);
		subPartners.add(sPart2);
		subPartners.add(sPart3);
		subPartners.add(sPart4);
		psp.getSubPartners().addAll(subPartners);
		return psp;
	}

	@Override
	@Transactional
	public SubPartnerDetails getAllSubPartners() {
		SubPartnerDetails subPartnerDetails = new SubPartnerDetails();
		try {
			List<Partner> subPartnerList = partnerRepository
					.findByIsSubPartner();
			if (subPartnerList == null) {
				subPartnerDetails = setSubPartnerDetailsStatus(
						subPartnerDetails,
						CCIConstants.FAILURE,
						CCIConstants.TYPE_ERROR,
						ErrorCode.ERROR_GET_SUB_PARTNER_DETAILS.getValue(),
						messageUtil
								.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
				LOGGER.error(messageUtil
						.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
				return subPartnerDetails;
			}
			subPartnerDetails.setCount(subPartnerList.size());
			for (Partner subPartner : subPartnerList) {
				SubPartnerDetail subPartnerDetail = new SubPartnerDetail();
				subPartnerDetail.setSubPartnerId(subPartner.getPartnerGoId());
				if (subPartner.getPartnerContacts() != null
						&& subPartner.getPartnerContacts().size() > 0) {
					subPartnerDetail.setSubPartnerFirstName(subPartner
							.getPartnerContacts().iterator().next()
							.getFirstName());
					subPartnerDetail.setSubPartnerLastName(subPartner
							.getPartnerContacts().iterator().next()
							.getLastName());
				}
				SubPartnerCountryStatus subPartnerCountryStatus = new SubPartnerCountryStatus();
				subPartnerCountryStatus.setSubPartnerCountryId(subPartner
						.getLookupCountry1().getCountryId());
				subPartnerCountryStatus.setSubPartnerCountryName(subPartner
						.getLookupCountry1().getCountryName());
				subPartnerCountryStatus.setSubPartnerCountryCode(subPartner
						.getLookupCountry1().getCountryCode());
				subPartnerDetail
						.setSubPartnerCountryStatus(subPartnerCountryStatus);

				SubPartnerStatus subPartnerStatus = new com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerStatus();
				/*
				 * subPartnerStatus.setSubPartnerStatusId(subPartner.
				 * getPartnerStatus().getPartnerStatusId());
				 * subPartnerStatus.setSubPartnerStatus
				 * (subPartner.getPartnerStatus().getPartnerStatusName());
				 * subPartnerDetail.setSubPartnerStatus(subPartnerStatus);
				 */

				List<SubPartnerSeasons> subPartnerSeasonsList = new ArrayList<SubPartnerSeasons>();
				if (subPartner.getPartnerSeasons() != null
						&& subPartner.getPartnerSeasons().size() > 0) {
					for (PartnerSeason partnerSeason : subPartner
							.getPartnerSeasons()) {
						SubPartnerSeasons SubPartnerSeasons = new com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerSeasons();
						SubPartnerSeasons.setSubPartnerSeasonId(partnerSeason
								.getPartnerSeasonId());
						SubPartnerSeasons
								.setSubPartnerSeasonProgramId(partnerSeason
										.getDepartmentProgram()
										.getDepartmentProgramId());
						SubPartnerSeasons
								.setSubPartnerSeasonProgram(partnerSeason
										.getDepartmentProgram()
										.getProgramName());
						subPartnerSeasonsList.add(SubPartnerSeasons);
					}
				}
				subPartnerDetail.getSubPartnerSeasons().addAll(
						subPartnerSeasonsList);
				subPartnerDetails.getSubPartnerDetails().add(subPartnerDetail);
				subPartnerDetails = setSubPartnerDetailsStatus(
						subPartnerDetails, CCIConstants.SUCCESS,
						CCIConstants.TYPE_INFO,
						ErrorCode.SUB_PARTNER_CODE.getValue(),
						messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
			}
		} catch (CcighgoException e) {
			subPartnerDetails = setSubPartnerDetailsStatus(
					subPartnerDetails,
					CCIConstants.FAILURE,
					CCIConstants.TYPE_ERROR,
					ErrorCode.ERROR_GET_SUB_PARTNER_DETAILS.getValue(),
					messageUtil
							.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
			LOGGER.error(messageUtil
					.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
		}

		return subPartnerDetails;
	}

	@Override
	@Transactional
	public SubPartner viewSubPartners(String subPartnerId) {
		SubPartner subPartner = new SubPartner();
		try {
			if (subPartnerId == null && Integer.valueOf(subPartnerId) > 0) {
				subPartner = setSubPartnerStatus(
						subPartner,
						CCIConstants.FAILURE,
						CCIConstants.TYPE_ERROR,
						ErrorCode.INVALID_SUB_PARTNER_ID.getValue(),
						messageUtil
								.getMessage(SubPartnerMessageConstants.INVALID_SUB_PARTNER_ID));
				LOGGER.error(messageUtil
						.getMessage(SubPartnerMessageConstants.INVALID_SUB_PARTNER_ID));
				return subPartner;
			}
			Partner partnerSubPartner = partnerRepository.findOne(Integer
					.valueOf(subPartnerId));

			if (partnerSubPartner == null) {
				subPartner = setSubPartnerStatus(
						subPartner,
						CCIConstants.FAILURE,
						CCIConstants.TYPE_ERROR,
						ErrorCode.ERROR_GET_SUB_PARTNER_DETAILS.getValue(),
						messageUtil
								.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
				LOGGER.error(messageUtil
						.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
				return subPartner;
			}
			if (partnerSubPartner.getIsSubPartner() == CCIConstants.ACTIVE) {
				subPartner.setSubPartnerId(partnerSubPartner.getPartnerGoId());

				// Agency Details
				SubPartnerAgency subPartnerAgency = new SubPartnerAgency();
				subPartnerAgency.setCompanyName(partnerSubPartner
						.getCompanyName() == null ? CCIConstants.EMPTY_DATA
						: partnerSubPartner.getCompanyName());

				// Status
				SubPartnerStatus subPartnerStatus = new SubPartnerStatus();
				if (partnerSubPartner.getPartnerReviewStatuses() != null
						&& partnerSubPartner.getPartnerReviewStatuses().size() > 0) {
					subPartnerStatus.setSubPartnerStatusId(partnerSubPartner
							.getPartnerReviewStatuses().get(0)
							.getPartnerStatus2().getPartnerStatusId());
					subPartnerStatus.setSubPartnerStatus(partnerSubPartner
							.getPartnerReviewStatuses().get(0)
							.getPartnerStatus2().getPartnerStatusName());
				}
				subPartnerAgency.setSubPartnerStatus(subPartnerStatus);
				subPartnerAgency.setNeedPartnerReview(partnerSubPartner
						.getNeedPartnerReview());
				subPartnerAgency.setDeliverDSForms(partnerSubPartner
						.getDeliverDSForms());
				subPartnerAgency.setPayGreenheartDirectly(partnerSubPartner
						.getPayGreenheartDirectly());
				subPartnerAgency.setUserName(partnerSubPartner
						.getGoIdSequence().getLogins().iterator().next()
						.getLoginName());
				subPartner.setSubPartnerAgency(subPartnerAgency);

				// primary contact
				SubPartnerPrimaryContact subPartnerPrimaryContact = new SubPartnerPrimaryContact();
				if (partnerSubPartner.getPartnerContacts() != null
						&& partnerSubPartner.getPartnerContacts().size() > 0) {
					PartnerContact partnerContact = partnerSubPartner
							.getPartnerContacts().iterator().next();
					com.ccighgo.service.transport.utility.beans.gender.Salutation salutation = new com.ccighgo.service.transport.utility.beans.gender.Salutation();
					salutation.setSalutationId(partnerContact.getSalutation()
							.getSalutationId());
					salutation.setSalutationCode(partnerContact.getSalutation()
							.getSalutationName());
					salutation.setActive(partnerContact.getSalutation()
							.getActive());
					subPartnerPrimaryContact.setSalutation(salutation);
					subPartnerPrimaryContact
							.setTitle(partnerContact.getTitle());
					subPartnerPrimaryContact.setFirstName(partnerContact
							.getFirstName());
					subPartnerPrimaryContact.setLastName(partnerContact
							.getLastName());
					subPartnerPrimaryContact
							.setEmail(partnerContact.getEmail());
					subPartnerPrimaryContact
							.setPhone(partnerContact.getPhone());
					subPartnerPrimaryContact.setEmergencyPhone(partnerContact
							.getEmergencyPhone());
					subPartnerPrimaryContact.setFax(partnerContact.getFax());
					subPartnerPrimaryContact
							.setReceiveNotificationEmailFromCCI(partnerContact
									.getReceiveNotificationEmails());
					subPartnerPrimaryContact.setSkypeId(partnerContact
							.getSkypeId());
					subPartnerPrimaryContact.setWebsite(partnerContact
							.getWebsite());
					subPartnerPrimaryContact
							.setTypeOfPartnerUser(partnerContact
									.getPartnerOffice().getPartnerOfficeType()
									.getPartnerOfficeTypeId());
				}
				// TODO: // need to change type here if above code is not proper
				subPartner
						.setSubPartnerPrimaryContact(subPartnerPrimaryContact);

				// seasons
				List<SubPartnerSeasons> subPartnerSeasonsList = new ArrayList<SubPartnerSeasons>();
				if (partnerSubPartner.getPartnerSeasons() != null
						&& partnerSubPartner.getPartnerSeasons().size() > 0) {
					for (PartnerSeason partnerSeason : partnerSubPartner
							.getPartnerSeasons()) {
						SubPartnerSeasons SubPartnerSeasons = new com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerSeasons();
						SubPartnerSeasons.setSubPartnerSeasonId(partnerSeason
								.getPartnerSeasonId());
						SubPartnerSeasons
								.setSubPartnerSeasonProgramId(partnerSeason
										.getDepartmentProgram()
										.getDepartmentProgramId());
						SubPartnerSeasons
								.setSubPartnerSeasonProgram(partnerSeason
										.getDepartmentProgram()
										.getProgramName());
						subPartnerSeasonsList.add(SubPartnerSeasons);
					}
				}
				subPartner.getSubPartnerSeasons().addAll(subPartnerSeasonsList);

				// Physical Address
				SubPartnerPhysicalAddress subPartnerPhysicalAddress = new SubPartnerPhysicalAddress();
				subPartnerPhysicalAddress
						.setPhysicalAddressLineOne(partnerSubPartner
								.getPhysicalAddressLineOne());
				subPartnerPhysicalAddress
						.setPhysicalAddressLineTwo(partnerSubPartner
								.getPhysicalAddressLineTwo());
				subPartnerPhysicalAddress.setPhysicalCity(partnerSubPartner
						.getPhysicalCity());
				subPartnerPhysicalAddress.setPhysicalstate(partnerSubPartner
						.getPhysicalstate());
				subPartnerPhysicalAddress.setPhysicalZipcode(partnerSubPartner
						.getPhysicalZipcode());

				SubPartnerCountryStatus subPartnerCountryStatus1 = new SubPartnerCountryStatus();
				subPartnerCountryStatus1
						.setSubPartnerCountryName(partnerSubPartner
								.getLookupCountry1().getCountryName());
				subPartnerCountryStatus1
						.setSubPartnerCountryId(partnerSubPartner
								.getLookupCountry1().getCountryId());
				subPartnerCountryStatus1
						.setSubPartnerCountryCode(partnerSubPartner
								.getLookupCountry1().getCountryCode());
				subPartnerPhysicalAddress
						.setPhysicalSubPartnerCountryStatus(subPartnerCountryStatus1);
				subPartner
						.setSubPartnerPhysicalAddress(subPartnerPhysicalAddress);

				// Mailing Address
				SubPartnerMailingAddress subPartnerMailingAddress = new SubPartnerMailingAddress();
				subPartnerMailingAddress.setAddressLineOne(partnerSubPartner
						.getAddressLineOne());
				subPartnerMailingAddress.setAddressLineTwo(partnerSubPartner
						.getAddressLineTwo());
				subPartnerMailingAddress.setCity(partnerSubPartner.getCity());
				subPartnerMailingAddress.setState(partnerSubPartner.getState());
				subPartnerMailingAddress.setZipcode(partnerSubPartner
						.getZipcode());

				SubPartnerCountryStatus subPartnerCountryStatus2 = new SubPartnerCountryStatus();
				subPartnerCountryStatus2
						.setSubPartnerCountryName(partnerSubPartner
								.getLookupCountry2().getCountryName());
				subPartnerCountryStatus2
						.setSubPartnerCountryId(partnerSubPartner
								.getLookupCountry2().getCountryId());
				subPartnerCountryStatus2
						.setSubPartnerCountryCode(partnerSubPartner
								.getLookupCountry2().getCountryCode());
				subPartnerMailingAddress
						.setMailingSubPartnerCountryStatus(subPartnerCountryStatus2);
				subPartner
						.setSubPartnerMailingAddress(subPartnerMailingAddress);

				// Sub Partner Note topic
				NoteTopics NoteTopics = new NoteTopics();
				List<Topic> topicList = new ArrayList<Topic>();
				if (partnerSubPartner.getPartnerNoteTopics() != null
						&& partnerSubPartner.getPartnerNoteTopics().size() > 0) {
					List<PartnerNoteTopic> partnerNoteTopicDBList = partnerSubPartner
							.getPartnerNoteTopics();
					for (PartnerNoteTopic partnerNoteTopic : partnerNoteTopicDBList) {
						Topic topic = new Topic();
						topic.setTopicId(partnerNoteTopic
								.getPartnerNoteTopicId());
						topic.setTopicTitle(partnerNoteTopic
								.getPartnerNoteTopicName());
						// creator
						Creator TopicCreator = new Creator();
						// TODO
						// TopicCreator.setCreatedBy(String.valueOf(partnerSubPartner.getPartnerCcicontacts().get(0).getCcistaffUser().getCciStaffUserId()));
						// TopicCreator.setCreatedByPicUrl(partnerSubPartner.getPartnerCcicontacts().get(0).getCcistaffUser().getPhoto());//
						// TODO : need clarification
						// TopicCreator.setDesignation(partnerSubPartner.getPartnerCcicontacts().get(0).getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole().getCciStaffRoleName());
						// // TODO : need clarification
						topic.setCreator(TopicCreator);
						topic.setPrivacy(partnerNoteTopic.getIsPublic() == CCIConstants.INACTIVE ? "private"
								: "public");

						List<NoteTags> noteTagsList = new ArrayList<NoteTags>();

						for (PartnerNoteTag partnerNoteTag : partnerNoteTagRepository
								.findAll()) {
							NoteTags noteTags = new NoteTags();
							noteTags.setNoteTagId(partnerNoteTag
									.getPartnerNoteTagId());
							noteTags.setNoteTag(partnerNoteTag.getTagName());
							if (partnerNoteTag.getTagName().equalsIgnoreCase(
									CCIConstants.Work_Travels))
								noteTags.setNoteTagValue(partnerNoteTopic
										.getW_t());
							if (partnerNoteTag.getTagName().equalsIgnoreCase(
									CCIConstants.HSP_J1))
								noteTags.setNoteTagValue(partnerNoteTopic
										.getJ1());
							if (partnerNoteTag.getTagName().equalsIgnoreCase(
									CCIConstants.GHT))
								noteTags.setNoteTagValue(partnerNoteTopic
										.getGht());
							if (partnerNoteTag.getTagName().equalsIgnoreCase(
									CCIConstants.STBound))
								noteTags.setNoteTagValue(partnerNoteTopic
										.getStInbound());
							if (partnerNoteTag.getTagName().equalsIgnoreCase(
									CCIConstants.Intern))
								noteTags.setNoteTagValue(partnerNoteTopic
										.getIntern());
							if (partnerNoteTag.getTagName().equalsIgnoreCase(
									CCIConstants.Trainee))
								noteTags.setNoteTagValue(partnerNoteTopic
										.getTrainee());
							if (partnerNoteTag.getTagName().equalsIgnoreCase(
									CCIConstants.Meeting_Visit))
								noteTags.setNoteTagValue(partnerNoteTopic
										.getMeeting_visit());
							if (partnerNoteTag.getTagName().equalsIgnoreCase(
									CCIConstants.CompitetorInfo))
								noteTags.setNoteTagValue(partnerNoteTopic
										.getCompetitorInfo());
							if (partnerNoteTag.getTagName().equalsIgnoreCase(
									CCIConstants.Embassy_VisaInfo))
								noteTags.setNoteTagValue(partnerNoteTopic
										.getEmbassy_VisaInfo());
							if (partnerNoteTag.getTagName().equalsIgnoreCase(
									CCIConstants.SeasonInfo))
								noteTags.setNoteTagValue(partnerNoteTopic
										.getSeasonInfo());
							if (partnerNoteTag.getTagName().equalsIgnoreCase(
									CCIConstants.HSPF1))
								noteTags.setNoteTagValue(partnerNoteTopic
										.getF1());

							noteTagsList.add(noteTags);
						}
						topic.getNoteTags().addAll(noteTagsList);
						List<Note> noteList = new ArrayList<Note>();
						List<PartnerNote> partnerNoteDbList = partnerNoteTopic
								.getPartnerNotes();
						topic.setNotesCount(partnerNoteDbList.size());
						for (PartnerNote partnerNote : partnerNoteDbList) {
							Note note = new Note();

							note.setNoteId(partnerNote.getPartnerNotesId());
							note.setTopicId(partnerNote.getPartnerNoteTopic()
									.getPartnerNoteTopicId());
							note.setTimestamp(partnerNote.getCreatedOn()
									.toString());
							Creator Notecreator = new Creator();
							// TODO
							// Notecreator.setCreatedBy(String.valueOf(partnerSubPartner.getPartnerCcicontacts().get(0).getCcistaffUser().getCciStaffUserId()));
							// Notecreator.setCreatedByPicUrl(partnerSubPartner.getPartnerCcicontacts().get(0).getCcistaffUser().getPhoto());//
							// TODO : need clarification
							// Notecreator.setDesignation(partnerSubPartner.getPartnerCcicontacts().get(0).getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole().getCciStaffRoleName());//
							// TODO
							note.setCreator(Notecreator);
							note.setNote(partnerNote.getPartnerNote());
							noteList.add(note);
						}
						if (partnerNoteDbList.size() > 0)
							topic.setAddedOn(String.valueOf(partnerNoteTopic
									.getPartnerNotes().get(0).getCreatedOn()));// TODO
						topic.getNotes().addAll(noteList);
						topicList.add(topic);
					}
					NoteTopics.getTopicList().addAll(topicList);
					NoteTopics.setTopicCount(partnerNoteTopicDBList.size());
				}
				subPartner.setPartnerSeasonNotes(NoteTopics);
				subPartner = setSubPartnerStatus(subPartner,
						CCIConstants.SUCCESS, CCIConstants.TYPE_INFO,
						ErrorCode.SUB_PARTNER_CODE.getValue(),
						messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));

			} else {
				subPartner = setSubPartnerStatus(
						subPartner,
						CCIConstants.FAILURE,
						CCIConstants.TYPE_ERROR,
						ErrorCode.INVALID_SUB_PARTNER_ID.getValue(),
						messageUtil
								.getMessage(SubPartnerMessageConstants.INVALID_SUB_PARTNER_ID));
				LOGGER.error(messageUtil
						.getMessage(SubPartnerMessageConstants.INVALID_SUB_PARTNER_ID));
			}
		} catch (CcighgoException e) {
			subPartner = setSubPartnerStatus(
					subPartner,
					CCIConstants.FAILURE,
					CCIConstants.TYPE_ERROR,
					ErrorCode.ERROR_GET_SUB_PARTNER_DETAILS.getValue(),
					messageUtil
							.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
			LOGGER.error(messageUtil
					.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
		}
		return subPartner;
	}

	@Override
	@Transactional
	public SubPartner createSubPartner(SubPartner subPartner,
			HttpServletRequest request) {
		SubPartner createdSubPartner = new SubPartner();
		try {
			if (subPartner == null) {
				createdSubPartner = setSubPartnerStatus(
						createdSubPartner,
						CCIConstants.FAILURE,
						CCIConstants.TYPE_ERROR,
						ErrorCode.FAILED_SUB_PARTNER_DETAILS_NULL.getValue(),
						messageUtil
								.getMessage(SubPartnerMessageConstants.FAILED_SUB_PARTNER_DETAILS_NULL));
				LOGGER.error(messageUtil
						.getMessage(SubPartnerMessageConstants.FAILED_SUB_PARTNER_DETAILS_NULL));
				return createdSubPartner;
			}
			// validate username
			if (loginRepository.findByLoginName(subPartner
					.getSubPartnerAgency().getUserName()) != null) {
				// return username already exsist
				createdSubPartner = setSubPartnerStatus(
						createdSubPartner,
						CCIConstants.FAILURE,
						CCIConstants.TYPE_ERROR,
						ErrorCode.SUB_PARTNER_CREATE_USER_USERNAME_EXIST
								.getValue(),
						messageUtil
								.getMessage(SubPartnerMessageConstants.SUB_PARTNER_CREATE_USER_USERNAME_EXIST));
				LOGGER.error(messageUtil
						.getMessage(SubPartnerMessageConstants.SUB_PARTNER_CREATE_USER_USERNAME_EXIST));
				return createdSubPartner;
			}
			// findByemail

			if (loginRepository.findByEmail(subPartner
					.getSubPartnerPrimaryContact().getEmail()) != null) {
				// return email already exist
				createdSubPartner = setSubPartnerStatus(
						createdSubPartner,
						CCIConstants.FAILURE,
						CCIConstants.TYPE_ERROR,
						ErrorCode.SUB_PARTNER_CREATE_USER_EMAIL_EXIST
								.getValue(),
						messageUtil
								.getMessage(SubPartnerMessageConstants.SUB_PARTNER_CREATE_USER_EMAIL_EXIST));
				LOGGER.error(messageUtil
						.getMessage(SubPartnerMessageConstants.SUB_PARTNER_CREATE_USER_EMAIL_EXIST));
				return createdSubPartner;
			}

			Partner subPartnerDetails = new Partner();
			String partnerGuid = UuidUtils.nextHexUUID();
			subPartnerDetails.setPartnerGuid(partnerGuid);
			// agency details
			PartnerStatus partnerStatus = new PartnerStatus();
			SubPartnerAgency subPartnerAgency = null;
			if (subPartner.getSubPartnerAgency() != null) {
				subPartnerAgency = subPartner.getSubPartnerAgency();
				subPartnerDetails.setCompanyName(subPartnerAgency
						.getCompanyName());
				partnerStatus.setPartnerStatusId(subPartner
						.getSubPartnerAgency().getSubPartnerStatus()
						.getSubPartnerStatusId());
				partnerStatus.setPartnerStatusName(subPartner
						.getSubPartnerAgency().getSubPartnerStatus()
						.getSubPartnerStatus());
				subPartnerDetails.setNeedPartnerReview(subPartnerAgency
						.getNeedPartnerReview());
				subPartnerDetails.setDeliverDSForms(subPartnerAgency
						.getDeliverDSForms());
				subPartnerDetails.setPayGreenheartDirectly(subPartnerAgency
						.getPayGreenheartDirectly());
			}
			// Login And GoId
			GoIdSequence goIdSequence = new GoIdSequence();
			goIdSequence = goIdSequenceRepository.save(goIdSequence);

			com.ccighgo.db.entities.UserType partnerUserType = userTypeRepository
					.findOne(CCIConstants.PARTNER_USER_TYPE);
			if (partnerUserType == null) {
				partnerUserType = new com.ccighgo.db.entities.UserType();
			}
			List<Login> loginList = new ArrayList<Login>();
			Login login = new Login();
			login.setActive(subPartner.getSubPartnerAgency()
					.getSubPartnerStatus().getSubPartnerStatusId() == 1 ? CCIConstants.ACTIVE
					: CCIConstants.INACTIVE);
			login.setLoginName(subPartner.getSubPartnerAgency().getUserName());
			login.setPassword(PasswordUtil.hashKey("password"));
			login.setKeyValue(UuidUtils.nextHexUUID());
			login.setCreatedBy(goIdSequence.getGoId());
			login.setCreatedOn(new java.sql.Timestamp(System
					.currentTimeMillis()));
			login.setModifiedBy(goIdSequence.getGoId());
			login.setModifiedOn(new java.sql.Timestamp(System
					.currentTimeMillis()));
			login.setGoIdSequence(goIdSequence);
			login.setEmail(subPartner.getSubPartnerPrimaryContact().getEmail());
			login = loginRepository.save(login);
			loginList.add(login);
			goIdSequence.setLogins(loginList);
			subPartnerDetails.setGoIdSequence(goIdSequence);
			subPartnerDetails.setPartnerGoId(goIdSequence.getGoId());

			subPartnerDetails.setIsSubPartner(CCIConstants.ACTIVE);

			LoginUserType loginUserType = new LoginUserType();
			loginUserType.setActive(CCIConstants.ACTIVE);
			loginUserType.setUserType(partnerUserType);
			loginUserType.setCreatedBy(goIdSequence.getGoId());
			loginUserType.setCreatedOn(new java.sql.Timestamp(System
					.currentTimeMillis()));
			loginUserType.setModifiedBy(goIdSequence.getGoId());
			loginUserType.setModifiedOn(new java.sql.Timestamp(System
					.currentTimeMillis()));
			loginUserType.setDefaultUserType(CCIConstants.ACTIVE);
			loginUserType.setLogin(login);
			loginUserType = loginUserTypeRepository.save(loginUserType);

			// physical address
			SubPartnerPhysicalAddress subPartnerPhysicalAddress = null;
			if (subPartner.getSubPartnerPhysicalAddress() != null) {
				subPartnerPhysicalAddress = subPartner
						.getSubPartnerPhysicalAddress();
				subPartnerDetails
						.setPhysicalAddressLineOne(subPartnerPhysicalAddress
								.getPhysicalAddressLineOne());
				subPartnerDetails
						.setPhysicalAddressLineTwo(subPartnerPhysicalAddress
								.getPhysicalAddressLineTwo());
				subPartnerDetails.setPhysicalCity(subPartnerPhysicalAddress
						.getPhysicalCity());
				subPartnerDetails.setPhysicalstate(subPartnerPhysicalAddress
						.getPhysicalstate());
				subPartnerDetails.setPhysicalZipcode(subPartnerPhysicalAddress
						.getPhysicalZipcode());

				LookupCountry subPartnerCountry1 = new LookupCountry();
				subPartnerCountry1.setCountryCode(subPartner
						.getSubPartnerPhysicalAddress()
						.getPhysicalSubPartnerCountryStatus()
						.getSubPartnerCountryCode());
				subPartnerCountry1.setCountryId(subPartner
						.getSubPartnerPhysicalAddress()
						.getPhysicalSubPartnerCountryStatus()
						.getSubPartnerCountryId());
				subPartnerCountry1.setCountryName(subPartner
						.getSubPartnerPhysicalAddress()
						.getPhysicalSubPartnerCountryStatus()
						.getSubPartnerCountryName());
				subPartnerDetails.setLookupCountry1(subPartnerCountry1);
			}

			// mailing address
			SubPartnerMailingAddress subPartnerMailingAddress = null;
			if (subPartner.getSubPartnerMailingAddress() != null) {
				subPartnerMailingAddress = subPartner
						.getSubPartnerMailingAddress();
				subPartnerDetails.setAddressLineOne(subPartnerMailingAddress
						.getAddressLineOne());
				subPartnerDetails.setAddressLineTwo(subPartnerMailingAddress
						.getAddressLineTwo());
				subPartnerDetails.setCity(subPartnerMailingAddress.getCity());
				subPartnerDetails.setState(subPartnerMailingAddress.getState());
				subPartnerDetails.setZipcode(subPartnerMailingAddress
						.getZipcode());

				LookupCountry subPartnerCountry2 = new LookupCountry();
				subPartnerCountry2.setCountryCode(subPartner
						.getSubPartnerMailingAddress()
						.getMailingSubPartnerCountryStatus()
						.getSubPartnerCountryCode());
				subPartnerCountry2.setCountryId(subPartner
						.getSubPartnerMailingAddress()
						.getMailingSubPartnerCountryStatus()
						.getSubPartnerCountryId());
				subPartnerCountry2.setCountryName(subPartner
						.getSubPartnerMailingAddress()
						.getMailingSubPartnerCountryStatus()
						.getSubPartnerCountryName());
				subPartnerDetails.setLookupCountry2(subPartnerCountry2);

				subPartnerDetails.setCreatedBy(goIdSequence.getGoId());
				subPartnerDetails.setCreatedOn(new java.sql.Timestamp(System
						.currentTimeMillis()));
				subPartnerDetails.setModifiedBy(goIdSequence.getGoId());
				subPartnerDetails.setModifiedOn(new java.sql.Timestamp(System
						.currentTimeMillis()));
				subPartnerDetails = partnerRepository.save(subPartnerDetails);
			}

			// TODO
			// Sub partner CCI Contact
			// PartnerCCIContact partnerCCIContact = new PartnerCCIContact();
			// partnerCCIContact.setPartner(subPartnerDetails);
			// Login cciLogin =
			// loginRepository.findByLoginName(subPartner.getLoggedUsername());
			// partnerCCIContact.setCcistaffUser(cciLogin.getGoIdSequence().getCcistaffUser());
			// partnerCCIContact =
			// partnerCCIContactRepository.save(partnerCCIContact);

			// Sub partner Review Status
			PartnerReviewStatus partnerReviewStatus = new PartnerReviewStatus();
			// TODO
			// partnerReviewStatus.setCcistaffUser(cciLogin.getGoIdSequence().getCcistaffUser());
			partnerReviewStatus.setPartner(subPartnerDetails);
			partnerReviewStatus.setPartnerStatus1(partnerStatus);
			partnerReviewStatus.setPartnerStatus2(partnerStatus);
			partnerReviewStatus.setPartnerStatusReason(CCIConstants.EMPTY_DATA);
			partnerReviewStatus = partnerReviewStatusRepository
					.save(partnerReviewStatus);

			// sub partner office
			SubPartnerPhysicalAddress subPartnerOfficeAddress = null;
			PartnerOffice partnerOffice = new PartnerOffice();
			if (subPartner.getSubPartnerPhysicalAddress() != null) {
				subPartnerOfficeAddress = subPartner
						.getSubPartnerPhysicalAddress();
				partnerOffice.setAdressOne(subPartnerOfficeAddress
						.getPhysicalAddressLineOne());
				partnerOffice.setAdressTwo(subPartnerOfficeAddress
						.getPhysicalAddressLineTwo());
				partnerOffice
						.setCity(subPartnerOfficeAddress.getPhysicalCity());
				partnerOffice.setCreatedBy(subPartnerDetails.getCreatedBy());
				partnerOffice.setCreatedOn(new java.sql.Timestamp(System
						.currentTimeMillis()));
				partnerOffice.setFaxNumber(subPartner
						.getSubPartnerPrimaryContact().getFax());

				LookupCountry subPartnerCountry3 = new LookupCountry();
				subPartnerCountry3.setCountryId(subPartnerOfficeAddress
						.getPhysicalSubPartnerCountryStatus()
						.getSubPartnerCountryId());
				subPartnerCountry3.setCountryCode(subPartnerOfficeAddress
						.getPhysicalSubPartnerCountryStatus()
						.getSubPartnerCountryCode());
				subPartnerCountry3.setCountryName(subPartnerOfficeAddress
						.getPhysicalSubPartnerCountryStatus()
						.getSubPartnerCountryName());
				partnerOffice.setLookupCountry(subPartnerCountry3);

				partnerOffice.setModifiedBy(subPartnerDetails.getModifiedBy());
				partnerOffice.setModifiedOn(new java.sql.Timestamp(System
						.currentTimeMillis()));
				partnerOffice.setPartner(subPartnerDetails);
				partnerOffice.setPartnerOfficeType(partnerOfficeTypeRepository
						.findOne(Integer.valueOf(subPartner
								.getSubPartnerPrimaryContact()
								.getTypeOfPartnerUser())));
				partnerOffice.setPhoneNumber(subPartner
						.getSubPartnerPrimaryContact().getPhone());
				partnerOffice.setPostalCode(subPartnerOfficeAddress
						.getPhysicalZipcode());
				partnerOffice.setState(subPartnerOfficeAddress
						.getPhysicalstate());
				partnerOffice = partnerOfficeRepository.save(partnerOffice);
			}

			// sub partner contact
			if (subPartner.getSubPartnerPrimaryContact() != null) {
				SubPartnerPrimaryContact subPartnerPrimaryContact = subPartner
						.getSubPartnerPrimaryContact();
				List<PartnerContact> partnerContactList = new ArrayList<PartnerContact>();
				PartnerContact partnerContact = new PartnerContact();
				Salutation salutation = salutationRepository
						.findOne(subPartnerPrimaryContact.getSalutation()
								.getSalutationId());
				partnerContact.setSalutation(salutation);
				partnerContact.setTitle(subPartnerPrimaryContact.getTitle());
				partnerContact.setFirstName(subPartnerPrimaryContact
						.getFirstName());
				partnerContact.setLastName(subPartnerPrimaryContact
						.getLastName());
				partnerContact.setEmail(subPartnerPrimaryContact.getEmail());
				partnerContact.setPhone(subPartnerPrimaryContact.getPhone());
				partnerContact.setEmergencyPhone(subPartnerPrimaryContact
						.getEmergencyPhone());
				partnerContact.setFax(subPartnerPrimaryContact.getFax());
				partnerContact
						.setReceiveNotificationEmails(subPartnerPrimaryContact
								.getReceiveNotificationEmailFromCCI());
				partnerContact
						.setSkypeId(subPartnerPrimaryContact.getSkypeId());
				partnerContact
						.setWebsite(subPartnerPrimaryContact.getWebsite());
				partnerContact.setPartnerOffice(partnerOffice);
				partnerContact.setPartner(subPartnerDetails);
				partnerContact.setCreatedBy(subPartnerDetails.getCreatedBy());
				partnerContact.setCreatedOn(new java.sql.Timestamp(System
						.currentTimeMillis()));
				partnerContact
						.setModifiedBy(subPartnerDetails.getPartnerGoId());
				partnerContact.setModifiedOn(new java.sql.Timestamp(System
						.currentTimeMillis()));

				partnerContactList.add(partnerContact);
				partnerContactList = partnerContactRepository
						.save(partnerContactList);
			}

			// Note Topics
			if (subPartner.getPartnerSeasonNotes().getTopicList() != null
					&& subPartner.getPartnerSeasonNotes().getTopicList().size() > 0) {
				for (Topic subPartnerNoteTopic : subPartner
						.getPartnerSeasonNotes().getTopicList()) {
					PartnerNoteTopic partnerNoteTopic = new PartnerNoteTopic();
					partnerNoteTopic.setPartner(subPartnerDetails);
					partnerNoteTopic.setIsPublic(subPartnerNoteTopic
							.getPrivacy() == "public" ? CCIConstants.ACTIVE
							: CCIConstants.INACTIVE);
					partnerNoteTopic
							.setPartnerNoteTopicName(subPartnerNoteTopic
									.getTopicTitle());
					if (subPartnerNoteTopic.getNoteTags() != null
							&& subPartnerNoteTopic.getNoteTags().size() > 0) {
						for (NoteTags noteTags : subPartnerNoteTopic
								.getNoteTags()) {
							if (noteTags.getNoteTag().equalsIgnoreCase(
									CCIConstants.CompitetorInfo))
								partnerNoteTopic.setCompetitorInfo(noteTags
										.getNoteTagValue());
							if (noteTags.getNoteTag().equalsIgnoreCase(
									CCIConstants.Embassy_VisaInfo))
								partnerNoteTopic.setEmbassy_VisaInfo(noteTags
										.getNoteTagValue());
							if (noteTags.getNoteTag().equalsIgnoreCase(
									CCIConstants.HSPF1))
								partnerNoteTopic.setF1(noteTags
										.getNoteTagValue());
							if (noteTags.getNoteTag().equalsIgnoreCase(
									CCIConstants.GHT))
								partnerNoteTopic.setGht(noteTags
										.getNoteTagValue());
							if (noteTags.getNoteTag().equalsIgnoreCase(
									CCIConstants.Intern))
								partnerNoteTopic.setIntern(noteTags
										.getNoteTagValue());
							if (noteTags.getNoteTag().equalsIgnoreCase(
									CCIConstants.HSP_J1))
								partnerNoteTopic.setJ1(noteTags
										.getNoteTagValue());
							if (noteTags.getNoteTag().equalsIgnoreCase(
									CCIConstants.Meeting_Visit))
								partnerNoteTopic.setMeeting_visit(noteTags
										.getNoteTagValue());
							if (noteTags.getNoteTag().equalsIgnoreCase(
									CCIConstants.SeasonInfo))
								partnerNoteTopic.setSeasonInfo(noteTags
										.getNoteTagValue());
							if (noteTags.getNoteTag().equalsIgnoreCase(
									CCIConstants.STBound))
								partnerNoteTopic.setStInbound(noteTags
										.getNoteTagValue());
							if (noteTags.getNoteTag().equalsIgnoreCase(
									CCIConstants.Trainee))
								partnerNoteTopic.setTrainee(noteTags
										.getNoteTagValue());
							if (noteTags.getNoteTag().equalsIgnoreCase(
									CCIConstants.Work_Travels))
								partnerNoteTopic.setW_t(noteTags
										.getNoteTagValue());
						}
					}
					partnerNoteTopic = partnerNoteTopicRepository
							.save(partnerNoteTopic);
					// notes
					List<PartnerNote> partnerNoteList = new ArrayList<PartnerNote>();
					if (subPartnerNoteTopic.getNotes() != null
							&& subPartnerNoteTopic.getNotes().size() > 0) {
						for (Note subPartnerNote : subPartnerNoteTopic
								.getNotes()) {
							PartnerNote partnerNote = new PartnerNote();
							partnerNote
									.setPartnerNote(subPartnerNote.getNote());
							partnerNote.setCreatedBy(partnerNoteTopic
									.getPartner().getPartnerGoId());
							partnerNote.setCreatedOn(new java.sql.Timestamp(
									System.currentTimeMillis()));
							partnerNote.setModifiedBy(partnerNoteTopic
									.getPartner().getPartnerGoId());
							partnerNote.setModifiedOn(new java.sql.Timestamp(
									System.currentTimeMillis()));
							partnerNote.setPartnerNoteTopic(partnerNoteTopic);
							partnerNote.setPartner(subPartnerDetails);
							partnerNoteList.add(partnerNote);
						}
						partnerNoteList = partnerNoteRepository
								.save(partnerNoteList);
					}
				}
			}
			Login loginEmail = loginRepository.findByEmail(subPartner
					.getSubPartnerPrimaryContact().getEmail());
			if (loginEmail == null) {
				subPartner = setSubPartnerStatus(
						subPartner,
						CCIConstants.FAILURE,
						CCIConstants.TYPE_ERROR,
						ErrorCode.FAILED_TO_GET_EMAIL.getValue(),
						messageUtil
								.getMessage(SubPartnerMessageConstants.FAILED_TO_GET_EMAIL));
				LOGGER.error(messageUtil
						.getMessage(SubPartnerMessageConstants.FAILED_TO_GET_EMAIL));
				return createdSubPartner;
			}

			String body = "<p>This email was sent automatically by CCI Greenheart Online system to inform you that you an online account has been created for you.  </p></br>"
					+ "<p>Please go to the following page and follow the instructions to login to the system. </p> "
					+ "<p>"
					+ CCIUtils.formResetURL(request).concat(
							loginEmail.getKeyValue())
					+ "</p></br>"
					+ "<p>Thank you,</p><p>GO System Support.</p>";
			email.send(loginEmail.getEmail(),
					CCIConstants.CREATE_CCI_USER_SUBJECT, body, true);
			createdSubPartner = setSubPartnerStatus(createdSubPartner,
					CCIConstants.SUCCESS, CCIConstants.TYPE_INFO,
					ErrorCode.SUB_PARTNER_CODE.getValue(),
					messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));

			// createdSubPartner =
			// viewSubPartners(subPartnerDetails.getPartnerGoId().toString());
		} catch (CcighgoException e) {
			subPartner = setSubPartnerStatus(
					subPartner,
					CCIConstants.FAILURE,
					CCIConstants.TYPE_ERROR,
					ErrorCode.FAILED_CREATE_SUB_PARTNER.getValue(),
					messageUtil
							.getMessage(SubPartnerMessageConstants.FAILED_CREATE_SUB_PARTNER));
			LOGGER.error(messageUtil
					.getMessage(SubPartnerMessageConstants.FAILED_CREATE_SUB_PARTNER));
		}
		return createdSubPartner;
	}

	@Override
	@Transactional
	public SubPartner updateSubPartner(SubPartner subPartner) {

		SubPartner updatedSubPartner = null;
		try {
			Partner subPartnerDetails = partnerRepository.findOne(subPartner
					.getSubPartnerId());

			if (subPartnerDetails == null) {
				subPartner = setSubPartnerStatus(
						subPartner,
						CCIConstants.FAILURE,
						CCIConstants.TYPE_ERROR,
						ErrorCode.ERROR_GET_SUB_PARTNER_DETAILS.getValue(),
						messageUtil
								.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
				LOGGER.error(messageUtil
						.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
				return updatedSubPartner;
			}
			// agency details
			SubPartnerAgency SubPartnerAgency = null;
			if (subPartner.getSubPartnerAgency() != null) {
				SubPartnerAgency = subPartner.getSubPartnerAgency();
				subPartnerDetails.setCompanyName(SubPartnerAgency
						.getCompanyName());
				subPartnerDetails.setNeedPartnerReview(SubPartnerAgency
						.getNeedPartnerReview());
				subPartnerDetails.setDeliverDSForms(SubPartnerAgency
						.getDeliverDSForms());
				subPartnerDetails.setPayGreenheartDirectly(SubPartnerAgency
						.getPayGreenheartDirectly());
			}
			// Login
			if (subPartnerDetails.getGoIdSequence().getLogins() != null
					&& subPartnerDetails.getGoIdSequence().getLogins().size() > 0) {
				Login login = subPartnerDetails.getGoIdSequence().getLogins()
						.iterator().next();
				if (!login.getLoginName().equalsIgnoreCase(
						subPartner.getSubPartnerAgency().getUserName())) {
					if (loginRepository.findByLoginName(subPartner
							.getSubPartnerAgency().getUserName()) != null)
						login.setLoginName(subPartner.getSubPartnerAgency()
								.getUserName());
				}
				login.setModifiedBy(subPartner.getSubPartnerId());
				login.setModifiedOn(new java.sql.Timestamp(System
						.currentTimeMillis()));
				if (!login.getEmail().equalsIgnoreCase(
						subPartner.getSubPartnerPrimaryContact().getEmail())) {
					if (loginRepository.findByEmail(subPartner
							.getSubPartnerPrimaryContact().getEmail()) != null)
						login.setEmail(subPartner.getSubPartnerPrimaryContact()
								.getEmail());
				}
			}
			// physical address
			SubPartnerPhysicalAddress subPartnerPhysicalAddress = null;
			if (subPartner.getSubPartnerPhysicalAddress() != null) {
				subPartnerPhysicalAddress = subPartner
						.getSubPartnerPhysicalAddress();
				subPartnerDetails
						.setPhysicalAddressLineOne(subPartnerPhysicalAddress
								.getPhysicalAddressLineOne());
				subPartnerDetails
						.setPhysicalAddressLineTwo(subPartnerPhysicalAddress
								.getPhysicalAddressLineTwo());
				subPartnerDetails.setPhysicalCity(subPartnerPhysicalAddress
						.getPhysicalCity());
				subPartnerDetails.setPhysicalstate(subPartnerPhysicalAddress
						.getPhysicalstate());
				subPartnerDetails.setPhysicalZipcode(subPartnerPhysicalAddress
						.getPhysicalZipcode());

				LookupCountry subPartnerCountry1 = new LookupCountry();
				subPartnerCountry1.setCountryCode(subPartner
						.getSubPartnerPhysicalAddress()
						.getPhysicalSubPartnerCountryStatus()
						.getSubPartnerCountryCode());
				subPartnerCountry1.setCountryId(subPartner
						.getSubPartnerPhysicalAddress()
						.getPhysicalSubPartnerCountryStatus()
						.getSubPartnerCountryId());
				subPartnerCountry1.setCountryName(subPartner
						.getSubPartnerPhysicalAddress()
						.getPhysicalSubPartnerCountryStatus()
						.getSubPartnerCountryName());
				subPartnerDetails.setLookupCountry1(subPartnerCountry1);
			}
			// mailing address
			SubPartnerMailingAddress subPartnerMailingAddress = null;
			if (subPartner.getSubPartnerMailingAddress() != null) {
				subPartnerMailingAddress = subPartner
						.getSubPartnerMailingAddress();
				subPartnerDetails.setAddressLineOne(subPartnerMailingAddress
						.getAddressLineOne());
				subPartnerDetails.setAddressLineTwo(subPartnerMailingAddress
						.getAddressLineTwo());
				subPartnerDetails.setCity(subPartnerMailingAddress.getCity());
				subPartnerDetails.setState(subPartnerMailingAddress.getState());
				subPartnerDetails.setZipcode(subPartnerMailingAddress
						.getZipcode());

				LookupCountry subPartnerCountry2 = new LookupCountry();
				subPartnerCountry2.setCountryCode(subPartner
						.getSubPartnerMailingAddress()
						.getMailingSubPartnerCountryStatus()
						.getSubPartnerCountryCode());
				subPartnerCountry2.setCountryId(subPartner
						.getSubPartnerMailingAddress()
						.getMailingSubPartnerCountryStatus()
						.getSubPartnerCountryId());
				subPartnerCountry2.setCountryName(subPartner
						.getSubPartnerMailingAddress()
						.getMailingSubPartnerCountryStatus()
						.getSubPartnerCountryName());
				subPartnerDetails.setLookupCountry2(subPartnerCountry2);

				subPartnerDetails.setModifiedBy(subPartner.getSubPartnerId());
				subPartnerDetails.setModifiedOn(new java.sql.Timestamp(System
						.currentTimeMillis()));
			}

			// Sub partner CCI Contact
			Login cciLogin = loginRepository.findByLoginName(subPartner
					.getLoggedUsername());
			// TODO
			// subPartnerDetails.getPartnerCcicontacts().get(0).setCcistaffUser(cciLogin.getGoIdSequence().getCcistaffUser());

			// Sub partner Review Status
			subPartnerDetails
					.getPartnerReviewStatuses()
					.get(0)
					.getPartnerStatus2()
					.setPartnerStatusId(
							subPartner.getSubPartnerAgency()
									.getSubPartnerStatus()
									.getSubPartnerStatusId());
			subPartnerDetails
					.getPartnerReviewStatuses()
					.get(0)
					.getPartnerStatus2()
					.setPartnerStatusName(
							subPartner.getSubPartnerAgency()
									.getSubPartnerStatus()
									.getSubPartnerStatus());
			subPartnerDetails
					.getPartnerReviewStatuses()
					.get(0)
					.setCcistaffUser(
							cciLogin.getGoIdSequence().getCcistaffUser());

			// sub partner office
			List<PartnerOffice> partnerOfficeList = new ArrayList<PartnerOffice>();
			SubPartnerPhysicalAddress subPartnerOfficeAddress = null;
			if (subPartner.getSubPartnerPhysicalAddress() != null) {
				subPartnerOfficeAddress = subPartner
						.getSubPartnerPhysicalAddress();
				PartnerOffice partnerOffice = subPartnerDetails
						.getPartnerOffices().iterator().next();
				partnerOffice.setAdressOne(subPartnerOfficeAddress
						.getPhysicalAddressLineOne());
				partnerOffice.setAdressTwo(subPartnerOfficeAddress
						.getPhysicalAddressLineTwo());
				partnerOffice
						.setCity(subPartnerOfficeAddress.getPhysicalCity());
				partnerOffice.setFaxNumber(subPartner
						.getSubPartnerPrimaryContact().getFax());

				LookupCountry subPartnerCountry3 = new LookupCountry();
				subPartnerCountry3.setCountryId(subPartnerOfficeAddress
						.getPhysicalSubPartnerCountryStatus()
						.getSubPartnerCountryId());
				subPartnerCountry3.setCountryCode(subPartnerOfficeAddress
						.getPhysicalSubPartnerCountryStatus()
						.getSubPartnerCountryCode());
				subPartnerCountry3.setCountryName(subPartnerOfficeAddress
						.getPhysicalSubPartnerCountryStatus()
						.getSubPartnerCountryName());
				partnerOffice.setLookupCountry(subPartnerCountry3);

				partnerOffice.setModifiedBy(subPartner.getSubPartnerId());
				partnerOffice.setModifiedOn(new java.sql.Timestamp(System
						.currentTimeMillis()));
				partnerOffice.setPartnerOfficeType(partnerOfficeTypeRepository
						.findOne(Integer.valueOf(subPartner
								.getSubPartnerPrimaryContact()
								.getTypeOfPartnerUser())));
				partnerOffice.setPhoneNumber(subPartner
						.getSubPartnerPrimaryContact().getPhone());
				partnerOffice.setPostalCode(subPartnerOfficeAddress
						.getPhysicalZipcode());
				partnerOffice.setState(subPartnerOfficeAddress
						.getPhysicalstate());

				partnerOfficeList.add(partnerOffice);
			}
			subPartnerDetails.setPartnerOffices(partnerOfficeList);

			// sub partner contact
			List<PartnerContact> partnerContactList = new ArrayList<PartnerContact>();
			SubPartnerPrimaryContact subPartnerPrimaryContact = null;
			if (subPartner.getSubPartnerPrimaryContact() != null) {
				subPartnerPrimaryContact = subPartner
						.getSubPartnerPrimaryContact();
				PartnerContact partnerContact = subPartnerDetails
						.getPartnerContacts().iterator().next();
				Salutation salutation = salutationRepository
						.findOne(subPartnerPrimaryContact.getSalutation()
								.getSalutationId());
				partnerContact.setSalutation(salutation);
				partnerContact.setTitle(subPartnerPrimaryContact.getTitle());
				partnerContact.setFirstName(subPartnerPrimaryContact
						.getFirstName());
				partnerContact.setLastName(subPartnerPrimaryContact
						.getLastName());
				partnerContact.setEmail(subPartnerPrimaryContact.getEmail());
				partnerContact.setPhone(subPartnerPrimaryContact.getPhone());
				partnerContact.setEmergencyPhone(subPartnerPrimaryContact
						.getEmergencyPhone());
				partnerContact.setFax(subPartnerPrimaryContact.getFax());
				partnerContact
						.setReceiveNotificationEmails(subPartnerPrimaryContact
								.getReceiveNotificationEmailFromCCI());
				partnerContact
						.setSkypeId(subPartnerPrimaryContact.getSkypeId());
				partnerContact
						.setWebsite(subPartnerPrimaryContact.getWebsite());

				partnerContactList.add(partnerContact);
			}
			subPartnerDetails.setPartnerContacts(partnerContactList);

			// TODO: notes need to update if required
			partnerRepository.saveAndFlush(subPartnerDetails);
			updatedSubPartner = setSubPartnerStatus(updatedSubPartner,
					CCIConstants.SUCCESS, CCIConstants.TYPE_INFO,
					ErrorCode.SUB_PARTNER_CODE.getValue(),
					messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
			updatedSubPartner = viewSubPartners(String
					.valueOf(subPartnerDetails.getPartnerGoId()));
		} catch (CcighgoException e) {
			subPartner = setSubPartnerStatus(
					subPartner,
					CCIConstants.FAILURE,
					CCIConstants.TYPE_ERROR,
					ErrorCode.FAILED_UPDATE_SUB_PARTNER.getValue(),
					messageUtil
							.getMessage(SubPartnerMessageConstants.FAILED_UPDATE_SUB_PARTNER));
			LOGGER.error(messageUtil
					.getMessage(SubPartnerMessageConstants.FAILED_UPDATE_SUB_PARTNER));
		}
		return updatedSubPartner;
	}

	private SubPartner setSubPartnerStatus(SubPartner subPartner, String code,
			String type, int serviceCode, String message) {
		if (subPartner == null)
			subPartner = new SubPartner();
		subPartner.setStatus(componentUtils.getStatus(code, type, serviceCode,
				message));
		return subPartner;
	}

	private SubPartnerDetails setSubPartnerDetailsStatus(
			SubPartnerDetails subPartnerDetails, String code, String type,
			int serviceCode, String message) {
		if (subPartnerDetails == null)
			subPartnerDetails = new SubPartnerDetails();
		subPartnerDetails.setStatus(componentUtils.getStatus(code, type,
				serviceCode, message));
		return subPartnerDetails;
	}

	@Override
	@Transactional
	public com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail getSubPartnerDetail(
			String subPartnerId) {
		com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail subPartnerDetail = new com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail();
		try {
			Partner partnerSubPartner = partnerRepository.findOne(Integer
					.valueOf(subPartnerId));
			if (partnerSubPartner == null) {
				subPartnerDetail
						.setStatus(componentUtils.getStatus(
								CCIConstants.FAILURE,
								CCIConstants.TYPE_ERROR,
								ErrorCode.ERROR_PARTNER_GET_DETAILS.getValue(),
								messageUtil
										.getMessage(SubPartnerMessageConstants.INVALID_SUB_PARTNER_ID)));
				LOGGER.error(messageUtil
						.getMessage(SubPartnerMessageConstants.INVALID_SUB_PARTNER_ID));
				return subPartnerDetail;
			}
			Details details = new Details();
			details.setAgencyName(partnerSubPartner.getCompanyName());
			details.setLogoImageURL("");
			details.setLogoUserName("");
			details.setNeedsPartnerReview(partnerSubPartner
					.getNeedPartnerReview() == CCIConstants.ACTIVE ? true
					: false); // ternary operator
			details.setPayGreenHeartDirectly(partnerSubPartner.getPayGreenheartDirectly()==CCIConstants.ACTIVE?true:false);
			details.setRecivevisaforms(partnerSubPartner.getDeliverDSForms()==CCIConstants.ACTIVE?true:false);
			List<PartnerUser> partnerUsers=partnerSubPartner.getPartnerUsers();
			PartnerUser partnerUser=new PartnerUser();
			if(partnerUsers!=null)
			{
			for(PartnerUser pu:partnerUsers)
			{
				if(pu!=null)
				if(pu.getPartner().getPartnerGoId()==Integer.valueOf(subPartnerId))
				{
					partnerUser=pu;
					break;
				}
			}
		        Login login=partnerUser.getLogin();
		        if(login!=null)
		        {
		        details.setUsername(login.getLoginName());
		        details.setNewPassword(login.getPassword());
		        }
			}
			
            details.setPayGreenHeartDirectly(partnerSubPartner.getPayGreenheartDirectly()==CCIConstants.ACTIVE?true:false);
        
			/*
			 * Primary Contact
			 */
			PartnerContact partnerContact = new PartnerContact();
			SubPartnersPrimaryContact subPartnerPrimaryContact = new SubPartnersPrimaryContact();
			if (partnerSubPartner.getPartnerContacts() != null
					&& partnerSubPartner.getPartnerContacts().size() > 0) {
				List<PartnerContact> partnerContactList = partnerSubPartner
						.getPartnerContacts();
				for (PartnerContact ptc : partnerContactList) {
					if (ptc.getIsPrimary() == CCIConstants.ACTIVE ? true
							: false) {
						partnerContact = ptc;
						break;
					}
				}
				if (partnerContact != null) {
				List<TypeofPartnerUser> typeofPartnerUser=new ArrayList<TypeofPartnerUser>();
				
				
					Salutation subPartnerSalutation = partnerContact
							.getSalutation();
					com.ccighgo.service.transport.partner.beans.subpartnerdetail.Salutation salutation = new com.ccighgo.service.transport.partner.beans.subpartnerdetail.Salutation();

					if (subPartnerSalutation != null) {
						salutation.setSalutationId(subPartnerSalutation
								.getSalutationId());
						salutation.setSalutationName(subPartnerSalutation
								.getSalutationName());
						salutation.setActive(subPartnerSalutation.getActive()==CCIConstants.ACTIVE?true:false);
					}
					subPartnerPrimaryContact.setSalutation(salutation);
					subPartnerPrimaryContact
							.setTitle(partnerContact.getTitle());
					subPartnerPrimaryContact.setFirstName(partnerContact
							.getFirstName());
					subPartnerPrimaryContact.setLastName(partnerContact
							.getLastName());
					subPartnerPrimaryContact
							.setEmail(partnerContact.getEmail());
					subPartnerPrimaryContact
							.setPhone(partnerContact.getPhone());
					subPartnerPrimaryContact.setEmergencyPhone(partnerContact
							.getEmergencyPhone());
					subPartnerPrimaryContact.setFax(partnerContact.getFax());
					if(partnerContact.getReceiveNotificationEmails()!=null)
					subPartnerPrimaryContact.setReciveNotificationemailfromcc(partnerContact.getReceiveNotificationEmails()==CCIConstants.ACTIVE?true:false);
					subPartnerPrimaryContact.setSkypeId(partnerContact.getSkypeId());
					subPartnerPrimaryContact.setWebsite(partnerContact.getWebsite());
					//subPartnerPrimaryContact.setTypeofpartneruser(partnerContact.get);
				}
				if(partnerUser!=null)
				{
					List<PartnerUserRole> partnerUserRolls=partnerUser.getPartnerUserRoles();
					for(PartnerUserRole pur:partnerUserRolls)
					{
						TypeofPartnerUser tpu=new TypeofPartnerUser();
						tpu.setPartnerUserRoleId(tpu.getPartnerUserRoleId());
						tpu.setPartnerUserRoleName(pur.getPartnerUserRoleName());
						subPartnerPrimaryContact.getTypeofpartneruser().add(tpu);
					}
				}
				
			}

			/*
			 * Physical Address
			 */
			
			SubPartnersPhysicalAddress subPartnerPhysicalAddress = new SubPartnersPhysicalAddress();
			subPartnerPhysicalAddress.setPhysicalAddress1(partnerSubPartner
					.getPhysicalAddressLineOne());
			subPartnerPhysicalAddress.setPhysicalAddress2(partnerSubPartner
					.getPhysicalAddressLineTwo());
			subPartnerPhysicalAddress.setPhysicalAddressCity(partnerSubPartner
					.getPhysicalCity());
			subPartnerPhysicalAddress
					.setPhysicalAddressStateOrProvince(partnerSubPartner
							.getPhysicalstate());
			subPartnerPhysicalAddress
					.setPhysicalAddressZipCode(partnerSubPartner
							.getPhysicalZipcode());

			

			/*
			 * Mailing Address
			 */

			SubPartnersMailingAddress subPartnersMailingAddress = new SubPartnersMailingAddress();

			if (partnerSubPartner.getMailingAddressIsSameAsPhysicalAdress() == CCIConstants.ACTIVE ? true: false) {
				subPartnersMailingAddress.setMailingAddress1(partnerSubPartner
						.getPhysicalAddressLineOne());
				subPartnersMailingAddress.setMailingAddress2(partnerSubPartner
						.getPhysicalAddressLineTwo());
				subPartnersMailingAddress
						.setMailingAddressCity(partnerSubPartner
								.getPhysicalCity());
				subPartnersMailingAddress
						.setMailingAddressZipCode(partnerSubPartner
								.getPhysicalZipcode());
			} else {
				subPartnersMailingAddress.setMailingAddress1(partnerSubPartner
						.getAddressLineOne());
				subPartnersMailingAddress.setMailingAddress2(partnerSubPartner
						.getAddressLineTwo());
				subPartnersMailingAddress
						.setMailingAddressCity(partnerSubPartner.getCity());
				subPartnersMailingAddress
						.setMailingAddressStateOrProvince(partnerSubPartner
								.getState());
				
			}
			List<PartnerNoteTopic> partnerTopics = partnerNoteTopicRepository.findAllPartnerNoteTopicByPartnerId(Integer.valueOf(subPartnerId));
			
			if(partnerTopics!=null)
			{
			for(PartnerNoteTopic partnerTopic:partnerTopics )
			{
				Topics tpc=new Topics();
				tpc.setPartnerNoteTopicName(partnerTopic.getPartnerNoteTopicName());
				tpc.setPartnerNoteTopicId(partnerTopic.getPartnerNoteTopicId());
				tpc.setCompetitorInfo(partnerTopic.getCompetitorInfo()==CCIConstants.ACTIVE?true:false);
				tpc.setEmbassyVisaInfo(partnerTopic.getEmbassy_VisaInfo()==CCIConstants.ACTIVE?true:false);
				tpc.setIsPublic(partnerTopic.getIsPublic()==CCIConstants.ACTIVE?true:false);
				tpc.setWT(partnerTopic.getW_t()==CCIConstants.ACTIVE?true:false);
				tpc.setJ1(partnerTopic.getJ1()==CCIConstants.ACTIVE?true:false);
				tpc.setGht(partnerTopic.getGht()==CCIConstants.ACTIVE?true:false);
				tpc.setStInbound(partnerTopic.getStInbound()==CCIConstants.ACTIVE?true:false);
				tpc.setMeetingVisit(partnerTopic.getMeeting_visit()==CCIConstants.ACTIVE?true:false);			
				tpc.setSeasonInfo(partnerTopic.getSeasonInfo()==CCIConstants.ACTIVE?true:false);
				tpc.setF1(partnerTopic.getF1()==CCIConstants.ACTIVE?true:false);
				tpc.setIntern(partnerTopic.getIntern()==CCIConstants.ACTIVE?true:false);
				tpc.setTrainee(partnerTopic.getTrainee()==CCIConstants.ACTIVE?true:false);
			List<PartnerNote> partnerNotes =partnerTopic.getPartnerNotes();
					 /*partnerNoteRepository.findAllPartnerNoteByPartnerId(Integer
						.valueOf(subPartnerId));*/
	            if (partnerNotes != null) {
	               for (PartnerNote partnerNote : partnerNotes) {
	            	   
	                  SubPartnerScreeningNotes note = new SubPartnerScreeningNotes();
	            
	                 PartnerUser notePartnerUser=partnerUserRepository.findOne(partnerNote.getCreatedBy());
	                  //
	                  if (notePartnerUser != null) {
	                     com.ccighgo.service.transport.partner.beans.subpartnerdetail.NoteUserCreator noteCreator = new com.ccighgo.service.transport.partner.beans.subpartnerdetail.NoteUserCreator();
	                     noteCreator.setPhotoUrl(notePartnerUser.getPhoto());
	                     noteCreator.setRole(notePartnerUser.getTitle());
	                     noteCreator.setUserName(notePartnerUser.getFirstName()+" "+notePartnerUser.getLastName());
	                     note.setCreatedBy(noteCreator);
	                    note.setUserId(notePartnerUser.getPartnerUserId());
	                  }
	                  note.setCreatedOn(DateUtils.getDateAndTime(partnerNote.getCreatedOn()));
	                  note.setNoteValue(partnerNote.getPartnerNote());
	                  note.setTopicId(tpc.getPartnerNoteTopicId());
	                  note.setPartnerId(Integer.valueOf(subPartnerId));
	                  
	                  tpc.getPartnerNotes().add(note);
	               }
	                
	               }
	            subPartnerDetail.getTopic().add(tpc);
	            }
			}
			subPartnerDetail.setPartnerDetail(details);
			subPartnerDetail
					.setSubPartnerPrimaryContact(subPartnerPrimaryContact);
			subPartnerDetail
					.setSubPartnerPhysicalAddress(subPartnerPhysicalAddress);
			subPartnerDetail
					.setSubPartnerMailingAddress(subPartnersMailingAddress);
			
			subPartnerDetail.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
	                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
			

		} catch (CcighgoException e) {
			subPartnerDetail
					.setStatus(componentUtils.getStatus(
							CCIConstants.FAILURE,
							CCIConstants.TYPE_ERROR,
							ErrorCode.ERROR_PARTNER_GET_DETAILS.getValue(),
							messageUtil
									.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS)));
			LOGGER.error(messageUtil
					.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS)
					+ ":" + e);
		}
		return subPartnerDetail;
	}
	@Override
	public WSDefaultResponse addSubPartnerScreenNote(SubPartnerScreeningNotes noteDetail) {
		WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
		try
		{
		PartnerNote note=new PartnerNote();
		note.setCreatedBy(noteDetail.getUserId());
        note.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));       
        note.setModifiedBy(noteDetail.getUserId());
        
        note.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         Partner partner = partnerRepository.findOne(noteDetail.getPartnerId());
        note.setPartner(partner);
        note.setPartnerNote(noteDetail.getNoteValue());
      
        PartnerNoteTopic partnerNoteTopic = partnerNoteTopicRepository.findOne(noteDetail.getTopicId());
        note.setPartnerNoteTopic(partnerNoteTopic);
        
        partnerNoteRepository.saveAndFlush(note);
        wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SUB_PARTNER_NOTE_CREATED.getValue(),
	               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
		}
		catch(Exception e)
		{
			ExceptionUtil.logException(e, LOGGER);
	        
			wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_ADD_SUBPARTNER_NOTE.getValue(),
	                 messageUtil.getMessage(SubPartnerMessageConstants.FAILED_TO_ADD_SUBPARTNER_NOTE)));
	           LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.FAILED_TO_ADD_SUBPARTNER_NOTE));
	           
		}
		return wsDefaultResponse;
	}
	@Override
	@Transactional
	public WSDefaultResponse updatePartnerUserStatus(String partnerUserId,
			String statusVal) {
		 WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
			try
			{
				PartnerReviewStatus partnerReviewStatus = partnerReviewStatusRepository.findApplicationStatusByGoId(Integer.parseInt(partnerUserId));
				PartnerStatus partnerStatus2=partnerReviewStatus.getPartnerStatus2();
				partnerStatus2.setPartnerStatusName(statusVal);
				partnerReviewStatusRepository.saveAndFlush(partnerReviewStatus);
		         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.PARTNER_APPLICATION_STATUS_UPDATED.getValue(),
		               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
				
			}catch(Exception e) {
		         ExceptionUtil.logException(e, LOGGER);
		         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.CANT_UPDATE_PARTNER_APPLICATION_STATUS.getValue(),
		                 messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_PARTNER_APPLICATION_STATUS)));
		           LOGGER.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_PARTNER_APPLICATION_STATUS));
				
			}
		return wsDefaultResponse;
	}

	



	
}
