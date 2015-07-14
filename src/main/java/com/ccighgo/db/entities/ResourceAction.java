package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the ResourceActions database table.
 * 
 */
@Entity
@Table(name="ResourceActions")
@NamedQuery(name="ResourceAction.findAll", query="SELECT r FROM ResourceAction r")
public class ResourceAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int resourceActionId;

	@Column(nullable=false)
	private byte active;

	@Column(nullable=false)
	private int createdBy;

	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(nullable=false, length=50)
	private String resourceAction;

	@Column(nullable=false)
	private byte visibleToUser;

	//bi-directional many-to-one association to CCIStaffRolesDefaultResourcePermission
	@OneToMany(mappedBy="resourceAction")
	private List<CCIStaffRolesDefaultResourcePermission> ccistaffRolesDefaultResourcePermissions;

	//bi-directional many-to-one association to CCIStaffUsersResourcePermission
	@OneToMany(mappedBy="resourceAction")
	private List<CCIStaffUsersResourcePermission> ccistaffUsersResourcePermissions;

	//bi-directional many-to-one association to ResourcePermission
	@OneToMany(mappedBy="resourceAction")
	private List<ResourcePermission> resourcePermissions;

	public ResourceAction() {
	}

	public int getResourceActionId() {
		return this.resourceActionId;
	}

	public void setResourceActionId(int resourceActionId) {
		this.resourceActionId = resourceActionId;
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

	public String getResourceAction() {
		return this.resourceAction;
	}

	public void setResourceAction(String resourceAction) {
		this.resourceAction = resourceAction;
	}

	public byte getVisibleToUser() {
		return this.visibleToUser;
	}

	public void setVisibleToUser(byte visibleToUser) {
		this.visibleToUser = visibleToUser;
	}

	public List<CCIStaffRolesDefaultResourcePermission> getCcistaffRolesDefaultResourcePermissions() {
		return this.ccistaffRolesDefaultResourcePermissions;
	}

	public void setCcistaffRolesDefaultResourcePermissions(List<CCIStaffRolesDefaultResourcePermission> ccistaffRolesDefaultResourcePermissions) {
		this.ccistaffRolesDefaultResourcePermissions = ccistaffRolesDefaultResourcePermissions;
	}

	public CCIStaffRolesDefaultResourcePermission addCcistaffRolesDefaultResourcePermission(CCIStaffRolesDefaultResourcePermission ccistaffRolesDefaultResourcePermission) {
		getCcistaffRolesDefaultResourcePermissions().add(ccistaffRolesDefaultResourcePermission);
		ccistaffRolesDefaultResourcePermission.setResourceAction(this);

		return ccistaffRolesDefaultResourcePermission;
	}

	public CCIStaffRolesDefaultResourcePermission removeCcistaffRolesDefaultResourcePermission(CCIStaffRolesDefaultResourcePermission ccistaffRolesDefaultResourcePermission) {
		getCcistaffRolesDefaultResourcePermissions().remove(ccistaffRolesDefaultResourcePermission);
		ccistaffRolesDefaultResourcePermission.setResourceAction(null);

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
		ccistaffUsersResourcePermission.setResourceAction(this);

		return ccistaffUsersResourcePermission;
	}

	public CCIStaffUsersResourcePermission removeCcistaffUsersResourcePermission(CCIStaffUsersResourcePermission ccistaffUsersResourcePermission) {
		getCcistaffUsersResourcePermissions().remove(ccistaffUsersResourcePermission);
		ccistaffUsersResourcePermission.setResourceAction(null);

		return ccistaffUsersResourcePermission;
	}

	public List<ResourcePermission> getResourcePermissions() {
		return this.resourcePermissions;
	}

	public void setResourcePermissions(List<ResourcePermission> resourcePermissions) {
		this.resourcePermissions = resourcePermissions;
	}

	public ResourcePermission addResourcePermission(ResourcePermission resourcePermission) {
		getResourcePermissions().add(resourcePermission);
		resourcePermission.setResourceAction(this);

		return resourcePermission;
	}

	public ResourcePermission removeResourcePermission(ResourcePermission resourcePermission) {
		getResourcePermissions().remove(resourcePermission);
		resourcePermission.setResourceAction(null);

		return resourcePermission;
	}

}