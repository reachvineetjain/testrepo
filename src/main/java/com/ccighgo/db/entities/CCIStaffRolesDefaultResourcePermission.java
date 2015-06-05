package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the CCIStaffRolesDefaultResourcePermissions database table.
 * 
 */
@Entity
@Table(name="CCIStaffRolesDefaultResourcePermissions")
@NamedQuery(name="CCIStaffRolesDefaultResourcePermission.findAll", query="SELECT c FROM CCIStaffRolesDefaultResourcePermission c")
public class CCIStaffRolesDefaultResourcePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int cciStaffRolesDefaultResourcePermissionId;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to CCIStaffRolesDepartment
	@ManyToOne
	@JoinColumn(name="cciStaffRolesDepartmentId", nullable=false)
	private CCIStaffRolesDepartment ccistaffRolesDepartment;

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

	public CCIStaffRolesDefaultResourcePermission() {
	}

	public int getCciStaffRolesDefaultResourcePermissionId() {
		return this.cciStaffRolesDefaultResourcePermissionId;
	}

	public void setCciStaffRolesDefaultResourcePermissionId(int cciStaffRolesDefaultResourcePermissionId) {
		this.cciStaffRolesDefaultResourcePermissionId = cciStaffRolesDefaultResourcePermissionId;
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

	public CCIStaffRolesDepartment getCcistaffRolesDepartment() {
		return this.ccistaffRolesDepartment;
	}

	public void setCcistaffRolesDepartment(CCIStaffRolesDepartment ccistaffRolesDepartment) {
		this.ccistaffRolesDepartment = ccistaffRolesDepartment;
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