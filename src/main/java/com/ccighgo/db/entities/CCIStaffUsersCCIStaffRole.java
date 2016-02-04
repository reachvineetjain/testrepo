package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the CCIStaffUsersCCIStaffRoles database table.
 * 
 */
@Entity
@Table(name="CCIStaffUsersCCIStaffRoles")
@NamedQuery(name="CCIStaffUsersCCIStaffRole.findAll", query="SELECT c FROM CCIStaffUsersCCIStaffRole c")
public class CCIStaffUsersCCIStaffRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CCIStaffUsersCCIStaffRolePK id;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	//bi-directional many-to-one association to CCIStaffRole
	@ManyToOne
	@JoinColumn(name="cciStaffRoleId", nullable=false, insertable=false, updatable=false)
	private CCIStaffRole ccistaffRole;

	//bi-directional many-to-one association to CCIStaffUser
	@ManyToOne
	@JoinColumn(name="cciStaffUserId", nullable=false, insertable=false, updatable=false)
	private CCIStaffUser ccistaffUser;

	public CCIStaffUsersCCIStaffRole() {
	}

	public CCIStaffUsersCCIStaffRolePK getId() {
		return this.id;
	}

	public void setId(CCIStaffUsersCCIStaffRolePK id) {
		this.id = id;
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

	public CCIStaffRole getCcistaffRole() {
		return this.ccistaffRole;
	}

	public void setCcistaffRole(CCIStaffRole ccistaffRole) {
		this.ccistaffRole = ccistaffRole;
	}

	public CCIStaffUser getCcistaffUser() {
		return this.ccistaffUser;
	}

	public void setCcistaffUser(CCIStaffUser ccistaffUser) {
		this.ccistaffUser = ccistaffUser;
	}

}