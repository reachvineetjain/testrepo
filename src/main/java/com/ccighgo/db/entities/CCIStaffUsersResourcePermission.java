package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the CCIStaffUsersResourcePermissions database table.
 * 
 */
@Entity
@Table(name="CCIStaffUsersResourcePermissions")
@NamedQuery(name="CCIStaffUsersResourcePermission.findAll", query="SELECT c FROM CCIStaffUsersResourcePermission c")
public class CCIStaffUsersResourcePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int cciStaffUsersResourcePermissionID;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to CCIStaffUser
	@ManyToOne
	@JoinColumn(name="cciStaffUserId", nullable=false)
	private CCIStaffUser ccistaffUser;

	//bi-directional many-to-one association to DepartmentResourceGroup
	@ManyToOne
	@JoinColumn(name="departmentResourceGroupId", nullable=false)
	private DepartmentResourceGroup departmentResourceGroup;

	//bi-directional many-to-one association to ResourceAction
	@ManyToOne
	@JoinColumn(name="resourceActionId", nullable=false)
	private ResourceAction resourceAction;

	//bi-directional many-to-one association to ResourcePermission
	@ManyToOne
	@JoinColumn(name="resourcePermissionId", nullable=false)
	private ResourcePermission resourcePermission;

	public CCIStaffUsersResourcePermission() {
	}

	public int getCciStaffUsersResourcePermissionID() {
		return this.cciStaffUsersResourcePermissionID;
	}

	public void setCciStaffUsersResourcePermissionID(int cciStaffUsersResourcePermissionID) {
		this.cciStaffUsersResourcePermissionID = cciStaffUsersResourcePermissionID;
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

	public CCIStaffUser getCcistaffUser() {
		return this.ccistaffUser;
	}

	public void setCcistaffUser(CCIStaffUser ccistaffUser) {
		this.ccistaffUser = ccistaffUser;
	}

	public DepartmentResourceGroup getDepartmentResourceGroup() {
		return this.departmentResourceGroup;
	}

	public void setDepartmentResourceGroup(DepartmentResourceGroup departmentResourceGroup) {
		this.departmentResourceGroup = departmentResourceGroup;
	}

	public ResourceAction getResourceAction() {
		return this.resourceAction;
	}

	public void setResourceAction(ResourceAction resourceAction) {
		this.resourceAction = resourceAction;
	}

	public ResourcePermission getResourcePermission() {
		return this.resourcePermission;
	}

	public void setResourcePermission(ResourcePermission resourcePermission) {
		this.resourcePermission = resourcePermission;
	}

}