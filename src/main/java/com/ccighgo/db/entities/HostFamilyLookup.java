package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HostFamilyLookup database table.
 * 
 */
@Entity
@Table(name="HostFamilyLookup")
@NamedQuery(name="HostFamilyLookup.findAll", query="SELECT h FROM HostFamilyLookup h")
public class HostFamilyLookup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int hostFamilyLookUpId;

	private byte active;

	@Column(length=50)
	private String displayType;

	@Column(length=50)
	private String displayValue;

	public HostFamilyLookup() {
	}

	public int getHostFamilyLookUpId() {
		return this.hostFamilyLookUpId;
	}

	public void setHostFamilyLookUpId(int hostFamilyLookUpId) {
		this.hostFamilyLookUpId = hostFamilyLookUpId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getDisplayType() {
		return this.displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	public String getDisplayValue() {
		return this.displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

}