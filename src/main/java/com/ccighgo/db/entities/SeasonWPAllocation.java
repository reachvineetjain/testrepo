package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the seasonwpallocation database table.
 * 
 */
@Entity
@Table(name="seasonwpallocation")
@NamedQuery(name="SeasonWPAllocation.findAll", query="SELECT s FROM SeasonWPAllocation s")
public class SeasonWPAllocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int seasonWPAllocationID;

	private int maxPax;

	//bi-directional many-to-one association to DepartmentProgramOption
	@ManyToOne
	@JoinColumn(name="programOptionID", nullable=false)
	private DepartmentProgramOption departmentprogramoption;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonID", nullable=false)
	private Season season;

	public SeasonWPAllocation() {
	}

	public int getSeasonWPAllocationID() {
		return this.seasonWPAllocationID;
	}

	public void setSeasonWPAllocationID(int seasonWPAllocationID) {
		this.seasonWPAllocationID = seasonWPAllocationID;
	}

	public int getMaxPax() {
		return this.maxPax;
	}

	public void setMaxPax(int maxPax) {
		this.maxPax = maxPax;
	}

	public DepartmentProgramOption getDepartmentprogramoption() {
		return this.departmentprogramoption;
	}

	public void setDepartmentprogramoption(DepartmentProgramOption departmentprogramoption) {
		this.departmentprogramoption = departmentprogramoption;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

}