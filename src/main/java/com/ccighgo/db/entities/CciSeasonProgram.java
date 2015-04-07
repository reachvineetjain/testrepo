package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the cci_season_program database table.
 * 
 */
@Entity
@Table(name="cci_season_program")
@NamedQuery(name="CciSeasonProgram.findAll", query="SELECT c FROM CciSeasonProgram c")
public class CciSeasonProgram implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int seasonProgramID;

	@Column(length=1)
	private String active;

	private int allocationAugust;

	private int allocationAugustGuaranteed;

	private int allocationDirectPlacement;

	private int allocationEZ;

	private int allocationInternFP;

	private int allocationInternJB;

	private int allocationInternJF;

	private int allocationInternSP;

	private int allocationInternVJF;

	private int allocationJanuary;

	private int allocationJanuaryGuaranteed;

	private int allocationJobBoard;

	private int allocationJobFair;

	private int allocationSelfPlaced;

	private int allocationTraineeFP;

	private int allocationTraineeJB;

	private int allocationTraineeJF;

	private int allocationTraineeSP;

	private int allocationTraineeVJF;

	private int allocationVirtualJobFair;

	@Temporal(TemporalType.TIMESTAMP)
	private Date applicationDeadlineDate;

	private int cAPDepositContractFeeTypeID;

	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	private int defaultPaymentScheduleID;

	private int departureBoundary;

	private int deposit;

	@Temporal(TemporalType.TIMESTAMP)
	private Date earliestBirthDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	private int fieldStaffHoldLength;

	private int fieldStaffStipend;

	private int fieldStaffStudentView;

	@Column(length=1)
	private String hFCanHostFirstSemester;

	@Column(length=1)
	private String hFCanHostSecondSemester;

	@Column(length=1)
	private String hFCanReturningView;

	private int hFRequiredReferences;

	private int hoursBeforeHoldExpirationWarning;

	@Column(length=1)
	private String isJobBoardOpen;

	@Column(length=1)
	private String isPSPP;

	@Temporal(TemporalType.TIMESTAMP)
	private Date latestBirthDate;

	private int maxPendingJobApps;

	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date participantInsuranceUpdated;

	@Column(length=50)
	private String seasonFullName;

	@Column(length=50)
	private String seasonName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date secondDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date secondEarliestBirthDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date secondEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date secondLatestBirthDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date secondStartDate;

	private int sequenceNo;

	private byte[] stamp;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Column(length=1)
	private String welcomeFamilyActive;

	//bi-directional many-to-one association to CciSeasonDocument
	@OneToMany(mappedBy="cciSeasonProgram")
	private List<CciSeasonDocument> cciSeasonDocuments;

	//bi-directional many-to-one association to CciSeasonNote
	@OneToMany(mappedBy="cciSeasonProgram")
	private List<CciSeasonNote> cciSeasonNotes;

	//bi-directional many-to-one association to CciFieldStaffAgreement
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fieldStaffAgreementID")
	private CciFieldStaffAgreement cciFieldStaffAgreement;

	//bi-directional many-to-one association to CciProgram
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="programID")
	private CciProgram cciProgram;

	//bi-directional many-to-one association to CciSeasonStatus
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="seasonStatusID")
	private CciSeasonStatus cciSeasonStatus;

	public CciSeasonProgram() {
	}

	public int getSeasonProgramID() {
		return this.seasonProgramID;
	}

	public void setSeasonProgramID(int seasonProgramID) {
		this.seasonProgramID = seasonProgramID;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public int getAllocationAugust() {
		return this.allocationAugust;
	}

	public void setAllocationAugust(int allocationAugust) {
		this.allocationAugust = allocationAugust;
	}

	public int getAllocationAugustGuaranteed() {
		return this.allocationAugustGuaranteed;
	}

	public void setAllocationAugustGuaranteed(int allocationAugustGuaranteed) {
		this.allocationAugustGuaranteed = allocationAugustGuaranteed;
	}

	public int getAllocationDirectPlacement() {
		return this.allocationDirectPlacement;
	}

	public void setAllocationDirectPlacement(int allocationDirectPlacement) {
		this.allocationDirectPlacement = allocationDirectPlacement;
	}

	public int getAllocationEZ() {
		return this.allocationEZ;
	}

	public void setAllocationEZ(int allocationEZ) {
		this.allocationEZ = allocationEZ;
	}

	public int getAllocationInternFP() {
		return this.allocationInternFP;
	}

	public void setAllocationInternFP(int allocationInternFP) {
		this.allocationInternFP = allocationInternFP;
	}

	public int getAllocationInternJB() {
		return this.allocationInternJB;
	}

	public void setAllocationInternJB(int allocationInternJB) {
		this.allocationInternJB = allocationInternJB;
	}

	public int getAllocationInternJF() {
		return this.allocationInternJF;
	}

	public void setAllocationInternJF(int allocationInternJF) {
		this.allocationInternJF = allocationInternJF;
	}

	public int getAllocationInternSP() {
		return this.allocationInternSP;
	}

	public void setAllocationInternSP(int allocationInternSP) {
		this.allocationInternSP = allocationInternSP;
	}

	public int getAllocationInternVJF() {
		return this.allocationInternVJF;
	}

	public void setAllocationInternVJF(int allocationInternVJF) {
		this.allocationInternVJF = allocationInternVJF;
	}

	public int getAllocationJanuary() {
		return this.allocationJanuary;
	}

	public void setAllocationJanuary(int allocationJanuary) {
		this.allocationJanuary = allocationJanuary;
	}

	public int getAllocationJanuaryGuaranteed() {
		return this.allocationJanuaryGuaranteed;
	}

	public void setAllocationJanuaryGuaranteed(int allocationJanuaryGuaranteed) {
		this.allocationJanuaryGuaranteed = allocationJanuaryGuaranteed;
	}

	public int getAllocationJobBoard() {
		return this.allocationJobBoard;
	}

	public void setAllocationJobBoard(int allocationJobBoard) {
		this.allocationJobBoard = allocationJobBoard;
	}

	public int getAllocationJobFair() {
		return this.allocationJobFair;
	}

	public void setAllocationJobFair(int allocationJobFair) {
		this.allocationJobFair = allocationJobFair;
	}

	public int getAllocationSelfPlaced() {
		return this.allocationSelfPlaced;
	}

	public void setAllocationSelfPlaced(int allocationSelfPlaced) {
		this.allocationSelfPlaced = allocationSelfPlaced;
	}

	public int getAllocationTraineeFP() {
		return this.allocationTraineeFP;
	}

	public void setAllocationTraineeFP(int allocationTraineeFP) {
		this.allocationTraineeFP = allocationTraineeFP;
	}

	public int getAllocationTraineeJB() {
		return this.allocationTraineeJB;
	}

	public void setAllocationTraineeJB(int allocationTraineeJB) {
		this.allocationTraineeJB = allocationTraineeJB;
	}

	public int getAllocationTraineeJF() {
		return this.allocationTraineeJF;
	}

	public void setAllocationTraineeJF(int allocationTraineeJF) {
		this.allocationTraineeJF = allocationTraineeJF;
	}

	public int getAllocationTraineeSP() {
		return this.allocationTraineeSP;
	}

	public void setAllocationTraineeSP(int allocationTraineeSP) {
		this.allocationTraineeSP = allocationTraineeSP;
	}

	public int getAllocationTraineeVJF() {
		return this.allocationTraineeVJF;
	}

	public void setAllocationTraineeVJF(int allocationTraineeVJF) {
		this.allocationTraineeVJF = allocationTraineeVJF;
	}

	public int getAllocationVirtualJobFair() {
		return this.allocationVirtualJobFair;
	}

	public void setAllocationVirtualJobFair(int allocationVirtualJobFair) {
		this.allocationVirtualJobFair = allocationVirtualJobFair;
	}

	public Date getApplicationDeadlineDate() {
		return this.applicationDeadlineDate;
	}

	public void setApplicationDeadlineDate(Date applicationDeadlineDate) {
		this.applicationDeadlineDate = applicationDeadlineDate;
	}

	public int getCAPDepositContractFeeTypeID() {
		return this.cAPDepositContractFeeTypeID;
	}

	public void setCAPDepositContractFeeTypeID(int cAPDepositContractFeeTypeID) {
		this.cAPDepositContractFeeTypeID = cAPDepositContractFeeTypeID;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public int getDefaultPaymentScheduleID() {
		return this.defaultPaymentScheduleID;
	}

	public void setDefaultPaymentScheduleID(int defaultPaymentScheduleID) {
		this.defaultPaymentScheduleID = defaultPaymentScheduleID;
	}

	public int getDepartureBoundary() {
		return this.departureBoundary;
	}

	public void setDepartureBoundary(int departureBoundary) {
		this.departureBoundary = departureBoundary;
	}

	public int getDeposit() {
		return this.deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public Date getEarliestBirthDate() {
		return this.earliestBirthDate;
	}

	public void setEarliestBirthDate(Date earliestBirthDate) {
		this.earliestBirthDate = earliestBirthDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getFieldStaffHoldLength() {
		return this.fieldStaffHoldLength;
	}

	public void setFieldStaffHoldLength(int fieldStaffHoldLength) {
		this.fieldStaffHoldLength = fieldStaffHoldLength;
	}

	public int getFieldStaffStipend() {
		return this.fieldStaffStipend;
	}

	public void setFieldStaffStipend(int fieldStaffStipend) {
		this.fieldStaffStipend = fieldStaffStipend;
	}

	public int getFieldStaffStudentView() {
		return this.fieldStaffStudentView;
	}

	public void setFieldStaffStudentView(int fieldStaffStudentView) {
		this.fieldStaffStudentView = fieldStaffStudentView;
	}

	public String getHFCanHostFirstSemester() {
		return this.hFCanHostFirstSemester;
	}

	public void setHFCanHostFirstSemester(String hFCanHostFirstSemester) {
		this.hFCanHostFirstSemester = hFCanHostFirstSemester;
	}

	public String getHFCanHostSecondSemester() {
		return this.hFCanHostSecondSemester;
	}

	public void setHFCanHostSecondSemester(String hFCanHostSecondSemester) {
		this.hFCanHostSecondSemester = hFCanHostSecondSemester;
	}

	public String getHFCanReturningView() {
		return this.hFCanReturningView;
	}

	public void setHFCanReturningView(String hFCanReturningView) {
		this.hFCanReturningView = hFCanReturningView;
	}

	public int getHFRequiredReferences() {
		return this.hFRequiredReferences;
	}

	public void setHFRequiredReferences(int hFRequiredReferences) {
		this.hFRequiredReferences = hFRequiredReferences;
	}

	public int getHoursBeforeHoldExpirationWarning() {
		return this.hoursBeforeHoldExpirationWarning;
	}

	public void setHoursBeforeHoldExpirationWarning(int hoursBeforeHoldExpirationWarning) {
		this.hoursBeforeHoldExpirationWarning = hoursBeforeHoldExpirationWarning;
	}

	public String getIsJobBoardOpen() {
		return this.isJobBoardOpen;
	}

	public void setIsJobBoardOpen(String isJobBoardOpen) {
		this.isJobBoardOpen = isJobBoardOpen;
	}

	public String getIsPSPP() {
		return this.isPSPP;
	}

	public void setIsPSPP(String isPSPP) {
		this.isPSPP = isPSPP;
	}

	public Date getLatestBirthDate() {
		return this.latestBirthDate;
	}

	public void setLatestBirthDate(Date latestBirthDate) {
		this.latestBirthDate = latestBirthDate;
	}

	public int getMaxPendingJobApps() {
		return this.maxPendingJobApps;
	}

	public void setMaxPendingJobApps(int maxPendingJobApps) {
		this.maxPendingJobApps = maxPendingJobApps;
	}

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Date getParticipantInsuranceUpdated() {
		return this.participantInsuranceUpdated;
	}

	public void setParticipantInsuranceUpdated(Date participantInsuranceUpdated) {
		this.participantInsuranceUpdated = participantInsuranceUpdated;
	}

	public String getSeasonFullName() {
		return this.seasonFullName;
	}

	public void setSeasonFullName(String seasonFullName) {
		this.seasonFullName = seasonFullName;
	}

	public String getSeasonName() {
		return this.seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public Date getSecondDeadlineDate() {
		return this.secondDeadlineDate;
	}

	public void setSecondDeadlineDate(Date secondDeadlineDate) {
		this.secondDeadlineDate = secondDeadlineDate;
	}

	public Date getSecondEarliestBirthDate() {
		return this.secondEarliestBirthDate;
	}

	public void setSecondEarliestBirthDate(Date secondEarliestBirthDate) {
		this.secondEarliestBirthDate = secondEarliestBirthDate;
	}

	public Date getSecondEndDate() {
		return this.secondEndDate;
	}

	public void setSecondEndDate(Date secondEndDate) {
		this.secondEndDate = secondEndDate;
	}

	public Date getSecondLatestBirthDate() {
		return this.secondLatestBirthDate;
	}

	public void setSecondLatestBirthDate(Date secondLatestBirthDate) {
		this.secondLatestBirthDate = secondLatestBirthDate;
	}

	public Date getSecondStartDate() {
		return this.secondStartDate;
	}

	public void setSecondStartDate(Date secondStartDate) {
		this.secondStartDate = secondStartDate;
	}

	public int getSequenceNo() {
		return this.sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public byte[] getStamp() {
		return this.stamp;
	}

	public void setStamp(byte[] stamp) {
		this.stamp = stamp;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getWelcomeFamilyActive() {
		return this.welcomeFamilyActive;
	}

	public void setWelcomeFamilyActive(String welcomeFamilyActive) {
		this.welcomeFamilyActive = welcomeFamilyActive;
	}

	public List<CciSeasonDocument> getCciSeasonDocuments() {
		return this.cciSeasonDocuments;
	}

	public void setCciSeasonDocuments(List<CciSeasonDocument> cciSeasonDocuments) {
		this.cciSeasonDocuments = cciSeasonDocuments;
	}

	public CciSeasonDocument addCciSeasonDocument(CciSeasonDocument cciSeasonDocument) {
		getCciSeasonDocuments().add(cciSeasonDocument);
		cciSeasonDocument.setCciSeasonProgram(this);

		return cciSeasonDocument;
	}

	public CciSeasonDocument removeCciSeasonDocument(CciSeasonDocument cciSeasonDocument) {
		getCciSeasonDocuments().remove(cciSeasonDocument);
		cciSeasonDocument.setCciSeasonProgram(null);

		return cciSeasonDocument;
	}

	public List<CciSeasonNote> getCciSeasonNotes() {
		return this.cciSeasonNotes;
	}

	public void setCciSeasonNotes(List<CciSeasonNote> cciSeasonNotes) {
		this.cciSeasonNotes = cciSeasonNotes;
	}

	public CciSeasonNote addCciSeasonNote(CciSeasonNote cciSeasonNote) {
		getCciSeasonNotes().add(cciSeasonNote);
		cciSeasonNote.setCciSeasonProgram(this);

		return cciSeasonNote;
	}

	public CciSeasonNote removeCciSeasonNote(CciSeasonNote cciSeasonNote) {
		getCciSeasonNotes().remove(cciSeasonNote);
		cciSeasonNote.setCciSeasonProgram(null);

		return cciSeasonNote;
	}

	public CciFieldStaffAgreement getCciFieldStaffAgreement() {
		return this.cciFieldStaffAgreement;
	}

	public void setCciFieldStaffAgreement(CciFieldStaffAgreement cciFieldStaffAgreement) {
		this.cciFieldStaffAgreement = cciFieldStaffAgreement;
	}

	public CciProgram getCciProgram() {
		return this.cciProgram;
	}

	public void setCciProgram(CciProgram cciProgram) {
		this.cciProgram = cciProgram;
	}

	public CciSeasonStatus getCciSeasonStatus() {
		return this.cciSeasonStatus;
	}

	public void setCciSeasonStatus(CciSeasonStatus cciSeasonStatus) {
		this.cciSeasonStatus = cciSeasonStatus;
	}

}