package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the SeasonHSPConfiguration database table.
 * 
 */
@Entity
@NamedQuery(name="SeasonHSPConfiguration.findAll", query="SELECT s FROM SeasonHSPConfiguration s")
public class SeasonHSPConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer seasonHSPConfigurationId;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date seasonEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date seasonStartDate;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId",insertable=false,updatable=false)
	private Season season;

	public SeasonHSPConfiguration() {
	}

	public Integer getSeasonHSPConfigurationId() {
		return this.seasonHSPConfigurationId;
	}

	public void setSeasonHSPConfigurationId(Integer seasonHSPConfigurationId) {
		this.seasonHSPConfigurationId = seasonHSPConfigurationId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
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