package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the departmentprogramoptions database table.
 * 
 */
@Entity
@Table(name="departmentprogramoptions")
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
	private DepartmentProgram departmentprogram;

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

	public DepartmentProgram getDepartmentprogram() {
		return this.departmentprogram;
	}

	public void setDepartmentprogram(DepartmentProgram departmentprogram) {
		this.departmentprogram = departmentprogram;
	}
}