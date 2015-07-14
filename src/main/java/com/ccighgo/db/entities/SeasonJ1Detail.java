package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the SeasonJ1Details database table.
 * 
 */
@Entity
@Table(name="SeasonJ1Details")
@NamedQuery(name="SeasonJ1Detail.findAll", query="SELECT s FROM SeasonJ1Detail s")
public class SeasonJ1Detail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int seasonJ1DetailsId;

	private byte activeFullYearJanProgram;

	@Temporal(TemporalType.TIMESTAMP)
	private Date augFullYearAppDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date augFullYearEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date augFullYearStartDate;

	@Column(nullable=false)
	private int createdBy;

	private Timestamp createdOn;

	private int fieldStaffHoldLength;

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

	@Temporal(TemporalType.DATE)
	private Date hfInquiryDate;

	private int hfReferences;

	private int hoursBeforeHoldExpirationWarning;

	@Temporal(TemporalType.TIMESTAMP)
	private Date janFullYearAppDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date janFullYearEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date janFullYearStartDate;

	@Column(nullable=false)
	private int modifiedBy;

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

	private byte showAugFullYearToNewHF;

	private byte showFirstSemToNewHF;

	private byte showGuaranteed;

	private byte showJanFullYearToNewHF;

	private byte showSeasonToCurrentHF;

	private byte showSecondSemToNewHF;

	private byte showSpecialRequestStudent;

	private byte showUnguaranteed;

	private byte showWelcomeFamily;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId", nullable=false)
	private Season season;

	//bi-directional many-to-one association to SeasonStatus
	@ManyToOne
	@JoinColumn(name="programStatusId")
	private SeasonStatus seasonStatus;

	//bi-directional many-to-one association to FieldStaffAgreement
	@ManyToOne
	@JoinColumn(name="fsAgreementId")
	private FieldStaffAgreement fieldStaffAgreement;

	//bi-directional many-to-one association to PaymentSchedule
	@ManyToOne
	@JoinColumn(name="lcPaymentScheduleId")
	private PaymentSchedule paymentSchedule;

	public SeasonJ1Detail() {
	}

	public int getSeasonJ1DetailsId() {
		return this.seasonJ1DetailsId;
	}

	public void setSeasonJ1DetailsId(int seasonJ1DetailsId) {
		this.seasonJ1DetailsId = seasonJ1DetailsId;
	}

	public byte getActiveFullYearJanProgram() {
		return this.activeFullYearJanProgram;
	}

	public void setActiveFullYearJanProgram(byte activeFullYearJanProgram) {
		this.activeFullYearJanProgram = activeFullYearJanProgram;
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

	public int getFieldStaffHoldLength() {
		return this.fieldStaffHoldLength;
	}

	public void setFieldStaffHoldLength(int fieldStaffHoldLength) {
		this.fieldStaffHoldLength = fieldStaffHoldLength;
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

	public Date getHfInquiryDate() {
		return this.hfInquiryDate;
	}

	public void setHfInquiryDate(Date hfInquiryDate) {
		this.hfInquiryDate = hfInquiryDate;
	}

	public int getHfReferences() {
		return this.hfReferences;
	}

	public void setHfReferences(int hfReferences) {
		this.hfReferences = hfReferences;
	}

	public int getHoursBeforeHoldExpirationWarning() {
		return this.hoursBeforeHoldExpirationWarning;
	}

	public void setHoursBeforeHoldExpirationWarning(int hoursBeforeHoldExpirationWarning) {
		this.hoursBeforeHoldExpirationWarning = hoursBeforeHoldExpirationWarning;
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

	public byte getShowAugFullYearToNewHF() {
		return this.showAugFullYearToNewHF;
	}

	public void setShowAugFullYearToNewHF(byte showAugFullYearToNewHF) {
		this.showAugFullYearToNewHF = showAugFullYearToNewHF;
	}

	public byte getShowFirstSemToNewHF() {
		return this.showFirstSemToNewHF;
	}

	public void setShowFirstSemToNewHF(byte showFirstSemToNewHF) {
		this.showFirstSemToNewHF = showFirstSemToNewHF;
	}

	public byte getShowGuaranteed() {
		return this.showGuaranteed;
	}

	public void setShowGuaranteed(byte showGuaranteed) {
		this.showGuaranteed = showGuaranteed;
	}

	public byte getShowJanFullYearToNewHF() {
		return this.showJanFullYearToNewHF;
	}

	public void setShowJanFullYearToNewHF(byte showJanFullYearToNewHF) {
		this.showJanFullYearToNewHF = showJanFullYearToNewHF;
	}

	public byte getShowSeasonToCurrentHF() {
		return this.showSeasonToCurrentHF;
	}

	public void setShowSeasonToCurrentHF(byte showSeasonToCurrentHF) {
		this.showSeasonToCurrentHF = showSeasonToCurrentHF;
	}

	public byte getShowSecondSemToNewHF() {
		return this.showSecondSemToNewHF;
	}

	public void setShowSecondSemToNewHF(byte showSecondSemToNewHF) {
		this.showSecondSemToNewHF = showSecondSemToNewHF;
	}

	public byte getShowSpecialRequestStudent() {
		return this.showSpecialRequestStudent;
	}

	public void setShowSpecialRequestStudent(byte showSpecialRequestStudent) {
		this.showSpecialRequestStudent = showSpecialRequestStudent;
	}

	public byte getShowUnguaranteed() {
		return this.showUnguaranteed;
	}

	public void setShowUnguaranteed(byte showUnguaranteed) {
		this.showUnguaranteed = showUnguaranteed;
	}

	public byte getShowWelcomeFamily() {
		return this.showWelcomeFamily;
	}

	public void setShowWelcomeFamily(byte showWelcomeFamily) {
		this.showWelcomeFamily = showWelcomeFamily;
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

}