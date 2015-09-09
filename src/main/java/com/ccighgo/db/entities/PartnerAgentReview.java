package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PartnerAgentReview database table.
 * 
 */
@Entity
@Table(name="PartnerAgentReview")
@NamedQuery(name="PartnerAgentReview.findAll", query="SELECT p FROM PartnerAgentReview p")
public class PartnerAgentReview implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerAgentReviewid;

	private Integer agentRating;

	@Temporal(TemporalType.DATE)
	private Date followUpDate;

	private byte moreAboutAmbassadorScholership;

	private Integer partnerMessagesId;

	//bi-directional many-to-one association to PartnerAgentInquiryDocument
	@OneToMany(mappedBy="partnerAgentReview")
	private List<PartnerAgentInquiryDocument> partnerAgentInquiryDocuments;

	//bi-directional many-to-one association to PartnerAgentInquiryNote
	@OneToMany(mappedBy="partnerAgentReview")
	private List<PartnerAgentInquiryNote> partnerAgentInquiryNotes;

	//bi-directional many-to-one association to PartnerAgentProgram
	@ManyToOne
	@JoinColumn(name="partnerAgentProgramId")
	private PartnerAgentProgram partnerAgentProgram;

	//bi-directional many-to-one association to CCIStaffUser
	@ManyToOne
	@JoinColumn(name="cciStaffUserId")
	private CCIStaffUser ccistaffUser;

	//bi-directional many-to-one association to PartnerAgentInquiryStatus
	@ManyToOne
	@JoinColumn(name="partnerAgentInquiryStatusId")
	private PartnerAgentInquiryStatus partnerAgentInquiryStatus;

	public PartnerAgentReview() {
	}

	public Integer getPartnerAgentReviewid() {
		return this.partnerAgentReviewid;
	}

	public void setPartnerAgentReviewid(Integer partnerAgentReviewid) {
		this.partnerAgentReviewid = partnerAgentReviewid;
	}

	public Integer getAgentRating() {
		return this.agentRating;
	}

	public void setAgentRating(Integer agentRating) {
		this.agentRating = agentRating;
	}

	public Date getFollowUpDate() {
		return this.followUpDate;
	}

	public void setFollowUpDate(Date followUpDate) {
		this.followUpDate = followUpDate;
	}

	public byte getMoreAboutAmbassadorScholership() {
		return this.moreAboutAmbassadorScholership;
	}

	public void setMoreAboutAmbassadorScholership(byte moreAboutAmbassadorScholership) {
		this.moreAboutAmbassadorScholership = moreAboutAmbassadorScholership;
	}

	public Integer getPartnerMessagesId() {
		return this.partnerMessagesId;
	}

	public void setPartnerMessagesId(Integer partnerMessagesId) {
		this.partnerMessagesId = partnerMessagesId;
	}

	public List<PartnerAgentInquiryDocument> getPartnerAgentInquiryDocuments() {
		return this.partnerAgentInquiryDocuments;
	}

	public void setPartnerAgentInquiryDocuments(List<PartnerAgentInquiryDocument> partnerAgentInquiryDocuments) {
		this.partnerAgentInquiryDocuments = partnerAgentInquiryDocuments;
	}

	public PartnerAgentInquiryDocument addPartnerAgentInquiryDocument(PartnerAgentInquiryDocument partnerAgentInquiryDocument) {
		getPartnerAgentInquiryDocuments().add(partnerAgentInquiryDocument);
		partnerAgentInquiryDocument.setPartnerAgentReview(this);

		return partnerAgentInquiryDocument;
	}

	public PartnerAgentInquiryDocument removePartnerAgentInquiryDocument(PartnerAgentInquiryDocument partnerAgentInquiryDocument) {
		getPartnerAgentInquiryDocuments().remove(partnerAgentInquiryDocument);
		partnerAgentInquiryDocument.setPartnerAgentReview(null);

		return partnerAgentInquiryDocument;
	}

	public List<PartnerAgentInquiryNote> getPartnerAgentInquiryNotes() {
		return this.partnerAgentInquiryNotes;
	}

	public void setPartnerAgentInquiryNotes(List<PartnerAgentInquiryNote> partnerAgentInquiryNotes) {
		this.partnerAgentInquiryNotes = partnerAgentInquiryNotes;
	}

	public PartnerAgentInquiryNote addPartnerAgentInquiryNote(PartnerAgentInquiryNote partnerAgentInquiryNote) {
		getPartnerAgentInquiryNotes().add(partnerAgentInquiryNote);
		partnerAgentInquiryNote.setPartnerAgentReview(this);

		return partnerAgentInquiryNote;
	}

	public PartnerAgentInquiryNote removePartnerAgentInquiryNote(PartnerAgentInquiryNote partnerAgentInquiryNote) {
		getPartnerAgentInquiryNotes().remove(partnerAgentInquiryNote);
		partnerAgentInquiryNote.setPartnerAgentReview(null);

		return partnerAgentInquiryNote;
	}

	public PartnerAgentProgram getPartnerAgentProgram() {
		return this.partnerAgentProgram;
	}

	public void setPartnerAgentProgram(PartnerAgentProgram partnerAgentProgram) {
		this.partnerAgentProgram = partnerAgentProgram;
	}

	public CCIStaffUser getCcistaffUser() {
		return this.ccistaffUser;
	}

	public void setCcistaffUser(CCIStaffUser ccistaffUser) {
		this.ccistaffUser = ccistaffUser;
	}

	public PartnerAgentInquiryStatus getPartnerAgentInquiryStatus() {
		return this.partnerAgentInquiryStatus;
	}

	public void setPartnerAgentInquiryStatus(PartnerAgentInquiryStatus partnerAgentInquiryStatus) {
		this.partnerAgentInquiryStatus = partnerAgentInquiryStatus;
	}

}