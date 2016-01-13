package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the MoveReason database table.
 * 
 */
@Entity
@Table(name="MoveReason")
@NamedQuery(name="MoveReason.findAll", query="SELECT m FROM MoveReason m")
public class MoveReason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int moveReasonId;

	private Byte active;

	@Column(length=2)
	private String changeHomeCode;

	@Column(length=100)
	private String moveReasonName;

	@Column(length=500)
	private String reasonDescription;

	//bi-directional many-to-one association to HostFamilyParticipant
	@OneToMany(mappedBy="moveReason1")
	private List<HostFamilyParticipant> hostFamilyParticipants;

	//bi-directional one-to-one association to HostFamilyParticipant
	@OneToOne
	@JoinColumn(name="moveReasonId", nullable=false, insertable=false, updatable=false)
	private HostFamilyParticipant hostFamilyParticipant;

	public MoveReason() {
	}

	public int getMoveReasonId() {
		return this.moveReasonId;
	}

	public void setMoveReasonId(int moveReasonId) {
		this.moveReasonId = moveReasonId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public String getChangeHomeCode() {
		return this.changeHomeCode;
	}

	public void setChangeHomeCode(String changeHomeCode) {
		this.changeHomeCode = changeHomeCode;
	}

	public String getMoveReasonName() {
		return this.moveReasonName;
	}

	public void setMoveReasonName(String moveReasonName) {
		this.moveReasonName = moveReasonName;
	}

	public String getReasonDescription() {
		return this.reasonDescription;
	}

	public void setReasonDescription(String reasonDescription) {
		this.reasonDescription = reasonDescription;
	}

	public List<HostFamilyParticipant> getHostFamilyParticipants() {
		return this.hostFamilyParticipants;
	}

	public void setHostFamilyParticipants(List<HostFamilyParticipant> hostFamilyParticipants) {
		this.hostFamilyParticipants = hostFamilyParticipants;
	}

	public HostFamilyParticipant addHostFamilyParticipant(HostFamilyParticipant hostFamilyParticipant) {
		getHostFamilyParticipants().add(hostFamilyParticipant);
		hostFamilyParticipant.setMoveReason1(this);

		return hostFamilyParticipant;
	}

	public HostFamilyParticipant removeHostFamilyParticipant(HostFamilyParticipant hostFamilyParticipant) {
		getHostFamilyParticipants().remove(hostFamilyParticipant);
		hostFamilyParticipant.setMoveReason1(null);

		return hostFamilyParticipant;
	}

	public HostFamilyParticipant getHostFamilyParticipant() {
		return this.hostFamilyParticipant;
	}

	public void setHostFamilyParticipant(HostFamilyParticipant hostFamilyParticipant) {
		this.hostFamilyParticipant = hostFamilyParticipant;
	}

}