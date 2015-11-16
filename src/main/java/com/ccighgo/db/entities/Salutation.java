package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Salutation database table.
 * 
 */
@Entity
@Table(name="Salutation")
@NamedQuery(name="Salutation.findAll", query="SELECT s FROM Salutation s")
public class Salutation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer salutationId;

	private Byte active;

	@Column(length=30)
	private String salutationName;

	//bi-directional many-to-one association to PartnerAgentInquiry
	@OneToMany(mappedBy="salutation")
	private List<PartnerAgentInquiry> partnerAgentInquiries;

	//bi-directional many-to-one association to PartnerContact
	@OneToMany(mappedBy="salutation")
	private List<PartnerContact> partnerContacts;

	//bi-directional many-to-one association to PartnerUser
	@OneToMany(mappedBy="salutation")
	private List<PartnerUser> partnerUsers;

	public Salutation() {
	}

	public Integer getSalutationId() {
		return this.salutationId;
	}

	public void setSalutationId(Integer salutationId) {
		this.salutationId = salutationId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public String getSalutationName() {
		return this.salutationName;
	}

	public void setSalutationName(String salutationName) {
		this.salutationName = salutationName;
	}

	public List<PartnerAgentInquiry> getPartnerAgentInquiries() {
		return this.partnerAgentInquiries;
	}

	public void setPartnerAgentInquiries(List<PartnerAgentInquiry> partnerAgentInquiries) {
		this.partnerAgentInquiries = partnerAgentInquiries;
	}

	public PartnerAgentInquiry addPartnerAgentInquiry(PartnerAgentInquiry partnerAgentInquiry) {
		getPartnerAgentInquiries().add(partnerAgentInquiry);
		partnerAgentInquiry.setSalutation(this);

		return partnerAgentInquiry;
	}

	public PartnerAgentInquiry removePartnerAgentInquiry(PartnerAgentInquiry partnerAgentInquiry) {
		getPartnerAgentInquiries().remove(partnerAgentInquiry);
		partnerAgentInquiry.setSalutation(null);

		return partnerAgentInquiry;
	}

	public List<PartnerContact> getPartnerContacts() {
		return this.partnerContacts;
	}

	public void setPartnerContacts(List<PartnerContact> partnerContacts) {
		this.partnerContacts = partnerContacts;
	}

	public PartnerContact addPartnerContact(PartnerContact partnerContact) {
		getPartnerContacts().add(partnerContact);
		partnerContact.setSalutation(this);

		return partnerContact;
	}

	public PartnerContact removePartnerContact(PartnerContact partnerContact) {
		getPartnerContacts().remove(partnerContact);
		partnerContact.setSalutation(null);

		return partnerContact;
	}

	public List<PartnerUser> getPartnerUsers() {
		return this.partnerUsers;
	}

	public void setPartnerUsers(List<PartnerUser> partnerUsers) {
		this.partnerUsers = partnerUsers;
	}

	public PartnerUser addPartnerUser(PartnerUser partnerUser) {
		getPartnerUsers().add(partnerUser);
		partnerUser.setSalutation(this);

		return partnerUser;
	}

	public PartnerUser removePartnerUser(PartnerUser partnerUser) {
		getPartnerUsers().remove(partnerUser);
		partnerUser.setSalutation(null);

		return partnerUser;
	}

}