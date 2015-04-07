package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cci_season_status database table.
 * 
 */
@Entity
@Table(name="cci_season_status")
@NamedQuery(name="CciSeasonStatus.findAll", query="SELECT c FROM CciSeasonStatus c")
public class CciSeasonStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int seasonStatusID;

	@Column(length=1)
	private String active;

	@Column(length=50)
	private String seasonStatusName;

	private int sequenceNo;

	//bi-directional many-to-one association to CciSeasonProgram
	@OneToMany(mappedBy="cciSeasonStatus")
	private List<CciSeasonProgram> cciSeasonPrograms;

	public CciSeasonStatus() {
	}

	public int getSeasonStatusID() {
		return this.seasonStatusID;
	}

	public void setSeasonStatusID(int seasonStatusID) {
		this.seasonStatusID = seasonStatusID;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getSeasonStatusName() {
		return this.seasonStatusName;
	}

	public void setSeasonStatusName(String seasonStatusName) {
		this.seasonStatusName = seasonStatusName;
	}

	public int getSequenceNo() {
		return this.sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public List<CciSeasonProgram> getCciSeasonPrograms() {
		return this.cciSeasonPrograms;
	}

	public void setCciSeasonPrograms(List<CciSeasonProgram> cciSeasonPrograms) {
		this.cciSeasonPrograms = cciSeasonPrograms;
	}

	public CciSeasonProgram addCciSeasonProgram(CciSeasonProgram cciSeasonProgram) {
		getCciSeasonPrograms().add(cciSeasonProgram);
		cciSeasonProgram.setCciSeasonStatus(this);

		return cciSeasonProgram;
	}

	public CciSeasonProgram removeCciSeasonProgram(CciSeasonProgram cciSeasonProgram) {
		getCciSeasonPrograms().remove(cciSeasonProgram);
		cciSeasonProgram.setCciSeasonStatus(null);

		return cciSeasonProgram;
	}

}