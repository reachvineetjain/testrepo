package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the HostFamilyFinalEvaluation database table.
 * 
 */
@Entity
@Table(name="HostFamilyFinalEvaluation")
@NamedQuery(name="HostFamilyFinalEvaluation.findAll", query="SELECT h FROM HostFamilyFinalEvaluation h")
public class HostFamilyFinalEvaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyFinalEvaluationId;

	private Byte active;

	private Byte adequateOrientation;

	@Column(length=1000)
	private String adviceForHostFamilies;

	private Byte approvedByCCI;

	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	private Integer createdBy;

	private Timestamp createdOn;

	private Byte didChores;

	private Byte didExtraCurriculars;

	private Byte didWellAcademically;

	@Column(length=10)
	private String fieldStaffHelpfulness;

	private Byte followedHygiene;

	private Byte followedRules;

	@Column(length=10)
	private String insuranceQuality;

	@Column(length=10)
	private String materialQuality;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	@Column(length=1000)
	private String overallExpectations;

	@Column(length=10)
	private String overallExperience;

	@Column(length=10)
	private String participantCompatibility;

	@Column(length=10)
	private String participantFlexibility;

	@Column(length=1000)
	private String problem;

	@Column(length=100)
	private String problemHandledBy;

	private Byte recommendCCI;

	private Byte relatedToUSStudents;

	private Byte showToPartner;

	@Temporal(TemporalType.TIMESTAMP)
	private Date submittedDate;

	private Byte submittedToCCI;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId")
	private HostFamilySeason hostFamilySeason;

	//bi-directional many-to-one association to Participant
	@ManyToOne
	@JoinColumn(name="participantGoId")
	private Participant participant;

	public HostFamilyFinalEvaluation() {
	}

	public Integer getHostFamilyFinalEvaluationId() {
		return this.hostFamilyFinalEvaluationId;
	}

	public void setHostFamilyFinalEvaluationId(Integer hostFamilyFinalEvaluationId) {
		this.hostFamilyFinalEvaluationId = hostFamilyFinalEvaluationId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Byte getAdequateOrientation() {
		return this.adequateOrientation;
	}

	public void setAdequateOrientation(Byte adequateOrientation) {
		this.adequateOrientation = adequateOrientation;
	}

	public String getAdviceForHostFamilies() {
		return this.adviceForHostFamilies;
	}

	public void setAdviceForHostFamilies(String adviceForHostFamilies) {
		this.adviceForHostFamilies = adviceForHostFamilies;
	}

	public Byte getApprovedByCCI() {
		return this.approvedByCCI;
	}

	public void setApprovedByCCI(Byte approvedByCCI) {
		this.approvedByCCI = approvedByCCI;
	}

	public Date getApprovedDate() {
		return this.approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
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

	public Byte getDidChores() {
		return this.didChores;
	}

	public void setDidChores(Byte didChores) {
		this.didChores = didChores;
	}

	public Byte getDidExtraCurriculars() {
		return this.didExtraCurriculars;
	}

	public void setDidExtraCurriculars(Byte didExtraCurriculars) {
		this.didExtraCurriculars = didExtraCurriculars;
	}

	public Byte getDidWellAcademically() {
		return this.didWellAcademically;
	}

	public void setDidWellAcademically(Byte didWellAcademically) {
		this.didWellAcademically = didWellAcademically;
	}

	public String getFieldStaffHelpfulness() {
		return this.fieldStaffHelpfulness;
	}

	public void setFieldStaffHelpfulness(String fieldStaffHelpfulness) {
		this.fieldStaffHelpfulness = fieldStaffHelpfulness;
	}

	public Byte getFollowedHygiene() {
		return this.followedHygiene;
	}

	public void setFollowedHygiene(Byte followedHygiene) {
		this.followedHygiene = followedHygiene;
	}

	public Byte getFollowedRules() {
		return this.followedRules;
	}

	public void setFollowedRules(Byte followedRules) {
		this.followedRules = followedRules;
	}

	public String getInsuranceQuality() {
		return this.insuranceQuality;
	}

	public void setInsuranceQuality(String insuranceQuality) {
		this.insuranceQuality = insuranceQuality;
	}

	public String getMaterialQuality() {
		return this.materialQuality;
	}

	public void setMaterialQuality(String materialQuality) {
		this.materialQuality = materialQuality;
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

	public String getOverallExpectations() {
		return this.overallExpectations;
	}

	public void setOverallExpectations(String overallExpectations) {
		this.overallExpectations = overallExpectations;
	}

	public String getOverallExperience() {
		return this.overallExperience;
	}

	public void setOverallExperience(String overallExperience) {
		this.overallExperience = overallExperience;
	}

	public String getParticipantCompatibility() {
		return this.participantCompatibility;
	}

	public void setParticipantCompatibility(String participantCompatibility) {
		this.participantCompatibility = participantCompatibility;
	}

	public String getParticipantFlexibility() {
		return this.participantFlexibility;
	}

	public void setParticipantFlexibility(String participantFlexibility) {
		this.participantFlexibility = participantFlexibility;
	}

	public String getProblem() {
		return this.problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getProblemHandledBy() {
		return this.problemHandledBy;
	}

	public void setProblemHandledBy(String problemHandledBy) {
		this.problemHandledBy = problemHandledBy;
	}

	public Byte getRecommendCCI() {
		return this.recommendCCI;
	}

	public void setRecommendCCI(Byte recommendCCI) {
		this.recommendCCI = recommendCCI;
	}

	public Byte getRelatedToUSStudents() {
		return this.relatedToUSStudents;
	}

	public void setRelatedToUSStudents(Byte relatedToUSStudents) {
		this.relatedToUSStudents = relatedToUSStudents;
	}

	public Byte getShowToPartner() {
		return this.showToPartner;
	}

	public void setShowToPartner(Byte showToPartner) {
		this.showToPartner = showToPartner;
	}

	public Date getSubmittedDate() {
		return this.submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public Byte getSubmittedToCCI() {
		return this.submittedToCCI;
	}

	public void setSubmittedToCCI(Byte submittedToCCI) {
		this.submittedToCCI = submittedToCCI;
	}

	public HostFamilySeason getHostFamilySeason() {
		return this.hostFamilySeason;
	}

	public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
		this.hostFamilySeason = hostFamilySeason;
	}

	public Participant getParticipant() {
		return this.participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

}