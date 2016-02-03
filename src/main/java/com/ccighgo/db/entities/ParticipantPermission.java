package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ParticipantPermissions database table.
 * 
 */
@Entity
@Table(name="ParticipantPermissions")
@NamedQuery(name="ParticipantPermission.findAll", query="SELECT p FROM ParticipantPermission p")
public class ParticipantPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer participantPermissionsId;

	//bi-directional many-to-one association to Participant
	@ManyToOne
	@JoinColumn(name="participantGoId",insertable=false,updatable=false)
	private Participant participant;

	public ParticipantPermission() {
	}

	public Integer getParticipantPermissionsId() {
		return this.participantPermissionsId;
	}

	public void setParticipantPermissionsId(Integer participantPermissionsId) {
		this.participantPermissionsId = participantPermissionsId;
	}

	public Participant getParticipant() {
		return this.participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

}