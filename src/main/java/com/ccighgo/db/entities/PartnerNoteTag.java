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
	@Column(unique=true, nullable=false)
	private int partnerNoteTagId;

	@Column(length=50)
	private String tagName;

	public PartnerNoteTag() {
	}

	public int getPartnerNoteTagId() {
		return this.partnerNoteTagId;
	}

	public void setPartnerNoteTagId(int partnerNoteTagId) {
		this.partnerNoteTagId = partnerNoteTagId;
	}

	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}