package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the SeasonHSPAllocation database table.
 * 
 */
@Entity
@Table(name="SeasonHSPAllocation")
@NamedQuery(name="SeasonHSPAllocation.findAll", query="SELECT s FROM SeasonHSPAllocation s")
public class SeasonHSPAllocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int seasonHSPAllocationId;

	@Column(nullable=false)
	private int createdBy;

	private Timestamp createdOn;

	private int maxGuaranteedPax;

	private int maxUnguaranteedPax;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId", nullable=false)
	private Season season;

	//bi-directional many-to-one association to DepartmentProgramOption
	@ManyToOne
	@JoinColumn(name="departmentProgramOptionId", nullable=false)
	private DepartmentProgramOption departmentProgramOption;

	public SeasonHSPAllocation() {
	}

	public int getSeasonHSPAllocationId() {
		return this.seasonHSPAllocationId;
	}

	public void setSeasonHSPAllocationId(int seasonHSPAllocationId) {
		this.seasonHSPAllocationId = seasonHSPAllocationId;
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

	public int getMaxGuaranteedPax() {
		return this.maxGuaranteedPax;
	}

	public void setMaxGuaranteedPax(int maxGuaranteedPax) {
		this.maxGuaranteedPax = maxGuaranteedPax;
	}

	public int getMaxUnguaranteedPax() {
		return this.maxUnguaranteedPax;
	}

	public void setMaxUnguaranteedPax(int maxUnguaranteedPax) {
		this.maxUnguaranteedPax = maxUnguaranteedPax;
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

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public DepartmentProgramOption getDepartmentProgramOption() {
		return this.departmentProgramOption;
	}

	public void setDepartmentProgramOption(DepartmentProgramOption departmentProgramOption) {
		this.departmentProgramOption = departmentProgramOption;
	}

}