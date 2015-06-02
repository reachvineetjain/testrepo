package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ccistaffusersresourcepermissions database table.
 * 
 */
@Entity
@Table(name="ccistaffusersresourcepermissions")
@NamedQuery(name="CciStaffUsersResourcePermission.findAll", query="SELECT c FROM CciStaffUsersResourcePermission c")
public class CciStaffUsersResourcePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CciStaffUsersResourcePermissionPK id;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to CciStaffUser
	@ManyToOne
	@JoinColumn(name="cciStaffUserId", nullable=false, insertable=false, updatable=false)
	private CciStaffUser ccistaffuser;

	//bi-directional many-to-one association to ResourcePermission
	@ManyToOne
	@JoinColumn(name="resourcesPermissionsId", nullable=false, insertable=false, updatable=false)
	private ResourcePermission resourcepermission;

	public CciStaffUsersResourcePermission() {
	}

	public CciStaffUsersResourcePermissionPK getId() {
		return this.id;
	}

	public void setId(CciStaffUsersResourcePermissionPK id) {
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

	public CciStaffUser getCcistaffuser() {
		return this.ccistaffuser;
	}

	public void setCcistaffuser(CciStaffUser ccistaffuser) {
		this.ccistaffuser = ccistaffuser;
	}

	public ResourcePermission getResourcepermission() {
		return this.resourcepermission;
	}

	public void setResourcepermission(ResourcePermission resourcepermission) {
		this.resourcepermission = resourcepermission;
	}

}