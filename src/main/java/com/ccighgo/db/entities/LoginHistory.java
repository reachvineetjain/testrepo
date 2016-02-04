package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the LoginHistory database table.
 * 
 */
@Entity
@NamedQuery(name="LoginHistory.findAll", query="SELECT l FROM LoginHistory l")
public class LoginHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer loginHistoryId;

	private String ipAddress;

	private Timestamp loggedOn;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="loginId")
	private Login login;

	public LoginHistory() {
	}

	public Integer getLoginHistoryId() {
		return this.loginHistoryId;
	}

	public void setLoginHistoryId(Integer loginHistoryId) {
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