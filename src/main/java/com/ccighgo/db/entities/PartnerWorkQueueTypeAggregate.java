package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the PartnerWorkQueueTypeAggregate database table.
 * 
 */
@Entity
@Table(name="PartnerWorkQueueTypeAggregate")
@NamedQuery(name="PartnerWorkQueueTypeAggregate.findAll", query="SELECT p FROM PartnerWorkQueueTypeAggregate p")
public class PartnerWorkQueueTypeAggregate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerWQTypeAggregateId;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	private int partnerWQTypeAggregate;

	@Column(length=50)
	private String partnerWQTypeName;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId")
	private LookupDepartmentProgram lookupDepartmentProgram;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId")
	private Partner partner;

	//bi-directional many-to-one association to PartnerWorkQueueType
	@ManyToOne
	@JoinColumn(name="partnerWQTypeId")
	private PartnerWorkQueueType partnerWorkQueueType;

	public PartnerWorkQueueTypeAggregate() {
	}

	public Integer getPartnerWQTypeAggregateId() {
		return this.partnerWQTypeAggregateId;
	}

	public void setPartnerWQTypeAggregateId(Integer partnerWQTypeAggregateId) {
		this.partnerWQTypeAggregateId = partnerWQTypeAggregateId;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getPartnerWQTypeAggregate() {
		return this.partnerWQTypeAggregate;
	}

	public void setPartnerWQTypeAggregate(int partnerWQTypeAggregate) {
		this.partnerWQTypeAggregate = partnerWQTypeAggregate;
	}

	public String getPartnerWQTypeName() {
		return this.partnerWQTypeName;
	}

	public void setPartnerWQTypeName(String partnerWQTypeName) {
		this.partnerWQTypeName = partnerWQTypeName;
	}

	public LookupDepartmentProgram getLookupDepartmentProgram() {
		return this.lookupDepartmentProgram;
	}

	public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		this.lookupDepartmentProgram = lookupDepartmentProgram;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public PartnerWorkQueueType getPartnerWorkQueueType() {
		return this.partnerWorkQueueType;
	}

	public void setPartnerWorkQueueType(PartnerWorkQueueType partnerWorkQueueType) {
		this.partnerWorkQueueType = partnerWorkQueueType;
	}

}