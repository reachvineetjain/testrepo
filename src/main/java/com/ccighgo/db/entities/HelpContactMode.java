package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the HelpContactMode database table.
 * 
 */
@Entity
@NamedQuery(name="HelpContactMode.findAll", query="SELECT h FROM HelpContactMode h")
public class HelpContactMode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer helpContactModeId;

	private Byte active;

	private String helpContactModeName;

	//bi-directional many-to-one association to PartnerHelpRequest
	@OneToMany(mappedBy="helpContactMode")
	private List<PartnerHelpRequest> partnerHelpRequests;

	public HelpContactMode() {
	}

	public Integer getHelpContactModeId() {
		return this.helpContactModeId;
	}

	public void setHelpContactModeId(Integer helpContactModeId) {
		this.helpContactModeId = helpContactModeId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public String getHelpContactModeName() {
		return this.helpContactModeName;
	}

	public void setHelpContactModeName(String helpContactModeName) {
		this.helpContactModeName = helpContactModeName;
	}

	public List<PartnerHelpRequest> getPartnerHelpRequests() {
		return this.partnerHelpRequests;
	}

	public void setPartnerHelpRequests(List<PartnerHelpRequest> partnerHelpRequests) {
		this.partnerHelpRequests = partnerHelpRequests;
	}

	public PartnerHelpRequest addPartnerHelpRequest(PartnerHelpRequest partnerHelpRequest) {
		getPartnerHelpRequests().add(partnerHelpRequest);
		partnerHelpRequest.setHelpContactMode(this);

		return partnerHelpRequest;
	}

	public PartnerHelpRequest removePartnerHelpRequest(PartnerHelpRequest partnerHelpRequest) {
		getPartnerHelpRequests().remove(partnerHelpRequest);
		partnerHelpRequest.setHelpContactMode(null);

		return partnerHelpRequest;
	}

}