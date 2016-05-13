package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the PartnerSeasonNoteTopics database table.
 * 
 */
@Entity
@Table(name="PartnerSeasonNoteTopics")
@NamedQuery(name="PartnerSeasonNoteTopic.findAll", query="SELECT p FROM PartnerSeasonNoteTopic p")
public class PartnerSeasonNoteTopic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerSeasonNoteTopicId;

	private Byte competitorInfo;

	private Integer createdBy;

	private Timestamp createdOn;

	@Column(name="`embassy/VisaInfo`")
	private Byte embassy_VisaInfo;

	private Byte f1;

	private Byte ght;

	private Byte intern;

	private Byte isPublic;

	private Byte isVisibleToPartner;

	private Byte j1;

	@Column(name="`meeting/visit`")
	private Byte meeting_visit;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	@Column(length=50)
	private String partnerSeasonNoteTopicName;

	private Byte seasonInfo;

	private Byte stInbound;

	private Byte trainee;

	@Column(name="`w&t`")
	private Byte w_t;

	//bi-directional many-to-one association to PartnerSeason
	@ManyToOne
	@JoinColumn(name="partnerSeasonId")
	private PartnerSeason partnerSeason;

	//bi-directional many-to-one association to PartnerSeasonNote
	@OneToMany(mappedBy="partnerSeasonNoteTopic")
	private List<PartnerSeasonNote> partnerSeasonNotes;

	public PartnerSeasonNoteTopic() {
	}

	public Integer getPartnerSeasonNoteTopicId() {
		return this.partnerSeasonNoteTopicId;
	}

	public void setPartnerSeasonNoteTopicId(Integer partnerSeasonNoteTopicId) {
		this.partnerSeasonNoteTopicId = partnerSeasonNoteTopicId;
	}

	public Byte getCompetitorInfo() {
		return this.competitorInfo;
	}

	public void setCompetitorInfo(Byte competitorInfo) {
		this.competitorInfo = competitorInfo;
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

	public Byte getEmbassy_VisaInfo() {
		return this.embassy_VisaInfo;
	}

	public void setEmbassy_VisaInfo(Byte embassy_VisaInfo) {
		this.embassy_VisaInfo = embassy_VisaInfo;
	}

	public Byte getF1() {
		return this.f1;
	}

	public void setF1(Byte f1) {
		this.f1 = f1;
	}

	public Byte getGht() {
		return this.ght;
	}

	public void setGht(Byte ght) {
		this.ght = ght;
	}

	public Byte getIntern() {
		return this.intern;
	}

	public void setIntern(Byte intern) {
		this.intern = intern;
	}

	public Byte getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(Byte isPublic) {
		this.isPublic = isPublic;
	}

	public Byte getIsVisibleToPartner() {
		return this.isVisibleToPartner;
	}

	public void setIsVisibleToPartner(Byte isVisibleToPartner) {
		this.isVisibleToPartner = isVisibleToPartner;
	}

	public Byte getJ1() {
		return this.j1;
	}

	public void setJ1(Byte j1) {
		this.j1 = j1;
	}

	public Byte getMeeting_visit() {
		return this.meeting_visit;
	}

	public void setMeeting_visit(Byte meeting_visit) {
		this.meeting_visit = meeting_visit;
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

	public String getPartnerSeasonNoteTopicName() {
		return this.partnerSeasonNoteTopicName;
	}

	public void setPartnerSeasonNoteTopicName(String partnerSeasonNoteTopicName) {
		this.partnerSeasonNoteTopicName = partnerSeasonNoteTopicName;
	}

	public Byte getSeasonInfo() {
		return this.seasonInfo;
	}

	public void setSeasonInfo(Byte seasonInfo) {
		this.seasonInfo = seasonInfo;
	}

	public Byte getStInbound() {
		return this.stInbound;
	}

	public void setStInbound(Byte stInbound) {
		this.stInbound = stInbound;
	}

	public Byte getTrainee() {
		return this.trainee;
	}

	public void setTrainee(Byte trainee) {
		this.trainee = trainee;
	}

	public Byte getW_t() {
		return this.w_t;
	}

	public void setW_t(Byte w_t) {
		this.w_t = w_t;
	}

	public PartnerSeason getPartnerSeason() {
		return this.partnerSeason;
	}

	public void setPartnerSeason(PartnerSeason partnerSeason) {
		this.partnerSeason = partnerSeason;
	}

	public List<PartnerSeasonNote> getPartnerSeasonNotes() {
		return this.partnerSeasonNotes;
	}

	public void setPartnerSeasonNotes(List<PartnerSeasonNote> partnerSeasonNotes) {
		this.partnerSeasonNotes = partnerSeasonNotes;
	}

	public PartnerSeasonNote addPartnerSeasonNote(PartnerSeasonNote partnerSeasonNote) {
		getPartnerSeasonNotes().add(partnerSeasonNote);
		partnerSeasonNote.setPartnerSeasonNoteTopic(this);

		return partnerSeasonNote;
	}

	public PartnerSeasonNote removePartnerSeasonNote(PartnerSeasonNote partnerSeasonNote) {
		getPartnerSeasonNotes().remove(partnerSeasonNote);
		partnerSeasonNote.setPartnerSeasonNoteTopic(null);

		return partnerSeasonNote;
	}

}