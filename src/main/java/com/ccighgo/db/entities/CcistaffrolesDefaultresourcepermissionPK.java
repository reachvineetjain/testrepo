package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ccistaffroles_defaultresourcepermissions database table.
 * 
 */
@Embeddable
public class CcistaffrolesDefaultresourcepermissionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int cciStaffDepartmentRoleId;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int resourcePermissionsId;

	public CcistaffrolesDefaultresourcepermissionPK() {
	}
	public int getCciStaffDepartmentRoleId() {
		return this.cciStaffDepartmentRoleId;
	}
	public void setCciStaffDepartmentRoleId(int cciStaffDepartmentRoleId) {
		this.cciStaffDepartmentRoleId = cciStaffDepartmentRoleId;
	}
	public int getResourcePermissionsId() {
		return this.resourcePermissionsId;
	}
	public void setResourcePermissionsId(int resourcePermissionsId) {
		this.resourcePermissionsId = resourcePermissionsId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CcistaffrolesDefaultresourcepermissionPK)) {
			return false;
		}
		CcistaffrolesDefaultresourcepermissionPK castOther = (CcistaffrolesDefaultresourcepermissionPK)other;
		return 
			(this.cciStaffDepartmentRoleId == castOther.cciStaffDepartmentRoleId)
			&& (this.resourcePermissionsId == castOther.resourcePermissionsId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cciStaffDepartmentRoleId;
		hash = hash * prime + this.resourcePermissionsId;
		
		return hash;
	}
}