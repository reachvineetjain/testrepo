package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the department_resourcegroups database table.
 * 
 */
@Entity
@Table(name="department_resourcegroups")
@NamedQuery(name="DepartmentResourcegroup.findAll", query="SELECT d FROM DepartmentResourcegroup d")
public class DepartmentResourcegroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DepartmentResourcegroupPK id;

	public DepartmentResourcegroup() {
	}

	public DepartmentResourcegroupPK getId() {
		return this.id;
	}

	public void setId(DepartmentResourcegroupPK id) {
		this.id = id;
	}

}