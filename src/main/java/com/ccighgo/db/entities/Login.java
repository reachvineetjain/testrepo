package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the login database table.
 * 
 */
@Entity
@Table(name="login")
@NamedQuery(name="Login.findAll", query="SELECT l FROM Login l")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int loginId;

	@Column(nullable=false, length=50)
	private String loginName;

	@Column(nullable=false, length=10)
	private String password;

	//bi-directional many-to-one association to History
	@OneToMany(mappedBy="login")
	private List<History> histories;

	//bi-directional many-to-one association to UserType
	@ManyToOne
	@JoinColumn(name="userTypeId", nullable=false)
	private UserType userType;

	//bi-directional many-to-one association to PasswordHistory
	@OneToMany(mappedBy="login")
	private List<PasswordHistory> passwordHistories;

	//bi-directional many-to-one association to Ccistaffuser
	@OneToMany(mappedBy="login")
	private List<Ccistaffuser> ccistaffusers;

	public Login() {
	}

	public int getLoginId() {
		return this.loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<History> getHistories() {
		return this.histories;
	}

	public void setHistories(List<History> histories) {
		this.histories = histories;
	}

	public History addHistory(History history) {
		getHistories().add(history);
		history.setLogin(this);

		return history;
	}

	public History removeHistory(History history) {
		getHistories().remove(history);
		history.setLogin(null);

		return history;
	}

	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public List<PasswordHistory> getPasswordHistories() {
		return this.passwordHistories;
	}

	public void setPasswordHistories(List<PasswordHistory> passwordHistories) {
		this.passwordHistories = passwordHistories;
	}

	public PasswordHistory addPasswordHistory(PasswordHistory passwordHistory) {
		getPasswordHistories().add(passwordHistory);
		passwordHistory.setLogin(this);

		return passwordHistory;
	}

	public PasswordHistory removePasswordHistory(PasswordHistory passwordHistory) {
		getPasswordHistories().remove(passwordHistory);
		passwordHistory.setLogin(null);

		return passwordHistory;
	}

	public List<Ccistaffuser> getCcistaffusers() {
		return this.ccistaffusers;
	}

	public void setCcistaffusers(List<Ccistaffuser> ccistaffusers) {
		this.ccistaffusers = ccistaffusers;
	}

	public Ccistaffuser addCcistaffuser(Ccistaffuser ccistaffuser) {
		getCcistaffusers().add(ccistaffuser);
		ccistaffuser.setLogin(this);

		return ccistaffuser;
	}

	public Ccistaffuser removeCcistaffuser(Ccistaffuser ccistaffuser) {
		getCcistaffusers().remove(ccistaffuser);
		ccistaffuser.setLogin(null);

		return ccistaffuser;
	}

}