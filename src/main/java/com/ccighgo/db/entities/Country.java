package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Countries database table.
 * 
 */
@Entity
@Table(name="Countries")
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int countryID;

	@Column(nullable=false)
	private byte active;

	@Column(nullable=false, length=5)
	private String countryCode;

	@Column(length=300)
	private String countryFlag;

	@Column(nullable=false, length=50)
	private String countryName;

	@Column(nullable=false)
	private byte reqFinalSOAonDS;

	//bi-directional many-to-one association to CCIStaffUser
	@OneToMany(mappedBy="country")
	private List<CCIStaffUser> ccistaffUsers;

	public Country() {
	}

	public int getCountryID() {
		return this.countryID;
	}

	public void setCountryID(int countryID) {
		this.countryID = countryID;
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

	public byte getReqFinalSOAonDS() {
		return this.reqFinalSOAonDS;
	}

	public void setReqFinalSOAonDS(byte reqFinalSOAonDS) {
		this.reqFinalSOAonDS = reqFinalSOAonDS;
	}

	public List<CCIStaffUser> getCcistaffUsers() {
		return this.ccistaffUsers;
	}

	public void setCcistaffUsers(List<CCIStaffUser> ccistaffUsers) {
		this.ccistaffUsers = ccistaffUsers;
	}

	public CCIStaffUser addCcistaffUser(CCIStaffUser ccistaffUser) {
		getCcistaffUsers().add(ccistaffUser);
		ccistaffUser.setCountry(this);

		return ccistaffUser;
	}

	public CCIStaffUser removeCcistaffUser(CCIStaffUser ccistaffUser) {
		getCcistaffUsers().remove(ccistaffUser);
		ccistaffUser.setCountry(null);

		return ccistaffUser;
	}

}