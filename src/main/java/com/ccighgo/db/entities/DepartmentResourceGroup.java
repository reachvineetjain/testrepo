package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the departmentresourcegroups database table.
 * 
 */
@Entity
@Table(name="departmentresourcegroups")
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

	//bi-directional many-to-one association to Departments
	@ManyToOne
	@JoinColumn(name="departmentID", nullable=false)
	private Departments department;

	//bi-directional many-to-one association to ResourcePermission
	@OneToMany(mappedBy="departmentresourcegroup")
	private List<ResourcePermission> resourcepermissions;

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

	public Departments getDepartment() {
		return this.department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	public List<ResourcePermission> getResourcepermissions() {
		return this.resourcepermissions;
	}

	public void setResourcepermissions(List<ResourcePermission> resourcepermissions) {
		this.resourcepermissions = resourcepermissions;
	}

	public ResourcePermission addResourcepermission(ResourcePermission resourcepermission) {
		getResourcepermissions().add(resourcepermission);
		resourcepermission.setDepartmentresourcegroup(this);

		return resourcepermission;
	}

	public ResourcePermission removeResourcepermission(ResourcePermission resourcepermission) {
		getResourcepermissions().remove(resourcepermission);
		resourcepermission.setDepartmentresourcegroup(null);

		return resourcepermission;
	}

}