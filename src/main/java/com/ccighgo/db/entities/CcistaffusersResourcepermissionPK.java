package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ccistaffusers_resourcepermissions database table.
 * 
 */
@Embeddable
public class CcistaffusersResourcepermissionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int cciStaffUserId;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int resourcesPermissionsId;

	public CcistaffusersResourcepermissionPK() {
	}
	public int getCciStaffUserId() {
		return this.cciStaffUserId;
	}
	public void setCciStaffUserId(int cciStaffUserId) {
		this.cciStaffUserId = cciStaffUserId;
	}
	public int getResourcesPermissionsId() {
		return this.resourcesPermissionsId;
	}
	public void setResourcesPermissionsId(int resourcesPermissionsId) {
		this.resourcesPermissionsId = resourcesPermissionsId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CcistaffusersResourcepermissionPK)) {
			return false;
		}
		CcistaffusersResourcepermissionPK castOther = (CcistaffusersResourcepermissionPK)other;
		return 
			(this.cciStaffUserId == castOther.cciStaffUserId)
			&& (this.resourcesPermissionsId == castOther.resourcesPermissionsId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cciStaffUserId;
		hash = hash * prime + this.resourcesPermissionsId;
		
		return hash;
	}
}