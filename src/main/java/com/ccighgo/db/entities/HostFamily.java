package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the HostFamily database table.
 * 
 */
@Entity
@Table(name="HostFamily")
@NamedQuery(name="HostFamily.findAll", query="SELECT h FROM HostFamily h")
public class HostFamily implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyGoId;

	private Integer hostFamilyStatusId;

	//bi-directional one-to-one association to GoIdSequence
	@OneToOne
	@JoinColumn(name="hostFamilyGoId", nullable=false, insertable=false, updatable=false)
	private GoIdSequence goIdSequence;

	//bi-directional many-to-one association to HostFamilyPermission
	@OneToMany(mappedBy="hostFamily")
	private List<HostFamilyPermission> hostFamilyPermissions;

	public HostFamily() {
	}

	public Integer getHostFamilyGoId() {
		return this.hostFamilyGoId;
	}

	public void setHostFamilyGoId(Integer hostFamilyGoId) {
		this.hostFamilyGoId = hostFamilyGoId;
	}

	public Integer getHostFamilyStatusId() {
		return this.hostFamilyStatusId;
	}

	public void setHostFamilyStatusId(Integer hostFamilyStatusId) {
		this.hostFamilyStatusId = hostFamilyStatusId;
	}

	public GoIdSequence getGoIdSequence() {
		return this.goIdSequence;
	}

	public void setGoIdSequence(GoIdSequence goIdSequence) {
		this.goIdSequence = goIdSequence;
	}

	public List<HostFamilyPermission> getHostFamilyPermissions() {
		return this.hostFamilyPermissions;
	}

	public void setHostFamilyPermissions(List<HostFamilyPermission> hostFamilyPermissions) {
		this.hostFamilyPermissions = hostFamilyPermissions;
	}

	public HostFamilyPermission addHostFamilyPermission(HostFamilyPermission hostFamilyPermission) {
		getHostFamilyPermissions().add(hostFamilyPermission);
		hostFamilyPermission.setHostFamily(this);

		return hostFamilyPermission;
	}

	public HostFamilyPermission removeHostFamilyPermission(HostFamilyPermission hostFamilyPermission) {
		getHostFamilyPermissions().remove(hostFamilyPermission);
		hostFamilyPermission.setHostFamily(null);

		return hostFamilyPermission;
	}

}