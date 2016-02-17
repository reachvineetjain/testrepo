package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the AnnouncementCreateUserType database table.
 * 
 */
@Entity
@Table(name="AnnouncementCreateUserType")
@NamedQuery(name="AnnouncementCreateUserType.findAll", query="SELECT a FROM AnnouncementCreateUserType a")
public class AnnouncementCreateUserType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer announcementCreateUserTypeId;

	@Column(nullable=false, length=50)
	private String announcementCreateUserTypeName;

	//bi-directional many-to-one association to AnnouncementInformation
	@OneToMany(mappedBy="announcementCreateUserType")
	private List<AnnouncementInformation> announcementInformations;

	//bi-directional many-to-one association to AnnouncementInformationHistory
	@OneToMany(mappedBy="announcementCreateUserType")
	private List<AnnouncementInformationHistory> announcementInformationHistories;

	//bi-directional many-to-one association to FieldStaffAnnouncementResult
	@OneToMany(mappedBy="announcementCreateUserType")
	private List<FieldStaffAnnouncementResult> fieldStaffAnnouncementResults;

	//bi-directional many-to-one association to HostFamilyAnnouncementResult
	@OneToMany(mappedBy="announcementCreateUserType")
	private List<HostFamilyAnnouncementResult> hostFamilyAnnouncementResults;

	//bi-directional many-to-one association to ParticipantAnnouncementResult
	@OneToMany(mappedBy="announcementCreateUserType")
	private List<ParticipantAnnouncementResult> participantAnnouncementResults;

	//bi-directional many-to-one association to PartnerAnnouncementResult
	@OneToMany(mappedBy="announcementCreateUserType")
	private List<PartnerAnnouncementResult> partnerAnnouncementResults;

	public AnnouncementCreateUserType() {
	}

	public Integer getAnnouncementCreateUserTypeId() {
		return this.announcementCreateUserTypeId;
	}

	public void setAnnouncementCreateUserTypeId(Integer announcementCreateUserTypeId) {
		this.announcementCreateUserTypeId = announcementCreateUserTypeId;
	}

	public String getAnnouncementCreateUserTypeName() {
		return this.announcementCreateUserTypeName;
	}

	public void setAnnouncementCreateUserTypeName(String announcementCreateUserTypeName) {
		this.announcementCreateUserTypeName = announcementCreateUserTypeName;
	}

	public List<AnnouncementInformation> getAnnouncementInformations() {
		return this.announcementInformations;
	}

	public void setAnnouncementInformations(List<AnnouncementInformation> announcementInformations) {
		this.announcementInformations = announcementInformations;
	}

	public AnnouncementInformation addAnnouncementInformation(AnnouncementInformation announcementInformation) {
		getAnnouncementInformations().add(announcementInformation);
		announcementInformation.setAnnouncementCreateUserType(this);

		return announcementInformation;
	}

	public AnnouncementInformation removeAnnouncementInformation(AnnouncementInformation announcementInformation) {
		getAnnouncementInformations().remove(announcementInformation);
		announcementInformation.setAnnouncementCreateUserType(null);

		return announcementInformation;
	}

	public List<AnnouncementInformationHistory> getAnnouncementInformationHistories() {
		return this.announcementInformationHistories;
	}

	public void setAnnouncementInformationHistories(List<AnnouncementInformationHistory> announcementInformationHistories) {
		this.announcementInformationHistories = announcementInformationHistories;
	}

	public AnnouncementInformationHistory addAnnouncementInformationHistory(AnnouncementInformationHistory announcementInformationHistory) {
		getAnnouncementInformationHistories().add(announcementInformationHistory);
		announcementInformationHistory.setAnnouncementCreateUserType(this);

		return announcementInformationHistory;
	}

	public AnnouncementInformationHistory removeAnnouncementInformationHistory(AnnouncementInformationHistory announcementInformationHistory) {
		getAnnouncementInformationHistories().remove(announcementInformationHistory);
		announcementInformationHistory.setAnnouncementCreateUserType(null);

		return announcementInformationHistory;
	}

	public List<FieldStaffAnnouncementResult> getFieldStaffAnnouncementResults() {
		return this.fieldStaffAnnouncementResults;
	}

	public void setFieldStaffAnnouncementResults(List<FieldStaffAnnouncementResult> fieldStaffAnnouncementResults) {
		this.fieldStaffAnnouncementResults = fieldStaffAnnouncementResults;
	}

	public FieldStaffAnnouncementResult addFieldStaffAnnouncementResult(FieldStaffAnnouncementResult fieldStaffAnnouncementResult) {
		getFieldStaffAnnouncementResults().add(fieldStaffAnnouncementResult);
		fieldStaffAnnouncementResult.setAnnouncementCreateUserType(this);

		return fieldStaffAnnouncementResult;
	}

	public FieldStaffAnnouncementResult removeFieldStaffAnnouncementResult(FieldStaffAnnouncementResult fieldStaffAnnouncementResult) {
		getFieldStaffAnnouncementResults().remove(fieldStaffAnnouncementResult);
		fieldStaffAnnouncementResult.setAnnouncementCreateUserType(null);

		return fieldStaffAnnouncementResult;
	}

	public List<HostFamilyAnnouncementResult> getHostFamilyAnnouncementResults() {
		return this.hostFamilyAnnouncementResults;
	}

	public void setHostFamilyAnnouncementResults(List<HostFamilyAnnouncementResult> hostFamilyAnnouncementResults) {
		this.hostFamilyAnnouncementResults = hostFamilyAnnouncementResults;
	}

	public HostFamilyAnnouncementResult addHostFamilyAnnouncementResult(HostFamilyAnnouncementResult hostFamilyAnnouncementResult) {
		getHostFamilyAnnouncementResults().add(hostFamilyAnnouncementResult);
		hostFamilyAnnouncementResult.setAnnouncementCreateUserType(this);

		return hostFamilyAnnouncementResult;
	}

	public HostFamilyAnnouncementResult removeHostFamilyAnnouncementResult(HostFamilyAnnouncementResult hostFamilyAnnouncementResult) {
		getHostFamilyAnnouncementResults().remove(hostFamilyAnnouncementResult);
		hostFamilyAnnouncementResult.setAnnouncementCreateUserType(null);

		return hostFamilyAnnouncementResult;
	}

	public List<ParticipantAnnouncementResult> getParticipantAnnouncementResults() {
		return this.participantAnnouncementResults;
	}

	public void setParticipantAnnouncementResults(List<ParticipantAnnouncementResult> participantAnnouncementResults) {
		this.participantAnnouncementResults = participantAnnouncementResults;
	}

	public ParticipantAnnouncementResult addParticipantAnnouncementResult(ParticipantAnnouncementResult participantAnnouncementResult) {
		getParticipantAnnouncementResults().add(participantAnnouncementResult);
		participantAnnouncementResult.setAnnouncementCreateUserType(this);

		return participantAnnouncementResult;
	}

	public ParticipantAnnouncementResult removeParticipantAnnouncementResult(ParticipantAnnouncementResult participantAnnouncementResult) {
		getParticipantAnnouncementResults().remove(participantAnnouncementResult);
		participantAnnouncementResult.setAnnouncementCreateUserType(null);

		return participantAnnouncementResult;
	}

	public List<PartnerAnnouncementResult> getPartnerAnnouncementResults() {
		return this.partnerAnnouncementResults;
	}

	public void setPartnerAnnouncementResults(List<PartnerAnnouncementResult> partnerAnnouncementResults) {
		this.partnerAnnouncementResults = partnerAnnouncementResults;
	}

	public PartnerAnnouncementResult addPartnerAnnouncementResult(PartnerAnnouncementResult partnerAnnouncementResult) {
		getPartnerAnnouncementResults().add(partnerAnnouncementResult);
		partnerAnnouncementResult.setAnnouncementCreateUserType(this);

		return partnerAnnouncementResult;
	}

	public PartnerAnnouncementResult removePartnerAnnouncementResult(PartnerAnnouncementResult partnerAnnouncementResult) {
		getPartnerAnnouncementResults().remove(partnerAnnouncementResult);
		partnerAnnouncementResult.setAnnouncementCreateUserType(null);

		return partnerAnnouncementResult;
	}

}