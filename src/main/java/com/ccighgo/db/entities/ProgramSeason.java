package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ProgramSeasons database table.
 * 
 */
@Table(name="ProgramSeasons")
@NamedQuery(name="ProgramSeason.findAll", query="SELECT p FROM ProgramSeason p")
public class ProgramSeason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable=false)
	private Integer departmentProgramId;

	@Column(length=55)
	private String programName;

	private Integer programStatusId;

	public ProgramSeason() {
	}

	public Integer getDepartmentProgramId() {
		return this.departmentProgramId;
	}

	public void setDepartmentProgramId(Integer departmentProgramId) {
		this.departmentProgramId = departmentProgramId;
	}

	public String getProgramName() {
		return this.programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public Integer getProgramStatusId() {
		return this.programStatusId;
	}

	public void setProgramStatusId(Integer programStatusId) {
		this.programStatusId = programStatusId;
	}

}