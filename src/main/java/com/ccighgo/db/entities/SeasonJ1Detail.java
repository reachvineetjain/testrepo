package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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
	@Column(unique=true, nullable=false)
	private int seasonJ1DetailsId;

	@Column(nullable=false)
	private byte activeFullYearJanProgram;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date applicationDeadlineDateForFirstSem;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date applicationDeadlineDateForSecSem;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date augFullYearAppDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date augFullYearEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date augFullYearStartDate;

	@Column(nullable=false)
	private int fieldStaffHoldLength;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date firstSemEarliestBirthDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date firstSemEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date firstSemLatestBirthDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date firstSemStartDate;

	@Column(nullable=false)
	private int fsAgreementId;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date hfInquiryDate;

	@Column(nullable=false)
	private int hfReferences;

	@Column(nullable=false)
	private int hoursBeforeHoldExpirationWarning;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date janApplicationDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date janFullYearEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date janFullYearStartDate;

	@Column(nullable=false)
	private int lcPaymentScheduleId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date secondSemEarliestBirthDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date secondSemEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date secondSemLatestBirthDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date secondSemStartDate;

	@Column(nullable=false)
	private byte showAugFullYearToNewHF;

	@Column(nullable=false)
	private byte showFirstSemToNewHF;

	@Column(nullable=false)
	private byte showGuaranteed;

	@Column(nullable=false)
	private byte showJanFullYearToNewHF;

	@Column(nullable=false)
	private byte showSeasonToCurrentHF;

	@Column(nullable=false)
	private byte showSecondSemToNewHF;

	@Column(nullable=false)
	private byte showSpecialRequstStudent;

	@Column(nullable=false)
	private byte showUnguaranteed;

	@Column(nullable=false)
	private byte welcomeFamily;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId", nullable=false)
	private Season season;

	//bi-directional many-to-one association to SeasonStatus
	@ManyToOne
	@JoinColumn(name="programStatusId", nullable=false)
	private SeasonStatus seasonStatus;

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

	public Date getApplicationDeadlineDateForFirstSem() {
		return this.applicationDeadlineDateForFirstSem;
	}

	public void setApplicationDeadlineDateForFirstSem(Date applicationDeadlineDateForFirstSem) {
		this.applicationDeadlineDateForFirstSem = applicationDeadlineDateForFirstSem;
	}

	public Date getApplicationDeadlineDateForSecSem() {
		return this.applicationDeadlineDateForSecSem;
	}

	public void setApplicationDeadlineDateForSecSem(Date applicationDeadlineDateForSecSem) {
		this.applicationDeadlineDateForSecSem = applicationDeadlineDateForSecSem;
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

	public int getFieldStaffHoldLength() {
		return this.fieldStaffHoldLength;
	}

	public void setFieldStaffHoldLength(int fieldStaffHoldLength) {
		this.fieldStaffHoldLength = fieldStaffHoldLength;
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

	public int getFsAgreementId() {
		return this.fsAgreementId;
	}

	public void setFsAgreementId(int fsAgreementId) {
		this.fsAgreementId = fsAgreementId;
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

	public Date getJanApplicationDeadlineDate() {
		return this.janApplicationDeadlineDate;
	}

	public void setJanApplicationDeadlineDate(Date janApplicationDeadlineDate) {
		this.janApplicationDeadlineDate = janApplicationDeadlineDate;
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

	public int getLcPaymentScheduleId() {
		return this.lcPaymentScheduleId;
	}

	public void setLcPaymentScheduleId(int lcPaymentScheduleId) {
		this.lcPaymentScheduleId = lcPaymentScheduleId;
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

	public byte getShowSpecialRequstStudent() {
		return this.showSpecialRequstStudent;
	}

	public void setShowSpecialRequstStudent(byte showSpecialRequstStudent) {
		this.showSpecialRequstStudent = showSpecialRequstStudent;
	}

	public byte getShowUnguaranteed() {
		return this.showUnguaranteed;
	}

	public void setShowUnguaranteed(byte showUnguaranteed) {
		this.showUnguaranteed = showUnguaranteed;
	}

	public byte getWelcomeFamily() {
		return this.welcomeFamily;
	}

	public void setWelcomeFamily(byte welcomeFamily) {
		this.welcomeFamily = welcomeFamily;
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