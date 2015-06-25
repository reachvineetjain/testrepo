package com.ccighgo.db.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the SeasonCAPDetails database table.
 * 
 */
@Entity
@Table(name="SeasonCAPDetails")
@NamedQuery(name="SeasonCAPDetail.findAll", query="SELECT s FROM SeasonCAPDetail s")
public class SeasonCAPDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int seasonCAPDetailsId;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date internAppDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date internEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date internStartDate;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(length=45)
	private String programName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date traineeAppDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date traineeEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date traineeStartDate;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId", nullable=false)
	private Season season;

	//bi-directional many-to-one association to SeasonStatus
	@ManyToOne
	@JoinColumn(name="programStatusId")
	private SeasonStatus seasonStatus;

	public SeasonCAPDetail() {
	}

	public int getSeasonCAPDetailsId() {
		return this.seasonCAPDetailsId;
	}

	public void setSeasonCAPDetailsId(int seasonCAPDetailsId) {
		this.seasonCAPDetailsId = seasonCAPDetailsId;
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

	public Date getInternAppDeadlineDate() {
		return this.internAppDeadlineDate;
	}

	public void setInternAppDeadlineDate(Date internAppDeadlineDate) {
		this.internAppDeadlineDate = internAppDeadlineDate;
	}

	public Date getInternEndDate() {
		return this.internEndDate;
	}

	public void setInternEndDate(Date internEndDate) {
		this.internEndDate = internEndDate;
	}

	public Date getInternStartDate() {
		return this.internStartDate;
	}

	public void setInternStartDate(Date internStartDate) {
		this.internStartDate = internStartDate;
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

	public String getProgramName() {
		return this.programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public Date getTraineeAppDeadlineDate() {
		return this.traineeAppDeadlineDate;
	}

	public void setTraineeAppDeadlineDate(Date traineeAppDeadlineDate) {
		this.traineeAppDeadlineDate = traineeAppDeadlineDate;
	}

	public Date getTraineeEndDate() {
		return this.traineeEndDate;
	}

	public void setTraineeEndDate(Date traineeEndDate) {
		this.traineeEndDate = traineeEndDate;
	}

	public Date getTraineeStartDate() {
		return this.traineeStartDate;
	}

	public void setTraineeStartDate(Date traineeStartDate) {
		this.traineeStartDate = traineeStartDate;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public SeasonStatus getSeasonStatus() {
		return this.seasonStatus;
	}

	public void setSeasonStatus(SeasonStatus seasonStatus) {
		this.seasonStatus = seasonStatus;
	}

}