package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the HostFamilySeasonNoteTopics database table.
 * 
 */
@Entity
@Table(name="HostFamilySeasonNoteTopics")
@NamedQuery(name="HostFamilySeasonNoteTopic.findAll", query="SELECT h FROM HostFamilySeasonNoteTopic h")
public class HostFamilySeasonNoteTopic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilySeasonNoteTopicsId;

	private Integer createdBy;

	private Timestamp createdOn;

	private Byte isPublic;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	@Column(length=50)
	private String title;

	@Column(length=50)
	private String topicName;

	//bi-directional many-to-one association to HostFamilySeasonNote
	@OneToMany(mappedBy="hostFamilySeasonNoteTopic")
	private List<HostFamilySeasonNote> hostFamilySeasonNotes;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId")
	private HostFamilySeason hostFamilySeason;

	public HostFamilySeasonNoteTopic() {
	}

	public Integer getHostFamilySeasonNoteTopicsId() {
		return this.hostFamilySeasonNoteTopicsId;
	}

	public void setHostFamilySeasonNoteTopicsId(Integer hostFamilySeasonNoteTopicsId) {
		this.hostFamilySeasonNoteTopicsId = hostFamilySeasonNoteTopicsId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Byte getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(Byte isPublic) {
		this.isPublic = isPublic;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTopicName() {
		return this.topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public List<HostFamilySeasonNote> getHostFamilySeasonNotes() {
		return this.hostFamilySeasonNotes;
	}

	public void setHostFamilySeasonNotes(List<HostFamilySeasonNote> hostFamilySeasonNotes) {
		this.hostFamilySeasonNotes = hostFamilySeasonNotes;
	}

	public HostFamilySeasonNote addHostFamilySeasonNote(HostFamilySeasonNote hostFamilySeasonNote) {
		getHostFamilySeasonNotes().add(hostFamilySeasonNote);
		hostFamilySeasonNote.setHostFamilySeasonNoteTopic(this);

		return hostFamilySeasonNote;
	}

	public HostFamilySeasonNote removeHostFamilySeasonNote(HostFamilySeasonNote hostFamilySeasonNote) {
		getHostFamilySeasonNotes().remove(hostFamilySeasonNote);
		hostFamilySeasonNote.setHostFamilySeasonNoteTopic(null);

		return hostFamilySeasonNote;
	}

	public HostFamilySeason getHostFamilySeason() {
		return this.hostFamilySeason;
	}

	public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
		this.hostFamilySeason = hostFamilySeason;
	}

}