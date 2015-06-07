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
	private int departmentProgramOptionId;

	@Column(nullable=false, length=10)
	private String programOptionCode;

	@Column(nullable=false, length=50)
	private String programOptionName;

	//bi-directional many-to-one association to DepartmentProgram
	@ManyToOne
	@JoinColumn(name="departmentProgramId", nullable=false)
	private DepartmentProgram departmentProgram;

	//bi-directional many-to-one association to SeasonHSPAllocation
	@OneToMany(mappedBy="departmentProgramOption")
	private List<SeasonHSPAllocation> seasonHspallocations;

	//bi-directional many-to-one association to SeasonWPAllocation
	@OneToMany(mappedBy="departmentProgramOption")
	private List<SeasonWPAllocation> seasonWpallocations;

	public DepartmentProgramOption() {
	}

	public int getDepartmentProgramOptionId() {
		return this.departmentProgramOptionId;
	}

	public void setDepartmentProgramOptionId(int departmentProgramOptionId) {
		this.departmentProgramOptionId = departmentProgramOptionId;
	}

	public String getProgramOptionCode() {
		return this.programOptionCode;
	}

	public void setProgramOptionCode(String programOptionCode) {
		this.programOptionCode = programOptionCode;
	}

	public String getProgramOptionName() {
		return this.programOptionName;
	}

	public void setProgramOptionName(String programOptionName) {
		this.programOptionName = programOptionName;
	}

	public DepartmentProgram getDepartmentProgram() {
		return this.departmentProgram;
	}

	public void setDepartmentProgram(DepartmentProgram departmentProgram) {
		this.departmentProgram = departmentProgram;
	}

	public List<SeasonHSPAllocation> getSeasonHspallocations() {
		return this.seasonHspallocations;
	}

	public void setSeasonHspallocations(List<SeasonHSPAllocation> seasonHspallocations) {
		this.seasonHspallocations = seasonHspallocations;
	}

	public SeasonHSPAllocation addSeasonHspallocation(SeasonHSPAllocation seasonHspallocation) {
		getSeasonHspallocations().add(seasonHspallocation);
		seasonHspallocation.setDepartmentProgramOption(this);

		return seasonHspallocation;
	}

	public SeasonHSPAllocation removeSeasonHspallocation(SeasonHSPAllocation seasonHspallocation) {
		getSeasonHspallocations().remove(seasonHspallocation);
		seasonHspallocation.setDepartmentProgramOption(null);

		return seasonHspallocation;
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