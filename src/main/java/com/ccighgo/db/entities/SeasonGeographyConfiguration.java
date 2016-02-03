package com.ccighgo.db.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the SeasonGeographyConfiguration database table.
 * 
 */
@Entity
@NamedQuery(name="SeasonGeographyConfiguration.findAll", query="SELECT s FROM SeasonGeographyConfiguration s")
public class SeasonGeographyConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer seasonGeographyConfigurationId;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	//bi-directional many-to-one association to FieldStaffLeadershipSeason
	@OneToMany(mappedBy = "seasonGeographyConfiguration", fetch = FetchType.LAZY,cascade = { CascadeType.REMOVE })
   @Fetch(value = FetchMode.SUBSELECT)
	private List<FieldStaffLeadershipSeason> fieldStaffLeadershipSeasons;

	//bi-directional many-to-one association to LookupUSState
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usStatesId",insertable=false,updatable=false)
	private LookupUSState lookupUsstate;

	//bi-directional many-to-one association to Region
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="regionId",insertable=false,updatable=false)
	private Region region;

	//bi-directional many-to-one association to Season
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="seasonId",insertable=false,updatable=false)
	private Season season;

	//bi-directional many-to-one association to SuperRegion
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="superRegionId",insertable=false,updatable=false)
	private SuperRegion superRegion;

	public SeasonGeographyConfiguration() {
	}

	public Integer getSeasonGeographyConfigurationId() {
		return this.seasonGeographyConfigurationId;
	}

	public void setSeasonGeographyConfigurationId(Integer seasonGeographyConfigurationId) {
		this.seasonGeographyConfigurationId = seasonGeographyConfigurationId;
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

	public List<FieldStaffLeadershipSeason> getFieldStaffLeadershipSeasons() {
		return this.fieldStaffLeadershipSeasons;
	}

	public void setFieldStaffLeadershipSeasons(List<FieldStaffLeadershipSeason> fieldStaffLeadershipSeasons) {
		this.fieldStaffLeadershipSeasons = fieldStaffLeadershipSeasons;
	}

	public FieldStaffLeadershipSeason addFieldStaffLeadershipSeason(FieldStaffLeadershipSeason fieldStaffLeadershipSeason) {
		getFieldStaffLeadershipSeasons().add(fieldStaffLeadershipSeason);
		fieldStaffLeadershipSeason.setSeasonGeographyConfiguration(this);

		return fieldStaffLeadershipSeason;
	}

	public FieldStaffLeadershipSeason removeFieldStaffLeadershipSeason(FieldStaffLeadershipSeason fieldStaffLeadershipSeason) {
		getFieldStaffLeadershipSeasons().remove(fieldStaffLeadershipSeason);
		fieldStaffLeadershipSeason.setSeasonGeographyConfiguration(null);

		return fieldStaffLeadershipSeason;
	}

	public LookupUSState getLookupUsstate() {
		return this.lookupUsstate;
	}

	public void setLookupUsstate(LookupUSState lookupUsstate) {
		this.lookupUsstate = lookupUsstate;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public SuperRegion getSuperRegion() {
		return this.superRegion;
	}

	public void setSuperRegion(SuperRegion superRegion) {
		this.superRegion = superRegion;
	}

}