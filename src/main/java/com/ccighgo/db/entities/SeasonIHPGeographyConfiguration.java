package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the SeasonIHPGeographyConfiguration database table.
 * 
 */
@Entity
@Table(name="SeasonIHPGeographyConfiguration")
@NamedQuery(name="SeasonIHPGeographyConfiguration.findAll", query="SELECT s FROM SeasonIHPGeographyConfiguration s")
public class SeasonIHPGeographyConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer seasonIHPGeographyConfigurationId;

	@Column(nullable=false)
	private Integer createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to LookupUSState
	@ManyToOne
	@JoinColumn(name="usStatesId")
	private LookupUSState lookupUsstate;

	//bi-directional many-to-one association to RegionIHP
	@ManyToOne
	@JoinColumn(name="regionIHPId")
	private RegionIHP regionIhp;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId")
	private Season season;

	public SeasonIHPGeographyConfiguration() {
	}

	public Integer getSeasonIHPGeographyConfigurationId() {
		return this.seasonIHPGeographyConfigurationId;
	}

	public void setSeasonIHPGeographyConfigurationId(Integer seasonIHPGeographyConfigurationId) {
		this.seasonIHPGeographyConfigurationId = seasonIHPGeographyConfigurationId;
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

	public LookupUSState getLookupUsstate() {
		return this.lookupUsstate;
	}

	public void setLookupUsstate(LookupUSState lookupUsstate) {
		this.lookupUsstate = lookupUsstate;
	}

	public RegionIHP getRegionIhp() {
		return this.regionIhp;
	}

	public void setRegionIhp(RegionIHP regionIhp) {
		this.regionIhp = regionIhp;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

}