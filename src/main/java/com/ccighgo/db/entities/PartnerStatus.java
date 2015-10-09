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

	@Column(length=50)
	private String partnerStatusName;

	//bi-directional many-to-one association to PartnerReviewStatus
	@OneToMany(mappedBy="partnerStatus1")
	private List<PartnerReviewStatus> partnerReviewStatuses1;

	//bi-directional many-to-one association to PartnerReviewStatus
	@OneToMany(mappedBy="partnerStatus2")
	private List<PartnerReviewStatus> partnerReviewStatuses2;

	//bi-directional many-to-one association to PartnerSeason
	@OneToMany(mappedBy="partnerStatus")
	private List<PartnerSeason> partnerSeasons;

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

	public List<PartnerSeason> getPartnerSeasons() {
		return this.partnerSeasons;
	}

	public void setPartnerSeasons(List<PartnerSeason> partnerSeasons) {
		this.partnerSeasons = partnerSeasons;
	}

	public PartnerSeason addPartnerSeason(PartnerSeason partnerSeason) {
		getPartnerSeasons().add(partnerSeason);
		partnerSeason.setPartnerStatus(this);

		return partnerSeason;
	}

	public PartnerSeason removePartnerSeason(PartnerSeason partnerSeason) {
		getPartnerSeasons().remove(partnerSeason);
		partnerSeason.setPartnerStatus(null);

		return partnerSeason;
	}

}