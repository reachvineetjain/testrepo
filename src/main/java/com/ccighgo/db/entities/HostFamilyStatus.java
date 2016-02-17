package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the HostFamilyStatus database table.
 * 
 */
@Entity
@NamedQuery(name="HostFamilyStatus.findAll", query="SELECT h FROM HostFamilyStatus h")
public class HostFamilyStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer hostFamilyStatusId;

	private Byte active;

	private String hostFamilyStatusName;

	private Byte isParticipantStatus;

	private Byte isSeasonStatus;

	//bi-directional many-to-one association to HostFamily
	@OneToMany(mappedBy="hostFamilyStatus")
	private List<HostFamily> hostFamilies;

	//bi-directional many-to-one association to HostFamilyInquiry
	@OneToMany(mappedBy="hostFamilyStatus")
	private List<HostFamilyInquiry> hostFamilyInquiries;

	//bi-directional many-to-one association to HostFamilyParticipant
	@OneToMany(mappedBy="hostFamilyStatus")
	private List<HostFamilyParticipant> hostFamilyParticipants;

	//bi-directional many-to-one association to HostFamilySeason
	@OneToMany(mappedBy="hostFamilyStatus")
	private List<HostFamilySeason> hostFamilySeasons;

	public HostFamilyStatus() {
	}

	public Integer getHostFamilyStatusId() {
		return this.hostFamilyStatusId;
	}

	public void setHostFamilyStatusId(Integer hostFamilyStatusId) {
		this.hostFamilyStatusId = hostFamilyStatusId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public String getHostFamilyStatusName() {
		return this.hostFamilyStatusName;
	}

	public void setHostFamilyStatusName(String hostFamilyStatusName) {
		this.hostFamilyStatusName = hostFamilyStatusName;
	}

	public Byte getIsParticipantStatus() {
		return this.isParticipantStatus;
	}

	public void setIsParticipantStatus(Byte isParticipantStatus) {
		this.isParticipantStatus = isParticipantStatus;
	}

	public Byte getIsSeasonStatus() {
		return this.isSeasonStatus;
	}

	public void setIsSeasonStatus(Byte isSeasonStatus) {
		this.isSeasonStatus = isSeasonStatus;
	}

	public List<HostFamily> getHostFamilies() {
		return this.hostFamilies;
	}

	public void setHostFamilies(List<HostFamily> hostFamilies) {
		this.hostFamilies = hostFamilies;
	}

	public HostFamily addHostFamily(HostFamily hostFamily) {
		getHostFamilies().add(hostFamily);
		hostFamily.setHostFamilyStatus(this);

		return hostFamily;
	}

	public HostFamily removeHostFamily(HostFamily hostFamily) {
		getHostFamilies().remove(hostFamily);
		hostFamily.setHostFamilyStatus(null);

		return hostFamily;
	}

	public List<HostFamilyInquiry> getHostFamilyInquiries() {
		return this.hostFamilyInquiries;
	}

	public void setHostFamilyInquiries(List<HostFamilyInquiry> hostFamilyInquiries) {
		this.hostFamilyInquiries = hostFamilyInquiries;
	}

	public HostFamilyInquiry addHostFamilyInquiry(HostFamilyInquiry hostFamilyInquiry) {
		getHostFamilyInquiries().add(hostFamilyInquiry);
		hostFamilyInquiry.setHostFamilyStatus(this);

		return hostFamilyInquiry;
	}

	public HostFamilyInquiry removeHostFamilyInquiry(HostFamilyInquiry hostFamilyInquiry) {
		getHostFamilyInquiries().remove(hostFamilyInquiry);
		hostFamilyInquiry.setHostFamilyStatus(null);

		return hostFamilyInquiry;
	}

	public List<HostFamilyParticipant> getHostFamilyParticipants() {
		return this.hostFamilyParticipants;
	}

	public void setHostFamilyParticipants(List<HostFamilyParticipant> hostFamilyParticipants) {
		this.hostFamilyParticipants = hostFamilyParticipants;
	}

	public HostFamilyParticipant addHostFamilyParticipant(HostFamilyParticipant hostFamilyParticipant) {
		getHostFamilyParticipants().add(hostFamilyParticipant);
		hostFamilyParticipant.setHostFamilyStatus(this);

		return hostFamilyParticipant;
	}

	public HostFamilyParticipant removeHostFamilyParticipant(HostFamilyParticipant hostFamilyParticipant) {
		getHostFamilyParticipants().remove(hostFamilyParticipant);
		hostFamilyParticipant.setHostFamilyStatus(null);

		return hostFamilyParticipant;
	}

	public List<HostFamilySeason> getHostFamilySeasons() {
		return this.hostFamilySeasons;
	}

	public void setHostFamilySeasons(List<HostFamilySeason> hostFamilySeasons) {
		this.hostFamilySeasons = hostFamilySeasons;
	}

	public HostFamilySeason addHostFamilySeason(HostFamilySeason hostFamilySeason) {
		getHostFamilySeasons().add(hostFamilySeason);
		hostFamilySeason.setHostFamilyStatus(this);

		return hostFamilySeason;
	}

	public HostFamilySeason removeHostFamilySeason(HostFamilySeason hostFamilySeason) {
		getHostFamilySeasons().remove(hostFamilySeason);
		hostFamilySeason.setHostFamilyStatus(null);

		return hostFamilySeason;
	}

}