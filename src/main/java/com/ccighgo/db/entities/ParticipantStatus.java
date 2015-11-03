package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ParticipantStatus database table.
 * 
 */
@Entity
@Table(name="ParticipantStatus")
@NamedQuery(name="ParticipantStatus.findAll", query="SELECT p FROM ParticipantStatus p")
public class ParticipantStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer participantStatusId;

	private Byte active;

	@Column(nullable=false, length=50)
	private String participantStatusName;

	//bi-directional many-to-one association to Participant
	@OneToMany(mappedBy="participantStatus")
	private List<Participant> participants;

	public ParticipantStatus() {
	}

	public Integer getParticipantStatusId() {
		return this.participantStatusId;
	}

	public void setParticipantStatusId(Integer participantStatusId) {
		this.participantStatusId = participantStatusId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public String getParticipantStatusName() {
		return this.participantStatusName;
	}

	public void setParticipantStatusName(String participantStatusName) {
		this.participantStatusName = participantStatusName;
	}

	public List<Participant> getParticipants() {
		return this.participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public Participant addParticipant(Participant participant) {
		getParticipants().add(participant);
		participant.setParticipantStatus(this);

		return participant;
	}

	public Participant removeParticipant(Participant participant) {
		getParticipants().remove(participant);
		participant.setParticipantStatus(null);

		return participant;
	}

}