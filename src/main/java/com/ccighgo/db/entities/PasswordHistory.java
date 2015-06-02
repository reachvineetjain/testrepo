package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the passwordhistory database table.
 * 
 */
@Entity
@Table(name="passwordhistory")
@NamedQuery(name="PasswordHistory.findAll", query="SELECT p FROM PasswordHistory p")
public class PasswordHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int passwordHistoryId;

	@Column(length=40)
	private String password1;

	@Column(length=40)
	private String password2;

	@Column(length=40)
	private String password3;

	@Column(length=40)
	private String password4;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="loginId")
	private Login login;

	public PasswordHistory() {
	}

	public int getPasswordHistoryId() {
		return this.passwordHistoryId;
	}

	public void setPasswordHistoryId(int passwordHistoryId) {
		this.passwordHistoryId = passwordHistoryId;
	}

	public String getPassword1() {
		return this.password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return this.password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getPassword3() {
		return this.password3;
	}

	public void setPassword3(String password3) {
		this.password3 = password3;
	}

	public String getPassword4() {
		return this.password4;
	}

	public void setPassword4(String password4) {
		this.password4 = password4;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}