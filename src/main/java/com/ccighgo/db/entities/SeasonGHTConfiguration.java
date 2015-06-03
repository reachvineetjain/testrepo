package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


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
	private int seasonGHTConfigurationID;

	@Column(nullable=false)
	private int deposite;

	@Column(nullable=false)
	private Timestamp endDate;

	@Column(nullable=false)
	private int fieldStaffStipend;

	@Column(nullable=false)
	private Timestamp startDate;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="SeasonID", nullable=false)
	private Season season;

	public SeasonGHTConfiguration() {
	}

	public int getSeasonGHTConfigurationID() {
		return this.seasonGHTConfigurationID;
	}

	public void setSeasonGHTConfigurationID(int seasonGHTConfigurationID) {
		this.seasonGHTConfigurationID = seasonGHTConfigurationID;
	}

	public int getDeposite() {
		return this.deposite;
	}

	public void setDeposite(int deposite) {
		this.deposite = deposite;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public int getFieldStaffStipend() {
		return this.fieldStaffStipend;
	}

	public void setFieldStaffStipend(int fieldStaffStipend) {
		this.fieldStaffStipend = fieldStaffStipend;
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