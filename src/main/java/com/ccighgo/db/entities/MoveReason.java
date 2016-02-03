package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the MoveReason database table.
 * 
 */
@Entity
@NamedQuery(name="MoveReason.findAll", query="SELECT m FROM MoveReason m")
public class MoveReason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer moveReasonId;

	private Byte active;

	private String changeHomeCode;

	private String moveReasonName;

	private String reasonDescription;

	//bi-directional many-to-one association to HostFamilyParticipant
	@OneToMany(mappedBy="moveReason1")
	private List<HostFamilyParticipant> hostFamilyParticipants;

	//bi-directional one-to-one association to HostFamilyParticipant
	@OneToOne
	@JoinColumn(name="moveReasonId",insertable=false,updatable=false)
	private HostFamilyParticipant hostFamilyParticipant;

	public MoveReason() {
	}

	public Integer getMoveReasonId() {
		return this.moveReasonId;
	}

	public void setMoveReasonId(Integer moveReasonId) {
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