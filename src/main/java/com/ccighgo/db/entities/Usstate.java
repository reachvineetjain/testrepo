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
@NamedQuery(name="Usstate.findAll", query="SELECT u FROM Usstate u")
public class Usstate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=5)
	private String stateCode;

	@Column(nullable=false, length=50)
	private String stateName;

	//bi-directional many-to-one association to Ccistaffuser
	@OneToMany(mappedBy="usstate")
	private List<Ccistaffuser> ccistaffusers;

	public Usstate() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Ccistaffuser> getCcistaffusers() {
		return this.ccistaffusers;
	}

	public void setCcistaffusers(List<Ccistaffuser> ccistaffusers) {
		this.ccistaffusers = ccistaffusers;
	}

	public Ccistaffuser addCcistaffuser(Ccistaffuser ccistaffuser) {
		getCcistaffusers().add(ccistaffuser);
		ccistaffuser.setUsstate(this);

		return ccistaffuser;
	}

	public Ccistaffuser removeCcistaffuser(Ccistaffuser ccistaffuser) {
		getCcistaffusers().remove(ccistaffuser);
		ccistaffuser.setUsstate(null);

		return ccistaffuser;
	}

}