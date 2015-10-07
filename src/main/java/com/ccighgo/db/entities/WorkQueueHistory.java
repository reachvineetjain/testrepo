package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the WorkQueueHistory database table.
 * 
 */
@Entity
@Table(name="WorkQueueHistory")
@NamedQuery(name="WorkQueueHistory.findAll", query="SELECT w FROM WorkQueueHistory w")
public class WorkQueueHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer workQueueHistoryId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private Integer fromStateTypeId;

	private Integer shooterGoId;

	@Column(length=50)
	private String shooterRoleType;

	private Integer targetGoId;

	@Column(length=50)
	private String targetRoleType;

	private Integer toStateTypeId;

	public WorkQueueHistory() {
	}

	public Integer getWorkQueueHistoryId() {
		return this.workQueueHistoryId;
	}

	public void setWorkQueueHistoryId(Integer workQueueHistoryId) {
		this.workQueueHistoryId = workQueueHistoryId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getFromStateTypeId() {
		return this.fromStateTypeId;
	}

	public void setFromStateTypeId(Integer fromStateTypeId) {
		this.fromStateTypeId = fromStateTypeId;
	}

	public Integer getShooterGoId() {
		return this.shooterGoId;
	}

	public void setShooterGoId(Integer shooterGoId) {
		this.shooterGoId = shooterGoId;
	}

	public String getShooterRoleType() {
		return this.shooterRoleType;
	}

	public void setShooterRoleType(String shooterRoleType) {
		this.shooterRoleType = shooterRoleType;
	}

	public Integer getTargetGoId() {
		return this.targetGoId;
	}

	public void setTargetGoId(Integer targetGoId) {
		this.targetGoId = targetGoId;
	}

	public String getTargetRoleType() {
		return this.targetRoleType;
	}

	public void setTargetRoleType(String targetRoleType) {
		this.targetRoleType = targetRoleType;
	}

	public Integer getToStateTypeId() {
		return this.toStateTypeId;
	}

	public void setToStateTypeId(Integer toStateTypeId) {
		this.toStateTypeId = toStateTypeId;
	}

}