package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilyContactHistory database table.
 * 
 */
@Entity
@NamedQuery(name="HostFamilyContactHistory.findAll", query="SELECT h FROM HostFamilyContactHistory h")
public class HostFamilyContactHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer hostFamilyContactHistoryId;

	private String agenda;

	private String contactMode;

	private String duration;

	private Byte isDone;

	private String notes;

	@Temporal(TemporalType.TIMESTAMP)
	private Date praposedContactDate;

	private String praposedTime;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId",insertable=false,updatable=false)
	private HostFamilySeason hostFamilySeason;

	public HostFamilyContactHistory() {
	}

	public Integer getHostFamilyContactHistoryId() {
		return this.hostFamilyContactHistoryId;
	}

	public void setHostFamilyContactHistoryId(Integer hostFamilyContactHistoryId) {
		this.hostFamilyContactHistoryId = hostFamilyContactHistoryId;
	}

	public String getAgenda() {
		return this.agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}

	public String getContactMode() {
		return this.contactMode;
	}

	public void setContactMode(String contactMode) {
		this.contactMode = contactMode;
	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Byte getIsDone() {
		return this.isDone;
	}

	public void setIsDone(Byte isDone) {
		this.isDone = isDone;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getPraposedContactDate() {
		return this.praposedContactDate;
	}

	public void setPraposedContactDate(Date praposedContactDate) {
		this.praposedContactDate = praposedContactDate;
	}

	public String getPraposedTime() {
		return this.praposedTime;
	}

	public void setPraposedTime(String praposedTime) {
		this.praposedTime = praposedTime;
	}

	public HostFamilySeason getHostFamilySeason() {
		return this.hostFamilySeason;
	}

	public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
		this.hostFamilySeason = hostFamilySeason;
	}

}