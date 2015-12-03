package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the SeasonF1Details database table.
 * 
 */
@Entity
@Table(name="SeasonF1Details")
@NamedQuery(name="SeasonF1Detail.findAll", query="SELECT s FROM SeasonF1Detail s")
public class SeasonF1Detail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer seasonF1DetailsId;

	private Byte activeFullYearJanProgram;

	private Byte allowFieldStaffToStartRenewalProcess;

	@Temporal(TemporalType.TIMESTAMP)
	private Date augFullYearAppDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date augFullYearEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date augFullYearStartDate;

	@Column(nullable=false)
	private Integer createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date firstSemAppDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date firstSemEarliestBirthDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date firstSemEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date firstSemLatestBirthDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date firstSemStartDate;

	private int greenHeartMargin;

	@Temporal(TemporalType.DATE)
	private Date hfInquiryDate;

	private Integer hfReferences;

	@Temporal(TemporalType.TIMESTAMP)
	private Date janFullYearAppDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date janFullYearEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date janFullYearStartDate;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(length=45)
	private String programName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date secondSemAppDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date secondSemEarliestBirthDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date secondSemEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date secondSemLatestBirthDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date secondSemStartDate;

	private Byte showAugFullYearToNewHF;

	private Byte showFirstSemToNewHF;

	private Byte showJanFullYearToNewHF;

	private Byte showSeasonToCurrentHF;

	private Byte showSecSemToNewHF;

	private Byte showSpecialRequestStudent;

	private Byte showWelcomeFamily;

	//bi-directional many-to-one association to FieldStaffAgreement
	@ManyToOne
	@JoinColumn(name="fsAgreementId")
	private FieldStaffAgreement fieldStaffAgreement;

	//bi-directional many-to-one association to PaymentSchedule
	@ManyToOne
	@JoinColumn(name="lcPaymentScheduleId")
	private PaymentSchedule paymentSchedule;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId", nullable=false)
	private Season season;

	//bi-directional many-to-one association to SeasonStatus
	@ManyToOne
	@JoinColumn(name="programStatusId")
	private SeasonStatus seasonStatus;

	public SeasonF1Detail() {
	}

	public Integer getSeasonF1DetailsId() {
		return this.seasonF1DetailsId;
	}

	public void setSeasonF1DetailsId(Integer seasonF1DetailsId) {
		this.seasonF1DetailsId = seasonF1DetailsId;
	}

	public Byte getActiveFullYearJanProgram() {
		return this.activeFullYearJanProgram;
	}

	public void setActiveFullYearJanProgram(Byte activeFullYearJanProgram) {
		this.activeFullYearJanProgram = activeFullYearJanProgram;
	}

	public Byte getAllowFieldStaffToStartRenewalProcess() {
		return this.allowFieldStaffToStartRenewalProcess;
	}

	public void setAllowFieldStaffToStartRenewalProcess(Byte allowFieldStaffToStartRenewalProcess) {
		this.allowFieldStaffToStartRenewalProcess = allowFieldStaffToStartRenewalProcess;
	}

	public Date getAugFullYearAppDeadlineDate() {
		return this.augFullYearAppDeadlineDate;
	}

	public void setAugFullYearAppDeadlineDate(Date augFullYearAppDeadlineDate) {
		this.augFullYearAppDeadlineDate = augFullYearAppDeadlineDate;
	}

	public Date getAugFullYearEndDate() {
		return this.augFullYearEndDate;
	}

	public void setAugFullYearEndDate(Date augFullYearEndDate) {
		this.augFullYearEndDate = augFullYearEndDate;
	}

	public Date getAugFullYearStartDate() {
		return this.augFullYearStartDate;
	}

	public void setAugFullYearStartDate(Date augFullYearStartDate) {
		this.augFullYearStartDate = augFullYearStartDate;
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

	public Date getFirstSemAppDeadlineDate() {
		return this.firstSemAppDeadlineDate;
	}

	public void setFirstSemAppDeadlineDate(Date firstSemAppDeadlineDate) {
		this.firstSemAppDeadlineDate = firstSemAppDeadlineDate;
	}

	public Date getFirstSemEarliestBirthDate() {
		return this.firstSemEarliestBirthDate;
	}

	public void setFirstSemEarliestBirthDate(Date firstSemEarliestBirthDate) {
		this.firstSemEarliestBirthDate = firstSemEarliestBirthDate;
	}

	public Date getFirstSemEndDate() {
		return this.firstSemEndDate;
	}

	public void setFirstSemEndDate(Date firstSemEndDate) {
		this.firstSemEndDate = firstSemEndDate;
	}

	public Date getFirstSemLatestBirthDate() {
		return this.firstSemLatestBirthDate;
	}

	public void setFirstSemLatestBirthDate(Date firstSemLatestBirthDate) {
		this.firstSemLatestBirthDate = firstSemLatestBirthDate;
	}

	public Date getFirstSemStartDate() {
		return this.firstSemStartDate;
	}

	public void setFirstSemStartDate(Date firstSemStartDate) {
		this.firstSemStartDate = firstSemStartDate;
	}

	public int getGreenHeartMargin() {
		return this.greenHeartMargin;
	}

	public void setGreenHeartMargin(int greenHeartMargin) {
		this.greenHeartMargin = greenHeartMargin;
	}

	public Date getHfInquiryDate() {
		return this.hfInquiryDate;
	}

	public void setHfInquiryDate(Date hfInquiryDate) {
		this.hfInquiryDate = hfInquiryDate;
	}

	public Integer getHfReferences() {
		return this.hfReferences;
	}

	public void setHfReferences(Integer hfReferences) {
		this.hfReferences = hfReferences;
	}

	public Date getJanFullYearAppDeadlineDate() {
		return this.janFullYearAppDeadlineDate;
	}

	public void setJanFullYearAppDeadlineDate(Date janFullYearAppDeadlineDate) {
		this.janFullYearAppDeadlineDate = janFullYearAppDeadlineDate;
	}

	public Date getJanFullYearEndDate() {
		return this.janFullYearEndDate;
	}

	public void setJanFullYearEndDate(Date janFullYearEndDate) {
		this.janFullYearEndDate = janFullYearEndDate;
	}

	public Date getJanFullYearStartDate() {
		return this.janFullYearStartDate;
	}

	public void setJanFullYearStartDate(Date janFullYearStartDate) {
		this.janFullYearStartDate = janFullYearStartDate;
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

	public String getProgramName() {
		return this.programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public Date getSecondSemAppDeadlineDate() {
		return this.secondSemAppDeadlineDate;
	}

	public void setSecondSemAppDeadlineDate(Date secondSemAppDeadlineDate) {
		this.secondSemAppDeadlineDate = secondSemAppDeadlineDate;
	}

	public Date getSecondSemEarliestBirthDate() {
		return this.secondSemEarliestBirthDate;
	}

	public void setSecondSemEarliestBirthDate(Date secondSemEarliestBirthDate) {
		this.secondSemEarliestBirthDate = secondSemEarliestBirthDate;
	}

	public Date getSecondSemEndDate() {
		return this.secondSemEndDate;
	}

	public void setSecondSemEndDate(Date secondSemEndDate) {
		this.secondSemEndDate = secondSemEndDate;
	}

	public Date getSecondSemLatestBirthDate() {
		return this.secondSemLatestBirthDate;
	}

	public void setSecondSemLatestBirthDate(Date secondSemLatestBirthDate) {
		this.secondSemLatestBirthDate = secondSemLatestBirthDate;
	}

	public Date getSecondSemStartDate() {
		return this.secondSemStartDate;
	}

	public void setSecondSemStartDate(Date secondSemStartDate) {
		this.secondSemStartDate = secondSemStartDate;
	}

	public Byte getShowAugFullYearToNewHF() {
		return this.showAugFullYearToNewHF;
	}

	public void setShowAugFullYearToNewHF(Byte showAugFullYearToNewHF) {
		this.showAugFullYearToNewHF = showAugFullYearToNewHF;
	}

	public Byte getShowFirstSemToNewHF() {
		return this.showFirstSemToNewHF;
	}

	public void setShowFirstSemToNewHF(Byte showFirstSemToNewHF) {
		this.showFirstSemToNewHF = showFirstSemToNewHF;
	}

	public Byte getShowJanFullYearToNewHF() {
		return this.showJanFullYearToNewHF;
	}

	public void setShowJanFullYearToNewHF(Byte showJanFullYearToNewHF) {
		this.showJanFullYearToNewHF = showJanFullYearToNewHF;
	}

	public Byte getShowSeasonToCurrentHF() {
		return this.showSeasonToCurrentHF;
	}

	public void setShowSeasonToCurrentHF(Byte showSeasonToCurrentHF) {
		this.showSeasonToCurrentHF = showSeasonToCurrentHF;
	}

	public Byte getShowSecSemToNewHF() {
		return this.showSecSemToNewHF;
	}

	public void setShowSecSemToNewHF(Byte showSecSemToNewHF) {
		this.showSecSemToNewHF = showSecSemToNewHF;
	}

	public Byte getShowSpecialRequestStudent() {
		return this.showSpecialRequestStudent;
	}

	public void setShowSpecialRequestStudent(Byte showSpecialRequestStudent) {
		this.showSpecialRequestStudent = showSpecialRequestStudent;
	}

	public Byte getShowWelcomeFamily() {
		return this.showWelcomeFamily;
	}

	public void setShowWelcomeFamily(Byte showWelcomeFamily) {
		this.showWelcomeFamily = showWelcomeFamily;
	}

	public FieldStaffAgreement getFieldStaffAgreement() {
		return this.fieldStaffAgreement;
	}

	public void setFieldStaffAgreement(FieldStaffAgreement fieldStaffAgreement) {
		this.fieldStaffAgreement = fieldStaffAgreement;
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

	public SeasonStatus getSeasonStatus() {
		return this.seasonStatus;
	}

	public void setSeasonStatus(SeasonStatus seasonStatus) {
		this.seasonStatus = seasonStatus;
	}

}