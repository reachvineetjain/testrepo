package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HostFamilyChange database table.
 * 
 */
@Entity
@Table(name="HostFamilyChange")
@NamedQuery(name="HostFamilyChange.findAll", query="SELECT h FROM HostFamilyChange h")
public class HostFamilyChange implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int hostFamilyChangeId;

	private int createdBy;

	private byte isApproved;

	@Column(length=50)
	private String newCurrentAddress;

	@Column(length=50)
	private String newCurrentCity;

	@Column(length=50)
	private String newCurrentPostalCode;

	private int newCurrentStateID;

	@Column(length=50)
	private String oldCurrentAddress;

	@Column(length=50)
	private String oldCurrentCity;

	@Column(length=25)
	private String oldCurrentPostalCode;

	private int oldCurrentStateID;

	//bi-directional many-to-one association to HostFamily
	@ManyToOne
	@JoinColumn(name="hostFamilyGoId")
	private HostFamily hostFamily;

	public HostFamilyChange() {
	}

	public int getHostFamilyChangeId() {
		return this.hostFamilyChangeId;
	}

	public void setHostFamilyChangeId(int hostFamilyChangeId) {
		this.hostFamilyChangeId = hostFamilyChangeId;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public byte getIsApproved() {
		return this.isApproved;
	}

	public void setIsApproved(byte isApproved) {
		this.isApproved = isApproved;
	}

	public String getNewCurrentAddress() {
		return this.newCurrentAddress;
	}

	public void setNewCurrentAddress(String newCurrentAddress) {
		this.newCurrentAddress = newCurrentAddress;
	}

	public String getNewCurrentCity() {
		return this.newCurrentCity;
	}

	public void setNewCurrentCity(String newCurrentCity) {
		this.newCurrentCity = newCurrentCity;
	}

	public String getNewCurrentPostalCode() {
		return this.newCurrentPostalCode;
	}

	public void setNewCurrentPostalCode(String newCurrentPostalCode) {
		this.newCurrentPostalCode = newCurrentPostalCode;
	}

	public int getNewCurrentStateID() {
		return this.newCurrentStateID;
	}

	public void setNewCurrentStateID(int newCurrentStateID) {
		this.newCurrentStateID = newCurrentStateID;
	}

	public String getOldCurrentAddress() {
		return this.oldCurrentAddress;
	}

	public void setOldCurrentAddress(String oldCurrentAddress) {
		this.oldCurrentAddress = oldCurrentAddress;
	}

	public String getOldCurrentCity() {
		return this.oldCurrentCity;
	}

	public void setOldCurrentCity(String oldCurrentCity) {
		this.oldCurrentCity = oldCurrentCity;
	}

	public String getOldCurrentPostalCode() {
		return this.oldCurrentPostalCode;
	}

	public void setOldCurrentPostalCode(String oldCurrentPostalCode) {
		this.oldCurrentPostalCode = oldCurrentPostalCode;
	}

	public int getOldCurrentStateID() {
		return this.oldCurrentStateID;
	}

	public void setOldCurrentStateID(int oldCurrentStateID) {
		this.oldCurrentStateID = oldCurrentStateID;
	}

	public HostFamily getHostFamily() {
		return this.hostFamily;
	}

	public void setHostFamily(HostFamily hostFamily) {
		this.hostFamily = hostFamily;
	}

}