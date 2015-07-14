package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int seasonWPConfigurationId;

	@Column(nullable=false)
	private int createdBy;

	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

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

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
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