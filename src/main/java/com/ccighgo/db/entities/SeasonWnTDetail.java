package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SeasonWnTDetails database table.
 * 
 */
@Entity
@Table(name="SeasonWnTDetails")
@NamedQuery(name="SeasonWnTDetail.findAll", query="SELECT s FROM SeasonWnTDetail s")
public class SeasonWnTDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int seasonWTDetailsId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date applicationDeadlineDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date endDate;

	@Column(nullable=false)
	private byte isJobBoardOpen;

	@Column(nullable=false)
	private int maxPendingJobApps;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date startDate;

	//bi-directional many-to-one association to AnnualSeason
	@ManyToOne
	@JoinColumn(name="annualSeasonId", nullable=false)
	private AnnualSeason annualSeason;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId", nullable=false)
	private Season season;

	//bi-directional many-to-one association to SeasonStatus
	@ManyToOne
	@JoinColumn(name="programStatusId", nullable=false)
	private SeasonStatus seasonStatus;

	public SeasonWnTDetail() {
	}

	public int getSeasonWTDetailsId() {
		return this.seasonWTDetailsId;
	}

	public void setSeasonWTDetailsId(int seasonWTDetailsId) {
		this.seasonWTDetailsId = seasonWTDetailsId;
	}

	public Date getApplicationDeadlineDate() {
		return this.applicationDeadlineDate;
	}

	public void setApplicationDeadlineDate(Date applicationDeadlineDate) {
		this.applicationDeadlineDate = applicationDeadlineDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public byte getIsJobBoardOpen() {
		return this.isJobBoardOpen;
	}

	public void setIsJobBoardOpen(byte isJobBoardOpen) {
		this.isJobBoardOpen = isJobBoardOpen;
	}

	public int getMaxPendingJobApps() {
		return this.maxPendingJobApps;
	}

	public void setMaxPendingJobApps(int maxPendingJobApps) {
		this.maxPendingJobApps = maxPendingJobApps;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public AnnualSeason getAnnualSeason() {
		return this.annualSeason;
	}

	public void setAnnualSeason(AnnualSeason annualSeason) {
		this.annualSeason = annualSeason;
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