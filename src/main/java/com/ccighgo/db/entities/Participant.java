package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


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
	@Column(unique=true, nullable=false)
	private Integer participantGoId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Column(length=50)
	private String firstName;

	private Byte guaranteed;

	private Byte isLead;

	@Column(length=50)
	private String lastName;

	@Column(length=300)
	private String photo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	private Byte submittedFlightInfo;

	//bi-directional many-to-one association to FieldStaffParticipant
	@OneToMany(mappedBy="participant")
	private List<FieldStaffParticipant> fieldStaffParticipants;

	//bi-directional many-to-one association to HostFamilyEvaluation
	@OneToMany(mappedBy="participant")
	private List<HostFamilyEvaluation> hostFamilyEvaluations;

	//bi-directional many-to-one association to HostFamilyFinalEvaluation
	@OneToMany(mappedBy="participant")
	private List<HostFamilyFinalEvaluation> hostFamilyFinalEvaluations;

	//bi-directional many-to-one association to HostFamilyParticipant
	@OneToMany(mappedBy="participant")
	private List<HostFamilyParticipant> hostFamilyParticipants;

	//bi-directional many-to-one association to HostFamilyParticipantHistory
	@OneToMany(mappedBy="participant")
	private List<HostFamilyParticipantHistory> hostFamilyParticipantHistories;

	//bi-directional many-to-one association to ParticipantAnnouncementResult
	@OneToMany(mappedBy="participant")
	private List<ParticipantAnnouncementResult> participantAnnouncementResults;

	//bi-directional many-to-one association to ParticipantPermission
	@OneToMany(mappedBy="participant")
	private List<ParticipantPermission> participantPermissions;

	//bi-directional many-to-one association to DepartmentProgramOption
	@ManyToOne
	@JoinColumn(name="departmentProgramOptionId")
	private DepartmentProgramOption departmentProgramOption;

	//bi-directional many-to-one association to DepartmentProgram
	@ManyToOne
	@JoinColumn(name="departmentProgramId")
	private DepartmentProgram departmentProgram;

	//bi-directional many-to-one association to LookupCountry
	@ManyToOne
	@JoinColumn(name="countryId")
	private LookupCountry lookupCountry;

	//bi-directional many-to-one association to ParticipantStatus
	@ManyToOne
	@JoinColumn(name="participantStatusId")
	private ParticipantStatus participantStatus;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId")
	private Partner partner1;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="subPartnerId")
	private Partner partner2;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId")
	private Season season;

	public Participant() {
	}

	public Integer getParticipantGoId() {
		return this.participantGoId;
	}

	public void setParticipantGoId(Integer participantGoId) {
		this.participantGoId = participantGoId;
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

	public Byte getGuaranteed() {
		return this.guaranteed;
	}

	public void setGuaranteed(Byte guaranteed) {
		this.guaranteed = guaranteed;
	}

	public Byte getIsLead() {
		return this.isLead;
	}

	public void setIsLead(Byte isLead) {
		this.isLead = isLead;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Byte getSubmittedFlightInfo() {
		return this.submittedFlightInfo;
	}

	public void setSubmittedFlightInfo(Byte submittedFlightInfo) {
		this.submittedFlightInfo = submittedFlightInfo;
	}

	public List<FieldStaffParticipant> getFieldStaffParticipants() {
		return this.fieldStaffParticipants;
	}

	public void setFieldStaffParticipants(List<FieldStaffParticipant> fieldStaffParticipants) {
		this.fieldStaffParticipants = fieldStaffParticipants;
	}

	public FieldStaffParticipant addFieldStaffParticipant(FieldStaffParticipant fieldStaffParticipant) {
		getFieldStaffParticipants().add(fieldStaffParticipant);
		fieldStaffParticipant.setParticipant(this);

		return fieldStaffParticipant;
	}

	public FieldStaffParticipant removeFieldStaffParticipant(FieldStaffParticipant fieldStaffParticipant) {
		getFieldStaffParticipants().remove(fieldStaffParticipant);
		fieldStaffParticipant.setParticipant(null);

		return fieldStaffParticipant;
	}

	public List<HostFamilyEvaluation> getHostFamilyEvaluations() {
		return this.hostFamilyEvaluations;
	}

	public void setHostFamilyEvaluations(List<HostFamilyEvaluation> hostFamilyEvaluations) {
		this.hostFamilyEvaluations = hostFamilyEvaluations;
	}

	public HostFamilyEvaluation addHostFamilyEvaluation(HostFamilyEvaluation hostFamilyEvaluation) {
		getHostFamilyEvaluations().add(hostFamilyEvaluation);
		hostFamilyEvaluation.setParticipant(this);

		return hostFamilyEvaluation;
	}

	public HostFamilyEvaluation removeHostFamilyEvaluation(HostFamilyEvaluation hostFamilyEvaluation) {
		getHostFamilyEvaluations().remove(hostFamilyEvaluation);
		hostFamilyEvaluation.setParticipant(null);

		return hostFamilyEvaluation;
	}

	public List<HostFamilyFinalEvaluation> getHostFamilyFinalEvaluations() {
		return this.hostFamilyFinalEvaluations;
	}

	public void setHostFamilyFinalEvaluations(List<HostFamilyFinalEvaluation> hostFamilyFinalEvaluations) {
		this.hostFamilyFinalEvaluations = hostFamilyFinalEvaluations;
	}

	public HostFamilyFinalEvaluation addHostFamilyFinalEvaluation(HostFamilyFinalEvaluation hostFamilyFinalEvaluation) {
		getHostFamilyFinalEvaluations().add(hostFamilyFinalEvaluation);
		hostFamilyFinalEvaluation.setParticipant(this);

		return hostFamilyFinalEvaluation;
	}

	public HostFamilyFinalEvaluation removeHostFamilyFinalEvaluation(HostFamilyFinalEvaluation hostFamilyFinalEvaluation) {
		getHostFamilyFinalEvaluations().remove(hostFamilyFinalEvaluation);
		hostFamilyFinalEvaluation.setParticipant(null);

		return hostFamilyFinalEvaluation;
	}

	public List<HostFamilyParticipant> getHostFamilyParticipants() {
		return this.hostFamilyParticipants;
	}

	public void setHostFamilyParticipants(List<HostFamilyParticipant> hostFamilyParticipants) {
		this.hostFamilyParticipants = hostFamilyParticipants;
	}

	public HostFamilyParticipant addHostFamilyParticipant(HostFamilyParticipant hostFamilyParticipant) {
		getHostFamilyParticipants().add(hostFamilyParticipant);
		hostFamilyParticipant.setParticipant(this);

		return hostFamilyParticipant;
	}

	public HostFamilyParticipant removeHostFamilyParticipant(HostFamilyParticipant hostFamilyParticipant) {
		getHostFamilyParticipants().remove(hostFamilyParticipant);
		hostFamilyParticipant.setParticipant(null);

		return hostFamilyParticipant;
	}

	public List<HostFamilyParticipantHistory> getHostFamilyParticipantHistories() {
		return this.hostFamilyParticipantHistories;
	}

	public void setHostFamilyParticipantHistories(List<HostFamilyParticipantHistory> hostFamilyParticipantHistories) {
		this.hostFamilyParticipantHistories = hostFamilyParticipantHistories;
	}

	public HostFamilyParticipantHistory addHostFamilyParticipantHistory(HostFamilyParticipantHistory hostFamilyParticipantHistory) {
		getHostFamilyParticipantHistories().add(hostFamilyParticipantHistory);
		hostFamilyParticipantHistory.setParticipant(this);

		return hostFamilyParticipantHistory;
	}

	public HostFamilyParticipantHistory removeHostFamilyParticipantHistory(HostFamilyParticipantHistory hostFamilyParticipantHistory) {
		getHostFamilyParticipantHistories().remove(hostFamilyParticipantHistory);
		hostFamilyParticipantHistory.setParticipant(null);

		return hostFamilyParticipantHistory;
	}

	public List<ParticipantAnnouncementResult> getParticipantAnnouncementResults() {
		return this.participantAnnouncementResults;
	}

	public void setParticipantAnnouncementResults(List<ParticipantAnnouncementResult> participantAnnouncementResults) {
		this.participantAnnouncementResults = participantAnnouncementResults;
	}

	public ParticipantAnnouncementResult addParticipantAnnouncementResult(ParticipantAnnouncementResult participantAnnouncementResult) {
		getParticipantAnnouncementResults().add(participantAnnouncementResult);
		participantAnnouncementResult.setParticipant(this);

		return participantAnnouncementResult;
	}

	public ParticipantAnnouncementResult removeParticipantAnnouncementResult(ParticipantAnnouncementResult participantAnnouncementResult) {
		getParticipantAnnouncementResults().remove(participantAnnouncementResult);
		participantAnnouncementResult.setParticipant(null);

		return participantAnnouncementResult;
	}

	public List<ParticipantPermission> getParticipantPermissions() {
		return this.participantPermissions;
	}

	public void setParticipantPermissions(List<ParticipantPermission> participantPermissions) {
		this.participantPermissions = participantPermissions;
	}

	public ParticipantPermission addParticipantPermission(ParticipantPermission participantPermission) {
		getParticipantPermissions().add(participantPermission);
		participantPermission.setParticipant(this);

		return participantPermission;
	}

	public ParticipantPermission removeParticipantPermission(ParticipantPermission participantPermission) {
		getParticipantPermissions().remove(participantPermission);
		participantPermission.setParticipant(null);

		return participantPermission;
	}

	public DepartmentProgramOption getDepartmentProgramOption() {
		return this.departmentProgramOption;
	}

	public void setDepartmentProgramOption(DepartmentProgramOption departmentProgramOption) {
		this.departmentProgramOption = departmentProgramOption;
	}

	public DepartmentProgram getDepartmentProgram() {
		return this.departmentProgram;
	}

	public void setDepartmentProgram(DepartmentProgram departmentProgram) {
		this.departmentProgram = departmentProgram;
	}

	public LookupCountry getLookupCountry() {
		return this.lookupCountry;
	}

	public void setLookupCountry(LookupCountry lookupCountry) {
		this.lookupCountry = lookupCountry;
	}

	public ParticipantStatus getParticipantStatus() {
		return this.participantStatus;
	}

	public void setParticipantStatus(ParticipantStatus participantStatus) {
		this.participantStatus = participantStatus;
	}

	public Partner getPartner1() {
		return this.partner1;
	}

	public void setPartner1(Partner partner1) {
		this.partner1 = partner1;
	}

	public Partner getPartner2() {
		return this.partner2;
	}

	public void setPartner2(Partner partner2) {
		this.partner2 = partner2;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

}