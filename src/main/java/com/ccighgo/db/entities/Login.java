package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the Login database table.
 * 
 */
@Entity
@NamedQuery(name="Login.findAll", query="SELECT l FROM Login l")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer loginId;

	private Integer createdBy;

	private Timestamp createdOn;

	private String loginName;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	private String password;

	//bi-directional many-to-one association to CCIStaffUser
	@OneToMany(mappedBy="login")
	private List<CCIStaffUser> ccistaffUsers;

	//bi-directional many-to-one association to GoIdSequence
	@ManyToOne
	@JoinColumn(name="goId")
	private GoIdSequence goIdSequence;

	//bi-directional many-to-one association to LoginHistory
	@OneToMany(mappedBy="login")
	private List<LoginHistory> loginHistories;

	//bi-directional many-to-one association to LoginUserType
	@OneToMany(mappedBy="login")
	private List<LoginUserType> loginUserTypes;

	//bi-directional many-to-one association to PasswordHistory
	@OneToMany(mappedBy="login")
	private List<PasswordHistory> passwordHistories;

	public Login() {
	}

	public Integer getLoginId() {
		return this.loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<CCIStaffUser> getCcistaffUsers() {
		return this.ccistaffUsers;
	}

	public void setCcistaffUsers(List<CCIStaffUser> ccistaffUsers) {
		this.ccistaffUsers = ccistaffUsers;
	}

	public CCIStaffUser addCcistaffUser(CCIStaffUser ccistaffUser) {
		getCcistaffUsers().add(ccistaffUser);
		ccistaffUser.setLogin(this);

		return ccistaffUser;
	}

	public CCIStaffUser removeCcistaffUser(CCIStaffUser ccistaffUser) {
		getCcistaffUsers().remove(ccistaffUser);
		ccistaffUser.setLogin(null);

		return ccistaffUser;
	}

	public GoIdSequence getGoIdSequence() {
		return this.goIdSequence;
	}

	public void setGoIdSequence(GoIdSequence goIdSequence) {
		this.goIdSequence = goIdSequence;
	}

	public List<LoginHistory> getLoginHistories() {
		return this.loginHistories;
	}

	public void setLoginHistories(List<LoginHistory> loginHistories) {
		this.loginHistories = loginHistories;
	}

	public LoginHistory addLoginHistory(LoginHistory loginHistory) {
		getLoginHistories().add(loginHistory);
		loginHistory.setLogin(this);

		return loginHistory;
	}

	public LoginHistory removeLoginHistory(LoginHistory loginHistory) {
		getLoginHistories().remove(loginHistory);
		loginHistory.setLogin(null);

		return loginHistory;
	}

	public List<LoginUserType> getLoginUserTypes() {
		return this.loginUserTypes;
	}

	public void setLoginUserTypes(List<LoginUserType> loginUserTypes) {
		this.loginUserTypes = loginUserTypes;
	}

	public LoginUserType addLoginUserType(LoginUserType loginUserType) {
		getLoginUserTypes().add(loginUserType);
		loginUserType.setLogin(this);

		return loginUserType;
	}

	public LoginUserType removeLoginUserType(LoginUserType loginUserType) {
		getLoginUserTypes().remove(loginUserType);
		loginUserType.setLogin(null);

		return loginUserType;
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

}