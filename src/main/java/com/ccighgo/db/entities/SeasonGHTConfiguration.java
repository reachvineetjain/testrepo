package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SeasonGHTConfiguration database table.
 * 
 */
@Entity
@Table(name="SeasonGHTConfiguration")
@NamedQuery(name="SeasonGHTConfiguration.findAll", query="SELECT s FROM SeasonGHTConfiguration s")
public class SeasonGHTConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int seasonGHTConfigurationId;

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

	public SeasonGHTConfiguration() {
	}

	public int getSeasonGHTConfigurationId() {
		return this.seasonGHTConfigurationId;
	}

	public void setSeasonGHTConfigurationId(int seasonGHTConfigurationId) {
		this.seasonGHTConfigurationId = seasonGHTConfigurationId;
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