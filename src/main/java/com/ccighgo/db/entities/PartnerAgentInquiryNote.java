package com.ccighgo.db.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the PartnerAgentInquiryNotes database table.
 * 
 */
@Entity
@Table(name="PartnerAgentInquiryNotes")
@NamedQuery(name="PartnerAgentInquiryNote.findAll", query="SELECT p FROM PartnerAgentInquiryNote p")
public class PartnerAgentInquiryNote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer partnerAgentInquiryNotesId;

	private String partnerAgentInquiryNotes;

	//bi-directional many-to-one association to PartnerAgentInquiry
	@ManyToOne
	@JoinColumn(name="partnerAgentGoId")
	private PartnerAgentInquiry partnerAgentInquiry;

	//bi-directional many-to-one association to PartnerAgentReview
	@ManyToOne
	@JoinColumn(name="partnerAgentReviewId")
	private PartnerAgentReview partnerAgentReview;

	public PartnerAgentInquiryNote() {
	}

	public Integer getPartnerAgentInquiryNotesId() {
		return this.partnerAgentInquiryNotesId;
	}

	public void setPartnerAgentInquiryNotesId(Integer partnerAgentInquiryNotesId) {
		this.partnerAgentInquiryNotesId = partnerAgentInquiryNotesId;
	}

	public String getPartnerAgentInquiryNotes() {
		return this.partnerAgentInquiryNotes;
	}

	public void setPartnerAgentInquiryNotes(String partnerAgentInquiryNotes) {
		this.partnerAgentInquiryNotes = partnerAgentInquiryNotes;
	}

	public PartnerAgentInquiry getPartnerAgentInquiry() {
		return this.partnerAgentInquiry;
	}

	public void setPartnerAgentInquiry(PartnerAgentInquiry partnerAgentInquiry) {
		this.partnerAgentInquiry = partnerAgentInquiry;
	}

	public PartnerAgentReview getPartnerAgentReview() {
		return this.partnerAgentReview;
	}

	public void setPartnerAgentReview(PartnerAgentReview partnerAgentReview) {
		this.partnerAgentReview = partnerAgentReview;
	}

}