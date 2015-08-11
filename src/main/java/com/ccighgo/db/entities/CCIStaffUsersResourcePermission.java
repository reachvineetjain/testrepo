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

	@EmbeddedId
	private CCIStaffUsersResourcePermissionPK id;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	//bi-directional many-to-one association to CCIStaffUser
	@ManyToOne
	@JoinColumn(name="cciStaffUserId")
	private CCIStaffUser ccistaffUser;

	//bi-directional many-to-one association to DepartmentResourceGroup
	@ManyToOne
	@JoinColumn(name="departmentResourceGroupId")
	private DepartmentResourceGroup departmentResourceGroup;

	//bi-directional many-to-one association to ResourceAction
	@ManyToOne
	@JoinColumn(name="resourceActionId")
	private ResourceAction resourceAction;

	//bi-directional many-to-one association to ResourcePermission
	@ManyToOne
	@JoinColumn(name="resourcePermissionId")
	private ResourcePermission resourcePermission;

	public CCIStaffUsersResourcePermission() {
	}

	public CCIStaffUsersResourcePermissionPK getId() {
		return this.id;
	}

	public void setId(CCIStaffUsersResourcePermissionPK id) {
		this.id = id;
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