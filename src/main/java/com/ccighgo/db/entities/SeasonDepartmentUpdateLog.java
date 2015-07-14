package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the SeasonDepartmentUpdateLog database table.
 * 
 */
@Entity
@Table(name="SeasonDepartmentUpdateLog")
@NamedQuery(name="SeasonDepartmentUpdateLog.findAll", query="SELECT s FROM SeasonDepartmentUpdateLog s")
public class SeasonDepartmentUpdateLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int updateDepartmentLogId;

	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Lob
	private String updateLogObject;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId")
	private Season season;

	public SeasonDepartmentUpdateLog() {
	}

	public int getUpdateDepartmentLogId() {
		return this.updateDepartmentLogId;
	}

	public void setUpdateDepartmentLogId(int updateDepartmentLogId) {
		this.updateDepartmentLogId = updateDepartmentLogId;
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