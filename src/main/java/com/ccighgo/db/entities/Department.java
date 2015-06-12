package com.ccighgo.db.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the Departments database table.
 * 
 */
@Entity
@Table(name="Departments")
@NamedQuery(name="Department.findAll", query="SELECT d FROM Department d")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int departmentId;

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

	//bi-directional many-to-one association to CCIStaffRolesDepartment
	@OneToMany(mappedBy="department")
	private List<CCIStaffRolesDepartment> ccistaffRolesDepartments;

	//bi-directional many-to-one association to DepartmentFunction
	@OneToMany(mappedBy="department")
	private List<DepartmentFunction> departmentFunctions;

	//bi-directional many-to-one association to DepartmentProgram
	@OneToMany(mappedBy="department", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<DepartmentProgram> departmentPrograms;

	//bi-directional many-to-one association to DepartmentResourceGroup
	@OneToMany(mappedBy="department")
	private List<DepartmentResourceGroup> departmentResourceGroups;

	//bi-directional many-to-one association to Season
	@OneToMany(mappedBy="department")
	private List<Season> seasons;

	public Department() {
	}

	public int getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
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

	public List<CCIStaffRolesDepartment> getCcistaffRolesDepartments() {
		return this.ccistaffRolesDepartments;
	}

	public void setCcistaffRolesDepartments(List<CCIStaffRolesDepartment> ccistaffRolesDepartments) {
		this.ccistaffRolesDepartments = ccistaffRolesDepartments;
	}

	public CCIStaffRolesDepartment addCcistaffRolesDepartment(CCIStaffRolesDepartment ccistaffRolesDepartment) {
		getCcistaffRolesDepartments().add(ccistaffRolesDepartment);
		ccistaffRolesDepartment.setDepartment(this);

		return ccistaffRolesDepartment;
	}

	public CCIStaffRolesDepartment removeCcistaffRolesDepartment(CCIStaffRolesDepartment ccistaffRolesDepartment) {
		getCcistaffRolesDepartments().remove(ccistaffRolesDepartment);
		ccistaffRolesDepartment.setDepartment(null);

		return ccistaffRolesDepartment;
	}

	public List<DepartmentFunction> getDepartmentFunctions() {
		return this.departmentFunctions;
	}

	public void setDepartmentFunctions(List<DepartmentFunction> departmentFunctions) {
		this.departmentFunctions = departmentFunctions;
	}

	public DepartmentFunction addDepartmentFunction(DepartmentFunction departmentFunction) {
		getDepartmentFunctions().add(departmentFunction);
		departmentFunction.setDepartment(this);

		return departmentFunction;
	}

	public DepartmentFunction removeDepartmentFunction(DepartmentFunction departmentFunction) {
		getDepartmentFunctions().remove(departmentFunction);
		departmentFunction.setDepartment(null);

		return departmentFunction;
	}

	public List<DepartmentProgram> getDepartmentPrograms() {
		return this.departmentPrograms;
	}

	public void setDepartmentPrograms(List<DepartmentProgram> departmentPrograms) {
		this.departmentPrograms = departmentPrograms;
	}

	public DepartmentProgram addDepartmentProgram(DepartmentProgram departmentProgram) {
		getDepartmentPrograms().add(departmentProgram);
		departmentProgram.setDepartment(this);

		return departmentProgram;
	}

	public DepartmentProgram removeDepartmentProgram(DepartmentProgram departmentProgram) {
		getDepartmentPrograms().remove(departmentProgram);
		departmentProgram.setDepartment(null);

		return departmentProgram;
	}

	public List<DepartmentResourceGroup> getDepartmentResourceGroups() {
		return this.departmentResourceGroups;
	}

	public void setDepartmentResourceGroups(List<DepartmentResourceGroup> departmentResourceGroups) {
		this.departmentResourceGroups = departmentResourceGroups;
	}

	public DepartmentResourceGroup addDepartmentResourceGroup(DepartmentResourceGroup departmentResourceGroup) {
		getDepartmentResourceGroups().add(departmentResourceGroup);
		departmentResourceGroup.setDepartment(this);

		return departmentResourceGroup;
	}

	public DepartmentResourceGroup removeDepartmentResourceGroup(DepartmentResourceGroup departmentResourceGroup) {
		getDepartmentResourceGroups().remove(departmentResourceGroup);
		departmentResourceGroup.setDepartment(null);

		return departmentResourceGroup;
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