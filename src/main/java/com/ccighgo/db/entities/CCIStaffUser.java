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
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer cciStaffUserId;

	@Column(nullable=false)
	private byte active;

	@Column(nullable=false, length=64)
	private String cciAdminGuid;

	@Column(length=50)
	private String city;

	@Column(nullable=false)
	private Integer createdBy;

	private Timestamp createdOn;

	@Column(nullable=false, length=50)
	private String email;

	@Column(length=15)
	private String emergencyPhone;

	@Column(nullable=false, length=30)
	private String firstName;

	@Column(length=100)
	private String homeAddressLineOne;

	@Column(length=100)
	private String homeAddressLineTwo;

	@Column(nullable=false, length=30)
	private String lastName;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(length=300)
	private String photo;

	@Column(length=15)
	private String primaryPhone;

	@Column(length=20)
	private String sevisId;

	private Integer supervisorId;

	@Column(length=50)
	private String zip;

	//bi-directional many-to-one association to CCIStaffUserNote
	@OneToMany(mappedBy = "ccistaffUser", fetch = FetchType.EAGER)
   @Fetch(value = FetchMode.SUBSELECT)
	private List<CCIStaffUserNote> ccistaffUserNotes;

	//bi-directional many-to-one association to CCIStaffUserProgram
	@OneToMany(mappedBy = "ccistaffUser", fetch = FetchType.EAGER)
   @Fetch(value = FetchMode.SUBSELECT)
	private List<CCIStaffUserProgram> ccistaffUserPrograms;

	//bi-directional many-to-one association to GoIdSequence
	@OneToOne
	@JoinColumn(name="cciStaffUserId", nullable=false, insertable=false, updatable=false)
	private GoIdSequence goIdSequence;

	//bi-directional many-to-one association to LookupCountry
	@ManyToOne
	@JoinColumn(name="countryId")
	private LookupCountry lookupCountry;

	//bi-directional many-to-one association to LookupGender
	@ManyToOne
	@JoinColumn(name="genderId")
	private LookupGender lookupGender;

	//bi-directional many-to-one association to LookupUSState
	@ManyToOne
	@JoinColumn(name="usStatesId")
	private LookupUSState lookupUsstate;

	//bi-directional many-to-one association to CCIStaffUsersCCIStaffRole
	@OneToMany(mappedBy = "ccistaffUser", fetch = FetchType.EAGER)
   @Fetch(value = FetchMode.SUBSELECT)
	private List<CCIStaffUsersCCIStaffRole> ccistaffUsersCcistaffRoles;

	//bi-directional many-to-one association to CCIStaffUsersResourcePermission
	@OneToMany(mappedBy = "ccistaffUser", fetch = FetchType.EAGER)
   @Fetch(value = FetchMode.SUBSELECT)
	private List<CCIStaffUsersResourcePermission> ccistaffUsersResourcePermissions;

	  //bi-directional many-to-one association to PartnerAgentReview
   @OneToMany(mappedBy="ccistaffUser")
   private List<PartnerAgentReview> partnerAgentReviews;
	public CCIStaffUser() {
	}
	
	public CCIStaffUser(Integer cciStaffUserId) {
	   this.cciStaffUserId = cciStaffUserId;
   }

	public Integer getCciStaffUserId() {
		return this.cciStaffUserId;
	}

	public void setCciStaffUserId(Integer cciStaffUserId) {
		this.cciStaffUserId = cciStaffUserId;
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

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPrimaryPhone() {
		return this.primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getSevisId() {
		return this.sevisId;
	}

	public void setSevisId(String sevisId) {
		this.sevisId = sevisId;
	}

	public Integer getSupervisorId() {
		return this.supervisorId;
	}

	public void setSupervisorId(Integer supervisorId) {
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

	public GoIdSequence getGoIdSequence() {
		return this.goIdSequence;
	}

	public void setGoIdSequence(GoIdSequence goIdSequence) {
		this.goIdSequence = goIdSequence;
	}

	public LookupCountry getLookupCountry() {
		return this.lookupCountry;
	}

	public void setLookupCountry(LookupCountry lookupCountry) {
		this.lookupCountry = lookupCountry;
	}

	public LookupGender getLookupGender() {
		return this.lookupGender;
	}

	public void setLookupGender(LookupGender lookupGender) {
		this.lookupGender = lookupGender;
	}

	public LookupUSState getLookupUsstate() {
		return this.lookupUsstate;
	}

	public void setLookupUsstate(LookupUSState lookupUsstate) {
		this.lookupUsstate = lookupUsstate;
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
	
	public List<PartnerAgentReview> getPartnerAgentReviews() {
      return this.partnerAgentReviews;
   }

   public void setPartnerAgentReviews(List<PartnerAgentReview> partnerAgentReviews) {
      this.partnerAgentReviews = partnerAgentReviews;
   }

}