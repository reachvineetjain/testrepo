package com.ccighgo.db.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the Partner database table.
 * 
 */
@Entity
@Table(name="Partner")
@NamedQuery(name="Partner.findAll", query="SELECT p FROM Partner p")
public class Partner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer partnerGoId;

	@Column(length=150)
	private String acronym;

	@Column(length=150)
	private String addressLineOne;

	@Column(length=150)
	private String addressLineTwo;

	@Column(length=1000)
	private String billingNotes;

	private Byte canHaveSubPartner;

	@Column(length=50)
	private String city;

	@Column(length=250)
	private String companyName;

	@Column(length=2000)
	private String contactNotes;

	@Column(length=50)
	private String contractSigner;

	private Integer createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	private Integer dandBNumber;

	private Byte deliverDSForms;

	@Column(length=100)
	private String email;

	private Byte hasSubPartners;

	@Column(length=100)
	private String invoiceMail;

	private Byte isSubPartner;

	private Integer lastSelectedProgramId;

	private Byte mailingAddressIsSameAsPhysicalAdress;

	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	private Byte multiCountrySender;

	private Byte needPartnerReview;

	private Integer oldId;

	private Integer parentPartnerGoId;

	private Byte participantELTISRequired;

	private Byte participantMedicalReleaseRequired;

	private Byte participantSLEPRequired;

	private Byte participantTranscriptRequired;

	@Column(length=64)
	private String partnerGuid;

	@Column(length=300)
	private String partnerLogo;

	private Byte payGreenheartDirectly;

	@Column(length=150)
	private String physicalAddressLineOne;

	@Column(length=150)
	private String physicalAddressLineTwo;

	@Column(length=50)
	private String physicalCity;

	@Column(length=50)
	private String physicalstate;

	@Column(length=15)
	private String physicalZipcode;

	@Column(length=40)
	private String quickbooksCode;

	private Byte receiveAYPMails;

	@Column(length=50)
	private String state;

	private Byte subscribeToCCINewsletter;

	private Byte unguaranteedFormRequired;

	@Column(length=15)
	private String zipcode;

	//bi-directional many-to-one association to Participant
	@OneToMany(mappedBy="partner1")
	private List<Participant> participants1;

	//bi-directional many-to-one association to Participant
	@OneToMany(mappedBy="partner2")
	private List<Participant> participants2;

	//bi-directional one-to-one association to GoIdSequence
	@OneToOne
	@JoinColumn(name="partnerGoId", nullable=false, insertable=false, updatable=false)
	private GoIdSequence goIdSequence;

	//bi-directional many-to-one association to LookupCountry
	@ManyToOne
	@JoinColumn(name="physicalcountryId")
	private LookupCountry lookupCountry1;

	//bi-directional many-to-one association to LookupCountry
	@ManyToOne
	@JoinColumn(name="countryId")
	private LookupCountry lookupCountry2;

	//bi-directional many-to-one association to PartnerAgentInquiry
	@OneToMany(mappedBy="partner")
	private List<PartnerAgentInquiry> partnerAgentInquiries;

	//bi-directional many-to-one association to PartnerAnnouncement
	@OneToMany(mappedBy="partner")
	private List<PartnerAnnouncement> partnerAnnouncements;

	//bi-directional many-to-one association to PartnerContact
	@OneToMany(mappedBy="partner")
	private List<PartnerContact> partnerContacts;

	//bi-directional many-to-one association to PartnerDocument
	@OneToMany(mappedBy="partner")
	private List<PartnerDocument> partnerDocuments;

	//bi-directional many-to-one association to PartnerHelpRequest
	@OneToMany(mappedBy="partner1")
	private List<PartnerHelpRequest> partnerHelpRequests1;

	//bi-directional many-to-one association to PartnerHelpRequest
	@OneToMany(mappedBy="partner2")
	private List<PartnerHelpRequest> partnerHelpRequests2;

	//bi-directional many-to-one association to PartnerMessage
	@OneToMany(mappedBy="partner")
	private List<PartnerMessage> partnerMessages;

	//bi-directional many-to-one association to PartnerNoteTopic
	@OneToMany(mappedBy="partner")
	private List<PartnerNoteTopic> partnerNoteTopics;

	//bi-directional many-to-one association to PartnerNote
	@OneToMany(mappedBy="partner")
	private List<PartnerNote> partnerNotes;

	//bi-directional many-to-one association to PartnerOffice
	@OneToMany(mappedBy="partner")
	private List<PartnerOffice> partnerOffices;

	//bi-directional many-to-one association to PartnerProgram
	@OneToMany(mappedBy="partner")
	private List<PartnerProgram> partnerPrograms;

	//bi-directional many-to-one association to PartnerQuickStatsCategoryAggregate
	@OneToMany(mappedBy="partner")
	private List<PartnerQuickStatsCategoryAggregate> partnerQuickStatsCategoryAggregates;

	//bi-directional many-to-one association to PartnerQuickStatsTypeAggregate
	@OneToMany(mappedBy="partner")
	private List<PartnerQuickStatsTypeAggregate> partnerQuickStatsTypeAggregates;

	//bi-directional many-to-one association to PartnerReferenceCheck
	@OneToMany(mappedBy="partner")
	private List<PartnerReferenceCheck> partnerReferenceChecks;

	//bi-directional many-to-one association to PartnerReviewStatus
	@OneToMany(mappedBy="partner")
	private List<PartnerReviewStatus> partnerReviewStatuses;

	//bi-directional many-to-one association to PartnerSeason
	@OneToMany(mappedBy="partner")
	private List<PartnerSeason> partnerSeasons;

	//bi-directional many-to-one association to PartnerUser
	@OneToMany(mappedBy = "partner", fetch = FetchType.EAGER)
   @Fetch(value = FetchMode.SUBSELECT)
	private List<PartnerUser> partnerUsers;

	//bi-directional many-to-one association to PartnerWorkQueue
	@OneToMany(mappedBy="partner")
	private List<PartnerWorkQueue> partnerWorkQueues;

	//bi-directional many-to-one association to PartnerWorkQueueCategoryAggregate
	@OneToMany(mappedBy="partner")
	private List<PartnerWorkQueueCategoryAggregate> partnerWorkQueueCategoryAggregates;

	//bi-directional many-to-one association to PartnerWorkQueueTypeAggregate
	@OneToMany(mappedBy="partner")
	private List<PartnerWorkQueueTypeAggregate> partnerWorkQueueTypeAggregates;

	public Partner() {
	}

	public Integer getPartnerGoId() {
		return this.partnerGoId;
	}

	public void setPartnerGoId(Integer partnerGoId) {
		this.partnerGoId = partnerGoId;
	}

	public String getAcronym() {
		return this.acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getAddressLineOne() {
		return this.addressLineOne;
	}

	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	public String getAddressLineTwo() {
		return this.addressLineTwo;
	}

	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}

	public String getBillingNotes() {
		return this.billingNotes;
	}

	public void setBillingNotes(String billingNotes) {
		this.billingNotes = billingNotes;
	}

	public Byte getCanHaveSubPartner() {
		return this.canHaveSubPartner;
	}

	public void setCanHaveSubPartner(Byte canHaveSubPartner) {
		this.canHaveSubPartner = canHaveSubPartner;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactNotes() {
		return this.contactNotes;
	}

	public void setContactNotes(String contactNotes) {
		this.contactNotes = contactNotes;
	}

	public String getContractSigner() {
		return this.contractSigner;
	}

	public void setContractSigner(String contractSigner) {
		this.contractSigner = contractSigner;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getDandBNumber() {
		return this.dandBNumber;
	}

	public void setDandBNumber(Integer dandBNumber) {
		this.dandBNumber = dandBNumber;
	}

	public Byte getDeliverDSForms() {
		return this.deliverDSForms;
	}

	public void setDeliverDSForms(Byte deliverDSForms) {
		this.deliverDSForms = deliverDSForms;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Byte getHasSubPartners() {
		return this.hasSubPartners;
	}

	public void setHasSubPartners(Byte hasSubPartners) {
		this.hasSubPartners = hasSubPartners;
	}

	public String getInvoiceMail() {
		return this.invoiceMail;
	}

	public void setInvoiceMail(String invoiceMail) {
		this.invoiceMail = invoiceMail;
	}

	public Byte getIsSubPartner() {
		return this.isSubPartner;
	}

	public void setIsSubPartner(Byte isSubPartner) {
		this.isSubPartner = isSubPartner;
	}

	public Integer getLastSelectedProgramId() {
		return this.lastSelectedProgramId;
	}

	public void setLastSelectedProgramId(Integer lastSelectedProgramId) {
		this.lastSelectedProgramId = lastSelectedProgramId;
	}

	public Byte getMailingAddressIsSameAsPhysicalAdress() {
		return this.mailingAddressIsSameAsPhysicalAdress;
	}

	public void setMailingAddressIsSameAsPhysicalAdress(Byte mailingAddressIsSameAsPhysicalAdress) {
		this.mailingAddressIsSameAsPhysicalAdress = mailingAddressIsSameAsPhysicalAdress;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Byte getMultiCountrySender() {
		return this.multiCountrySender;
	}

	public void setMultiCountrySender(Byte multiCountrySender) {
		this.multiCountrySender = multiCountrySender;
	}

	public Byte getNeedPartnerReview() {
		return this.needPartnerReview;
	}

	public void setNeedPartnerReview(Byte needPartnerReview) {
		this.needPartnerReview = needPartnerReview;
	}

	public Integer getOldId() {
		return this.oldId;
	}

	public void setOldId(Integer oldId) {
		this.oldId = oldId;
	}

	public Integer getParentPartnerGoId() {
		return this.parentPartnerGoId;
	}

	public void setParentPartnerGoId(Integer parentPartnerGoId) {
		this.parentPartnerGoId = parentPartnerGoId;
	}

	public Byte getParticipantELTISRequired() {
		return this.participantELTISRequired;
	}

	public void setParticipantELTISRequired(Byte participantELTISRequired) {
		this.participantELTISRequired = participantELTISRequired;
	}

	public Byte getParticipantMedicalReleaseRequired() {
		return this.participantMedicalReleaseRequired;
	}

	public void setParticipantMedicalReleaseRequired(Byte participantMedicalReleaseRequired) {
		this.participantMedicalReleaseRequired = participantMedicalReleaseRequired;
	}

	public Byte getParticipantSLEPRequired() {
		return this.participantSLEPRequired;
	}

	public void setParticipantSLEPRequired(Byte participantSLEPRequired) {
		this.participantSLEPRequired = participantSLEPRequired;
	}

	public Byte getParticipantTranscriptRequired() {
		return this.participantTranscriptRequired;
	}

	public void setParticipantTranscriptRequired(Byte participantTranscriptRequired) {
		this.participantTranscriptRequired = participantTranscriptRequired;
	}

	public String getPartnerGuid() {
		return this.partnerGuid;
	}

	public void setPartnerGuid(String partnerGuid) {
		this.partnerGuid = partnerGuid;
	}

	public String getPartnerLogo() {
		return this.partnerLogo;
	}

	public void setPartnerLogo(String partnerLogo) {
		this.partnerLogo = partnerLogo;
	}

	public Byte getPayGreenheartDirectly() {
		return this.payGreenheartDirectly;
	}

	public void setPayGreenheartDirectly(Byte payGreenheartDirectly) {
		this.payGreenheartDirectly = payGreenheartDirectly;
	}

	public String getPhysicalAddressLineOne() {
		return this.physicalAddressLineOne;
	}

	public void setPhysicalAddressLineOne(String physicalAddressLineOne) {
		this.physicalAddressLineOne = physicalAddressLineOne;
	}

	public String getPhysicalAddressLineTwo() {
		return this.physicalAddressLineTwo;
	}

	public void setPhysicalAddressLineTwo(String physicalAddressLineTwo) {
		this.physicalAddressLineTwo = physicalAddressLineTwo;
	}

	public String getPhysicalCity() {
		return this.physicalCity;
	}

	public void setPhysicalCity(String physicalCity) {
		this.physicalCity = physicalCity;
	}

	public String getPhysicalstate() {
		return this.physicalstate;
	}

	public void setPhysicalstate(String physicalstate) {
		this.physicalstate = physicalstate;
	}

	public String getPhysicalZipcode() {
		return this.physicalZipcode;
	}

	public void setPhysicalZipcode(String physicalZipcode) {
		this.physicalZipcode = physicalZipcode;
	}

	public String getQuickbooksCode() {
		return this.quickbooksCode;
	}

	public void setQuickbooksCode(String quickbooksCode) {
		this.quickbooksCode = quickbooksCode;
	}

	public Byte getReceiveAYPMails() {
		return this.receiveAYPMails;
	}

	public void setReceiveAYPMails(Byte receiveAYPMails) {
		this.receiveAYPMails = receiveAYPMails;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Byte getSubscribeToCCINewsletter() {
		return this.subscribeToCCINewsletter;
	}

	public void setSubscribeToCCINewsletter(Byte subscribeToCCINewsletter) {
		this.subscribeToCCINewsletter = subscribeToCCINewsletter;
	}

	public Byte getUnguaranteedFormRequired() {
		return this.unguaranteedFormRequired;
	}

	public void setUnguaranteedFormRequired(Byte unguaranteedFormRequired) {
		this.unguaranteedFormRequired = unguaranteedFormRequired;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public List<Participant> getParticipants1() {
		return this.participants1;
	}

	public void setParticipants1(List<Participant> participants1) {
		this.participants1 = participants1;
	}

	public Participant addParticipants1(Participant participants1) {
		getParticipants1().add(participants1);
		participants1.setPartner1(this);

		return participants1;
	}

	public Participant removeParticipants1(Participant participants1) {
		getParticipants1().remove(participants1);
		participants1.setPartner1(null);

		return participants1;
	}

	public List<Participant> getParticipants2() {
		return this.participants2;
	}

	public void setParticipants2(List<Participant> participants2) {
		this.participants2 = participants2;
	}

	public Participant addParticipants2(Participant participants2) {
		getParticipants2().add(participants2);
		participants2.setPartner2(this);

		return participants2;
	}

	public Participant removeParticipants2(Participant participants2) {
		getParticipants2().remove(participants2);
		participants2.setPartner2(null);

		return participants2;
	}

	public GoIdSequence getGoIdSequence() {
		return this.goIdSequence;
	}

	public void setGoIdSequence(GoIdSequence goIdSequence) {
		this.goIdSequence = goIdSequence;
	}

	public LookupCountry getLookupCountry1() {
		return this.lookupCountry1;
	}

	public void setLookupCountry1(LookupCountry lookupCountry1) {
		this.lookupCountry1 = lookupCountry1;
	}

	public LookupCountry getLookupCountry2() {
		return this.lookupCountry2;
	}

	public void setLookupCountry2(LookupCountry lookupCountry2) {
		this.lookupCountry2 = lookupCountry2;
	}

	public List<PartnerAgentInquiry> getPartnerAgentInquiries() {
		return this.partnerAgentInquiries;
	}

	public void setPartnerAgentInquiries(List<PartnerAgentInquiry> partnerAgentInquiries) {
		this.partnerAgentInquiries = partnerAgentInquiries;
	}

	public PartnerAgentInquiry addPartnerAgentInquiry(PartnerAgentInquiry partnerAgentInquiry) {
		getPartnerAgentInquiries().add(partnerAgentInquiry);
		partnerAgentInquiry.setPartner(this);

		return partnerAgentInquiry;
	}

	public PartnerAgentInquiry removePartnerAgentInquiry(PartnerAgentInquiry partnerAgentInquiry) {
		getPartnerAgentInquiries().remove(partnerAgentInquiry);
		partnerAgentInquiry.setPartner(null);

		return partnerAgentInquiry;
	}

	public List<PartnerAnnouncement> getPartnerAnnouncements() {
		return this.partnerAnnouncements;
	}

	public void setPartnerAnnouncements(List<PartnerAnnouncement> partnerAnnouncements) {
		this.partnerAnnouncements = partnerAnnouncements;
	}

	public PartnerAnnouncement addPartnerAnnouncement(PartnerAnnouncement partnerAnnouncement) {
		getPartnerAnnouncements().add(partnerAnnouncement);
		partnerAnnouncement.setPartner(this);

		return partnerAnnouncement;
	}

	public PartnerAnnouncement removePartnerAnnouncement(PartnerAnnouncement partnerAnnouncement) {
		getPartnerAnnouncements().remove(partnerAnnouncement);
		partnerAnnouncement.setPartner(null);

		return partnerAnnouncement;
	}

	public List<PartnerContact> getPartnerContacts() {
		return this.partnerContacts;
	}

	public void setPartnerContacts(List<PartnerContact> partnerContacts) {
		this.partnerContacts = partnerContacts;
	}

	public PartnerContact addPartnerContact(PartnerContact partnerContact) {
		getPartnerContacts().add(partnerContact);
		partnerContact.setPartner(this);

		return partnerContact;
	}

	public PartnerContact removePartnerContact(PartnerContact partnerContact) {
		getPartnerContacts().remove(partnerContact);
		partnerContact.setPartner(null);

		return partnerContact;
	}

	public List<PartnerDocument> getPartnerDocuments() {
		return this.partnerDocuments;
	}

	public void setPartnerDocuments(List<PartnerDocument> partnerDocuments) {
		this.partnerDocuments = partnerDocuments;
	}

	public PartnerDocument addPartnerDocument(PartnerDocument partnerDocument) {
		getPartnerDocuments().add(partnerDocument);
		partnerDocument.setPartner(this);

		return partnerDocument;
	}

	public PartnerDocument removePartnerDocument(PartnerDocument partnerDocument) {
		getPartnerDocuments().remove(partnerDocument);
		partnerDocument.setPartner(null);

		return partnerDocument;
	}

	public List<PartnerHelpRequest> getPartnerHelpRequests1() {
		return this.partnerHelpRequests1;
	}

	public void setPartnerHelpRequests1(List<PartnerHelpRequest> partnerHelpRequests1) {
		this.partnerHelpRequests1 = partnerHelpRequests1;
	}

	public PartnerHelpRequest addPartnerHelpRequests1(PartnerHelpRequest partnerHelpRequests1) {
		getPartnerHelpRequests1().add(partnerHelpRequests1);
		partnerHelpRequests1.setPartner1(this);

		return partnerHelpRequests1;
	}

	public PartnerHelpRequest removePartnerHelpRequests1(PartnerHelpRequest partnerHelpRequests1) {
		getPartnerHelpRequests1().remove(partnerHelpRequests1);
		partnerHelpRequests1.setPartner1(null);

		return partnerHelpRequests1;
	}

	public List<PartnerHelpRequest> getPartnerHelpRequests2() {
		return this.partnerHelpRequests2;
	}

	public void setPartnerHelpRequests2(List<PartnerHelpRequest> partnerHelpRequests2) {
		this.partnerHelpRequests2 = partnerHelpRequests2;
	}

	public PartnerHelpRequest addPartnerHelpRequests2(PartnerHelpRequest partnerHelpRequests2) {
		getPartnerHelpRequests2().add(partnerHelpRequests2);
		partnerHelpRequests2.setPartner2(this);

		return partnerHelpRequests2;
	}

	public PartnerHelpRequest removePartnerHelpRequests2(PartnerHelpRequest partnerHelpRequests2) {
		getPartnerHelpRequests2().remove(partnerHelpRequests2);
		partnerHelpRequests2.setPartner2(null);

		return partnerHelpRequests2;
	}

	public List<PartnerMessage> getPartnerMessages() {
		return this.partnerMessages;
	}

	public void setPartnerMessages(List<PartnerMessage> partnerMessages) {
		this.partnerMessages = partnerMessages;
	}

	public PartnerMessage addPartnerMessage(PartnerMessage partnerMessage) {
		getPartnerMessages().add(partnerMessage);
		partnerMessage.setPartner(this);

		return partnerMessage;
	}

	public PartnerMessage removePartnerMessage(PartnerMessage partnerMessage) {
		getPartnerMessages().remove(partnerMessage);
		partnerMessage.setPartner(null);

		return partnerMessage;
	}

	public List<PartnerNoteTopic> getPartnerNoteTopics() {
		return this.partnerNoteTopics;
	}

	public void setPartnerNoteTopics(List<PartnerNoteTopic> partnerNoteTopics) {
		this.partnerNoteTopics = partnerNoteTopics;
	}

	public PartnerNoteTopic addPartnerNoteTopic(PartnerNoteTopic partnerNoteTopic) {
		getPartnerNoteTopics().add(partnerNoteTopic);
		partnerNoteTopic.setPartner(this);

		return partnerNoteTopic;
	}

	public PartnerNoteTopic removePartnerNoteTopic(PartnerNoteTopic partnerNoteTopic) {
		getPartnerNoteTopics().remove(partnerNoteTopic);
		partnerNoteTopic.setPartner(null);

		return partnerNoteTopic;
	}

	public List<PartnerNote> getPartnerNotes() {
		return this.partnerNotes;
	}

	public void setPartnerNotes(List<PartnerNote> partnerNotes) {
		this.partnerNotes = partnerNotes;
	}

	public PartnerNote addPartnerNote(PartnerNote partnerNote) {
		getPartnerNotes().add(partnerNote);
		partnerNote.setPartner(this);

		return partnerNote;
	}

	public PartnerNote removePartnerNote(PartnerNote partnerNote) {
		getPartnerNotes().remove(partnerNote);
		partnerNote.setPartner(null);

		return partnerNote;
	}

	public List<PartnerOffice> getPartnerOffices() {
		return this.partnerOffices;
	}

	public void setPartnerOffices(List<PartnerOffice> partnerOffices) {
		this.partnerOffices = partnerOffices;
	}

	public PartnerOffice addPartnerOffice(PartnerOffice partnerOffice) {
		getPartnerOffices().add(partnerOffice);
		partnerOffice.setPartner(this);

		return partnerOffice;
	}

	public PartnerOffice removePartnerOffice(PartnerOffice partnerOffice) {
		getPartnerOffices().remove(partnerOffice);
		partnerOffice.setPartner(null);

		return partnerOffice;
	}

	public List<PartnerProgram> getPartnerPrograms() {
		return this.partnerPrograms;
	}

	public void setPartnerPrograms(List<PartnerProgram> partnerPrograms) {
		this.partnerPrograms = partnerPrograms;
	}

	public PartnerProgram addPartnerProgram(PartnerProgram partnerProgram) {
		getPartnerPrograms().add(partnerProgram);
		partnerProgram.setPartner(this);

		return partnerProgram;
	}

	public PartnerProgram removePartnerProgram(PartnerProgram partnerProgram) {
		getPartnerPrograms().remove(partnerProgram);
		partnerProgram.setPartner(null);

		return partnerProgram;
	}

	public List<PartnerQuickStatsCategoryAggregate> getPartnerQuickStatsCategoryAggregates() {
		return this.partnerQuickStatsCategoryAggregates;
	}

	public void setPartnerQuickStatsCategoryAggregates(List<PartnerQuickStatsCategoryAggregate> partnerQuickStatsCategoryAggregates) {
		this.partnerQuickStatsCategoryAggregates = partnerQuickStatsCategoryAggregates;
	}

	public PartnerQuickStatsCategoryAggregate addPartnerQuickStatsCategoryAggregate(PartnerQuickStatsCategoryAggregate partnerQuickStatsCategoryAggregate) {
		getPartnerQuickStatsCategoryAggregates().add(partnerQuickStatsCategoryAggregate);
		partnerQuickStatsCategoryAggregate.setPartner(this);

		return partnerQuickStatsCategoryAggregate;
	}

	public PartnerQuickStatsCategoryAggregate removePartnerQuickStatsCategoryAggregate(PartnerQuickStatsCategoryAggregate partnerQuickStatsCategoryAggregate) {
		getPartnerQuickStatsCategoryAggregates().remove(partnerQuickStatsCategoryAggregate);
		partnerQuickStatsCategoryAggregate.setPartner(null);

		return partnerQuickStatsCategoryAggregate;
	}

	public List<PartnerQuickStatsTypeAggregate> getPartnerQuickStatsTypeAggregates() {
		return this.partnerQuickStatsTypeAggregates;
	}

	public void setPartnerQuickStatsTypeAggregates(List<PartnerQuickStatsTypeAggregate> partnerQuickStatsTypeAggregates) {
		this.partnerQuickStatsTypeAggregates = partnerQuickStatsTypeAggregates;
	}

	public PartnerQuickStatsTypeAggregate addPartnerQuickStatsTypeAggregate(PartnerQuickStatsTypeAggregate partnerQuickStatsTypeAggregate) {
		getPartnerQuickStatsTypeAggregates().add(partnerQuickStatsTypeAggregate);
		partnerQuickStatsTypeAggregate.setPartner(this);

		return partnerQuickStatsTypeAggregate;
	}

	public PartnerQuickStatsTypeAggregate removePartnerQuickStatsTypeAggregate(PartnerQuickStatsTypeAggregate partnerQuickStatsTypeAggregate) {
		getPartnerQuickStatsTypeAggregates().remove(partnerQuickStatsTypeAggregate);
		partnerQuickStatsTypeAggregate.setPartner(null);

		return partnerQuickStatsTypeAggregate;
	}

	public List<PartnerReferenceCheck> getPartnerReferenceChecks() {
		return this.partnerReferenceChecks;
	}

	public void setPartnerReferenceChecks(List<PartnerReferenceCheck> partnerReferenceChecks) {
		this.partnerReferenceChecks = partnerReferenceChecks;
	}

	public PartnerReferenceCheck addPartnerReferenceCheck(PartnerReferenceCheck partnerReferenceCheck) {
		getPartnerReferenceChecks().add(partnerReferenceCheck);
		partnerReferenceCheck.setPartner(this);

		return partnerReferenceCheck;
	}

	public PartnerReferenceCheck removePartnerReferenceCheck(PartnerReferenceCheck partnerReferenceCheck) {
		getPartnerReferenceChecks().remove(partnerReferenceCheck);
		partnerReferenceCheck.setPartner(null);

		return partnerReferenceCheck;
	}

	public List<PartnerReviewStatus> getPartnerReviewStatuses() {
		return this.partnerReviewStatuses;
	}

	public void setPartnerReviewStatuses(List<PartnerReviewStatus> partnerReviewStatuses) {
		this.partnerReviewStatuses = partnerReviewStatuses;
	}

	public PartnerReviewStatus addPartnerReviewStatus(PartnerReviewStatus partnerReviewStatus) {
		getPartnerReviewStatuses().add(partnerReviewStatus);
		partnerReviewStatus.setPartner(this);

		return partnerReviewStatus;
	}

	public PartnerReviewStatus removePartnerReviewStatus(PartnerReviewStatus partnerReviewStatus) {
		getPartnerReviewStatuses().remove(partnerReviewStatus);
		partnerReviewStatus.setPartner(null);

		return partnerReviewStatus;
	}

	public List<PartnerSeason> getPartnerSeasons() {
		return this.partnerSeasons;
	}

	public void setPartnerSeasons(List<PartnerSeason> partnerSeasons) {
		this.partnerSeasons = partnerSeasons;
	}

	public PartnerSeason addPartnerSeason(PartnerSeason partnerSeason) {
		getPartnerSeasons().add(partnerSeason);
		partnerSeason.setPartner(this);

		return partnerSeason;
	}

	public PartnerSeason removePartnerSeason(PartnerSeason partnerSeason) {
		getPartnerSeasons().remove(partnerSeason);
		partnerSeason.setPartner(null);

		return partnerSeason;
	}

	public List<PartnerUser> getPartnerUsers() {
		return this.partnerUsers;
	}

	public void setPartnerUsers(List<PartnerUser> partnerUsers) {
		this.partnerUsers = partnerUsers;
	}

	public PartnerUser addPartnerUser(PartnerUser partnerUser) {
		getPartnerUsers().add(partnerUser);
		partnerUser.setPartner(this);

		return partnerUser;
	}

	public PartnerUser removePartnerUser(PartnerUser partnerUser) {
		getPartnerUsers().remove(partnerUser);
		partnerUser.setPartner(null);

		return partnerUser;
	}

	public List<PartnerWorkQueue> getPartnerWorkQueues() {
		return this.partnerWorkQueues;
	}

	public void setPartnerWorkQueues(List<PartnerWorkQueue> partnerWorkQueues) {
		this.partnerWorkQueues = partnerWorkQueues;
	}

	public PartnerWorkQueue addPartnerWorkQueue(PartnerWorkQueue partnerWorkQueue) {
		getPartnerWorkQueues().add(partnerWorkQueue);
		partnerWorkQueue.setPartner(this);

		return partnerWorkQueue;
	}

	public PartnerWorkQueue removePartnerWorkQueue(PartnerWorkQueue partnerWorkQueue) {
		getPartnerWorkQueues().remove(partnerWorkQueue);
		partnerWorkQueue.setPartner(null);

		return partnerWorkQueue;
	}

	public List<PartnerWorkQueueCategoryAggregate> getPartnerWorkQueueCategoryAggregates() {
		return this.partnerWorkQueueCategoryAggregates;
	}

	public void setPartnerWorkQueueCategoryAggregates(List<PartnerWorkQueueCategoryAggregate> partnerWorkQueueCategoryAggregates) {
		this.partnerWorkQueueCategoryAggregates = partnerWorkQueueCategoryAggregates;
	}

	public PartnerWorkQueueCategoryAggregate addPartnerWorkQueueCategoryAggregate(PartnerWorkQueueCategoryAggregate partnerWorkQueueCategoryAggregate) {
		getPartnerWorkQueueCategoryAggregates().add(partnerWorkQueueCategoryAggregate);
		partnerWorkQueueCategoryAggregate.setPartner(this);

		return partnerWorkQueueCategoryAggregate;
	}

	public PartnerWorkQueueCategoryAggregate removePartnerWorkQueueCategoryAggregate(PartnerWorkQueueCategoryAggregate partnerWorkQueueCategoryAggregate) {
		getPartnerWorkQueueCategoryAggregates().remove(partnerWorkQueueCategoryAggregate);
		partnerWorkQueueCategoryAggregate.setPartner(null);

		return partnerWorkQueueCategoryAggregate;
	}

	public List<PartnerWorkQueueTypeAggregate> getPartnerWorkQueueTypeAggregates() {
		return this.partnerWorkQueueTypeAggregates;
	}

	public void setPartnerWorkQueueTypeAggregates(List<PartnerWorkQueueTypeAggregate> partnerWorkQueueTypeAggregates) {
		this.partnerWorkQueueTypeAggregates = partnerWorkQueueTypeAggregates;
	}

	public PartnerWorkQueueTypeAggregate addPartnerWorkQueueTypeAggregate(PartnerWorkQueueTypeAggregate partnerWorkQueueTypeAggregate) {
		getPartnerWorkQueueTypeAggregates().add(partnerWorkQueueTypeAggregate);
		partnerWorkQueueTypeAggregate.setPartner(this);

		return partnerWorkQueueTypeAggregate;
	}

	public PartnerWorkQueueTypeAggregate removePartnerWorkQueueTypeAggregate(PartnerWorkQueueTypeAggregate partnerWorkQueueTypeAggregate) {
		getPartnerWorkQueueTypeAggregates().remove(partnerWorkQueueTypeAggregate);
		partnerWorkQueueTypeAggregate.setPartner(null);

		return partnerWorkQueueTypeAggregate;
	}

}