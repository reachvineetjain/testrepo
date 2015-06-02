package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the departmentprograms database table.
 * 
 */
@Entity
@Table(name="departmentprograms")
@NamedQuery(name="DepartmentProgram.findAll", query="SELECT d FROM DepartmentProgram d")
public class DepartmentProgram implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int programID;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(length=100)
	private String description;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(nullable=false, length=50)
	private String program;

	//bi-directional many-to-one association to CciStaffUserProgram
	@OneToMany(mappedBy="departmentprogram")
	private List<CciStaffUserProgram> ccistaffuserprograms;

	//bi-directional many-to-one association to DepartmentProgramOption
	@OneToMany(mappedBy="departmentprogram")
	private List<DepartmentProgramOption> departmentprogramoptions;

	//bi-directional many-to-one association to Departments
	@ManyToOne
	@JoinColumn(name="departmentID", nullable=false)
	private Departments department;

	public DepartmentProgram() {
	}

	public int getProgramID() {
		return this.programID;
	}

	public void setProgramID(int programID) {
		this.programID = programID;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getProgram() {
		return this.program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public List<CciStaffUserProgram> getCcistaffuserprograms() {
		return this.ccistaffuserprograms;
	}

	public void setCcistaffuserprograms(List<CciStaffUserProgram> ccistaffuserprograms) {
		this.ccistaffuserprograms = ccistaffuserprograms;
	}

	public CciStaffUserProgram addCcistaffuserprogram(CciStaffUserProgram ccistaffuserprogram) {
		getCcistaffuserprograms().add(ccistaffuserprogram);
		ccistaffuserprogram.setDepartmentprogram(this);

		return ccistaffuserprogram;
	}

	public CciStaffUserProgram removeCcistaffuserprogram(CciStaffUserProgram ccistaffuserprogram) {
		getCcistaffuserprograms().remove(ccistaffuserprogram);
		ccistaffuserprogram.setDepartmentprogram(null);

		return ccistaffuserprogram;
	}

	public List<DepartmentProgramOption> getDepartmentprogramoptions() {
		return this.departmentprogramoptions;
	}

	public void setDepartmentprogramoptions(List<DepartmentProgramOption> departmentprogramoptions) {
		this.departmentprogramoptions = departmentprogramoptions;
	}

	public DepartmentProgramOption addDepartmentprogramoption(DepartmentProgramOption departmentprogramoption) {
		getDepartmentprogramoptions().add(departmentprogramoption);
		departmentprogramoption.setDepartmentprogram(this);

		return departmentprogramoption;
	}

	public DepartmentProgramOption removeDepartmentprogramoption(DepartmentProgramOption departmentprogramoption) {
		getDepartmentprogramoptions().remove(departmentprogramoption);
		departmentprogramoption.setDepartmentprogram(null);

		return departmentprogramoption;
	}

	public Departments getDepartment() {
		return this.department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

}