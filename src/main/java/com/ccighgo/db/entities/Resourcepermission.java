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
@NamedQuery(name="Resourcepermission.findAll", query="SELECT r FROM Resourcepermission r")
public class Resourcepermission implements Serializable {
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

	//bi-directional many-to-one association to CcistaffrolesDefaultresourcepermission
	@OneToMany(mappedBy="resourcepermission")
	private List<CcistaffrolesDefaultresourcepermission> ccistaffrolesDefaultresourcepermissions;

	//bi-directional many-to-one association to CcistaffusersResourcepermission
	@OneToMany(mappedBy="resourcepermission")
	private List<CcistaffusersResourcepermission> ccistaffusersResourcepermissions;

	//bi-directional many-to-one association to Resourceaction
	@ManyToOne
	@JoinColumn(name="resourceActionID", nullable=false)
	private Resourceaction resourceaction;

	//bi-directional many-to-one association to Resourcegroup
	@ManyToOne
	@JoinColumn(name="resourceGroupID", nullable=false)
	private Resourcegroup resourcegroup;

	public Resourcepermission() {
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

	public List<CcistaffrolesDefaultresourcepermission> getCcistaffrolesDefaultresourcepermissions() {
		return this.ccistaffrolesDefaultresourcepermissions;
	}

	public void setCcistaffrolesDefaultresourcepermissions(List<CcistaffrolesDefaultresourcepermission> ccistaffrolesDefaultresourcepermissions) {
		this.ccistaffrolesDefaultresourcepermissions = ccistaffrolesDefaultresourcepermissions;
	}

	public CcistaffrolesDefaultresourcepermission addCcistaffrolesDefaultresourcepermission(CcistaffrolesDefaultresourcepermission ccistaffrolesDefaultresourcepermission) {
		getCcistaffrolesDefaultresourcepermissions().add(ccistaffrolesDefaultresourcepermission);
		ccistaffrolesDefaultresourcepermission.setResourcepermission(this);

		return ccistaffrolesDefaultresourcepermission;
	}

	public CcistaffrolesDefaultresourcepermission removeCcistaffrolesDefaultresourcepermission(CcistaffrolesDefaultresourcepermission ccistaffrolesDefaultresourcepermission) {
		getCcistaffrolesDefaultresourcepermissions().remove(ccistaffrolesDefaultresourcepermission);
		ccistaffrolesDefaultresourcepermission.setResourcepermission(null);

		return ccistaffrolesDefaultresourcepermission;
	}

	public List<CcistaffusersResourcepermission> getCcistaffusersResourcepermissions() {
		return this.ccistaffusersResourcepermissions;
	}

	public void setCcistaffusersResourcepermissions(List<CcistaffusersResourcepermission> ccistaffusersResourcepermissions) {
		this.ccistaffusersResourcepermissions = ccistaffusersResourcepermissions;
	}

	public CcistaffusersResourcepermission addCcistaffusersResourcepermission(CcistaffusersResourcepermission ccistaffusersResourcepermission) {
		getCcistaffusersResourcepermissions().add(ccistaffusersResourcepermission);
		ccistaffusersResourcepermission.setResourcepermission(this);

		return ccistaffusersResourcepermission;
	}

	public CcistaffusersResourcepermission removeCcistaffusersResourcepermission(CcistaffusersResourcepermission ccistaffusersResourcepermission) {
		getCcistaffusersResourcepermissions().remove(ccistaffusersResourcepermission);
		ccistaffusersResourcepermission.setResourcepermission(null);

		return ccistaffusersResourcepermission;
	}

	public Resourceaction getResourceaction() {
		return this.resourceaction;
	}

	public void setResourceaction(Resourceaction resourceaction) {
		this.resourceaction = resourceaction;
	}

	public Resourcegroup getResourcegroup() {
		return this.resourcegroup;
	}

	public void setResourcegroup(Resourcegroup resourcegroup) {
		this.resourcegroup = resourcegroup;
	}

}