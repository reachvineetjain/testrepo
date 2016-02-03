package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the SeasonDepartmentUpdateLog database table.
 * 
 */
@Entity
@NamedQuery(name="SeasonDepartmentUpdateLog.findAll", query="SELECT s FROM SeasonDepartmentUpdateLog s")
public class SeasonDepartmentUpdateLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer updateDepartmentLogId;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	@Lob
	private String updateLogObject;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId",insertable=false,updatable=false)
	private Season season;

	public SeasonDepartmentUpdateLog() {
	}

	public Integer getUpdateDepartmentLogId() {
		return this.updateDepartmentLogId;
	}

	public void setUpdateDepartmentLogId(Integer updateDepartmentLogId) {
		this.updateDepartmentLogId = updateDepartmentLogId;
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

	public String getUpdateLogObject() {
		return this.updateLogObject;
	}

	public void setUpdateLogObject(String updateLogObject) {
		this.updateLogObject = updateLogObject;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

}