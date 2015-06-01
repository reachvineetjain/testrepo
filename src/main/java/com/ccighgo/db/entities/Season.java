package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the season database table.
 * 
 */
@Entity
@Table(name="season")
@NamedQuery(name="Season.findAll", query="SELECT s FROM Season s")
public class Season implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int seasonID;

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
	private List<RegionSeason> regionseasons;

	//bi-directional many-to-one association to SeasonStatus
	@ManyToOne
	@JoinColumn(name="seasonStatusID", nullable=false)
	private SeasonStatus seasonstatus;

	//bi-directional many-to-one association to Departments
	@ManyToOne
	@JoinColumn(name="departmentID", nullable=false)
	private Departments department;

	//bi-directional many-to-one association to SeasonCAPDetails
	@OneToMany(mappedBy="season")
	private List<SeasonCAPDetails> seasoncapdetails;

	//bi-directional many-to-one association to SeasonF1Details
	@OneToMany(mappedBy="season")
	private List<SeasonF1Details> seasonf1details;

	//bi-directional many-to-one association to SeasonGHTConfiguration
	@OneToMany(mappedBy="season")
	private List<SeasonGHTConfiguration> seasonghtconfigurations;

	//bi-directional many-to-one association to SeasonHSADetails
	@OneToMany(mappedBy="season")
	private List<SeasonHSADetails> seasonhsadetails;

	//bi-directional many-to-one association to SeasonHSPAllocation
	@OneToMany(mappedBy="season")
	private List<SeasonHSPAllocation> seasonhspallocatins;

	//bi-directional many-to-one association to SeasonHSPConfiguration
	@OneToMany(mappedBy="season")
	private List<SeasonHSPConfiguration> seasonhspconfigurations;

	//bi-directional many-to-one association to SeasonJ1Details
	@OneToMany(mappedBy="season")
	private List<SeasonJ1Details> seasonj1details;

	//bi-directional many-to-one association to SeasonLSDetails
	@OneToMany(mappedBy="season")
	private List<SeasonLSDetails> seasonlsdetails;

	//bi-directional many-to-one association to SeasonTADetails
	@OneToMany(mappedBy="season")
	private List<SeasonTADetails> seasontadetails;

	//bi-directional many-to-one association to SeasonVolunteersDetails
	@OneToMany(mappedBy="season")
	private List<SeasonVolunteersDetails> seasonvolunteersdetails;

	//bi-directional many-to-one association to SeasonWADetails
	@OneToMany(mappedBy="season")
	private List<SeasonWADetails> seasonwadetails;

	//bi-directional many-to-one association to SeasonWnTDetails
	@OneToMany(mappedBy="season")
	private List<SeasonWnTDetails> seasonwntdetails;

	//bi-directional many-to-one association to SeasonWPAllocation
	@OneToMany(mappedBy="season")
	private List<SeasonWPAllocation> seasonwpallocations;

	//bi-directional many-to-one association to SeasonWPConfiguration
	@OneToMany(mappedBy="season")
	private List<SeasonWPConfiguration> seasonwpconfigurations;

	//bi-directional many-to-one association to USSchoolSeason
	@OneToMany(mappedBy="season")
	private List<USSchoolSeason> usschoolseasons;

	public Season() {
	}

	public int getSeasonID() {
		return this.seasonID;
	}

	public void setSeasonID(int seasonID) {
		this.seasonID = seasonID;
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

	public List<RegionSeason> getRegionseasons() {
		return this.regionseasons;
	}

	public void setRegionseasons(List<RegionSeason> regionseasons) {
		this.regionseasons = regionseasons;
	}

	public RegionSeason addRegionseason(RegionSeason regionseason) {
		getRegionseasons().add(regionseason);
		regionseason.setSeason(this);

		return regionseason;
	}

	public RegionSeason removeRegionseason(RegionSeason regionseason) {
		getRegionseasons().remove(regionseason);
		regionseason.setSeason(null);

		return regionseason;
	}

	public SeasonStatus getSeasonstatus() {
		return this.seasonstatus;
	}

	public void setSeasonstatus(SeasonStatus seasonstatus) {
		this.seasonstatus = seasonstatus;
	}

	public Departments getDepartment() {
		return this.department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	public List<SeasonCAPDetails> getSeasoncapdetails() {
		return this.seasoncapdetails;
	}

	public void setSeasoncapdetails(List<SeasonCAPDetails> seasoncapdetails) {
		this.seasoncapdetails = seasoncapdetails;
	}

	public SeasonCAPDetails addSeasoncapdetail(SeasonCAPDetails seasoncapdetail) {
		getSeasoncapdetails().add(seasoncapdetail);
		seasoncapdetail.setSeason(this);

		return seasoncapdetail;
	}

	public SeasonCAPDetails removeSeasoncapdetail(SeasonCAPDetails seasoncapdetail) {
		getSeasoncapdetails().remove(seasoncapdetail);
		seasoncapdetail.setSeason(null);

		return seasoncapdetail;
	}

	public List<SeasonF1Details> getSeasonf1details() {
		return this.seasonf1details;
	}

	public void setSeasonf1details(List<SeasonF1Details> seasonf1details) {
		this.seasonf1details = seasonf1details;
	}

	public SeasonF1Details addSeasonf1detail(SeasonF1Details seasonf1detail) {
		getSeasonf1details().add(seasonf1detail);
		seasonf1detail.setSeason(this);

		return seasonf1detail;
	}

	public SeasonF1Details removeSeasonf1detail(SeasonF1Details seasonf1detail) {
		getSeasonf1details().remove(seasonf1detail);
		seasonf1detail.setSeason(null);

		return seasonf1detail;
	}

	public List<SeasonGHTConfiguration> getSeasonghtconfigurations() {
		return this.seasonghtconfigurations;
	}

	public void setSeasonghtconfigurations(List<SeasonGHTConfiguration> seasonghtconfigurations) {
		this.seasonghtconfigurations = seasonghtconfigurations;
	}

	public SeasonGHTConfiguration addSeasonghtconfiguration(SeasonGHTConfiguration seasonghtconfiguration) {
		getSeasonghtconfigurations().add(seasonghtconfiguration);
		seasonghtconfiguration.setSeason(this);

		return seasonghtconfiguration;
	}

	public SeasonGHTConfiguration removeSeasonghtconfiguration(SeasonGHTConfiguration seasonghtconfiguration) {
		getSeasonghtconfigurations().remove(seasonghtconfiguration);
		seasonghtconfiguration.setSeason(null);

		return seasonghtconfiguration;
	}

	public List<SeasonHSADetails> getSeasonhsadetails() {
		return this.seasonhsadetails;
	}

	public void setSeasonhsadetails(List<SeasonHSADetails> seasonhsadetails) {
		this.seasonhsadetails = seasonhsadetails;
	}

	public SeasonHSADetails addSeasonhsadetail(SeasonHSADetails seasonhsadetail) {
		getSeasonhsadetails().add(seasonhsadetail);
		seasonhsadetail.setSeason(this);

		return seasonhsadetail;
	}

	public SeasonHSADetails removeSeasonhsadetail(SeasonHSADetails seasonhsadetail) {
		getSeasonhsadetails().remove(seasonhsadetail);
		seasonhsadetail.setSeason(null);

		return seasonhsadetail;
	}

	public List<SeasonHSPAllocation> getSeasonhspallocatins() {
		return this.seasonhspallocatins;
	}

	public void setSeasonhspallocatins(List<SeasonHSPAllocation> seasonhspallocatins) {
		this.seasonhspallocatins = seasonhspallocatins;
	}

	public SeasonHSPAllocation addSeasonhspallocatin(SeasonHSPAllocation seasonhspallocatin) {
		getSeasonhspallocatins().add(seasonhspallocatin);
		seasonhspallocatin.setSeason(this);

		return seasonhspallocatin;
	}

	public SeasonHSPAllocation removeSeasonhspallocatin(SeasonHSPAllocation seasonhspallocatin) {
		getSeasonhspallocatins().remove(seasonhspallocatin);
		seasonhspallocatin.setSeason(null);

		return seasonhspallocatin;
	}

	public List<SeasonHSPConfiguration> getSeasonhspconfigurations() {
		return this.seasonhspconfigurations;
	}

	public void setSeasonhspconfigurations(List<SeasonHSPConfiguration> seasonhspconfigurations) {
		this.seasonhspconfigurations = seasonhspconfigurations;
	}

	public SeasonHSPConfiguration addSeasonhspconfiguration(SeasonHSPConfiguration seasonhspconfiguration) {
		getSeasonhspconfigurations().add(seasonhspconfiguration);
		seasonhspconfiguration.setSeason(this);

		return seasonhspconfiguration;
	}

	public SeasonHSPConfiguration removeSeasonhspconfiguration(SeasonHSPConfiguration seasonhspconfiguration) {
		getSeasonhspconfigurations().remove(seasonhspconfiguration);
		seasonhspconfiguration.setSeason(null);

		return seasonhspconfiguration;
	}

	public List<SeasonJ1Details> getSeasonj1details() {
		return this.seasonj1details;
	}

	public void setSeasonj1details(List<SeasonJ1Details> seasonj1details) {
		this.seasonj1details = seasonj1details;
	}

	public SeasonJ1Details addSeasonj1detail(SeasonJ1Details seasonj1detail) {
		getSeasonj1details().add(seasonj1detail);
		seasonj1detail.setSeason(this);

		return seasonj1detail;
	}

	public SeasonJ1Details removeSeasonj1detail(SeasonJ1Details seasonj1detail) {
		getSeasonj1details().remove(seasonj1detail);
		seasonj1detail.setSeason(null);

		return seasonj1detail;
	}

	public List<SeasonLSDetails> getSeasonlsdetails() {
		return this.seasonlsdetails;
	}

	public void setSeasonlsdetails(List<SeasonLSDetails> seasonlsdetails) {
		this.seasonlsdetails = seasonlsdetails;
	}

	public SeasonLSDetails addSeasonlsdetail(SeasonLSDetails seasonlsdetail) {
		getSeasonlsdetails().add(seasonlsdetail);
		seasonlsdetail.setSeason(this);

		return seasonlsdetail;
	}

	public SeasonLSDetails removeSeasonlsdetail(SeasonLSDetails seasonlsdetail) {
		getSeasonlsdetails().remove(seasonlsdetail);
		seasonlsdetail.setSeason(null);

		return seasonlsdetail;
	}

	public List<SeasonTADetails> getSeasontadetails() {
		return this.seasontadetails;
	}

	public void setSeasontadetails(List<SeasonTADetails> seasontadetails) {
		this.seasontadetails = seasontadetails;
	}

	public SeasonTADetails addSeasontadetail(SeasonTADetails seasontadetail) {
		getSeasontadetails().add(seasontadetail);
		seasontadetail.setSeason(this);

		return seasontadetail;
	}

	public SeasonTADetails removeSeasontadetail(SeasonTADetails seasontadetail) {
		getSeasontadetails().remove(seasontadetail);
		seasontadetail.setSeason(null);

		return seasontadetail;
	}

	public List<SeasonVolunteersDetails> getSeasonvolunteersdetails() {
		return this.seasonvolunteersdetails;
	}

	public void setSeasonvolunteersdetails(List<SeasonVolunteersDetails> seasonvolunteersdetails) {
		this.seasonvolunteersdetails = seasonvolunteersdetails;
	}

	public SeasonVolunteersDetails addSeasonvolunteersdetail(SeasonVolunteersDetails seasonvolunteersdetail) {
		getSeasonvolunteersdetails().add(seasonvolunteersdetail);
		seasonvolunteersdetail.setSeason(this);

		return seasonvolunteersdetail;
	}

	public SeasonVolunteersDetails removeSeasonvolunteersdetail(SeasonVolunteersDetails seasonvolunteersdetail) {
		getSeasonvolunteersdetails().remove(seasonvolunteersdetail);
		seasonvolunteersdetail.setSeason(null);

		return seasonvolunteersdetail;
	}

	public List<SeasonWADetails> getSeasonwadetails() {
		return this.seasonwadetails;
	}

	public void setSeasonwadetails(List<SeasonWADetails> seasonwadetails) {
		this.seasonwadetails = seasonwadetails;
	}

	public SeasonWADetails addSeasonwadetail(SeasonWADetails seasonwadetail) {
		getSeasonwadetails().add(seasonwadetail);
		seasonwadetail.setSeason(this);

		return seasonwadetail;
	}

	public SeasonWADetails removeSeasonwadetail(SeasonWADetails seasonwadetail) {
		getSeasonwadetails().remove(seasonwadetail);
		seasonwadetail.setSeason(null);

		return seasonwadetail;
	}

	public List<SeasonWnTDetails> getSeasonwntdetails() {
		return this.seasonwntdetails;
	}

	public void setSeasonwntdetails(List<SeasonWnTDetails> seasonwntdetails) {
		this.seasonwntdetails = seasonwntdetails;
	}

	public SeasonWnTDetails addSeasonwntdetail(SeasonWnTDetails seasonwntdetail) {
		getSeasonwntdetails().add(seasonwntdetail);
		seasonwntdetail.setSeason(this);

		return seasonwntdetail;
	}

	public SeasonWnTDetails removeSeasonwntdetail(SeasonWnTDetails seasonwntdetail) {
		getSeasonwntdetails().remove(seasonwntdetail);
		seasonwntdetail.setSeason(null);

		return seasonwntdetail;
	}

	public List<SeasonWPAllocation> getSeasonwpallocations() {
		return this.seasonwpallocations;
	}

	public void setSeasonwpallocations(List<SeasonWPAllocation> seasonwpallocations) {
		this.seasonwpallocations = seasonwpallocations;
	}

	public SeasonWPAllocation addSeasonwpallocation(SeasonWPAllocation seasonwpallocation) {
		getSeasonwpallocations().add(seasonwpallocation);
		seasonwpallocation.setSeason(this);

		return seasonwpallocation;
	}

	public SeasonWPAllocation removeSeasonwpallocation(SeasonWPAllocation seasonwpallocation) {
		getSeasonwpallocations().remove(seasonwpallocation);
		seasonwpallocation.setSeason(null);

		return seasonwpallocation;
	}

	public List<SeasonWPConfiguration> getSeasonwpconfigurations() {
		return this.seasonwpconfigurations;
	}

	public void setSeasonwpconfigurations(List<SeasonWPConfiguration> seasonwpconfigurations) {
		this.seasonwpconfigurations = seasonwpconfigurations;
	}

	public SeasonWPConfiguration addSeasonwpconfiguration(SeasonWPConfiguration seasonwpconfiguration) {
		getSeasonwpconfigurations().add(seasonwpconfiguration);
		seasonwpconfiguration.setSeason(this);

		return seasonwpconfiguration;
	}

	public SeasonWPConfiguration removeSeasonwpconfiguration(SeasonWPConfiguration seasonwpconfiguration) {
		getSeasonwpconfigurations().remove(seasonwpconfiguration);
		seasonwpconfiguration.setSeason(null);

		return seasonwpconfiguration;
	}

	public List<USSchoolSeason> getUsschoolseasons() {
		return this.usschoolseasons;
	}

	public void setUsschoolseasons(List<USSchoolSeason> usschoolseasons) {
		this.usschoolseasons = usschoolseasons;
	}

	public USSchoolSeason addUsschoolseason(USSchoolSeason usschoolseason) {
		getUsschoolseasons().add(usschoolseason);
		usschoolseason.setSeason(this);

		return usschoolseason;
	}

	public USSchoolSeason removeUsschoolseason(USSchoolSeason usschoolseason) {
		getUsschoolseasons().remove(usschoolseason);
		usschoolseason.setSeason(null);

		return usschoolseason;
	}

}