package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the resourcepermissions database table.
 * 
 */
@Entity
@Table(name="resourcepermissions")
@NamedQuery(name="ResourcePermission.findAll", query="SELECT r FROM ResourcePermission r")
public class ResourcePermission implements Serializable {
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

	@Column(length=200)
	private String resourceDescription;

	@Column(length=50)
	private String resourceName;

	//bi-directional many-to-one association to CciStaffRolesDefaultResourcePermission
	@OneToMany(mappedBy="resourcepermission")
	private List<CciStaffRolesDefaultResourcePermission> ccistaffrolesdefaultresourcepermissions;

	//bi-directional many-to-one association to CciStaffUsersResourcePermission
	@OneToMany(mappedBy="resourcepermission")
	private List<CciStaffUsersResourcePermission> ccistaffusersresourcepermissions;

	//bi-directional many-to-one association to DepartmentResourceGroup
	@ManyToOne
	@JoinColumn(name="deptResourceGroupID", nullable=false)
	private DepartmentResourceGroup departmentresourcegroup;

	//bi-directional many-to-one association to ResourceActions
	@ManyToOne
	@JoinColumn(name="resourceActionID", nullable=false)
	private ResourceActions resourceaction;

	public ResourcePermission() {
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

	public List<CciStaffRolesDefaultResourcePermission> getCcistaffrolesdefaultresourcepermissions() {
		return this.ccistaffrolesdefaultresourcepermissions;
	}

	public void setCcistaffrolesdefaultresourcepermissions(List<CciStaffRolesDefaultResourcePermission> ccistaffrolesdefaultresourcepermissions) {
		this.ccistaffrolesdefaultresourcepermissions = ccistaffrolesdefaultresourcepermissions;
	}

	public CciStaffRolesDefaultResourcePermission addCcistaffrolesdefaultresourcepermission(CciStaffRolesDefaultResourcePermission ccistaffrolesdefaultresourcepermission) {
		getCcistaffrolesdefaultresourcepermissions().add(ccistaffrolesdefaultresourcepermission);
		ccistaffrolesdefaultresourcepermission.setResourcepermission(this);

		return ccistaffrolesdefaultresourcepermission;
	}

	public CciStaffRolesDefaultResourcePermission removeCcistaffrolesdefaultresourcepermission(CciStaffRolesDefaultResourcePermission ccistaffrolesdefaultresourcepermission) {
		getCcistaffrolesdefaultresourcepermissions().remove(ccistaffrolesdefaultresourcepermission);
		ccistaffrolesdefaultresourcepermission.setResourcepermission(null);

		return ccistaffrolesdefaultresourcepermission;
	}

	public List<CciStaffUsersResourcePermission> getCcistaffusersresourcepermissions() {
		return this.ccistaffusersresourcepermissions;
	}

	public void setCcistaffusersresourcepermissions(List<CciStaffUsersResourcePermission> ccistaffusersresourcepermissions) {
		this.ccistaffusersresourcepermissions = ccistaffusersresourcepermissions;
	}

	public CciStaffUsersResourcePermission addCcistaffusersresourcepermission(CciStaffUsersResourcePermission ccistaffusersresourcepermission) {
		getCcistaffusersresourcepermissions().add(ccistaffusersresourcepermission);
		ccistaffusersresourcepermission.setResourcepermission(this);

		return ccistaffusersresourcepermission;
	}

	public CciStaffUsersResourcePermission removeCcistaffusersresourcepermission(CciStaffUsersResourcePermission ccistaffusersresourcepermission) {
		getCcistaffusersresourcepermissions().remove(ccistaffusersresourcepermission);
		ccistaffusersresourcepermission.setResourcepermission(null);

		return ccistaffusersresourcepermission;
	}

	public DepartmentResourceGroup getDepartmentresourcegroup() {
		return this.departmentresourcegroup;
	}

	public void setDepartmentresourcegroup(DepartmentResourceGroup departmentresourcegroup) {
		this.departmentresourcegroup = departmentresourcegroup;
	}

	public ResourceActions getResourceaction() {
		return this.resourceaction;
	}

	public void setResourceaction(ResourceActions resourceaction) {
		this.resourceaction = resourceaction;
	}

}