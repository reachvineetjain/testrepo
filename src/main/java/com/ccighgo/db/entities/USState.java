package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usstates database table.
 * 
 */
@Entity
@Table(name="usstates")
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

	//bi-directional many-to-one association to CciStaffUser
	@OneToMany(mappedBy="usstate")
	private List<CciStaffUser> ccistaffusers;

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

	public List<CciStaffUser> getCcistaffusers() {
		return this.ccistaffusers;
	}

	public void setCcistaffusers(List<CciStaffUser> ccistaffusers) {
		this.ccistaffusers = ccistaffusers;
	}

	public CciStaffUser addCcistaffuser(CciStaffUser ccistaffuser) {
		getCcistaffusers().add(ccistaffuser);
		ccistaffuser.setUsstate(this);

		return ccistaffuser;
	}

	public CciStaffUser removeCcistaffuser(CciStaffUser ccistaffuser) {
		getCcistaffusers().remove(ccistaffuser);
		ccistaffuser.setUsstate(null);

		return ccistaffuser;
	}

}