package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the FieldStaffLeadershipSeason database table.
 * 
 */
@Entity
@Table(name="FieldStaffLeadershipSeason")
@NamedQuery(name="FieldStaffLeadershipSeason.findAll", query="SELECT f FROM FieldStaffLeadershipSeason f")
public class FieldStaffLeadershipSeason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer fieldStaffLeadershipSeasonId;

	@Column(nullable=false)
	private Integer createdBy;

	private Timestamp createdOn;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="fieldStaffId")
	private FieldStaff fieldStaff1;

	//bi-directional many-to-one association to FieldStaffType
	@ManyToOne
	@JoinColumn(name="fieldStaffTypeId")
	private FieldStaffType fieldStaffType;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="erdId")
	private FieldStaff fieldStaff2;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="rdId")
	private FieldStaff fieldStaff3;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="rmId")
	private FieldStaff fieldStaff4;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId")
	private Season season;

	//bi-directional many-to-one association to SeasonGeographyConfiguration
	@ManyToOne
	@JoinColumn(name="seasonGeographyConfigurationId")
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

	public FieldStaff getFieldStaff1() {
		return this.fieldStaff1;
	}

	public void setFieldStaff1(FieldStaff fieldStaff1) {
		this.fieldStaff1 = fieldStaff1;
	}

	public FieldStaffType getFieldStaffType() {
		return this.fieldStaffType;
	}

	public void setFieldStaffType(FieldStaffType fieldStaffType) {
		this.fieldStaffType = fieldStaffType;
	}

	public FieldStaff getFieldStaff2() {
		return this.fieldStaff2;
	}

	public void setFieldStaff2(FieldStaff fieldStaff2) {
		this.fieldStaff2 = fieldStaff2;
	}

	public FieldStaff getFieldStaff3() {
		return this.fieldStaff3;
	}

	public void setFieldStaff3(FieldStaff fieldStaff3) {
		this.fieldStaff3 = fieldStaff3;
	}

	public FieldStaff getFieldStaff4() {
		return this.fieldStaff4;
	}

	public void setFieldStaff4(FieldStaff fieldStaff4) {
		this.fieldStaff4 = fieldStaff4;
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