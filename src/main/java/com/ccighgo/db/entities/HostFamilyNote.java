package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilyNote database table.
 * 
 */
@Entity
@NamedQuery(name="HostFamilyNote.findAll", query="SELECT h FROM HostFamilyNote h")
public class HostFamilyNote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer hostFamilyNoteId;

	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	private Integer modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	@Lob
	private String note;

	//bi-directional many-to-one association to HostFamily
	@ManyToOne
	@JoinColumn(name="hostFamilyGoId",insertable=false,updatable=false)
	private HostFamily hostFamily;

	public HostFamilyNote() {
	}

	public Integer getHostFamilyNoteId() {
		return this.hostFamilyNoteId;
	}

	public void setHostFamilyNoteId(Integer hostFamilyNoteId) {
		this.hostFamilyNoteId = hostFamilyNoteId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public HostFamily getHostFamily() {
		return this.hostFamily;
	}

	public void setHostFamily(HostFamily hostFamily) {
		this.hostFamily = hostFamily;
	}

}