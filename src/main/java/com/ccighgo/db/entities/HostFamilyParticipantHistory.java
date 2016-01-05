package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilyParticipantHistory database table.
 * 
 */
@Entity
@Table(name="HostFamilyParticipantHistory")
@NamedQuery(name="HostFamilyParticipantHistory.findAll", query="SELECT h FROM HostFamilyParticipantHistory h")
public class HostFamilyParticipantHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyParticipantHistoryId;

	private Byte active;

	@Temporal(TemporalType.TIMESTAMP)
	private Date beginDate;

	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Column(length=50)
	private String hostFamilyName;

	@Column(length=50)
	private String localCoordinatorName;

	private int modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="localCoordinatorId")
	private FieldStaff fieldStaff;

	//bi-directional many-to-one association to HostFamily
	@ManyToOne
	@JoinColumn(name="hostFamilyGoId")
	private HostFamily hostFamily;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId")
	private HostFamilySeason hostFamilySeason;

	//bi-directional many-to-one association to Participant
	@ManyToOne
	@JoinColumn(name="participantGoId")
	private Participant participant;

	public HostFamilyParticipantHistory() {
	}

	public Integer getHostFamilyParticipantHistoryId() {
		return this.hostFamilyParticipantHistoryId;
	}

	public void setHostFamilyParticipantHistoryId(Integer hostFamilyParticipantHistoryId) {
		this.hostFamilyParticipantHistoryId = hostFamilyParticipantHistoryId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getHostFamilyName() {
		return this.hostFamilyName;
	}

	public void setHostFamilyName(String hostFamilyName) {
		this.hostFamilyName = hostFamilyName;
	}

	public String getLocalCoordinatorName() {
		return this.localCoordinatorName;
	}

	public void setLocalCoordinatorName(String localCoordinatorName) {
		this.localCoordinatorName = localCoordinatorName;
	}

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public FieldStaff getFieldStaff() {
		return this.fieldStaff;
	}

	public void setFieldStaff(FieldStaff fieldStaff) {
		this.fieldStaff = fieldStaff;
	}

	public HostFamily getHostFamily() {
		return this.hostFamily;
	}

	public void setHostFamily(HostFamily hostFamily) {
		this.hostFamily = hostFamily;
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