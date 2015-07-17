package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the FieldStaffLCSeason database table.
 * 
 */
@Entity
@Table(name="FieldStaffLCSeason")
@NamedQuery(name="FieldStaffLCSeason.findAll", query="SELECT f FROM FieldStaffLCSeason f")
public class FieldStaffLCSeason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int fieldStaffLCSeasonId;

	@Column(nullable=false)
	private int createdBy;

	private Timestamp createdOn;

	private int erdId;

	private int fieldStaffId;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	private int rdId;

	private int rmId;

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

	public FieldStaffLCSeason() {
	}

	public int getFieldStaffLCSeasonId() {
		return this.fieldStaffLCSeasonId;
	}

	public void setFieldStaffLCSeasonId(int fieldStaffLCSeasonId) {
		this.fieldStaffLCSeasonId = fieldStaffLCSeasonId;
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

	public int getErdId() {
		return this.erdId;
	}

	public void setErdId(int erdId) {
		this.erdId = erdId;
	}

	public int getFieldStaffId() {
		return this.fieldStaffId;
	}

	public void setFieldStaffId(int fieldStaffId) {
		this.fieldStaffId = fieldStaffId;
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

	public int getRdId() {
		return this.rdId;
	}

	public void setRdId(int rdId) {
		this.rdId = rdId;
	}

	public int getRmId() {
		return this.rmId;
	}

	public void setRmId(int rmId) {
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