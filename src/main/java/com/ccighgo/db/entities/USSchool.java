package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USSchool database table.
 * 
 */
@Entity
@Table(name="USSchool")
@NamedQuery(name="USSchool.findAll", query="SELECT u FROM USSchool u")
public class USSchool implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int usSchoolId;

	public USSchool() {
	}

	public int getUsSchoolId() {
		return this.usSchoolId;
	}

	public void setUsSchoolId(int usSchoolId) {
		this.usSchoolId = usSchoolId;
	}

}