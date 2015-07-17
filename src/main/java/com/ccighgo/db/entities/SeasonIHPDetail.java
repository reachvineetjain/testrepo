package com.ccighgo.db.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the SeasonIHPDetails database table.
 * 
 */
@Entity
@Table(name="SeasonIHPDetails")
@NamedQuery(name="SeasonIHPDetail.findAll", query="SELECT s FROM SeasonIHPDetail s")
public class SeasonIHPDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int seasonIHPDetailsId;

	private int applicationDeadLineWeeks;

	@Column(nullable=false)
	private int createdBy;

	private Timestamp createdOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	private int lcHoldTime;

	private int maxParticipants;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	private int numberOfLCToRequestHold;

	@Column(length=45)
	private String programName;

	private int splitPlacementPending;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	private byte stopAcceptingApps;

	private byte stopAcceptingAppsHighSchoolVisits;

	private byte stopAcceptingAppsHolidayHomestay;

	private byte stopAcceptingAppsLanguageBuddy;

	private byte stopAcceptingAppsStandardIHP;

	private byte stopAcceptingAppsVolunteerHomestay;

	//bi-directional many-to-one association to LookupGender
	@ManyToOne
	@JoinColumn(name="stopAcceptingAppsByGender")
	private LookupGender lookupGender;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId", nullable=false)
	private Season season;

	//bi-directional many-to-one association to SeasonStatus
	@ManyToOne
	@JoinColumn(name="programStatusId")
	private SeasonStatus seasonStatus;

	//bi-directional many-to-one association to SeasonIHPDetailsRegionApplication
	@OneToMany(mappedBy = "seasonIhpdetail", fetch = FetchType.EAGER)
   @Fetch(value = FetchMode.SUBSELECT)
	private List<SeasonIHPDetailsRegionApplication> seasonIhpdetailsRegionApplications;

	public SeasonIHPDetail() {
	}

	public int getSeasonIHPDetailsId() {
		return this.seasonIHPDetailsId;
	}

	public void setSeasonIHPDetailsId(int seasonIHPDetailsId) {
		this.seasonIHPDetailsId = seasonIHPDetailsId;
	}

	public int getApplicationDeadLineWeeks() {
		return this.applicationDeadLineWeeks;
	}

	public void setApplicationDeadLineWeeks(int applicationDeadLineWeeks) {
		this.applicationDeadLineWeeks = applicationDeadLineWeeks;
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

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getLcHoldTime() {
		return this.lcHoldTime;
	}

	public void setLcHoldTime(int lcHoldTime) {
		this.lcHoldTime = lcHoldTime;
	}

	public int getMaxParticipants() {
		return this.maxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
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

	public int getNumberOfLCToRequestHold() {
		return this.numberOfLCToRequestHold;
	}

	public void setNumberOfLCToRequestHold(int numberOfLCToRequestHold) {
		this.numberOfLCToRequestHold = numberOfLCToRequestHold;
	}

	public String getProgramName() {
		return this.programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public int getSplitPlacementPending() {
		return this.splitPlacementPending;
	}

	public void setSplitPlacementPending(int splitPlacementPending) {
		this.splitPlacementPending = splitPlacementPending;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public byte getStopAcceptingApps() {
		return this.stopAcceptingApps;
	}

	public void setStopAcceptingApps(byte stopAcceptingApps) {
		this.stopAcceptingApps = stopAcceptingApps;
	}

	public byte getStopAcceptingAppsHighSchoolVisits() {
		return this.stopAcceptingAppsHighSchoolVisits;
	}

	public void setStopAcceptingAppsHighSchoolVisits(byte stopAcceptingAppsHighSchoolVisits) {
		this.stopAcceptingAppsHighSchoolVisits = stopAcceptingAppsHighSchoolVisits;
	}

	public byte getStopAcceptingAppsHolidayHomestay() {
		return this.stopAcceptingAppsHolidayHomestay;
	}

	public void setStopAcceptingAppsHolidayHomestay(byte stopAcceptingAppsHolidayHomestay) {
		this.stopAcceptingAppsHolidayHomestay = stopAcceptingAppsHolidayHomestay;
	}

	public byte getStopAcceptingAppsLanguageBuddy() {
		return this.stopAcceptingAppsLanguageBuddy;
	}

	public void setStopAcceptingAppsLanguageBuddy(byte stopAcceptingAppsLanguageBuddy) {
		this.stopAcceptingAppsLanguageBuddy = stopAcceptingAppsLanguageBuddy;
	}

	public byte getStopAcceptingAppsStandardIHP() {
		return this.stopAcceptingAppsStandardIHP;
	}

	public void setStopAcceptingAppsStandardIHP(byte stopAcceptingAppsStandardIHP) {
		this.stopAcceptingAppsStandardIHP = stopAcceptingAppsStandardIHP;
	}

	public byte getStopAcceptingAppsVolunteerHomestay() {
		return this.stopAcceptingAppsVolunteerHomestay;
	}

	public void setStopAcceptingAppsVolunteerHomestay(byte stopAcceptingAppsVolunteerHomestay) {
		this.stopAcceptingAppsVolunteerHomestay = stopAcceptingAppsVolunteerHomestay;
	}

	public LookupGender getLookupGender() {
		return this.lookupGender;
	}

	public void setLookupGender(LookupGender lookupGender) {
		this.lookupGender = lookupGender;
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

	public List<SeasonIHPDetailsRegionApplication> getSeasonIhpdetailsRegionApplications() {
		return this.seasonIhpdetailsRegionApplications;
	}

	public void setSeasonIhpdetailsRegionApplications(List<SeasonIHPDetailsRegionApplication> seasonIhpdetailsRegionApplications) {
		this.seasonIhpdetailsRegionApplications = seasonIhpdetailsRegionApplications;
	}

	public SeasonIHPDetailsRegionApplication addSeasonIhpdetailsRegionApplication(SeasonIHPDetailsRegionApplication seasonIhpdetailsRegionApplication) {
		getSeasonIhpdetailsRegionApplications().add(seasonIhpdetailsRegionApplication);
		seasonIhpdetailsRegionApplication.setSeasonIhpdetail(this);

		return seasonIhpdetailsRegionApplication;
	}

	public SeasonIHPDetailsRegionApplication removeSeasonIhpdetailsRegionApplication(SeasonIHPDetailsRegionApplication seasonIhpdetailsRegionApplication) {
		getSeasonIhpdetailsRegionApplications().remove(seasonIhpdetailsRegionApplication);
		seasonIhpdetailsRegionApplication.setSeasonIhpdetail(null);

		return seasonIhpdetailsRegionApplication;
	}

}