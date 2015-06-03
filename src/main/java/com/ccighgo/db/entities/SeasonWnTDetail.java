package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


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
	private int seasonWnTDetailsID;

	@Column(nullable=false)
	private int annualSeason;

	@Column(nullable=false)
	private Timestamp cutOffDate;

	@Column(nullable=false)
	private Timestamp endDate;

	@Column(nullable=false)
	private byte isJobBoardOpen;

	@Column(nullable=false)
	private int maxPendingJobApps;

	@Column(nullable=false)
	private byte programStatus;

	@Column(nullable=false)
	private Timestamp startDate;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonID", nullable=false)
	private Season season;

	public SeasonWnTDetail() {
	}

	public int getSeasonWnTDetailsID() {
		return this.seasonWnTDetailsID;
	}

	public void setSeasonWnTDetailsID(int seasonWnTDetailsID) {
		this.seasonWnTDetailsID = seasonWnTDetailsID;
	}

	public int getAnnualSeason() {
		return this.annualSeason;
	}

	public void setAnnualSeason(int annualSeason) {
		this.annualSeason = annualSeason;
	}

	public Timestamp getCutOffDate() {
		return this.cutOffDate;
	}

	public void setCutOffDate(Timestamp cutOffDate) {
		this.cutOffDate = cutOffDate;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
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

	public byte getProgramStatus() {
		return this.programStatus;
	}

	public void setProgramStatus(byte programStatus) {
		this.programStatus = programStatus;
	}

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

}