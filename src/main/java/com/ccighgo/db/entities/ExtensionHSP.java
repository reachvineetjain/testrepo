package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the extensionhsp database table.
 * 
 */
@Entity
@Table(name="extensionhsp")
@NamedQuery(name="ExtensionHSP.findAll", query="SELECT e FROM ExtensionHSP e")
public class ExtensionHSP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int extentionID;

	@Column(nullable=false)
	private Timestamp extentionEbdDate;

	@Column(nullable=false)
	private Timestamp extentionStartDate;

	//bi-directional many-to-one association to SeasonF1Details
	@ManyToOne
	@JoinColumn(name="seasonF1DetailsId", nullable=false)
	private SeasonF1Details seasonf1detail;

	//bi-directional many-to-one association to SeasonJ1Details
	@ManyToOne
	@JoinColumn(name="seasonJ1DetailsID", nullable=false)
	private SeasonJ1Details seasonj1detail;

	public ExtensionHSP() {
	}

	public int getExtentionID() {
		return this.extentionID;
	}

	public void setExtentionID(int extentionID) {
		this.extentionID = extentionID;
	}

	public Timestamp getExtentionEbdDate() {
		return this.extentionEbdDate;
	}

	public void setExtentionEbdDate(Timestamp extentionEbdDate) {
		this.extentionEbdDate = extentionEbdDate;
	}

	public Timestamp getExtentionStartDate() {
		return this.extentionStartDate;
	}

	public void setExtentionStartDate(Timestamp extentionStartDate) {
		this.extentionStartDate = extentionStartDate;
	}

	public SeasonF1Details getSeasonf1detail() {
		return this.seasonf1detail;
	}

	public void setSeasonf1detail(SeasonF1Details seasonf1detail) {
		this.seasonf1detail = seasonf1detail;
	}

	public SeasonJ1Details getSeasonj1detail() {
		return this.seasonj1detail;
	}

	public void setSeasonj1detail(SeasonJ1Details seasonj1detail) {
		this.seasonj1detail = seasonj1detail;
	}

}