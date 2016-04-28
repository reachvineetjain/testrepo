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

	private Integer lookupDepartmentProgramId;

	private Integer partnerPermissionsCategoryId;

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

	public Integer getLookupDepartmentProgramId() {
		return this.lookupDepartmentProgramId;
	}

	public void setLookupDepartmentProgramId(Integer lookupDepartmentProgramId) {
		this.lookupDepartmentProgramId = lookupDepartmentProgramId;
	}

	public Integer getPartnerPermissionsCategoryId() {
		return this.partnerPermissionsCategoryId;
	}

	public void setPartnerPermissionsCategoryId(Integer partnerPermissionsCategoryId) {
		this.partnerPermissionsCategoryId = partnerPermissionsCategoryId;
	}

	public PartnerUser getPartnerUser() {
		return this.partnerUser;
	}

	public void setPartnerUser(PartnerUser partnerUser) {
		this.partnerUser = partnerUser;
	}

}