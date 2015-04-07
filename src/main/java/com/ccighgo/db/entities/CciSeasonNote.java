package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the cci_season_note database table.
 * 
 */
@Entity
@Table(name="cci_season_note")
@NamedQuery(name="CciSeasonNote.findAll", query="SELECT c FROM CciSeasonNote c")
public class CciSeasonNote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int seasonNoteID;

	@Column(length=1)
	private String active;

	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	private String note;

	private byte[] stamp;

	//bi-directional many-to-one association to CciSeasonProgram
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="seasonProgramID")
	private CciSeasonProgram cciSeasonProgram;

	public CciSeasonNote() {
	}

	public int getSeasonNoteID() {
		return this.seasonNoteID;
	}

	public void setSeasonNoteID(int seasonNoteID) {
		this.seasonNoteID = seasonNoteID;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
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

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public byte[] getStamp() {
		return this.stamp;
	}

	public void setStamp(byte[] stamp) {
		this.stamp = stamp;
	}

	public CciSeasonProgram getCciSeasonProgram() {
		return this.cciSeasonProgram;
	}

	public void setCciSeasonProgram(CciSeasonProgram cciSeasonProgram) {
		this.cciSeasonProgram = cciSeasonProgram;
	}

}