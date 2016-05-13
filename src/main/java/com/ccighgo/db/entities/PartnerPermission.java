package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PartnerPermissions database table.
 * 
 */
@Entity
@Table(name="PartnerPermissions")
@NamedQuery(name="PartnerPermission.findAll", query="SELECT p FROM PartnerPermission p")
public class PartnerPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerPermissionId;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId")
	private LookupDepartmentProgram lookupDepartmentProgram;

	//bi-directional many-to-one association to PartnerPermissionsCategory
	@ManyToOne
	@JoinColumn(name="partnerPermissionsCategoryId")
	private PartnerPermissionsCategory partnerPermissionsCategory;

	//bi-directional many-to-one association to PartnerUser
	@ManyToOne
	@JoinColumn(name="partnerUserId")
	private PartnerUser partnerUser;

	public PartnerPermission() {
	}

	public Integer getPartnerPermissionId() {
		return this.partnerPermissionId;
	}

	public void setPartnerPermissionId(Integer partnerPermissionId) {
		this.partnerPermissionId = partnerPermissionId;
	}

	public LookupDepartmentProgram getLookupDepartmentProgram() {
		return this.lookupDepartmentProgram;
	}

	public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		this.lookupDepartmentProgram = lookupDepartmentProgram;
	}

	public PartnerPermissionsCategory getPartnerPermissionsCategory() {
		return this.partnerPermissionsCategory;
	}

	public void setPartnerPermissionsCategory(PartnerPermissionsCategory partnerPermissionsCategory) {
		this.partnerPermissionsCategory = partnerPermissionsCategory;
	}

	public PartnerUser getPartnerUser() {
		return this.partnerUser;
	}

	public void setPartnerUser(PartnerUser partnerUser) {
		this.partnerUser = partnerUser;
	}

}