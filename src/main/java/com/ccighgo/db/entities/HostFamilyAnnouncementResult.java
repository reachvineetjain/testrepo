package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilyAnnouncementResults database table.
 * 
 */
@Entity
@Table(name="HostFamilyAnnouncementResults")
@NamedQuery(name="HostFamilyAnnouncementResult.findAll", query="SELECT h FROM HostFamilyAnnouncementResult h")
public class HostFamilyAnnouncementResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyAnnouncementResultsId;

	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Column(length=200)
	private String hostFamilyAnnouncementResultOption;

	//bi-directional many-to-one association to AnnouncementCreateUserType
	@ManyToOne
	@JoinColumn(name="announcementCreateTypeUserId")
	private AnnouncementCreateUserType announcementCreateUserType;

	//bi-directional many-to-one association to AnnouncementType
	@ManyToOne
	@JoinColumn(name="announcementTypeId")
	private AnnouncementType announcementType;

	//bi-directional many-to-one association to HostFamily
	@ManyToOne
	@JoinColumn(name="hostFamilyGoId")
	private HostFamily hostFamily;

	public HostFamilyAnnouncementResult() {
	}

	public Integer getHostFamilyAnnouncementResultsId() {
		return this.hostFamilyAnnouncementResultsId;
	}

	public void setHostFamilyAnnouncementResultsId(Integer hostFamilyAnnouncementResultsId) {
		this.hostFamilyAnnouncementResultsId = hostFamilyAnnouncementResultsId;
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

	public String getHostFamilyAnnouncementResultOption() {
		return this.hostFamilyAnnouncementResultOption;
	}

	public void setHostFamilyAnnouncementResultOption(String hostFamilyAnnouncementResultOption) {
		this.hostFamilyAnnouncementResultOption = hostFamilyAnnouncementResultOption;
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

	public HostFamily getHostFamily() {
		return this.hostFamily;
	}

	public void setHostFamily(HostFamily hostFamily) {
		this.hostFamily = hostFamily;
	}

}