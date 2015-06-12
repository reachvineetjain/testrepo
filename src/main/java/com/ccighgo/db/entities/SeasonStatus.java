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

	//bi-directional many-to-one association to SeasonCAPDetail
	@OneToMany(mappedBy="seasonStatus")
	private List<SeasonCAPDetail> seasonCapdetails;

	//bi-directional many-to-one association to SeasonF1Detail
	@OneToMany(mappedBy="seasonStatus")
	private List<SeasonF1Detail> seasonF1details;

	//bi-directional many-to-one association to SeasonHSADetail
	@OneToMany(mappedBy="seasonStatus")
	private List<SeasonHSADetail> seasonHsadetails;

	//bi-directional many-to-one association to SeasonJ1Detail
	@OneToMany(mappedBy="seasonStatus")
	private List<SeasonJ1Detail> seasonJ1details;

	//bi-directional many-to-one association to SeasonLSDetail
	@OneToMany(mappedBy="seasonStatus")
	private List<SeasonLSDetail> seasonLsdetails;

	//bi-directional many-to-one association to SeasonTADetail
	@OneToMany(mappedBy="seasonStatus")
	private List<SeasonTADetail> seasonTadetails;

	//bi-directional many-to-one association to SeasonWADetail
	@OneToMany(mappedBy="seasonStatus")
	private List<SeasonWADetail> seasonWadetails;

	//bi-directional many-to-one association to SeasonWnTDetail
	@OneToMany(mappedBy="seasonStatus")
	private List<SeasonWnTDetail> seasonWnTdetails;

	//bi-directional many-to-one association to SeasonVADetail
	@OneToMany(mappedBy="seasonStatus")
	private List<SeasonVADetail> seasonVadetails;

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

	public List<SeasonCAPDetail> getSeasonCapdetails() {
		return this.seasonCapdetails;
	}

	public void setSeasonCapdetails(List<SeasonCAPDetail> seasonCapdetails) {
		this.seasonCapdetails = seasonCapdetails;
	}

	public SeasonCAPDetail addSeasonCapdetail(SeasonCAPDetail seasonCapdetail) {
		getSeasonCapdetails().add(seasonCapdetail);
		seasonCapdetail.setSeasonStatus(this);

		return seasonCapdetail;
	}

	public SeasonCAPDetail removeSeasonCapdetail(SeasonCAPDetail seasonCapdetail) {
		getSeasonCapdetails().remove(seasonCapdetail);
		seasonCapdetail.setSeasonStatus(null);

		return seasonCapdetail;
	}

	public List<SeasonF1Detail> getSeasonF1details() {
		return this.seasonF1details;
	}

	public void setSeasonF1details(List<SeasonF1Detail> seasonF1details) {
		this.seasonF1details = seasonF1details;
	}

	public SeasonF1Detail addSeasonF1detail(SeasonF1Detail seasonF1detail) {
		getSeasonF1details().add(seasonF1detail);
		seasonF1detail.setSeasonStatus(this);

		return seasonF1detail;
	}

	public SeasonF1Detail removeSeasonF1detail(SeasonF1Detail seasonF1detail) {
		getSeasonF1details().remove(seasonF1detail);
		seasonF1detail.setSeasonStatus(null);

		return seasonF1detail;
	}

	public List<SeasonHSADetail> getSeasonHsadetails() {
		return this.seasonHsadetails;
	}

	public void setSeasonHsadetails(List<SeasonHSADetail> seasonHsadetails) {
		this.seasonHsadetails = seasonHsadetails;
	}

	public SeasonHSADetail addSeasonHsadetail(SeasonHSADetail seasonHsadetail) {
		getSeasonHsadetails().add(seasonHsadetail);
		seasonHsadetail.setSeasonStatus(this);

		return seasonHsadetail;
	}

	public SeasonHSADetail removeSeasonHsadetail(SeasonHSADetail seasonHsadetail) {
		getSeasonHsadetails().remove(seasonHsadetail);
		seasonHsadetail.setSeasonStatus(null);

		return seasonHsadetail;
	}

	public List<SeasonJ1Detail> getSeasonJ1details() {
		return this.seasonJ1details;
	}

	public void setSeasonJ1details(List<SeasonJ1Detail> seasonJ1details) {
		this.seasonJ1details = seasonJ1details;
	}

	public SeasonJ1Detail addSeasonJ1detail(SeasonJ1Detail seasonJ1detail) {
		getSeasonJ1details().add(seasonJ1detail);
		seasonJ1detail.setSeasonStatus(this);

		return seasonJ1detail;
	}

	public SeasonJ1Detail removeSeasonJ1detail(SeasonJ1Detail seasonJ1detail) {
		getSeasonJ1details().remove(seasonJ1detail);
		seasonJ1detail.setSeasonStatus(null);

		return seasonJ1detail;
	}

	public List<SeasonLSDetail> getSeasonLsdetails() {
		return this.seasonLsdetails;
	}

	public void setSeasonLsdetails(List<SeasonLSDetail> seasonLsdetails) {
		this.seasonLsdetails = seasonLsdetails;
	}

	public SeasonLSDetail addSeasonLsdetail(SeasonLSDetail seasonLsdetail) {
		getSeasonLsdetails().add(seasonLsdetail);
		seasonLsdetail.setSeasonStatus(this);

		return seasonLsdetail;
	}

	public SeasonLSDetail removeSeasonLsdetail(SeasonLSDetail seasonLsdetail) {
		getSeasonLsdetails().remove(seasonLsdetail);
		seasonLsdetail.setSeasonStatus(null);

		return seasonLsdetail;
	}

	public List<SeasonTADetail> getSeasonTadetails() {
		return this.seasonTadetails;
	}

	public void setSeasonTadetails(List<SeasonTADetail> seasonTadetails) {
		this.seasonTadetails = seasonTadetails;
	}

	public SeasonTADetail addSeasonTadetail(SeasonTADetail seasonTadetail) {
		getSeasonTadetails().add(seasonTadetail);
		seasonTadetail.setSeasonStatus(this);

		return seasonTadetail;
	}

	public SeasonTADetail removeSeasonTadetail(SeasonTADetail seasonTadetail) {
		getSeasonTadetails().remove(seasonTadetail);
		seasonTadetail.setSeasonStatus(null);

		return seasonTadetail;
	}

	public List<SeasonWADetail> getSeasonWadetails() {
		return this.seasonWadetails;
	}

	public void setSeasonWadetails(List<SeasonWADetail> seasonWadetails) {
		this.seasonWadetails = seasonWadetails;
	}

	public SeasonWADetail addSeasonWadetail(SeasonWADetail seasonWadetail) {
		getSeasonWadetails().add(seasonWadetail);
		seasonWadetail.setSeasonStatus(this);

		return seasonWadetail;
	}

	public SeasonWADetail removeSeasonWadetail(SeasonWADetail seasonWadetail) {
		getSeasonWadetails().remove(seasonWadetail);
		seasonWadetail.setSeasonStatus(null);

		return seasonWadetail;
	}

	public List<SeasonWnTDetail> getSeasonWnTdetails() {
		return this.seasonWnTdetails;
	}

	public void setSeasonWnTdetails(List<SeasonWnTDetail> seasonWnTdetails) {
		this.seasonWnTdetails = seasonWnTdetails;
	}

	public SeasonWnTDetail addSeasonWnTdetail(SeasonWnTDetail seasonWnTdetail) {
		getSeasonWnTdetails().add(seasonWnTdetail);
		seasonWnTdetail.setSeasonStatus(this);

		return seasonWnTdetail;
	}

	public SeasonWnTDetail removeSeasonWnTdetail(SeasonWnTDetail seasonWnTdetail) {
		getSeasonWnTdetails().remove(seasonWnTdetail);
		seasonWnTdetail.setSeasonStatus(null);

		return seasonWnTdetail;
	}

	public List<SeasonVADetail> getSeasonVadetails() {
		return this.seasonVadetails;
	}

	public void setSeasonVadetails(List<SeasonVADetail> seasonVadetails) {
		this.seasonVadetails = seasonVadetails;
	}

	public SeasonVADetail addSeasonVadetail(SeasonVADetail seasonVadetail) {
		getSeasonVadetails().add(seasonVadetail);
		seasonVadetail.setSeasonStatus(this);

		return seasonVadetail;
	}

	public SeasonVADetail removeSeasonVadetail(SeasonVADetail seasonVadetail) {
		getSeasonVadetails().remove(seasonVadetail);
		seasonVadetail.setSeasonStatus(null);

		return seasonVadetail;
	}

}