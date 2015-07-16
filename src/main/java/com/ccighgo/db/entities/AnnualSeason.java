package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the AnnualSeason database table.
 * 
 */
@Entity
@Table(name="AnnualSeason")
@NamedQuery(name="AnnualSeason.findAll", query="SELECT a FROM AnnualSeason a")
public class AnnualSeason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int annualSeasonId;

	@Column(nullable=false)
	private byte active;

	@Column(nullable=false, length=50)
	private String annualSeasonName;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to SeasonWPAllocation
	@OneToMany(mappedBy="annualSeason")
	private List<SeasonWPAllocation> seasonWpallocations;

	//bi-directional many-to-one association to SeasonWnTDetail
	@OneToMany(mappedBy="annualSeason")
	private List<SeasonWnTDetail> seasonWnTdetails;

	public AnnualSeason() {
	}

	public int getAnnualSeasonId() {
		return this.annualSeasonId;
	}

	public void setAnnualSeasonId(int annualSeasonId) {
		this.annualSeasonId = annualSeasonId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getAnnualSeasonName() {
		return this.annualSeasonName;
	}

	public void setAnnualSeasonName(String annualSeasonName) {
		this.annualSeasonName = annualSeasonName;
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

	public List<SeasonWPAllocation> getSeasonWpallocations() {
		return this.seasonWpallocations;
	}

	public void setSeasonWpallocations(List<SeasonWPAllocation> seasonWpallocations) {
		this.seasonWpallocations = seasonWpallocations;
	}

	public SeasonWPAllocation addSeasonWpallocation(SeasonWPAllocation seasonWpallocation) {
		getSeasonWpallocations().add(seasonWpallocation);
		seasonWpallocation.setAnnualSeason(this);

		return seasonWpallocation;
	}

	public SeasonWPAllocation removeSeasonWpallocation(SeasonWPAllocation seasonWpallocation) {
		getSeasonWpallocations().remove(seasonWpallocation);
		seasonWpallocation.setAnnualSeason(null);

		return seasonWpallocation;
	}

	public List<SeasonWnTDetail> getSeasonWnTdetails() {
		return this.seasonWnTdetails;
	}

	public void setSeasonWnTdetails(List<SeasonWnTDetail> seasonWnTdetails) {
		this.seasonWnTdetails = seasonWnTdetails;
	}

	public SeasonWnTDetail addSeasonWnTdetail(SeasonWnTDetail seasonWnTdetail) {
		getSeasonWnTdetails().add(seasonWnTdetail);
		seasonWnTdetail.setAnnualSeason(this);

		return seasonWnTdetail;
	}

	public SeasonWnTDetail removeSeasonWnTdetail(SeasonWnTDetail seasonWnTdetail) {
		getSeasonWnTdetails().remove(seasonWnTdetail);
		seasonWnTdetail.setAnnualSeason(null);

		return seasonWnTdetail;
	}

}