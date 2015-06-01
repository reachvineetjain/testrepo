package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the region database table.
 * 
 */
@Entity
@Table(name="region")
@NamedQuery(name="Region.findAll", query="SELECT r FROM Region r")
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int regionID;

	@Column(nullable=false)
	private byte active;

	@Column(nullable=false, length=50)
	private String regionName;

	//bi-directional many-to-one association to RegionSeason
	@OneToMany(mappedBy="region")
	private List<RegionSeason> regionseasons;

	public Region() {
	}

	public int getRegionID() {
		return this.regionID;
	}

	public void setRegionID(int regionID) {
		this.regionID = regionID;
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

	public List<RegionSeason> getRegionseasons() {
		return this.regionseasons;
	}

	public void setRegionseasons(List<RegionSeason> regionseasons) {
		this.regionseasons = regionseasons;
	}

	public RegionSeason addRegionseason(RegionSeason regionseason) {
		getRegionseasons().add(regionseason);
		regionseason.setRegion(this);

		return regionseason;
	}

	public RegionSeason removeRegionseason(RegionSeason regionseason) {
		getRegionseasons().remove(regionseason);
		regionseason.setRegion(null);

		return regionseason;
	}

}