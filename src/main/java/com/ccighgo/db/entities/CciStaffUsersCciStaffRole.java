package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ccistaffusersccistaffroles database table.
 * 
 */
@Entity
@Table(name="ccistaffusersccistaffroles")
@NamedQuery(name="CciStaffUsersCciStaffRole.findAll", query="SELECT c FROM CciStaffUsersCciStaffRole c")
public class CciStaffUsersCciStaffRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CciStaffUsersCciStaffRolePK id;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to CciStaffRole
	@ManyToOne
	@JoinColumn(name="cciStaffRolesID", nullable=false, insertable=false, updatable=false)
	private CciStaffRole ccistaffrole;

	//bi-directional many-to-one association to CciStaffUser
	@ManyToOne
	@JoinColumn(name="cciStaffID", nullable=false, insertable=false, updatable=false)
	private CciStaffUser ccistaffuser;

	public CciStaffUsersCciStaffRole() {
	}

	public CciStaffUsersCciStaffRolePK getId() {
		return this.id;
	}

	public void setId(CciStaffUsersCciStaffRolePK id) {
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

	public CciStaffRole getCcistaffrole() {
		return this.ccistaffrole;
	}

	public void setCcistaffrole(CciStaffRole ccistaffrole) {
		this.ccistaffrole = ccistaffrole;
	}

	public CciStaffUser getCcistaffuser() {
		return this.ccistaffuser;
	}

	public void setCcistaffuser(CciStaffUser ccistaffuser) {
		this.ccistaffuser = ccistaffuser;
	}

}