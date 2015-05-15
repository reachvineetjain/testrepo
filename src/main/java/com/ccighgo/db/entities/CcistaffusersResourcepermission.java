package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ccistaffusers_resourcepermissions database table.
 * 
 */
@Entity
@Table(name="ccistaffusers_resourcepermissions")
@NamedQuery(name="CcistaffusersResourcepermission.findAll", query="SELECT c FROM CcistaffusersResourcepermission c")
public class CcistaffusersResourcepermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CcistaffusersResourcepermissionPK id;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to Ccistaffuser
	@ManyToOne
	@JoinColumn(name="cciStaffUserId", nullable=false, insertable=false, updatable=false)
	private Ccistaffuser ccistaffuser;

	//bi-directional many-to-one association to Resourcepermission
	@ManyToOne
	@JoinColumn(name="resourcesPermissionsId", nullable=false, insertable=false, updatable=false)
	private Resourcepermission resourcepermission;

	public CcistaffusersResourcepermission() {
	}

	public CcistaffusersResourcepermissionPK getId() {
		return this.id;
	}

	public void setId(CcistaffusersResourcepermissionPK id) {
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

	public Ccistaffuser getCcistaffuser() {
		return this.ccistaffuser;
	}

	public void setCcistaffuser(Ccistaffuser ccistaffuser) {
		this.ccistaffuser = ccistaffuser;
	}

	public Resourcepermission getResourcepermission() {
		return this.resourcepermission;
	}

	public void setResourcepermission(Resourcepermission resourcepermission) {
		this.resourcepermission = resourcepermission;
	}

}