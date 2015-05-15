package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ccistaffusers_ccistaffroles database table.
 * 
 */
@Embeddable
public class CcistaffusersCcistaffrolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int cciStaffID;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int cciStaffRolesID;

	public CcistaffusersCcistaffrolePK() {
	}
	public int getCciStaffID() {
		return this.cciStaffID;
	}
	public void setCciStaffID(int cciStaffID) {
		this.cciStaffID = cciStaffID;
	}
	public int getCciStaffRolesID() {
		return this.cciStaffRolesID;
	}
	public void setCciStaffRolesID(int cciStaffRolesID) {
		this.cciStaffRolesID = cciStaffRolesID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CcistaffusersCcistaffrolePK)) {
			return false;
		}
		CcistaffusersCcistaffrolePK castOther = (CcistaffusersCcistaffrolePK)other;
		return 
			(this.cciStaffID == castOther.cciStaffID)
			&& (this.cciStaffRolesID == castOther.cciStaffRolesID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cciStaffID;
		hash = hash * prime + this.cciStaffRolesID;
		
		return hash;
	}
}