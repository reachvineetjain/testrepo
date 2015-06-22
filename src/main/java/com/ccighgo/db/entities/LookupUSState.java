package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the LookupUSStates database table.
 * 
 */
@Entity
@Table(name="LookupUSStates")
@NamedQuery(name="LookupUSState.findAll", query="SELECT l FROM LookupUSState l")
public class LookupUSState implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int usStatesId;

	@Column(nullable=false, length=5)
	private String stateCode;

	@Column(nullable=false, length=50)
	private String stateName;

	//bi-directional many-to-one association to CCIStaffUser
	@OneToMany(mappedBy="lookupUsstate")
	private List<CCIStaffUser> ccistaffUsers;

	public LookupUSState() {
	}

	public int getUsStatesId() {
		return this.usStatesId;
	}

	public void setUsStatesId(int usStatesId) {
		this.usStatesId = usStatesId;
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
		ccistaffUser.setLookupUsstate(this);

		return ccistaffUser;
	}

	public CCIStaffUser removeCcistaffUser(CCIStaffUser ccistaffUser) {
		getCcistaffUsers().remove(ccistaffUser);
		ccistaffUser.setLookupUsstate(null);

		return ccistaffUser;
	}

}