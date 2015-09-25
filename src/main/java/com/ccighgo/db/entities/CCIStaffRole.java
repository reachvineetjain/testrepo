package com.ccighgo.db.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the CCIStaffRoles database table.
 * 
 */
@Entity
@Table(name="CCIStaffRoles")
@NamedQuery(name="CCIStaffRole.findAll", query="SELECT c FROM CCIStaffRole c")
public class CCIStaffRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer cciStaffRoleId;

	@Column(nullable=false, length=50)
	private String cciStaffRoleName;

	@Column(nullable=false)
	private Integer createdBy;

	private Timestamp createdOn;

	private Integer hierarchy;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to CCIStaffRolesDepartment
	@OneToMany(mappedBy = "ccistaffRole", fetch = FetchType.EAGER)
   @Fetch(value = FetchMode.SUBSELECT)
	private List<CCIStaffRolesDepartment> ccistaffRolesDepartments;

	//bi-directional many-to-one association to CCIStaffUsersCCIStaffRole
	@OneToMany(mappedBy="ccistaffRole")
	private List<CCIStaffUsersCCIStaffRole> ccistaffUsersCcistaffRoles;

	public CCIStaffRole() {
	}

	public Integer getCciStaffRoleId() {
		return this.cciStaffRoleId;
	}

	public void setCciStaffRoleId(Integer cciStaffRoleId) {
		this.cciStaffRoleId = cciStaffRoleId;
	}

	public String getCciStaffRoleName() {
		return this.cciStaffRoleName;
	}

	public void setCciStaffRoleName(String cciStaffRoleName) {
		this.cciStaffRoleName = cciStaffRoleName;
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

	public Integer getHierarchy() {
		return this.hierarchy;
	}

	public void setHierarchy(Integer hierarchy) {
		this.hierarchy = hierarchy;
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
		ccistaffRolesDepartment.setCcistaffRole(this);

		return ccistaffRolesDepartment;
	}

	public CCIStaffRolesDepartment removeCcistaffRolesDepartment(CCIStaffRolesDepartment ccistaffRolesDepartment) {
		getCcistaffRolesDepartments().remove(ccistaffRolesDepartment);
		ccistaffRolesDepartment.setCcistaffRole(null);

		return ccistaffRolesDepartment;
	}

	public List<CCIStaffUsersCCIStaffRole> getCcistaffUsersCcistaffRoles() {
		return this.ccistaffUsersCcistaffRoles;
	}

	public void setCcistaffUsersCcistaffRoles(List<CCIStaffUsersCCIStaffRole> ccistaffUsersCcistaffRoles) {
		this.ccistaffUsersCcistaffRoles = ccistaffUsersCcistaffRoles;
	}

	public CCIStaffUsersCCIStaffRole addCcistaffUsersCcistaffRole(CCIStaffUsersCCIStaffRole ccistaffUsersCcistaffRole) {
		getCcistaffUsersCcistaffRoles().add(ccistaffUsersCcistaffRole);
		ccistaffUsersCcistaffRole.setCcistaffRole(this);

		return ccistaffUsersCcistaffRole;
	}

	public CCIStaffUsersCCIStaffRole removeCcistaffUsersCcistaffRole(CCIStaffUsersCCIStaffRole ccistaffUsersCcistaffRole) {
		getCcistaffUsersCcistaffRoles().remove(ccistaffUsersCcistaffRole);
		ccistaffUsersCcistaffRole.setCcistaffRole(null);

		return ccistaffUsersCcistaffRole;
	}

}