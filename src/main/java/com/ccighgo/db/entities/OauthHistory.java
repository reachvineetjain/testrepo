package com.ccighgo.db.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the OauthHistory database table.
 * 
 */
@Entity
@Table(name="OauthHistory")
@NamedQuery(name="OauthHistory.findAll", query="SELECT o FROM OauthHistory o")
public class OauthHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OauthHistoryPK id;

	public OauthHistory() {
	}

	public OauthHistoryPK getId() {
		return this.id;
	}

	public void setId(OauthHistoryPK id) {
		this.id = id;
	}

}