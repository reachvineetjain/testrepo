package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usschool database table.
 * 
 */
@Entity
@Table(name="usschool")
@NamedQuery(name="USSchool.findAll", query="SELECT u FROM USSchool u")
public class USSchool implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int usschoolID;

	public USSchool() {
	}

	public int getUsschoolID() {
		return this.usschoolID;
	}

	public void setUsschoolID(int usschoolID) {
		this.usschoolID = usschoolID;
	}

}