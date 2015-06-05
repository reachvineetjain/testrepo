package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RegionSeason database table.
 * 
 */
@Entity
@Table(name="RegionSeason")
@NamedQuery(name="RegionSeason.findAll", query="SELECT r FROM RegionSeason r")
public class RegionSeason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int regionSeasonId;

	//bi-directional many-to-one association to Region
	@ManyToOne
	@JoinColumn(name="regionId", nullable=false)
	private Region region;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId", nullable=false)
	private Season season;

	public RegionSeason() {
	}

	public int getRegionSeasonId() {
		return this.regionSeasonId;
	}

	public void setRegionSeasonId(int regionSeasonId) {
		this.regionSeasonId = regionSeasonId;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

}