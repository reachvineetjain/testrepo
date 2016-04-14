package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PartnerAnnouncementResults database table.
 * 
 */
@Entity
@Table(name="PartnerAnnouncementResults")
@NamedQuery(name="PartnerAnnouncementResult.findAll", query="SELECT p FROM PartnerAnnouncementResult p")
public class PartnerAnnouncementResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerAnnouncementResultsId;

	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Column(length=200)
	private String partnerAnnouncementResultOption;

	//bi-directional many-to-one association to AnnouncementCreateUserType
	@ManyToOne
	@JoinColumn(name="announcementCreateTypeUserId")
	private AnnouncementCreateUserType announcementCreateUserType;

	//bi-directional many-to-one association to AnnouncementType
	@ManyToOne
	@JoinColumn(name="announcementTypeId")
	private AnnouncementType announcementType;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId")
	private Partner partner;

	public PartnerAnnouncementResult() {
	}

	public Integer getPartnerAnnouncementResultsId() {
		return this.partnerAnnouncementResultsId;
	}

	public void setPartnerAnnouncementResultsId(Integer partnerAnnouncementResultsId) {
		this.partnerAnnouncementResultsId = partnerAnnouncementResultsId;
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

	public String getPartnerAnnouncementResultOption() {
		return this.partnerAnnouncementResultOption;
	}

	public void setPartnerAnnouncementResultOption(String partnerAnnouncementResultOption) {
		this.partnerAnnouncementResultOption = partnerAnnouncementResultOption;
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

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

}