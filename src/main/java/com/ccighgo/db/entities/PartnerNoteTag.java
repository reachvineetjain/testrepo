package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PartnerNoteTags database table.
 * 
 */
@Entity
@Table(name="PartnerNoteTags")
@NamedQuery(name="PartnerNoteTag.findAll", query="SELECT p FROM PartnerNoteTag p")
public class PartnerNoteTag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer partnerNoteTagId;

	private String tagName;

	public PartnerNoteTag() {
	}

	public Integer getPartnerNoteTagId() {
		return this.partnerNoteTagId;
	}

	public void setPartnerNoteTagId(Integer partnerNoteTagId) {
		this.partnerNoteTagId = partnerNoteTagId;
	}

	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}