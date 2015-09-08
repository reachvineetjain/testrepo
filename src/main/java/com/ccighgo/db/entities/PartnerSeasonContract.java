package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the PartnerSeasonContract database table.
 * 
 */
@Entity
@Table(name="PartnerSeasonContract")
@NamedQuery(name="PartnerSeasonContract.findAll", query="SELECT p FROM PartnerSeasonContract p")
public class PartnerSeasonContract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerSeasonContractId;

	private byte active;

	private Integer createdBy;

	private Timestamp createdOn;

	@Column(length=50)
	private String displayName;

	@Column(length=100)
	private String fileName;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	@Column(nullable=false, length=64)
	private String partnerSeasonContractGuid;

	//bi-directional many-to-one association to PartnerSeason
	@ManyToOne
	@JoinColumn(name="partnerSeasonId")
	private PartnerSeason partnerSeason;

	public PartnerSeasonContract() {
	}

	public Integer getPartnerSeasonContractId() {
		return this.partnerSeasonContractId;
	}

	public void setPartnerSeasonContractId(Integer partnerSeasonContractId) {
		this.partnerSeasonContractId = partnerSeasonContractId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
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

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public String getPartnerSeasonContractGuid() {
		return this.partnerSeasonContractGuid;
	}

	public void setPartnerSeasonContractGuid(String partnerSeasonContractGuid) {
		this.partnerSeasonContractGuid = partnerSeasonContractGuid;
	}

	public PartnerSeason getPartnerSeason() {
		return this.partnerSeason;
	}

	public void setPartnerSeason(PartnerSeason partnerSeason) {
		this.partnerSeason = partnerSeason;
	}

}