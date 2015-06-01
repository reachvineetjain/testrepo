package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the seasonhspallocatin database table.
 * 
 */
@Entity
@Table(name="seasonhspallocatin")
@NamedQuery(name="SeasonHSPAllocation.findAll", query="SELECT s FROM SeasonHSPAllocation s")
public class SeasonHSPAllocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int seasonHSPAllocationID;

	@Column(nullable=false)
	private int maxGuaranteedPax;

	@Column(nullable=false)
	private int maxUnguaranteedPax;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonID", nullable=false)
	private Season season;

	//bi-directional many-to-one association to DepartmentProgramOption
	@ManyToOne
	@JoinColumn(name="programOptionID", nullable=false)
	private DepartmentProgramOption departmentprogramoption;

	public SeasonHSPAllocation() {
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

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public DepartmentProgramOption getDepartmentprogramoption() {
		return this.departmentprogramoption;
	}

	public void setDepartmentprogramoption(DepartmentProgramOption departmentprogramoption) {
		this.departmentprogramoption = departmentprogramoption;
	}

}