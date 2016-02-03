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
	private Integer seasonDepartmentNotesId;

	private Byte active;

	private Integer createdBy;

	private Timestamp createdOn;

	private String departmentNote;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId")
	private Season season;

	public SeasonDepartmentNote() {
	}

	public Integer getSeasonDepartmentNotesId() {
		return this.seasonDepartmentNotesId;
	}

	public void setSeasonDepartmentNotesId(Integer seasonDepartmentNotesId) {
		this.seasonDepartmentNotesId = seasonDepartmentNotesId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
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