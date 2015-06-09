package com.ccighgo.db.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the Season database table.
 * 
 */
@Entity
@Table(name="Season")
@NamedQuery(name="Season.findAll", query="SELECT s FROM Season s")
public class Season implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int seasonId;

	@Column(nullable=false)
	private byte active;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(nullable=false, length=50)
	private String seasonFullName;

	@Column(nullable=false, length=50)
	private String seasonName;

	//bi-directional many-to-one association to RegionSeason
	@OneToMany(mappedBy="season")
	private List<RegionSeason> regionSeasons;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="departmentId", nullable=false)
	private Department department;

	//bi-directional many-to-one association to SeasonStatus
	@ManyToOne
	@JoinColumn(name="seasonStatusID", nullable=false)
	private SeasonStatus seasonStatus;

	//bi-directional many-to-one association to SeasonCAPDetail
	@OneToMany(mappedBy="season")
	private List<SeasonCAPDetail> seasonCapdetails;

	//bi-directional many-to-one association to SeasonF1Detail
	@OneToMany(mappedBy="season")
	private List<SeasonF1Detail> seasonF1details;

	//bi-directional many-to-one association to SeasonGHTConfiguration
	@OneToMany(mappedBy="season")
	private List<SeasonGHTConfiguration> seasonGhtconfigurations;

	//bi-directional many-to-one association to SeasonHSADetail
	@OneToMany(mappedBy="season")
	private List<SeasonHSADetail> seasonHsadetails;

	//bi-directional many-to-one association to SeasonHSPAllocation
	@OneToMany(mappedBy="season")
	private List<SeasonHSPAllocation> seasonHspallocations;

	//bi-directional many-to-one association to SeasonHSPConfiguration
	@OneToMany(mappedBy="season", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<SeasonHSPConfiguration> seasonHspconfigurations;

	//bi-directional many-to-one association to SeasonJ1Detail
	@OneToMany(mappedBy="season")
	private List<SeasonJ1Detail> seasonJ1details;

	//bi-directional many-to-one association to SeasonLSDetail
	@OneToMany(mappedBy="season")
	private List<SeasonLSDetail> seasonLsdetails;

	//bi-directional many-to-one association to SeasonTADetail
	@OneToMany(mappedBy="season")
	private List<SeasonTADetail> seasonTadetails;

	//bi-directional many-to-one association to SeasonVolunteersDetail
	@OneToMany(mappedBy="season")
	private List<SeasonVolunteersDetail> seasonVolunteersDetails;

	//bi-directional many-to-one association to SeasonWADetail
	@OneToMany(mappedBy="season")
	private List<SeasonWADetail> seasonWadetails;

	//bi-directional many-to-one association to SeasonWPAllocation
	@OneToMany(mappedBy="season")
	private List<SeasonWPAllocation> seasonWpallocations;

	//bi-directional many-to-one association to SeasonWPConfiguration
	@OneToMany(mappedBy="season")
	private List<SeasonWPConfiguration> seasonWpconfigurations;

	//bi-directional many-to-one association to SeasonWnTDetail
	@OneToMany(mappedBy="season")
	private List<SeasonWnTDetail> seasonWnTdetails;

	//bi-directional many-to-one association to USSchoolSeason
	@OneToMany(mappedBy="season")
	private List<USSchoolSeason> usschoolSeasons;

	public Season() {
	}

	public int getSeasonId() {
		return this.seasonId;
	}

	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
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

	public List<RegionSeason> getRegionSeasons() {
		return this.regionSeasons;
	}

	public void setRegionSeasons(List<RegionSeason> regionSeasons) {
		this.regionSeasons = regionSeasons;
	}

	public RegionSeason addRegionSeason(RegionSeason regionSeason) {
		getRegionSeasons().add(regionSeason);
		regionSeason.setSeason(this);

		return regionSeason;
	}

	public RegionSeason removeRegionSeason(RegionSeason regionSeason) {
		getRegionSeasons().remove(regionSeason);
		regionSeason.setSeason(null);

		return regionSeason;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public SeasonStatus getSeasonStatus() {
		return this.seasonStatus;
	}

	public void setSeasonStatus(SeasonStatus seasonStatus) {
		this.seasonStatus = seasonStatus;
	}

	public List<SeasonCAPDetail> getSeasonCapdetails() {
		return this.seasonCapdetails;
	}

	public void setSeasonCapdetails(List<SeasonCAPDetail> seasonCapdetails) {
		this.seasonCapdetails = seasonCapdetails;
	}

	public SeasonCAPDetail addSeasonCapdetail(SeasonCAPDetail seasonCapdetail) {
		getSeasonCapdetails().add(seasonCapdetail);
		seasonCapdetail.setSeason(this);

		return seasonCapdetail;
	}

	public SeasonCAPDetail removeSeasonCapdetail(SeasonCAPDetail seasonCapdetail) {
		getSeasonCapdetails().remove(seasonCapdetail);
		seasonCapdetail.setSeason(null);

		return seasonCapdetail;
	}

	public List<SeasonF1Detail> getSeasonF1details() {
		return this.seasonF1details;
	}

	public void setSeasonF1details(List<SeasonF1Detail> seasonF1details) {
		this.seasonF1details = seasonF1details;
	}

	public SeasonF1Detail addSeasonF1detail(SeasonF1Detail seasonF1detail) {
		getSeasonF1details().add(seasonF1detail);
		seasonF1detail.setSeason(this);

		return seasonF1detail;
	}

	public SeasonF1Detail removeSeasonF1detail(SeasonF1Detail seasonF1detail) {
		getSeasonF1details().remove(seasonF1detail);
		seasonF1detail.setSeason(null);

		return seasonF1detail;
	}

	public List<SeasonGHTConfiguration> getSeasonGhtconfigurations() {
		return this.seasonGhtconfigurations;
	}

	public void setSeasonGhtconfigurations(List<SeasonGHTConfiguration> seasonGhtconfigurations) {
		this.seasonGhtconfigurations = seasonGhtconfigurations;
	}

	public SeasonGHTConfiguration addSeasonGhtconfiguration(SeasonGHTConfiguration seasonGhtconfiguration) {
		getSeasonGhtconfigurations().add(seasonGhtconfiguration);
		seasonGhtconfiguration.setSeason(this);

		return seasonGhtconfiguration;
	}

	public SeasonGHTConfiguration removeSeasonGhtconfiguration(SeasonGHTConfiguration seasonGhtconfiguration) {
		getSeasonGhtconfigurations().remove(seasonGhtconfiguration);
		seasonGhtconfiguration.setSeason(null);

		return seasonGhtconfiguration;
	}

	public List<SeasonHSADetail> getSeasonHsadetails() {
		return this.seasonHsadetails;
	}

	public void setSeasonHsadetails(List<SeasonHSADetail> seasonHsadetails) {
		this.seasonHsadetails = seasonHsadetails;
	}

	public SeasonHSADetail addSeasonHsadetail(SeasonHSADetail seasonHsadetail) {
		getSeasonHsadetails().add(seasonHsadetail);
		seasonHsadetail.setSeason(this);

		return seasonHsadetail;
	}

	public SeasonHSADetail removeSeasonHsadetail(SeasonHSADetail seasonHsadetail) {
		getSeasonHsadetails().remove(seasonHsadetail);
		seasonHsadetail.setSeason(null);

		return seasonHsadetail;
	}

	public List<SeasonHSPAllocation> getSeasonHspallocations() {
		return this.seasonHspallocations;
	}

	public void setSeasonHspallocations(List<SeasonHSPAllocation> seasonHspallocations) {
		this.seasonHspallocations = seasonHspallocations;
	}

	public SeasonHSPAllocation addSeasonHspallocation(SeasonHSPAllocation seasonHspallocation) {
		getSeasonHspallocations().add(seasonHspallocation);
		seasonHspallocation.setSeason(this);

		return seasonHspallocation;
	}

	public SeasonHSPAllocation removeSeasonHspallocation(SeasonHSPAllocation seasonHspallocation) {
		getSeasonHspallocations().remove(seasonHspallocation);
		seasonHspallocation.setSeason(null);

		return seasonHspallocation;
	}

	public List<SeasonHSPConfiguration> getSeasonHspconfigurations() {
		return this.seasonHspconfigurations;
	}

	public void setSeasonHspconfigurations(List<SeasonHSPConfiguration> seasonHspconfigurations) {
		this.seasonHspconfigurations = seasonHspconfigurations;
	}

	public SeasonHSPConfiguration addSeasonHspconfiguration(SeasonHSPConfiguration seasonHspconfiguration) {
		getSeasonHspconfigurations().add(seasonHspconfiguration);
		seasonHspconfiguration.setSeason(this);

		return seasonHspconfiguration;
	}

	public SeasonHSPConfiguration removeSeasonHspconfiguration(SeasonHSPConfiguration seasonHspconfiguration) {
		getSeasonHspconfigurations().remove(seasonHspconfiguration);
		seasonHspconfiguration.setSeason(null);

		return seasonHspconfiguration;
	}

	public List<SeasonJ1Detail> getSeasonJ1details() {
		return this.seasonJ1details;
	}

	public void setSeasonJ1details(List<SeasonJ1Detail> seasonJ1details) {
		this.seasonJ1details = seasonJ1details;
	}

	public SeasonJ1Detail addSeasonJ1detail(SeasonJ1Detail seasonJ1detail) {
		getSeasonJ1details().add(seasonJ1detail);
		seasonJ1detail.setSeason(this);

		return seasonJ1detail;
	}

	public SeasonJ1Detail removeSeasonJ1detail(SeasonJ1Detail seasonJ1detail) {
		getSeasonJ1details().remove(seasonJ1detail);
		seasonJ1detail.setSeason(null);

		return seasonJ1detail;
	}

	public List<SeasonLSDetail> getSeasonLsdetails() {
		return this.seasonLsdetails;
	}

	public void setSeasonLsdetails(List<SeasonLSDetail> seasonLsdetails) {
		this.seasonLsdetails = seasonLsdetails;
	}

	public SeasonLSDetail addSeasonLsdetail(SeasonLSDetail seasonLsdetail) {
		getSeasonLsdetails().add(seasonLsdetail);
		seasonLsdetail.setSeason(this);

		return seasonLsdetail;
	}

	public SeasonLSDetail removeSeasonLsdetail(SeasonLSDetail seasonLsdetail) {
		getSeasonLsdetails().remove(seasonLsdetail);
		seasonLsdetail.setSeason(null);

		return seasonLsdetail;
	}

	public List<SeasonTADetail> getSeasonTadetails() {
		return this.seasonTadetails;
	}

	public void setSeasonTadetails(List<SeasonTADetail> seasonTadetails) {
		this.seasonTadetails = seasonTadetails;
	}

	public SeasonTADetail addSeasonTadetail(SeasonTADetail seasonTadetail) {
		getSeasonTadetails().add(seasonTadetail);
		seasonTadetail.setSeason(this);

		return seasonTadetail;
	}

	public SeasonTADetail removeSeasonTadetail(SeasonTADetail seasonTadetail) {
		getSeasonTadetails().remove(seasonTadetail);
		seasonTadetail.setSeason(null);

		return seasonTadetail;
	}

	public List<SeasonVolunteersDetail> getSeasonVolunteersDetails() {
		return this.seasonVolunteersDetails;
	}

	public void setSeasonVolunteersDetails(List<SeasonVolunteersDetail> seasonVolunteersDetails) {
		this.seasonVolunteersDetails = seasonVolunteersDetails;
	}

	public SeasonVolunteersDetail addSeasonVolunteersDetail(SeasonVolunteersDetail seasonVolunteersDetail) {
		getSeasonVolunteersDetails().add(seasonVolunteersDetail);
		seasonVolunteersDetail.setSeason(this);

		return seasonVolunteersDetail;
	}

	public SeasonVolunteersDetail removeSeasonVolunteersDetail(SeasonVolunteersDetail seasonVolunteersDetail) {
		getSeasonVolunteersDetails().remove(seasonVolunteersDetail);
		seasonVolunteersDetail.setSeason(null);

		return seasonVolunteersDetail;
	}

	public List<SeasonWADetail> getSeasonWadetails() {
		return this.seasonWadetails;
	}

	public void setSeasonWadetails(List<SeasonWADetail> seasonWadetails) {
		this.seasonWadetails = seasonWadetails;
	}

	public SeasonWADetail addSeasonWadetail(SeasonWADetail seasonWadetail) {
		getSeasonWadetails().add(seasonWadetail);
		seasonWadetail.setSeason(this);

		return seasonWadetail;
	}

	public SeasonWADetail removeSeasonWadetail(SeasonWADetail seasonWadetail) {
		getSeasonWadetails().remove(seasonWadetail);
		seasonWadetail.setSeason(null);

		return seasonWadetail;
	}

	public List<SeasonWPAllocation> getSeasonWpallocations() {
		return this.seasonWpallocations;
	}

	public void setSeasonWpallocations(List<SeasonWPAllocation> seasonWpallocations) {
		this.seasonWpallocations = seasonWpallocations;
	}

	public SeasonWPAllocation addSeasonWpallocation(SeasonWPAllocation seasonWpallocation) {
		getSeasonWpallocations().add(seasonWpallocation);
		seasonWpallocation.setSeason(this);

		return seasonWpallocation;
	}

	public SeasonWPAllocation removeSeasonWpallocation(SeasonWPAllocation seasonWpallocation) {
		getSeasonWpallocations().remove(seasonWpallocation);
		seasonWpallocation.setSeason(null);

		return seasonWpallocation;
	}

	public List<SeasonWPConfiguration> getSeasonWpconfigurations() {
		return this.seasonWpconfigurations;
	}

	public void setSeasonWpconfigurations(List<SeasonWPConfiguration> seasonWpconfigurations) {
		this.seasonWpconfigurations = seasonWpconfigurations;
	}

	public SeasonWPConfiguration addSeasonWpconfiguration(SeasonWPConfiguration seasonWpconfiguration) {
		getSeasonWpconfigurations().add(seasonWpconfiguration);
		seasonWpconfiguration.setSeason(this);

		return seasonWpconfiguration;
	}

	public SeasonWPConfiguration removeSeasonWpconfiguration(SeasonWPConfiguration seasonWpconfiguration) {
		getSeasonWpconfigurations().remove(seasonWpconfiguration);
		seasonWpconfiguration.setSeason(null);

		return seasonWpconfiguration;
	}

	public List<SeasonWnTDetail> getSeasonWnTdetails() {
		return this.seasonWnTdetails;
	}

	public void setSeasonWnTdetails(List<SeasonWnTDetail> seasonWnTdetails) {
		this.seasonWnTdetails = seasonWnTdetails;
	}

	public SeasonWnTDetail addSeasonWnTdetail(SeasonWnTDetail seasonWnTdetail) {
		getSeasonWnTdetails().add(seasonWnTdetail);
		seasonWnTdetail.setSeason(this);

		return seasonWnTdetail;
	}

	public SeasonWnTDetail removeSeasonWnTdetail(SeasonWnTDetail seasonWnTdetail) {
		getSeasonWnTdetails().remove(seasonWnTdetail);
		seasonWnTdetail.setSeason(null);

		return seasonWnTdetail;
	}

	public List<USSchoolSeason> getUsschoolSeasons() {
		return this.usschoolSeasons;
	}

	public void setUsschoolSeasons(List<USSchoolSeason> usschoolSeasons) {
		this.usschoolSeasons = usschoolSeasons;
	}

	public USSchoolSeason addUsschoolSeason(USSchoolSeason usschoolSeason) {
		getUsschoolSeasons().add(usschoolSeason);
		usschoolSeason.setSeason(this);

		return usschoolSeason;
	}

	public USSchoolSeason removeUsschoolSeason(USSchoolSeason usschoolSeason) {
		getUsschoolSeasons().remove(usschoolSeason);
		usschoolSeason.setSeason(null);

		return usschoolSeason;
	}

}