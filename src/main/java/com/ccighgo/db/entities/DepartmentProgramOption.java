package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the DepartmentProgramOptions database table.
 * 
 */
@Entity
@Table(name="DepartmentProgramOptions")
@NamedQuery(name="DepartmentProgramOption.findAll", query="SELECT d FROM DepartmentProgramOption d")
public class DepartmentProgramOption implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer departmentProgramOptionId;

	private String programOptionCode;

	private String programOptionName;

	//bi-directional many-to-one association to DepartmentProgram
	@ManyToOne
	@JoinColumn(name="departmentProgramId",insertable=false,updatable=false)
	private DepartmentProgram departmentProgram;

	//bi-directional many-to-one association to Participant
	@OneToMany(mappedBy="departmentProgramOption")
	private List<Participant> participants;

	//bi-directional many-to-one association to PartnerSeasonAllocation
	@OneToMany(mappedBy="departmentProgramOption")
	private List<PartnerSeasonAllocation> partnerSeasonAllocations;

	//bi-directional many-to-one association to SeasonHSPAllocation
	@OneToMany(mappedBy="departmentProgramOption")
	private List<SeasonHSPAllocation> seasonHspallocations;

	//bi-directional many-to-one association to SeasonWPAllocation
	@OneToMany(mappedBy="departmentProgramOption")
	private List<SeasonWPAllocation> seasonWpallocations;

	public DepartmentProgramOption() {
	}

	public Integer getDepartmentProgramOptionId() {
		return this.departmentProgramOptionId;
	}

	public void setDepartmentProgramOptionId(Integer departmentProgramOptionId) {
		this.departmentProgramOptionId = departmentProgramOptionId;
	}

	public String getProgramOptionCode() {
		return this.programOptionCode;
	}

	public void setProgramOptionCode(String programOptionCode) {
		this.programOptionCode = programOptionCode;
	}

	public String getProgramOptionName() {
		return this.programOptionName;
	}

	public void setProgramOptionName(String programOptionName) {
		this.programOptionName = programOptionName;
	}

	public DepartmentProgram getDepartmentProgram() {
		return this.departmentProgram;
	}

	public void setDepartmentProgram(DepartmentProgram departmentProgram) {
		this.departmentProgram = departmentProgram;
	}

	public List<Participant> getParticipants() {
		return this.participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public Participant addParticipant(Participant participant) {
		getParticipants().add(participant);
		participant.setDepartmentProgramOption(this);

		return participant;
	}

	public Participant removeParticipant(Participant participant) {
		getParticipants().remove(participant);
		participant.setDepartmentProgramOption(null);

		return participant;
	}

	public List<PartnerSeasonAllocation> getPartnerSeasonAllocations() {
		return this.partnerSeasonAllocations;
	}

	public void setPartnerSeasonAllocations(List<PartnerSeasonAllocation> partnerSeasonAllocations) {
		this.partnerSeasonAllocations = partnerSeasonAllocations;
	}

	public PartnerSeasonAllocation addPartnerSeasonAllocation(PartnerSeasonAllocation partnerSeasonAllocation) {
		getPartnerSeasonAllocations().add(partnerSeasonAllocation);
		partnerSeasonAllocation.setDepartmentProgramOption(this);

		return partnerSeasonAllocation;
	}

	public PartnerSeasonAllocation removePartnerSeasonAllocation(PartnerSeasonAllocation partnerSeasonAllocation) {
		getPartnerSeasonAllocations().remove(partnerSeasonAllocation);
		partnerSeasonAllocation.setDepartmentProgramOption(null);

		return partnerSeasonAllocation;
	}

	public List<SeasonHSPAllocation> getSeasonHspallocations() {
		return this.seasonHspallocations;
	}

	public void setSeasonHspallocations(List<SeasonHSPAllocation> seasonHspallocations) {
		this.seasonHspallocations = seasonHspallocations;
	}

	public SeasonHSPAllocation addSeasonHspallocation(SeasonHSPAllocation seasonHspallocation) {
		getSeasonHspallocations().add(seasonHspallocation);
		seasonHspallocation.setDepartmentProgramOption(this);

		return seasonHspallocation;
	}

	public SeasonHSPAllocation removeSeasonHspallocation(SeasonHSPAllocation seasonHspallocation) {
		getSeasonHspallocations().remove(seasonHspallocation);
		seasonHspallocation.setDepartmentProgramOption(null);

		return seasonHspallocation;
	}

	public List<SeasonWPAllocation> getSeasonWpallocations() {
		return this.seasonWpallocations;
	}

	public void setSeasonWpallocations(List<SeasonWPAllocation> seasonWpallocations) {
		this.seasonWpallocations = seasonWpallocations;
	}

	public SeasonWPAllocation addSeasonWpallocation(SeasonWPAllocation seasonWpallocation) {
		getSeasonWpallocations().add(seasonWpallocation);
		seasonWpallocation.setDepartmentProgramOption(this);

		return seasonWpallocation;
	}

	public SeasonWPAllocation removeSeasonWpallocation(SeasonWPAllocation seasonWpallocation) {
		getSeasonWpallocations().remove(seasonWpallocation);
		seasonWpallocation.setDepartmentProgramOption(null);

		return seasonWpallocation;
	}

}