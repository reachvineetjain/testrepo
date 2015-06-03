package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SeasonHSPAllocatin database table.
 * 
 */
@Entity
@Table(name="SeasonHSPAllocatin")
@NamedQuery(name="SeasonHSPAllocatin.findAll", query="SELECT s FROM SeasonHSPAllocatin s")
public class SeasonHSPAllocatin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int seasonHSPAllocationID;

	@Column(nullable=false)
	private int maxGuaranteedPax;

	@Column(nullable=false)
	private int maxUnguaranteedPax;

	//bi-directional many-to-one association to DepartmentProgramOption
	@ManyToOne
	@JoinColumn(name="programOptionID", nullable=false)
	private DepartmentProgramOption departmentProgramOption;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonID", nullable=false)
	private Season season;

	public SeasonHSPAllocatin() {
	}

	public int getSeasonHSPAllocationID() {
		return this.seasonHSPAllocationID;
	}

	public void setSeasonHSPAllocationID(int seasonHSPAllocationID) {
		this.seasonHSPAllocationID = seasonHSPAllocationID;
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