package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the ResourcePermissions database table.
 * 
 */
@Entity
@Table(name="ResourcePermissions")
@NamedQuery(name="ResourcePermission.findAll", query="SELECT r FROM ResourcePermission r")
public class ResourcePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int resourcePermissionId;

	@Column(nullable=false)
	private byte active;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(length=200)
	private String resourceDescription;

	@Column(length=50)
	private String resourceName;

	//bi-directional many-to-one association to CCIStaffRolesDefaultResourcePermission
	@OneToMany(mappedBy="resourcePermission")
	private List<CCIStaffRolesDefaultResourcePermission> ccistaffRolesDefaultResourcePermissions;

	//bi-directional many-to-one association to CCIStaffUsersResourcePermission
	@OneToMany(mappedBy="resourcePermission")
	private List<CCIStaffUsersResourcePermission> ccistaffUsersResourcePermissions;

	//bi-directional many-to-one association to DepartmentResourceGroup
	@ManyToOne
	@JoinColumn(name="departmentResourceGroupId", nullable=false)
	private DepartmentResourceGroup departmentResourceGroup;

	//bi-directional many-to-one association to ResourceAction
	@ManyToOne
	@JoinColumn(name="resourceActionID", nullable=false)
	private ResourceAction resourceAction;

	public ResourcePermission() {
	}

	public int getResourcePermissionId() {
		return this.resourcePermissionId;
	}

	public void setResourcePermissionId(int resourcePermissionId) {
		this.resourcePermissionId = resourcePermissionId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
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

	public String getResourceDescription() {
		return this.resourceDescription;
	}

	public void setResourceDescription(String resourceDescription) {
		this.resourceDescription = resourceDescription;
	}

	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public List<CCIStaffRolesDefaultResourcePermission> getCcistaffRolesDefaultResourcePermissions() {
		return this.ccistaffRolesDefaultResourcePermissions;
	}

	public void setCcistaffRolesDefaultResourcePermissions(List<CCIStaffRolesDefaultResourcePermission> ccistaffRolesDefaultResourcePermissions) {
		this.ccistaffRolesDefaultResourcePermissions = ccistaffRolesDefaultResourcePermissions;
	}

	public CCIStaffRolesDefaultResourcePermission addCcistaffRolesDefaultResourcePermission(CCIStaffRolesDefaultResourcePermission ccistaffRolesDefaultResourcePermission) {
		getCcistaffRolesDefaultResourcePermissions().add(ccistaffRolesDefaultResourcePermission);
		ccistaffRolesDefaultResourcePermission.setResourcePermission(this);

		return ccistaffRolesDefaultResourcePermission;
	}

	public CCIStaffRolesDefaultResourcePermission removeCcistaffRolesDefaultResourcePermission(CCIStaffRolesDefaultResourcePermission ccistaffRolesDefaultResourcePermission) {
		getCcistaffRolesDefaultResourcePermissions().remove(ccistaffRolesDefaultResourcePermission);
		ccistaffRolesDefaultResourcePermission.setResourcePermission(null);

		return ccistaffRolesDefaultResourcePermission;
	}

	public List<CCIStaffUsersResourcePermission> getCcistaffUsersResourcePermissions() {
		return this.ccistaffUsersResourcePermissions;
	}

	public void setCcistaffUsersResourcePermissions(List<CCIStaffUsersResourcePermission> ccistaffUsersResourcePermissions) {
		this.ccistaffUsersResourcePermissions = ccistaffUsersResourcePermissions;
	}

	public CCIStaffUsersResourcePermission addCcistaffUsersResourcePermission(CCIStaffUsersResourcePermission ccistaffUsersResourcePermission) {
		getCcistaffUsersResourcePermissions().add(ccistaffUsersResourcePermission);
		ccistaffUsersResourcePermission.setResourcePermission(this);

		return ccistaffUsersResourcePermission;
	}

	public CCIStaffUsersResourcePermission removeCcistaffUsersResourcePermission(CCIStaffUsersResourcePermission ccistaffUsersResourcePermission) {
		getCcistaffUsersResourcePermissions().remove(ccistaffUsersResourcePermission);
		ccistaffUsersResourcePermission.setResourcePermission(null);

		return ccistaffUsersResourcePermission;
	}

	public DepartmentResourceGroup getDepartmentResourceGroup() {
		return this.departmentResourceGroup;
	}

	public void setDepartmentResourceGroup(DepartmentResourceGroup departmentResourceGroup) {
		this.departmentResourceGroup = departmentResourceGroup;
	}

	public ResourceAction getResourceAction() {
		return this.resourceAction;
	}

	public void setResourceAction(ResourceAction resourceAction) {
		this.resourceAction = resourceAction;
	}

}