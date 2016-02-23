package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PartnerUpdateLog database table.
 * 
 */
@Entity
@Table(name="PartnerUpdateLog")
@NamedQuery(name="PartnerUpdateLog.findAll", query="SELECT p FROM PartnerUpdateLog p")
public class PartnerUpdateLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int partnerUpdateLogId;

	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Lob
	private String updateLogObject;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId")
	private Partner partner;

	public PartnerUpdateLog() {
	}

	public int getPartnerUpdateLogId() {
		return this.partnerUpdateLogId;
	}

	public void setPartnerUpdateLogId(int partnerUpdateLogId) {
		this.partnerUpdateLogId = partnerUpdateLogId;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdateLogObject() {
		return this.updateLogObject;
	}

	public void setUpdateLogObject(String updateLogObject) {
		this.updateLogObject = updateLogObject;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

}