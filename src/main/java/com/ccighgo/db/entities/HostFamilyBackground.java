package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilyBackground database table.
 * 
 */
@Entity
@Table(name="HostFamilyBackground")
@NamedQuery(name="HostFamilyBackground.findAll", query="SELECT h FROM HostFamilyBackground h")
public class HostFamilyBackground implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyBackgroundId;

	@Column(length=100)
	private String account;

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Temporal(TemporalType.DATE)
	private Date dateCheckedByCCI;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOrderClosed;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOrderReceived;

	@Column(length=50)
	private String firstName;

	@Column(length=50)
	private String lastName;

	private short password;

	@Column(length=50)
	private String relationshipToHostParent;

	@Column(nullable=false, length=100)
	private String resultStatus;

	@Column(length=200)
	private String resultURL;

	@Column(nullable=false)
	private Byte status;

	private Integer userId;

	//bi-directional many-to-one association to HostFamily
	@ManyToOne
	@JoinColumn(name="hostFamilyGoId")
	private HostFamily hostFamily;

	//bi-directional many-to-one association to HostFamilyMember
	@ManyToOne
	@JoinColumn(name="hostFamilyMemberId")
	private HostFamilyMember hostFamilyMember;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId")
	private HostFamilySeason hostFamilySeason;

	public HostFamilyBackground() {
	}

	public Integer getHostFamilyBackgroundId() {
		return this.hostFamilyBackgroundId;
	}

	public void setHostFamilyBackgroundId(Integer hostFamilyBackgroundId) {
		this.hostFamilyBackgroundId = hostFamilyBackgroundId;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getDateCheckedByCCI() {
		return this.dateCheckedByCCI;
	}

	public void setDateCheckedByCCI(Date dateCheckedByCCI) {
		this.dateCheckedByCCI = dateCheckedByCCI;
	}

	public Date getDateOrderClosed() {
		return this.dateOrderClosed;
	}

	public void setDateOrderClosed(Date dateOrderClosed) {
		this.dateOrderClosed = dateOrderClosed;
	}

	public Date getDateOrderReceived() {
		return this.dateOrderReceived;
	}

	public void setDateOrderReceived(Date dateOrderReceived) {
		this.dateOrderReceived = dateOrderReceived;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public short getPassword() {
		return this.password;
	}

	public void setPassword(short password) {
		this.password = password;
	}

	public String getRelationshipToHostParent() {
		return this.relationshipToHostParent;
	}

	public void setRelationshipToHostParent(String relationshipToHostParent) {
		this.relationshipToHostParent = relationshipToHostParent;
	}

	public String getResultStatus() {
		return this.resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getResultURL() {
		return this.resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public HostFamily getHostFamily() {
		return this.hostFamily;
	}

	public void setHostFamily(HostFamily hostFamily) {
		this.hostFamily = hostFamily;
	}

	public HostFamilyMember getHostFamilyMember() {
		return this.hostFamilyMember;
	}

	public void setHostFamilyMember(HostFamilyMember hostFamilyMember) {
		this.hostFamilyMember = hostFamilyMember;
	}

	public HostFamilySeason getHostFamilySeason() {
		return this.hostFamilySeason;
	}

	public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
		this.hostFamilySeason = hostFamilySeason;
	}

}