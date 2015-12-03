package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the FieldStaffLeadershipSeasonDetails database table.
 * 
 */
@Entity
@Table(name="FieldStaffLeadershipSeasonDetails")
@NamedQuery(name="FieldStaffLeadershipSeasonDetail.findAll", query="SELECT f FROM FieldStaffLeadershipSeasonDetail f")
public class FieldStaffLeadershipSeasonDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer fieldStaffLeadershipSeasonDetailsId;

	private Byte active;

	private Byte agreeToTerms;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer defaultMonotoringStipendId;

	private Byte isFlexStaff;

	private Byte isRecruiterLC;

	private Byte isYesStaff;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	private Integer paymentScheduleId;

	@Column(length=100)
	private String signature;

	@Temporal(TemporalType.TIMESTAMP)
	private Date signedDate;

	//bi-directional many-to-one association to DepartmentProgram
	@ManyToOne
	@JoinColumn(name="departmentProgramId")
	private DepartmentProgram departmentProgram;

	//bi-directional many-to-one association to FieldStaffLeadershipSeason
	@ManyToOne
	@JoinColumn(name="fieldStaffLeadershipSeasonId")
	private FieldStaffLeadershipSeason fieldStaffLeadershipSeason;

	//bi-directional many-to-one association to FieldStaffStatus
	@ManyToOne
	@JoinColumn(name="fieldStaffSeasonStatusId")
	private FieldStaffStatus fieldStaffStatus;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId")
	private Season season;

	//bi-directional many-to-one association to FieldStaffLeadershipSeasonDocument
	@OneToMany(mappedBy="fieldStaffLeadershipSeasonDetail")
	private List<FieldStaffLeadershipSeasonDocument> fieldStaffLeadershipSeasonDocuments;

	public FieldStaffLeadershipSeasonDetail() {
	}

	public Integer getFieldStaffLeadershipSeasonDetailsId() {
		return this.fieldStaffLeadershipSeasonDetailsId;
	}

	public void setFieldStaffLeadershipSeasonDetailsId(Integer fieldStaffLeadershipSeasonDetailsId) {
		this.fieldStaffLeadershipSeasonDetailsId = fieldStaffLeadershipSeasonDetailsId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Byte getAgreeToTerms() {
		return this.agreeToTerms;
	}

	public void setAgreeToTerms(Byte agreeToTerms) {
		this.agreeToTerms = agreeToTerms;
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

	public Integer getDefaultMonotoringStipendId() {
		return this.defaultMonotoringStipendId;
	}

	public void setDefaultMonotoringStipendId(Integer defaultMonotoringStipendId) {
		this.defaultMonotoringStipendId = defaultMonotoringStipendId;
	}

	public Byte getIsFlexStaff() {
		return this.isFlexStaff;
	}

	public void setIsFlexStaff(Byte isFlexStaff) {
		this.isFlexStaff = isFlexStaff;
	}

	public Byte getIsRecruiterLC() {
		return this.isRecruiterLC;
	}

	public void setIsRecruiterLC(Byte isRecruiterLC) {
		this.isRecruiterLC = isRecruiterLC;
	}

	public Byte getIsYesStaff() {
		return this.isYesStaff;
	}

	public void setIsYesStaff(Byte isYesStaff) {
		this.isYesStaff = isYesStaff;
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

	public Integer getPaymentScheduleId() {
		return this.paymentScheduleId;
	}

	public void setPaymentScheduleId(Integer paymentScheduleId) {
		this.paymentScheduleId = paymentScheduleId;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Date getSignedDate() {
		return this.signedDate;
	}

	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
	}

	public DepartmentProgram getDepartmentProgram() {
		return this.departmentProgram;
	}

	public void setDepartmentProgram(DepartmentProgram departmentProgram) {
		this.departmentProgram = departmentProgram;
	}

	public FieldStaffLeadershipSeason getFieldStaffLeadershipSeason() {
		return this.fieldStaffLeadershipSeason;
	}

	public void setFieldStaffLeadershipSeason(FieldStaffLeadershipSeason fieldStaffLeadershipSeason) {
		this.fieldStaffLeadershipSeason = fieldStaffLeadershipSeason;
	}

	public FieldStaffStatus getFieldStaffStatus() {
		return this.fieldStaffStatus;
	}

	public void setFieldStaffStatus(FieldStaffStatus fieldStaffStatus) {
		this.fieldStaffStatus = fieldStaffStatus;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public List<FieldStaffLeadershipSeasonDocument> getFieldStaffLeadershipSeasonDocuments() {
		return this.fieldStaffLeadershipSeasonDocuments;
	}

	public void setFieldStaffLeadershipSeasonDocuments(List<FieldStaffLeadershipSeasonDocument> fieldStaffLeadershipSeasonDocuments) {
		this.fieldStaffLeadershipSeasonDocuments = fieldStaffLeadershipSeasonDocuments;
	}

	public FieldStaffLeadershipSeasonDocument addFieldStaffLeadershipSeasonDocument(FieldStaffLeadershipSeasonDocument fieldStaffLeadershipSeasonDocument) {
		getFieldStaffLeadershipSeasonDocuments().add(fieldStaffLeadershipSeasonDocument);
		fieldStaffLeadershipSeasonDocument.setFieldStaffLeadershipSeasonDetail(this);

		return fieldStaffLeadershipSeasonDocument;
	}

	public FieldStaffLeadershipSeasonDocument removeFieldStaffLeadershipSeasonDocument(FieldStaffLeadershipSeasonDocument fieldStaffLeadershipSeasonDocument) {
		getFieldStaffLeadershipSeasonDocuments().remove(fieldStaffLeadershipSeasonDocument);
		fieldStaffLeadershipSeasonDocument.setFieldStaffLeadershipSeasonDetail(null);

		return fieldStaffLeadershipSeasonDocument;
	}

}