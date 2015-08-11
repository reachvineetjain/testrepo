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

	private byte active;

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
	   if (this.seasonDepartmentNotesId != null)
         return this.seasonDepartmentNotesId;
      return 0;
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
	   if (this.createdBy != null)
         return this.createdBy;
      return 0;
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
	   if (this.modifiedBy != null)
         return this.modifiedBy;
      return 0;
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