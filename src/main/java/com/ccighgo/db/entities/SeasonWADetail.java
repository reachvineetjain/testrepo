package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SeasonWADetails database table.
 * 
 */
@Entity
@Table(name="SeasonWADetails")
@NamedQuery(name="SeasonWADetail.findAll", query="SELECT s FROM SeasonWADetail s")
public class SeasonWADetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int seasonWADetailsId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date startDate;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId", nullable=false)
	private Season season;

	//bi-directional many-to-one association to SeasonStatus
	@ManyToOne
	@JoinColumn(name="programStatusId", nullable=false)
	private SeasonStatus seasonStatus;

	public SeasonWADetail() {
	}

	public int getSeasonWADetailsId() {
		return this.seasonWADetailsId;
	}

	public void setSeasonWADetailsId(int seasonWADetailsId) {
		this.seasonWADetailsId = seasonWADetailsId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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