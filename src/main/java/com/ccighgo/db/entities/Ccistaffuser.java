package com.ccighgo.db.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the ccistaffusers database table.
 * 
 */
@Entity
@Table(name="ccistaffusers")
@NamedQuery(name="Ccistaffuser.findAll", query="SELECT c FROM Ccistaffuser c")
public class Ccistaffuser implements Serializable {
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

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="countryID", nullable=false)
	private Country country;

	//bi-directional many-to-one association to Usstate
	@ManyToOne
	@JoinColumn(name="stateID", nullable=false)
	private Usstate usstate;

	//bi-directional many-to-one association to CcistaffusersCcistaffrole
	@OneToMany(mappedBy="ccistaffuser", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<CcistaffusersCcistaffrole> ccistaffusersCcistaffroles;

	//bi-directional many-to-one association to CcistaffusersDepartment
	@OneToMany(mappedBy="ccistaffuser", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<CcistaffusersDepartment> ccistaffusersDepartments;

	//bi-directional many-to-one association to CcistaffusersResourcepermission
	@OneToMany(mappedBy="ccistaffuser", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<CcistaffusersResourcepermission> ccistaffusersResourcepermissions;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="loginID", nullable=false)
	private Login login;

	public Ccistaffuser() {
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

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Usstate getUsstate() {
		return this.usstate;
	}

	public void setUsstate(Usstate usstate) {
		this.usstate = usstate;
	}

	public List<CcistaffusersCcistaffrole> getCcistaffusersCcistaffroles() {
		return this.ccistaffusersCcistaffroles;
	}

	public void setCcistaffusersCcistaffroles(List<CcistaffusersCcistaffrole> ccistaffusersCcistaffroles) {
		this.ccistaffusersCcistaffroles = ccistaffusersCcistaffroles;
	}

	public CcistaffusersCcistaffrole addCcistaffusersCcistaffrole(CcistaffusersCcistaffrole ccistaffusersCcistaffrole) {
		getCcistaffusersCcistaffroles().add(ccistaffusersCcistaffrole);
		ccistaffusersCcistaffrole.setCcistaffuser(this);

		return ccistaffusersCcistaffrole;
	}

	public CcistaffusersCcistaffrole removeCcistaffusersCcistaffrole(CcistaffusersCcistaffrole ccistaffusersCcistaffrole) {
		getCcistaffusersCcistaffroles().remove(ccistaffusersCcistaffrole);
		ccistaffusersCcistaffrole.setCcistaffuser(null);

		return ccistaffusersCcistaffrole;
	}

	public List<CcistaffusersDepartment> getCcistaffusersDepartments() {
		return this.ccistaffusersDepartments;
	}

	public void setCcistaffusersDepartments(List<CcistaffusersDepartment> ccistaffusersDepartments) {
		this.ccistaffusersDepartments = ccistaffusersDepartments;
	}

	public CcistaffusersDepartment addCcistaffusersDepartment(CcistaffusersDepartment ccistaffusersDepartment) {
		getCcistaffusersDepartments().add(ccistaffusersDepartment);
		ccistaffusersDepartment.setCcistaffuser(this);

		return ccistaffusersDepartment;
	}

	public CcistaffusersDepartment removeCcistaffusersDepartment(CcistaffusersDepartment ccistaffusersDepartment) {
		getCcistaffusersDepartments().remove(ccistaffusersDepartment);
		ccistaffusersDepartment.setCcistaffuser(null);

		return ccistaffusersDepartment;
	}

	public List<CcistaffusersResourcepermission> getCcistaffusersResourcepermissions() {
		return this.ccistaffusersResourcepermissions;
	}

	public void setCcistaffusersResourcepermissions(List<CcistaffusersResourcepermission> ccistaffusersResourcepermissions) {
		this.ccistaffusersResourcepermissions = ccistaffusersResourcepermissions;
	}

	public CcistaffusersResourcepermission addCcistaffusersResourcepermission(CcistaffusersResourcepermission ccistaffusersResourcepermission) {
		getCcistaffusersResourcepermissions().add(ccistaffusersResourcepermission);
		ccistaffusersResourcepermission.setCcistaffuser(this);

		return ccistaffusersResourcepermission;
	}

	public CcistaffusersResourcepermission removeCcistaffusersResourcepermission(CcistaffusersResourcepermission ccistaffusersResourcepermission) {
		getCcistaffusersResourcepermissions().remove(ccistaffusersResourcepermission);
		ccistaffusersResourcepermission.setCcistaffuser(null);

		return ccistaffusersResourcepermission;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}