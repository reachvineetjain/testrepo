package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 * The persistent class for the SeasonGeographyConfiguration database table.
 * 
 */
@Entity
@Table(name="SeasonGeographyConfiguration")
@NamedQuery(name="SeasonGeographyConfiguration.findAll", query="SELECT s FROM SeasonGeographyConfiguration s")
public class SeasonGeographyConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer seasonGeographyConfigurationId;

	@Column(nullable=false)
	private Integer createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to FieldStaffLCSeason
	@OneToMany(mappedBy = "seasonGeographyConfiguration", fetch = FetchType.LAZY)
	   @Fetch(value = FetchMode.SUBSELECT)
	private List<FieldStaffLCSeason> fieldStaffLcseasons;

	//bi-directional many-to-one association to FieldStaffLeadershipSeason
	@OneToMany(mappedBy = "seasonGeographyConfiguration", fetch = FetchType.LAZY,cascade = { CascadeType.REMOVE })
	   @Fetch(value = FetchMode.SUBSELECT)
	private List<FieldStaffLeadershipSeason> fieldStaffLeadershipSeasons;

	//bi-directional many-to-one association to LookupUSState
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usStatesId")
	private LookupUSState lookupUsstate;

	//bi-directional many-to-one association to Region
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="regionId")
	private Region region;

	//bi-directional many-to-one association to Season
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="seasonId")
	private Season season;

	//bi-directional many-to-one association to SuperRegion
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="superRegionId")
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

	public List<FieldStaffLCSeason> getFieldStaffLcseasons() {
		return this.fieldStaffLcseasons;
	}

	public void setFieldStaffLcseasons(List<FieldStaffLCSeason> fieldStaffLcseasons) {
		this.fieldStaffLcseasons = fieldStaffLcseasons;
	}

	public FieldStaffLCSeason addFieldStaffLcseason(FieldStaffLCSeason fieldStaffLcseason) {
		getFieldStaffLcseasons().add(fieldStaffLcseason);
		fieldStaffLcseason.setSeasonGeographyConfiguration(this);

		return fieldStaffLcseason;
	}

	public FieldStaffLCSeason removeFieldStaffLcseason(FieldStaffLCSeason fieldStaffLcseason) {
		getFieldStaffLcseasons().remove(fieldStaffLcseason);
		fieldStaffLcseason.setSeasonGeographyConfiguration(null);

		return fieldStaffLcseason;
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