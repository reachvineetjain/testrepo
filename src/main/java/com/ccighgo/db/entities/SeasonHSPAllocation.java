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
	private Integer seasonHSPAllocationId;

	@Column(nullable=false)
	private Integer createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	private Integer maxGuaranteedPax;

	private Integer maxUnguaranteedPax;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to DepartmentProgramOption
	@ManyToOne
	@JoinColumn(name="departmentProgramOptionId", nullable=false)
	private DepartmentProgramOption departmentProgramOption;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId", nullable=false)
	private Season season;

	public SeasonHSPAllocation() {
	}

	public Integer getSeasonHSPAllocationId() {
		return this.seasonHSPAllocationId;
	}

	public void setSeasonHSPAllocationId(Integer seasonHSPAllocationId) {
		this.seasonHSPAllocationId = seasonHSPAllocationId;
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

	public Integer getMaxGuaranteedPax() {
		return this.maxGuaranteedPax;
	}

	public void setMaxGuaranteedPax(Integer maxGuaranteedPax) {
		this.maxGuaranteedPax = maxGuaranteedPax;
	}

	public Integer getMaxUnguaranteedPax() {
		return this.maxUnguaranteedPax;
	}

	public void setMaxUnguaranteedPax(Integer maxUnguaranteedPax) {
		this.maxUnguaranteedPax = maxUnguaranteedPax;
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