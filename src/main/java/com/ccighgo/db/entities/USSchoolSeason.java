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
	private int usSchoolSeasonID;

	@Column(nullable=false)
	private Timestamp schoolEndDate;

	@Column(nullable=false)
	private Timestamp schoolStartDate;

	@Column(nullable=false)
	private Timestamp secondSemStartDate;

	@Column(nullable=false)
	private int usSchoolID;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonID", nullable=false)
	private Season season;

	public USSchoolSeason() {
	}

	public int getUsSchoolSeasonID() {
		return this.usSchoolSeasonID;
	}

	public void setUsSchoolSeasonID(int usSchoolSeasonID) {
		this.usSchoolSeasonID = usSchoolSeasonID;
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

	public int getUsSchoolID() {
		return this.usSchoolID;
	}

	public void setUsSchoolID(int usSchoolID) {
		this.usSchoolID = usSchoolID;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

}