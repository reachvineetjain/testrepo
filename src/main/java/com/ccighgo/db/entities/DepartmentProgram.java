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

	private Timestamp createdOn;

	@Column(length=100)
	private String description;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(nullable=false, length=50)
	private String programName;

	//bi-directional many-to-one association to CCIStaffUserProgram
	@OneToMany(mappedBy="departmentProgram")
	private List<CCIStaffUserProgram> ccistaffUserPrograms;

	//bi-directional many-to-one association to DepartmentProgramOption
	@OneToMany(mappedBy="departmentProgram", fetch=FetchType.EAGER)
   @Fetch(value = FetchMode.SUBSELECT)
	private List<DepartmentProgramOption> departmentProgramOptions;

	//bi-directional many-to-one association to LookupDepartment
	@ManyToOne
	@JoinColumn(name="departmentId", nullable=false)
	private LookupDepartment lookupDepartment;

	//bi-directional many-to-one association to SeasonProgramDocument
	@OneToMany(mappedBy="departmentProgram")
	private List<SeasonProgramDocument> seasonProgramDocuments;

	//bi-directional many-to-one association to SeasonProgramNote
	@OneToMany(mappedBy="departmentProgram")
	private List<SeasonProgramNote> seasonProgramNotes;

	//bi-directional many-to-one association to SeasonProgramUpdateLog
	@OneToMany(mappedBy="departmentProgram")
	private List<SeasonProgramUpdateLog> seasonProgramUpdateLogs;

	//bi-directional many-to-one association to FieldStaffLCSeason
	@OneToMany(mappedBy="departmentProgram")
	private List<FieldStaffLCSeason> fieldStaffLcseasons;

	//bi-directional many-to-one association to FieldStaffLeadershipSeason
	@OneToMany(mappedBy="departmentProgram")
	private List<FieldStaffLeadershipSeason> fieldStaffLeadershipSeasons;

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

	public List<CCIStaffUserProgram> getCcistaffUserPrograms() {
		return this.ccistaffUserPrograms;
	}

	public void setCcistaffUserPrograms(List<CCIStaffUserProgram> ccistaffUserPrograms) {
		this.ccistaffUserPrograms = ccistaffUserPrograms;
	}

	public CCIStaffUserProgram addCcistaffUserProgram(CCIStaffUserProgram ccistaffUserProgram) {
		getCcistaffUserPrograms().add(ccistaffUserProgram);
		ccistaffUserProgram.setDepartmentProgram(this);

		return ccistaffUserProgram;
	}

	public CCIStaffUserProgram removeCcistaffUserProgram(CCIStaffUserProgram ccistaffUserProgram) {
		getCcistaffUserPrograms().remove(ccistaffUserProgram);
		ccistaffUserProgram.setDepartmentProgram(null);

		return ccistaffUserProgram;
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

	public List<FieldStaffLeadershipSeason> getFieldStaffLeadershipSeasons() {
		return this.fieldStaffLeadershipSeasons;
	}

	public void setFieldStaffLeadershipSeasons(List<FieldStaffLeadershipSeason> fieldStaffLeadershipSeasons) {
		this.fieldStaffLeadershipSeasons = fieldStaffLeadershipSeasons;
	}

	public FieldStaffLeadershipSeason addFieldStaffLeadershipSeason(FieldStaffLeadershipSeason fieldStaffLeadershipSeason) {
		getFieldStaffLeadershipSeasons().add(fieldStaffLeadershipSeason);
		fieldStaffLeadershipSeason.setDepartmentProgram(this);

		return fieldStaffLeadershipSeason;
	}

	public FieldStaffLeadershipSeason removeFieldStaffLeadershipSeason(FieldStaffLeadershipSeason fieldStaffLeadershipSeason) {
		getFieldStaffLeadershipSeasons().remove(fieldStaffLeadershipSeason);
		fieldStaffLeadershipSeason.setDepartmentProgram(null);

		return fieldStaffLeadershipSeason;
	}

}