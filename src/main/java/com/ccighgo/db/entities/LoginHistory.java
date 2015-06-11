package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the LoginHistory database table.
 * 
 */
@Entity
@Table(name="LoginHistory")
@NamedQuery(name="LoginHistory.findAll", query="SELECT l FROM LoginHistory l")
public class LoginHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int loginHistoryId;

	@Column(length=20)
	private String ipAddress;

	@Column(nullable=false)
	private Timestamp loggedOn;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="loginId", nullable=false)
	private Login login;

	public LoginHistory() {
	}

	public int getLoginHistoryId() {
		return this.loginHistoryId;
	}

	public void setLoginHistoryId(int loginHistoryId) {
		this.loginHistoryId = loginHistoryId;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Timestamp getLoggedOn() {
		return this.loggedOn;
	}

	public void setLoggedOn(Timestamp loggedOn) {
		this.loggedOn = loggedOn;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}