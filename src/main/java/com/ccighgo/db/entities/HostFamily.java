package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the HostFamily database table.
 * 
 */
@Entity
@NamedQuery(name="HostFamily.findAll", query="SELECT h FROM HostFamily h")
public class HostFamily implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer hostFamilyGoId;

	private Byte active;

	private String bestContactNumberToReach;

	private String bestWayToReachYou;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer currentSeasonId;

	private String emergencyContact;

	private String emergencyPhone;

	private String firstName;

	private Byte hasHomeBusiness;

	private Byte haveAHomePhone;

	@Lob
	private String homeBusinessType;

	private Byte homePhone;

	private Byte isBlacklisted;

	private Byte isDoNotContact;

	private Byte isNotQuilified;

	private String lastName;

	private Byte liveAlone;

	private String mailingAddress1;

	private String mailingAddress2;

	private Byte mailingAddressSameAsCurrentAddress;

	private String mailingCity;

	private String mailingZipCode;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	private String phone;

	private String physicalAddress1;

	private String physicalAddress2;

	private String physicalCity;

	private Integer physicalCountryId;

	private String physicalZipCode;

	private Byte receiveEmails;

	private Byte skipIntroScreen;

	//bi-directional one-to-one association to GoIdSequence
	@OneToOne
	@JoinColumn(name="hostFamilyGoId",insertable=false,updatable=false)
	private GoIdSequence goIdSequence;

	//bi-directional many-to-one association to HostFamilyStatus
	@ManyToOne
	@JoinColumn(name="hostFamilyStatusId",insertable=false,updatable=false)
	private HostFamilyStatus hostFamilyStatus;

	//bi-directional many-to-one association to LookupCountry
	@ManyToOne
	@JoinColumn(name="physicalStateId",insertable=false,updatable=false)
	private LookupCountry lookupCountry;

	//bi-directional many-to-one association to LookupUSState
	@ManyToOne
	@JoinColumn(name="mailingStateId",insertable=false,updatable=false)
	private LookupUSState lookupUsstate1;

	//bi-directional many-to-one association to LookupUSState
	@ManyToOne
	@JoinColumn(name="physicalStateId",insertable=false,updatable=false)
	private LookupUSState lookupUsstate2;

	//bi-directional many-to-one association to HostFamilyAirport
	@OneToMany(mappedBy="hostFamily")
	private List<HostFamilyAirport> hostFamilyAirports;

	//bi-directional many-to-one association to HostFamilyInquiry
	@OneToMany(mappedBy="hostFamily")
	private List<HostFamilyInquiry> hostFamilyInquiries;

	//bi-directional many-to-one association to HostFamilyNote
	@OneToMany(mappedBy="hostFamily")
	private List<HostFamilyNote> hostFamilyNotes;

	//bi-directional many-to-one association to HostFamilyParticipantHistory
	@OneToMany(mappedBy="hostFamily")
	private List<HostFamilyParticipantHistory> hostFamilyParticipantHistories;

	//bi-directional many-to-one association to HostFamilyPermission
	@OneToMany(mappedBy="hostFamily")
	private List<HostFamilyPermission> hostFamilyPermissions;

	//bi-directional many-to-one association to HostFamilyPotentialReference
	@OneToMany(mappedBy="hostFamily")
	private List<HostFamilyPotentialReference> hostFamilyPotentialReferences;

	//bi-directional many-to-one association to HostFamilySeason
	@OneToMany(mappedBy="hostFamily")
	private List<HostFamilySeason> hostFamilySeasons;

	public HostFamily() {
	}

	public Integer getHostFamilyGoId() {
		return this.hostFamilyGoId;
	}

	public void setHostFamilyGoId(Integer hostFamilyGoId) {
		this.hostFamilyGoId = hostFamilyGoId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public String getBestContactNumberToReach() {
		return this.bestContactNumberToReach;
	}

	public void setBestContactNumberToReach(String bestContactNumberToReach) {
		this.bestContactNumberToReach = bestContactNumberToReach;
	}

	public String getBestWayToReachYou() {
		return this.bestWayToReachYou;
	}

	public void setBestWayToReachYou(String bestWayToReachYou) {
		this.bestWayToReachYou = bestWayToReachYou;
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

	public Integer getCurrentSeasonId() {
		return this.currentSeasonId;
	}

	public void setCurrentSeasonId(Integer currentSeasonId) {
		this.currentSeasonId = currentSeasonId;
	}

	public String getEmergencyContact() {
		return this.emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getEmergencyPhone() {
		return this.emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Byte getHasHomeBusiness() {
		return this.hasHomeBusiness;
	}

	public void setHasHomeBusiness(Byte hasHomeBusiness) {
		this.hasHomeBusiness = hasHomeBusiness;
	}

	public Byte getHaveAHomePhone() {
		return this.haveAHomePhone;
	}

	public void setHaveAHomePhone(Byte haveAHomePhone) {
		this.haveAHomePhone = haveAHomePhone;
	}

	public String getHomeBusinessType() {
		return this.homeBusinessType;
	}

	public void setHomeBusinessType(String homeBusinessType) {
		this.homeBusinessType = homeBusinessType;
	}

	public Byte getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(Byte homePhone) {
		this.homePhone = homePhone;
	}

	public Byte getIsBlacklisted() {
		return this.isBlacklisted;
	}

	public void setIsBlacklisted(Byte isBlacklisted) {
		this.isBlacklisted = isBlacklisted;
	}

	public Byte getIsDoNotContact() {
		return this.isDoNotContact;
	}

	public void setIsDoNotContact(Byte isDoNotContact) {
		this.isDoNotContact = isDoNotContact;
	}

	public Byte getIsNotQuilified() {
		return this.isNotQuilified;
	}

	public void setIsNotQuilified(Byte isNotQuilified) {
		this.isNotQuilified = isNotQuilified;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Byte getLiveAlone() {
		return this.liveAlone;
	}

	public void setLiveAlone(Byte liveAlone) {
		this.liveAlone = liveAlone;
	}

	public String getMailingAddress1() {
		return this.mailingAddress1;
	}

	public void setMailingAddress1(String mailingAddress1) {
		this.mailingAddress1 = mailingAddress1;
	}

	public String getMailingAddress2() {
		return this.mailingAddress2;
	}

	public void setMailingAddress2(String mailingAddress2) {
		this.mailingAddress2 = mailingAddress2;
	}

	public Byte getMailingAddressSameAsCurrentAddress() {
		return this.mailingAddressSameAsCurrentAddress;
	}

	public void setMailingAddressSameAsCurrentAddress(Byte mailingAddressSameAsCurrentAddress) {
		this.mailingAddressSameAsCurrentAddress = mailingAddressSameAsCurrentAddress;
	}

	public String getMailingCity() {
		return this.mailingCity;
	}

	public void setMailingCity(String mailingCity) {
		this.mailingCity = mailingCity;
	}

	public String getMailingZipCode() {
		return this.mailingZipCode;
	}

	public void setMailingZipCode(String mailingZipCode) {
		this.mailingZipCode = mailingZipCode;
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

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhysicalAddress1() {
		return this.physicalAddress1;
	}

	public void setPhysicalAddress1(String physicalAddress1) {
		this.physicalAddress1 = physicalAddress1;
	}

	public String getPhysicalAddress2() {
		return this.physicalAddress2;
	}

	public void setPhysicalAddress2(String physicalAddress2) {
		this.physicalAddress2 = physicalAddress2;
	}

	public String getPhysicalCity() {
		return this.physicalCity;
	}

	public void setPhysicalCity(String physicalCity) {
		this.physicalCity = physicalCity;
	}

	public Integer getPhysicalCountryId() {
		return this.physicalCountryId;
	}

	public void setPhysicalCountryId(Integer physicalCountryId) {
		this.physicalCountryId = physicalCountryId;
	}

	public String getPhysicalZipCode() {
		return this.physicalZipCode;
	}

	public void setPhysicalZipCode(String physicalZipCode) {
		this.physicalZipCode = physicalZipCode;
	}

	public Byte getReceiveEmails() {
		return this.receiveEmails;
	}

	public void setReceiveEmails(Byte receiveEmails) {
		this.receiveEmails = receiveEmails;
	}

	public Byte getSkipIntroScreen() {
		return this.skipIntroScreen;
	}

	public void setSkipIntroScreen(Byte skipIntroScreen) {
		this.skipIntroScreen = skipIntroScreen;
	}

	public GoIdSequence getGoIdSequence() {
		return this.goIdSequence;
	}

	public void setGoIdSequence(GoIdSequence goIdSequence) {
		this.goIdSequence = goIdSequence;
	}

	public HostFamilyStatus getHostFamilyStatus() {
		return this.hostFamilyStatus;
	}

	public void setHostFamilyStatus(HostFamilyStatus hostFamilyStatus) {
		this.hostFamilyStatus = hostFamilyStatus;
	}

	public LookupCountry getLookupCountry() {
		return this.lookupCountry;
	}

	public void setLookupCountry(LookupCountry lookupCountry) {
		this.lookupCountry = lookupCountry;
	}

	public LookupUSState getLookupUsstate1() {
		return this.lookupUsstate1;
	}

	public void setLookupUsstate1(LookupUSState lookupUsstate1) {
		this.lookupUsstate1 = lookupUsstate1;
	}

	public LookupUSState getLookupUsstate2() {
		return this.lookupUsstate2;
	}

	public void setLookupUsstate2(LookupUSState lookupUsstate2) {
		this.lookupUsstate2 = lookupUsstate2;
	}

	public List<HostFamilyAirport> getHostFamilyAirports() {
		return this.hostFamilyAirports;
	}

	public void setHostFamilyAirports(List<HostFamilyAirport> hostFamilyAirports) {
		this.hostFamilyAirports = hostFamilyAirports;
	}

	public HostFamilyAirport addHostFamilyAirport(HostFamilyAirport hostFamilyAirport) {
		getHostFamilyAirports().add(hostFamilyAirport);
		hostFamilyAirport.setHostFamily(this);

		return hostFamilyAirport;
	}

	public HostFamilyAirport removeHostFamilyAirport(HostFamilyAirport hostFamilyAirport) {
		getHostFamilyAirports().remove(hostFamilyAirport);
		hostFamilyAirport.setHostFamily(null);

		return hostFamilyAirport;
	}

	public List<HostFamilyInquiry> getHostFamilyInquiries() {
		return this.hostFamilyInquiries;
	}

	public void setHostFamilyInquiries(List<HostFamilyInquiry> hostFamilyInquiries) {
		this.hostFamilyInquiries = hostFamilyInquiries;
	}

	public HostFamilyInquiry addHostFamilyInquiry(HostFamilyInquiry hostFamilyInquiry) {
		getHostFamilyInquiries().add(hostFamilyInquiry);
		hostFamilyInquiry.setHostFamily(this);

		return hostFamilyInquiry;
	}

	public HostFamilyInquiry removeHostFamilyInquiry(HostFamilyInquiry hostFamilyInquiry) {
		getHostFamilyInquiries().remove(hostFamilyInquiry);
		hostFamilyInquiry.setHostFamily(null);

		return hostFamilyInquiry;
	}

	public List<HostFamilyNote> getHostFamilyNotes() {
		return this.hostFamilyNotes;
	}

	public void setHostFamilyNotes(List<HostFamilyNote> hostFamilyNotes) {
		this.hostFamilyNotes = hostFamilyNotes;
	}

	public HostFamilyNote addHostFamilyNote(HostFamilyNote hostFamilyNote) {
		getHostFamilyNotes().add(hostFamilyNote);
		hostFamilyNote.setHostFamily(this);

		return hostFamilyNote;
	}

	public HostFamilyNote removeHostFamilyNote(HostFamilyNote hostFamilyNote) {
		getHostFamilyNotes().remove(hostFamilyNote);
		hostFamilyNote.setHostFamily(null);

		return hostFamilyNote;
	}

	public List<HostFamilyParticipantHistory> getHostFamilyParticipantHistories() {
		return this.hostFamilyParticipantHistories;
	}

	public void setHostFamilyParticipantHistories(List<HostFamilyParticipantHistory> hostFamilyParticipantHistories) {
		this.hostFamilyParticipantHistories = hostFamilyParticipantHistories;
	}

	public HostFamilyParticipantHistory addHostFamilyParticipantHistory(HostFamilyParticipantHistory hostFamilyParticipantHistory) {
		getHostFamilyParticipantHistories().add(hostFamilyParticipantHistory);
		hostFamilyParticipantHistory.setHostFamily(this);

		return hostFamilyParticipantHistory;
	}

	public HostFamilyParticipantHistory removeHostFamilyParticipantHistory(HostFamilyParticipantHistory hostFamilyParticipantHistory) {
		getHostFamilyParticipantHistories().remove(hostFamilyParticipantHistory);
		hostFamilyParticipantHistory.setHostFamily(null);

		return hostFamilyParticipantHistory;
	}

	public List<HostFamilyPermission> getHostFamilyPermissions() {
		return this.hostFamilyPermissions;
	}

	public void setHostFamilyPermissions(List<HostFamilyPermission> hostFamilyPermissions) {
		this.hostFamilyPermissions = hostFamilyPermissions;
	}

	public HostFamilyPermission addHostFamilyPermission(HostFamilyPermission hostFamilyPermission) {
		getHostFamilyPermissions().add(hostFamilyPermission);
		hostFamilyPermission.setHostFamily(this);

		return hostFamilyPermission;
	}

	public HostFamilyPermission removeHostFamilyPermission(HostFamilyPermission hostFamilyPermission) {
		getHostFamilyPermissions().remove(hostFamilyPermission);
		hostFamilyPermission.setHostFamily(null);

		return hostFamilyPermission;
	}

	public List<HostFamilyPotentialReference> getHostFamilyPotentialReferences() {
		return this.hostFamilyPotentialReferences;
	}

	public void setHostFamilyPotentialReferences(List<HostFamilyPotentialReference> hostFamilyPotentialReferences) {
		this.hostFamilyPotentialReferences = hostFamilyPotentialReferences;
	}

	public HostFamilyPotentialReference addHostFamilyPotentialReference(HostFamilyPotentialReference hostFamilyPotentialReference) {
		getHostFamilyPotentialReferences().add(hostFamilyPotentialReference);
		hostFamilyPotentialReference.setHostFamily(this);

		return hostFamilyPotentialReference;
	}

	public HostFamilyPotentialReference removeHostFamilyPotentialReference(HostFamilyPotentialReference hostFamilyPotentialReference) {
		getHostFamilyPotentialReferences().remove(hostFamilyPotentialReference);
		hostFamilyPotentialReference.setHostFamily(null);

		return hostFamilyPotentialReference;
	}

	public List<HostFamilySeason> getHostFamilySeasons() {
		return this.hostFamilySeasons;
	}

	public void setHostFamilySeasons(List<HostFamilySeason> hostFamilySeasons) {
		this.hostFamilySeasons = hostFamilySeasons;
	}

	public HostFamilySeason addHostFamilySeason(HostFamilySeason hostFamilySeason) {
		getHostFamilySeasons().add(hostFamilySeason);
		hostFamilySeason.setHostFamily(this);

		return hostFamilySeason;
	}

	public HostFamilySeason removeHostFamilySeason(HostFamilySeason hostFamilySeason) {
		getHostFamilySeasons().remove(hostFamilySeason);
		hostFamilySeason.setHostFamily(null);

		return hostFamilySeason;
	}

}