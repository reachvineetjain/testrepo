package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the FieldStaffLCSeason database table.
 * 
 */
@Entity
@Table(name="FieldStaffLCSeason")
@NamedQuery(name="FieldStaffLCSeason.findAll", query="SELECT f FROM FieldStaffLCSeason f")
public class FieldStaffLCSeason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer fieldStaffLCSeasonId;

	private Byte active;

	private Byte agreeToTerms;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer defaultMonitoringStipendId;

	private Byte isFLEXStaff;

	private Byte isYESStaff;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	@Column(length=100)
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

	//bi-directional many-to-one association to PaymentSchedule
	@ManyToOne
	@JoinColumn(name="paymentScheduleId")
	private PaymentSchedule paymentSchedule;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId")
	private Season season;

	//bi-directional many-to-one association to SeasonGeographyConfiguration
	@ManyToOne
	@JoinColumn(name="seasonGeographyConfigurationId")
	private SeasonGeographyConfiguration seasonGeographyConfiguration;

	//bi-directional many-to-one association to FieldStaffLCSeasonDocument
	@OneToMany(mappedBy="fieldStaffLcseason")
	private List<FieldStaffLCSeasonDocument> fieldStaffLcseasonDocuments;

	public FieldStaffLCSeason() {
	}

	public Integer getFieldStaffLCSeasonId() {
		return this.fieldStaffLCSeasonId;
	}

	public void setFieldStaffLCSeasonId(Integer fieldStaffLCSeasonId) {
		this.fieldStaffLCSeasonId = fieldStaffLCSeasonId;
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

	public Integer getDefaultMonitoringStipendId() {
		return this.defaultMonitoringStipendId;
	}

	public void setDefaultMonitoringStipendId(Integer defaultMonitoringStipendId) {
		this.defaultMonitoringStipendId = defaultMonitoringStipendId;
	}

	public Byte getIsFLEXStaff() {
		return this.isFLEXStaff;
	}

	public void setIsFLEXStaff(Byte isFLEXStaff) {
		this.isFLEXStaff = isFLEXStaff;
	}

	public Byte getIsYESStaff() {
		return this.isYESStaff;
	}

	public void setIsYESStaff(Byte isYESStaff) {
		this.isYESStaff = isYESStaff;
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

	public SeasonGeographyConfiguration getSeasonGeographyConfiguration() {
		return this.seasonGeographyConfiguration;
	}

	public void setSeasonGeographyConfiguration(SeasonGeographyConfiguration seasonGeographyConfiguration) {
		this.seasonGeographyConfiguration = seasonGeographyConfiguration;
	}

	public List<FieldStaffLCSeasonDocument> getFieldStaffLcseasonDocuments() {
		return this.fieldStaffLcseasonDocuments;
	}

	public void setFieldStaffLcseasonDocuments(List<FieldStaffLCSeasonDocument> fieldStaffLcseasonDocuments) {
		this.fieldStaffLcseasonDocuments = fieldStaffLcseasonDocuments;
	}

	public FieldStaffLCSeasonDocument addFieldStaffLcseasonDocument(FieldStaffLCSeasonDocument fieldStaffLcseasonDocument) {
		getFieldStaffLcseasonDocuments().add(fieldStaffLcseasonDocument);
		fieldStaffLcseasonDocument.setFieldStaffLcseason(this);

		return fieldStaffLcseasonDocument;
	}

	public FieldStaffLCSeasonDocument removeFieldStaffLcseasonDocument(FieldStaffLCSeasonDocument fieldStaffLcseasonDocument) {
		getFieldStaffLcseasonDocuments().remove(fieldStaffLcseasonDocument);
		fieldStaffLcseasonDocument.setFieldStaffLcseason(null);

		return fieldStaffLcseasonDocument;
	}

}