package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PartnerMessages database table.
 * 
 */
@Entity
@Table(name="PartnerMessages")
@NamedQuery(name="PartnerMessage.findAll", query="SELECT p FROM PartnerMessage p")
public class PartnerMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer partnerInquiryMessageId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	private String partnerInquiryMessage;

	//bi-directional many-to-one association to CCIStaffUser
	@ManyToOne
	@JoinColumn(name="cciStaffUserId",insertable=false,updatable=false)
	private CCIStaffUser ccistaffUser;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId",insertable=false,updatable=false)
	private Partner partner;

	public PartnerMessage() {
	}

	public Integer getPartnerInquiryMessageId() {
		return this.partnerInquiryMessageId;
	}

	public void setPartnerInquiryMessageId(Integer partnerInquiryMessageId) {
		this.partnerInquiryMessageId = partnerInquiryMessageId;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getPartnerInquiryMessage() {
		return this.partnerInquiryMessage;
	}

	public void setPartnerInquiryMessage(String partnerInquiryMessage) {
		this.partnerInquiryMessage = partnerInquiryMessage;
	}

	public CCIStaffUser getCcistaffUser() {
		return this.ccistaffUser;
	}

	public void setCcistaffUser(CCIStaffUser ccistaffUser) {
		this.ccistaffUser = ccistaffUser;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

}