package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the history database table.
 * 
 */
@Entity
@Table(name="history")
@NamedQuery(name="History.findAll", query="SELECT h FROM History h")
public class History implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int historyId;

	@Column(length=20)
	private String ipAddress;

	@Column(nullable=false)
	private Timestamp loggedOn;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="loginId", nullable=false)
	private Login login;

	public History() {
	}

	public int getHistoryId() {
		return this.historyId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
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