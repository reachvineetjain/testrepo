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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int seasonIHPDetailsRegionApplicationId;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	private byte stopAcceptingApps;

	//bi-directional many-to-one association to SeasonIHPDetail
	@ManyToOne
	@JoinColumn(name="seasonIHPDetailsId")
	private SeasonIHPDetail seasonIhpdetail;

	//bi-directional many-to-one association to RegionIHP
	@ManyToOne
	@JoinColumn(name="regionIHPId")
	private RegionIHP regionIhp;

	public SeasonIHPDetailsRegionApplication() {
	}

	public int getSeasonIHPDetailsRegionApplicationId() {
		return this.seasonIHPDetailsRegionApplicationId;
	}

	public void setSeasonIHPDetailsRegionApplicationId(int seasonIHPDetailsRegionApplicationId) {
		this.seasonIHPDetailsRegionApplicationId = seasonIHPDetailsRegionApplicationId;
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

	public byte getStopAcceptingApps() {
		return this.stopAcceptingApps;
	}

	public void setStopAcceptingApps(byte stopAcceptingApps) {
		this.stopAcceptingApps = stopAcceptingApps;
	}

	public SeasonIHPDetail getSeasonIhpdetail() {
		return this.seasonIhpdetail;
	}

	public void setSeasonIhpdetail(SeasonIHPDetail seasonIhpdetail) {
		this.seasonIhpdetail = seasonIhpdetail;
	}

	public RegionIHP getRegionIhp() {
		return this.regionIhp;
	}

	public void setRegionIhp(RegionIHP regionIhp) {
		this.regionIhp = regionIhp;
	}

}