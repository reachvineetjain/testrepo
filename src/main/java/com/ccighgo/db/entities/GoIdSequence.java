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

	//bi-directional many-to-one association to CCIStaffUser
	@OneToMany(mappedBy="goIdSequence")
	private List<CCIStaffUser> ccistaffUsers;

	//bi-directional many-to-one association to Login
	@OneToMany(mappedBy="goIdSequence")
	private List<Login> logins;

	public GoIdSequence() {
	}

	public Integer getGoId() {
		return this.goId;
	}

	public void setGoId(Integer goId) {
		this.goId = goId;
	}

	public List<CCIStaffUser> getCcistaffUsers() {
		return this.ccistaffUsers;
	}

	public void setCcistaffUsers(List<CCIStaffUser> ccistaffUsers) {
		this.ccistaffUsers = ccistaffUsers;
	}

	public CCIStaffUser addCcistaffUser(CCIStaffUser ccistaffUser) {
		getCcistaffUsers().add(ccistaffUser);
		ccistaffUser.setGoIdSequence(this);

		return ccistaffUser;
	}

	public CCIStaffUser removeCcistaffUser(CCIStaffUser ccistaffUser) {
		getCcistaffUsers().remove(ccistaffUser);
		ccistaffUser.setGoIdSequence(null);

		return ccistaffUser;
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

}