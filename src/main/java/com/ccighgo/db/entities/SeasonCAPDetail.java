package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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
	@Column(unique=true, nullable=false)
	private int seasonCAPDetailsId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date internAppDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date internEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date internStartDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date traineeAppDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date traineeEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date traineeStartDate;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId", nullable=false)
	private Season season;

	//bi-directional many-to-one association to SeasonStatus
	@ManyToOne
	@JoinColumn(name="programStatusId", nullable=false)
	private SeasonStatus seasonStatus;

	public SeasonCAPDetail() {
	}

	public int getSeasonCAPDetailsId() {
		return this.seasonCAPDetailsId;
	}

	public void setSeasonCAPDetailsId(int seasonCAPDetailsId) {
		this.seasonCAPDetailsId = seasonCAPDetailsId;
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