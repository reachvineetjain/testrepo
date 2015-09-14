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

	//bi-directional many-to-one association to Login
	@OneToOne(mappedBy="goIdSequence")
   private Login login;

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

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Login addLogin(Login login) {
		login.setGoIdSequence(this);
		return login;
	}

	public Login removeLogin(Login login) {
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