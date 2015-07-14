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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int cciStaffRoleId;

	@Column(nullable=false, length=50)
	private String cciStaffRoleName;

	@Column(nullable=false)
	private int createdBy;

	private Timestamp createdOn;

	private int hierarchy;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to CCIStaffRolesDepartment
	@OneToMany(mappedBy="ccistaffRole", fetch=FetchType.EAGER)
   @Fetch(value = FetchMode.SUBSELECT)
	private List<CCIStaffRolesDepartment> ccistaffRolesDepartments;

	//bi-directional many-to-one association to CCIStaffUsersCCIStaffRole
	@OneToMany(mappedBy="ccistaffRole")
	private List<CCIStaffUsersCCIStaffRole> ccistaffUsersCcistaffRoles;

	public CCIStaffRole() {
	}

	public int getCciStaffRoleId() {
		return this.cciStaffRoleId;
	}

	public void setCciStaffRoleId(int cciStaffRoleId) {
		this.cciStaffRoleId = cciStaffRoleId;
	}

	public String getCciStaffRoleName() {
		return this.cciStaffRoleName;
	}

	public void setCciStaffRoleName(String cciStaffRoleName) {
		this.cciStaffRoleName = cciStaffRoleName;
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

	public int getHierarchy() {
		return this.hierarchy;
	}

	public void setHierarchy(int hierarchy) {
		this.hierarchy = hierarchy;
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