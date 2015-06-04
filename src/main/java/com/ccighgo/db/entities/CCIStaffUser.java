package com.ccighgo.db.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the CCIStaffUsers database table.
 * 
 */
@Entity
@Table(name="CCIStaffUsers")
@NamedQuery(name="CCIStaffUser.findAll", query="SELECT c FROM CCIStaffUser c")
public class CCIStaffUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int cciStaffUserID;

	@Column(nullable=false)
	private byte active;

	@Column(nullable=false, length=64)
	private String cciAdminGuid;

	@Column(length=50)
	private String city;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false, length=50)
	private String email;

	@Column(length=50)
	private String emergencyPhone;

	@Column(nullable=false, length=50)
	private String firstName;

	@Column(length=100)
	private String homeAddressLineOne;

	@Column(length=100)
	private String homeAddressLineTwo;

	@Column(nullable=false, length=50)
	private String lastName;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(length=25)
	private String phone;

	@Column(length=300)
	private String photo;

	private int sequenceNo;

	@Column(length=20)
	private String sevisID;

	private byte[] stamp;

	private int supervisorId;

	@Column(length=50)
	private String zip;

	//bi-directional many-to-one association to CCIStaffUserNote
	@OneToMany(mappedBy="ccistaffUser", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
	private List<CCIStaffUserNote> ccistaffUserNotes;

	//bi-directional many-to-one association to CCIStaffUserProgram
	@OneToMany(mappedBy="ccistaffUser", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
	private List<CCIStaffUserProgram> ccistaffUserPrograms;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="countryID")
	private Country country;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="loginID", nullable=false)
	private Login login;

	//bi-directional many-to-one association to USState
	@ManyToOne
	@JoinColumn(name="stateID")
	private USState usstate;

	//bi-directional many-to-one association to CCIStaffUsersCCIStaffRole
	@OneToMany(mappedBy="ccistaffUser", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
	private List<CCIStaffUsersCCIStaffRole> ccistaffUsersCcistaffRoles;

	//bi-directional many-to-one association to CCIStaffUsersResourcePermission
	@OneToMany(mappedBy="ccistaffUser", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
	private List<CCIStaffUsersResourcePermission> ccistaffUsersResourcePermissions;

	public CCIStaffUser() {
	}

	public int getCciStaffUserID() {
		return this.cciStaffUserID;
	}

	public void setCciStaffUserID(int cciStaffUserID) {
		this.cciStaffUserID = cciStaffUserID;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getCciAdminGuid() {
		return this.cciAdminGuid;
	}

	public void setCciAdminGuid(String cciAdminGuid) {
		this.cciAdminGuid = cciAdminGuid;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
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

	public String getEmergencyPhone() {
		return this.emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getHomeAddressLineOne() {
		return this.homeAddressLineOne;
	}

	public void setHomeAddressLineOne(String homeAddressLineOne) {
		this.homeAddressLineOne = homeAddressLineOne;
	}

	public String getHomeAddressLineTwo() {
		return this.homeAddressLineTwo;
	}

	public void setHomeAddressLineTwo(String homeAddressLineTwo) {
		this.homeAddressLineTwo = homeAddressLineTwo;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getSequenceNo() {
		return this.sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getSevisID() {
		return this.sevisID;
	}

	public void setSevisID(String sevisID) {
		this.sevisID = sevisID;
	}

	public byte[] getStamp() {
		return this.stamp;
	}

	public void setStamp(byte[] stamp) {
		this.stamp = stamp;
	}

	public int getSupervisorId() {
		return this.supervisorId;
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public List<CCIStaffUserNote> getCcistaffUserNotes() {
		return this.ccistaffUserNotes;
	}

	public void setCcistaffUserNotes(List<CCIStaffUserNote> ccistaffUserNotes) {
		this.ccistaffUserNotes = ccistaffUserNotes;
	}

	public CCIStaffUserNote addCcistaffUserNote(CCIStaffUserNote ccistaffUserNote) {
		getCcistaffUserNotes().add(ccistaffUserNote);
		ccistaffUserNote.setCcistaffUser(this);

		return ccistaffUserNote;
	}

	public CCIStaffUserNote removeCcistaffUserNote(CCIStaffUserNote ccistaffUserNote) {
		getCcistaffUserNotes().remove(ccistaffUserNote);
		ccistaffUserNote.setCcistaffUser(null);

		return ccistaffUserNote;
	}

	public List<CCIStaffUserProgram> getCcistaffUserPrograms() {
		return this.ccistaffUserPrograms;
	}

	public void setCcistaffUserPrograms(List<CCIStaffUserProgram> ccistaffUserPrograms) {
		this.ccistaffUserPrograms = ccistaffUserPrograms;
	}

	public CCIStaffUserProgram addCcistaffUserProgram(CCIStaffUserProgram ccistaffUserProgram) {
		getCcistaffUserPrograms().add(ccistaffUserProgram);
		ccistaffUserProgram.setCcistaffUser(this);

		return ccistaffUserProgram;
	}

	public CCIStaffUserProgram removeCcistaffUserProgram(CCIStaffUserProgram ccistaffUserProgram) {
		getCcistaffUserPrograms().remove(ccistaffUserProgram);
		ccistaffUserProgram.setCcistaffUser(null);

		return ccistaffUserProgram;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public USState getUsstate() {
		return this.usstate;
	}

	public void setUsstate(USState usstate) {
		this.usstate = usstate;
	}

	public List<CCIStaffUsersCCIStaffRole> getCcistaffUsersCcistaffRoles() {
		return this.ccistaffUsersCcistaffRoles;
	}

	public void setCcistaffUsersCcistaffRoles(List<CCIStaffUsersCCIStaffRole> ccistaffUsersCcistaffRoles) {
		this.ccistaffUsersCcistaffRoles = ccistaffUsersCcistaffRoles;
	}

	public CCIStaffUsersCCIStaffRole addCcistaffUsersCcistaffRole(CCIStaffUsersCCIStaffRole ccistaffUsersCcistaffRole) {
		getCcistaffUsersCcistaffRoles().add(ccistaffUsersCcistaffRole);
		ccistaffUsersCcistaffRole.setCcistaffUser(this);

		return ccistaffUsersCcistaffRole;
	}

	public CCIStaffUsersCCIStaffRole removeCcistaffUsersCcistaffRole(CCIStaffUsersCCIStaffRole ccistaffUsersCcistaffRole) {
		getCcistaffUsersCcistaffRoles().remove(ccistaffUsersCcistaffRole);
		ccistaffUsersCcistaffRole.setCcistaffUser(null);

		return ccistaffUsersCcistaffRole;
	}

	public List<CCIStaffUsersResourcePermission> getCcistaffUsersResourcePermissions() {
		return this.ccistaffUsersResourcePermissions;
	}

	public void setCcistaffUsersResourcePermissions(List<CCIStaffUsersResourcePermission> ccistaffUsersResourcePermissions) {
		this.ccistaffUsersResourcePermissions = ccistaffUsersResourcePermissions;
	}

	public CCIStaffUsersResourcePermission addCcistaffUsersResourcePermission(CCIStaffUsersResourcePermission ccistaffUsersResourcePermission) {
		getCcistaffUsersResourcePermissions().add(ccistaffUsersResourcePermission);
		ccistaffUsersResourcePermission.setCcistaffUser(this);

		return ccistaffUsersResourcePermission;
	}

	public CCIStaffUsersResourcePermission removeCcistaffUsersResourcePermission(CCIStaffUsersResourcePermission ccistaffUsersResourcePermission) {
		getCcistaffUsersResourcePermissions().remove(ccistaffUsersResourcePermission);
		ccistaffUsersResourcePermission.setCcistaffUser(null);

		return ccistaffUsersResourcePermission;
	}

}