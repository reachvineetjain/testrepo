package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the seasonwpconfiguration database table.
 * 
 */
@Entity
@Table(name="seasonwpconfiguration")
@NamedQuery(name="SeasonWPConfiguration.findAll", query="SELECT s FROM SeasonWPConfiguration s")
public class SeasonWPConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int seasonWPConfigurationID;

	@Column(nullable=false)
	private Timestamp seasonEndDate;

	@Column(nullable=false)
	private Timestamp seasonStartDate;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonID", nullable=false)
	private Season season;

	public SeasonWPConfiguration() {
	}

	public int getSeasonWPConfigurationID() {
		return this.seasonWPConfigurationID;
	}

	public void setSeasonWPConfigurationID(int seasonWPConfigurationID) {
		this.seasonWPConfigurationID = seasonWPConfigurationID;
	}

	public Timestamp getSeasonEndDate() {
		return this.seasonEndDate;
	}

	public void setSeasonEndDate(Timestamp seasonEndDate) {
		this.seasonEndDate = seasonEndDate;
	}

	public Timestamp getSeasonStartDate() {
		return this.seasonStartDate;
	}

	public void setSeasonStartDate(Timestamp seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

}