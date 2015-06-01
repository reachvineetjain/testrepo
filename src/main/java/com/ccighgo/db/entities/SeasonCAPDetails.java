package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the seasoncapdetails database table.
 * 
 */
@Entity
@Table(name="seasoncapdetails")
@NamedQuery(name="SeasonCAPDetails.findAll", query="SELECT s FROM SeasonCAPDetails s")
public class SeasonCAPDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int seasonCAPDetailsID;

	@Column(nullable=false)
	private Timestamp internCutoffDate;

	@Column(nullable=false)
	private Timestamp internEndDate;

	@Column(nullable=false)
	private Timestamp internStartDate;

	@Column(nullable=false)
	private byte programSeasonStatus;

	@Column(nullable=false)
	private Timestamp traineeCutOffDate;

	@Column(nullable=false)
	private Timestamp traineeEndDate;

	@Column(nullable=false)
	private Timestamp traineeStartDate;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonID", nullable=false)
	private Season season;

	public SeasonCAPDetails() {
	}

	public int getSeasonCAPDetailsID() {
		return this.seasonCAPDetailsID;
	}

	public void setSeasonCAPDetailsID(int seasonCAPDetailsID) {
		this.seasonCAPDetailsID = seasonCAPDetailsID;
	}

	public Timestamp getInternCutoffDate() {
		return this.internCutoffDate;
	}

	public void setInternCutoffDate(Timestamp internCutoffDate) {
		this.internCutoffDate = internCutoffDate;
	}

	public Timestamp getInternEndDate() {
		return this.internEndDate;
	}

	public void setInternEndDate(Timestamp internEndDate) {
		this.internEndDate = internEndDate;
	}

	public Timestamp getInternStartDate() {
		return this.internStartDate;
	}

	public void setInternStartDate(Timestamp internStartDate) {
		this.internStartDate = internStartDate;
	}

	public byte getProgramSeasonStatus() {
		return this.programSeasonStatus;
	}

	public void setProgramSeasonStatus(byte programSeasonStatus) {
		this.programSeasonStatus = programSeasonStatus;
	}

	public Timestamp getTraineeCutOffDate() {
		return this.traineeCutOffDate;
	}

	public void setTraineeCutOffDate(Timestamp traineeCutOffDate) {
		this.traineeCutOffDate = traineeCutOffDate;
	}

	public Timestamp getTraineeEndDate() {
		return this.traineeEndDate;
	}

	public void setTraineeEndDate(Timestamp traineeEndDate) {
		this.traineeEndDate = traineeEndDate;
	}

	public Timestamp getTraineeStartDate() {
		return this.traineeStartDate;
	}

	public void setTraineeStartDate(Timestamp traineeStartDate) {
		this.traineeStartDate = traineeStartDate;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

}