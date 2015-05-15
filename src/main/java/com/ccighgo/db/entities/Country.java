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
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=5)
	private String countryCode;

	@Column(nullable=false, length=50)
	private String countryName;

	//bi-directional many-to-one association to Ccistaffuser
	@OneToMany(mappedBy="country")
	private List<Ccistaffuser> ccistaffusers;

	public Country() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public List<Ccistaffuser> getCcistaffusers() {
		return this.ccistaffusers;
	}

	public void setCcistaffusers(List<Ccistaffuser> ccistaffusers) {
		this.ccistaffusers = ccistaffusers;
	}

	public Ccistaffuser addCcistaffuser(Ccistaffuser ccistaffuser) {
		getCcistaffusers().add(ccistaffuser);
		ccistaffuser.setCountry(this);

		return ccistaffuser;
	}

	public Ccistaffuser removeCcistaffuser(Ccistaffuser ccistaffuser) {
		getCcistaffusers().remove(ccistaffuser);
		ccistaffuser.setCountry(null);

		return ccistaffuser;
	}

}