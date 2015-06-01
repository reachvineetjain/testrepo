package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the seasonlsdetails database table.
 * 
 */
@Entity
@Table(name="seasonlsdetails")
@NamedQuery(name="SeasonLSDetails.findAll", query="SELECT s FROM SeasonLSDetails s")
public class SeasonLSDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int seasonLSDetailsID;

	@Column(nullable=false)
	private Timestamp cutOffDate;

	@Column(nullable=false)
	private int departureBoundry;

	@Column(nullable=false)
	private Timestamp endDate;

	@Column(nullable=false)
	private Timestamp startDate;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonID", nullable=false)
	private Season season;

	public SeasonLSDetails() {
	}

	public int getSeasonLSDetailsID() {
		return this.seasonLSDetailsID;
	}

	public void setSeasonLSDetailsID(int seasonLSDetailsID) {
		this.seasonLSDetailsID = seasonLSDetailsID;
	}

	public Timestamp getCutOffDate() {
		return this.cutOffDate;
	}

	public void setCutOffDate(Timestamp cutOffDate) {
		this.cutOffDate = cutOffDate;
	}

	public int getDepartureBoundry() {
		return this.departureBoundry;
	}

	public void setDepartureBoundry(int departureBoundry) {
		this.departureBoundry = departureBoundry;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
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