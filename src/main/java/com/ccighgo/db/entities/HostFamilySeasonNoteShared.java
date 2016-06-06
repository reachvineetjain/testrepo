package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the HostFamilySeasonNoteShared database table.
 * 
 */
@Entity
@Table(name="HostFamilySeasonNoteShared")
@NamedQuery(name="HostFamilySeasonNoteShared.findAll", query="SELECT h FROM HostFamilySeasonNoteShared h")
public class HostFamilySeasonNoteShared implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int hostFamilySeasonNoteSharedId;

	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	private int modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	@Lob
	private String note;

	@Column(nullable=false)
	private int sharedByCciGoId;

	//bi-directional many-to-one association to HostFamilySeasonNote
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonNoteId", nullable=false)
	private HostFamilySeasonNote hostFamilySeasonNote;

	//bi-directional many-to-one association to HostFamilySeasonNoteSharedDetail
	@OneToMany(mappedBy="hostFamilySeasonNoteShared")
	private List<HostFamilySeasonNoteSharedDetail> hostFamilySeasonNoteSharedDetails;

	public HostFamilySeasonNoteShared() {
	}

	public int getHostFamilySeasonNoteSharedId() {
		return this.hostFamilySeasonNoteSharedId;
	}

	public void setHostFamilySeasonNoteSharedId(int hostFamilySeasonNoteSharedId) {
		this.hostFamilySeasonNoteSharedId = hostFamilySeasonNoteSharedId;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getSharedByCciGoId() {
		return this.sharedByCciGoId;
	}

	public void setSharedByCciGoId(int sharedByCciGoId) {
		this.sharedByCciGoId = sharedByCciGoId;
	}

	public HostFamilySeasonNote getHostFamilySeasonNote() {
		return this.hostFamilySeasonNote;
	}

	public void setHostFamilySeasonNote(HostFamilySeasonNote hostFamilySeasonNote) {
		this.hostFamilySeasonNote = hostFamilySeasonNote;
	}

	public List<HostFamilySeasonNoteSharedDetail> getHostFamilySeasonNoteSharedDetails() {
		return this.hostFamilySeasonNoteSharedDetails;
	}

	public void setHostFamilySeasonNoteSharedDetails(List<HostFamilySeasonNoteSharedDetail> hostFamilySeasonNoteSharedDetails) {
		this.hostFamilySeasonNoteSharedDetails = hostFamilySeasonNoteSharedDetails;
	}

	public HostFamilySeasonNoteSharedDetail addHostFamilySeasonNoteSharedDetail(HostFamilySeasonNoteSharedDetail hostFamilySeasonNoteSharedDetail) {
		getHostFamilySeasonNoteSharedDetails().add(hostFamilySeasonNoteSharedDetail);
		hostFamilySeasonNoteSharedDetail.setHostFamilySeasonNoteShared(this);

		return hostFamilySeasonNoteSharedDetail;
	}

	public HostFamilySeasonNoteSharedDetail removeHostFamilySeasonNoteSharedDetail(HostFamilySeasonNoteSharedDetail hostFamilySeasonNoteSharedDetail) {
		getHostFamilySeasonNoteSharedDetails().remove(hostFamilySeasonNoteSharedDetail);
		hostFamilySeasonNoteSharedDetail.setHostFamilySeasonNoteShared(null);

		return hostFamilySeasonNoteSharedDetail;
	}

}