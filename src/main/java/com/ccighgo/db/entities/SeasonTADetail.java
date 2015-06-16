package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SeasonTADetails database table.
 * 
 */
@Entity
@Table(name="SeasonTADetails")
@NamedQuery(name="SeasonTADetail.findAll", query="SELECT s FROM SeasonTADetail s")
public class SeasonTADetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int seasonTADetailsId;

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

	public SeasonTADetail() {
	}

	public int getSeasonTADetailsId() {
		return this.seasonTADetailsId;
	}

	public void setSeasonTADetailsId(int seasonTADetailsId) {
		this.seasonTADetailsId = seasonTADetailsId;
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