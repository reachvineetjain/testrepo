package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the FieldStaff database table.
 * 
 */
@Entity
@Table(name="FieldStaff")
@NamedQuery(name="FieldStaff.findAll", query="SELECT f FROM FieldStaff f")
public class FieldStaff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer fieldStaffGoId;

	private Byte agreementNoticeSent;

	private Byte agreeToTerms;

	private Integer approvedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	@Lob
	private String areaHighSchools;

	@Temporal(TemporalType.TIMESTAMP)
	private Date backgroundCheckDate;

	private Integer backgroundCheckPassed;

	private Byte bestNumberCell;

	private Byte bestNumberHome;

	private Byte bestNumberWork;

	@Column(length=50)
	private String bestTimeToCall;

	@Temporal(TemporalType.TIMESTAMP)
	private Date birthDate;

	private Byte canPresentGrantPax;

	@Column(length=25)
	private String cellPhone;

	private Integer childServicesContact;

	@Lob
	private String childServicesContactDetails;

	@Lob
	private String comments;

	@Lob
	private String communityDescription;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer crime;

	@Lob
	private String crimeDetails;

	@Column(length=100)
	private String currentAddress1;

	@Column(length=100)
	private String currentAddress2;

	@Column(length=100)
	private String currentCity;

	private Byte currentCommunityVolunteer;

	@Lob
	private String currentCommunityVolunteerDetails;

	private Integer currentSeasonId;

	@Column(length=25)
	private String currentZipCode;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDOSCertTestTaken;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateW9FormReceived;

	@Column(length=25)
	private String emergencyPhone;

	private Integer ERDId;

	@Column(length=25)
	private String fax;

	private Integer fieldStaffWillingToHostId;

	@Column(length=50)
	private String firstName;

	private Integer hasBeenARBefore;

	private Integer hasHostedBefore;

	private Byte hostFamilyApplicationStarted;

	private Integer interestedInLocalCoordinatorForSummer;

	private Integer interestedInLocalCoordinatorForYear;

	@Lob
	private String internationalExperienceDetails;

	private Byte isBlacklisted;

	private Integer isCurrentlyAR;

	private Byte isDoNotContact;

	@Column(length=50)
	private String lastName;

	@Column(length=100)
	private String mailingAddress1;

	@Column(length=100)
	private String mailingAddress2;

	@Column(length=100)
	private String mailingCity;

	@Column(length=25)
	private String mailingZipCode;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date originalStartDate;

	@Lob
	private String pastARExperience;

	@Lob
	private String pastHostingDetails;

	@Lob
	private String pastHostingOverview;

	@Column(length=25)
	private String phone;

	@Column(length=300)
	private String photo;

	private Integer RDId;

	private Byte reasonForRejection;

	@Lob
	private String reasonsForApplying;

	private Byte receiveEmails;

	private Byte residesAlone;

	private Integer RMId;

	@Lob
	private String selfDescription;

	@Column(length=15)
	private String ssNumber;

	@Temporal(TemporalType.TIMESTAMP)
	private Date submittedDate;

	@Lob
	private String teenagerExperienceDetails;

	@Column(length=25)
	private String tollFreePhone;

	private Integer totalPlacementsAuto;

	private Integer totalPlacementsManual;

	@Temporal(TemporalType.TIMESTAMP)
	private Date voCompletionDate;

	private Integer voLastSlideViewed;

	@Column(length=50)
	private String whereToCall;

	@Column(length=25)
	private String workPhone;

	//bi-directional many-to-one association to CCIStaffUser
	@ManyToOne
	@JoinColumn(name="cciStaffUserId")
	private CCIStaffUser ccistaffUser;

	//bi-directional many-to-one association to FieldStaffStatus
	@ManyToOne
	@JoinColumn(name="fieldStaffStatusId")
	private FieldStaffStatus fieldStaffStatus;

	//bi-directional many-to-one association to FieldStaffType
	@ManyToOne
	@JoinColumn(name="fieldStaffTypeId", unique=true)
	private FieldStaffType fieldStaffType;

	//bi-directional one-to-one association to GoIdSequence
	@OneToOne
	@JoinColumn(name="fieldStaffGoId", nullable=false, insertable=false, updatable=false)
	private GoIdSequence goIdSequence;

	//bi-directional many-to-one association to LookupGender
	@ManyToOne
	@JoinColumn(name="genderId")
	private LookupGender lookupGender;

	//bi-directional many-to-one association to LookupUSState
	@ManyToOne
	@JoinColumn(name="mailingStateId")
	private LookupUSState lookupUsstate1;

	//bi-directional many-to-one association to LookupUSState
	@ManyToOne
	@JoinColumn(name="currentStateId")
	private LookupUSState lookupUsstate2;

	//bi-directional many-to-one association to Salutation
	@ManyToOne
	@JoinColumn(name="salutationId")
	private Salutation salutation;

	//bi-directional many-to-one association to FieldStaffAnnouncement
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffAnnouncement> fieldStaffAnnouncements;

	//bi-directional many-to-one association to FieldStaffAnnouncementResult
	@OneToMany(mappedBy="fieldStaff1")
	private List<FieldStaffAnnouncementResult> fieldStaffAnnouncementResults1;

	//bi-directional many-to-one association to FieldStaffAnnouncementResult
	@OneToMany(mappedBy="fieldStaff2")
	private List<FieldStaffAnnouncementResult> fieldStaffAnnouncementResults2;

	//bi-directional many-to-one association to FieldStaffDocument
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffDocument> fieldStaffDocuments;

	//bi-directional many-to-one association to FieldStaffFamilyMember
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffFamilyMember> fieldStaffFamilyMembers;

	//bi-directional many-to-one association to FieldStaffHistory
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffHistory> fieldStaffHistories;

	//bi-directional many-to-one association to FieldStaffLeadershipSeason
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffLeadershipSeason> fieldStaffLeadershipSeasons;

	//bi-directional many-to-one association to FieldStaffNote
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffNote> fieldStaffNotes;

	//bi-directional many-to-one association to FieldStaffNoteTopic
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffNoteTopic> fieldStaffNoteTopics;

	//bi-directional many-to-one association to FieldStaffParticipant
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffParticipant> fieldStaffParticipants;

	//bi-directional many-to-one association to FieldStaffPermission
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffPermission> fieldStaffPermissions;

	//bi-directional many-to-one association to FieldStaffQuickStatsCategoryAggregate
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffQuickStatsCategoryAggregate> fieldStaffQuickStatsCategoryAggregates;

	//bi-directional many-to-one association to FieldStaffQuickStatsTypeAggregate
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffQuickStatsTypeAggregate> fieldStaffQuickStatsTypeAggregates;

	//bi-directional many-to-one association to FieldStaffReference
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffReference> fieldStaffReferences;

	//bi-directional many-to-one association to FieldStaffSeason
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffSeason> fieldStaffSeasons;

	//bi-directional many-to-one association to FieldStaffUpdateLog
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffUpdateLog> fieldStaffUpdateLogs;

	//bi-directional many-to-one association to FieldStaffWorkQueue
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffWorkQueue> fieldStaffWorkQueues;

	//bi-directional many-to-one association to FieldStaffWorkQueueCategoryAggregate
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffWorkQueueCategoryAggregate> fieldStaffWorkQueueCategoryAggregates;

	//bi-directional many-to-one association to FieldStaffWorkQueueTypeAggregate
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffWorkQueueTypeAggregate> fieldStaffWorkQueueTypeAggregates;

	//bi-directional many-to-one association to HostFamilyEvaluation
	@OneToMany(mappedBy="fieldStaff")
	private List<HostFamilyEvaluation> hostFamilyEvaluations;

	//bi-directional many-to-one association to HostFamilyParticipant
	@OneToMany(mappedBy="fieldStaff1")
	private List<HostFamilyParticipant> hostFamilyParticipants1;

	//bi-directional many-to-one association to HostFamilyParticipant
	@OneToMany(mappedBy="fieldStaff2")
	private List<HostFamilyParticipant> hostFamilyParticipants2;

	//bi-directional many-to-one association to HostFamilyParticipantHistory
	@OneToMany(mappedBy="fieldStaff")
	private List<HostFamilyParticipantHistory> hostFamilyParticipantHistories;

	//bi-directional many-to-one association to HostFamilySeason
	@OneToMany(mappedBy="fieldStaff1")
	private List<HostFamilySeason> hostFamilySeasons1;

	//bi-directional many-to-one association to HostFamilySeason
	@OneToMany(mappedBy="fieldStaff2")
	private List<HostFamilySeason> hostFamilySeasons2;

	//bi-directional many-to-one association to HostFamilySeason
	@OneToMany(mappedBy="fieldStaff3")
	private List<HostFamilySeason> hostFamilySeasons3;

	public FieldStaff() {
	}

	public Integer getFieldStaffGoId() {
		return this.fieldStaffGoId;
	}

	public void setFieldStaffGoId(Integer fieldStaffGoId) {
		this.fieldStaffGoId = fieldStaffGoId;
	}

	public Byte getAgreementNoticeSent() {
		return this.agreementNoticeSent;
	}

	public void setAgreementNoticeSent(Byte agreementNoticeSent) {
		this.agreementNoticeSent = agreementNoticeSent;
	}

	public Byte getAgreeToTerms() {
		return this.agreeToTerms;
	}

	public void setAgreeToTerms(Byte agreeToTerms) {
		this.agreeToTerms = agreeToTerms;
	}

	public Integer getApprovedBy() {
		return this.approvedBy;
	}

	public void setApprovedBy(Integer approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return this.approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getAreaHighSchools() {
		return this.areaHighSchools;
	}

	public void setAreaHighSchools(String areaHighSchools) {
		this.areaHighSchools = areaHighSchools;
	}

	public Date getBackgroundCheckDate() {
		return this.backgroundCheckDate;
	}

	public void setBackgroundCheckDate(Date backgroundCheckDate) {
		this.backgroundCheckDate = backgroundCheckDate;
	}

	public Integer getBackgroundCheckPassed() {
		return this.backgroundCheckPassed;
	}

	public void setBackgroundCheckPassed(Integer backgroundCheckPassed) {
		this.backgroundCheckPassed = backgroundCheckPassed;
	}

	public Byte getBestNumberCell() {
		return this.bestNumberCell;
	}

	public void setBestNumberCell(Byte bestNumberCell) {
		this.bestNumberCell = bestNumberCell;
	}

	public Byte getBestNumberHome() {
		return this.bestNumberHome;
	}

	public void setBestNumberHome(Byte bestNumberHome) {
		this.bestNumberHome = bestNumberHome;
	}

	public Byte getBestNumberWork() {
		return this.bestNumberWork;
	}

	public void setBestNumberWork(Byte bestNumberWork) {
		this.bestNumberWork = bestNumberWork;
	}

	public String getBestTimeToCall() {
		return this.bestTimeToCall;
	}

	public void setBestTimeToCall(String bestTimeToCall) {
		this.bestTimeToCall = bestTimeToCall;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Byte getCanPresentGrantPax() {
		return this.canPresentGrantPax;
	}

	public void setCanPresentGrantPax(Byte canPresentGrantPax) {
		this.canPresentGrantPax = canPresentGrantPax;
	}

	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public Integer getChildServicesContact() {
		return this.childServicesContact;
	}

	public void setChildServicesContact(Integer childServicesContact) {
		this.childServicesContact = childServicesContact;
	}

	public String getChildServicesContactDetails() {
		return this.childServicesContactDetails;
	}

	public void setChildServicesContactDetails(String childServicesContactDetails) {
		this.childServicesContactDetails = childServicesContactDetails;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCommunityDescription() {
		return this.communityDescription;
	}

	public void setCommunityDescription(String communityDescription) {
		this.communityDescription = communityDescription;
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

	public Integer getCrime() {
		return this.crime;
	}

	public void setCrime(Integer crime) {
		this.crime = crime;
	}

	public String getCrimeDetails() {
		return this.crimeDetails;
	}

	public void setCrimeDetails(String crimeDetails) {
		this.crimeDetails = crimeDetails;
	}

	public String getCurrentAddress1() {
		return this.currentAddress1;
	}

	public void setCurrentAddress1(String currentAddress1) {
		this.currentAddress1 = currentAddress1;
	}

	public String getCurrentAddress2() {
		return this.currentAddress2;
	}

	public void setCurrentAddress2(String currentAddress2) {
		this.currentAddress2 = currentAddress2;
	}

	public String getCurrentCity() {
		return this.currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public Byte getCurrentCommunityVolunteer() {
		return this.currentCommunityVolunteer;
	}

	public void setCurrentCommunityVolunteer(Byte currentCommunityVolunteer) {
		this.currentCommunityVolunteer = currentCommunityVolunteer;
	}

	public String getCurrentCommunityVolunteerDetails() {
		return this.currentCommunityVolunteerDetails;
	}

	public void setCurrentCommunityVolunteerDetails(String currentCommunityVolunteerDetails) {
		this.currentCommunityVolunteerDetails = currentCommunityVolunteerDetails;
	}

	public Integer getCurrentSeasonId() {
		return this.currentSeasonId;
	}

	public void setCurrentSeasonId(Integer currentSeasonId) {
		this.currentSeasonId = currentSeasonId;
	}

	public String getCurrentZipCode() {
		return this.currentZipCode;
	}

	public void setCurrentZipCode(String currentZipCode) {
		this.currentZipCode = currentZipCode;
	}

	public Date getDateDOSCertTestTaken() {
		return this.dateDOSCertTestTaken;
	}

	public void setDateDOSCertTestTaken(Date dateDOSCertTestTaken) {
		this.dateDOSCertTestTaken = dateDOSCertTestTaken;
	}

	public Date getDateW9FormReceived() {
		return this.dateW9FormReceived;
	}

	public void setDateW9FormReceived(Date dateW9FormReceived) {
		this.dateW9FormReceived = dateW9FormReceived;
	}

	public String getEmergencyPhone() {
		return this.emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public Integer getERDId() {
		return this.ERDId;
	}

	public void setERDId(Integer ERDId) {
		this.ERDId = ERDId;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Integer getFieldStaffWillingToHostId() {
		return this.fieldStaffWillingToHostId;
	}

	public void setFieldStaffWillingToHostId(Integer fieldStaffWillingToHostId) {
		this.fieldStaffWillingToHostId = fieldStaffWillingToHostId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getHasBeenARBefore() {
		return this.hasBeenARBefore;
	}

	public void setHasBeenARBefore(Integer hasBeenARBefore) {
		this.hasBeenARBefore = hasBeenARBefore;
	}

	public Integer getHasHostedBefore() {
		return this.hasHostedBefore;
	}

	public void setHasHostedBefore(Integer hasHostedBefore) {
		this.hasHostedBefore = hasHostedBefore;
	}

	public Byte getHostFamilyApplicationStarted() {
		return this.hostFamilyApplicationStarted;
	}

	public void setHostFamilyApplicationStarted(Byte hostFamilyApplicationStarted) {
		this.hostFamilyApplicationStarted = hostFamilyApplicationStarted;
	}

	public Integer getInterestedInLocalCoordinatorForSummer() {
		return this.interestedInLocalCoordinatorForSummer;
	}

	public void setInterestedInLocalCoordinatorForSummer(Integer interestedInLocalCoordinatorForSummer) {
		this.interestedInLocalCoordinatorForSummer = interestedInLocalCoordinatorForSummer;
	}

	public Integer getInterestedInLocalCoordinatorForYear() {
		return this.interestedInLocalCoordinatorForYear;
	}

	public void setInterestedInLocalCoordinatorForYear(Integer interestedInLocalCoordinatorForYear) {
		this.interestedInLocalCoordinatorForYear = interestedInLocalCoordinatorForYear;
	}

	public String getInternationalExperienceDetails() {
		return this.internationalExperienceDetails;
	}

	public void setInternationalExperienceDetails(String internationalExperienceDetails) {
		this.internationalExperienceDetails = internationalExperienceDetails;
	}

	public Byte getIsBlacklisted() {
		return this.isBlacklisted;
	}

	public void setIsBlacklisted(Byte isBlacklisted) {
		this.isBlacklisted = isBlacklisted;
	}

	public Integer getIsCurrentlyAR() {
		return this.isCurrentlyAR;
	}

	public void setIsCurrentlyAR(Integer isCurrentlyAR) {
		this.isCurrentlyAR = isCurrentlyAR;
	}

	public Byte getIsDoNotContact() {
		return this.isDoNotContact;
	}

	public void setIsDoNotContact(Byte isDoNotContact) {
		this.isDoNotContact = isDoNotContact;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMailingAddress1() {
		return this.mailingAddress1;
	}

	public void setMailingAddress1(String mailingAddress1) {
		this.mailingAddress1 = mailingAddress1;
	}

	public String getMailingAddress2() {
		return this.mailingAddress2;
	}

	public void setMailingAddress2(String mailingAddress2) {
		this.mailingAddress2 = mailingAddress2;
	}

	public String getMailingCity() {
		return this.mailingCity;
	}

	public void setMailingCity(String mailingCity) {
		this.mailingCity = mailingCity;
	}

	public String getMailingZipCode() {
		return this.mailingZipCode;
	}

	public void setMailingZipCode(String mailingZipCode) {
		this.mailingZipCode = mailingZipCode;
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

	public Date getOriginalStartDate() {
		return this.originalStartDate;
	}

	public void setOriginalStartDate(Date originalStartDate) {
		this.originalStartDate = originalStartDate;
	}

	public String getPastARExperience() {
		return this.pastARExperience;
	}

	public void setPastARExperience(String pastARExperience) {
		this.pastARExperience = pastARExperience;
	}

	public String getPastHostingDetails() {
		return this.pastHostingDetails;
	}

	public void setPastHostingDetails(String pastHostingDetails) {
		this.pastHostingDetails = pastHostingDetails;
	}

	public String getPastHostingOverview() {
		return this.pastHostingOverview;
	}

	public void setPastHostingOverview(String pastHostingOverview) {
		this.pastHostingOverview = pastHostingOverview;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getRDId() {
		return this.RDId;
	}

	public void setRDId(Integer RDId) {
		this.RDId = RDId;
	}

	public Byte getReasonForRejection() {
		return this.reasonForRejection;
	}

	public void setReasonForRejection(Byte reasonForRejection) {
		this.reasonForRejection = reasonForRejection;
	}

	public String getReasonsForApplying() {
		return this.reasonsForApplying;
	}

	public void setReasonsForApplying(String reasonsForApplying) {
		this.reasonsForApplying = reasonsForApplying;
	}

	public Byte getReceiveEmails() {
		return this.receiveEmails;
	}

	public void setReceiveEmails(Byte receiveEmails) {
		this.receiveEmails = receiveEmails;
	}

	public Byte getResidesAlone() {
		return this.residesAlone;
	}

	public void setResidesAlone(Byte residesAlone) {
		this.residesAlone = residesAlone;
	}

	public Integer getRMId() {
		return this.RMId;
	}

	public void setRMId(Integer RMId) {
		this.RMId = RMId;
	}

	public String getSelfDescription() {
		return this.selfDescription;
	}

	public void setSelfDescription(String selfDescription) {
		this.selfDescription = selfDescription;
	}

	public String getSsNumber() {
		return this.ssNumber;
	}

	public void setSsNumber(String ssNumber) {
		this.ssNumber = ssNumber;
	}

	public Date getSubmittedDate() {
		return this.submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String getTeenagerExperienceDetails() {
		return this.teenagerExperienceDetails;
	}

	public void setTeenagerExperienceDetails(String teenagerExperienceDetails) {
		this.teenagerExperienceDetails = teenagerExperienceDetails;
	}

	public String getTollFreePhone() {
		return this.tollFreePhone;
	}

	public void setTollFreePhone(String tollFreePhone) {
		this.tollFreePhone = tollFreePhone;
	}

	public Integer getTotalPlacementsAuto() {
		return this.totalPlacementsAuto;
	}

	public void setTotalPlacementsAuto(Integer totalPlacementsAuto) {
		this.totalPlacementsAuto = totalPlacementsAuto;
	}

	public Integer getTotalPlacementsManual() {
		return this.totalPlacementsManual;
	}

	public void setTotalPlacementsManual(Integer totalPlacementsManual) {
		this.totalPlacementsManual = totalPlacementsManual;
	}

	public Date getVoCompletionDate() {
		return this.voCompletionDate;
	}

	public void setVoCompletionDate(Date voCompletionDate) {
		this.voCompletionDate = voCompletionDate;
	}

	public Integer getVoLastSlideViewed() {
		return this.voLastSlideViewed;
	}

	public void setVoLastSlideViewed(Integer voLastSlideViewed) {
		this.voLastSlideViewed = voLastSlideViewed;
	}

	public String getWhereToCall() {
		return this.whereToCall;
	}

	public void setWhereToCall(String whereToCall) {
		this.whereToCall = whereToCall;
	}

	public String getWorkPhone() {
		return this.workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public CCIStaffUser getCcistaffUser() {
		return this.ccistaffUser;
	}

	public void setCcistaffUser(CCIStaffUser ccistaffUser) {
		this.ccistaffUser = ccistaffUser;
	}

	public FieldStaffStatus getFieldStaffStatus() {
		return this.fieldStaffStatus;
	}

	public void setFieldStaffStatus(FieldStaffStatus fieldStaffStatus) {
		this.fieldStaffStatus = fieldStaffStatus;
	}

	public FieldStaffType getFieldStaffType() {
		return this.fieldStaffType;
	}

	public void setFieldStaffType(FieldStaffType fieldStaffType) {
		this.fieldStaffType = fieldStaffType;
	}

	public GoIdSequence getGoIdSequence() {
		return this.goIdSequence;
	}

	public void setGoIdSequence(GoIdSequence goIdSequence) {
		this.goIdSequence = goIdSequence;
	}

	public LookupGender getLookupGender() {
		return this.lookupGender;
	}

	public void setLookupGender(LookupGender lookupGender) {
		this.lookupGender = lookupGender;
	}

	public LookupUSState getLookupUsstate1() {
		return this.lookupUsstate1;
	}

	public void setLookupUsstate1(LookupUSState lookupUsstate1) {
		this.lookupUsstate1 = lookupUsstate1;
	}

	public LookupUSState getLookupUsstate2() {
		return this.lookupUsstate2;
	}

	public void setLookupUsstate2(LookupUSState lookupUsstate2) {
		this.lookupUsstate2 = lookupUsstate2;
	}

	public Salutation getSalutation() {
		return this.salutation;
	}

	public void setSalutation(Salutation salutation) {
		this.salutation = salutation;
	}

	public List<FieldStaffAnnouncement> getFieldStaffAnnouncements() {
		return this.fieldStaffAnnouncements;
	}

	public void setFieldStaffAnnouncements(List<FieldStaffAnnouncement> fieldStaffAnnouncements) {
		this.fieldStaffAnnouncements = fieldStaffAnnouncements;
	}

	public FieldStaffAnnouncement addFieldStaffAnnouncement(FieldStaffAnnouncement fieldStaffAnnouncement) {
		getFieldStaffAnnouncements().add(fieldStaffAnnouncement);
		fieldStaffAnnouncement.setFieldStaff(this);

		return fieldStaffAnnouncement;
	}

	public FieldStaffAnnouncement removeFieldStaffAnnouncement(FieldStaffAnnouncement fieldStaffAnnouncement) {
		getFieldStaffAnnouncements().remove(fieldStaffAnnouncement);
		fieldStaffAnnouncement.setFieldStaff(null);

		return fieldStaffAnnouncement;
	}

	public List<FieldStaffAnnouncementResult> getFieldStaffAnnouncementResults1() {
		return this.fieldStaffAnnouncementResults1;
	}

	public void setFieldStaffAnnouncementResults1(List<FieldStaffAnnouncementResult> fieldStaffAnnouncementResults1) {
		this.fieldStaffAnnouncementResults1 = fieldStaffAnnouncementResults1;
	}

	public FieldStaffAnnouncementResult addFieldStaffAnnouncementResults1(FieldStaffAnnouncementResult fieldStaffAnnouncementResults1) {
		getFieldStaffAnnouncementResults1().add(fieldStaffAnnouncementResults1);
		fieldStaffAnnouncementResults1.setFieldStaff1(this);

		return fieldStaffAnnouncementResults1;
	}

	public FieldStaffAnnouncementResult removeFieldStaffAnnouncementResults1(FieldStaffAnnouncementResult fieldStaffAnnouncementResults1) {
		getFieldStaffAnnouncementResults1().remove(fieldStaffAnnouncementResults1);
		fieldStaffAnnouncementResults1.setFieldStaff1(null);

		return fieldStaffAnnouncementResults1;
	}

	public List<FieldStaffAnnouncementResult> getFieldStaffAnnouncementResults2() {
		return this.fieldStaffAnnouncementResults2;
	}

	public void setFieldStaffAnnouncementResults2(List<FieldStaffAnnouncementResult> fieldStaffAnnouncementResults2) {
		this.fieldStaffAnnouncementResults2 = fieldStaffAnnouncementResults2;
	}

	public FieldStaffAnnouncementResult addFieldStaffAnnouncementResults2(FieldStaffAnnouncementResult fieldStaffAnnouncementResults2) {
		getFieldStaffAnnouncementResults2().add(fieldStaffAnnouncementResults2);
		fieldStaffAnnouncementResults2.setFieldStaff2(this);

		return fieldStaffAnnouncementResults2;
	}

	public FieldStaffAnnouncementResult removeFieldStaffAnnouncementResults2(FieldStaffAnnouncementResult fieldStaffAnnouncementResults2) {
		getFieldStaffAnnouncementResults2().remove(fieldStaffAnnouncementResults2);
		fieldStaffAnnouncementResults2.setFieldStaff2(null);

		return fieldStaffAnnouncementResults2;
	}

	public List<FieldStaffDocument> getFieldStaffDocuments() {
		return this.fieldStaffDocuments;
	}

	public void setFieldStaffDocuments(List<FieldStaffDocument> fieldStaffDocuments) {
		this.fieldStaffDocuments = fieldStaffDocuments;
	}

	public FieldStaffDocument addFieldStaffDocument(FieldStaffDocument fieldStaffDocument) {
		getFieldStaffDocuments().add(fieldStaffDocument);
		fieldStaffDocument.setFieldStaff(this);

		return fieldStaffDocument;
	}

	public FieldStaffDocument removeFieldStaffDocument(FieldStaffDocument fieldStaffDocument) {
		getFieldStaffDocuments().remove(fieldStaffDocument);
		fieldStaffDocument.setFieldStaff(null);

		return fieldStaffDocument;
	}

	public List<FieldStaffFamilyMember> getFieldStaffFamilyMembers() {
		return this.fieldStaffFamilyMembers;
	}

	public void setFieldStaffFamilyMembers(List<FieldStaffFamilyMember> fieldStaffFamilyMembers) {
		this.fieldStaffFamilyMembers = fieldStaffFamilyMembers;
	}

	public FieldStaffFamilyMember addFieldStaffFamilyMember(FieldStaffFamilyMember fieldStaffFamilyMember) {
		getFieldStaffFamilyMembers().add(fieldStaffFamilyMember);
		fieldStaffFamilyMember.setFieldStaff(this);

		return fieldStaffFamilyMember;
	}

	public FieldStaffFamilyMember removeFieldStaffFamilyMember(FieldStaffFamilyMember fieldStaffFamilyMember) {
		getFieldStaffFamilyMembers().remove(fieldStaffFamilyMember);
		fieldStaffFamilyMember.setFieldStaff(null);

		return fieldStaffFamilyMember;
	}

	public List<FieldStaffHistory> getFieldStaffHistories() {
		return this.fieldStaffHistories;
	}

	public void setFieldStaffHistories(List<FieldStaffHistory> fieldStaffHistories) {
		this.fieldStaffHistories = fieldStaffHistories;
	}

	public FieldStaffHistory addFieldStaffHistory(FieldStaffHistory fieldStaffHistory) {
		getFieldStaffHistories().add(fieldStaffHistory);
		fieldStaffHistory.setFieldStaff(this);

		return fieldStaffHistory;
	}

	public FieldStaffHistory removeFieldStaffHistory(FieldStaffHistory fieldStaffHistory) {
		getFieldStaffHistories().remove(fieldStaffHistory);
		fieldStaffHistory.setFieldStaff(null);

		return fieldStaffHistory;
	}

	public List<FieldStaffLeadershipSeason> getFieldStaffLeadershipSeasons() {
		return this.fieldStaffLeadershipSeasons;
	}

	public void setFieldStaffLeadershipSeasons(List<FieldStaffLeadershipSeason> fieldStaffLeadershipSeasons) {
		this.fieldStaffLeadershipSeasons = fieldStaffLeadershipSeasons;
	}

	public FieldStaffLeadershipSeason addFieldStaffLeadershipSeason(FieldStaffLeadershipSeason fieldStaffLeadershipSeason) {
		getFieldStaffLeadershipSeasons().add(fieldStaffLeadershipSeason);
		fieldStaffLeadershipSeason.setFieldStaff(this);

		return fieldStaffLeadershipSeason;
	}

	public FieldStaffLeadershipSeason removeFieldStaffLeadershipSeason(FieldStaffLeadershipSeason fieldStaffLeadershipSeason) {
		getFieldStaffLeadershipSeasons().remove(fieldStaffLeadershipSeason);
		fieldStaffLeadershipSeason.setFieldStaff(null);

		return fieldStaffLeadershipSeason;
	}

	public List<FieldStaffNote> getFieldStaffNotes() {
		return this.fieldStaffNotes;
	}

	public void setFieldStaffNotes(List<FieldStaffNote> fieldStaffNotes) {
		this.fieldStaffNotes = fieldStaffNotes;
	}

	public FieldStaffNote addFieldStaffNote(FieldStaffNote fieldStaffNote) {
		getFieldStaffNotes().add(fieldStaffNote);
		fieldStaffNote.setFieldStaff(this);

		return fieldStaffNote;
	}

	public FieldStaffNote removeFieldStaffNote(FieldStaffNote fieldStaffNote) {
		getFieldStaffNotes().remove(fieldStaffNote);
		fieldStaffNote.setFieldStaff(null);

		return fieldStaffNote;
	}

	public List<FieldStaffNoteTopic> getFieldStaffNoteTopics() {
		return this.fieldStaffNoteTopics;
	}

	public void setFieldStaffNoteTopics(List<FieldStaffNoteTopic> fieldStaffNoteTopics) {
		this.fieldStaffNoteTopics = fieldStaffNoteTopics;
	}

	public FieldStaffNoteTopic addFieldStaffNoteTopic(FieldStaffNoteTopic fieldStaffNoteTopic) {
		getFieldStaffNoteTopics().add(fieldStaffNoteTopic);
		fieldStaffNoteTopic.setFieldStaff(this);

		return fieldStaffNoteTopic;
	}

	public FieldStaffNoteTopic removeFieldStaffNoteTopic(FieldStaffNoteTopic fieldStaffNoteTopic) {
		getFieldStaffNoteTopics().remove(fieldStaffNoteTopic);
		fieldStaffNoteTopic.setFieldStaff(null);

		return fieldStaffNoteTopic;
	}

	public List<FieldStaffParticipant> getFieldStaffParticipants() {
		return this.fieldStaffParticipants;
	}

	public void setFieldStaffParticipants(List<FieldStaffParticipant> fieldStaffParticipants) {
		this.fieldStaffParticipants = fieldStaffParticipants;
	}

	public FieldStaffParticipant addFieldStaffParticipant(FieldStaffParticipant fieldStaffParticipant) {
		getFieldStaffParticipants().add(fieldStaffParticipant);
		fieldStaffParticipant.setFieldStaff(this);

		return fieldStaffParticipant;
	}

	public FieldStaffParticipant removeFieldStaffParticipant(FieldStaffParticipant fieldStaffParticipant) {
		getFieldStaffParticipants().remove(fieldStaffParticipant);
		fieldStaffParticipant.setFieldStaff(null);

		return fieldStaffParticipant;
	}

	public List<FieldStaffPermission> getFieldStaffPermissions() {
		return this.fieldStaffPermissions;
	}

	public void setFieldStaffPermissions(List<FieldStaffPermission> fieldStaffPermissions) {
		this.fieldStaffPermissions = fieldStaffPermissions;
	}

	public FieldStaffPermission addFieldStaffPermission(FieldStaffPermission fieldStaffPermission) {
		getFieldStaffPermissions().add(fieldStaffPermission);
		fieldStaffPermission.setFieldStaff(this);

		return fieldStaffPermission;
	}

	public FieldStaffPermission removeFieldStaffPermission(FieldStaffPermission fieldStaffPermission) {
		getFieldStaffPermissions().remove(fieldStaffPermission);
		fieldStaffPermission.setFieldStaff(null);

		return fieldStaffPermission;
	}

	public List<FieldStaffQuickStatsCategoryAggregate> getFieldStaffQuickStatsCategoryAggregates() {
		return this.fieldStaffQuickStatsCategoryAggregates;
	}

	public void setFieldStaffQuickStatsCategoryAggregates(List<FieldStaffQuickStatsCategoryAggregate> fieldStaffQuickStatsCategoryAggregates) {
		this.fieldStaffQuickStatsCategoryAggregates = fieldStaffQuickStatsCategoryAggregates;
	}

	public FieldStaffQuickStatsCategoryAggregate addFieldStaffQuickStatsCategoryAggregate(FieldStaffQuickStatsCategoryAggregate fieldStaffQuickStatsCategoryAggregate) {
		getFieldStaffQuickStatsCategoryAggregates().add(fieldStaffQuickStatsCategoryAggregate);
		fieldStaffQuickStatsCategoryAggregate.setFieldStaff(this);

		return fieldStaffQuickStatsCategoryAggregate;
	}

	public FieldStaffQuickStatsCategoryAggregate removeFieldStaffQuickStatsCategoryAggregate(FieldStaffQuickStatsCategoryAggregate fieldStaffQuickStatsCategoryAggregate) {
		getFieldStaffQuickStatsCategoryAggregates().remove(fieldStaffQuickStatsCategoryAggregate);
		fieldStaffQuickStatsCategoryAggregate.setFieldStaff(null);

		return fieldStaffQuickStatsCategoryAggregate;
	}

	public List<FieldStaffQuickStatsTypeAggregate> getFieldStaffQuickStatsTypeAggregates() {
		return this.fieldStaffQuickStatsTypeAggregates;
	}

	public void setFieldStaffQuickStatsTypeAggregates(List<FieldStaffQuickStatsTypeAggregate> fieldStaffQuickStatsTypeAggregates) {
		this.fieldStaffQuickStatsTypeAggregates = fieldStaffQuickStatsTypeAggregates;
	}

	public FieldStaffQuickStatsTypeAggregate addFieldStaffQuickStatsTypeAggregate(FieldStaffQuickStatsTypeAggregate fieldStaffQuickStatsTypeAggregate) {
		getFieldStaffQuickStatsTypeAggregates().add(fieldStaffQuickStatsTypeAggregate);
		fieldStaffQuickStatsTypeAggregate.setFieldStaff(this);

		return fieldStaffQuickStatsTypeAggregate;
	}

	public FieldStaffQuickStatsTypeAggregate removeFieldStaffQuickStatsTypeAggregate(FieldStaffQuickStatsTypeAggregate fieldStaffQuickStatsTypeAggregate) {
		getFieldStaffQuickStatsTypeAggregates().remove(fieldStaffQuickStatsTypeAggregate);
		fieldStaffQuickStatsTypeAggregate.setFieldStaff(null);

		return fieldStaffQuickStatsTypeAggregate;
	}

	public List<FieldStaffReference> getFieldStaffReferences() {
		return this.fieldStaffReferences;
	}

	public void setFieldStaffReferences(List<FieldStaffReference> fieldStaffReferences) {
		this.fieldStaffReferences = fieldStaffReferences;
	}

	public FieldStaffReference addFieldStaffReference(FieldStaffReference fieldStaffReference) {
		getFieldStaffReferences().add(fieldStaffReference);
		fieldStaffReference.setFieldStaff(this);

		return fieldStaffReference;
	}

	public FieldStaffReference removeFieldStaffReference(FieldStaffReference fieldStaffReference) {
		getFieldStaffReferences().remove(fieldStaffReference);
		fieldStaffReference.setFieldStaff(null);

		return fieldStaffReference;
	}

	public List<FieldStaffSeason> getFieldStaffSeasons() {
		return this.fieldStaffSeasons;
	}

	public void setFieldStaffSeasons(List<FieldStaffSeason> fieldStaffSeasons) {
		this.fieldStaffSeasons = fieldStaffSeasons;
	}

	public FieldStaffSeason addFieldStaffSeason(FieldStaffSeason fieldStaffSeason) {
		getFieldStaffSeasons().add(fieldStaffSeason);
		fieldStaffSeason.setFieldStaff(this);

		return fieldStaffSeason;
	}

	public FieldStaffSeason removeFieldStaffSeason(FieldStaffSeason fieldStaffSeason) {
		getFieldStaffSeasons().remove(fieldStaffSeason);
		fieldStaffSeason.setFieldStaff(null);

		return fieldStaffSeason;
	}

	public List<FieldStaffUpdateLog> getFieldStaffUpdateLogs() {
		return this.fieldStaffUpdateLogs;
	}

	public void setFieldStaffUpdateLogs(List<FieldStaffUpdateLog> fieldStaffUpdateLogs) {
		this.fieldStaffUpdateLogs = fieldStaffUpdateLogs;
	}

	public FieldStaffUpdateLog addFieldStaffUpdateLog(FieldStaffUpdateLog fieldStaffUpdateLog) {
		getFieldStaffUpdateLogs().add(fieldStaffUpdateLog);
		fieldStaffUpdateLog.setFieldStaff(this);

		return fieldStaffUpdateLog;
	}

	public FieldStaffUpdateLog removeFieldStaffUpdateLog(FieldStaffUpdateLog fieldStaffUpdateLog) {
		getFieldStaffUpdateLogs().remove(fieldStaffUpdateLog);
		fieldStaffUpdateLog.setFieldStaff(null);

		return fieldStaffUpdateLog;
	}

	public List<FieldStaffWorkQueue> getFieldStaffWorkQueues() {
		return this.fieldStaffWorkQueues;
	}

	public void setFieldStaffWorkQueues(List<FieldStaffWorkQueue> fieldStaffWorkQueues) {
		this.fieldStaffWorkQueues = fieldStaffWorkQueues;
	}

	public FieldStaffWorkQueue addFieldStaffWorkQueue(FieldStaffWorkQueue fieldStaffWorkQueue) {
		getFieldStaffWorkQueues().add(fieldStaffWorkQueue);
		fieldStaffWorkQueue.setFieldStaff(this);

		return fieldStaffWorkQueue;
	}

	public FieldStaffWorkQueue removeFieldStaffWorkQueue(FieldStaffWorkQueue fieldStaffWorkQueue) {
		getFieldStaffWorkQueues().remove(fieldStaffWorkQueue);
		fieldStaffWorkQueue.setFieldStaff(null);

		return fieldStaffWorkQueue;
	}

	public List<FieldStaffWorkQueueCategoryAggregate> getFieldStaffWorkQueueCategoryAggregates() {
		return this.fieldStaffWorkQueueCategoryAggregates;
	}

	public void setFieldStaffWorkQueueCategoryAggregates(List<FieldStaffWorkQueueCategoryAggregate> fieldStaffWorkQueueCategoryAggregates) {
		this.fieldStaffWorkQueueCategoryAggregates = fieldStaffWorkQueueCategoryAggregates;
	}

	public FieldStaffWorkQueueCategoryAggregate addFieldStaffWorkQueueCategoryAggregate(FieldStaffWorkQueueCategoryAggregate fieldStaffWorkQueueCategoryAggregate) {
		getFieldStaffWorkQueueCategoryAggregates().add(fieldStaffWorkQueueCategoryAggregate);
		fieldStaffWorkQueueCategoryAggregate.setFieldStaff(this);

		return fieldStaffWorkQueueCategoryAggregate;
	}

	public FieldStaffWorkQueueCategoryAggregate removeFieldStaffWorkQueueCategoryAggregate(FieldStaffWorkQueueCategoryAggregate fieldStaffWorkQueueCategoryAggregate) {
		getFieldStaffWorkQueueCategoryAggregates().remove(fieldStaffWorkQueueCategoryAggregate);
		fieldStaffWorkQueueCategoryAggregate.setFieldStaff(null);

		return fieldStaffWorkQueueCategoryAggregate;
	}

	public List<FieldStaffWorkQueueTypeAggregate> getFieldStaffWorkQueueTypeAggregates() {
		return this.fieldStaffWorkQueueTypeAggregates;
	}

	public void setFieldStaffWorkQueueTypeAggregates(List<FieldStaffWorkQueueTypeAggregate> fieldStaffWorkQueueTypeAggregates) {
		this.fieldStaffWorkQueueTypeAggregates = fieldStaffWorkQueueTypeAggregates;
	}

	public FieldStaffWorkQueueTypeAggregate addFieldStaffWorkQueueTypeAggregate(FieldStaffWorkQueueTypeAggregate fieldStaffWorkQueueTypeAggregate) {
		getFieldStaffWorkQueueTypeAggregates().add(fieldStaffWorkQueueTypeAggregate);
		fieldStaffWorkQueueTypeAggregate.setFieldStaff(this);

		return fieldStaffWorkQueueTypeAggregate;
	}

	public FieldStaffWorkQueueTypeAggregate removeFieldStaffWorkQueueTypeAggregate(FieldStaffWorkQueueTypeAggregate fieldStaffWorkQueueTypeAggregate) {
		getFieldStaffWorkQueueTypeAggregates().remove(fieldStaffWorkQueueTypeAggregate);
		fieldStaffWorkQueueTypeAggregate.setFieldStaff(null);

		return fieldStaffWorkQueueTypeAggregate;
	}

	public List<HostFamilyEvaluation> getHostFamilyEvaluations() {
		return this.hostFamilyEvaluations;
	}

	public void setHostFamilyEvaluations(List<HostFamilyEvaluation> hostFamilyEvaluations) {
		this.hostFamilyEvaluations = hostFamilyEvaluations;
	}

	public HostFamilyEvaluation addHostFamilyEvaluation(HostFamilyEvaluation hostFamilyEvaluation) {
		getHostFamilyEvaluations().add(hostFamilyEvaluation);
		hostFamilyEvaluation.setFieldStaff(this);

		return hostFamilyEvaluation;
	}

	public HostFamilyEvaluation removeHostFamilyEvaluation(HostFamilyEvaluation hostFamilyEvaluation) {
		getHostFamilyEvaluations().remove(hostFamilyEvaluation);
		hostFamilyEvaluation.setFieldStaff(null);

		return hostFamilyEvaluation;
	}

	public List<HostFamilyParticipant> getHostFamilyParticipants1() {
		return this.hostFamilyParticipants1;
	}

	public void setHostFamilyParticipants1(List<HostFamilyParticipant> hostFamilyParticipants1) {
		this.hostFamilyParticipants1 = hostFamilyParticipants1;
	}

	public HostFamilyParticipant addHostFamilyParticipants1(HostFamilyParticipant hostFamilyParticipants1) {
		getHostFamilyParticipants1().add(hostFamilyParticipants1);
		hostFamilyParticipants1.setFieldStaff1(this);

		return hostFamilyParticipants1;
	}

	public HostFamilyParticipant removeHostFamilyParticipants1(HostFamilyParticipant hostFamilyParticipants1) {
		getHostFamilyParticipants1().remove(hostFamilyParticipants1);
		hostFamilyParticipants1.setFieldStaff1(null);

		return hostFamilyParticipants1;
	}

	public List<HostFamilyParticipant> getHostFamilyParticipants2() {
		return this.hostFamilyParticipants2;
	}

	public void setHostFamilyParticipants2(List<HostFamilyParticipant> hostFamilyParticipants2) {
		this.hostFamilyParticipants2 = hostFamilyParticipants2;
	}

	public HostFamilyParticipant addHostFamilyParticipants2(HostFamilyParticipant hostFamilyParticipants2) {
		getHostFamilyParticipants2().add(hostFamilyParticipants2);
		hostFamilyParticipants2.setFieldStaff2(this);

		return hostFamilyParticipants2;
	}

	public HostFamilyParticipant removeHostFamilyParticipants2(HostFamilyParticipant hostFamilyParticipants2) {
		getHostFamilyParticipants2().remove(hostFamilyParticipants2);
		hostFamilyParticipants2.setFieldStaff2(null);

		return hostFamilyParticipants2;
	}

	public List<HostFamilyParticipantHistory> getHostFamilyParticipantHistories() {
		return this.hostFamilyParticipantHistories;
	}

	public void setHostFamilyParticipantHistories(List<HostFamilyParticipantHistory> hostFamilyParticipantHistories) {
		this.hostFamilyParticipantHistories = hostFamilyParticipantHistories;
	}

	public HostFamilyParticipantHistory addHostFamilyParticipantHistory(HostFamilyParticipantHistory hostFamilyParticipantHistory) {
		getHostFamilyParticipantHistories().add(hostFamilyParticipantHistory);
		hostFamilyParticipantHistory.setFieldStaff(this);

		return hostFamilyParticipantHistory;
	}

	public HostFamilyParticipantHistory removeHostFamilyParticipantHistory(HostFamilyParticipantHistory hostFamilyParticipantHistory) {
		getHostFamilyParticipantHistories().remove(hostFamilyParticipantHistory);
		hostFamilyParticipantHistory.setFieldStaff(null);

		return hostFamilyParticipantHistory;
	}

	public List<HostFamilySeason> getHostFamilySeasons1() {
		return this.hostFamilySeasons1;
	}

	public void setHostFamilySeasons1(List<HostFamilySeason> hostFamilySeasons1) {
		this.hostFamilySeasons1 = hostFamilySeasons1;
	}

	public HostFamilySeason addHostFamilySeasons1(HostFamilySeason hostFamilySeasons1) {
		getHostFamilySeasons1().add(hostFamilySeasons1);
		hostFamilySeasons1.setFieldStaff1(this);

		return hostFamilySeasons1;
	}

	public HostFamilySeason removeHostFamilySeasons1(HostFamilySeason hostFamilySeasons1) {
		getHostFamilySeasons1().remove(hostFamilySeasons1);
		hostFamilySeasons1.setFieldStaff1(null);

		return hostFamilySeasons1;
	}

	public List<HostFamilySeason> getHostFamilySeasons2() {
		return this.hostFamilySeasons2;
	}

	public void setHostFamilySeasons2(List<HostFamilySeason> hostFamilySeasons2) {
		this.hostFamilySeasons2 = hostFamilySeasons2;
	}

	public HostFamilySeason addHostFamilySeasons2(HostFamilySeason hostFamilySeasons2) {
		getHostFamilySeasons2().add(hostFamilySeasons2);
		hostFamilySeasons2.setFieldStaff2(this);

		return hostFamilySeasons2;
	}

	public HostFamilySeason removeHostFamilySeasons2(HostFamilySeason hostFamilySeasons2) {
		getHostFamilySeasons2().remove(hostFamilySeasons2);
		hostFamilySeasons2.setFieldStaff2(null);

		return hostFamilySeasons2;
	}

	public List<HostFamilySeason> getHostFamilySeasons3() {
		return this.hostFamilySeasons3;
	}

	public void setHostFamilySeasons3(List<HostFamilySeason> hostFamilySeasons3) {
		this.hostFamilySeasons3 = hostFamilySeasons3;
	}

	public HostFamilySeason addHostFamilySeasons3(HostFamilySeason hostFamilySeasons3) {
		getHostFamilySeasons3().add(hostFamilySeasons3);
		hostFamilySeasons3.setFieldStaff3(this);

		return hostFamilySeasons3;
	}

	public HostFamilySeason removeHostFamilySeasons3(HostFamilySeason hostFamilySeasons3) {
		getHostFamilySeasons3().remove(hostFamilySeasons3);
		hostFamilySeasons3.setFieldStaff3(null);

		return hostFamilySeasons3;
	}

}