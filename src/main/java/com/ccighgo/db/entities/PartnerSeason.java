package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the PartnerSeason database table.
 * 
 */
@Entity
@Table(name="PartnerSeason")
@NamedQuery(name="PartnerSeason.findAll", query="SELECT p FROM PartnerSeason p")
public class PartnerSeason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerSeasonId;

	private byte active;

	private byte canAccessJobBoard;

	private Integer cciStaffUserId;

	private int contractScheduleId;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer departmentProgramId;

	private byte disableAddParticipant;

	@Column(length=2000)
	private String exceptionComments;

	@Column(length=200)
	private String insuranceCarrierName;

	@Column(length=50)
	private String insurancePhoneNumber;

	@Column(length=100)
	private String insurancePolicyNumber;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date originalsReceivedDate;

	private byte participantPaysDeposit;

	@Temporal(TemporalType.TIMESTAMP)
	private Date partnerSeasonAppDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date partnerSeasonEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date partnerSeasonExtAppDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date partnerSeasonExtSecSemDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date partnerSeasonSecSemDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date partnerSeasonStartDate;

	private Integer partnerTaxCompanyId;

	private byte questionaireRequired;

	@Temporal(TemporalType.TIMESTAMP)
	private Date questionnaireSubmittedOn;

	private Integer seasonId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timelyReturnReportReceivedDate;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId")
	private Partner partner;

	//bi-directional many-to-one association to PartnerStatus
	@ManyToOne
	@JoinColumn(name="partnerSeasonStatusId")
	private PartnerStatus partnerStatus;

	//bi-directional many-to-one association to PartnerSeasonAllocation
	@OneToMany(mappedBy="partnerSeason")
	private List<PartnerSeasonAllocation> partnerSeasonAllocations;

	//bi-directional many-to-one association to PartnerSeasonContract
	@OneToMany(mappedBy="partnerSeason")
	private List<PartnerSeasonContract> partnerSeasonContracts;

	public PartnerSeason() {
	}

	public Integer getPartnerSeasonId() {
		return this.partnerSeasonId;
	}

	public void setPartnerSeasonId(Integer partnerSeasonId) {
		this.partnerSeasonId = partnerSeasonId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public byte getCanAccessJobBoard() {
		return this.canAccessJobBoard;
	}

	public void setCanAccessJobBoard(byte canAccessJobBoard) {
		this.canAccessJobBoard = canAccessJobBoard;
	}

	public Integer getCciStaffUserId() {
		return this.cciStaffUserId;
	}

	public void setCciStaffUserId(Integer cciStaffUserId) {
		this.cciStaffUserId = cciStaffUserId;
	}

	public int getContractScheduleId() {
		return this.contractScheduleId;
	}

	public void setContractScheduleId(int contractScheduleId) {
		this.contractScheduleId = contractScheduleId;
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

	public Integer getDepartmentProgramId() {
		return this.departmentProgramId;
	}

	public void setDepartmentProgramId(Integer departmentProgramId) {
		this.departmentProgramId = departmentProgramId;
	}

	public byte getDisableAddParticipant() {
		return this.disableAddParticipant;
	}

	public void setDisableAddParticipant(byte disableAddParticipant) {
		this.disableAddParticipant = disableAddParticipant;
	}

	public String getExceptionComments() {
		return this.exceptionComments;
	}

	public void setExceptionComments(String exceptionComments) {
		this.exceptionComments = exceptionComments;
	}

	public String getInsuranceCarrierName() {
		return this.insuranceCarrierName;
	}

	public void setInsuranceCarrierName(String insuranceCarrierName) {
		this.insuranceCarrierName = insuranceCarrierName;
	}

	public String getInsurancePhoneNumber() {
		return this.insurancePhoneNumber;
	}

	public void setInsurancePhoneNumber(String insurancePhoneNumber) {
		this.insurancePhoneNumber = insurancePhoneNumber;
	}

	public String getInsurancePolicyNumber() {
		return this.insurancePolicyNumber;
	}

	public void setInsurancePolicyNumber(String insurancePolicyNumber) {
		this.insurancePolicyNumber = insurancePolicyNumber;
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

	public Date getOriginalsReceivedDate() {
		return this.originalsReceivedDate;
	}

	public void setOriginalsReceivedDate(Date originalsReceivedDate) {
		this.originalsReceivedDate = originalsReceivedDate;
	}

	public byte getParticipantPaysDeposit() {
		return this.participantPaysDeposit;
	}

	public void setParticipantPaysDeposit(byte participantPaysDeposit) {
		this.participantPaysDeposit = participantPaysDeposit;
	}

	public Date getPartnerSeasonAppDeadlineDate() {
		return this.partnerSeasonAppDeadlineDate;
	}

	public void setPartnerSeasonAppDeadlineDate(Date partnerSeasonAppDeadlineDate) {
		this.partnerSeasonAppDeadlineDate = partnerSeasonAppDeadlineDate;
	}

	public Date getPartnerSeasonEndDate() {
		return this.partnerSeasonEndDate;
	}

	public void setPartnerSeasonEndDate(Date partnerSeasonEndDate) {
		this.partnerSeasonEndDate = partnerSeasonEndDate;
	}

	public Date getPartnerSeasonExtAppDeadlineDate() {
		return this.partnerSeasonExtAppDeadlineDate;
	}

	public void setPartnerSeasonExtAppDeadlineDate(Date partnerSeasonExtAppDeadlineDate) {
		this.partnerSeasonExtAppDeadlineDate = partnerSeasonExtAppDeadlineDate;
	}

	public Date getPartnerSeasonExtSecSemDeadlineDate() {
		return this.partnerSeasonExtSecSemDeadlineDate;
	}

	public void setPartnerSeasonExtSecSemDeadlineDate(Date partnerSeasonExtSecSemDeadlineDate) {
		this.partnerSeasonExtSecSemDeadlineDate = partnerSeasonExtSecSemDeadlineDate;
	}

	public Date getPartnerSeasonSecSemDeadlineDate() {
		return this.partnerSeasonSecSemDeadlineDate;
	}

	public void setPartnerSeasonSecSemDeadlineDate(Date partnerSeasonSecSemDeadlineDate) {
		this.partnerSeasonSecSemDeadlineDate = partnerSeasonSecSemDeadlineDate;
	}

	public Date getPartnerSeasonStartDate() {
		return this.partnerSeasonStartDate;
	}

	public void setPartnerSeasonStartDate(Date partnerSeasonStartDate) {
		this.partnerSeasonStartDate = partnerSeasonStartDate;
	}

	public Integer getPartnerTaxCompanyId() {
		return this.partnerTaxCompanyId;
	}

	public void setPartnerTaxCompanyId(Integer partnerTaxCompanyId) {
		this.partnerTaxCompanyId = partnerTaxCompanyId;
	}

	public byte getQuestionaireRequired() {
		return this.questionaireRequired;
	}

	public void setQuestionaireRequired(byte questionaireRequired) {
		this.questionaireRequired = questionaireRequired;
	}

	public Date getQuestionnaireSubmittedOn() {
		return this.questionnaireSubmittedOn;
	}

	public void setQuestionnaireSubmittedOn(Date questionnaireSubmittedOn) {
		this.questionnaireSubmittedOn = questionnaireSubmittedOn;
	}

	public Integer getSeasonId() {
		return this.seasonId;
	}

	public void setSeasonId(Integer seasonId) {
		this.seasonId = seasonId;
	}

	public Date getTimelyReturnReportReceivedDate() {
		return this.timelyReturnReportReceivedDate;
	}

	public void setTimelyReturnReportReceivedDate(Date timelyReturnReportReceivedDate) {
		this.timelyReturnReportReceivedDate = timelyReturnReportReceivedDate;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public PartnerStatus getPartnerStatus() {
		return this.partnerStatus;
	}

	public void setPartnerStatus(PartnerStatus partnerStatus) {
		this.partnerStatus = partnerStatus;
	}

	public List<PartnerSeasonAllocation> getPartnerSeasonAllocations() {
		return this.partnerSeasonAllocations;
	}

	public void setPartnerSeasonAllocations(List<PartnerSeasonAllocation> partnerSeasonAllocations) {
		this.partnerSeasonAllocations = partnerSeasonAllocations;
	}

	public PartnerSeasonAllocation addPartnerSeasonAllocation(PartnerSeasonAllocation partnerSeasonAllocation) {
		getPartnerSeasonAllocations().add(partnerSeasonAllocation);
		partnerSeasonAllocation.setPartnerSeason(this);

		return partnerSeasonAllocation;
	}

	public PartnerSeasonAllocation removePartnerSeasonAllocation(PartnerSeasonAllocation partnerSeasonAllocation) {
		getPartnerSeasonAllocations().remove(partnerSeasonAllocation);
		partnerSeasonAllocation.setPartnerSeason(null);

		return partnerSeasonAllocation;
	}

	public List<PartnerSeasonContract> getPartnerSeasonContracts() {
		return this.partnerSeasonContracts;
	}

	public void setPartnerSeasonContracts(List<PartnerSeasonContract> partnerSeasonContracts) {
		this.partnerSeasonContracts = partnerSeasonContracts;
	}

	public PartnerSeasonContract addPartnerSeasonContract(PartnerSeasonContract partnerSeasonContract) {
		getPartnerSeasonContracts().add(partnerSeasonContract);
		partnerSeasonContract.setPartnerSeason(this);

		return partnerSeasonContract;
	}

	public PartnerSeasonContract removePartnerSeasonContract(PartnerSeasonContract partnerSeasonContract) {
		getPartnerSeasonContracts().remove(partnerSeasonContract);
		partnerSeasonContract.setPartnerSeason(null);

		return partnerSeasonContract;
	}

}