package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilySeasonNote database table.
 * 
 */
@Entity
@NamedQuery(name="HostFamilySeasonNote.findAll", query="SELECT h FROM HostFamilySeasonNote h")
public class HostFamilySeasonNote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer hostFamilySeasonNoteId;

	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	private Integer modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	@Lob
	private String note;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId",insertable=false,updatable=false)
	private HostFamilySeason hostFamilySeason;

	public HostFamilySeasonNote() {
	}

	public Integer getHostFamilySeasonNoteId() {
		return this.hostFamilySeasonNoteId;
	}

	public void setHostFamilySeasonNoteId(Integer hostFamilySeasonNoteId) {
		this.hostFamilySeasonNoteId = hostFamilySeasonNoteId;
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

	public HostFamilySeason getHostFamilySeason() {
		return this.hostFamilySeason;
	}

	public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
		this.hostFamilySeason = hostFamilySeason;
	}

}