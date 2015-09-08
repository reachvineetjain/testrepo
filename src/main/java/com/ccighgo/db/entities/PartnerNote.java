package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
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
	@Column(unique=true, nullable=false)
	private Integer partnerNotesId;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	@Lob
	private String partnerNote;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId")
	private Partner partner;

	//bi-directional many-to-one association to PartnerNoteTopic
	@ManyToOne
	@JoinColumn(name="partnerNoteTopicId")
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