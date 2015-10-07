package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PartnerUser database table.
 * 
 */
@Entity
@Table(name="PartnerUser")
@NamedQuery(name="PartnerUser.findAll", query="SELECT p FROM PartnerUser p")
public class PartnerUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerUserId;

	private Byte active;

	@Column(length=50)
	private String email;

	@Column(length=150)
	private String emergencyPhone;

	@Column(length=150)
	private String fax;

	@Column(length=150)
	private String firstName;

	private Byte isPrimary;

	@Column(length=150)
	private String lastName;

	@Column(length=150)
	private String phone;

	@Column(length=300)
	private String photo;

	@Column(length=150)
	private String skypeId;

	@Column(length=150)
	private String title;

	//bi-directional many-to-one association to PartnerPermission
	@OneToOne(mappedBy="partnerUser", fetch = FetchType.EAGER , cascade = { CascadeType.ALL })
   private PartnerPermission partnerPermissions;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="loginId")
	private Login login;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId")
	private Partner partner;

	//bi-directional many-to-one association to PartnerUserRole
	@OneToMany(mappedBy="partnerUser")
	private List<PartnerUserRole> partnerUserRoles;

	//bi-directional many-to-one association to LookupGender
	@ManyToOne
	@JoinColumn(name="genderId")
	private LookupGender lookupGender;

	//bi-directional many-to-one association to Salutation
	@ManyToOne
	@JoinColumn(name="salutationId")
	private Salutation salutation;

	public PartnerUser() {
	}

	public Integer getPartnerUserId() {
		return this.partnerUserId;
	}

	public void setPartnerUserId(Integer partnerUserId) {
		this.partnerUserId = partnerUserId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
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

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Byte getIsPrimary() {
		return this.isPrimary;
	}

	public void setIsPrimary(Byte isPrimary) {
		this.isPrimary = isPrimary;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getSkypeId() {
		return this.skypeId;
	}

	public void setSkypeId(String skypeId) {
		this.skypeId = skypeId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PartnerPermission getPartnerPermissions() {
		return this.partnerPermissions;
	}

	public void setPartnerPermissions(PartnerPermission partnerPermissions) {
		this.partnerPermissions = partnerPermissions;
	}

	public PartnerPermission addPartnerPermission(PartnerPermission partnerPermission) {
		partnerPermission.setPartnerUser(this);
		return partnerPermission;
	}

	public PartnerPermission removePartnerPermission(PartnerPermission partnerPermission) {
		partnerPermission.setPartnerUser(null);
		return partnerPermission;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public List<PartnerUserRole> getPartnerUserRoles() {
		return this.partnerUserRoles;
	}

	public void setPartnerUserRoles(List<PartnerUserRole> partnerUserRoles) {
		this.partnerUserRoles = partnerUserRoles;
	}

	public PartnerUserRole addPartnerUserRole(PartnerUserRole partnerUserRole) {
		getPartnerUserRoles().add(partnerUserRole);
		partnerUserRole.setPartnerUser(this);

		return partnerUserRole;
	}

	public PartnerUserRole removePartnerUserRole(PartnerUserRole partnerUserRole) {
		getPartnerUserRoles().remove(partnerUserRole);
		partnerUserRole.setPartnerUser(null);

		return partnerUserRole;
	}

	public LookupGender getLookupGender() {
		return this.lookupGender;
	}

	public void setLookupGender(LookupGender lookupGender) {
		this.lookupGender = lookupGender;
	}

	public Salutation getSalutation() {
		return this.salutation;
	}

	public void setSalutation(Salutation salutation) {
		this.salutation = salutation;
	}

}