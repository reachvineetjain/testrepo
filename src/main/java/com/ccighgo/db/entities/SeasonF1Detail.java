package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the SeasonF1Details database table.
 * 
 */
@Entity
@Table(name="SeasonF1Details")
@NamedQuery(name="SeasonF1Detail.findAll", query="SELECT s FROM SeasonF1Detail s")
public class SeasonF1Detail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int seasonF1DetailsID;

	@Column(nullable=false)
	private byte allowFieldStaffToStartRenewelProcess;

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

	//bi-directional many-to-one association to ExtensionHSP
	@OneToMany(mappedBy="seasonF1detail")
	private List<ExtensionHSP> extensionHsps;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonID", nullable=false)
	private Season season;

	public SeasonF1Detail() {
	}

	public int getSeasonF1DetailsID() {
		return this.seasonF1DetailsID;
	}

	public void setSeasonF1DetailsID(int seasonF1DetailsID) {
		this.seasonF1DetailsID = seasonF1DetailsID;
	}

	public byte getAllowFieldStaffToStartRenewelProcess() {
		return this.allowFieldStaffToStartRenewelProcess;
	}

	public void setAllowFieldStaffToStartRenewelProcess(byte allowFieldStaffToStartRenewelProcess) {
		this.allowFieldStaffToStartRenewelProcess = allowFieldStaffToStartRenewelProcess;
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

	public List<ExtensionHSP> getExtensionHsps() {
		return this.extensionHsps;
	}

	public void setExtensionHsps(List<ExtensionHSP> extensionHsps) {
		this.extensionHsps = extensionHsps;
	}

	public ExtensionHSP addExtensionHsp(ExtensionHSP extensionHsp) {
		getExtensionHsps().add(extensionHsp);
		extensionHsp.setSeasonF1detail(this);

		return extensionHsp;
	}

	public ExtensionHSP removeExtensionHsp(ExtensionHSP extensionHsp) {
		getExtensionHsps().remove(extensionHsp);
		extensionHsp.setSeasonF1detail(null);

		return extensionHsp;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

}