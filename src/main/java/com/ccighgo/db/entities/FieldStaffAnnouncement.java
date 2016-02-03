package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the FieldStaffAnnouncement database table.
 * 
 */
@Entity
@NamedQuery(name="FieldStaffAnnouncement.findAll", query="SELECT f FROM FieldStaffAnnouncement f")
public class FieldStaffAnnouncement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer fieldStaffAnnouncementId;

	private Byte active;

	@Lob
	private String announcement;

	private Byte archived;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	private Byte showERD;

	private Byte showLC;

	private Byte showRD;

	private Byte showRM;

	private String title;

	//bi-directional many-to-one association to DepartmentProgram
	@ManyToOne
	@JoinColumn(name="departmentProgramId",insertable=false,updatable=false)
	private DepartmentProgram departmentProgram;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="fieldStaffGoId",insertable=false,updatable=false)
	private FieldStaff fieldStaff;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId",insertable=false,updatable=false)
	private Season season;

	public FieldStaffAnnouncement() {
	}

	public Integer getFieldStaffAnnouncementId() {
		return this.fieldStaffAnnouncementId;
	}

	public void setFieldStaffAnnouncementId(Integer fieldStaffAnnouncementId) {
		this.fieldStaffAnnouncementId = fieldStaffAnnouncementId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public String getAnnouncement() {
		return this.announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}

	public Byte getArchived() {
		return this.archived;
	}

	public void setArchived(Byte archived) {
		this.archived = archived;
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

	public Byte getShowERD() {
		return this.showERD;
	}

	public void setShowERD(Byte showERD) {
		this.showERD = showERD;
	}

	public Byte getShowLC() {
		return this.showLC;
	}

	public void setShowLC(Byte showLC) {
		this.showLC = showLC;
	}

	public Byte getShowRD() {
		return this.showRD;
	}

	public void setShowRD(Byte showRD) {
		this.showRD = showRD;
	}

	public Byte getShowRM() {
		return this.showRM;
	}

	public void setShowRM(Byte showRM) {
		this.showRM = showRM;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public DepartmentProgram getDepartmentProgram() {
		return this.departmentProgram;
	}

	public void setDepartmentProgram(DepartmentProgram departmentProgram) {
		this.departmentProgram = departmentProgram;
	}

	public FieldStaff getFieldStaff() {
		return this.fieldStaff;
	}

	public void setFieldStaff(FieldStaff fieldStaff) {
		this.fieldStaff = fieldStaff;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

}