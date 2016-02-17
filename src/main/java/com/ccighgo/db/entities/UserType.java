package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the UserType database table.
 * 
 */
@Entity
@NamedQuery(name="UserType.findAll", query="SELECT u FROM UserType u")
public class UserType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userTypeId;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	private String userTypeCode;

	private String userTypeName;

	//bi-directional many-to-one association to HostFamilyPotentialReference
	@OneToMany(mappedBy="userType")
	private List<HostFamilyPotentialReference> hostFamilyPotentialReferences;

	//bi-directional many-to-one association to LoginUserType
	@OneToMany(mappedBy="userType")
	private List<LoginUserType> loginUserTypes;

	public UserType() {
	}

	public Integer getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
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

	public String getUserTypeCode() {
		return this.userTypeCode;
	}

	public void setUserTypeCode(String userTypeCode) {
		this.userTypeCode = userTypeCode;
	}

	public String getUserTypeName() {
		return this.userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public List<HostFamilyPotentialReference> getHostFamilyPotentialReferences() {
		return this.hostFamilyPotentialReferences;
	}

	public void setHostFamilyPotentialReferences(List<HostFamilyPotentialReference> hostFamilyPotentialReferences) {
		this.hostFamilyPotentialReferences = hostFamilyPotentialReferences;
	}

	public HostFamilyPotentialReference addHostFamilyPotentialReference(HostFamilyPotentialReference hostFamilyPotentialReference) {
		getHostFamilyPotentialReferences().add(hostFamilyPotentialReference);
		hostFamilyPotentialReference.setUserType(this);

		return hostFamilyPotentialReference;
	}

	public HostFamilyPotentialReference removeHostFamilyPotentialReference(HostFamilyPotentialReference hostFamilyPotentialReference) {
		getHostFamilyPotentialReferences().remove(hostFamilyPotentialReference);
		hostFamilyPotentialReference.setUserType(null);

		return hostFamilyPotentialReference;
	}

	public List<LoginUserType> getLoginUserTypes() {
		return this.loginUserTypes;
	}

	public void setLoginUserTypes(List<LoginUserType> loginUserTypes) {
		this.loginUserTypes = loginUserTypes;
	}

	public LoginUserType addLoginUserType(LoginUserType loginUserType) {
		getLoginUserTypes().add(loginUserType);
		loginUserType.setUserType(this);

		return loginUserType;
	}

	public LoginUserType removeLoginUserType(LoginUserType loginUserType) {
		getLoginUserTypes().remove(loginUserType);
		loginUserType.setUserType(null);

		return loginUserType;
	}

}