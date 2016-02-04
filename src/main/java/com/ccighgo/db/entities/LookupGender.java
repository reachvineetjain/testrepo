package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the LookupGender database table.
 * 
 */
@Entity
@NamedQuery(name="LookupGender.findAll", query="SELECT l FROM LookupGender l")
public class LookupGender implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer genderId;

	private String genderName;

	//bi-directional many-to-one association to CCIStaffUser
	@OneToMany(mappedBy="lookupGender")
	private List<CCIStaffUser> ccistaffUsers;

	//bi-directional many-to-one association to FieldStaff
	@OneToMany(mappedBy="lookupGender")
	private List<FieldStaff> fieldStaffs;

	//bi-directional many-to-one association to FieldStaffFamilyMember
	@OneToMany(mappedBy="lookupGender")
	private List<FieldStaffFamilyMember> fieldStaffFamilyMembers;

	//bi-directional many-to-one association to HostFamilyMember
	@OneToMany(mappedBy="lookupGender")
	private List<HostFamilyMember> hostFamilyMembers;

	//bi-directional many-to-one association to PartnerUser
	@OneToMany(mappedBy="lookupGender")
	private List<PartnerUser> partnerUsers;

	//bi-directional many-to-one association to SeasonIHPDetail
	@OneToMany(mappedBy="lookupGender")
	private List<SeasonIHPDetail> seasonIhpdetails;

	public LookupGender() {
	}

	public Integer getGenderId() {
		return this.genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public String getGenderName() {
		return this.genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public List<CCIStaffUser> getCcistaffUsers() {
		return this.ccistaffUsers;
	}

	public void setCcistaffUsers(List<CCIStaffUser> ccistaffUsers) {
		this.ccistaffUsers = ccistaffUsers;
	}

	public CCIStaffUser addCcistaffUser(CCIStaffUser ccistaffUser) {
		getCcistaffUsers().add(ccistaffUser);
		ccistaffUser.setLookupGender(this);

		return ccistaffUser;
	}

	public CCIStaffUser removeCcistaffUser(CCIStaffUser ccistaffUser) {
		getCcistaffUsers().remove(ccistaffUser);
		ccistaffUser.setLookupGender(null);

		return ccistaffUser;
	}

	public List<FieldStaff> getFieldStaffs() {
		return this.fieldStaffs;
	}

	public void setFieldStaffs(List<FieldStaff> fieldStaffs) {
		this.fieldStaffs = fieldStaffs;
	}

	public FieldStaff addFieldStaff(FieldStaff fieldStaff) {
		getFieldStaffs().add(fieldStaff);
		fieldStaff.setLookupGender(this);

		return fieldStaff;
	}

	public FieldStaff removeFieldStaff(FieldStaff fieldStaff) {
		getFieldStaffs().remove(fieldStaff);
		fieldStaff.setLookupGender(null);

		return fieldStaff;
	}

	public List<FieldStaffFamilyMember> getFieldStaffFamilyMembers() {
		return this.fieldStaffFamilyMembers;
	}

	public void setFieldStaffFamilyMembers(List<FieldStaffFamilyMember> fieldStaffFamilyMembers) {
		this.fieldStaffFamilyMembers = fieldStaffFamilyMembers;
	}

	public FieldStaffFamilyMember addFieldStaffFamilyMember(FieldStaffFamilyMember fieldStaffFamilyMember) {
		getFieldStaffFamilyMembers().add(fieldStaffFamilyMember);
		fieldStaffFamilyMember.setLookupGender(this);

		return fieldStaffFamilyMember;
	}

	public FieldStaffFamilyMember removeFieldStaffFamilyMember(FieldStaffFamilyMember fieldStaffFamilyMember) {
		getFieldStaffFamilyMembers().remove(fieldStaffFamilyMember);
		fieldStaffFamilyMember.setLookupGender(null);

		return fieldStaffFamilyMember;
	}

	public List<HostFamilyMember> getHostFamilyMembers() {
		return this.hostFamilyMembers;
	}

	public void setHostFamilyMembers(List<HostFamilyMember> hostFamilyMembers) {
		this.hostFamilyMembers = hostFamilyMembers;
	}

	public HostFamilyMember addHostFamilyMember(HostFamilyMember hostFamilyMember) {
		getHostFamilyMembers().add(hostFamilyMember);
		hostFamilyMember.setLookupGender(this);

		return hostFamilyMember;
	}

	public HostFamilyMember removeHostFamilyMember(HostFamilyMember hostFamilyMember) {
		getHostFamilyMembers().remove(hostFamilyMember);
		hostFamilyMember.setLookupGender(null);

		return hostFamilyMember;
	}

	public List<PartnerUser> getPartnerUsers() {
		return this.partnerUsers;
	}

	public void setPartnerUsers(List<PartnerUser> partnerUsers) {
		this.partnerUsers = partnerUsers;
	}

	public PartnerUser addPartnerUser(PartnerUser partnerUser) {
		getPartnerUsers().add(partnerUser);
		partnerUser.setLookupGender(this);

		return partnerUser;
	}

	public PartnerUser removePartnerUser(PartnerUser partnerUser) {
		getPartnerUsers().remove(partnerUser);
		partnerUser.setLookupGender(null);

		return partnerUser;
	}

	public List<SeasonIHPDetail> getSeasonIhpdetails() {
		return this.seasonIhpdetails;
	}

	public void setSeasonIhpdetails(List<SeasonIHPDetail> seasonIhpdetails) {
		this.seasonIhpdetails = seasonIhpdetails;
	}

	public SeasonIHPDetail addSeasonIhpdetail(SeasonIHPDetail seasonIhpdetail) {
		getSeasonIhpdetails().add(seasonIhpdetail);
		seasonIhpdetail.setLookupGender(this);

		return seasonIhpdetail;
	}

	public SeasonIHPDetail removeSeasonIhpdetail(SeasonIHPDetail seasonIhpdetail) {
		getSeasonIhpdetails().remove(seasonIhpdetail);
		seasonIhpdetail.setLookupGender(null);

		return seasonIhpdetail;
	}

}