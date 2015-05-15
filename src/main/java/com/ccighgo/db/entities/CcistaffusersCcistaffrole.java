package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ccistaffusers_ccistaffroles database table.
 * 
 */
@Entity
@Table(name="ccistaffusers_ccistaffroles")
@NamedQuery(name="CcistaffusersCcistaffrole.findAll", query="SELECT c FROM CcistaffusersCcistaffrole c")
public class CcistaffusersCcistaffrole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CcistaffusersCcistaffrolePK id;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to Ccistaffrole
	@ManyToOne
	@JoinColumn(name="cciStaffRolesID", nullable=false, insertable=false, updatable=false)
	private Ccistaffrole ccistaffrole;

	//bi-directional many-to-one association to Ccistaffuser
	@ManyToOne
	@JoinColumn(name="cciStaffID", nullable=false, insertable=false, updatable=false)
	private Ccistaffuser ccistaffuser;

	public CcistaffusersCcistaffrole() {
	}

	public CcistaffusersCcistaffrolePK getId() {
		return this.id;
	}

	public void setId(CcistaffusersCcistaffrolePK id) {
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

	public Ccistaffrole getCcistaffrole() {
		return this.ccistaffrole;
	}

	public void setCcistaffrole(Ccistaffrole ccistaffrole) {
		this.ccistaffrole = ccistaffrole;
	}

	public Ccistaffuser getCcistaffuser() {
		return this.ccistaffuser;
	}

	public void setCcistaffuser(Ccistaffuser ccistaffuser) {
		this.ccistaffuser = ccistaffuser;
	}

}