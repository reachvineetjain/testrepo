package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the USSchoolSeason database table.
 * 
 */
@Entity
@Table(name="USSchoolSeason")
@NamedQuery(name="USSchoolSeason.findAll", query="SELECT u FROM USSchoolSeason u")
public class USSchoolSeason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int usSchoolSeasonId;

	@Column(nullable=false)
	private Timestamp schoolEndDate;

	@Column(nullable=false)
	private Timestamp schoolStartDate;

	@Column(nullable=false)
	private Timestamp secondSemStartDate;

	@Column(nullable=false)
	private int usSchoolId;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId", nullable=false)
	private Season season;

	public USSchoolSeason() {
	}

	public int getUsSchoolSeasonId() {
		return this.usSchoolSeasonId;
	}

	public void setUsSchoolSeasonId(int usSchoolSeasonId) {
		this.usSchoolSeasonId = usSchoolSeasonId;
	}

	public Timestamp getSchoolEndDate() {
		return this.schoolEndDate;
	}

	public void setSchoolEndDate(Timestamp schoolEndDate) {
		this.schoolEndDate = schoolEndDate;
	}

	public Timestamp getSchoolStartDate() {
		return this.schoolStartDate;
	}

	public void setSchoolStartDate(Timestamp schoolStartDate) {
		this.schoolStartDate = schoolStartDate;
	}

	public Timestamp getSecondSemStartDate() {
		return this.secondSemStartDate;
	}

	public void setSecondSemStartDate(Timestamp secondSemStartDate) {
		this.secondSemStartDate = secondSemStartDate;
	}

	public int getUsSchoolId() {
		return this.usSchoolId;
	}

	public void setUsSchoolId(int usSchoolId) {
		this.usSchoolId = usSchoolId;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

}