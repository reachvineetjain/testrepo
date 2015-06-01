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
@NamedQuery(name="Departments.findAll", query="SELECT d FROM Departments d")
public class Departments implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int departmentID;

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

	//bi-directional many-to-one association to CciStaffRolesDepartment
	@OneToMany(mappedBy="department")
	private List<CciStaffRolesDepartment> ccistaffrolesdepartments;

	//bi-directional many-to-one association to DepartmentFunction
	@OneToMany(mappedBy="department")
	private List<DepartmentFunction> departmentfunctions;

	//bi-directional many-to-one association to DepartmentProgram
	@OneToMany(mappedBy="department")
	private List<DepartmentProgram> departmentprograms;

	//bi-directional many-to-one association to DepartmentResourceGroup
	@OneToMany(mappedBy="department")
	private List<DepartmentResourceGroup> departmentresourcegroups;

	//bi-directional many-to-one association to Season
	@OneToMany(mappedBy="department")
	private List<Season> seasons;

	public Departments() {
	}

	public int getDepartmentID() {
		return this.departmentID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
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

	public List<CciStaffRolesDepartment> getCcistaffrolesdepartments() {
		return this.ccistaffrolesdepartments;
	}

	public void setCcistaffrolesdepartments(List<CciStaffRolesDepartment> ccistaffrolesdepartments) {
		this.ccistaffrolesdepartments = ccistaffrolesdepartments;
	}

	public CciStaffRolesDepartment addCcistaffrolesdepartment(CciStaffRolesDepartment ccistaffrolesdepartment) {
		getCcistaffrolesdepartments().add(ccistaffrolesdepartment);
		ccistaffrolesdepartment.setDepartment(this);

		return ccistaffrolesdepartment;
	}

	public CciStaffRolesDepartment removeCcistaffrolesdepartment(CciStaffRolesDepartment ccistaffrolesdepartment) {
		getCcistaffrolesdepartments().remove(ccistaffrolesdepartment);
		ccistaffrolesdepartment.setDepartment(null);

		return ccistaffrolesdepartment;
	}

	public List<DepartmentFunction> getDepartmentfunctions() {
		return this.departmentfunctions;
	}

	public void setDepartmentfunctions(List<DepartmentFunction> departmentfunctions) {
		this.departmentfunctions = departmentfunctions;
	}

	public DepartmentFunction addDepartmentfunction(DepartmentFunction departmentfunction) {
		getDepartmentfunctions().add(departmentfunction);
		departmentfunction.setDepartment(this);

		return departmentfunction;
	}

	public DepartmentFunction removeDepartmentfunction(DepartmentFunction departmentfunction) {
		getDepartmentfunctions().remove(departmentfunction);
		departmentfunction.setDepartment(null);

		return departmentfunction;
	}

	public List<DepartmentProgram> getDepartmentprograms() {
		return this.departmentprograms;
	}

	public void setDepartmentprograms(List<DepartmentProgram> departmentprograms) {
		this.departmentprograms = departmentprograms;
	}

	public DepartmentProgram addDepartmentprogram(DepartmentProgram departmentprogram) {
		getDepartmentprograms().add(departmentprogram);
		departmentprogram.setDepartment(this);

		return departmentprogram;
	}

	public DepartmentProgram removeDepartmentprogram(DepartmentProgram departmentprogram) {
		getDepartmentprograms().remove(departmentprogram);
		departmentprogram.setDepartment(null);

		return departmentprogram;
	}

	public List<DepartmentResourceGroup> getDepartmentresourcegroups() {
		return this.departmentresourcegroups;
	}

	public void setDepartmentresourcegroups(List<DepartmentResourceGroup> departmentresourcegroups) {
		this.departmentresourcegroups = departmentresourcegroups;
	}

	public DepartmentResourceGroup addDepartmentresourcegroup(DepartmentResourceGroup departmentresourcegroup) {
		getDepartmentresourcegroups().add(departmentresourcegroup);
		departmentresourcegroup.setDepartment(this);

		return departmentresourcegroup;
	}

	public DepartmentResourceGroup removeDepartmentresourcegroup(DepartmentResourceGroup departmentresourcegroup) {
		getDepartmentresourcegroups().remove(departmentresourcegroup);
		departmentresourcegroup.setDepartment(null);

		return departmentresourcegroup;
	}

	public List<Season> getSeasons() {
		return this.seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}

	public Season addSeason(Season season) {
		getSeasons().add(season);
		season.setDepartment(this);

		return season;
	}

	public Season removeSeason(Season season) {
		getSeasons().remove(season);
		season.setDepartment(null);

		return season;
	}

}