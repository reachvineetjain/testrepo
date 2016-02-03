package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the SeasonIHPDetailsRegionApplications database table.
 * 
 */
@Entity
@Table(name="SeasonIHPDetailsRegionApplications")
@NamedQuery(name="SeasonIHPDetailsRegionApplication.findAll", query="SELECT s FROM SeasonIHPDetailsRegionApplication s")
public class SeasonIHPDetailsRegionApplication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer seasonIHPDetailsRegionApplicationId;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	private Byte stopAcceptingApps;

	//bi-directional many-to-one association to RegionIHP
	@ManyToOne
	@JoinColumn(name="regionIHPId",insertable=false,updatable=false)
	private RegionIHP regionIhp;

	//bi-directional many-to-one association to SeasonIHPDetail
	@ManyToOne
	@JoinColumn(name="seasonIHPDetailsId",insertable=false,updatable=false)
	private SeasonIHPDetail seasonIhpdetail;

	public SeasonIHPDetailsRegionApplication() {
	}

	public Integer getSeasonIHPDetailsRegionApplicationId() {
		return this.seasonIHPDetailsRegionApplicationId;
	}

	public void setSeasonIHPDetailsRegionApplicationId(Integer seasonIHPDetailsRegionApplicationId) {
		this.seasonIHPDetailsRegionApplicationId = seasonIHPDetailsRegionApplicationId;
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

	public Byte getStopAcceptingApps() {
		return this.stopAcceptingApps;
	}

	public void setStopAcceptingApps(Byte stopAcceptingApps) {
		this.stopAcceptingApps = stopAcceptingApps;
	}

	public RegionIHP getRegionIhp() {
		return this.regionIhp;
	}

	public void setRegionIhp(RegionIHP regionIhp) {
		this.regionIhp = regionIhp;
	}

	public SeasonIHPDetail getSeasonIhpdetail() {
		return this.seasonIhpdetail;
	}

	public void setSeasonIhpdetail(SeasonIHPDetail seasonIhpdetail) {
		this.seasonIhpdetail = seasonIhpdetail;
	}

}