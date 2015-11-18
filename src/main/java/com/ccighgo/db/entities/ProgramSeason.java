package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ProgramSeasons database table.
 * 
 */
//@Entity
@Table(name="ProgramSeasons")
@NamedQuery(name="ProgramSeason.findAll", query="SELECT p FROM ProgramSeason p")
public class ProgramSeason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	private Date augFullYearStartDate;

	@Column(nullable=false)
	private int departmentProgramId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date firstSemStartDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date janFullYearStartDate;

	@Column(length=19)
	private String latestStartDate;

	@Column(length=50)
	private String programName;

	private int programStatusId;

	@Column(nullable=false)
	private int seasonId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date secondSemStartDate;

	public ProgramSeason() {
	}

	public Date getAugFullYearStartDate() {
		return this.augFullYearStartDate;
	}

	public void setAugFullYearStartDate(Date augFullYearStartDate) {
		this.augFullYearStartDate = augFullYearStartDate;
	}

	public int getDepartmentProgramId() {
		return this.departmentProgramId;
	}

	public void setDepartmentProgramId(int departmentProgramId) {
		this.departmentProgramId = departmentProgramId;
	}

	public Date getFirstSemStartDate() {
		return this.firstSemStartDate;
	}

	public void setFirstSemStartDate(Date firstSemStartDate) {
		this.firstSemStartDate = firstSemStartDate;
	}

	public Date getJanFullYearStartDate() {
		return this.janFullYearStartDate;
	}

	public void setJanFullYearStartDate(Date janFullYearStartDate) {
		this.janFullYearStartDate = janFullYearStartDate;
	}

	public String getLatestStartDate() {
		return this.latestStartDate;
	}

	public void setLatestStartDate(String latestStartDate) {
		this.latestStartDate = latestStartDate;
	}

	public String getProgramName() {
		return this.programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public int getProgramStatusId() {
		return this.programStatusId;
	}

	public void setProgramStatusId(int programStatusId) {
		this.programStatusId = programStatusId;
	}

	public int getSeasonId() {
		return this.seasonId;
	}

	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}

	public Date getSecondSemStartDate() {
		return this.secondSemStartDate;
	}

	public void setSecondSemStartDate(Date secondSemStartDate) {
		this.secondSemStartDate = secondSemStartDate;
	}

}