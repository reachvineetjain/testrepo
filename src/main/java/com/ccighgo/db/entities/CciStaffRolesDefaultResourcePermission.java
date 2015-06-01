package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ccistaffrolesdefaultresourcepermissions database table.
 * 
 */
@Entity
@Table(name="ccistaffrolesdefaultresourcepermissions")
@NamedQuery(name="CciStaffRolesDefaultResourcePermission.findAll", query="SELECT c FROM CciStaffRolesDefaultResourcePermission c")
public class CciStaffRolesDefaultResourcePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CciStaffRolesDefaultResourcePermissionPK id;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to CciStaffRolesDepartment
	@ManyToOne
	@JoinColumn(name="cciStaffDepartmentRoleId", nullable=false, insertable=false, updatable=false)
	private CciStaffRolesDepartment ccistaffrolesdepartment;

	//bi-directional many-to-one association to ResourcePermission
	@ManyToOne
	@JoinColumn(name="resourcePermissionsId", nullable=false, insertable=false, updatable=false)
	private ResourcePermission resourcepermission;

	public CciStaffRolesDefaultResourcePermission() {
	}

	public CciStaffRolesDefaultResourcePermissionPK getId() {
		return this.id;
	}

	public void setId(CciStaffRolesDefaultResourcePermissionPK id) {
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

	public CciStaffRolesDepartment getCcistaffrolesdepartment() {
		return this.ccistaffrolesdepartment;
	}

	public void setCcistaffrolesdepartment(CciStaffRolesDepartment ccistaffrolesdepartment) {
		this.ccistaffrolesdepartment = ccistaffrolesdepartment;
	}

	public ResourcePermission getResourcepermission() {
		return this.resourcepermission;
	}

	public void setResourcepermission(ResourcePermission resourcepermission) {
		this.resourcepermission = resourcepermission;
	}

}