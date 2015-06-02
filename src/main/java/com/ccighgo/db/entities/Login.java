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

	//bi-directional many-to-one association to CciStaffUser
	@OneToMany(mappedBy="login")
	private List<CciStaffUser> ccistaffusers;

	//bi-directional many-to-one association to History
	@OneToMany(mappedBy="login")
	private List<History> histories;

	//bi-directional many-to-one association to UserType
	@ManyToOne
	@JoinColumn(name="userTypeId", nullable=false)
	private UserType usertype;

	//bi-directional many-to-one association to PasswordHistory
	@OneToMany(mappedBy="login")
	private List<PasswordHistory> passwordhistories;

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

	public List<CciStaffUser> getCcistaffusers() {
		return this.ccistaffusers;
	}

	public void setCcistaffusers(List<CciStaffUser> ccistaffusers) {
		this.ccistaffusers = ccistaffusers;
	}

	public CciStaffUser addCcistaffuser(CciStaffUser ccistaffuser) {
		getCcistaffusers().add(ccistaffuser);
		ccistaffuser.setLogin(this);

		return ccistaffuser;
	}

	public CciStaffUser removeCcistaffuser(CciStaffUser ccistaffuser) {
		getCcistaffusers().remove(ccistaffuser);
		ccistaffuser.setLogin(null);

		return ccistaffuser;
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

	public UserType getUsertype() {
		return this.usertype;
	}

	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}

	public List<PasswordHistory> getPasswordhistories() {
		return this.passwordhistories;
	}

	public void setPasswordhistories(List<PasswordHistory> passwordhistories) {
		this.passwordhistories = passwordhistories;
	}

	public PasswordHistory addPasswordhistory(PasswordHistory passwordhistory) {
		getPasswordhistories().add(passwordhistory);
		passwordhistory.setLogin(this);

		return passwordhistory;
	}

	public PasswordHistory removePasswordhistory(PasswordHistory passwordhistory) {
		getPasswordhistories().remove(passwordhistory);
		passwordhistory.setLogin(null);

		return passwordhistory;
	}

}