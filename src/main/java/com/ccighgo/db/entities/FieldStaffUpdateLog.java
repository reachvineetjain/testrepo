package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the FieldStaffUpdateLog database table.
 * 
 */
@Entity
@Table(name="FieldStaffUpdateLog")
@NamedQuery(name="FieldStaffUpdateLog.findAll", query="SELECT f FROM FieldStaffUpdateLog f")
public class FieldStaffUpdateLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int fieldStaffUpdateLogId;

	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Lob
	private String updateLogObject;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="fieldStaffGoId")
	private FieldStaff fieldStaff;

	public FieldStaffUpdateLog() {
	}

	public int getFieldStaffUpdateLogId() {
		return this.fieldStaffUpdateLogId;
	}

	public void setFieldStaffUpdateLogId(int fieldStaffUpdateLogId) {
		this.fieldStaffUpdateLogId = fieldStaffUpdateLogId;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdateLogObject() {
		return this.updateLogObject;
	}

	public void setUpdateLogObject(String updateLogObject) {
		this.updateLogObject = updateLogObject;
	}

	public FieldStaff getFieldStaff() {
		return this.fieldStaff;
	}

	public void setFieldStaff(FieldStaff fieldStaff) {
		this.fieldStaff = fieldStaff;
	}

}