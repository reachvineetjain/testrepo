package com.ccighgo.db.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the DepartmentPrograms database table.
 * 
 */
@Entity
@Table(name="DepartmentPrograms")
@NamedQuery(name="DepartmentProgram.findAll", query="SELECT d FROM DepartmentProgram d")
public class DepartmentProgram implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer departmentProgramId;

	@Column(nullable=false)
	private Integer createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(length=100)
	private String description;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(nullable=false, length=50)
	private String programName;

	//bi-directional many-to-one association to DepartmentProgramOption
	@OneToMany(mappedBy = "departmentProgram", fetch = FetchType.EAGER)
	 @Fetch(value = FetchMode.SUBSELECT)
	private List<DepartmentProgramOption> departmentProgramOptions;

	//bi-directional many-to-one association to LookupDepartment
	@ManyToOne
	@JoinColumn(name="departmentId", nullable=false)
	private LookupDepartment lookupDepartment;

	//bi-directional many-to-one association to FieldStaffLCSeason
	@OneToMany(mappedBy="departmentProgram")
	private List<FieldStaffLCSeason> fieldStaffLcseasons;

	//bi-directional many-to-one association to FieldStaffLeadershipSeasonDetail
	@OneToMany(mappedBy="departmentProgram")
	private List<FieldStaffLeadershipSeasonDetail> fieldStaffLeadershipSeasonDetails;

	//bi-directional many-to-one association to FieldStaffWorkQueue
	@OneToMany(mappedBy="departmentProgram")
	private List<FieldStaffWorkQueue> fieldStaffWorkQueues;

	//bi-directional many-to-one association to Participant
	@OneToMany(mappedBy="departmentProgram")
	private List<Participant> participants;

	//bi-directional many-to-one association to PartnerAnnouncement
	@OneToMany(mappedBy="departmentProgram")
	private List<PartnerAnnouncement> partnerAnnouncements;

	//bi-directional many-to-one association to PartnerSeason
	@OneToMany(mappedBy="departmentProgram")
	private List<PartnerSeason> partnerSeasons;

	//bi-directional many-to-one association to PartnerWorkQueue
	@OneToMany(mappedBy="departmentProgram")
	private List<PartnerWorkQueue> partnerWorkQueues;

	//bi-directional many-to-one association to SeasonProgramDocument
	@OneToMany(mappedBy="departmentProgram")
	private List<SeasonProgramDocument> seasonProgramDocuments;

	//bi-directional many-to-one association to SeasonProgramNote
	@OneToMany(mappedBy="departmentProgram")
	private List<SeasonProgramNote> seasonProgramNotes;

	//bi-directional many-to-one association to SeasonProgramUpdateLog
	@OneToMany(mappedBy="departmentProgram")
	private List<SeasonProgramUpdateLog> seasonProgramUpdateLogs;

	public DepartmentProgram() {
	}

	public Integer getDepartmentProgramId() {
		return this.departmentProgramId;
	}

	public void setDepartmentProgramId(Integer departmentProgramId) {
		this.departmentProgramId = departmentProgramId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getProgramName() {
		return this.programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public List<DepartmentProgramOption> getDepartmentProgramOptions() {
		return this.departmentProgramOptions;
	}

	public void setDepartmentProgramOptions(List<DepartmentProgramOption> departmentProgramOptions) {
		this.departmentProgramOptions = departmentProgramOptions;
	}

	public DepartmentProgramOption addDepartmentProgramOption(DepartmentProgramOption departmentProgramOption) {
		getDepartmentProgramOptions().add(departmentProgramOption);
		departmentProgramOption.setDepartmentProgram(this);

		return departmentProgramOption;
	}

	public DepartmentProgramOption removeDepartmentProgramOption(DepartmentProgramOption departmentProgramOption) {
		getDepartmentProgramOptions().remove(departmentProgramOption);
		departmentProgramOption.setDepartmentProgram(null);

		return departmentProgramOption;
	}

	public LookupDepartment getLookupDepartment() {
		return this.lookupDepartment;
	}

	public void setLookupDepartment(LookupDepartment lookupDepartment) {
		this.lookupDepartment = lookupDepartment;
	}

	public List<FieldStaffLCSeason> getFieldStaffLcseasons() {
		return this.fieldStaffLcseasons;
	}

	public void setFieldStaffLcseasons(List<FieldStaffLCSeason> fieldStaffLcseasons) {
		this.fieldStaffLcseasons = fieldStaffLcseasons;
	}

	public FieldStaffLCSeason addFieldStaffLcseason(FieldStaffLCSeason fieldStaffLcseason) {
		getFieldStaffLcseasons().add(fieldStaffLcseason);
		fieldStaffLcseason.setDepartmentProgram(this);

		return fieldStaffLcseason;
	}

	public FieldStaffLCSeason removeFieldStaffLcseason(FieldStaffLCSeason fieldStaffLcseason) {
		getFieldStaffLcseasons().remove(fieldStaffLcseason);
		fieldStaffLcseason.setDepartmentProgram(null);

		return fieldStaffLcseason;
	}

	public List<FieldStaffLeadershipSeasonDetail> getFieldStaffLeadershipSeasonDetails() {
		return this.fieldStaffLeadershipSeasonDetails;
	}

	public void setFieldStaffLeadershipSeasonDetails(List<FieldStaffLeadershipSeasonDetail> fieldStaffLeadershipSeasonDetails) {
		this.fieldStaffLeadershipSeasonDetails = fieldStaffLeadershipSeasonDetails;
	}

	public FieldStaffLeadershipSeasonDetail addFieldStaffLeadershipSeasonDetail(FieldStaffLeadershipSeasonDetail fieldStaffLeadershipSeasonDetail) {
		getFieldStaffLeadershipSeasonDetails().add(fieldStaffLeadershipSeasonDetail);
		fieldStaffLeadershipSeasonDetail.setDepartmentProgram(this);

		return fieldStaffLeadershipSeasonDetail;
	}

	public FieldStaffLeadershipSeasonDetail removeFieldStaffLeadershipSeasonDetail(FieldStaffLeadershipSeasonDetail fieldStaffLeadershipSeasonDetail) {
		getFieldStaffLeadershipSeasonDetails().remove(fieldStaffLeadershipSeasonDetail);
		fieldStaffLeadershipSeasonDetail.setDepartmentProgram(null);

		return fieldStaffLeadershipSeasonDetail;
	}

	public List<FieldStaffWorkQueue> getFieldStaffWorkQueues() {
		return this.fieldStaffWorkQueues;
	}

	public void setFieldStaffWorkQueues(List<FieldStaffWorkQueue> fieldStaffWorkQueues) {
		this.fieldStaffWorkQueues = fieldStaffWorkQueues;
	}

	public FieldStaffWorkQueue addFieldStaffWorkQueue(FieldStaffWorkQueue fieldStaffWorkQueue) {
		getFieldStaffWorkQueues().add(fieldStaffWorkQueue);
		fieldStaffWorkQueue.setDepartmentProgram(this);

		return fieldStaffWorkQueue;
	}

	public FieldStaffWorkQueue removeFieldStaffWorkQueue(FieldStaffWorkQueue fieldStaffWorkQueue) {
		getFieldStaffWorkQueues().remove(fieldStaffWorkQueue);
		fieldStaffWorkQueue.setDepartmentProgram(null);

		return fieldStaffWorkQueue;
	}

	public List<Participant> getParticipants() {
		return this.participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public Participant addParticipant(Participant participant) {
		getParticipants().add(participant);
		participant.setDepartmentProgram(this);

		return participant;
	}

	public Participant removeParticipant(Participant participant) {
		getParticipants().remove(participant);
		participant.setDepartmentProgram(null);

		return participant;
	}

	public List<PartnerAnnouncement> getPartnerAnnouncements() {
		return this.partnerAnnouncements;
	}

	public void setPartnerAnnouncements(List<PartnerAnnouncement> partnerAnnouncements) {
		this.partnerAnnouncements = partnerAnnouncements;
	}

	public PartnerAnnouncement addPartnerAnnouncement(PartnerAnnouncement partnerAnnouncement) {
		getPartnerAnnouncements().add(partnerAnnouncement);
		partnerAnnouncement.setDepartmentProgram(this);

		return partnerAnnouncement;
	}

	public PartnerAnnouncement removePartnerAnnouncement(PartnerAnnouncement partnerAnnouncement) {
		getPartnerAnnouncements().remove(partnerAnnouncement);
		partnerAnnouncement.setDepartmentProgram(null);

		return partnerAnnouncement;
	}

	public List<PartnerSeason> getPartnerSeasons() {
		return this.partnerSeasons;
	}

	public void setPartnerSeasons(List<PartnerSeason> partnerSeasons) {
		this.partnerSeasons = partnerSeasons;
	}

	public PartnerSeason addPartnerSeason(PartnerSeason partnerSeason) {
		getPartnerSeasons().add(partnerSeason);
		partnerSeason.setDepartmentProgram(this);

		return partnerSeason;
	}

	public PartnerSeason removePartnerSeason(PartnerSeason partnerSeason) {
		getPartnerSeasons().remove(partnerSeason);
		partnerSeason.setDepartmentProgram(null);

		return partnerSeason;
	}

	public List<PartnerWorkQueue> getPartnerWorkQueues() {
		return this.partnerWorkQueues;
	}

	public void setPartnerWorkQueues(List<PartnerWorkQueue> partnerWorkQueues) {
		this.partnerWorkQueues = partnerWorkQueues;
	}

	public PartnerWorkQueue addPartnerWorkQueue(PartnerWorkQueue partnerWorkQueue) {
		getPartnerWorkQueues().add(partnerWorkQueue);
		partnerWorkQueue.setDepartmentProgram(this);

		return partnerWorkQueue;
	}

	public PartnerWorkQueue removePartnerWorkQueue(PartnerWorkQueue partnerWorkQueue) {
		getPartnerWorkQueues().remove(partnerWorkQueue);
		partnerWorkQueue.setDepartmentProgram(null);

		return partnerWorkQueue;
	}

	public List<SeasonProgramDocument> getSeasonProgramDocuments() {
		return this.seasonProgramDocuments;
	}

	public void setSeasonProgramDocuments(List<SeasonProgramDocument> seasonProgramDocuments) {
		this.seasonProgramDocuments = seasonProgramDocuments;
	}

	public SeasonProgramDocument addSeasonProgramDocument(SeasonProgramDocument seasonProgramDocument) {
		getSeasonProgramDocuments().add(seasonProgramDocument);
		seasonProgramDocument.setDepartmentProgram(this);

		return seasonProgramDocument;
	}

	public SeasonProgramDocument removeSeasonProgramDocument(SeasonProgramDocument seasonProgramDocument) {
		getSeasonProgramDocuments().remove(seasonProgramDocument);
		seasonProgramDocument.setDepartmentProgram(null);

		return seasonProgramDocument;
	}

	public List<SeasonProgramNote> getSeasonProgramNotes() {
		return this.seasonProgramNotes;
	}

	public void setSeasonProgramNotes(List<SeasonProgramNote> seasonProgramNotes) {
		this.seasonProgramNotes = seasonProgramNotes;
	}

	public SeasonProgramNote addSeasonProgramNote(SeasonProgramNote seasonProgramNote) {
		getSeasonProgramNotes().add(seasonProgramNote);
		seasonProgramNote.setDepartmentProgram(this);

		return seasonProgramNote;
	}

	public SeasonProgramNote removeSeasonProgramNote(SeasonProgramNote seasonProgramNote) {
		getSeasonProgramNotes().remove(seasonProgramNote);
		seasonProgramNote.setDepartmentProgram(null);

		return seasonProgramNote;
	}

	public List<SeasonProgramUpdateLog> getSeasonProgramUpdateLogs() {
		return this.seasonProgramUpdateLogs;
	}

	public void setSeasonProgramUpdateLogs(List<SeasonProgramUpdateLog> seasonProgramUpdateLogs) {
		this.seasonProgramUpdateLogs = seasonProgramUpdateLogs;
	}

	public SeasonProgramUpdateLog addSeasonProgramUpdateLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
		getSeasonProgramUpdateLogs().add(seasonProgramUpdateLog);
		seasonProgramUpdateLog.setDepartmentProgram(this);

		return seasonProgramUpdateLog;
	}

	public SeasonProgramUpdateLog removeSeasonProgramUpdateLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
		getSeasonProgramUpdateLogs().remove(seasonProgramUpdateLog);
		seasonProgramUpdateLog.setDepartmentProgram(null);

		return seasonProgramUpdateLog;
	}

}