package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the SeasonWnTSpringDetails database table.
 * 
 */
@Entity
@Table(name="SeasonWnTSpringDetails")
@NamedQuery(name="SeasonWnTSpringDetail.findAll", query="SELECT s FROM SeasonWnTSpringDetail s")
public class SeasonWnTSpringDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer seasonWnTSpringDetailsId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date applicationDeadlineDate;

	private Integer createdBy;

	private Timestamp createdOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	private Byte isJobBoardOpen;

	private Integer maxPendingJobApps;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	private String programName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId")
	private Season season;

	//bi-directional many-to-one association to SeasonStatus
	@ManyToOne
	@JoinColumn(name="programStatusId")
	private SeasonStatus seasonStatus;

	public SeasonWnTSpringDetail() {
	}

	public Integer getSeasonWnTSpringDetailsId() {
		return this.seasonWnTSpringDetailsId;
	}

	public void setSeasonWnTSpringDetailsId(Integer seasonWnTSpringDetailsId) {
		this.seasonWnTSpringDetailsId = seasonWnTSpringDetailsId;
	}

	public Date getApplicationDeadlineDate() {
		return this.applicationDeadlineDate;
	}

	public void setApplicationDeadlineDate(Date applicationDeadlineDate) {
		this.applicationDeadlineDate = applicationDeadlineDate;
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

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Byte getIsJobBoardOpen() {
		return this.isJobBoardOpen;
	}

	public void setIsJobBoardOpen(Byte isJobBoardOpen) {
		this.isJobBoardOpen = isJobBoardOpen;
	}

	public Integer getMaxPendingJobApps() {
		return this.maxPendingJobApps;
	}

	public void setMaxPendingJobApps(Integer maxPendingJobApps) {
		this.maxPendingJobApps = maxPendingJobApps;
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

	public String getProgramName() {
		return this.programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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