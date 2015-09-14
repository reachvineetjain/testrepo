package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PartnerCCIContact database table.
 * 
 */
@Entity
@Table(name="PartnerCCIContact")
@NamedQuery(name="PartnerCCIContact.findAll", query="SELECT p FROM PartnerCCIContact p")
public class PartnerCCIContact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerCCIContactId;

	//bi-directional many-to-one association to CCIStaffUser
	@ManyToOne
	@JoinColumn(name="cciStaffUserId")
	private CCIStaffUser ccistaffUser;

	//bi-directional many-to-one association to DepartmentProgram
	@ManyToOne
	@JoinColumn(name="departmentProgramId")
	private DepartmentProgram departmentProgram;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId")
	private Partner partner;

	public PartnerCCIContact() {
	}

	public Integer getPartnerCCIContactId() {
		return this.partnerCCIContactId;
	}

	public void setPartnerCCIContactId(Integer partnerCCIContactId) {
		this.partnerCCIContactId = partnerCCIContactId;
	}

	public CCIStaffUser getCcistaffUser() {
		return this.ccistaffUser;
	}

	public void setCcistaffUser(CCIStaffUser ccistaffUser) {
		this.ccistaffUser = ccistaffUser;
	}

	public DepartmentProgram getDepartmentProgram() {
		return this.departmentProgram;
	}

	public void setDepartmentProgram(DepartmentProgram departmentProgram) {
		this.departmentProgram = departmentProgram;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

}