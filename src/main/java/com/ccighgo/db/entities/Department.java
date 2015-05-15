package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the departments database table.
 * 
 */
@Entity
@Table(name="departments")
@NamedQuery(name="Department.findAll", query="SELECT d FROM Department d")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=10)
	private String acronym;

	@Column(nullable=false)
	private byte active;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false, length=50)
	private String departmentName;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to CcistaffrolesDepartment
	@OneToMany(mappedBy="department")
	private List<CcistaffrolesDepartment> ccistaffrolesDepartments;

	//bi-directional many-to-one association to CcistaffusersDepartment
	@OneToMany(mappedBy="department")
	private List<CcistaffusersDepartment> ccistaffusersDepartments;

	//bi-directional many-to-one association to Departmentfunction
	@OneToMany(mappedBy="department")
	private List<Departmentfunction> departmentfunctions;

	//bi-directional many-to-one association to Departmentprogram
	@OneToMany(mappedBy="department")
	private List<Departmentprogram> departmentprograms;

	//bi-directional many-to-many association to Resourcegroup
	@ManyToMany(mappedBy="departments")
	private List<Resourcegroup> resourcegroups;

	public Department() {
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

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
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

	public List<CcistaffrolesDepartment> getCcistaffrolesDepartments() {
		return this.ccistaffrolesDepartments;
	}

	public void setCcistaffrolesDepartments(List<CcistaffrolesDepartment> ccistaffrolesDepartments) {
		this.ccistaffrolesDepartments = ccistaffrolesDepartments;
	}

	public CcistaffrolesDepartment addCcistaffrolesDepartment(CcistaffrolesDepartment ccistaffrolesDepartment) {
		getCcistaffrolesDepartments().add(ccistaffrolesDepartment);
		ccistaffrolesDepartment.setDepartment(this);

		return ccistaffrolesDepartment;
	}

	public CcistaffrolesDepartment removeCcistaffrolesDepartment(CcistaffrolesDepartment ccistaffrolesDepartment) {
		getCcistaffrolesDepartments().remove(ccistaffrolesDepartment);
		ccistaffrolesDepartment.setDepartment(null);

		return ccistaffrolesDepartment;
	}

	public List<CcistaffusersDepartment> getCcistaffusersDepartments() {
		return this.ccistaffusersDepartments;
	}

	public void setCcistaffusersDepartments(List<CcistaffusersDepartment> ccistaffusersDepartments) {
		this.ccistaffusersDepartments = ccistaffusersDepartments;
	}

	public CcistaffusersDepartment addCcistaffusersDepartment(CcistaffusersDepartment ccistaffusersDepartment) {
		getCcistaffusersDepartments().add(ccistaffusersDepartment);
		ccistaffusersDepartment.setDepartment(this);

		return ccistaffusersDepartment;
	}

	public CcistaffusersDepartment removeCcistaffusersDepartment(CcistaffusersDepartment ccistaffusersDepartment) {
		getCcistaffusersDepartments().remove(ccistaffusersDepartment);
		ccistaffusersDepartment.setDepartment(null);

		return ccistaffusersDepartment;
	}

	public List<Departmentfunction> getDepartmentfunctions() {
		return this.departmentfunctions;
	}

	public void setDepartmentfunctions(List<Departmentfunction> departmentfunctions) {
		this.departmentfunctions = departmentfunctions;
	}

	public Departmentfunction addDepartmentfunction(Departmentfunction departmentfunction) {
		getDepartmentfunctions().add(departmentfunction);
		departmentfunction.setDepartment(this);

		return departmentfunction;
	}

	public Departmentfunction removeDepartmentfunction(Departmentfunction departmentfunction) {
		getDepartmentfunctions().remove(departmentfunction);
		departmentfunction.setDepartment(null);

		return departmentfunction;
	}

	public List<Departmentprogram> getDepartmentprograms() {
		return this.departmentprograms;
	}

	public void setDepartmentprograms(List<Departmentprogram> departmentprograms) {
		this.departmentprograms = departmentprograms;
	}

	public Departmentprogram addDepartmentprogram(Departmentprogram departmentprogram) {
		getDepartmentprograms().add(departmentprogram);
		departmentprogram.setDepartment(this);

		return departmentprogram;
	}

	public Departmentprogram removeDepartmentprogram(Departmentprogram departmentprogram) {
		getDepartmentprograms().remove(departmentprogram);
		departmentprogram.setDepartment(null);

		return departmentprogram;
	}

	public List<Resourcegroup> getResourcegroups() {
		return this.resourcegroups;
	}

	public void setResourcegroups(List<Resourcegroup> resourcegroups) {
		this.resourcegroups = resourcegroups;
	}

}