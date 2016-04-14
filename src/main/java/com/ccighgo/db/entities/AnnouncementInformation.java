package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the AnnouncementInformation database table.
 * 
 */
@Entity
@Table(name="AnnouncementInformation")
@NamedQuery(name="AnnouncementInformation.findAll", query="SELECT a FROM AnnouncementInformation a")
public class AnnouncementInformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer announcementInformationId;

	@Column(length=200)
	private String announcementPollOption1;

	@Column(length=200)
	private String announcementPollOption2;

	@Column(length=200)
	private String announcementPollOption3;

	@Column(length=200)
	private String announcementPollOption4;

	@Column(length=200)
	private String announcementPollOption5;

	@Column(length=1000)
	private String announement;

	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;

	@Column(length=45)
	private String region;

	@Column(length=45)
	private String targetType;

	@Column(length=45)
	private String targetTypeStatus;

	//bi-directional many-to-one association to AnnouncementCreateUserType
	@ManyToOne
	@JoinColumn(name="announcementCreateTypeUserId", nullable=false)
	private AnnouncementCreateUserType announcementCreateUserType;

	//bi-directional many-to-one association to AnnouncementType
	@ManyToOne
	@JoinColumn(name="announcementTypeId", nullable=false)
	private AnnouncementType announcementType;

	//bi-directional many-to-one association to DepartmentProgram
	@ManyToOne
	@JoinColumn(name="departmentProgramId")
	private DepartmentProgram departmentProgram;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId")
	private LookupDepartmentProgram lookupDepartmentProgram;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId")
	private Season season;

	public AnnouncementInformation() {
	}

	public Integer getAnnouncementInformationId() {
		return this.announcementInformationId;
	}

	public void setAnnouncementInformationId(Integer announcementInformationId) {
		this.announcementInformationId = announcementInformationId;
	}

	public String getAnnouncementPollOption1() {
		return this.announcementPollOption1;
	}

	public void setAnnouncementPollOption1(String announcementPollOption1) {
		this.announcementPollOption1 = announcementPollOption1;
	}

	public String getAnnouncementPollOption2() {
		return this.announcementPollOption2;
	}

	public void setAnnouncementPollOption2(String announcementPollOption2) {
		this.announcementPollOption2 = announcementPollOption2;
	}

	public String getAnnouncementPollOption3() {
		return this.announcementPollOption3;
	}

	public void setAnnouncementPollOption3(String announcementPollOption3) {
		this.announcementPollOption3 = announcementPollOption3;
	}

	public String getAnnouncementPollOption4() {
		return this.announcementPollOption4;
	}

	public void setAnnouncementPollOption4(String announcementPollOption4) {
		this.announcementPollOption4 = announcementPollOption4;
	}

	public String getAnnouncementPollOption5() {
		return this.announcementPollOption5;
	}

	public void setAnnouncementPollOption5(String announcementPollOption5) {
		this.announcementPollOption5 = announcementPollOption5;
	}

	public String getAnnounement() {
		return this.announement;
	}

	public void setAnnounement(String announement) {
		this.announement = announement;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTargetType() {
		return this.targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getTargetTypeStatus() {
		return this.targetTypeStatus;
	}

	public void setTargetTypeStatus(String targetTypeStatus) {
		this.targetTypeStatus = targetTypeStatus;
	}

	public AnnouncementCreateUserType getAnnouncementCreateUserType() {
		return this.announcementCreateUserType;
	}

	public void setAnnouncementCreateUserType(AnnouncementCreateUserType announcementCreateUserType) {
		this.announcementCreateUserType = announcementCreateUserType;
	}

	public AnnouncementType getAnnouncementType() {
		return this.announcementType;
	}

	public void setAnnouncementType(AnnouncementType announcementType) {
		this.announcementType = announcementType;
	}

	public DepartmentProgram getDepartmentProgram() {
		return this.departmentProgram;
	}

	public void setDepartmentProgram(DepartmentProgram departmentProgram) {
		this.departmentProgram = departmentProgram;
	}

	public LookupDepartmentProgram getLookupDepartmentProgram() {
		return this.lookupDepartmentProgram;
	}

	public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		this.lookupDepartmentProgram = lookupDepartmentProgram;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

}