package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the LookupCountries database table.
 * 
 */
@Entity
@Table(name="LookupCountries")
@NamedQuery(name="LookupCountry.findAll", query="SELECT l FROM LookupCountry l")
public class LookupCountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer countryId;

	@Column(nullable=false)
	private Byte active;

	@Column(nullable=false, length=5)
	private String countryCode;

	@Column(length=300)
	private String countryFlag;

	@Column(nullable=false, length=50)
	private String countryName;

	@Column(nullable=false)
	private Byte isReqFinalSOAonDS;

	//bi-directional many-to-one association to CCIStaffUser
	@OneToMany(mappedBy="lookupCountry")
	private List<CCIStaffUser> ccistaffUsers;

	//bi-directional many-to-one association to HostFamily
	@OneToMany(mappedBy="lookupCountry1")
	private List<HostFamily> hostFamilies1;

	//bi-directional many-to-one association to HostFamily
	@OneToMany(mappedBy="lookupCountry2")
	private List<HostFamily> hostFamilies2;

	//bi-directional many-to-one association to Participant
	@OneToMany(mappedBy="lookupCountry")
	private List<Participant> participants;

	//bi-directional many-to-one association to Partner
	@OneToMany(mappedBy="lookupCountry1")
	private List<Partner> partners1;

	//bi-directional many-to-one association to Partner
	@OneToMany(mappedBy="lookupCountry2")
	private List<Partner> partners2;

	//bi-directional many-to-one association to PartnerAgentInquiry
	@OneToMany(mappedBy="lookupCountry")
	private List<PartnerAgentInquiry> partnerAgentInquiries;

	//bi-directional many-to-one association to PartnerOffice
	@OneToMany(mappedBy="lookupCountry")
	private List<PartnerOffice> partnerOffices;

	public LookupCountry() {
	}

	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryFlag() {
		return this.countryFlag;
	}

	public void setCountryFlag(String countryFlag) {
		this.countryFlag = countryFlag;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Byte getIsReqFinalSOAonDS() {
		return this.isReqFinalSOAonDS;
	}

	public void setIsReqFinalSOAonDS(Byte isReqFinalSOAonDS) {
		this.isReqFinalSOAonDS = isReqFinalSOAonDS;
	}

	public List<CCIStaffUser> getCcistaffUsers() {
		return this.ccistaffUsers;
	}

	public void setCcistaffUsers(List<CCIStaffUser> ccistaffUsers) {
		this.ccistaffUsers = ccistaffUsers;
	}

	public CCIStaffUser addCcistaffUser(CCIStaffUser ccistaffUser) {
		getCcistaffUsers().add(ccistaffUser);
		ccistaffUser.setLookupCountry(this);

		return ccistaffUser;
	}

	public CCIStaffUser removeCcistaffUser(CCIStaffUser ccistaffUser) {
		getCcistaffUsers().remove(ccistaffUser);
		ccistaffUser.setLookupCountry(null);

		return ccistaffUser;
	}

	public List<HostFamily> getHostFamilies1() {
		return this.hostFamilies1;
	}

	public void setHostFamilies1(List<HostFamily> hostFamilies1) {
		this.hostFamilies1 = hostFamilies1;
	}

	public HostFamily addHostFamilies1(HostFamily hostFamilies1) {
		getHostFamilies1().add(hostFamilies1);
		hostFamilies1.setLookupCountry1(this);

		return hostFamilies1;
	}

	public HostFamily removeHostFamilies1(HostFamily hostFamilies1) {
		getHostFamilies1().remove(hostFamilies1);
		hostFamilies1.setLookupCountry1(null);

		return hostFamilies1;
	}

	public List<HostFamily> getHostFamilies2() {
		return this.hostFamilies2;
	}

	public void setHostFamilies2(List<HostFamily> hostFamilies2) {
		this.hostFamilies2 = hostFamilies2;
	}

	public HostFamily addHostFamilies2(HostFamily hostFamilies2) {
		getHostFamilies2().add(hostFamilies2);
		hostFamilies2.setLookupCountry2(this);

		return hostFamilies2;
	}

	public HostFamily removeHostFamilies2(HostFamily hostFamilies2) {
		getHostFamilies2().remove(hostFamilies2);
		hostFamilies2.setLookupCountry2(null);

		return hostFamilies2;
	}

	public List<Participant> getParticipants() {
		return this.participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public Participant addParticipant(Participant participant) {
		getParticipants().add(participant);
		participant.setLookupCountry(this);

		return participant;
	}

	public Participant removeParticipant(Participant participant) {
		getParticipants().remove(participant);
		participant.setLookupCountry(null);

		return participant;
	}

	public List<Partner> getPartners1() {
		return this.partners1;
	}

	public void setPartners1(List<Partner> partners1) {
		this.partners1 = partners1;
	}

	public Partner addPartners1(Partner partners1) {
		getPartners1().add(partners1);
		partners1.setLookupCountry1(this);

		return partners1;
	}

	public Partner removePartners1(Partner partners1) {
		getPartners1().remove(partners1);
		partners1.setLookupCountry1(null);

		return partners1;
	}

	public List<Partner> getPartners2() {
		return this.partners2;
	}

	public void setPartners2(List<Partner> partners2) {
		this.partners2 = partners2;
	}

	public Partner addPartners2(Partner partners2) {
		getPartners2().add(partners2);
		partners2.setLookupCountry2(this);

		return partners2;
	}

	public Partner removePartners2(Partner partners2) {
		getPartners2().remove(partners2);
		partners2.setLookupCountry2(null);

		return partners2;
	}

	public List<PartnerAgentInquiry> getPartnerAgentInquiries() {
		return this.partnerAgentInquiries;
	}

	public void setPartnerAgentInquiries(List<PartnerAgentInquiry> partnerAgentInquiries) {
		this.partnerAgentInquiries = partnerAgentInquiries;
	}

	public PartnerAgentInquiry addPartnerAgentInquiry(PartnerAgentInquiry partnerAgentInquiry) {
		getPartnerAgentInquiries().add(partnerAgentInquiry);
		partnerAgentInquiry.setLookupCountry(this);

		return partnerAgentInquiry;
	}

	public PartnerAgentInquiry removePartnerAgentInquiry(PartnerAgentInquiry partnerAgentInquiry) {
		getPartnerAgentInquiries().remove(partnerAgentInquiry);
		partnerAgentInquiry.setLookupCountry(null);

		return partnerAgentInquiry;
	}

	public List<PartnerOffice> getPartnerOffices() {
		return this.partnerOffices;
	}

	public void setPartnerOffices(List<PartnerOffice> partnerOffices) {
		this.partnerOffices = partnerOffices;
	}

	public PartnerOffice addPartnerOffice(PartnerOffice partnerOffice) {
		getPartnerOffices().add(partnerOffice);
		partnerOffice.setLookupCountry(this);

		return partnerOffice;
	}

	public PartnerOffice removePartnerOffice(PartnerOffice partnerOffice) {
		getPartnerOffices().remove(partnerOffice);
		partnerOffice.setLookupCountry(null);

		return partnerOffice;
	}

}