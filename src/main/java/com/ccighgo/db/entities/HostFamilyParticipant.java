package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilyParticipant database table.
 * 
 */
@Entity
@NamedQuery(name="HostFamilyParticipant.findAll", query="SELECT h FROM HostFamilyParticipant h")
public class HostFamilyParticipant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer hostFamilyParticipantId;

	private Byte active;

	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	private Byte hidePlacement;

	private Integer hostFamilyInterested;

	private Integer hostFamilyScore;

	private Byte isRecruiterLCLead;

	private Byte isWelcomeFamily;

	@Temporal(TemporalType.TIMESTAMP)
	private Date isWelcomeFamilyChangedDate;

	private Integer modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date movedDate;

	@Lob
	private String moveExplanation;

	private Byte movingParticipant;

	private Integer participantPayableId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date partnerApprovalDate;

	@Lob
	private String partnerRejectionMessage;

	private String permissionFormFileName;

	private String permissionFormFilePath;

	private String permissionFormName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date preferredArrivalDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date preferredDepartureDate;

	@Lob
	private String rejectionMessage;

	private Byte showToParticipant;

	private Byte uploadPermissionFormLater;

	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadPermissionLaterExpectedDate;

	@Lob
	private String uploadPermissionLaterReason;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="secondVisitLCId",insertable=false,updatable=false)
	private FieldStaff fieldStaff1;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="isRecruiterLCLeadId",insertable=false,updatable=false)
	private FieldStaff fieldStaff2;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId",insertable=false,updatable=false)
	private HostFamilySeason hostFamilySeason;

	//bi-directional many-to-one association to HostFamilyStatus
	@ManyToOne
	@JoinColumn(name="hostFamilyParticipantStatusId",insertable=false,updatable=false)
	private HostFamilyStatus hostFamilyStatus;

	//bi-directional many-to-one association to MoveReason
	@ManyToOne
	@JoinColumn(name="moveReasonId",insertable=false,updatable=false)
	private MoveReason moveReason1;

	//bi-directional many-to-one association to Participant
	@ManyToOne
	@JoinColumn(name="participantGoId",insertable=false,updatable=false)
	private Participant participant;

	//bi-directional many-to-one association to USSchool
	@ManyToOne
	@JoinColumn(name="usHighSchoolId",insertable=false,updatable=false)
	private USSchool usschool;

	//bi-directional one-to-one association to MoveReason
	@OneToOne(mappedBy="hostFamilyParticipant")
	private MoveReason moveReason2;

	public HostFamilyParticipant() {
	}

	public Integer getHostFamilyParticipantId() {
		return this.hostFamilyParticipantId;
	}

	public void setHostFamilyParticipantId(Integer hostFamilyParticipantId) {
		this.hostFamilyParticipantId = hostFamilyParticipantId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Date getApprovedDate() {
		return this.approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
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

	public Byte getHidePlacement() {
		return this.hidePlacement;
	}

	public void setHidePlacement(Byte hidePlacement) {
		this.hidePlacement = hidePlacement;
	}

	public Integer getHostFamilyInterested() {
		return this.hostFamilyInterested;
	}

	public void setHostFamilyInterested(Integer hostFamilyInterested) {
		this.hostFamilyInterested = hostFamilyInterested;
	}

	public Integer getHostFamilyScore() {
		return this.hostFamilyScore;
	}

	public void setHostFamilyScore(Integer hostFamilyScore) {
		this.hostFamilyScore = hostFamilyScore;
	}

	public Byte getIsRecruiterLCLead() {
		return this.isRecruiterLCLead;
	}

	public void setIsRecruiterLCLead(Byte isRecruiterLCLead) {
		this.isRecruiterLCLead = isRecruiterLCLead;
	}

	public Byte getIsWelcomeFamily() {
		return this.isWelcomeFamily;
	}

	public void setIsWelcomeFamily(Byte isWelcomeFamily) {
		this.isWelcomeFamily = isWelcomeFamily;
	}

	public Date getIsWelcomeFamilyChangedDate() {
		return this.isWelcomeFamilyChangedDate;
	}

	public void setIsWelcomeFamilyChangedDate(Date isWelcomeFamilyChangedDate) {
		this.isWelcomeFamilyChangedDate = isWelcomeFamilyChangedDate;
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

	public Date getMovedDate() {
		return this.movedDate;
	}

	public void setMovedDate(Date movedDate) {
		this.movedDate = movedDate;
	}

	public String getMoveExplanation() {
		return this.moveExplanation;
	}

	public void setMoveExplanation(String moveExplanation) {
		this.moveExplanation = moveExplanation;
	}

	public Byte getMovingParticipant() {
		return this.movingParticipant;
	}

	public void setMovingParticipant(Byte movingParticipant) {
		this.movingParticipant = movingParticipant;
	}

	public Integer getParticipantPayableId() {
		return this.participantPayableId;
	}

	public void setParticipantPayableId(Integer participantPayableId) {
		this.participantPayableId = participantPayableId;
	}

	public Date getPartnerApprovalDate() {
		return this.partnerApprovalDate;
	}

	public void setPartnerApprovalDate(Date partnerApprovalDate) {
		this.partnerApprovalDate = partnerApprovalDate;
	}

	public String getPartnerRejectionMessage() {
		return this.partnerRejectionMessage;
	}

	public void setPartnerRejectionMessage(String partnerRejectionMessage) {
		this.partnerRejectionMessage = partnerRejectionMessage;
	}

	public String getPermissionFormFileName() {
		return this.permissionFormFileName;
	}

	public void setPermissionFormFileName(String permissionFormFileName) {
		this.permissionFormFileName = permissionFormFileName;
	}

	public String getPermissionFormFilePath() {
		return this.permissionFormFilePath;
	}

	public void setPermissionFormFilePath(String permissionFormFilePath) {
		this.permissionFormFilePath = permissionFormFilePath;
	}

	public String getPermissionFormName() {
		return this.permissionFormName;
	}

	public void setPermissionFormName(String permissionFormName) {
		this.permissionFormName = permissionFormName;
	}

	public Date getPreferredArrivalDate() {
		return this.preferredArrivalDate;
	}

	public void setPreferredArrivalDate(Date preferredArrivalDate) {
		this.preferredArrivalDate = preferredArrivalDate;
	}

	public Date getPreferredDepartureDate() {
		return this.preferredDepartureDate;
	}

	public void setPreferredDepartureDate(Date preferredDepartureDate) {
		this.preferredDepartureDate = preferredDepartureDate;
	}

	public String getRejectionMessage() {
		return this.rejectionMessage;
	}

	public void setRejectionMessage(String rejectionMessage) {
		this.rejectionMessage = rejectionMessage;
	}

	public Byte getShowToParticipant() {
		return this.showToParticipant;
	}

	public void setShowToParticipant(Byte showToParticipant) {
		this.showToParticipant = showToParticipant;
	}

	public Byte getUploadPermissionFormLater() {
		return this.uploadPermissionFormLater;
	}

	public void setUploadPermissionFormLater(Byte uploadPermissionFormLater) {
		this.uploadPermissionFormLater = uploadPermissionFormLater;
	}

	public Date getUploadPermissionLaterExpectedDate() {
		return this.uploadPermissionLaterExpectedDate;
	}

	public void setUploadPermissionLaterExpectedDate(Date uploadPermissionLaterExpectedDate) {
		this.uploadPermissionLaterExpectedDate = uploadPermissionLaterExpectedDate;
	}

	public String getUploadPermissionLaterReason() {
		return this.uploadPermissionLaterReason;
	}

	public void setUploadPermissionLaterReason(String uploadPermissionLaterReason) {
		this.uploadPermissionLaterReason = uploadPermissionLaterReason;
	}

	public FieldStaff getFieldStaff1() {
		return this.fieldStaff1;
	}

	public void setFieldStaff1(FieldStaff fieldStaff1) {
		this.fieldStaff1 = fieldStaff1;
	}

	public FieldStaff getFieldStaff2() {
		return this.fieldStaff2;
	}

	public void setFieldStaff2(FieldStaff fieldStaff2) {
		this.fieldStaff2 = fieldStaff2;
	}

	public HostFamilySeason getHostFamilySeason() {
		return this.hostFamilySeason;
	}

	public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
		this.hostFamilySeason = hostFamilySeason;
	}

	public HostFamilyStatus getHostFamilyStatus() {
		return this.hostFamilyStatus;
	}

	public void setHostFamilyStatus(HostFamilyStatus hostFamilyStatus) {
		this.hostFamilyStatus = hostFamilyStatus;
	}

	public MoveReason getMoveReason1() {
		return this.moveReason1;
	}

	public void setMoveReason1(MoveReason moveReason1) {
		this.moveReason1 = moveReason1;
	}

	public Participant getParticipant() {
		return this.participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public USSchool getUsschool() {
		return this.usschool;
	}

	public void setUsschool(USSchool usschool) {
		this.usschool = usschool;
	}

	public MoveReason getMoveReason2() {
		return this.moveReason2;
	}

	public void setMoveReason2(MoveReason moveReason2) {
		this.moveReason2 = moveReason2;
	}

}