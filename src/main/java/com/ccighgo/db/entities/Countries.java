package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the countries database table.
 * 
 */
@Entity
@Table(name="countries")
@NamedQuery(name="Countries.findAll", query="SELECT c FROM Countries c")
public class Countries implements Serializable {
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
	private byte isReqFinalSOAonDS;

	//bi-directional many-to-one association to CciStaffUser
	@OneToMany(mappedBy="country")
	private List<CciStaffUser> ccistaffusers;

	public Countries() {
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
		return this.isReqFinalSOAonDS;
	}

	public void setReqFinalSOAonDS(byte isReqFinalSOAonDS) {
		this.isReqFinalSOAonDS = isReqFinalSOAonDS;
	}

	public List<CciStaffUser> getCcistaffusers() {
		return this.ccistaffusers;
	}

	public void setCcistaffusers(List<CciStaffUser> ccistaffusers) {
		this.ccistaffusers = ccistaffusers;
	}

	public CciStaffUser addCcistaffuser(CciStaffUser ccistaffuser) {
		getCcistaffusers().add(ccistaffuser);
		ccistaffuser.setCountry(this);

		return ccistaffuser;
	}

	public CciStaffUser removeCcistaffuser(CciStaffUser ccistaffuser) {
		getCcistaffusers().remove(ccistaffuser);
		ccistaffuser.setCountry(null);

		return ccistaffuser;
	}

}