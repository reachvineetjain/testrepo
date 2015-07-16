package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the SeasonProgramUpdateLog database table.
 * 
 */
@Entity
@Table(name="SeasonProgramUpdateLog")
@NamedQuery(name="SeasonProgramUpdateLog.findAll", query="SELECT s FROM SeasonProgramUpdateLog s")
public class SeasonProgramUpdateLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int updateProgramLogId;

	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Lob
	private String updateLogObject;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId")
	private Season season;

	//bi-directional many-to-one association to DepartmentProgram
	@ManyToOne
	@JoinColumn(name="departmentProgramId")
	private DepartmentProgram departmentProgram;

	public SeasonProgramUpdateLog() {
	}

	public int getUpdateProgramLogId() {
		return this.updateProgramLogId;
	}

	public void setUpdateProgramLogId(int updateProgramLogId) {
		this.updateProgramLogId = updateProgramLogId;
	}

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getUpdateLogObject() {
		return this.updateLogObject;
	}

	public void setUpdateLogObject(String updateLogObject) {
		this.updateLogObject = updateLogObject;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public DepartmentProgram getDepartmentProgram() {
		return this.departmentProgram;
	}

	public void setDepartmentProgram(DepartmentProgram departmentProgram) {
		this.departmentProgram = departmentProgram;
	}

}