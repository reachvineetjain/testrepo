package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the ccistaffusers database table.
 * 
 */
@Entity
@Table(name="ccistaffusers")
@NamedQuery(name="CciStaffUser.findAll", query="SELECT c FROM CciStaffUser c")
public class CciStaffUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int cciStaffUserID;

	@Column(nullable=false)
	private byte active;

	@Column(nullable=false, length=64)
	private String cciAdminGuid;

	@Column(nullable=false, length=50)
	private String city;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false, length=50)
	private String email;

	@Column(nullable=false, length=50)
	private String emergencyPhone;

	@Column(nullable=false, length=50)
	private String firstName;

	@Column(nullable=false, length=100)
	private String homeAddressLineOne;

	@Column(nullable=false, length=100)
	private String homeAddressLineTwo;

	@Column(nullable=false, length=50)
	private String lastName;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(nullable=false, length=25)
	private String phone;

	@Column(length=300)
	private String photo;

	@Column(nullable=false)
	private int sequenceNo;

	@Column(nullable=false, length=20)
	private String sevisID;

	@Column(nullable=false)
	private byte[] stamp;

	private int supervisorId;

	@Column(nullable=false, length=50)
	private String zip;

	//bi-directional many-to-one association to CciStaffUserNote
	@OneToMany(mappedBy="ccistaffuser")
	private List<CciStaffUserNote> ccistaffusernotes;

	//bi-directional many-to-one association to CciStaffUserProgram
	@OneToMany(mappedBy="ccistaffuser")
	private List<CciStaffUserProgram> ccistaffuserprograms;

	//bi-directional many-to-one association to Countries
	@ManyToOne
	@JoinColumn(name="countryID", nullable=false)
	private Countries country;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="loginID", nullable=false)
	private Login login;

	//bi-directional many-to-one association to USState
	@ManyToOne
	@JoinColumn(name="stateID", nullable=false)
	private USState usstate;

	//bi-directional many-to-one association to CciStaffUsersCciStaffRole
	@OneToMany(mappedBy="ccistaffuser")
	private List<CciStaffUsersCciStaffRole> ccistaffusersccistaffroles;

	//bi-directional many-to-one association to CciStaffUsersResourcePermission
	@OneToMany(mappedBy="ccistaffuser")
	private List<CciStaffUsersResourcePermission> ccistaffusersresourcepermissions;

	public CciStaffUser() {
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

	public List<CciStaffUserNote> getCcistaffusernotes() {
		return this.ccistaffusernotes;
	}

	public void setCcistaffusernotes(List<CciStaffUserNote> ccistaffusernotes) {
		this.ccistaffusernotes = ccistaffusernotes;
	}

	public CciStaffUserNote addCcistaffusernote(CciStaffUserNote ccistaffusernote) {
		getCcistaffusernotes().add(ccistaffusernote);
		ccistaffusernote.setCcistaffuser(this);

		return ccistaffusernote;
	}

	public CciStaffUserNote removeCcistaffusernote(CciStaffUserNote ccistaffusernote) {
		getCcistaffusernotes().remove(ccistaffusernote);
		ccistaffusernote.setCcistaffuser(null);

		return ccistaffusernote;
	}

	public List<CciStaffUserProgram> getCcistaffuserprograms() {
		return this.ccistaffuserprograms;
	}

	public void setCcistaffuserprograms(List<CciStaffUserProgram> ccistaffuserprograms) {
		this.ccistaffuserprograms = ccistaffuserprograms;
	}

	public CciStaffUserProgram addCcistaffuserprogram(CciStaffUserProgram ccistaffuserprogram) {
		getCcistaffuserprograms().add(ccistaffuserprogram);
		ccistaffuserprogram.setCcistaffuser(this);

		return ccistaffuserprogram;
	}

	public CciStaffUserProgram removeCcistaffuserprogram(CciStaffUserProgram ccistaffuserprogram) {
		getCcistaffuserprograms().remove(ccistaffuserprogram);
		ccistaffuserprogram.setCcistaffuser(null);

		return ccistaffuserprogram;
	}

	public Countries getCountry() {
		return this.country;
	}

	public void setCountry(Countries country) {
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

	public List<CciStaffUsersCciStaffRole> getCcistaffusersccistaffroles() {
		return this.ccistaffusersccistaffroles;
	}

	public void setCcistaffusersccistaffroles(List<CciStaffUsersCciStaffRole> ccistaffusersccistaffroles) {
		this.ccistaffusersccistaffroles = ccistaffusersccistaffroles;
	}

	public CciStaffUsersCciStaffRole addCcistaffusersccistaffrole(CciStaffUsersCciStaffRole ccistaffusersccistaffrole) {
		getCcistaffusersccistaffroles().add(ccistaffusersccistaffrole);
		ccistaffusersccistaffrole.setCcistaffuser(this);

		return ccistaffusersccistaffrole;
	}

	public CciStaffUsersCciStaffRole removeCcistaffusersccistaffrole(CciStaffUsersCciStaffRole ccistaffusersccistaffrole) {
		getCcistaffusersccistaffroles().remove(ccistaffusersccistaffrole);
		ccistaffusersccistaffrole.setCcistaffuser(null);

		return ccistaffusersccistaffrole;
	}

	public List<CciStaffUsersResourcePermission> getCcistaffusersresourcepermissions() {
		return this.ccistaffusersresourcepermissions;
	}

	public void setCcistaffusersresourcepermissions(List<CciStaffUsersResourcePermission> ccistaffusersresourcepermissions) {
		this.ccistaffusersresourcepermissions = ccistaffusersresourcepermissions;
	}

	public CciStaffUsersResourcePermission addCcistaffusersresourcepermission(CciStaffUsersResourcePermission ccistaffusersresourcepermission) {
		getCcistaffusersresourcepermissions().add(ccistaffusersresourcepermission);
		ccistaffusersresourcepermission.setCcistaffuser(this);

		return ccistaffusersresourcepermission;
	}

	public CciStaffUsersResourcePermission removeCcistaffusersresourcepermission(CciStaffUsersResourcePermission ccistaffusersresourcepermission) {
		getCcistaffusersresourcepermissions().remove(ccistaffusersresourcepermission);
		ccistaffusersresourcepermission.setCcistaffuser(null);

		return ccistaffusersresourcepermission;
	}

}