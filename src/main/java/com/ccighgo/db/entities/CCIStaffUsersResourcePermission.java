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
	@JoinColumn(name="cciStaffUserId", nullable=false, insertable=false, updatable=false)
	private CCIStaffUser ccistaffUser;

	//bi-directional many-to-one association to ResourcePermission
	@ManyToOne
	@JoinColumn(name="resourcesPermissionsId", nullable=false, insertable=false, updatable=false)
	private ResourcePermission resourcePermission;

	public CCIStaffUsersResourcePermission() {
	}

	public CCIStaffUsersResourcePermissionPK getId() {
		return this.id;
	}

	public void setId(CCIStaffUsersResourcePermissionPK id) {
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

	public CCIStaffUser getCcistaffUser() {
		return this.ccistaffUser;
	}

	public void setCcistaffUser(CCIStaffUser ccistaffUser) {
		this.ccistaffUser = ccistaffUser;
	}

	public ResourcePermission getResourcePermission() {
		return this.resourcePermission;
	}

	public void setResourcePermission(ResourcePermission resourcePermission) {
		this.resourcePermission = resourcePermission;
	}

}