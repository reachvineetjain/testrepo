package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the GoIdSequence database table.
 * 
 */
@Entity
@Table(name="GoIdSequence")
@NamedQuery(name="GoIdSequence.findAll", query="SELECT g FROM GoIdSequence g")
public class GoIdSequence implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer goId;

	//bi-directional one-to-one association to CCIStaffUser
	@OneToOne(mappedBy="goIdSequence")
	private CCIStaffUser ccistaffUser;

	//bi-directional one-to-one association to Employer
	@OneToOne(mappedBy="goIdSequence")
	private Employer employer;

	//bi-directional one-to-one association to HostFamily
	@OneToOne(mappedBy="goIdSequence")
	private HostFamily hostFamily;

	//bi-directional many-to-one association to Login
	@OneToMany(mappedBy="goIdSequence")
	private List<Login> logins;

	//bi-directional one-to-one association to Partner
	@OneToOne(mappedBy="goIdSequence")
	private Partner partner;

	public GoIdSequence() {
	}

	public Integer getGoId() {
		return this.goId;
	}

	public void setGoId(Integer goId) {
		this.goId = goId;
	}

	public CCIStaffUser getCcistaffUser() {
		return this.ccistaffUser;
	}

	public void setCcistaffUser(CCIStaffUser ccistaffUser) {
		this.ccistaffUser = ccistaffUser;
	}

	public Employer getEmployer() {
		return this.employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public HostFamily getHostFamily() {
		return this.hostFamily;
	}

	public void setHostFamily(HostFamily hostFamily) {
		this.hostFamily = hostFamily;
	}

	public List<Login> getLogins() {
		return this.logins;
	}

	public void setLogins(List<Login> logins) {
		this.logins = logins;
	}

	public Login addLogin(Login login) {
		getLogins().add(login);
		login.setGoIdSequence(this);

		return login;
	}

	public Login removeLogin(Login login) {
		getLogins().remove(login);
		login.setGoIdSequence(null);

		return login;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

}