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
@Table(name="Login")
@NamedQuery(name="Login.findAll", query="SELECT l FROM Login l")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer loginId;

	@Column(nullable=false)
	private Integer createdBy;

	private Timestamp createdOn;

	@Column(nullable=false, length=50)
	private String email;

	@Column(nullable=false, length=200)
	private String keyValue;

	@Column(nullable=false, length=50)
	private String loginName;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(nullable=false, length=100)
	private String password;

	//bi-directional many-to-one association to GoIdSequence
   @ManyToOne
   @JoinColumn(name="goId", nullable=false)
   private GoIdSequence goIdSequence;

	//bi-directional many-to-one association to LoginHistory
	@OneToMany(mappedBy="login")
	private List<LoginHistory> loginHistories;

	//bi-directional many-to-one association to LoginUserType
	@OneToMany(mappedBy="login")
	private List<LoginUserType> loginUserTypes;

	//bi-directional many-to-one association to PartnerUser
	@OneToMany(mappedBy="login")
	private List<PartnerUser> partnerUsers;

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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKeyValue() {
		return this.keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
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

	public List<PartnerUser> getPartnerUsers() {
		return this.partnerUsers;
	}

	public void setPartnerUsers(List<PartnerUser> partnerUsers) {
		this.partnerUsers = partnerUsers;
	}

	public PartnerUser addPartnerUser(PartnerUser partnerUser) {
		getPartnerUsers().add(partnerUser);
		partnerUser.setLogin(this);

		return partnerUser;
	}

	public PartnerUser removePartnerUser(PartnerUser partnerUser) {
		getPartnerUsers().remove(partnerUser);
		partnerUser.setLogin(null);

		return partnerUser;
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