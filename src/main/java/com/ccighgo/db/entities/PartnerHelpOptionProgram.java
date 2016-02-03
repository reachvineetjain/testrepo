package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the PartnerHelpOptionProgram database table.
 * 
 */
@Entity
@NamedQuery(name="PartnerHelpOptionProgram.findAll", query="SELECT p FROM PartnerHelpOptionProgram p")
public class PartnerHelpOptionProgram implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer partnerHelpOptionProgramId;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	private Integer partnerHelpOptionId;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId",insertable=false,updatable=false)
	private LookupDepartmentProgram lookupDepartmentProgram;

	public PartnerHelpOptionProgram() {
	}

	public Integer getPartnerHelpOptionProgramId() {
		return this.partnerHelpOptionProgramId;
	}

	public void setPartnerHelpOptionProgramId(Integer partnerHelpOptionProgramId) {
		this.partnerHelpOptionProgramId = partnerHelpOptionProgramId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Integer getPartnerHelpOptionId() {
		return this.partnerHelpOptionId;
	}

	public void setPartnerHelpOptionId(Integer partnerHelpOptionId) {
		this.partnerHelpOptionId = partnerHelpOptionId;
	}

	public LookupDepartmentProgram getLookupDepartmentProgram() {
		return this.lookupDepartmentProgram;
	}

	public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		this.lookupDepartmentProgram = lookupDepartmentProgram;
	}

}