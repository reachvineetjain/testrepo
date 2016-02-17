package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PartnerStatus database table.
 * 
 */
@Entity
@Table(name="PartnerStatus")
@NamedQuery(name="PartnerStatus.findAll", query="SELECT p FROM PartnerStatus p")
public class PartnerStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerStatusId;

	private Byte active;

	private Byte isLeadStatus;

	private Byte isSeasonStatus;

	@Column(length=50)
	private String partnerStatusName;

	//bi-directional many-to-one association to PartnerReviewStatus
	@OneToMany(mappedBy="partnerStatus1")
	private List<PartnerReviewStatus> partnerReviewStatuses1;

	//bi-directional many-to-one association to PartnerReviewStatus
	@OneToMany(mappedBy="partnerStatus2")
	private List<PartnerReviewStatus> partnerReviewStatuses2;

	//bi-directional many-to-one association to PartnerSeason
	@OneToMany(mappedBy="partnerStatus1")
	private List<PartnerSeason> partnerSeasons1;

	//bi-directional many-to-one association to PartnerSeason
	@OneToMany(mappedBy="partnerStatus2")
	private List<PartnerSeason> partnerSeasons2;

	//bi-directional many-to-one association to PartnerSeason
	@OneToMany(mappedBy="partnerStatus3")
	private List<PartnerSeason> partnerSeasons3;

	//bi-directional many-to-one association to PartnerSeasonAllocation
	@OneToMany(mappedBy="partnerStatus")
	private List<PartnerSeasonAllocation> partnerSeasonAllocations;

	public PartnerStatus() {
	}

	public Integer getPartnerStatusId() {
		return this.partnerStatusId;
	}

	public void setPartnerStatusId(Integer partnerStatusId) {
		this.partnerStatusId = partnerStatusId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Byte getIsLeadStatus() {
		return this.isLeadStatus;
	}

	public void setIsLeadStatus(Byte isLeadStatus) {
		this.isLeadStatus = isLeadStatus;
	}

	public Byte getIsSeasonStatus() {
		return this.isSeasonStatus;
	}

	public void setIsSeasonStatus(Byte isSeasonStatus) {
		this.isSeasonStatus = isSeasonStatus;
	}

	public String getPartnerStatusName() {
		return this.partnerStatusName;
	}

	public void setPartnerStatusName(String partnerStatusName) {
		this.partnerStatusName = partnerStatusName;
	}

	public List<PartnerReviewStatus> getPartnerReviewStatuses1() {
		return this.partnerReviewStatuses1;
	}

	public void setPartnerReviewStatuses1(List<PartnerReviewStatus> partnerReviewStatuses1) {
		this.partnerReviewStatuses1 = partnerReviewStatuses1;
	}

	public PartnerReviewStatus addPartnerReviewStatuses1(PartnerReviewStatus partnerReviewStatuses1) {
		getPartnerReviewStatuses1().add(partnerReviewStatuses1);
		partnerReviewStatuses1.setPartnerStatus1(this);

		return partnerReviewStatuses1;
	}

	public PartnerReviewStatus removePartnerReviewStatuses1(PartnerReviewStatus partnerReviewStatuses1) {
		getPartnerReviewStatuses1().remove(partnerReviewStatuses1);
		partnerReviewStatuses1.setPartnerStatus1(null);

		return partnerReviewStatuses1;
	}

	public List<PartnerReviewStatus> getPartnerReviewStatuses2() {
		return this.partnerReviewStatuses2;
	}

	public void setPartnerReviewStatuses2(List<PartnerReviewStatus> partnerReviewStatuses2) {
		this.partnerReviewStatuses2 = partnerReviewStatuses2;
	}

	public PartnerReviewStatus addPartnerReviewStatuses2(PartnerReviewStatus partnerReviewStatuses2) {
		getPartnerReviewStatuses2().add(partnerReviewStatuses2);
		partnerReviewStatuses2.setPartnerStatus2(this);

		return partnerReviewStatuses2;
	}

	public PartnerReviewStatus removePartnerReviewStatuses2(PartnerReviewStatus partnerReviewStatuses2) {
		getPartnerReviewStatuses2().remove(partnerReviewStatuses2);
		partnerReviewStatuses2.setPartnerStatus2(null);

		return partnerReviewStatuses2;
	}

	public List<PartnerSeason> getPartnerSeasons1() {
		return this.partnerSeasons1;
	}

	public void setPartnerSeasons1(List<PartnerSeason> partnerSeasons1) {
		this.partnerSeasons1 = partnerSeasons1;
	}

	public PartnerSeason addPartnerSeasons1(PartnerSeason partnerSeasons1) {
		getPartnerSeasons1().add(partnerSeasons1);
		partnerSeasons1.setPartnerStatus1(this);

		return partnerSeasons1;
	}

	public PartnerSeason removePartnerSeasons1(PartnerSeason partnerSeasons1) {
		getPartnerSeasons1().remove(partnerSeasons1);
		partnerSeasons1.setPartnerStatus1(null);

		return partnerSeasons1;
	}

	public List<PartnerSeason> getPartnerSeasons2() {
		return this.partnerSeasons2;
	}

	public void setPartnerSeasons2(List<PartnerSeason> partnerSeasons2) {
		this.partnerSeasons2 = partnerSeasons2;
	}

	public PartnerSeason addPartnerSeasons2(PartnerSeason partnerSeasons2) {
		getPartnerSeasons2().add(partnerSeasons2);
		partnerSeasons2.setPartnerStatus2(this);

		return partnerSeasons2;
	}

	public PartnerSeason removePartnerSeasons2(PartnerSeason partnerSeasons2) {
		getPartnerSeasons2().remove(partnerSeasons2);
		partnerSeasons2.setPartnerStatus2(null);

		return partnerSeasons2;
	}

	public List<PartnerSeason> getPartnerSeasons3() {
		return this.partnerSeasons3;
	}

	public void setPartnerSeasons3(List<PartnerSeason> partnerSeasons3) {
		this.partnerSeasons3 = partnerSeasons3;
	}

	public PartnerSeason addPartnerSeasons3(PartnerSeason partnerSeasons3) {
		getPartnerSeasons3().add(partnerSeasons3);
		partnerSeasons3.setPartnerStatus3(this);

		return partnerSeasons3;
	}

	public PartnerSeason removePartnerSeasons3(PartnerSeason partnerSeasons3) {
		getPartnerSeasons3().remove(partnerSeasons3);
		partnerSeasons3.setPartnerStatus3(null);

		return partnerSeasons3;
	}

	public List<PartnerSeasonAllocation> getPartnerSeasonAllocations() {
		return this.partnerSeasonAllocations;
	}

	public void setPartnerSeasonAllocations(List<PartnerSeasonAllocation> partnerSeasonAllocations) {
		this.partnerSeasonAllocations = partnerSeasonAllocations;
	}

	public PartnerSeasonAllocation addPartnerSeasonAllocation(PartnerSeasonAllocation partnerSeasonAllocation) {
		getPartnerSeasonAllocations().add(partnerSeasonAllocation);
		partnerSeasonAllocation.setPartnerStatus(this);

		return partnerSeasonAllocation;
	}

	public PartnerSeasonAllocation removePartnerSeasonAllocation(PartnerSeasonAllocation partnerSeasonAllocation) {
		getPartnerSeasonAllocations().remove(partnerSeasonAllocation);
		partnerSeasonAllocation.setPartnerStatus(null);

		return partnerSeasonAllocation;
	}

}