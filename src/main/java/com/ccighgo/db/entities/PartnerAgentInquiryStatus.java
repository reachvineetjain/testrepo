package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PartnerAgentInquiryStatus database table.
 * 
 */
@Entity
@NamedQuery(name="PartnerAgentInquiryStatus.findAll", query="SELECT p FROM PartnerAgentInquiryStatus p")
public class PartnerAgentInquiryStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer partnerAgentInquiryStatusId;

	private String partnerAgentInquiryStatus;

	//bi-directional many-to-one association to PartnerAgentReview
	@OneToMany(mappedBy="partnerAgentInquiryStatus")
	private List<PartnerAgentReview> partnerAgentReviews;

	public PartnerAgentInquiryStatus() {
	}

	public Integer getPartnerAgentInquiryStatusId() {
		return this.partnerAgentInquiryStatusId;
	}

	public void setPartnerAgentInquiryStatusId(Integer partnerAgentInquiryStatusId) {
		this.partnerAgentInquiryStatusId = partnerAgentInquiryStatusId;
	}

	public String getPartnerAgentInquiryStatus() {
		return this.partnerAgentInquiryStatus;
	}

	public void setPartnerAgentInquiryStatus(String partnerAgentInquiryStatus) {
		this.partnerAgentInquiryStatus = partnerAgentInquiryStatus;
	}

	public List<PartnerAgentReview> getPartnerAgentReviews() {
		return this.partnerAgentReviews;
	}

	public void setPartnerAgentReviews(List<PartnerAgentReview> partnerAgentReviews) {
		this.partnerAgentReviews = partnerAgentReviews;
	}

	public PartnerAgentReview addPartnerAgentReview(PartnerAgentReview partnerAgentReview) {
		getPartnerAgentReviews().add(partnerAgentReview);
		partnerAgentReview.setPartnerAgentInquiryStatus(this);

		return partnerAgentReview;
	}

	public PartnerAgentReview removePartnerAgentReview(PartnerAgentReview partnerAgentReview) {
		getPartnerAgentReviews().remove(partnerAgentReview);
		partnerAgentReview.setPartnerAgentInquiryStatus(null);

		return partnerAgentReview;
	}

}