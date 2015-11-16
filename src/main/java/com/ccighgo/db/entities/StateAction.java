package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the StateActions database table.
 * 
 */
@Entity
@Table(name="StateActions")
@NamedQuery(name="StateAction.findAll", query="SELECT s FROM StateAction s")
public class StateAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer stateActionId;

	@Column(length=50)
	private String action;

	//bi-directional many-to-one association to StateType
	@ManyToOne
	@JoinColumn(name="stateTypeId", nullable=false)
	private StateType stateType;

	public StateAction() {
	}

	public Integer getStateActionId() {
		return this.stateActionId;
	}

	public void setStateActionId(Integer stateActionId) {
		this.stateActionId = stateActionId;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public StateType getStateType() {
		return this.stateType;
	}

	public void setStateType(StateType stateType) {
		this.stateType = stateType;
	}

}