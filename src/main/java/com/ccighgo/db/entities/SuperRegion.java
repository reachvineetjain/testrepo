package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the SuperRegion database table.
 * 
 */
@Entity
@Table(name="SuperRegion")
@NamedQuery(name="SuperRegion.findAll", query="SELECT s FROM SuperRegion s")
public class SuperRegion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer superRegionId;

	private Byte active;

	@Column(nullable=false)
	private Integer createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(length=50)
	private String superRegionName;

	//bi-directional many-to-one association to SeasonGeographyConfiguration
	@OneToMany(mappedBy="superRegion")
	private List<SeasonGeographyConfiguration> seasonGeographyConfigurations;

	public SuperRegion() {
	}

	public Integer getSuperRegionId() {
		return this.superRegionId;
	}

	public void setSuperRegionId(Integer superRegionId) {
		this.superRegionId = superRegionId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
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

	public String getSuperRegionName() {
		return this.superRegionName;
	}

	public void setSuperRegionName(String superRegionName) {
		this.superRegionName = superRegionName;
	}

	public List<SeasonGeographyConfiguration> getSeasonGeographyConfigurations() {
		return this.seasonGeographyConfigurations;
	}

	public void setSeasonGeographyConfigurations(List<SeasonGeographyConfiguration> seasonGeographyConfigurations) {
		this.seasonGeographyConfigurations = seasonGeographyConfigurations;
	}

	public SeasonGeographyConfiguration addSeasonGeographyConfiguration(SeasonGeographyConfiguration seasonGeographyConfiguration) {
		getSeasonGeographyConfigurations().add(seasonGeographyConfiguration);
		seasonGeographyConfiguration.setSuperRegion(this);

		return seasonGeographyConfiguration;
	}

	public SeasonGeographyConfiguration removeSeasonGeographyConfiguration(SeasonGeographyConfiguration seasonGeographyConfiguration) {
		getSeasonGeographyConfigurations().remove(seasonGeographyConfiguration);
		seasonGeographyConfiguration.setSuperRegion(null);

		return seasonGeographyConfiguration;
	}

}