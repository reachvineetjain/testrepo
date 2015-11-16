package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HostFamilyPermissions database table.
 * 
 */
@Entity
@Table(name="HostFamilyPermissions")
@NamedQuery(name="HostFamilyPermission.findAll", query="SELECT h FROM HostFamilyPermission h")
public class HostFamilyPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyPermissionsId;

	//bi-directional many-to-one association to HostFamily
	@ManyToOne
	@JoinColumn(name="hostFamilyGoId")
	private HostFamily hostFamily;

	public HostFamilyPermission() {
	}

	public Integer getHostFamilyPermissionsId() {
		return this.hostFamilyPermissionsId;
	}

	public void setHostFamilyPermissionsId(Integer hostFamilyPermissionsId) {
		this.hostFamilyPermissionsId = hostFamilyPermissionsId;
	}

	public HostFamily getHostFamily() {
		return this.hostFamily;
	}

	public void setHostFamily(HostFamily hostFamily) {
		this.hostFamily = hostFamily;
	}

}