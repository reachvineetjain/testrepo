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

	private Byte active;

	private Integer createdBy;

	private Timestamp createdOn;

	private String email;

	private String keyValue;

	private String loginName;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	private String password;

	//bi-directional many-to-one association to GoIdSequence
	@ManyToOne
	@JoinColumn(name="goId",insertable=false,updatable=false)
	private GoIdSequence goIdSequence;

	//bi-directional many-to-one association to LoginHistory
	@OneToMany(mappedBy="login")
	private List<LoginHistory> loginHistories;

	//bi-directional many-to-one association to LoginUserType
	@OneToMany(mappedBy="login")
	private List<LoginUserType> loginUserTypes;

	//bi-directional many-to-one association to PartnerHelpRequest
	@OneToMany(mappedBy="login")
	private List<PartnerHelpRequest> partnerHelpRequests;

	//bi-directional many-to-one association to PartnerSeason
	@OneToMany(mappedBy="login")
	private List<PartnerSeason> partnerSeasons;

	//bi-directional many-to-one association to PartnerSeasonAllocation
	@OneToMany(mappedBy="login")
	private List<PartnerSeasonAllocation> partnerSeasonAllocations;

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

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
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

	public List<PartnerHelpRequest> getPartnerHelpRequests() {
		return this.partnerHelpRequests;
	}

	public void setPartnerHelpRequests(List<PartnerHelpRequest> partnerHelpRequests) {
		this.partnerHelpRequests = partnerHelpRequests;
	}

	public PartnerHelpRequest addPartnerHelpRequest(PartnerHelpRequest partnerHelpRequest) {
		getPartnerHelpRequests().add(partnerHelpRequest);
		partnerHelpRequest.setLogin(this);

		return partnerHelpRequest;
	}

	public PartnerHelpRequest removePartnerHelpRequest(PartnerHelpRequest partnerHelpRequest) {
		getPartnerHelpRequests().remove(partnerHelpRequest);
		partnerHelpRequest.setLogin(null);

		return partnerHelpRequest;
	}

	public List<PartnerSeason> getPartnerSeasons() {
		return this.partnerSeasons;
	}

	public void setPartnerSeasons(List<PartnerSeason> partnerSeasons) {
		this.partnerSeasons = partnerSeasons;
	}

	public PartnerSeason addPartnerSeason(PartnerSeason partnerSeason) {
		getPartnerSeasons().add(partnerSeason);
		partnerSeason.setLogin(this);

		return partnerSeason;
	}

	public PartnerSeason removePartnerSeason(PartnerSeason partnerSeason) {
		getPartnerSeasons().remove(partnerSeason);
		partnerSeason.setLogin(null);

		return partnerSeason;
	}

	public List<PartnerSeasonAllocation> getPartnerSeasonAllocations() {
		return this.partnerSeasonAllocations;
	}

	public void setPartnerSeasonAllocations(List<PartnerSeasonAllocation> partnerSeasonAllocations) {
		this.partnerSeasonAllocations = partnerSeasonAllocations;
	}

	public PartnerSeasonAllocation addPartnerSeasonAllocation(PartnerSeasonAllocation partnerSeasonAllocation) {
		getPartnerSeasonAllocations().add(partnerSeasonAllocation);
		partnerSeasonAllocation.setLogin(this);

		return partnerSeasonAllocation;
	}

	public PartnerSeasonAllocation removePartnerSeasonAllocation(PartnerSeasonAllocation partnerSeasonAllocation) {
		getPartnerSeasonAllocations().remove(partnerSeasonAllocation);
		partnerSeasonAllocation.setLogin(null);

		return partnerSeasonAllocation;
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