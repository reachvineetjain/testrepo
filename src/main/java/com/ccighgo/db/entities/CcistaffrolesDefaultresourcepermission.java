package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ccistaffroles_defaultresourcepermissions database table.
 * 
 */
@Entity
@Table(name="ccistaffroles_defaultresourcepermissions")
@NamedQuery(name="CcistaffrolesDefaultresourcepermission.findAll", query="SELECT c FROM CcistaffrolesDefaultresourcepermission c")
public class CcistaffrolesDefaultresourcepermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CcistaffrolesDefaultresourcepermissionPK id;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to CcistaffrolesDepartment
	@ManyToOne
	@JoinColumn(name="cciStaffDepartmentRoleId", nullable=false, insertable=false, updatable=false)
	private CcistaffrolesDepartment ccistaffrolesDepartment;

	//bi-directional many-to-one association to Resourcepermission
	@ManyToOne
	@JoinColumn(name="resourcePermissionsId", nullable=false, insertable=false, updatable=false)
	private Resourcepermission resourcepermission;

	public CcistaffrolesDefaultresourcepermission() {
	}

	public CcistaffrolesDefaultresourcepermissionPK getId() {
		return this.id;
	}

	public void setId(CcistaffrolesDefaultresourcepermissionPK id) {
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

	public CcistaffrolesDepartment getCcistaffrolesDepartment() {
		return this.ccistaffrolesDepartment;
	}

	public void setCcistaffrolesDepartment(CcistaffrolesDepartment ccistaffrolesDepartment) {
		this.ccistaffrolesDepartment = ccistaffrolesDepartment;
	}

	public Resourcepermission getResourcepermission() {
		return this.resourcepermission;
	}

	public void setResourcepermission(Resourcepermission resourcepermission) {
		this.resourcepermission = resourcepermission;
	}

}