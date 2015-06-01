package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ccistaffusernotes database table.
 * 
 */
@Entity
@Table(name="ccistaffusernotes")
@NamedQuery(name="CciStaffUserNote.findAll", query="SELECT c FROM CciStaffUserNote c")
public class CciStaffUserNote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int ccistaffusernotesID;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(nullable=false, length=1000)
	private String note;

	//bi-directional many-to-one association to CciStaffUser
	@ManyToOne
	@JoinColumn(name="ccistaffuserID", nullable=false)
	private CciStaffUser ccistaffuser;

	public CciStaffUserNote() {
	}

	public int getCcistaffusernotesID() {
		return this.ccistaffusernotesID;
	}

	public void setCcistaffusernotesID(int ccistaffusernotesID) {
		this.ccistaffusernotesID = ccistaffusernotesID;
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

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public CciStaffUser getCcistaffuser() {
		return this.ccistaffuser;
	}

	public void setCcistaffuser(CciStaffUser ccistaffuser) {
		this.ccistaffuser = ccistaffuser;
	}

}