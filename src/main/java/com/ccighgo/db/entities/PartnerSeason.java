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

   private Byte active;

   @Temporal(TemporalType.TIMESTAMP)
   private Date appDeadlineFollowupDate;

   @Temporal(TemporalType.TIMESTAMP)
   private Date appSecSemDeadlineFollowupDate;

   private Byte canAccessJobBoard;

   private Byte canCreateSubPartner;

   private Integer contractScheduleId;

   @Column(nullable=false)
   private Integer createdBy;

   @Column(nullable=false)
   private Timestamp createdOn;

   @Temporal(TemporalType.TIMESTAMP)
   private Date deadlineRequestedOn;

   @Temporal(TemporalType.TIMESTAMP)
   private Date deadlineRequestReviewedOn;

   private Byte disableAddParticipant;

   @Column(length=2000)
   private String exceptionComments;

   @Column(length=200)
   private String insuranceCarrierName;

   @Column(length=50)
   private String insurancePhoneNumber;

   @Column(length=100)
   private String insurancePolicyNumber;

   private Byte insuranceProvidedByCCI;

   @Column(nullable=false)
   private Byte isSignedContract;

   @Column(nullable=false)
   private Integer modifiedBy;

   @Column(nullable=false)
   private Timestamp modifiedOn;

   @Temporal(TemporalType.TIMESTAMP)
   private Date originalsReceivedDate;

   private Byte participantPaysDeposit;

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

   private Byte questionaireRequired;

   @Temporal(TemporalType.TIMESTAMP)
   private Date questionnaireSubmittedOn;

   private Byte sevisFeesPaidByCCI;

   @Temporal(TemporalType.TIMESTAMP)
   private Date timelyReturnReportReceivedDate;

   //bi-directional many-to-one association to CCIStaffUser
   @ManyToOne
   @JoinColumn(name="cciStaffUserId")
   private CCIStaffUser ccistaffUser1;

   //bi-directional many-to-one association to CCIStaffUser
   @ManyToOne
   @JoinColumn(name="deadlineRequestReviewedBy")
   private CCIStaffUser ccistaffUser2;

   //bi-directional many-to-one association to DepartmentProgram
   @ManyToOne
   @JoinColumn(name="departmentProgramId")
   private DepartmentProgram departmentProgram;

   //bi-directional many-to-one association to Login
   @ManyToOne
   @JoinColumn(name="deadlineRequestedBy")
   private Login login;

   //bi-directional many-to-one association to Partner
   @ManyToOne
   @JoinColumn(name="partnerGoId")
   private Partner partner;

   //bi-directional many-to-one association to PartnerStatus
   @ManyToOne
   @JoinColumn(name="partnerSeasonStatusId")
   private PartnerStatus partnerStatus1;

   //bi-directional many-to-one association to PartnerStatus
   @ManyToOne
   @JoinColumn(name="partnerDeadlineRequestStatusId")
   private PartnerStatus partnerStatus2;

   //bi-directional many-to-one association to PartnerStatus
   @ManyToOne
   @JoinColumn(name="partnerSecSemDeadlineRequestStatusId")
   private PartnerStatus partnerStatus3;

   //bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name="seasonId")
   private Season season;

   //bi-directional many-to-one association to PartnerSeasonAllocation
   @OneToMany(mappedBy="partnerSeason")
   private List<PartnerSeasonAllocation> partnerSeasonAllocations;

   //bi-directional many-to-one association to PartnerSeasonContract
   @OneToMany(mappedBy="partnerSeason")
   private List<PartnerSeasonContract> partnerSeasonContracts;

   //bi-directional many-to-one association to PartnerSeasonDocument
   @OneToMany(mappedBy="partnerSeason")
   private List<PartnerSeasonDocument> partnerSeasonDocuments;

   //bi-directional many-to-one association to PartnerSeasonNoteTopic
   @OneToMany(mappedBy="partnerSeason")
   private List<PartnerSeasonNoteTopic> partnerSeasonNoteTopics;

   //bi-directional many-to-one association to PartnerSeasonNote
   @OneToMany(mappedBy="partnerSeason")
   private List<PartnerSeasonNote> partnerSeasonNotes;

   public PartnerSeason() {
   }

   public Integer getPartnerSeasonId() {
      return this.partnerSeasonId;
   }

   public void setPartnerSeasonId(Integer partnerSeasonId) {
      this.partnerSeasonId = partnerSeasonId;
   }

   public Byte getActive() {
      return this.active;
   }

   public void setActive(Byte active) {
      this.active = active;
   }

   public Date getAppDeadlineFollowupDate() {
      return this.appDeadlineFollowupDate;
   }

   public void setAppDeadlineFollowupDate(Date appDeadlineFollowupDate) {
      this.appDeadlineFollowupDate = appDeadlineFollowupDate;
   }

   public Date getAppSecSemDeadlineFollowupDate() {
      return this.appSecSemDeadlineFollowupDate;
   }

   public void setAppSecSemDeadlineFollowupDate(Date appSecSemDeadlineFollowupDate) {
      this.appSecSemDeadlineFollowupDate = appSecSemDeadlineFollowupDate;
   }

   public Byte getCanAccessJobBoard() {
      return this.canAccessJobBoard;
   }

   public void setCanAccessJobBoard(Byte canAccessJobBoard) {
      this.canAccessJobBoard = canAccessJobBoard;
   }

   public Byte getCanCreateSubPartner() {
      return this.canCreateSubPartner;
   }

   public void setCanCreateSubPartner(Byte canCreateSubPartner) {
      this.canCreateSubPartner = canCreateSubPartner;
   }

   public Integer getContractScheduleId() {
      return this.contractScheduleId;
   }

   public void setContractScheduleId(Integer contractScheduleId) {
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

   public Date getDeadlineRequestedOn() {
      return this.deadlineRequestedOn;
   }

   public void setDeadlineRequestedOn(Date deadlineRequestedOn) {
      this.deadlineRequestedOn = deadlineRequestedOn;
   }

   public Date getDeadlineRequestReviewedOn() {
      return this.deadlineRequestReviewedOn;
   }

   public void setDeadlineRequestReviewedOn(Date deadlineRequestReviewedOn) {
      this.deadlineRequestReviewedOn = deadlineRequestReviewedOn;
   }

   public Byte getDisableAddParticipant() {
      return this.disableAddParticipant;
   }

   public void setDisableAddParticipant(Byte disableAddParticipant) {
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

   public Byte getInsuranceProvidedByCCI() {
      return this.insuranceProvidedByCCI;
   }

   public void setInsuranceProvidedByCCI(Byte insuranceProvidedByCCI) {
      this.insuranceProvidedByCCI = insuranceProvidedByCCI;
   }

   public Byte getIsSignedContract() {
      return this.isSignedContract;
   }

   public void setIsSignedContract(Byte isSignedContract) {
      this.isSignedContract = isSignedContract;
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

   public Byte getParticipantPaysDeposit() {
      return this.participantPaysDeposit;
   }

   public void setParticipantPaysDeposit(Byte participantPaysDeposit) {
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

   public Byte getQuestionaireRequired() {
      return this.questionaireRequired;
   }

   public void setQuestionaireRequired(Byte questionaireRequired) {
      this.questionaireRequired = questionaireRequired;
   }

   public Date getQuestionnaireSubmittedOn() {
      return this.questionnaireSubmittedOn;
   }

   public void setQuestionnaireSubmittedOn(Date questionnaireSubmittedOn) {
      this.questionnaireSubmittedOn = questionnaireSubmittedOn;
   }

   public Byte getSevisFeesPaidByCCI() {
      return this.sevisFeesPaidByCCI;
   }

   public void setSevisFeesPaidByCCI(Byte sevisFeesPaidByCCI) {
      this.sevisFeesPaidByCCI = sevisFeesPaidByCCI;
   }

   public Date getTimelyReturnReportReceivedDate() {
      return this.timelyReturnReportReceivedDate;
   }

   public void setTimelyReturnReportReceivedDate(Date timelyReturnReportReceivedDate) {
      this.timelyReturnReportReceivedDate = timelyReturnReportReceivedDate;
   }

   public CCIStaffUser getCcistaffUser1() {
      return this.ccistaffUser1;
   }

   public void setCcistaffUser1(CCIStaffUser ccistaffUser1) {
      this.ccistaffUser1 = ccistaffUser1;
   }

   public CCIStaffUser getCcistaffUser2() {
      return this.ccistaffUser2;
   }

   public void setCcistaffUser2(CCIStaffUser ccistaffUser2) {
      this.ccistaffUser2 = ccistaffUser2;
   }

   public DepartmentProgram getDepartmentProgram() {
      return this.departmentProgram;
   }

   public void setDepartmentProgram(DepartmentProgram departmentProgram) {
      this.departmentProgram = departmentProgram;
   }

   public Login getLogin() {
      return this.login;
   }

   public void setLogin(Login login) {
      this.login = login;
   }

   public Partner getPartner() {
      return this.partner;
   }

   public void setPartner(Partner partner) {
      this.partner = partner;
   }

   public PartnerStatus getPartnerStatus1() {
      return this.partnerStatus1;
   }

   public void setPartnerStatus1(PartnerStatus partnerStatus1) {
      this.partnerStatus1 = partnerStatus1;
   }

   public PartnerStatus getPartnerStatus2() {
      return this.partnerStatus2;
   }

   public void setPartnerStatus2(PartnerStatus partnerStatus2) {
      this.partnerStatus2 = partnerStatus2;
   }

   public PartnerStatus getPartnerStatus3() {
      return this.partnerStatus3;
   }

   public void setPartnerStatus3(PartnerStatus partnerStatus3) {
      this.partnerStatus3 = partnerStatus3;
   }

   public Season getSeason() {
      return this.season;
   }

   public void setSeason(Season season) {
      this.season = season;
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

   public List<PartnerSeasonDocument> getPartnerSeasonDocuments() {
      return this.partnerSeasonDocuments;
   }

   public void setPartnerSeasonDocuments(List<PartnerSeasonDocument> partnerSeasonDocuments) {
      this.partnerSeasonDocuments = partnerSeasonDocuments;
   }

   public PartnerSeasonDocument addPartnerSeasonDocument(PartnerSeasonDocument partnerSeasonDocument) {
      getPartnerSeasonDocuments().add(partnerSeasonDocument);
      partnerSeasonDocument.setPartnerSeason(this);

      return partnerSeasonDocument;
   }

   public PartnerSeasonDocument removePartnerSeasonDocument(PartnerSeasonDocument partnerSeasonDocument) {
      getPartnerSeasonDocuments().remove(partnerSeasonDocument);
      partnerSeasonDocument.setPartnerSeason(null);

      return partnerSeasonDocument;
   }

   public List<PartnerSeasonNoteTopic> getPartnerSeasonNoteTopics() {
      return this.partnerSeasonNoteTopics;
   }

   public void setPartnerSeasonNoteTopics(List<PartnerSeasonNoteTopic> partnerSeasonNoteTopics) {
      this.partnerSeasonNoteTopics = partnerSeasonNoteTopics;
   }

   public PartnerSeasonNoteTopic addPartnerSeasonNoteTopic(PartnerSeasonNoteTopic partnerSeasonNoteTopic) {
      getPartnerSeasonNoteTopics().add(partnerSeasonNoteTopic);
      partnerSeasonNoteTopic.setPartnerSeason(this);

      return partnerSeasonNoteTopic;
   }

   public PartnerSeasonNoteTopic removePartnerSeasonNoteTopic(PartnerSeasonNoteTopic partnerSeasonNoteTopic) {
      getPartnerSeasonNoteTopics().remove(partnerSeasonNoteTopic);
      partnerSeasonNoteTopic.setPartnerSeason(null);

      return partnerSeasonNoteTopic;
   }

   public List<PartnerSeasonNote> getPartnerSeasonNotes() {
      return this.partnerSeasonNotes;
   }

   public void setPartnerSeasonNotes(List<PartnerSeasonNote> partnerSeasonNotes) {
      this.partnerSeasonNotes = partnerSeasonNotes;
   }

   public PartnerSeasonNote addPartnerSeasonNote(PartnerSeasonNote partnerSeasonNote) {
      getPartnerSeasonNotes().add(partnerSeasonNote);
      partnerSeasonNote.setPartnerSeason(this);

      return partnerSeasonNote;
   }

   public PartnerSeasonNote removePartnerSeasonNote(PartnerSeasonNote partnerSeasonNote) {
      getPartnerSeasonNotes().remove(partnerSeasonNote);
      partnerSeasonNote.setPartnerSeason(null);

      return partnerSeasonNote;
   }

}