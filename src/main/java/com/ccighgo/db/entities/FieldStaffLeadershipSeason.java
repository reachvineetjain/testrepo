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

	private Integer erdId;

	private Integer fieldStaffId;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	private Integer rdId;

	private Integer rmId;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId")
	private Season season;

	//bi-directional many-to-one association to FieldStaffType
	@ManyToOne
	@JoinColumn(name="fieldStaffTypeId")
	private FieldStaffType fieldStaffType;

	//bi-directional many-to-one association to DepartmentProgram
	@ManyToOne
	@JoinColumn(name="departmentProgramId")
	private DepartmentProgram departmentProgram;

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

	public Integer getErdId() {
		return this.erdId;
	}

	public void setErdId(Integer erdId) {
		this.erdId = erdId;
	}

	public Integer getFieldStaffId() {
		return this.fieldStaffId;
	}

	public void setFieldStaffId(Integer fieldStaffId) {
		this.fieldStaffId = fieldStaffId;
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

	public Integer getRdId() {
		return this.rdId;
	}

	public void setRdId(Integer rdId) {
		this.rdId = rdId;
	}

	public Integer getRmId() {
		return this.rmId;
	}

	public void setRmId(Integer rmId) {
		this.rmId = rmId;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public FieldStaffType getFieldStaffType() {
		return this.fieldStaffType;
	}

	public void setFieldStaffType(FieldStaffType fieldStaffType) {
		this.fieldStaffType = fieldStaffType;
	}

	public DepartmentProgram getDepartmentProgram() {
		return this.departmentProgram;
	}

	public void setDepartmentProgram(DepartmentProgram departmentProgram) {
		this.departmentProgram = departmentProgram;
	}

	public SeasonGeographyConfiguration getSeasonGeographyConfiguration() {
		return this.seasonGeographyConfiguration;
	}

	public void setSeasonGeographyConfiguration(SeasonGeographyConfiguration seasonGeographyConfiguration) {
		this.seasonGeographyConfiguration = seasonGeographyConfiguration;
	}

}