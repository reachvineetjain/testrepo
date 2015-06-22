package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the SeasonWPAllocation database table.
 * 
 */
@Entity
@Table(name="SeasonWPAllocation")
@NamedQuery(name="SeasonWPAllocation.findAll", query="SELECT s FROM SeasonWPAllocation s")
public class SeasonWPAllocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int seasonWPAllocationId;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	private int maxPax;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to AnnualSeason
	@ManyToOne
	@JoinColumn(name="annualSeasonId")
	private AnnualSeason annualSeason;

	//bi-directional many-to-one association to DepartmentProgramOption
	@ManyToOne
	@JoinColumn(name="departmentProgramOptionId")
	private DepartmentProgramOption departmentProgramOption;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId", nullable=false)
	private Season season;

	public SeasonWPAllocation() {
	}

	public int getSeasonWPAllocationId() {
		return this.seasonWPAllocationId;
	}

	public void setSeasonWPAllocationId(int seasonWPAllocationId) {
		this.seasonWPAllocationId = seasonWPAllocationId;
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

	public int getMaxPax() {
		return this.maxPax;
	}

	public void setMaxPax(int maxPax) {
		this.maxPax = maxPax;
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

	public AnnualSeason getAnnualSeason() {
		return this.annualSeason;
	}

	public void setAnnualSeason(AnnualSeason annualSeason) {
		this.annualSeason = annualSeason;
	}

	public DepartmentProgramOption getDepartmentProgramOption() {
		return this.departmentProgramOption;
	}

	public void setDepartmentProgramOption(DepartmentProgramOption departmentProgramOption) {
		this.departmentProgramOption = departmentProgramOption;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

}