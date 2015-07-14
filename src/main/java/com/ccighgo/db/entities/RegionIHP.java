package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the RegionIHP database table.
 * 
 */
@Entity
@Table(name="RegionIHP")
@NamedQuery(name="RegionIHP.findAll", query="SELECT r FROM RegionIHP r")
public class RegionIHP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int regionIHPId;

	private byte active;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(length=45)
	private String regionName;

	//bi-directional many-to-one association to SeasonIHPDetailsRegionApplication
	@OneToMany(mappedBy="regionIhp")
	private List<SeasonIHPDetailsRegionApplication> seasonIhpdetailsRegionApplications;

	public RegionIHP() {
	}

	public int getRegionIHPId() {
		return this.regionIHPId;
	}

	public void setRegionIHPId(int regionIHPId) {
		this.regionIHPId = regionIHPId;
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

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public List<SeasonIHPDetailsRegionApplication> getSeasonIhpdetailsRegionApplications() {
		return this.seasonIhpdetailsRegionApplications;
	}

	public void setSeasonIhpdetailsRegionApplications(List<SeasonIHPDetailsRegionApplication> seasonIhpdetailsRegionApplications) {
		this.seasonIhpdetailsRegionApplications = seasonIhpdetailsRegionApplications;
	}

	public SeasonIHPDetailsRegionApplication addSeasonIhpdetailsRegionApplication(SeasonIHPDetailsRegionApplication seasonIhpdetailsRegionApplication) {
		getSeasonIhpdetailsRegionApplications().add(seasonIhpdetailsRegionApplication);
		seasonIhpdetailsRegionApplication.setRegionIhp(this);

		return seasonIhpdetailsRegionApplication;
	}

	public SeasonIHPDetailsRegionApplication removeSeasonIhpdetailsRegionApplication(SeasonIHPDetailsRegionApplication seasonIhpdetailsRegionApplication) {
		getSeasonIhpdetailsRegionApplications().remove(seasonIhpdetailsRegionApplication);
		seasonIhpdetailsRegionApplication.setRegionIhp(null);

		return seasonIhpdetailsRegionApplication;
	}

}