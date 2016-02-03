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
	private Integer resourcePermissionId;

	private Byte active;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	private String resourceDescription;

	private String resourceName;

	//bi-directional many-to-one association to CCIStaffRolesDefaultResourcePermission
	@OneToMany(mappedBy="resourcePermission")
	private List<CCIStaffRolesDefaultResourcePermission> ccistaffRolesDefaultResourcePermissions;

	//bi-directional many-to-one association to CCIStaffUsersResourcePermission
	@OneToMany(mappedBy="resourcePermission")
	private List<CCIStaffUsersResourcePermission> ccistaffUsersResourcePermissions;

	//bi-directional many-to-one association to DepartmentResourceGroup
	@ManyToOne
	@JoinColumn(name="departmentResourceGroupId",insertable=false,updatable=false)
	private DepartmentResourceGroup departmentResourceGroup;

	//bi-directional many-to-one association to ResourceAction
	@ManyToOne
	@JoinColumn(name="resourceActionID",insertable=false,updatable=false)
	private ResourceAction resourceAction;

	//bi-directional many-to-one association to StateTypeResourcePermission
	@OneToMany(mappedBy="resourcePermission")
	private List<StateTypeResourcePermission> stateTypeResourcePermissions;

	public ResourcePermission() {
	}

	public Integer getResourcePermissionId() {
		return this.resourcePermissionId;
	}

	public void setResourcePermissionId(Integer resourcePermissionId) {
		this.resourcePermissionId = resourcePermissionId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
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

	public List<StateTypeResourcePermission> getStateTypeResourcePermissions() {
		return this.stateTypeResourcePermissions;
	}

	public void setStateTypeResourcePermissions(List<StateTypeResourcePermission> stateTypeResourcePermissions) {
		this.stateTypeResourcePermissions = stateTypeResourcePermissions;
	}

	public StateTypeResourcePermission addStateTypeResourcePermission(StateTypeResourcePermission stateTypeResourcePermission) {
		getStateTypeResourcePermissions().add(stateTypeResourcePermission);
		stateTypeResourcePermission.setResourcePermission(this);

		return stateTypeResourcePermission;
	}

	public StateTypeResourcePermission removeStateTypeResourcePermission(StateTypeResourcePermission stateTypeResourcePermission) {
		getStateTypeResourcePermissions().remove(stateTypeResourcePermission);
		stateTypeResourcePermission.setResourcePermission(null);

		return stateTypeResourcePermission;
	}

}