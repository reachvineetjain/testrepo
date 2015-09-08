package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Participants database table.
 * 
 */
@Entity
@Table(name="Participants")
@NamedQuery(name="Participant.findAll", query="SELECT p FROM Participant p")
public class Participant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer participantGoId;

	private Integer departmentProgramId;

	@Column(length=50)
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Column(length=50)
	private String firstName;

	private byte guaranteed;

	@Column(length=50)
	private String lastName;

	private Integer participantStatusId;

	private Integer seasonId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	private byte submittedFlightInfo;

	private Integer subPartner;

	//bi-directional many-to-one association to LookupCountry
	@ManyToOne
	@JoinColumn(name="countryId")
	private LookupCountry lookupCountry;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId")
	private Partner partner;

	public Participant() {
	}

	public Integer getParticipantGoId() {
		return this.participantGoId;
	}

	public void setParticipantGoId(Integer participantGoId) {
		this.participantGoId = participantGoId;
	}

	public Integer getDepartmentProgramId() {
		return this.departmentProgramId;
	}

	public void setDepartmentProgramId(Integer departmentProgramId) {
		this.departmentProgramId = departmentProgramId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public byte getGuaranteed() {
		return this.guaranteed;
	}

	public void setGuaranteed(byte guaranteed) {
		this.guaranteed = guaranteed;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getParticipantStatusId() {
		return this.participantStatusId;
	}

	public void setParticipantStatusId(Integer participantStatusId) {
		this.participantStatusId = participantStatusId;
	}

	public Integer getSeasonId() {
		return this.seasonId;
	}

	public void setSeasonId(Integer seasonId) {
		this.seasonId = seasonId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public byte getSubmittedFlightInfo() {
		return this.submittedFlightInfo;
	}

	public void setSubmittedFlightInfo(byte submittedFlightInfo) {
		this.submittedFlightInfo = submittedFlightInfo;
	}

	public Integer getSubPartner() {
		return this.subPartner;
	}

	public void setSubPartner(Integer subPartner) {
		this.subPartner = subPartner;
	}

	public LookupCountry getLookupCountry() {
		return this.lookupCountry;
	}

	public void setLookupCountry(LookupCountry lookupCountry) {
		this.lookupCountry = lookupCountry;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

}