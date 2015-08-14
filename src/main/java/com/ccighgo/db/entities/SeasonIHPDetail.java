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
	private Integer seasonIHPDetailsId;

	private Integer applicationDeadLineWeeks;

	@Column(nullable=false)
	private Integer createdBy;

	private Timestamp createdOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	private Integer lcHoldTime;

	private Integer maxParticipants;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	private Integer numberOfLCToRequestHold;

	@Column(length=45)
	private String programName;

	private Integer splitPlacementPending;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	private byte stopAcceptingApps;

	private byte stopAcceptingAppsByGender;

	private byte stopAcceptingAppsHighSchoolVisits;

	private byte stopAcceptingAppsHolidayHomestay;

	private byte stopAcceptingAppsLanguageBuddy;

	private byte stopAcceptingAppsStandardIHP;

	private byte stopAcceptingAppsVolunteerHomestay;

	//bi-directional many-to-one association to LookupGender
	@ManyToOne
	@JoinColumn(name="genderId")
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

	public Integer getSeasonIHPDetailsId() {
		return this.seasonIHPDetailsId;
	}

	public void setSeasonIHPDetailsId(Integer seasonIHPDetailsId) {
		this.seasonIHPDetailsId = seasonIHPDetailsId;
	}

	public Integer getApplicationDeadLineWeeks() {
		return this.applicationDeadLineWeeks;
	}

	public void setApplicationDeadLineWeeks(Integer applicationDeadLineWeeks) {
		this.applicationDeadLineWeeks = applicationDeadLineWeeks;
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

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getLcHoldTime() {
		return this.lcHoldTime;
	}

	public void setLcHoldTime(Integer lcHoldTime) {
		this.lcHoldTime = lcHoldTime;
	}

	public Integer getMaxParticipants() {
		return this.maxParticipants;
	}

	public void setMaxParticipants(Integer maxParticipants) {
		this.maxParticipants = maxParticipants;
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

	public Integer getNumberOfLCToRequestHold() {
		return this.numberOfLCToRequestHold;
	}

	public void setNumberOfLCToRequestHold(Integer numberOfLCToRequestHold) {
		this.numberOfLCToRequestHold = numberOfLCToRequestHold;
	}

	public String getProgramName() {
		return this.programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public Integer getSplitPlacementPending() {
		return this.splitPlacementPending;
	}

	public void setSplitPlacementPending(Integer splitPlacementPending) {
		this.splitPlacementPending = splitPlacementPending;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Byte getStopAcceptingApps() {
		return this.stopAcceptingApps;
	}

	public void setStopAcceptingApps(byte stopAcceptingApps) {
		this.stopAcceptingApps = stopAcceptingApps;
	}

	public byte getStopAcceptingAppsByGender() {
		return this.stopAcceptingAppsByGender;
	}

	public void setStopAcceptingAppsByGender(byte stopAcceptingAppsByGender) {
		this.stopAcceptingAppsByGender = stopAcceptingAppsByGender;
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