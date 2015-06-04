package com.ccighgo.db.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the DepartmentPrograms database table.
 * 
 */
@Entity
@Table(name="DepartmentPrograms")
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

	//bi-directional many-to-one association to CCIStaffUserProgram
	@OneToMany(mappedBy="departmentProgram")
	private List<CCIStaffUserProgram> ccistaffUserPrograms;

	//bi-directional many-to-one association to DepartmentProgramOption
	@OneToMany(mappedBy="departmentProgram", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
	private List<DepartmentProgramOption> departmentProgramOptions;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="departmentID", nullable=false)
	private Department department;

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

	public List<CCIStaffUserProgram> getCcistaffUserPrograms() {
		return this.ccistaffUserPrograms;
	}

	public void setCcistaffUserPrograms(List<CCIStaffUserProgram> ccistaffUserPrograms) {
		this.ccistaffUserPrograms = ccistaffUserPrograms;
	}

	public CCIStaffUserProgram addCcistaffUserProgram(CCIStaffUserProgram ccistaffUserProgram) {
		getCcistaffUserPrograms().add(ccistaffUserProgram);
		ccistaffUserProgram.setDepartmentProgram(this);

		return ccistaffUserProgram;
	}

	public CCIStaffUserProgram removeCcistaffUserProgram(CCIStaffUserProgram ccistaffUserProgram) {
		getCcistaffUserPrograms().remove(ccistaffUserProgram);
		ccistaffUserProgram.setDepartmentProgram(null);

		return ccistaffUserProgram;
	}

	public List<DepartmentProgramOption> getDepartmentProgramOptions() {
		return this.departmentProgramOptions;
	}

	public void setDepartmentProgramOptions(List<DepartmentProgramOption> departmentProgramOptions) {
		this.departmentProgramOptions = departmentProgramOptions;
	}

	public DepartmentProgramOption addDepartmentProgramOption(DepartmentProgramOption departmentProgramOption) {
		getDepartmentProgramOptions().add(departmentProgramOption);
		departmentProgramOption.setDepartmentProgram(this);

		return departmentProgramOption;
	}

	public DepartmentProgramOption removeDepartmentProgramOption(DepartmentProgramOption departmentProgramOption) {
		getDepartmentProgramOptions().remove(departmentProgramOption);
		departmentProgramOption.setDepartmentProgram(null);

		return departmentProgramOption;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}