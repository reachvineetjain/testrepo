package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the GoIdSequence database table.
 * 
 */
@Entity
@NamedQuery(name="GoIdSequence.findAll", query="SELECT g FROM GoIdSequence g")
public class GoIdSequence implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer goId;

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