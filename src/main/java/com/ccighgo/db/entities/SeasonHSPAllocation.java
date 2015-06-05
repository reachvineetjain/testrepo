package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


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
	@Column(unique=true, nullable=false)
	private int seasonHSPAllocationId;

	@Column(nullable=false)
	private int maxGuaranteedPax;

	@Column(nullable=false)
	private int maxUnguaranteedPax;

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

	public int getSeasonHSPAllocationId() {
		return this.seasonHSPAllocationId;
	}

	public void setSeasonHSPAllocationId(int seasonHSPAllocationId) {
		this.seasonHSPAllocationId = seasonHSPAllocationId;
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