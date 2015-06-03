package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the SeasonHSPConfiguration database table.
 * 
 */
@Entity
@Table(name="SeasonHSPConfiguration")
@NamedQuery(name="SeasonHSPConfiguration.findAll", query="SELECT s FROM SeasonHSPConfiguration s")
public class SeasonHSPConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int seasonHSPConfigurationID;

	@Column(nullable=false)
	private int fieldStaffAgreementID;

	@Column(nullable=false)
	private int fieldStaffHoldLength;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date hfInquiry;

	@Column(nullable=false)
	private int hfRequiredReferences;

	@Column(nullable=false)
	private int hoursBeforeHoldExpirationWarning;

	@Column(nullable=false)
	private int lcPaymenstSchedule;

	@Column(nullable=false)
	private Timestamp seasonEndDate;

	@Column(nullable=false)
	private Timestamp seasonStartDate;

	@Column(nullable=false)
	private byte showFirstSemToHF;

	@Column(nullable=false)
	private byte showSeasonToCurrentHF;

	@Column(nullable=false)
	private byte showSecSemToHF;

	@Column(nullable=false)
	private byte showSpecialRequestStudent;

	@Column(nullable=false)
	private byte welComeFamily;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonID", nullable=false)
	private Season season;

	public SeasonHSPConfiguration() {
	}

	public int getSeasonHSPConfigurationID() {
		return this.seasonHSPConfigurationID;
	}

	public void setSeasonHSPConfigurationID(int seasonHSPConfigurationID) {
		this.seasonHSPConfigurationID = seasonHSPConfigurationID;
	}

	public int getFieldStaffAgreementID() {
		return this.fieldStaffAgreementID;
	}

	public void setFieldStaffAgreementID(int fieldStaffAgreementID) {
		this.fieldStaffAgreementID = fieldStaffAgreementID;
	}

	public int getFieldStaffHoldLength() {
		return this.fieldStaffHoldLength;
	}

	public void setFieldStaffHoldLength(int fieldStaffHoldLength) {
		this.fieldStaffHoldLength = fieldStaffHoldLength;
	}

	public Date getHfInquiry() {
		return this.hfInquiry;
	}

	public void setHfInquiry(Date hfInquiry) {
		this.hfInquiry = hfInquiry;
	}

	public int getHfRequiredReferences() {
		return this.hfRequiredReferences;
	}

	public void setHfRequiredReferences(int hfRequiredReferences) {
		this.hfRequiredReferences = hfRequiredReferences;
	}

	public int getHoursBeforeHoldExpirationWarning() {
		return this.hoursBeforeHoldExpirationWarning;
	}

	public void setHoursBeforeHoldExpirationWarning(int hoursBeforeHoldExpirationWarning) {
		this.hoursBeforeHoldExpirationWarning = hoursBeforeHoldExpirationWarning;
	}

	public int getLcPaymenstSchedule() {
		return this.lcPaymenstSchedule;
	}

	public void setLcPaymenstSchedule(int lcPaymenstSchedule) {
		this.lcPaymenstSchedule = lcPaymenstSchedule;
	}

	public Timestamp getSeasonEndDate() {
		return this.seasonEndDate;
	}

	public void setSeasonEndDate(Timestamp seasonEndDate) {
		this.seasonEndDate = seasonEndDate;
	}

	public Timestamp getSeasonStartDate() {
		return this.seasonStartDate;
	}

	public void setSeasonStartDate(Timestamp seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
	}

	public byte getShowFirstSemToHF() {
		return this.showFirstSemToHF;
	}

	public void setShowFirstSemToHF(byte showFirstSemToHF) {
		this.showFirstSemToHF = showFirstSemToHF;
	}

	public byte getShowSeasonToCurrentHF() {
		return this.showSeasonToCurrentHF;
	}

	public void setShowSeasonToCurrentHF(byte showSeasonToCurrentHF) {
		this.showSeasonToCurrentHF = showSeasonToCurrentHF;
	}

	public byte getShowSecSemToHF() {
		return this.showSecSemToHF;
	}

	public void setShowSecSemToHF(byte showSecSemToHF) {
		this.showSecSemToHF = showSecSemToHF;
	}

	public byte getShowSpecialRequestStudent() {
		return this.showSpecialRequestStudent;
	}

	public void setShowSpecialRequestStudent(byte showSpecialRequestStudent) {
		this.showSpecialRequestStudent = showSpecialRequestStudent;
	}

	public byte getWelComeFamily() {
		return this.welComeFamily;
	}

	public void setWelComeFamily(byte welComeFamily) {
		this.welComeFamily = welComeFamily;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

}