package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


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
	private int maxPax;

	//bi-directional many-to-one association to DepartmentProgramOption
	@ManyToOne
	@JoinColumn(name="departmentProgramOptionId", nullable=false)
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

	public int getMaxPax() {
		return this.maxPax;
	}

	public void setMaxPax(int maxPax) {
		this.maxPax = maxPax;
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