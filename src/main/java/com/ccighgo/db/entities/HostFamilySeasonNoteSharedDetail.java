package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HostFamilySeasonNoteSharedDetails database table.
 * 
 */
@Entity
@Table(name="HostFamilySeasonNoteSharedDetails")
@NamedQuery(name="HostFamilySeasonNoteSharedDetail.findAll", query="SELECT h FROM HostFamilySeasonNoteSharedDetail h")
public class HostFamilySeasonNoteSharedDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int hostFamilySeasonNoteSharedDetails;

	@Column(nullable=false)
	private int sharedToCciGoId;

	//bi-directional many-to-one association to HostFamilySeasonNoteShared
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonNoteSharedId", nullable=false)
	private HostFamilySeasonNoteShared hostFamilySeasonNoteShared;

	public HostFamilySeasonNoteSharedDetail() {
	}

	public int getHostFamilySeasonNoteSharedDetails() {
		return this.hostFamilySeasonNoteSharedDetails;
	}

	public void setHostFamilySeasonNoteSharedDetails(int hostFamilySeasonNoteSharedDetails) {
		this.hostFamilySeasonNoteSharedDetails = hostFamilySeasonNoteSharedDetails;
	}

	public int getSharedToCciGoId() {
		return this.sharedToCciGoId;
	}

	public void setSharedToCciGoId(int sharedToCciGoId) {
		this.sharedToCciGoId = sharedToCciGoId;
	}

	public HostFamilySeasonNoteShared getHostFamilySeasonNoteShared() {
		return this.hostFamilySeasonNoteShared;
	}

	public void setHostFamilySeasonNoteShared(HostFamilySeasonNoteShared hostFamilySeasonNoteShared) {
		this.hostFamilySeasonNoteShared = hostFamilySeasonNoteShared;
	}

}