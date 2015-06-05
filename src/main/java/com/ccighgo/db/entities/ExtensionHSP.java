package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ExtensionHSP database table.
 * 
 */
@Entity
@Table(name="ExtensionHSP")
@NamedQuery(name="ExtensionHSP.findAll", query="SELECT e FROM ExtensionHSP e")
public class ExtensionHSP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int extentionId;

	@Column(nullable=false)
	private Timestamp extentionEbdDate;

	@Column(nullable=false)
	private Timestamp extentionStartDate;

	//bi-directional many-to-one association to SeasonF1Detail
	@ManyToOne
	@JoinColumn(name="seasonF1DetailsId", nullable=false)
	private SeasonF1Detail seasonF1detail;

	//bi-directional many-to-one association to SeasonJ1Detail
	@ManyToOne
	@JoinColumn(name="seasonJ1DetailsId", nullable=false)
	private SeasonJ1Detail seasonJ1detail;

	public ExtensionHSP() {
	}

	public int getExtentionId() {
		return this.extentionId;
	}

	public void setExtentionId(int extentionId) {
		this.extentionId = extentionId;
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

	public SeasonF1Detail getSeasonF1detail() {
		return this.seasonF1detail;
	}

	public void setSeasonF1detail(SeasonF1Detail seasonF1detail) {
		this.seasonF1detail = seasonF1detail;
	}

	public SeasonJ1Detail getSeasonJ1detail() {
		return this.seasonJ1detail;
	}

	public void setSeasonJ1detail(SeasonJ1Detail seasonJ1detail) {
		this.seasonJ1detail = seasonJ1detail;
	}

}