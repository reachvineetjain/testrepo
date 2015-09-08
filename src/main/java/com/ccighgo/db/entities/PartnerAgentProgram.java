package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PartnerAgentProgram database table.
 * 
 */
@Entity
@Table(name="PartnerAgentProgram")
@NamedQuery(name="PartnerAgentProgram.findAll", query="SELECT p FROM PartnerAgentProgram p")
public class PartnerAgentProgram implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerAgentProgramId;

	//bi-directional many-to-one association to PartnerAgentInquiry
	@ManyToOne
	@JoinColumn(name="partnerAgentGoId")
	private PartnerAgentInquiry partnerAgentInquiry;

	//bi-directional many-to-one association to DepartmentProgram
	@ManyToOne
	@JoinColumn(name="departmentProgramId")
	private DepartmentProgram departmentProgram;

	//bi-directional many-to-one association to PartnerAgentReview
	@OneToMany(mappedBy="partnerAgentProgram")
	private List<PartnerAgentReview> partnerAgentReviews;

	public PartnerAgentProgram() {
	}

	public Integer getPartnerAgentProgramId() {
		return this.partnerAgentProgramId;
	}

	public void setPartnerAgentProgramId(Integer partnerAgentProgramId) {
		this.partnerAgentProgramId = partnerAgentProgramId;
	}

	public PartnerAgentInquiry getPartnerAgentInquiry() {
		return this.partnerAgentInquiry;
	}

	public void setPartnerAgentInquiry(PartnerAgentInquiry partnerAgentInquiry) {
		this.partnerAgentInquiry = partnerAgentInquiry;
	}

	public DepartmentProgram getDepartmentProgram() {
		return this.departmentProgram;
	}

	public void setDepartmentProgram(DepartmentProgram departmentProgram) {
		this.departmentProgram = departmentProgram;
	}

	public List<PartnerAgentReview> getPartnerAgentReviews() {
		return this.partnerAgentReviews;
	}

	public void setPartnerAgentReviews(List<PartnerAgentReview> partnerAgentReviews) {
		this.partnerAgentReviews = partnerAgentReviews;
	}

	public PartnerAgentReview addPartnerAgentReview(PartnerAgentReview partnerAgentReview) {
		getPartnerAgentReviews().add(partnerAgentReview);
		partnerAgentReview.setPartnerAgentProgram(this);

		return partnerAgentReview;
	}

	public PartnerAgentReview removePartnerAgentReview(PartnerAgentReview partnerAgentReview) {
		getPartnerAgentReviews().remove(partnerAgentReview);
		partnerAgentReview.setPartnerAgentProgram(null);

		return partnerAgentReview;
	}

}