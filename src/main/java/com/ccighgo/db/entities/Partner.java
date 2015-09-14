package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerGoId;

	@Column(length=15)
	private String acronym;

	@Column(length=150)
	private String addressLineOne;

	@Column(length=150)
	private String addressLineTwo;

	@Column(length=1000)
	private String billingNotes;

	private byte canHaveSubPartner;

	@Column(length=50)
	private String city;

	@Column(length=50)
	private String companyName;

	@Column(length=2000)
	private String contactNotes;

	@Column(length=50)
	private String contractSigner;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer dandBNumber;

	@Column(length=50)
	private String email;

	private byte hasSubPartners;

	@Column(length=50)
	private String invoiceMail;

	private byte isSubPartner;

	private Integer lastSelectedProgramId;

	private byte mailingAddressIsSameAsPhysicalAdress;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	private byte multiCountrySender;

	private Integer oldId;

	private Integer parentPartnerGoId;

	private byte participantELTISRequired;

	private byte participantMedicalReleaseRequired;

	private byte participantSLEPRequired;

	private byte participantTranscriptRequired;

	@Column(length=64)
	private String partnerGuid;

	@Column(length=40)
	private String quickbooksCode;

	private byte receiveAYPMails;

	@Column(length=50)
	private String state;

	private byte subscribeToCCINewsletter;

	private byte unguaranteedFormRequired;

	@Column(length=15)
	private String zipcode;

	//bi-directional many-to-one association to Participant
	@OneToMany(mappedBy="partner")
	private List<Participant> participants;

	//bi-directional one-to-one association to GoIdSequence
	@OneToOne
	@JoinColumn(name="partnerGoId", nullable=false, insertable=false, updatable=false)
	private GoIdSequence goIdSequence;

	//bi-directional many-to-one association to LookupCountry
	@ManyToOne
	@JoinColumn(name="countryId")
	private LookupCountry lookupCountry;

	//bi-directional many-to-one association to PartnerStatus
	@ManyToOne
	@JoinColumn(name="partnerStatusId")
	private PartnerStatus partnerStatus;

	//bi-directional many-to-one association to PartnerAgentInquiry
	@OneToMany(mappedBy="partner")
	private List<PartnerAgentInquiry> partnerAgentInquiries;

	//bi-directional many-to-one association to PartnerAnnouncement
	@OneToMany(mappedBy="partner")
	private List<PartnerAnnouncement> partnerAnnouncements;

	//bi-directional many-to-one association to PartnerCCIContact
	@OneToMany(mappedBy="partner")
	private List<PartnerCCIContact> partnerCcicontacts;

	//bi-directional many-to-one association to PartnerContact
	@OneToMany(mappedBy="partner")
	private List<PartnerContact> partnerContacts;

	//bi-directional many-to-one association to PartnerDocument
	@OneToMany(mappedBy="partner")
	private List<PartnerDocument> partnerDocuments;

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

	//bi-directional many-to-one association to PartnerReferenceCheck
	@OneToMany(mappedBy="partner")
	private List<PartnerReferenceCheck> partnerReferenceChecks;

	//bi-directional many-to-one association to PartnerReviewStatus
	@OneToMany(mappedBy="partner")
	private List<PartnerReviewStatus> partnerReviewStatuses;

	//bi-directional many-to-one association to PartnerSeason
	@OneToMany(mappedBy="partner")
	private List<PartnerSeason> partnerSeasons;

	//bi-directional many-to-one association to PartnerSeasonDocument
	@OneToMany(mappedBy="partner")
	private List<PartnerSeasonDocument> partnerSeasonDocuments;

	//bi-directional many-to-one association to PartnerUser
	@OneToMany(mappedBy="partner")
	private List<PartnerUser> partnerUsers;

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

	public byte getCanHaveSubPartner() {
		return this.canHaveSubPartner;
	}

	public void setCanHaveSubPartner(byte canHaveSubPartner) {
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte getHasSubPartners() {
		return this.hasSubPartners;
	}

	public void setHasSubPartners(byte hasSubPartners) {
		this.hasSubPartners = hasSubPartners;
	}

	public String getInvoiceMail() {
		return this.invoiceMail;
	}

	public void setInvoiceMail(String invoiceMail) {
		this.invoiceMail = invoiceMail;
	}

	public byte getIsSubPartner() {
		return this.isSubPartner;
	}

	public void setIsSubPartner(byte isSubPartner) {
		this.isSubPartner = isSubPartner;
	}

	public Integer getLastSelectedProgramId() {
		return this.lastSelectedProgramId;
	}

	public void setLastSelectedProgramId(Integer lastSelectedProgramId) {
		this.lastSelectedProgramId = lastSelectedProgramId;
	}

	public byte getMailingAddressIsSameAsPhysicalAdress() {
		return this.mailingAddressIsSameAsPhysicalAdress;
	}

	public void setMailingAddressIsSameAsPhysicalAdress(byte mailingAddressIsSameAsPhysicalAdress) {
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

	public byte getMultiCountrySender() {
		return this.multiCountrySender;
	}

	public void setMultiCountrySender(byte multiCountrySender) {
		this.multiCountrySender = multiCountrySender;
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

	public byte getParticipantELTISRequired() {
		return this.participantELTISRequired;
	}

	public void setParticipantELTISRequired(byte participantELTISRequired) {
		this.participantELTISRequired = participantELTISRequired;
	}

	public byte getParticipantMedicalReleaseRequired() {
		return this.participantMedicalReleaseRequired;
	}

	public void setParticipantMedicalReleaseRequired(byte participantMedicalReleaseRequired) {
		this.participantMedicalReleaseRequired = participantMedicalReleaseRequired;
	}

	public byte getParticipantSLEPRequired() {
		return this.participantSLEPRequired;
	}

	public void setParticipantSLEPRequired(byte participantSLEPRequired) {
		this.participantSLEPRequired = participantSLEPRequired;
	}

	public byte getParticipantTranscriptRequired() {
		return this.participantTranscriptRequired;
	}

	public void setParticipantTranscriptRequired(byte participantTranscriptRequired) {
		this.participantTranscriptRequired = participantTranscriptRequired;
	}

	public String getPartnerGuid() {
		return this.partnerGuid;
	}

	public void setPartnerGuid(String partnerGuid) {
		this.partnerGuid = partnerGuid;
	}

	public String getQuickbooksCode() {
		return this.quickbooksCode;
	}

	public void setQuickbooksCode(String quickbooksCode) {
		this.quickbooksCode = quickbooksCode;
	}

	public byte getReceiveAYPMails() {
		return this.receiveAYPMails;
	}

	public void setReceiveAYPMails(byte receiveAYPMails) {
		this.receiveAYPMails = receiveAYPMails;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public byte getSubscribeToCCINewsletter() {
		return this.subscribeToCCINewsletter;
	}

	public void setSubscribeToCCINewsletter(byte subscribeToCCINewsletter) {
		this.subscribeToCCINewsletter = subscribeToCCINewsletter;
	}

	public byte getUnguaranteedFormRequired() {
		return this.unguaranteedFormRequired;
	}

	public void setUnguaranteedFormRequired(byte unguaranteedFormRequired) {
		this.unguaranteedFormRequired = unguaranteedFormRequired;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public List<Participant> getParticipants() {
		return this.participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public Participant addParticipant(Participant participant) {
		getParticipants().add(participant);
		participant.setPartner(this);

		return participant;
	}

	public Participant removeParticipant(Participant participant) {
		getParticipants().remove(participant);
		participant.setPartner(null);

		return participant;
	}

	public GoIdSequence getGoIdSequence() {
		return this.goIdSequence;
	}

	public void setGoIdSequence(GoIdSequence goIdSequence) {
		this.goIdSequence = goIdSequence;
	}

	public LookupCountry getLookupCountry() {
		return this.lookupCountry;
	}

	public void setLookupCountry(LookupCountry lookupCountry) {
		this.lookupCountry = lookupCountry;
	}

	public PartnerStatus getPartnerStatus() {
		return this.partnerStatus;
	}

	public void setPartnerStatus(PartnerStatus partnerStatus) {
		this.partnerStatus = partnerStatus;
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

	public List<PartnerCCIContact> getPartnerCcicontacts() {
		return this.partnerCcicontacts;
	}

	public void setPartnerCcicontacts(List<PartnerCCIContact> partnerCcicontacts) {
		this.partnerCcicontacts = partnerCcicontacts;
	}

	public PartnerCCIContact addPartnerCcicontact(PartnerCCIContact partnerCcicontact) {
		getPartnerCcicontacts().add(partnerCcicontact);
		partnerCcicontact.setPartner(this);

		return partnerCcicontact;
	}

	public PartnerCCIContact removePartnerCcicontact(PartnerCCIContact partnerCcicontact) {
		getPartnerCcicontacts().remove(partnerCcicontact);
		partnerCcicontact.setPartner(null);

		return partnerCcicontact;
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

	public List<PartnerSeasonDocument> getPartnerSeasonDocuments() {
		return this.partnerSeasonDocuments;
	}

	public void setPartnerSeasonDocuments(List<PartnerSeasonDocument> partnerSeasonDocuments) {
		this.partnerSeasonDocuments = partnerSeasonDocuments;
	}

	public PartnerSeasonDocument addPartnerSeasonDocument(PartnerSeasonDocument partnerSeasonDocument) {
		getPartnerSeasonDocuments().add(partnerSeasonDocument);
		partnerSeasonDocument.setPartner(this);

		return partnerSeasonDocument;
	}

	public PartnerSeasonDocument removePartnerSeasonDocument(PartnerSeasonDocument partnerSeasonDocument) {
		getPartnerSeasonDocuments().remove(partnerSeasonDocument);
		partnerSeasonDocument.setPartner(null);

		return partnerSeasonDocument;
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

}