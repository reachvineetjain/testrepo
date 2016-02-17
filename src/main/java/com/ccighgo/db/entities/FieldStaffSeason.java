package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the FieldStaffSeason database table.
 * 
 */
@Entity
@NamedQuery(name="FieldStaffSeason.findAll", query="SELECT f FROM FieldStaffSeason f")
public class FieldStaffSeason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer filedStaffSeasonId;

	private Byte active;

	private Byte agreeToTerms;

	private Byte canRepresentGrantPax;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer defaultMonitoringStipendId;

	private Byte isRecruiterLC;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	private String signature;

	@Temporal(TemporalType.TIMESTAMP)
	private Date signedDate;

	//bi-directional many-to-one association to DepartmentProgram
	@ManyToOne
	@JoinColumn(name="departmentProgramId")
	private DepartmentProgram departmentProgram;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="fieldStaffGoId")
	private FieldStaff fieldStaff;

	//bi-directional many-to-one association to FieldStaffStatus
	@ManyToOne
	@JoinColumn(name="fieldStaffSeasonStatusId")
	private FieldStaffStatus fieldStaffStatus;

	//bi-directional many-to-one association to PaymentSchedule
	@ManyToOne
	@JoinColumn(name="paymentScheduleId")
	private PaymentSchedule paymentSchedule;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId")
	private Season season;

	//bi-directional many-to-one association to FieldStaffSeasonDocument
	@OneToMany(mappedBy="fieldStaffSeason")
	private List<FieldStaffSeasonDocument> fieldStaffSeasonDocuments;

	public FieldStaffSeason() {
	}

	public Integer getFiledStaffSeasonId() {
		return this.filedStaffSeasonId;
	}

	public void setFiledStaffSeasonId(Integer filedStaffSeasonId) {
		this.filedStaffSeasonId = filedStaffSeasonId;
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

	public Byte getCanRepresentGrantPax() {
		return this.canRepresentGrantPax;
	}

	public void setCanRepresentGrantPax(Byte canRepresentGrantPax) {
		this.canRepresentGrantPax = canRepresentGrantPax;
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

	public Integer getDefaultMonitoringStipendId() {
		return this.defaultMonitoringStipendId;
	}

	public void setDefaultMonitoringStipendId(Integer defaultMonitoringStipendId) {
		this.defaultMonitoringStipendId = defaultMonitoringStipendId;
	}

	public Byte getIsRecruiterLC() {
		return this.isRecruiterLC;
	}

	public void setIsRecruiterLC(Byte isRecruiterLC) {
		this.isRecruiterLC = isRecruiterLC;
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

	public FieldStaff getFieldStaff() {
		return this.fieldStaff;
	}

	public void setFieldStaff(FieldStaff fieldStaff) {
		this.fieldStaff = fieldStaff;
	}

	public FieldStaffStatus getFieldStaffStatus() {
		return this.fieldStaffStatus;
	}

	public void setFieldStaffStatus(FieldStaffStatus fieldStaffStatus) {
		this.fieldStaffStatus = fieldStaffStatus;
	}

	public PaymentSchedule getPaymentSchedule() {
		return this.paymentSchedule;
	}

	public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
		this.paymentSchedule = paymentSchedule;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public List<FieldStaffSeasonDocument> getFieldStaffSeasonDocuments() {
		return this.fieldStaffSeasonDocuments;
	}

	public void setFieldStaffSeasonDocuments(List<FieldStaffSeasonDocument> fieldStaffSeasonDocuments) {
		this.fieldStaffSeasonDocuments = fieldStaffSeasonDocuments;
	}

	public FieldStaffSeasonDocument addFieldStaffSeasonDocument(FieldStaffSeasonDocument fieldStaffSeasonDocument) {
		getFieldStaffSeasonDocuments().add(fieldStaffSeasonDocument);
		fieldStaffSeasonDocument.setFieldStaffSeason(this);

		return fieldStaffSeasonDocument;
	}

	public FieldStaffSeasonDocument removeFieldStaffSeasonDocument(FieldStaffSeasonDocument fieldStaffSeasonDocument) {
		getFieldStaffSeasonDocuments().remove(fieldStaffSeasonDocument);
		fieldStaffSeasonDocument.setFieldStaffSeason(null);

		return fieldStaffSeasonDocument;
	}

}