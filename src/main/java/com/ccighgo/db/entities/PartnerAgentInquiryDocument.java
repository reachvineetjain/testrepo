package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PartnerAgentInquiryDocument database table.
 * 
 */
@Entity
@Table(name="PartnerAgentInquiryDocument")
@NamedQuery(name="PartnerAgentInquiryDocument.findAll", query="SELECT p FROM PartnerAgentInquiryDocument p")
public class PartnerAgentInquiryDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerAgentInquiryDocumentId;

	@Column(nullable=false)
	private Byte active;

	private Integer documentInformationId;

	//bi-directional many-to-one association to PartnerAgentInquiry
	@ManyToOne
	@JoinColumn(name="partnerAgentGoId")
	private PartnerAgentInquiry partnerAgentInquiry;

	//bi-directional many-to-one association to PartnerAgentReview
	@ManyToOne
	@JoinColumn(name="partnerAgentReviewId")
	private PartnerAgentReview partnerAgentReview;

	public PartnerAgentInquiryDocument() {
	}

	public Integer getPartnerAgentInquiryDocumentId() {
		return this.partnerAgentInquiryDocumentId;
	}

	public void setPartnerAgentInquiryDocumentId(Integer partnerAgentInquiryDocumentId) {
		this.partnerAgentInquiryDocumentId = partnerAgentInquiryDocumentId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Integer getDocumentInformationId() {
		return this.documentInformationId;
	}

	public void setDocumentInformationId(Integer documentInformationId) {
		this.documentInformationId = documentInformationId;
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