package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the HostFamilyEvaluation database table.
 * 
 */
@Entity
@Table(name="HostFamilyEvaluation")
@NamedQuery(name="HostFamilyEvaluation.findAll", query="SELECT h FROM HostFamilyEvaluation h")
public class HostFamilyEvaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyEvaluationId;

	private Byte academicProblems;

	private Byte active;

	@Column(length=100)
	private String additionalComments;

	private Byte approvedByCCI;

	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	private Byte behaviorProblems;

	private Integer communicationEffort;

	private Integer contactWithFieldStaff;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer effortForHygiene;

	private Integer effortToAdopt;

	@Column(length=15)
	private String evaluationMonth;

	@Column(length=50)
	private String familyActivityParticipation;

	private Integer interestInFamily;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	@Column(length=1000)
	private String problems;

	@Column(length=50)
	private String relationship;

	@Column(length=100)
	private String relationshipComments;

	private Integer relationshipScale;

	private Byte showToPartner;

	@Temporal(TemporalType.TIMESTAMP)
	private Date submittedDate;

	private Byte submittedToCCI;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="fieldStaffGoId")
	private FieldStaff fieldStaff;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId")
	private HostFamilySeason hostFamilySeason;

	//bi-directional many-to-one association to Participant
	@ManyToOne
	@JoinColumn(name="participantGoId")
	private Participant participant;

	public HostFamilyEvaluation() {
	}

	public Integer getHostFamilyEvaluationId() {
		return this.hostFamilyEvaluationId;
	}

	public void setHostFamilyEvaluationId(Integer hostFamilyEvaluationId) {
		this.hostFamilyEvaluationId = hostFamilyEvaluationId;
	}

	public Byte getAcademicProblems() {
		return this.academicProblems;
	}

	public void setAcademicProblems(Byte academicProblems) {
		this.academicProblems = academicProblems;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public String getAdditionalComments() {
		return this.additionalComments;
	}

	public void setAdditionalComments(String additionalComments) {
		this.additionalComments = additionalComments;
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

	public Byte getBehaviorProblems() {
		return this.behaviorProblems;
	}

	public void setBehaviorProblems(Byte behaviorProblems) {
		this.behaviorProblems = behaviorProblems;
	}

	public Integer getCommunicationEffort() {
		return this.communicationEffort;
	}

	public void setCommunicationEffort(Integer communicationEffort) {
		this.communicationEffort = communicationEffort;
	}

	public Integer getContactWithFieldStaff() {
		return this.contactWithFieldStaff;
	}

	public void setContactWithFieldStaff(Integer contactWithFieldStaff) {
		this.contactWithFieldStaff = contactWithFieldStaff;
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

	public Integer getEffortForHygiene() {
		return this.effortForHygiene;
	}

	public void setEffortForHygiene(Integer effortForHygiene) {
		this.effortForHygiene = effortForHygiene;
	}

	public Integer getEffortToAdopt() {
		return this.effortToAdopt;
	}

	public void setEffortToAdopt(Integer effortToAdopt) {
		this.effortToAdopt = effortToAdopt;
	}

	public String getEvaluationMonth() {
		return this.evaluationMonth;
	}

	public void setEvaluationMonth(String evaluationMonth) {
		this.evaluationMonth = evaluationMonth;
	}

	public String getFamilyActivityParticipation() {
		return this.familyActivityParticipation;
	}

	public void setFamilyActivityParticipation(String familyActivityParticipation) {
		this.familyActivityParticipation = familyActivityParticipation;
	}

	public Integer getInterestInFamily() {
		return this.interestInFamily;
	}

	public void setInterestInFamily(Integer interestInFamily) {
		this.interestInFamily = interestInFamily;
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

	public String getProblems() {
		return this.problems;
	}

	public void setProblems(String problems) {
		this.problems = problems;
	}

	public String getRelationship() {
		return this.relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getRelationshipComments() {
		return this.relationshipComments;
	}

	public void setRelationshipComments(String relationshipComments) {
		this.relationshipComments = relationshipComments;
	}

	public Integer getRelationshipScale() {
		return this.relationshipScale;
	}

	public void setRelationshipScale(Integer relationshipScale) {
		this.relationshipScale = relationshipScale;
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

	public FieldStaff getFieldStaff() {
		return this.fieldStaff;
	}

	public void setFieldStaff(FieldStaff fieldStaff) {
		this.fieldStaff = fieldStaff;
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