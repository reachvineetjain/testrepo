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
	@Column(unique=true, nullable=false)
	private int id;

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

	@Column(nullable=false, length=50)
	private String resourceAction;

	@Column(nullable=false)
	private byte visibleToUser;

	//bi-directional many-to-one association to ResourcePermission
	@OneToMany(mappedBy="resourceAction")
	private List<ResourcePermission> resourcePermissions;

	public ResourceAction() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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