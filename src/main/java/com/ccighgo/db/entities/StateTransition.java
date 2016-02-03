package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the StateTransitions database table.
 * 
 */
@Entity
@Table(name="StateTransitions")
@NamedQuery(name="StateTransition.findAll", query="SELECT s FROM StateTransition s")
public class StateTransition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer stateTransitionId;

	private String fromRoletype;

	private String toRoleType;

	//bi-directional many-to-one association to StateType
	@ManyToOne
	@JoinColumn(name="fromStateTypeId",insertable=false,updatable=false)
	private StateType stateType1;

	//bi-directional many-to-one association to StateType
	@ManyToOne
	@JoinColumn(name="toStateTypeId",insertable=false,updatable=false)
	private StateType stateType2;

	public StateTransition() {
	}

	public Integer getStateTransitionId() {
		return this.stateTransitionId;
	}

	public void setStateTransitionId(Integer stateTransitionId) {
		this.stateTransitionId = stateTransitionId;
	}

	public String getFromRoletype() {
		return this.fromRoletype;
	}

	public void setFromRoletype(String fromRoletype) {
		this.fromRoletype = fromRoletype;
	}

	public String getToRoleType() {
		return this.toRoleType;
	}

	public void setToRoleType(String toRoleType) {
		this.toRoleType = toRoleType;
	}

	public StateType getStateType1() {
		return this.stateType1;
	}

	public void setStateType1(StateType stateType1) {
		this.stateType1 = stateType1;
	}

	public StateType getStateType2() {
		return this.stateType2;
	}

	public void setStateType2(StateType stateType2) {
		this.stateType2 = stateType2;
	}

}