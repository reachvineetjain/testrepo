package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PartnerProgram database table.
 * 
 */
@Entity
@NamedQuery(name="PartnerProgram.findAll", query="SELECT p FROM PartnerProgram p")
public class PartnerProgram implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer partnerProgramId;

	private Byte hasApplied;

	private Byte isEligible;

	//bi-directional many-to-one association to PartnerDocument
	@OneToMany(mappedBy="partnerProgram")
	private List<PartnerDocument> partnerDocuments;

	//bi-directional many-to-one association to CCIStaffUser
	@ManyToOne
	@JoinColumn(name="cciStaffUserId",insertable=false,updatable=false)
	private CCIStaffUser ccistaffUser;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId",insertable=false,updatable=false)
	private LookupDepartmentProgram lookupDepartmentProgram;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId",insertable=false,updatable=false)
	private Partner partner;

	public PartnerProgram() {
	}

	public Integer getPartnerProgramId() {
		return this.partnerProgramId;
	}

	public void setPartnerProgramId(Integer partnerProgramId) {
		this.partnerProgramId = partnerProgramId;
	}

	public Byte getHasApplied() {
		return this.hasApplied;
	}

	public void setHasApplied(Byte hasApplied) {
		this.hasApplied = hasApplied;
	}

	public Byte getIsEligible() {
		return this.isEligible;
	}

	public void setIsEligible(Byte isEligible) {
		this.isEligible = isEligible;
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