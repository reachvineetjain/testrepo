package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the FieldStaffHistory database table.
 * 
 */
@Entity
@NamedQuery(name="FieldStaffHistory.findAll", query="SELECT f FROM FieldStaffHistory f")
public class FieldStaffHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer fieldStaffHistoryId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date changeDate;

	private Integer erdId;

	private Integer newFieldStffTypeId;

	private Integer oldFieldStaffTypeId;

	private Integer rdId;

	private Integer rmId;

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

	public FieldStaffHistory() {
	}

	public Integer getFieldStaffHistoryId() {
		return this.fieldStaffHistoryId;
	}

	public void setFieldStaffHistoryId(Integer fieldStaffHistoryId) {
		this.fieldStaffHistoryId = fieldStaffHistoryId;
	}

	public Date getChangeDate() {
		return this.changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public Integer getErdId() {
		return this.erdId;
	}

	public void setErdId(Integer erdId) {
		this.erdId = erdId;
	}

	public Integer getNewFieldStffTypeId() {
		return this.newFieldStffTypeId;
	}

	public void setNewFieldStffTypeId(Integer newFieldStffTypeId) {
		this.newFieldStffTypeId = newFieldStffTypeId;
	}

	public Integer getOldFieldStaffTypeId() {
		return this.oldFieldStaffTypeId;
	}

	public void setOldFieldStaffTypeId(Integer oldFieldStaffTypeId) {
		this.oldFieldStaffTypeId = oldFieldStaffTypeId;
	}

	public Integer getRdId() {
		return this.rdId;
	}

	public void setRdId(Integer rdId) {
		this.rdId = rdId;
	}

	public Integer getRmId() {
		return this.rmId;
	}

	public void setRmId(Integer rmId) {
		this.rmId = rmId;
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