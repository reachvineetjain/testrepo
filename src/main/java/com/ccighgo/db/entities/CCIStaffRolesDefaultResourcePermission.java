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

	@EmbeddedId
	private CCIStaffRolesDefaultResourcePermissionPK id;

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
	@JoinColumn(name="cciStaffDepartmentRoleId", nullable=false, insertable=false, updatable=false)
	private CCIStaffRolesDepartment ccistaffRolesDepartment;

	//bi-directional many-to-one association to ResourcePermission
	@ManyToOne
	@JoinColumn(name="resourcePermissionsId", nullable=false, insertable=false, updatable=false)
	private ResourcePermission resourcePermission;

	public CCIStaffRolesDefaultResourcePermission() {
	}

	public CCIStaffRolesDefaultResourcePermissionPK getId() {
		return this.id;
	}

	public void setId(CCIStaffRolesDefaultResourcePermissionPK id) {
		this.id = id;
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

	public ResourcePermission getResourcePermission() {
		return this.resourcePermission;
	}

	public void setResourcePermission(ResourcePermission resourcePermission) {
		this.resourcePermission = resourcePermission;
	}

}