package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SeasonWPConfiguration database table.
 * 
 */
@Entity
@Table(name="SeasonWPConfiguration")
@NamedQuery(name="SeasonWPConfiguration.findAll", query="SELECT s FROM SeasonWPConfiguration s")
public class SeasonWPConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int seasonWPConfigurationId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date seasonEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date seasonStartDate;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId", nullable=false)
	private Season season;

	public SeasonWPConfiguration() {
	}

	public int getSeasonWPConfigurationId() {
		return this.seasonWPConfigurationId;
	}

	public void setSeasonWPConfigurationId(int seasonWPConfigurationId) {
		this.seasonWPConfigurationId = seasonWPConfigurationId;
	}

	public Date getSeasonEndDate() {
		return this.seasonEndDate;
	}

	public void setSeasonEndDate(Date seasonEndDate) {
		this.seasonEndDate = seasonEndDate;
	}

	public Date getSeasonStartDate() {
		return this.seasonStartDate;
	}

	public void setSeasonStartDate(Date seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

}