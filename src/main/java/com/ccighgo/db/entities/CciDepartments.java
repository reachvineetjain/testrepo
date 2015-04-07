package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the cci_departments database table.
 * 
 */
@Entity
@Table(name="cci_departments")
@NamedQuery(name="CciDepartment.findAll", query="SELECT c FROM CciDepartments c")
public class CciDepartments implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=10)
	private String acronym;

	@Column(length=1)
	private String active;

	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false, length=50)
	private String departmentName;

	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to CciDepartmentProgram
	@OneToMany(mappedBy="cciDepartment")
	private List<CciDepartmentProgram> cciDepartmentPrograms;

	//bi-directional many-to-one association to CciDepartmentalFunction
	@OneToMany(mappedBy="cciDepartment")
	private List<CciDepartmentalFunction> cciDepartmentalFunctions;

	//bi-directional many-to-one association to CciDepartmentalProcess
	@OneToMany(mappedBy="cciDepartment")
	private List<CciDepartmentalProcess> cciDepartmentalProcesses;

	//bi-directional many-to-one association to CciProgram
	@OneToMany(mappedBy="cciDepartment")
	private List<CciProgram> cciPrograms;

	public CciDepartments() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAcronym() {
		return this.acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
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

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public List<CciDepartmentProgram> getCciDepartmentPrograms() {
		return this.cciDepartmentPrograms;
	}

	public void setCciDepartmentPrograms(List<CciDepartmentProgram> cciDepartmentPrograms) {
		this.cciDepartmentPrograms = cciDepartmentPrograms;
	}

	public CciDepartmentProgram addCciDepartmentProgram(CciDepartmentProgram cciDepartmentProgram) {
		getCciDepartmentPrograms().add(cciDepartmentProgram);
		cciDepartmentProgram.setCciDepartment(this);

		return cciDepartmentProgram;
	}

	public CciDepartmentProgram removeCciDepartmentProgram(CciDepartmentProgram cciDepartmentProgram) {
		getCciDepartmentPrograms().remove(cciDepartmentProgram);
		cciDepartmentProgram.setCciDepartment(null);

		return cciDepartmentProgram;
	}

	public List<CciDepartmentalFunction> getCciDepartmentalFunctions() {
		return this.cciDepartmentalFunctions;
	}

	public void setCciDepartmentalFunctions(List<CciDepartmentalFunction> cciDepartmentalFunctions) {
		this.cciDepartmentalFunctions = cciDepartmentalFunctions;
	}

	public CciDepartmentalFunction addCciDepartmentalFunction(CciDepartmentalFunction cciDepartmentalFunction) {
		getCciDepartmentalFunctions().add(cciDepartmentalFunction);
		cciDepartmentalFunction.setCciDepartment(this);

		return cciDepartmentalFunction;
	}

	public CciDepartmentalFunction removeCciDepartmentalFunction(CciDepartmentalFunction cciDepartmentalFunction) {
		getCciDepartmentalFunctions().remove(cciDepartmentalFunction);
		cciDepartmentalFunction.setCciDepartment(null);

		return cciDepartmentalFunction;
	}

	public List<CciDepartmentalProcess> getCciDepartmentalProcesses() {
		return this.cciDepartmentalProcesses;
	}

	public void setCciDepartmentalProcesses(List<CciDepartmentalProcess> cciDepartmentalProcesses) {
		this.cciDepartmentalProcesses = cciDepartmentalProcesses;
	}

	public CciDepartmentalProcess addCciDepartmentalProcess(CciDepartmentalProcess cciDepartmentalProcess) {
		getCciDepartmentalProcesses().add(cciDepartmentalProcess);
		cciDepartmentalProcess.setCciDepartment(this);

		return cciDepartmentalProcess;
	}

	public CciDepartmentalProcess removeCciDepartmentalProcess(CciDepartmentalProcess cciDepartmentalProcess) {
		getCciDepartmentalProcesses().remove(cciDepartmentalProcess);
		cciDepartmentalProcess.setCciDepartment(null);

		return cciDepartmentalProcess;
	}

	public List<CciProgram> getCciPrograms() {
		return this.cciPrograms;
	}

	public void setCciPrograms(List<CciProgram> cciPrograms) {
		this.cciPrograms = cciPrograms;
	}

	public CciProgram addCciProgram(CciProgram cciProgram) {
		getCciPrograms().add(cciProgram);
		cciProgram.setCciDepartment(this);

		return cciProgram;
	}

	public CciProgram removeCciProgram(CciProgram cciProgram) {
		getCciPrograms().remove(cciProgram);
		cciProgram.setCciDepartment(null);

		return cciProgram;
	}

}