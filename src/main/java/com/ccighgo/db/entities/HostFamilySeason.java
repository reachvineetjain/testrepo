package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the HostFamilySeason database table.
 * 
 */
@Entity
@NamedQuery(name="HostFamilySeason.findAll", query="SELECT h FROM HostFamilySeason h")
public class HostFamilySeason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer hostFamilySeasonId;

	private Byte active;

	private Byte agreeToTerms;

	private Integer applicationApprovedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date applicationApprovedDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date applicationSubmittedDate;

	private Integer areaRepresentativeId;

	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	private Byte hasNoChildren;

	private String hostRecommendationsName;

	private String hostRecommendationsPhone;

	private Byte isDoublePlacement;

	@Lob
	private String learnAboutCCIMethod;

	private Integer localCoordinatorId;

	@Lob
	private String localNewspaperName;

	private Integer modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	private Integer recommendHost;

	private Integer regionalDirectorId;

	private Integer regionalManagerId;

	@Lob
	private String rejectionReason;

	private String signature;

	//bi-directional many-to-one association to HostFamilyCommunity
	@OneToMany(mappedBy="hostFamilySeason")
	private List<HostFamilyCommunity> hostFamilyCommunities;

	//bi-directional many-to-one association to HostFamilyContactHistory
	@OneToMany(mappedBy="hostFamilySeason")
	private List<HostFamilyContactHistory> hostFamilyContactHistories;

	//bi-directional many-to-one association to HostFamilyDetail
	@OneToMany(mappedBy="hostFamilySeason")
	private List<HostFamilyDetail> hostFamilyDetails;

	//bi-directional many-to-one association to HostFamilyDocument
	@OneToMany(mappedBy="hostFamilySeason")
	private List<HostFamilyDocument> hostFamilyDocuments;

	//bi-directional many-to-one association to HostFamilyHome
	@OneToMany(mappedBy="hostFamilySeason")
	private List<HostFamilyHome> hostFamilyHomes;

	//bi-directional many-to-one association to HostFamilyInterview
	@OneToMany(mappedBy="hostFamilySeason")
	private List<HostFamilyInterview> hostFamilyInterviews;

	//bi-directional many-to-one association to HostFamilyMember
	@OneToMany(mappedBy="hostFamilySeason")
	private List<HostFamilyMember> hostFamilyMembers;

	//bi-directional many-to-one association to HostFamilyMileageCheck
	@OneToMany(mappedBy="hostFamilySeason")
	private List<HostFamilyMileageCheck> hostFamilyMileageChecks;

	//bi-directional many-to-one association to HostFamilyParticipant
	@OneToMany(mappedBy="hostFamilySeason")
	private List<HostFamilyParticipant> hostFamilyParticipants;

	//bi-directional many-to-one association to HostFamilyParticipantHistory
	@OneToMany(mappedBy="hostFamilySeason")
	private List<HostFamilyParticipantHistory> hostFamilyParticipantHistories;

	//bi-directional many-to-one association to HostFamilyPet
	@OneToMany(mappedBy="hostFamilySeason")
	private List<HostFamilyPet> hostFamilyPets;

	//bi-directional many-to-one association to HostFamilyPhoto
	@OneToMany(mappedBy="hostFamilySeason")
	private List<HostFamilyPhoto> hostFamilyPhotos;

	//bi-directional many-to-one association to HostFamilyReference
	@OneToMany(mappedBy="hostFamilySeason")
	private List<HostFamilyReference> hostFamilyReferences;

	//bi-directional many-to-one association to DepartmentProgram
	@ManyToOne
	@JoinColumn(name="departmentProgramId",insertable=false,updatable=false)
	private DepartmentProgram departmentProgram;

	//bi-directional many-to-one association to HostFamily
	@ManyToOne
	@JoinColumn(name="hostFamilyGoId",insertable=false,updatable=false)
	private HostFamily hostFamily;

	//bi-directional many-to-one association to HostFamilyStatus
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonStatusId",insertable=false,updatable=false)
	private HostFamilyStatus hostFamilyStatus;

	//bi-directional many-to-one association to PaymentSchedule
	@ManyToOne
	@JoinColumn(name="paymentScheduleId",insertable=false,updatable=false)
	private PaymentSchedule paymentSchedule;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId",insertable=false,updatable=false)
	private Season season;

	//bi-directional many-to-one association to HostFamilySeasonNote
	@OneToMany(mappedBy="hostFamilySeason")
	private List<HostFamilySeasonNote> hostFamilySeasonNotes;

	public HostFamilySeason() {
	}

	public Integer getHostFamilySeasonId() {
		return this.hostFamilySeasonId;
	}

	public void setHostFamilySeasonId(Integer hostFamilySeasonId) {
		this.hostFamilySeasonId = hostFamilySeasonId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Byte getAgreeToTerms() {
		return this.agreeToTerms;
	}

	public void setAgreeToTerms(Byte agreeToTerms) {
		this.agreeToTerms = agreeToTerms;
	}

	public Integer getApplicationApprovedBy() {
		return this.applicationApprovedBy;
	}

	public void setApplicationApprovedBy(Integer applicationApprovedBy) {
		this.applicationApprovedBy = applicationApprovedBy;
	}

	public Date getApplicationApprovedDate() {
		return this.applicationApprovedDate;
	}

	public void setApplicationApprovedDate(Date applicationApprovedDate) {
		this.applicationApprovedDate = applicationApprovedDate;
	}

	public Date getApplicationSubmittedDate() {
		return this.applicationSubmittedDate;
	}

	public void setApplicationSubmittedDate(Date applicationSubmittedDate) {
		this.applicationSubmittedDate = applicationSubmittedDate;
	}

	public Integer getAreaRepresentativeId() {
		return this.areaRepresentativeId;
	}

	public void setAreaRepresentativeId(Integer areaRepresentativeId) {
		this.areaRepresentativeId = areaRepresentativeId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Byte getHasNoChildren() {
		return this.hasNoChildren;
	}

	public void setHasNoChildren(Byte hasNoChildren) {
		this.hasNoChildren = hasNoChildren;
	}

	public String getHostRecommendationsName() {
		return this.hostRecommendationsName;
	}

	public void setHostRecommendationsName(String hostRecommendationsName) {
		this.hostRecommendationsName = hostRecommendationsName;
	}

	public String getHostRecommendationsPhone() {
		return this.hostRecommendationsPhone;
	}

	public void setHostRecommendationsPhone(String hostRecommendationsPhone) {
		this.hostRecommendationsPhone = hostRecommendationsPhone;
	}

	public Byte getIsDoublePlacement() {
		return this.isDoublePlacement;
	}

	public void setIsDoublePlacement(Byte isDoublePlacement) {
		this.isDoublePlacement = isDoublePlacement;
	}

	public String getLearnAboutCCIMethod() {
		return this.learnAboutCCIMethod;
	}

	public void setLearnAboutCCIMethod(String learnAboutCCIMethod) {
		this.learnAboutCCIMethod = learnAboutCCIMethod;
	}

	public Integer getLocalCoordinatorId() {
		return this.localCoordinatorId;
	}

	public void setLocalCoordinatorId(Integer localCoordinatorId) {
		this.localCoordinatorId = localCoordinatorId;
	}

	public String getLocalNewspaperName() {
		return this.localNewspaperName;
	}

	public void setLocalNewspaperName(String localNewspaperName) {
		this.localNewspaperName = localNewspaperName;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Integer getRecommendHost() {
		return this.recommendHost;
	}

	public void setRecommendHost(Integer recommendHost) {
		this.recommendHost = recommendHost;
	}

	public Integer getRegionalDirectorId() {
		return this.regionalDirectorId;
	}

	public void setRegionalDirectorId(Integer regionalDirectorId) {
		this.regionalDirectorId = regionalDirectorId;
	}

	public Integer getRegionalManagerId() {
		return this.regionalManagerId;
	}

	public void setRegionalManagerId(Integer regionalManagerId) {
		this.regionalManagerId = regionalManagerId;
	}

	public String getRejectionReason() {
		return this.rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public List<HostFamilyCommunity> getHostFamilyCommunities() {
		return this.hostFamilyCommunities;
	}

	public void setHostFamilyCommunities(List<HostFamilyCommunity> hostFamilyCommunities) {
		this.hostFamilyCommunities = hostFamilyCommunities;
	}

	public HostFamilyCommunity addHostFamilyCommunity(HostFamilyCommunity hostFamilyCommunity) {
		getHostFamilyCommunities().add(hostFamilyCommunity);
		hostFamilyCommunity.setHostFamilySeason(this);

		return hostFamilyCommunity;
	}

	public HostFamilyCommunity removeHostFamilyCommunity(HostFamilyCommunity hostFamilyCommunity) {
		getHostFamilyCommunities().remove(hostFamilyCommunity);
		hostFamilyCommunity.setHostFamilySeason(null);

		return hostFamilyCommunity;
	}

	public List<HostFamilyContactHistory> getHostFamilyContactHistories() {
		return this.hostFamilyContactHistories;
	}

	public void setHostFamilyContactHistories(List<HostFamilyContactHistory> hostFamilyContactHistories) {
		this.hostFamilyContactHistories = hostFamilyContactHistories;
	}

	public HostFamilyContactHistory addHostFamilyContactHistory(HostFamilyContactHistory hostFamilyContactHistory) {
		getHostFamilyContactHistories().add(hostFamilyContactHistory);
		hostFamilyContactHistory.setHostFamilySeason(this);

		return hostFamilyContactHistory;
	}

	public HostFamilyContactHistory removeHostFamilyContactHistory(HostFamilyContactHistory hostFamilyContactHistory) {
		getHostFamilyContactHistories().remove(hostFamilyContactHistory);
		hostFamilyContactHistory.setHostFamilySeason(null);

		return hostFamilyContactHistory;
	}

	public List<HostFamilyDetail> getHostFamilyDetails() {
		return this.hostFamilyDetails;
	}

	public void setHostFamilyDetails(List<HostFamilyDetail> hostFamilyDetails) {
		this.hostFamilyDetails = hostFamilyDetails;
	}

	public HostFamilyDetail addHostFamilyDetail(HostFamilyDetail hostFamilyDetail) {
		getHostFamilyDetails().add(hostFamilyDetail);
		hostFamilyDetail.setHostFamilySeason(this);

		return hostFamilyDetail;
	}

	public HostFamilyDetail removeHostFamilyDetail(HostFamilyDetail hostFamilyDetail) {
		getHostFamilyDetails().remove(hostFamilyDetail);
		hostFamilyDetail.setHostFamilySeason(null);

		return hostFamilyDetail;
	}

	public List<HostFamilyDocument> getHostFamilyDocuments() {
		return this.hostFamilyDocuments;
	}

	public void setHostFamilyDocuments(List<HostFamilyDocument> hostFamilyDocuments) {
		this.hostFamilyDocuments = hostFamilyDocuments;
	}

	public HostFamilyDocument addHostFamilyDocument(HostFamilyDocument hostFamilyDocument) {
		getHostFamilyDocuments().add(hostFamilyDocument);
		hostFamilyDocument.setHostFamilySeason(this);

		return hostFamilyDocument;
	}

	public HostFamilyDocument removeHostFamilyDocument(HostFamilyDocument hostFamilyDocument) {
		getHostFamilyDocuments().remove(hostFamilyDocument);
		hostFamilyDocument.setHostFamilySeason(null);

		return hostFamilyDocument;
	}

	public List<HostFamilyHome> getHostFamilyHomes() {
		return this.hostFamilyHomes;
	}

	public void setHostFamilyHomes(List<HostFamilyHome> hostFamilyHomes) {
		this.hostFamilyHomes = hostFamilyHomes;
	}

	public HostFamilyHome addHostFamilyHome(HostFamilyHome hostFamilyHome) {
		getHostFamilyHomes().add(hostFamilyHome);
		hostFamilyHome.setHostFamilySeason(this);

		return hostFamilyHome;
	}

	public HostFamilyHome removeHostFamilyHome(HostFamilyHome hostFamilyHome) {
		getHostFamilyHomes().remove(hostFamilyHome);
		hostFamilyHome.setHostFamilySeason(null);

		return hostFamilyHome;
	}

	public List<HostFamilyInterview> getHostFamilyInterviews() {
		return this.hostFamilyInterviews;
	}

	public void setHostFamilyInterviews(List<HostFamilyInterview> hostFamilyInterviews) {
		this.hostFamilyInterviews = hostFamilyInterviews;
	}

	public HostFamilyInterview addHostFamilyInterview(HostFamilyInterview hostFamilyInterview) {
		getHostFamilyInterviews().add(hostFamilyInterview);
		hostFamilyInterview.setHostFamilySeason(this);

		return hostFamilyInterview;
	}

	public HostFamilyInterview removeHostFamilyInterview(HostFamilyInterview hostFamilyInterview) {
		getHostFamilyInterviews().remove(hostFamilyInterview);
		hostFamilyInterview.setHostFamilySeason(null);

		return hostFamilyInterview;
	}

	public List<HostFamilyMember> getHostFamilyMembers() {
		return this.hostFamilyMembers;
	}

	public void setHostFamilyMembers(List<HostFamilyMember> hostFamilyMembers) {
		this.hostFamilyMembers = hostFamilyMembers;
	}

	public HostFamilyMember addHostFamilyMember(HostFamilyMember hostFamilyMember) {
		getHostFamilyMembers().add(hostFamilyMember);
		hostFamilyMember.setHostFamilySeason(this);

		return hostFamilyMember;
	}

	public HostFamilyMember removeHostFamilyMember(HostFamilyMember hostFamilyMember) {
		getHostFamilyMembers().remove(hostFamilyMember);
		hostFamilyMember.setHostFamilySeason(null);

		return hostFamilyMember;
	}

	public List<HostFamilyMileageCheck> getHostFamilyMileageChecks() {
		return this.hostFamilyMileageChecks;
	}

	public void setHostFamilyMileageChecks(List<HostFamilyMileageCheck> hostFamilyMileageChecks) {
		this.hostFamilyMileageChecks = hostFamilyMileageChecks;
	}

	public HostFamilyMileageCheck addHostFamilyMileageCheck(HostFamilyMileageCheck hostFamilyMileageCheck) {
		getHostFamilyMileageChecks().add(hostFamilyMileageCheck);
		hostFamilyMileageCheck.setHostFamilySeason(this);

		return hostFamilyMileageCheck;
	}

	public HostFamilyMileageCheck removeHostFamilyMileageCheck(HostFamilyMileageCheck hostFamilyMileageCheck) {
		getHostFamilyMileageChecks().remove(hostFamilyMileageCheck);
		hostFamilyMileageCheck.setHostFamilySeason(null);

		return hostFamilyMileageCheck;
	}

	public List<HostFamilyParticipant> getHostFamilyParticipants() {
		return this.hostFamilyParticipants;
	}

	public void setHostFamilyParticipants(List<HostFamilyParticipant> hostFamilyParticipants) {
		this.hostFamilyParticipants = hostFamilyParticipants;
	}

	public HostFamilyParticipant addHostFamilyParticipant(HostFamilyParticipant hostFamilyParticipant) {
		getHostFamilyParticipants().add(hostFamilyParticipant);
		hostFamilyParticipant.setHostFamilySeason(this);

		return hostFamilyParticipant;
	}

	public HostFamilyParticipant removeHostFamilyParticipant(HostFamilyParticipant hostFamilyParticipant) {
		getHostFamilyParticipants().remove(hostFamilyParticipant);
		hostFamilyParticipant.setHostFamilySeason(null);

		return hostFamilyParticipant;
	}

	public List<HostFamilyParticipantHistory> getHostFamilyParticipantHistories() {
		return this.hostFamilyParticipantHistories;
	}

	public void setHostFamilyParticipantHistories(List<HostFamilyParticipantHistory> hostFamilyParticipantHistories) {
		this.hostFamilyParticipantHistories = hostFamilyParticipantHistories;
	}

	public HostFamilyParticipantHistory addHostFamilyParticipantHistory(HostFamilyParticipantHistory hostFamilyParticipantHistory) {
		getHostFamilyParticipantHistories().add(hostFamilyParticipantHistory);
		hostFamilyParticipantHistory.setHostFamilySeason(this);

		return hostFamilyParticipantHistory;
	}

	public HostFamilyParticipantHistory removeHostFamilyParticipantHistory(HostFamilyParticipantHistory hostFamilyParticipantHistory) {
		getHostFamilyParticipantHistories().remove(hostFamilyParticipantHistory);
		hostFamilyParticipantHistory.setHostFamilySeason(null);

		return hostFamilyParticipantHistory;
	}

	public List<HostFamilyPet> getHostFamilyPets() {
		return this.hostFamilyPets;
	}

	public void setHostFamilyPets(List<HostFamilyPet> hostFamilyPets) {
		this.hostFamilyPets = hostFamilyPets;
	}

	public HostFamilyPet addHostFamilyPet(HostFamilyPet hostFamilyPet) {
		getHostFamilyPets().add(hostFamilyPet);
		hostFamilyPet.setHostFamilySeason(this);

		return hostFamilyPet;
	}

	public HostFamilyPet removeHostFamilyPet(HostFamilyPet hostFamilyPet) {
		getHostFamilyPets().remove(hostFamilyPet);
		hostFamilyPet.setHostFamilySeason(null);

		return hostFamilyPet;
	}

	public List<HostFamilyPhoto> getHostFamilyPhotos() {
		return this.hostFamilyPhotos;
	}

	public void setHostFamilyPhotos(List<HostFamilyPhoto> hostFamilyPhotos) {
		this.hostFamilyPhotos = hostFamilyPhotos;
	}

	public HostFamilyPhoto addHostFamilyPhoto(HostFamilyPhoto hostFamilyPhoto) {
		getHostFamilyPhotos().add(hostFamilyPhoto);
		hostFamilyPhoto.setHostFamilySeason(this);

		return hostFamilyPhoto;
	}

	public HostFamilyPhoto removeHostFamilyPhoto(HostFamilyPhoto hostFamilyPhoto) {
		getHostFamilyPhotos().remove(hostFamilyPhoto);
		hostFamilyPhoto.setHostFamilySeason(null);

		return hostFamilyPhoto;
	}

	public List<HostFamilyReference> getHostFamilyReferences() {
		return this.hostFamilyReferences;
	}

	public void setHostFamilyReferences(List<HostFamilyReference> hostFamilyReferences) {
		this.hostFamilyReferences = hostFamilyReferences;
	}

	public HostFamilyReference addHostFamilyReference(HostFamilyReference hostFamilyReference) {
		getHostFamilyReferences().add(hostFamilyReference);
		hostFamilyReference.setHostFamilySeason(this);

		return hostFamilyReference;
	}

	public HostFamilyReference removeHostFamilyReference(HostFamilyReference hostFamilyReference) {
		getHostFamilyReferences().remove(hostFamilyReference);
		hostFamilyReference.setHostFamilySeason(null);

		return hostFamilyReference;
	}

	public DepartmentProgram getDepartmentProgram() {
		return this.departmentProgram;
	}

	public void setDepartmentProgram(DepartmentProgram departmentProgram) {
		this.departmentProgram = departmentProgram;
	}

	public HostFamily getHostFamily() {
		return this.hostFamily;
	}

	public void setHostFamily(HostFamily hostFamily) {
		this.hostFamily = hostFamily;
	}

	public HostFamilyStatus getHostFamilyStatus() {
		return this.hostFamilyStatus;
	}

	public void setHostFamilyStatus(HostFamilyStatus hostFamilyStatus) {
		this.hostFamilyStatus = hostFamilyStatus;
	}

	public PaymentSchedule getPaymentSchedule() {
		return this.paymentSchedule;
	}

	public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
		this.paymentSchedule = paymentSchedule;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public List<HostFamilySeasonNote> getHostFamilySeasonNotes() {
		return this.hostFamilySeasonNotes;
	}

	public void setHostFamilySeasonNotes(List<HostFamilySeasonNote> hostFamilySeasonNotes) {
		this.hostFamilySeasonNotes = hostFamilySeasonNotes;
	}

	public HostFamilySeasonNote addHostFamilySeasonNote(HostFamilySeasonNote hostFamilySeasonNote) {
		getHostFamilySeasonNotes().add(hostFamilySeasonNote);
		hostFamilySeasonNote.setHostFamilySeason(this);

		return hostFamilySeasonNote;
	}

	public HostFamilySeasonNote removeHostFamilySeasonNote(HostFamilySeasonNote hostFamilySeasonNote) {
		getHostFamilySeasonNotes().remove(hostFamilySeasonNote);
		hostFamilySeasonNote.setHostFamilySeason(null);

		return hostFamilySeasonNote;
	}

}