package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the resourcegroups database table.
 * 
 */
@Entity
@Table(name="resourcegroups")
@NamedQuery(name="Resourcegroup.findAll", query="SELECT r FROM Resourcegroup r")
public class Resourcegroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

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

	//bi-directional many-to-many association to Department
	@ManyToMany
	@JoinTable(
		name="department_resourcegroups"
		, joinColumns={
			@JoinColumn(name="resourceGroupId", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="departmentId", nullable=false)
			}
		)
	private List<Department> departments;

	//bi-directional many-to-one association to Resourcepermission
	@OneToMany(mappedBy="resourcegroup")
	private List<Resourcepermission> resourcepermissions;

	public Resourcegroup() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

	public String getResourceGroupName() {
		return this.resourceGroupName;
	}

	public void setResourceGroupName(String resourceGroupName) {
		this.resourceGroupName = resourceGroupName;
	}

	public List<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public List<Resourcepermission> getResourcepermissions() {
		return this.resourcepermissions;
	}

	public void setResourcepermissions(List<Resourcepermission> resourcepermissions) {
		this.resourcepermissions = resourcepermissions;
	}

	public Resourcepermission addResourcepermission(Resourcepermission resourcepermission) {
		getResourcepermissions().add(resourcepermission);
		resourcepermission.setResourcegroup(this);

		return resourcepermission;
	}

	public Resourcepermission removeResourcepermission(Resourcepermission resourcepermission) {
		getResourcepermissions().remove(resourcepermission);
		resourcepermission.setResourcegroup(null);

		return resourcepermission;
	}

}