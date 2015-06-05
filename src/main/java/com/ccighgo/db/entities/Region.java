package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Region database table.
 * 
 */
@Entity
@Table(name="Region")
@NamedQuery(name="Region.findAll", query="SELECT r FROM Region r")
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int regionId;

	@Column(nullable=false)
	private byte active;

	@Column(nullable=false, length=50)
	private String regionName;

	//bi-directional many-to-one association to RegionSeason
	@OneToMany(mappedBy="region")
	private List<RegionSeason> regionSeasons;

	public Region() {
	}

	public int getRegionId() {
		return this.regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public List<RegionSeason> getRegionSeasons() {
		return this.regionSeasons;
	}

	public void setRegionSeasons(List<RegionSeason> regionSeasons) {
		this.regionSeasons = regionSeasons;
	}

	public RegionSeason addRegionSeason(RegionSeason regionSeason) {
		getRegionSeasons().add(regionSeason);
		regionSeason.setRegion(this);

		return regionSeason;
	}

	public RegionSeason removeRegionSeason(RegionSeason regionSeason) {
		getRegionSeasons().remove(regionSeason);
		regionSeason.setRegion(null);

		return regionSeason;
	}

}