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
	private int countryId;

	@Column(nullable=false)
	private byte active;

	@Column(nullable=false, length=5)
	private String countryCode;

	@Column(length=300)
	private String countryFlag;

	@Column(nullable=false, length=50)
	private String countryName;

	@Column(nullable=false)
	private byte isReqFinalSOAonDS;

	//bi-directional many-to-one association to CCIStaffUser
	@OneToMany(mappedBy="lookupCountry")
	private List<CCIStaffUser> ccistaffUsers;

	public LookupCountry() {
	}

	public int getCountryId() {
		return this.countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
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

	public byte getIsReqFinalSOAonDS() {
		return this.isReqFinalSOAonDS;
	}

	public void setIsReqFinalSOAonDS(byte isReqFinalSOAonDS) {
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

}