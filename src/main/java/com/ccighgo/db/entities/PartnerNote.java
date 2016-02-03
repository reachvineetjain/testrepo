package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the PartnerNotes database table.
 * 
 */
@Entity
@Table(name="PartnerNotes")
@NamedQuery(name="PartnerNote.findAll", query="SELECT p FROM PartnerNote p")
public class PartnerNote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer partnerNotesId;

	private Integer createdBy;

	private Timestamp createdOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date followupDate;

	private Byte hasRead;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	@Lob
	private String partnerNote;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId",insertable=false,updatable=false)
	private Partner partner;

	//bi-directional many-to-one association to PartnerNoteTopic
	@ManyToOne
	@JoinColumn(name="partnerNoteTopicId",insertable=false,updatable=false)
	private PartnerNoteTopic partnerNoteTopic;

	public PartnerNote() {
	}

	public Integer getPartnerNotesId() {
		return this.partnerNotesId;
	}

	public void setPartnerNotesId(Integer partnerNotesId) {
		this.partnerNotesId = partnerNotesId;
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

	public Date getFollowupDate() {
		return this.followupDate;
	}

	public void setFollowupDate(Date followupDate) {
		this.followupDate = followupDate;
	}

	public Byte getHasRead() {
		return this.hasRead;
	}

	public void setHasRead(Byte hasRead) {
		this.hasRead = hasRead;
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

	public String getPartnerNote() {
		return this.partnerNote;
	}

	public void setPartnerNote(String partnerNote) {
		this.partnerNote = partnerNote;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public PartnerNoteTopic getPartnerNoteTopic() {
		return this.partnerNoteTopic;
	}

	public void setPartnerNoteTopic(PartnerNoteTopic partnerNoteTopic) {
		this.partnerNoteTopic = partnerNoteTopic;
	}

}