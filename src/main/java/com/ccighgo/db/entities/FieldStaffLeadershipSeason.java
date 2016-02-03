package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the FieldStaffLeadershipSeason database table.
 * 
 */
@Entity
@NamedQuery(name="FieldStaffLeadershipSeason.findAll", query="SELECT f FROM FieldStaffLeadershipSeason f")
public class FieldStaffLeadershipSeason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer fieldStaffLeadershipSeasonId;

	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	private Integer modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="fieldStaffGoId",insertable=false,updatable=false)
	private FieldStaff fieldStaff;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId",insertable=false,updatable=false)
	private Season season;

	//bi-directional many-to-one association to SeasonGeographyConfiguration
	@ManyToOne
	@JoinColumn(name="seasonGeographyConfigurationId",insertable=false,updatable=false)
	private SeasonGeographyConfiguration seasonGeographyConfiguration;

	public FieldStaffLeadershipSeason() {
	}

	public Integer getFieldStaffLeadershipSeasonId() {
		return this.fieldStaffLeadershipSeasonId;
	}

	public void setFieldStaffLeadershipSeasonId(Integer fieldStaffLeadershipSeasonId) {
		this.fieldStaffLeadershipSeasonId = fieldStaffLeadershipSeasonId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public FieldStaff getFieldStaff() {
		return this.fieldStaff;
	}

	public void setFieldStaff(FieldStaff fieldStaff) {
		this.fieldStaff = fieldStaff;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public SeasonGeographyConfiguration getSeasonGeographyConfiguration() {
		return this.seasonGeographyConfiguration;
	}

	public void setSeasonGeographyConfiguration(SeasonGeographyConfiguration seasonGeographyConfiguration) {
		this.seasonGeographyConfiguration = seasonGeographyConfiguration;
	}

}