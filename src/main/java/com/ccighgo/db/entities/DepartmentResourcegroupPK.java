package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the department_resourcegroups database table.
 * 
 */
@Embeddable
public class DepartmentResourcegroupPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int departmentId;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int resourceGroupId;

	public DepartmentResourcegroupPK() {
	}
	public int getDepartmentId() {
		return this.departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getResourceGroupId() {
		return this.resourceGroupId;
	}
	public void setResourceGroupId(int resourceGroupId) {
		this.resourceGroupId = resourceGroupId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DepartmentResourcegroupPK)) {
			return false;
		}
		DepartmentResourcegroupPK castOther = (DepartmentResourcegroupPK)other;
		return 
			(this.departmentId == castOther.departmentId)
			&& (this.resourceGroupId == castOther.resourceGroupId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.departmentId;
		hash = hash * prime + this.resourceGroupId;
		
		return hash;
	}
}