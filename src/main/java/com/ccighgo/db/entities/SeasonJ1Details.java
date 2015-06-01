package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the seasonj1details database table.
 * 
 */
@Entity
@Table(name="seasonj1details")
@NamedQuery(name="SeasonJ1Details.findAll", query="SELECT s FROM SeasonJ1Details s")
public class SeasonJ1Details implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int seasonJ1DetailsID;

	@Column(nullable=false)
	private Timestamp firstSemEarliestBirthDate;

	@Column(nullable=false)
	private Timestamp firstSemEndDate;

	@Column(nullable=false)
	private Timestamp firstSemLatestBirthDate;

	@Column(nullable=false)
	private Timestamp firstSemStartDate;

	@Column(nullable=false)
	private byte programStatus;

	@Column(nullable=false)
	private Timestamp secSemEarliestBirthDate;

	@Column(nullable=false)
	private Timestamp secSemEndDate;

	@Column(nullable=false)
	private Timestamp secSemLatestBirthDate;

	@Column(nullable=false)
	private Timestamp secSemStartDate;

	private byte showGuaranteed;

	private byte showUnguaranteed;

	//bi-directional many-to-one association to ExtensionHSP
	@OneToMany(mappedBy="seasonj1detail")
	private List<ExtensionHSP> extensionhsps;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonID", nullable=false)
	private Season season;

	public SeasonJ1Details() {
	}

	public int getSeasonJ1DetailsID() {
		return this.seasonJ1DetailsID;
	}

	public void setSeasonJ1DetailsID(int seasonJ1DetailsID) {
		this.seasonJ1DetailsID = seasonJ1DetailsID;
	}

	public Timestamp getFirstSemEarliestBirthDate() {
		return this.firstSemEarliestBirthDate;
	}

	public void setFirstSemEarliestBirthDate(Timestamp firstSemEarliestBirthDate) {
		this.firstSemEarliestBirthDate = firstSemEarliestBirthDate;
	}

	public Timestamp getFirstSemEndDate() {
		return this.firstSemEndDate;
	}

	public void setFirstSemEndDate(Timestamp firstSemEndDate) {
		this.firstSemEndDate = firstSemEndDate;
	}

	public Timestamp getFirstSemLatestBirthDate() {
		return this.firstSemLatestBirthDate;
	}

	public void setFirstSemLatestBirthDate(Timestamp firstSemLatestBirthDate) {
		this.firstSemLatestBirthDate = firstSemLatestBirthDate;
	}

	public Timestamp getFirstSemStartDate() {
		return this.firstSemStartDate;
	}

	public void setFirstSemStartDate(Timestamp firstSemStartDate) {
		this.firstSemStartDate = firstSemStartDate;
	}

	public byte getProgramStatus() {
		return this.programStatus;
	}

	public void setProgramStatus(byte programStatus) {
		this.programStatus = programStatus;
	}

	public Timestamp getSecSemEarliestBirthDate() {
		return this.secSemEarliestBirthDate;
	}

	public void setSecSemEarliestBirthDate(Timestamp secSemEarliestBirthDate) {
		this.secSemEarliestBirthDate = secSemEarliestBirthDate;
	}

	public Timestamp getSecSemEndDate() {
		return this.secSemEndDate;
	}

	public void setSecSemEndDate(Timestamp secSemEndDate) {
		this.secSemEndDate = secSemEndDate;
	}

	public Timestamp getSecSemLatestBirthDate() {
		return this.secSemLatestBirthDate;
	}

	public void setSecSemLatestBirthDate(Timestamp secSemLatestBirthDate) {
		this.secSemLatestBirthDate = secSemLatestBirthDate;
	}

	public Timestamp getSecSemStartDate() {
		return this.secSemStartDate;
	}

	public void setSecSemStartDate(Timestamp secSemStartDate) {
		this.secSemStartDate = secSemStartDate;
	}

	public byte getShowGuaranteed() {
		return this.showGuaranteed;
	}

	public void setShowGuaranteed(byte showGuaranteed) {
		this.showGuaranteed = showGuaranteed;
	}

	public byte getShowUnguaranteed() {
		return this.showUnguaranteed;
	}

	public void setShowUnguaranteed(byte showUnguaranteed) {
		this.showUnguaranteed = showUnguaranteed;
	}

	public List<ExtensionHSP> getExtensionhsps() {
		return this.extensionhsps;
	}

	public void setExtensionhsps(List<ExtensionHSP> extensionhsps) {
		this.extensionhsps = extensionhsps;
	}

	public ExtensionHSP addExtensionhsp(ExtensionHSP extensionhsp) {
		getExtensionhsps().add(extensionhsp);
		extensionhsp.setSeasonj1detail(this);

		return extensionhsp;
	}

	public ExtensionHSP removeExtensionhsp(ExtensionHSP extensionhsp) {
		getExtensionhsps().remove(extensionhsp);
		extensionhsp.setSeasonj1detail(null);

		return extensionhsp;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

}