package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the USStates database table.
 * 
 */
@Entity
@Table(name="USStates")
@NamedQuery(name="USState.findAll", query="SELECT u FROM USState u")
public class USState implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int usstatesID;

	@Column(nullable=false)
	private byte active;

	@Column(nullable=false, length=5)
	private String stateCode;

	@Column(nullable=false, length=50)
	private String stateName;

	//bi-directional many-to-one association to CCIStaffUser
	@OneToMany(mappedBy="usstate")
	private List<CCIStaffUser> ccistaffUsers;

	public USState() {
	}

	public int getUsstatesID() {
		return this.usstatesID;
	}

	public void setUsstatesID(int usstatesID) {
		this.usstatesID = usstatesID;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getStateCode() {
		return this.stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public List<CCIStaffUser> getCcistaffUsers() {
		return this.ccistaffUsers;
	}

	public void setCcistaffUsers(List<CCIStaffUser> ccistaffUsers) {
		this.ccistaffUsers = ccistaffUsers;
	}

	public CCIStaffUser addCcistaffUser(CCIStaffUser ccistaffUser) {
		getCcistaffUsers().add(ccistaffUser);
		ccistaffUser.setUsstate(this);

		return ccistaffUser;
	}

	public CCIStaffUser removeCcistaffUser(CCIStaffUser ccistaffUser) {
		getCcistaffUsers().remove(ccistaffUser);
		ccistaffUser.setUsstate(null);

		return ccistaffUser;
	}

}