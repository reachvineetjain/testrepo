package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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
	@Column(unique=true, nullable=false)
	private int seasonF1DetailsId;

	@Column(nullable=false)
	private byte activeFullYearJanProgram;

	@Column(nullable=false)
	private byte allowFieldStafftoStartRenewelProcess;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date applicationDeadlineForFirstSem;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date applicationDeadlineForSecSem;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date augFullYearAppDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date augFullYearEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date augFullYearStartDate;

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

	@Column(nullable=false)
	private int greenHeartMargin;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date hfInquiryDate;

	@Column(nullable=false)
	private int hfReferences;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date janFullYearAppDeadlineDate;

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
	private byte showJanFullYearToHF;

	@Column(nullable=false)
	private byte showSeasonToCurrentHF;

	@Column(nullable=false)
	private byte showSecSemToNewHF;

	@Column(nullable=false)
	private byte showSpecialRequstStudent;

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

	public SeasonF1Detail() {
	}

	public int getSeasonF1DetailsId() {
		return this.seasonF1DetailsId;
	}

	public void setSeasonF1DetailsId(int seasonF1DetailsId) {
		this.seasonF1DetailsId = seasonF1DetailsId;
	}

	public byte getActiveFullYearJanProgram() {
		return this.activeFullYearJanProgram;
	}

	public void setActiveFullYearJanProgram(byte activeFullYearJanProgram) {
		this.activeFullYearJanProgram = activeFullYearJanProgram;
	}

	public byte getAllowFieldStafftoStartRenewelProcess() {
		return this.allowFieldStafftoStartRenewelProcess;
	}

	public void setAllowFieldStafftoStartRenewelProcess(byte allowFieldStafftoStartRenewelProcess) {
		this.allowFieldStafftoStartRenewelProcess = allowFieldStafftoStartRenewelProcess;
	}

	public Date getApplicationDeadlineForFirstSem() {
		return this.applicationDeadlineForFirstSem;
	}

	public void setApplicationDeadlineForFirstSem(Date applicationDeadlineForFirstSem) {
		this.applicationDeadlineForFirstSem = applicationDeadlineForFirstSem;
	}

	public Date getApplicationDeadlineForSecSem() {
		return this.applicationDeadlineForSecSem;
	}

	public void setApplicationDeadlineForSecSem(Date applicationDeadlineForSecSem) {
		this.applicationDeadlineForSecSem = applicationDeadlineForSecSem;
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

	public int getHfReferences() {
		return this.hfReferences;
	}

	public void setHfReferences(int hfReferences) {
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

	public byte getShowJanFullYearToHF() {
		return this.showJanFullYearToHF;
	}

	public void setShowJanFullYearToHF(byte showJanFullYearToHF) {
		this.showJanFullYearToHF = showJanFullYearToHF;
	}

	public byte getShowSeasonToCurrentHF() {
		return this.showSeasonToCurrentHF;
	}

	public void setShowSeasonToCurrentHF(byte showSeasonToCurrentHF) {
		this.showSeasonToCurrentHF = showSeasonToCurrentHF;
	}

	public byte getShowSecSemToNewHF() {
		return this.showSecSemToNewHF;
	}

	public void setShowSecSemToNewHF(byte showSecSemToNewHF) {
		this.showSecSemToNewHF = showSecSemToNewHF;
	}

	public byte getShowSpecialRequstStudent() {
		return this.showSpecialRequstStudent;
	}

	public void setShowSpecialRequstStudent(byte showSpecialRequstStudent) {
		this.showSpecialRequstStudent = showSpecialRequstStudent;
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