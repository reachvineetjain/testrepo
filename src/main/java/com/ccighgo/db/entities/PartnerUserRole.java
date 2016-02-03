package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PartnerUserRoles database table.
 * 
 */
@Entity
@Table(name="PartnerUserRoles")
@NamedQuery(name="PartnerUserRole.findAll", query="SELECT p FROM PartnerUserRole p")
public class PartnerUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer partnerUserRoleId;

	private String partnerUserRoleName;

	//bi-directional many-to-one association to PartnerUser
	@ManyToOne
	@JoinColumn(name="partnerUserId",insertable=false,updatable=false)
	private PartnerUser partnerUser;

	public PartnerUserRole() {
	}

	public Integer getPartnerUserRoleId() {
		return this.partnerUserRoleId;
	}

	public void setPartnerUserRoleId(Integer partnerUserRoleId) {
		this.partnerUserRoleId = partnerUserRoleId;
	}

	public String getPartnerUserRoleName() {
		return this.partnerUserRoleName;
	}

	public void setPartnerUserRoleName(String partnerUserRoleName) {
		this.partnerUserRoleName = partnerUserRoleName;
	}

	public PartnerUser getPartnerUser() {
		return this.partnerUser;
	}

	public void setPartnerUser(PartnerUser partnerUser) {
		this.partnerUser = partnerUser;
	}

}