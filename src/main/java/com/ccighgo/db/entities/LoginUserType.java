package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the LoginUserType database table.
 * 
 */
@Entity
@NamedQuery(name="LoginUserType.findAll", query="SELECT l FROM LoginUserType l")
public class LoginUserType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer loginUserTypeId;

	private Byte active;

	private Integer createdBy;

	private Timestamp createdOn;

	private Byte defaultUserType;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="loginId")
	private Login login;

	//bi-directional many-to-one association to UserType
	@ManyToOne
	@JoinColumn(name="userTypeId")
	private UserType userType;

	public LoginUserType() {
	}

	public Integer getLoginUserTypeId() {
		return this.loginUserTypeId;
	}

	public void setLoginUserTypeId(Integer loginUserTypeId) {
		this.loginUserTypeId = loginUserTypeId;
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

	public Byte getDefaultUserType() {
		return this.defaultUserType;
	}

	public void setDefaultUserType(Byte defaultUserType) {
		this.defaultUserType = defaultUserType;
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

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}