package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 * The persistent class for the LookupDepartments database table.
 * 
 */
@Entity
@Table(name="LookupDepartments")
@NamedQuery(name="LookupDepartment.findAll", query="SELECT l FROM LookupDepartment l")
public class LookupDepartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer departmentId;

	@Column(length=10)
	private String acronym;

	@Column(nullable=false)
	private Byte active;

	@Column(nullable=false)
	private Integer createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false, length=50)
	private String departmentName;

	@Column(nullable=false)
	private Byte isVisibleToSeason;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to CCIStaffRolesDepartment
	@OneToMany(mappedBy="lookupDepartment")
	private List<CCIStaffRolesDepartment> ccistaffRolesDepartments;

	//bi-directional many-to-one association to DepartmentProgram
	  @OneToMany(mappedBy = "lookupDepartment", fetch = FetchType.EAGER)
	   @Fetch(value = FetchMode.SUBSELECT)
	private List<DepartmentProgram> departmentPrograms;

	//bi-directional many-to-one association to DepartmentResourceGroup
	@OneToMany(mappedBy="lookupDepartment")
	private List<DepartmentResourceGroup> departmentResourceGroups;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@OneToMany(mappedBy = "lookupDepartment", fetch = FetchType.EAGER)
   @Fetch(value = FetchMode.SUBSELECT)
	private List<LookupDepartmentProgram> lookupDepartmentPrograms;

	//bi-directional many-to-one association to Season
	@OneToMany(mappedBy="lookupDepartment")
	private List<Season> seasons;

	public LookupDepartment() {
	}

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getAcronym() {
		return this.acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
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

	public Byte getIsVisibleToSeason() {
		return this.isVisibleToSeason;
	}

	public void setIsVisibleToSeason(Byte isVisibleToSeason) {
		this.isVisibleToSeason = isVisibleToSeason;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
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
		ccistaffRolesDepartment.setLookupDepartment(this);

		return ccistaffRolesDepartment;
	}

	public CCIStaffRolesDepartment removeCcistaffRolesDepartment(CCIStaffRolesDepartment ccistaffRolesDepartment) {
		getCcistaffRolesDepartments().remove(ccistaffRolesDepartment);
		ccistaffRolesDepartment.setLookupDepartment(null);

		return ccistaffRolesDepartment;
	}

	public List<DepartmentProgram> getDepartmentPrograms() {
		return this.departmentPrograms;
	}

	public void setDepartmentPrograms(List<DepartmentProgram> departmentPrograms) {
		this.departmentPrograms = departmentPrograms;
	}

	public DepartmentProgram addDepartmentProgram(DepartmentProgram departmentProgram) {
		getDepartmentPrograms().add(departmentProgram);
		departmentProgram.setLookupDepartment(this);

		return departmentProgram;
	}

	public DepartmentProgram removeDepartmentProgram(DepartmentProgram departmentProgram) {
		getDepartmentPrograms().remove(departmentProgram);
		departmentProgram.setLookupDepartment(null);

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
		departmentResourceGroup.setLookupDepartment(this);

		return departmentResourceGroup;
	}

	public DepartmentResourceGroup removeDepartmentResourceGroup(DepartmentResourceGroup departmentResourceGroup) {
		getDepartmentResourceGroups().remove(departmentResourceGroup);
		departmentResourceGroup.setLookupDepartment(null);

		return departmentResourceGroup;
	}

	public List<LookupDepartmentProgram> getLookupDepartmentPrograms() {
		return this.lookupDepartmentPrograms;
	}

	public void setLookupDepartmentPrograms(List<LookupDepartmentProgram> lookupDepartmentPrograms) {
		this.lookupDepartmentPrograms = lookupDepartmentPrograms;
	}

	public LookupDepartmentProgram addLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		getLookupDepartmentPrograms().add(lookupDepartmentProgram);
		lookupDepartmentProgram.setLookupDepartment(this);

		return lookupDepartmentProgram;
	}

	public LookupDepartmentProgram removeLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		getLookupDepartmentPrograms().remove(lookupDepartmentProgram);
		lookupDepartmentProgram.setLookupDepartment(null);

		return lookupDepartmentProgram;
	}

	public List<Season> getSeasons() {
		return this.seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}

	public Season addSeason(Season season) {
		getSeasons().add(season);
		season.setLookupDepartment(this);

		return season;
	}

	public Season removeSeason(Season season) {
		getSeasons().remove(season);
		season.setLookupDepartment(null);

		return season;
	}

}