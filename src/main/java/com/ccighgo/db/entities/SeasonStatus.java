package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SeasonStatus database table.
 * 
 */
@Entity
@Table(name="SeasonStatus")
@NamedQuery(name="SeasonStatus.findAll", query="SELECT s FROM SeasonStatus s")
public class SeasonStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int seasonStatusId;

	@Column(nullable=false)
	private byte active;

	@Column(nullable=false, length=50)
	private String status;

	//bi-directional many-to-one association to Season
	@OneToMany(mappedBy="seasonStatus")
	private List<Season> seasons;

	public SeasonStatus() {
	}

	public int getSeasonStatusId() {
		return this.seasonStatusId;
	}

	public void setSeasonStatusId(int seasonStatusId) {
		this.seasonStatusId = seasonStatusId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Season> getSeasons() {
		return this.seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}

	public Season addSeason(Season season) {
		getSeasons().add(season);
		season.setSeasonStatus(this);

		return season;
	}

	public Season removeSeason(Season season) {
		getSeasons().remove(season);
		season.setSeasonStatus(null);

		return season;
	}

}