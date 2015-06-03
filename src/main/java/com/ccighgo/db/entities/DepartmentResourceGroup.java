package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the DepartmentResourceGroups database table.
 * 
 */
@Entity
@Table(name="DepartmentResourceGroups")
@NamedQuery(name="DepartmentResourceGroup.findAll", query="SELECT d FROM DepartmentResourceGroup d")
public class DepartmentResourceGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int deptResourceGroupID;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(nullable=false, length=50)
	private String resourceGroupName;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="departmentID", nullable=false)
	private Department department;

	//bi-directional many-to-one association to ResourcePermission
	@OneToMany(mappedBy="departmentResourceGroup")
	private List<ResourcePermission> resourcePermissions;

	public DepartmentResourceGroup() {
	}

	public int getDeptResourceGroupID() {
		return this.deptResourceGroupID;
	}

	public void setDeptResourceGroupID(int deptResourceGroupID) {
		this.deptResourceGroupID = deptResourceGroupID;
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

	public String getResourceGroupName() {
		return this.resourceGroupName;
	}

	public void setResourceGroupName(String resourceGroupName) {
		this.resourceGroupName = resourceGroupName;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<ResourcePermission> getResourcePermissions() {
		return this.resourcePermissions;
	}

	public void setResourcePermissions(List<ResourcePermission> resourcePermissions) {
		this.resourcePermissions = resourcePermissions;
	}

	public ResourcePermission addResourcePermission(ResourcePermission resourcePermission) {
		getResourcePermissions().add(resourcePermission);
		resourcePermission.setDepartmentResourceGroup(this);

		return resourcePermission;
	}

	public ResourcePermission removeResourcePermission(ResourcePermission resourcePermission) {
		getResourcePermissions().remove(resourcePermission);
		resourcePermission.setDepartmentResourceGroup(null);

		return resourcePermission;
	}

}