package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the SeasonDepartmentNotes database table.
 * 
 */
@Entity
@Table(name="SeasonDepartmentNotes")
@NamedQuery(name="SeasonDepartmentNote.findAll", query="SELECT s FROM SeasonDepartmentNote s")
public class SeasonDepartmentNote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer seasonDepartmentNotesId;

	private byte active;

	@Column(nullable=false)
	private Integer createdBy;

	private Timestamp createdOn;

	@Column(length=1000)
	private String departmentNote;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId", nullable=false)
	private Season season;

	public SeasonDepartmentNote() {
	}

	public Integer getSeasonDepartmentNotesId() {
		return this.seasonDepartmentNotesId;
	}

	public void setSeasonDepartmentNotesId(Integer seasonDepartmentNotesId) {
		this.seasonDepartmentNotesId = seasonDepartmentNotesId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
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

	public String getDepartmentNote() {
		return this.departmentNote;
	}

	public void setDepartmentNote(String departmentNote) {
		this.departmentNote = departmentNote;
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

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

}