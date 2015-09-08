package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PartnerProgram database table.
 * 
 */
@Entity
@Table(name="PartnerProgram")
@NamedQuery(name="PartnerProgram.findAll", query="SELECT p FROM PartnerProgram p")
public class PartnerProgram implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerProgramId;

	private byte hasApplied;

	private byte isEligible;

	private byte isOther;

	private byte isPDNotified;

	//bi-directional many-to-one association to PartnerDocument
	@OneToMany(mappedBy="partnerProgram")
	private List<PartnerDocument> partnerDocuments;

	//bi-directional many-to-one association to CCIStaffUser
	@ManyToOne
	@JoinColumn(name="markedEligibleBy")
	private CCIStaffUser ccistaffUser;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="departmentProgramId")
	private LookupDepartmentProgram lookupDepartmentProgram;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId")
	private Partner partner;

	public PartnerProgram() {
	}

	public Integer getPartnerProgramId() {
		return this.partnerProgramId;
	}

	public void setPartnerProgramId(Integer partnerProgramId) {
		this.partnerProgramId = partnerProgramId;
	}

	public byte getHasApplied() {
		return this.hasApplied;
	}

	public void setHasApplied(byte hasApplied) {
		this.hasApplied = hasApplied;
	}

	public byte getIsEligible() {
		return this.isEligible;
	}

	public void setIsEligible(byte isEligible) {
		this.isEligible = isEligible;
	}

	public byte getIsOther() {
		return this.isOther;
	}

	public void setIsOther(byte isOther) {
		this.isOther = isOther;
	}

	public byte getIsPDNotified() {
		return this.isPDNotified;
	}

	public void setIsPDNotified(byte isPDNotified) {
		this.isPDNotified = isPDNotified;
	}

	public List<PartnerDocument> getPartnerDocuments() {
		return this.partnerDocuments;
	}

	public void setPartnerDocuments(List<PartnerDocument> partnerDocuments) {
		this.partnerDocuments = partnerDocuments;
	}

	public PartnerDocument addPartnerDocument(PartnerDocument partnerDocument) {
		getPartnerDocuments().add(partnerDocument);
		partnerDocument.setPartnerProgram(this);

		return partnerDocument;
	}

	public PartnerDocument removePartnerDocument(PartnerDocument partnerDocument) {
		getPartnerDocuments().remove(partnerDocument);
		partnerDocument.setPartnerProgram(null);

		return partnerDocument;
	}

	public CCIStaffUser getCcistaffUser() {
		return this.ccistaffUser;
	}

	public void setCcistaffUser(CCIStaffUser ccistaffUser) {
		this.ccistaffUser = ccistaffUser;
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

}