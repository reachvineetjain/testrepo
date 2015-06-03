package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the DepartmentProgramOptions database table.
 * 
 */
@Entity
@Table(name="DepartmentProgramOptions")
@NamedQuery(name="DepartmentProgramOption.findAll", query="SELECT d FROM DepartmentProgramOption d")
public class DepartmentProgramOption implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int programOptionID;

	@Column(nullable=false, length=50)
	private String programOption;

	@Column(nullable=false, length=10)
	private String programOptionCode;

	//bi-directional many-to-one association to DepartmentProgram
	@ManyToOne
	@JoinColumn(name="programID", nullable=false)
	private DepartmentProgram departmentProgram;

	//bi-directional many-to-one association to SeasonHSPAllocatin
	@OneToMany(mappedBy="departmentProgramOption")
	private List<SeasonHSPAllocatin> seasonHspallocatins;

	//bi-directional many-to-one association to SeasonWPAllocation
	@OneToMany(mappedBy="departmentProgramOption")
	private List<SeasonWPAllocation> seasonWpallocations;

	public DepartmentProgramOption() {
	}

	public int getProgramOptionID() {
		return this.programOptionID;
	}

	public void setProgramOptionID(int programOptionID) {
		this.programOptionID = programOptionID;
	}

	public String getProgramOption() {
		return this.programOption;
	}

	public void setProgramOption(String programOption) {
		this.programOption = programOption;
	}

	public String getProgramOptionCode() {
		return this.programOptionCode;
	}

	public void setProgramOptionCode(String programOptionCode) {
		this.programOptionCode = programOptionCode;
	}

	public DepartmentProgram getDepartmentProgram() {
		return this.departmentProgram;
	}

	public void setDepartmentProgram(DepartmentProgram departmentProgram) {
		this.departmentProgram = departmentProgram;
	}

	public List<SeasonHSPAllocatin> getSeasonHspallocatins() {
		return this.seasonHspallocatins;
	}

	public void setSeasonHspallocatins(List<SeasonHSPAllocatin> seasonHspallocatins) {
		this.seasonHspallocatins = seasonHspallocatins;
	}

	public SeasonHSPAllocatin addSeasonHspallocatin(SeasonHSPAllocatin seasonHspallocatin) {
		getSeasonHspallocatins().add(seasonHspallocatin);
		seasonHspallocatin.setDepartmentProgramOption(this);

		return seasonHspallocatin;
	}

	public SeasonHSPAllocatin removeSeasonHspallocatin(SeasonHSPAllocatin seasonHspallocatin) {
		getSeasonHspallocatins().remove(seasonHspallocatin);
		seasonHspallocatin.setDepartmentProgramOption(null);

		return seasonHspallocatin;
	}

	public List<SeasonWPAllocation> getSeasonWpallocations() {
		return this.seasonWpallocations;
	}

	public void setSeasonWpallocations(List<SeasonWPAllocation> seasonWpallocations) {
		this.seasonWpallocations = seasonWpallocations;
	}

	public SeasonWPAllocation addSeasonWpallocation(SeasonWPAllocation seasonWpallocation) {
		getSeasonWpallocations().add(seasonWpallocation);
		seasonWpallocation.setDepartmentProgramOption(this);

		return seasonWpallocation;
	}

	public SeasonWPAllocation removeSeasonWpallocation(SeasonWPAllocation seasonWpallocation) {
		getSeasonWpallocations().remove(seasonWpallocation);
		seasonWpallocation.setDepartmentProgramOption(null);

		return seasonWpallocation;
	}

}