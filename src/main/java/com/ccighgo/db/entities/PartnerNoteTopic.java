package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PartnerNoteTopics database table.
 * 
 */
@Entity
@Table(name="PartnerNoteTopics")
@NamedQuery(name="PartnerNoteTopic.findAll", query="SELECT p FROM PartnerNoteTopic p")
public class PartnerNoteTopic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerNoteTopicId;
	
	private Boolean isPublic;

	private byte competitorInfo;

	@Column(name="`embassy/VisaInfo`")
	private byte embassy_VisaInfo;

	private byte f1;

	private byte ght;

	private byte intern;

	private byte j1;

	@Column(name="`meeting/visit`")
	private byte meeting_visit;

	@Column(length=50)
	private String partnerNoteTopicName;

	private byte seasonInfo;

	private byte stInbound;

	private byte trainee;

	@Column(name="`w&t`")
	private byte w_t;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId")
	private Partner partner;

	//bi-directional many-to-one association to PartnerNote
	@OneToMany(mappedBy="partnerNoteTopic")
	private List<PartnerNote> partnerNotes;

	public PartnerNoteTopic() {
	}

	public Integer getPartnerNoteTopicId() {
		return this.partnerNoteTopicId;
	}

	public void setPartnerNoteTopicId(Integer partnerNoteTopicId) {
		this.partnerNoteTopicId = partnerNoteTopicId;
	}

	public Boolean isPublic() {
      return isPublic;
   }

   public void setPublic(Boolean isPublic) {
      this.isPublic = isPublic;
   }

   public byte getCompetitorInfo() {
		return this.competitorInfo;
	}

	public void setCompetitorInfo(byte competitorInfo) {
		this.competitorInfo = competitorInfo;
	}

	public byte getEmbassy_VisaInfo() {
		return this.embassy_VisaInfo;
	}

	public void setEmbassy_VisaInfo(byte embassy_VisaInfo) {
		this.embassy_VisaInfo = embassy_VisaInfo;
	}

	public byte getF1() {
		return this.f1;
	}

	public void setF1(byte f1) {
		this.f1 = f1;
	}

	public byte getGht() {
		return this.ght;
	}

	public void setGht(byte ght) {
		this.ght = ght;
	}

	public byte getIntern() {
		return this.intern;
	}

	public void setIntern(byte intern) {
		this.intern = intern;
	}

	public byte getJ1() {
		return this.j1;
	}

	public void setJ1(byte j1) {
		this.j1 = j1;
	}

	public byte getMeeting_visit() {
		return this.meeting_visit;
	}

	public void setMeeting_visit(byte meeting_visit) {
		this.meeting_visit = meeting_visit;
	}

	public String getPartnerNoteTopicName() {
		return this.partnerNoteTopicName;
	}

	public void setPartnerNoteTopicName(String partnerNoteTopicName) {
		this.partnerNoteTopicName = partnerNoteTopicName;
	}

	public byte getSeasonInfo() {
		return this.seasonInfo;
	}

	public void setSeasonInfo(byte seasonInfo) {
		this.seasonInfo = seasonInfo;
	}

	public byte getStInbound() {
		return this.stInbound;
	}

	public void setStInbound(byte stInbound) {
		this.stInbound = stInbound;
	}

	public byte getTrainee() {
		return this.trainee;
	}

	public void setTrainee(byte trainee) {
		this.trainee = trainee;
	}

	public byte getW_t() {
		return this.w_t;
	}

	public void setW_t(byte w_t) {
		this.w_t = w_t;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public List<PartnerNote> getPartnerNotes() {
		return this.partnerNotes;
	}

	public void setPartnerNotes(List<PartnerNote> partnerNotes) {
		this.partnerNotes = partnerNotes;
	}

	public PartnerNote addPartnerNote(PartnerNote partnerNote) {
		getPartnerNotes().add(partnerNote);
		partnerNote.setPartnerNoteTopic(this);

		return partnerNote;
	}

	public PartnerNote removePartnerNote(PartnerNote partnerNote) {
		getPartnerNotes().remove(partnerNote);
		partnerNote.setPartnerNoteTopic(null);

		return partnerNote;
	}

}