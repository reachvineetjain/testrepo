package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the USSchoolSeason database table.
 * 
 */
@Entity
@NamedQuery(name="USSchoolSeason.findAll", query="SELECT u FROM USSchoolSeason u")
public class USSchoolSeason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer usSchoolSeasonId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date schoolEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date schoolStartDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date secondSemStartDate;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId",insertable=false,updatable=false)
	private Season season;

	//bi-directional many-to-one association to USSchool
	@ManyToOne
	@JoinColumn(name="usSchoolId",insertable=false,updatable=false)
	private USSchool usschool;

	public USSchoolSeason() {
	}

	public Integer getUsSchoolSeasonId() {
		return this.usSchoolSeasonId;
	}

	public void setUsSchoolSeasonId(Integer usSchoolSeasonId) {
		this.usSchoolSeasonId = usSchoolSeasonId;
	}

	public Date getSchoolEndDate() {
		return this.schoolEndDate;
	}

	public void setSchoolEndDate(Date schoolEndDate) {
		this.schoolEndDate = schoolEndDate;
	}

	public Date getSchoolStartDate() {
		return this.schoolStartDate;
	}

	public void setSchoolStartDate(Date schoolStartDate) {
		this.schoolStartDate = schoolStartDate;
	}

	public Date getSecondSemStartDate() {
		return this.secondSemStartDate;
	}

	public void setSecondSemStartDate(Date secondSemStartDate) {
		this.secondSemStartDate = secondSemStartDate;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public USSchool getUsschool() {
		return this.usschool;
	}

	public void setUsschool(USSchool usschool) {
		this.usschool = usschool;
	}

}